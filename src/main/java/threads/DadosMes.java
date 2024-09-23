package threads;

public class DadosMes {
    private double temperaturaMinima = Double.MAX_VALUE;
    private double temperaturaMaxima = Double.MIN_VALUE;
    private double somaTemperaturas = 0.0;
    private int contagemTemperaturas = 0;

    public void adicionarTemperatura(double temperatura) {
        if (temperatura < temperaturaMinima) {
            temperaturaMinima = temperatura;
        }
        if (temperatura > temperaturaMaxima) {
            temperaturaMaxima = temperatura;
        }
        somaTemperaturas += temperatura;
        contagemTemperaturas++;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public double calcularMedia() {
        return contagemTemperaturas > 0 ? somaTemperaturas / contagemTemperaturas : 0.0;
    }
}