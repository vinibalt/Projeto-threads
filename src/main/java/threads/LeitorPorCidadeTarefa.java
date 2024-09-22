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
            Map<Integer, DadosAno> dadosPorAno = new HashMap<>();

            while ((linha = br.readLine()) != null) {
                RegistroTemperatura registro = parseLinha(linha);
                cidade = registro.getCidade();
                pais = registro.getPais();
                dadosPorAno.computeIfAbsent(registro.getAno(), ano -> new DadosAno()).adicionarTemperatura(registro.getTemperatura());
            }

            ExecutorService executorServiceAnos = Executors.newFixedThreadPool(25);  // Até 25 anos (1995-2020)

            for (Map.Entry<Integer, DadosAno> entry : dadosPorAno.entrySet()) {
                int ano = entry.getKey();
                DadosAno dados = entry.getValue();
                String finalPais = pais;
                String finalCidade = cidade;
                executorServiceAnos.submit(() -> processarAno(finalPais, finalCidade, ano, dados));
            }

            executorServiceAnos.shutdown();
            while (!executorServiceAnos.isTerminated()) {}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processarAno(String pais, String cidade, int ano, DadosAno dadosAno) {
        System.out.println("País: " + pais + ", Cidade: " + cidade + ", Ano: " + ano + ", Temp. Mínima: " + dadosAno.getTemperaturaMinima() +
                ", Temp. Máxima: " + dadosAno.getTemperaturaMaxima());
    }

    private RegistroTemperatura parseLinha(String linha) {
        String[] campos = linha.split(",");
        String pais = campos[0];
        String cidade = campos[1];
        int ano = Integer.parseInt(campos[4]);
        double temperatura = Double.parseDouble(campos[5]);
        return new RegistroTemperatura(pais, cidade, ano, temperatura);
    }
}
