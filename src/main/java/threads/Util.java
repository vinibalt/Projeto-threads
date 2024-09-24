package threads;

import java.io.File;
import java.io.FilenameFilter;

public class Util {

    public static File[] obterArquivosCSV(String diretorio) {
        File pasta = new File(diretorio);

        if (!pasta.exists() || !pasta.isDirectory()) {
            System.out.println("Diretório não encontrado: " + diretorio);
            return new File[0];
        }

        return pasta.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".csv");
            }
        });
    }
}