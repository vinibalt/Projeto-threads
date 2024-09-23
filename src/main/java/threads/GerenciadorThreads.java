package threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.io.File;
import java.time.Instant;
import java.time.Duration;

public class GerenciadorThreads {

    public void executarExperimento(int versao) {
        int numThreads = obterNumeroThreadsPorVersao(versao);
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);


        String diretorioCSV = "src/input";


        File[] arquivos = Util.obterArquivosCSV(diretorioCSV);
        //Adicionar a quebra de arquivos. Dividir o número de arquivos pelo número de Threads

        if (arquivos.length == 0) {
            System.out.println("Nenhum arquivo CSV disponível para processamento.");
            return;
        }


        Instant inicioExperimento = Instant.now();


        for (File arquivo : arquivos) {
            Instant inicioArquivo = Instant.now();
            executorService.submit(() -> {
                new LeitorArquivoTarefa(arquivo).run();
            });
            Instant fimArquivo = Instant.now();

            Duration duracaoArquivo = Duration.between(inicioArquivo, fimArquivo);
            System.out.println("Tempo de execução para o arquivo " + arquivo.getName() + ": " + duracaoArquivo.toMillis() + " ms");
        }


        executorService.shutdown();
        while (!executorService.isTerminated()) {}


        Instant fimExperimento = Instant.now();
        Duration duracaoExperimento = Duration.between(inicioExperimento, fimExperimento);

        System.out.println("Experimento " + versao + " concluído.");
        System.out.println("Tempo total de execução: " + duracaoExperimento.toMillis() + " milissegundos.");
    }


    private int obterNumeroThreadsPorVersao(int versao) {
        switch (versao) {
            case 2: return 2;
            case 3: return 4;
            case 4: return 8;
            case 5: return 16;
            case 6: return 32;
            case 7: return 64;
            case 8: return 80;
            case 9: return 160;
            case 10: return 320;
            default: return 1;
        }
    }
}