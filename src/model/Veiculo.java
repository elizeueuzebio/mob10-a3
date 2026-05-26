package model;

public class Veiculo {
    private String modelo;
    private String placa;
    private String tipo;
    private boolean adaptado;
    private int capacidade;

    public Veiculo(String modelo, String placa, String tipo, int capacidade) {
        this(modelo, placa, tipo, false, capacidade);
    }

    public Veiculo(String modelo, String placa, String tipo, boolean adaptado, int capacidade) {
        this.modelo = modelo;
        this.placa = placa;
        this.tipo = tipo;
        this.adaptado = adaptado;
        this.capacidade = capacidade;
    }

    public String exibirDetalhes() {
        return tipo + " " + modelo + " (" + placa + ")";
    }

    public double calcularTarifa(double tarifaBase) {
        return adaptado ? tarifaBase + 7.0 : tarifaBase;
    }

    public boolean suportaNecessidadeEspecial(String necessidadeEspecial) {
        // A regra base considera o veiculo adaptado como compativel para necessidades especiais.
        return !possuiNecessidadeEspecial(necessidadeEspecial) || adaptado;
    }

    protected boolean possuiNecessidadeEspecial(String necessidadeEspecial) {
        return necessidadeEspecial != null
                && !necessidadeEspecial.trim().isEmpty()
                && !"nenhuma".equalsIgnoreCase(necessidadeEspecial);
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isAdaptado() {
        return adaptado;
    }

    public void setAdaptado(boolean adaptado) {
        this.adaptado = adaptado;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return "Veiculo{"
                + "modelo='" + modelo + '\''
                + ", placa='" + placa + '\''
                + ", tipo='" + tipo + '\''
                + ", adaptado=" + adaptado
                + ", capacidade=" + capacidade
                + '}';
    }
}
