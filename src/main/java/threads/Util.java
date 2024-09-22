package threads;

import java.io.File;
import java.io.FilenameFilter;

public class Util {

    public static File[] obterArquivosCSV(String diretorio) {
        File pasta = new File(diretorio);


        if (!pasta.exists() || !pasta.isDirectory()) {
            System.out.println("Diretório não encontrado ou não é um diretório válido: " + diretorio);
            return new File[0];
        }


        File[] arquivosCSV = pasta.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".csv");
            }
        });

        if (arquivosCSV == null || arquivosCSV.length == 0) {
            System.out.println("Nenhum arquivo CSV encontrado no diretório: " + diretorio);
            return new File[0];
        }

        return arquivosCSV;
    }
}