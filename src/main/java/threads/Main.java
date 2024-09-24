package threads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {
        int versaoExperimento;
        int numRodadas = 10;
        String nomeArquivoSaida = "tempos_execucao" + (int) Math.round((Math.random() * 100)) + ".txt";

        GerenciadorThreadsPorAno gerenciadorThreadsPorAno = new GerenciadorThreadsPorAno();
        GerenciadorThreads gerenciadorThreads = new GerenciadorThreads();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivoSaida))) {

            for (int rodada = 1; rodada <= numRodadas; rodada++) {
                writer.write("Rodada " + rodada + ":\n");


                for (versaoExperimento = 1; versaoExperimento <= 10; versaoExperimento++) {
                    Instant inicio = Instant.now();
                    gerenciadorThreads.executarExperimento(versaoExperimento);
                    Instant fim = Instant.now();
                    Duration duracao = Duration.between(inicio, fim);

                    writer.write("Versão " + versaoExperimento + ": " + duracao.toMillis() + " ms\n");
                }

                for (versaoExperimento = 11; versaoExperimento <= 20; versaoExperimento++) {
                    Instant inicio = Instant.now();
                    gerenciadorThreadsPorAno.executarExperimento(versaoExperimento);
                    Instant fim = Instant.now();
                    Duration duracao = Duration.between(inicio, fim);

                    writer.write("Versão " + versaoExperimento + ": " + duracao.toMillis() + " ms\n");
                }

                writer.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}