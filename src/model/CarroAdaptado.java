package model;

public class CarroAdaptado extends Veiculo {
    public CarroAdaptado(String modelo, String placa, int capacidade) {
        super(modelo, placa, "Carro Adaptado", true, capacidade);
    }

    @Override
    public double calcularTarifa(double tarifaBase) {
        return tarifaBase + 7.0;
    }

    @Override
    public boolean suportaNecessidadeEspecial(String necessidadeEspecial) {
        return true;
    }

    @Override
    public String exibirDetalhes() {
        return "Carro adaptado " + getModelo() + " (" + getPlaca() + ")";
    }
}
