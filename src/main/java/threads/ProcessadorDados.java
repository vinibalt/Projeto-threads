package threads;

import java.util.HashMap;
import java.util.Map;

public class ProcessadorDados {

    private static Map<String, Map<Integer, Map<Integer, DadosMes>>> mapaDados = new HashMap<>();

    public static synchronized void processar(RegistroTemperatura registro) {
        mapaDados.computeIfAbsent(registro.getCidade(), cidade -> new HashMap<>())
                .computeIfAbsent(registro.getAno(), ano -> new HashMap<>())
                .computeIfAbsent(registro.getMes(), mes -> new DadosMes())
                .adicionarTemperatura(registro.getTemperatura());
    }

    public static void imprimirResultados() {
        for (String cidade : mapaDados.keySet()) {
            Map<Integer, Map<Integer, DadosMes>> dadosPorAno = mapaDados.get(cidade);
            System.out.println("Cidade: " + cidade);

            for (Integer ano : dadosPorAno.keySet()) {
                Map<Integer, DadosMes> dadosPorMes = dadosPorAno.get(ano);

                for (Integer mes : dadosPorMes.keySet()) {
                    DadosMes dadosMes = dadosPorMes.get(mes);
                    double mediaTemperatura = dadosMes.calcularMedia();
                    System.out.println("  Ano: " + ano + ", Mês: " + mes +
                            " - Temp. Mínima: " + Math.round(dadosMes.getTemperaturaMinima()) +
                            ", Temp. Máxima: " + Math.round(dadosMes.getTemperaturaMaxima()) +
                            ", Temp. Média: " + Math.round(mediaTemperatura));
                }
            }
        }
    }
}