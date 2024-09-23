package threads;

public class RegistroTemperatura {
    private String pais;
    private String cidade;
    private int ano;
    private int mes;
    private double temperatura;

    public RegistroTemperatura(String pais, String cidade, int ano, int mes, double temperatura) {
        this.pais = pais;
        this.cidade = cidade;
        this.ano = ano;
        this.mes = mes;
        this.temperatura = temperatura;
    }

    public String getPais() {
        return pais;
    }

    public String getCidade() {
        return cidade;
    }

    public int getAno() {
        return ano;
    }

    public int getMes() {
        return mes;
    }

    public double getTemperatura() {
        return temperatura;
    }
}