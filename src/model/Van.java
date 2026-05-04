package model;

public class Van extends Veiculo {
    public Van(String modelo, String placa, boolean adaptado, int capacidade) {
        super(modelo, placa, "Van", adaptado, capacidade);
    }
}
