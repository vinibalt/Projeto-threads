package threads;

import java.io.File;
import java.time.Instant;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GerenciadorThreadsPorAno {

    public void executarExperimento(int versao) {
        int numThreadsCidade = obterNumeroThreadsPorVersao(versao);
        ExecutorService executorServiceCidade = Executors.newFixedThreadPool(numThreadsCidade);

        File[] arquivos = Util.obterArquivosCSV("src/input");
        List<List<File>> arquivosDivididos = dividirArquivosPorThread(arquivos, numThreadsCidade);

        Instant inicioExperimento = Instant.now();

        for (List<File> grupoArquivos : arquivosDivididos) {
            executorServiceCidade.submit(() -> {
                for (File arquivo : grupoArquivos) {
                    new LeitorPorCidadeTarefa(arquivo.getPath()).run();
                }
            });
        }

        executorServiceCidade.shutdown();
        while (!executorServiceCidade.isTerminated()) {}

        Instant fimExperimento = Instant.now();
        Duration duracaoExperimento = Duration.between(inicioExperimento, fimExperimento);

        System.out.println("Experimento " + versao + " concluído.");
        System.out.println("Tempo total de execução: " + duracaoExperimento.toMillis() + " milissegundos.");
    }

    private List<List<File>> dividirArquivosPorThread(File[] arquivos, int numThreads) {
        List<List<File>> arquivosDivididos = new ArrayList<>();
        int numArquivosPorThread = (int) Math.ceil((double) arquivos.length / numThreads);

        for (int i = 0; i < arquivos.length; i += numArquivosPorThread) {
            List<File> grupo = new ArrayList<>();
            for (int j = i; j < i + numArquivosPorThread && j < arquivos.length; j++) {
                grupo.add(arquivos[j]);
            }
            arquivosDivididos.add(grupo);
        }

        return arquivosDivididos;
    }

    private int obterNumeroThreadsPorVersao(int versao) {
        switch (versao) {
            case 12: return 2;
            case 13: return 4;
            case 14: return 8;
            case 15: return 16;
            case 16: return 32;
            case 17: return 64;
            case 18: return 80;
            case 19: return 160;
            case 20: return 320;
            default: return 1;
        }
    }
}