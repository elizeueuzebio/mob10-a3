package model;

public class CarroComum extends Veiculo {
    public CarroComum(String modelo, String placa, int capacidade) {
        super(modelo, placa, "Carro Comum", false, capacidade);
    }
}
