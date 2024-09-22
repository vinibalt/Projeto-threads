package threads;

public class DadosAno {
    private double temperaturaMinima = Double.MAX_VALUE;
    private double temperaturaMaxima = Double.MIN_VALUE;

    public synchronized void adicionarTemperatura(double temperatura) {
        if (temperatura < temperaturaMinima) {
            temperaturaMinima = temperatura;
        }
        if (temperatura > temperaturaMaxima) {
            temperaturaMaxima = temperatura;
        }
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }
}
