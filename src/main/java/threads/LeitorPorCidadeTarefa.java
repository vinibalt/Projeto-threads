package threads;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LeitorPorCidadeTarefa implements Runnable {

    private String caminhoArquivo;

    public LeitorPorCidadeTarefa(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            String cidade = "";
            String pais = "";
            br.readLine();
            Map<Integer, Map<Integer, DadosMes>> dadosPorMesPorAno = new HashMap<>();

            while ((linha = br.readLine()) != null) {
                RegistroTemperatura registro = parseLinha(linha);
                cidade = registro.getCidade();
                pais = registro.getPais();

                dadosPorMesPorAno.computeIfAbsent(registro.getAno(), ano -> new HashMap<>())
                        .computeIfAbsent(registro.getMes(), mes -> new DadosMes())
                        .adicionarTemperatura(registro.getTemperatura());
            }

            ExecutorService executorServiceMeses = Executors.newFixedThreadPool(25);  // Até 25 threads para anos

            for (Map.Entry<Integer, Map<Integer, DadosMes>> entryAno : dadosPorMesPorAno.entrySet()) {
                int ano = entryAno.getKey();
                Map<Integer, DadosMes> dadosPorMes = entryAno.getValue();
                String finalCidade = cidade;
                String finalPais = pais;

                for (Map.Entry<Integer, DadosMes> entryMes : dadosPorMes.entrySet()) {
                    int mes = entryMes.getKey();
                    DadosMes dadosMes = entryMes.getValue();

                    executorServiceMeses.submit(() -> processarMes(finalPais, finalCidade, ano, mes, dadosMes));
                }
            }

            executorServiceMeses.shutdown();
            while (!executorServiceMeses.isTerminated()) {}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processarMes(String pais, String cidade, int ano, int mes, DadosMes dadosMes) {
        double mediaTemperatura = dadosMes.calcularMedia();
        System.out.println("País: " + pais + ", Cidade: " + cidade + ", Ano: " + ano + ", Mês: " + mes +
                ", Temp. Mínima: " + Math.round(dadosMes.getTemperaturaMinima()) +
                ", Temp. Máxima: " + Math.round(dadosMes.getTemperaturaMaxima()) +
                ", Temp. Média: " + Math.round(mediaTemperatura));
    }

    private RegistroTemperatura parseLinha(String linha) {
        String[] campos = linha.split(",");
        String pais = campos[0];
        String cidade = campos[1];
        int mes = Integer.parseInt(campos[2]);
        int ano = Integer.parseInt(campos[4]);
        double temperatura = Double.parseDouble(campos[5]);
        return new RegistroTemperatura(pais, cidade, ano, mes, temperatura);
    }
}