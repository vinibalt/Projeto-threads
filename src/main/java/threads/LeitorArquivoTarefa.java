package threads;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import java.time.Duration;

public class LeitorArquivoTarefa implements Runnable {

    private File[] arquivos;

    public LeitorArquivoTarefa(File[] arquivos) {
        this.arquivos = arquivos;
    }

    @Override
    public void run() {
        for (File arquivo : arquivos) {
            Instant inicioThread = Instant.now();

            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                br.readLine();
                ExecutorService executorService = Executors.newFixedThreadPool(5);
                int contadorLinhas = 0;

                while ((linha = br.readLine()) != null) {
                    RegistroTemperatura registro = parseLinha(linha);
                    executorService.submit(() -> ProcessadorDados.processar(registro));
                    contadorLinhas++;
                }

                executorService.shutdown();
                while (!executorService.isTerminated()) {}

                System.out.println("Total de linhas lidas no arquivo " + arquivo.getName() + ": " + contadorLinhas);

            } catch (Exception e) {
                e.printStackTrace();
            }

            Instant fimThread = Instant.now();
            Duration duracaoThread = Duration.between(inicioThread, fimThread);

            System.out.println("Thread processando arquivo: " + arquivo.getName() + " finalizada.");
            System.out.println("Tempo de execução da thread: " + duracaoThread.toMillis() + " milissegundos.");
        }
    }

    private RegistroTemperatura parseLinha(String linha) {
        String[] campos = linha.split(",");
        String pais = campos[0];
        String cidade = campos[1];
        int mes = Integer.parseInt(campos[2]);
        int ano = Integer.parseInt(campos[4]);
        double temperatura = Double.parseDouble(campos[5]);
        return new RegistroTemperatura(pais, cidade, ano, mes, Math.round(temperatura));
    }
}