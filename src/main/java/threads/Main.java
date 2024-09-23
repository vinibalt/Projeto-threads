package threads;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int versaoExperimento = 1;
        GerenciadorThreadsPorAno gerenciadorThreadsPorAno = new GerenciadorThreadsPorAno();
        GerenciadorThreads gerenciadorThreads = new GerenciadorThreads();

        for (versaoExperimento = 1; versaoExperimento <= 10; versaoExperimento++){
           // gerenciadorThreads.executarExperimento(versaoExperimento);
        }

        for (versaoExperimento = 11; versaoExperimento <= 20; versaoExperimento++){
            gerenciadorThreadsPorAno.executarExperimento(versaoExperimento);
        }






    }
}
