package model;

public class CarroComum extends Veiculo {
    public CarroComum(String modelo, String placa, int capacidade) {
        super(modelo, placa, "Carro Comum", false, capacidade);
    }

    @Override
    public double calcularTarifa(double tarifaBase) {
        return tarifaBase;
    }

    @Override
    public boolean suportaNecessidadeEspecial(String necessidadeEspecial) {
        return !possuiNecessidadeEspecial(necessidadeEspecial);
    }

    @Override
    public String exibirDetalhes() {
        return "Carro comum " + getModelo() + " (" + getPlaca() + ")";
    }
}
