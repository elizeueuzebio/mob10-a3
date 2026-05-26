package model;

public class Van extends Veiculo {
    public Van(String modelo, String placa, int capacidade) {
        this(modelo, placa, false, capacidade);
    }

    public Van(String modelo, String placa, boolean adaptado, int capacidade) {
        super(modelo, placa, "Van", adaptado, capacidade);
    }

    @Override
    public double calcularTarifa(double tarifaBase) {
        return tarifaBase + (isAdaptado() ? 10.0 : 5.0);
    }

    @Override
    public boolean suportaNecessidadeEspecial(String necessidadeEspecial) {
        return isAdaptado() || !possuiNecessidadeEspecial(necessidadeEspecial);
    }

    @Override
    public String exibirDetalhes() {
        return "Van " + getModelo() + " (" + getPlaca() + ") - " + getCapacidade() + " lugares";
    }
}
