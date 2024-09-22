package threads;

import java.util.HashMap;
import java.util.Map;

public class ProcessadorDados {

    private static Map<String, RegistroTemperatura> mapaDados = new HashMap<>();

    public static synchronized void processar(RegistroTemperatura registro) {
        if (!mapaDados.containsKey(registro.getCidade())) {
            mapaDados.put(registro.getCidade(), registro);
        } else {
            RegistroTemperatura registroExistente = mapaDados.get(registro.getCidade());
            registroExistente.atualizarComNovaTemperatura(registro.getTemperatura());
        }
    }

    public static void imprimirResultados() {
        for (String cidade : mapaDados.keySet()) {
            RegistroTemperatura registro = mapaDados.get(cidade);
            System.out.println("Cidade: " + cidade + " - Temperatura Média: " + registro.getTemperatura());
        }
    }
}
