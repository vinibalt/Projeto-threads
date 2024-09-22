package threads;


public class RegistroTemperatura {
    private String pais;
    private String cidade;

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    private int ano;
    private double temperatura;

    private double somaTemperaturas;
    private int contagemRegistros;
    private double minTemperatura;
    private double maxTemperatura;

    public RegistroTemperatura(String pais, String cidade, int ano, double temperatura) {
        this.pais = pais;
        this.cidade = cidade;
        this.ano = ano;
        this.temperatura = temperatura;
        this.somaTemperaturas = temperatura;
        this.contagemRegistros = 1;
        this.minTemperatura = temperatura;
        this.maxTemperatura = temperatura;
    }

    public void atualizarComNovaTemperatura(double novaTemperatura) {
        somaTemperaturas += novaTemperatura;
        contagemRegistros++;
        if (novaTemperatura < minTemperatura) {
            minTemperatura = novaTemperatura;
        }
        if (novaTemperatura > maxTemperatura) {
            maxTemperatura = novaTemperatura;
        }
    }

    public String getCidade() {
        return cidade;
    }

    public double getTemperatura() {
        return somaTemperaturas / contagemRegistros;
    }

    public double getMinTemperatura() {
        return minTemperatura;
    }

    public double getMaxTemperatura() {
        return maxTemperatura;
    }

    public String getPais() {
        return this.pais;
    }
}
