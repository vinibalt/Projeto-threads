package threads;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int versaoExperimento = 1;
        GerenciadorThreadsPorAno gerenciadorThreadsPorAno = new GerenciadorThreadsPorAno();
        GerenciadorThreads gerenciadorThreads = new GerenciadorThreads();

        gerenciadorThreads.executarExperimento(versaoExperimento);

        gerenciadorThreadsPorAno.executarExperimento(versaoExperimento);




    }
}
