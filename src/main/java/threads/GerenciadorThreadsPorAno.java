package threads;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.time.Instant;
import java.time.Duration;

public class GerenciadorThreadsPorAno {

    public void executarExperimento(int versao) {
        int numThreadsCidade = obterNumeroThreadsPorVersao(versao);
        ExecutorService executorServiceCidade = Executors.newFixedThreadPool(numThreadsCidade);

        List<File> arquivos = List.of(Util.obterArquivosCSV("src/input"));

        Instant inicioExperimento = Instant.now();

        for (File arquivo : arquivos) {
            executorServiceCidade.submit(new LeitorPorCidadeTarefa(arquivo.getPath()));
        }

        executorServiceCidade.shutdown();
        while (!executorServiceCidade.isTerminated()) {}

        Instant fimExperimento = Instant.now();
        Duration duracaoExperimento = Duration.between(inicioExperimento, fimExperimento);

        System.out.println("Experimento " + versao + " concluído.");
        System.out.println("Tempo total de execução: " + duracaoExperimento.toMillis() + " milissegundos.");
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
