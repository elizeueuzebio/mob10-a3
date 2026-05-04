package service;

import java.util.ArrayList;

import model.Corrida;
import model.Motorista;
import model.Passageiro;

public class CorridaService {
    private ArrayList<Corrida> corridas;

    public CorridaService() {
        this.corridas = new ArrayList<>();
    }

    public void solicitar(Corrida corrida) {
        if (corrida == null) {
            return;
        }

        corrida.setStatus("SOLICITADA");
        if (!corridas.contains(corrida)) {
            corridas.add(corrida);
        }

        Passageiro passageiro = corrida.getPassageiro();
        if (passageiro != null) {
            passageiro.solicitarCorrida(corrida);
        }
    }

    public Motorista matchMotorista(Corrida corrida, ArrayList<Motorista> motoristas) {
        if (corrida == null || motoristas == null) {
            return null;
        }

        Passageiro passageiro = corrida.getPassageiro();
        boolean precisaVeiculoAdaptado = passageiro != null
                && passageiro.getNecessidadeEspecial() != null
                && !passageiro.getNecessidadeEspecial().isBlank();

        for (Motorista motorista : motoristas) {
            if (!motorista.isDisponibilidade()) {
                continue;
            }

            boolean veiculoCompativel = !precisaVeiculoAdaptado
                    || (motorista.getVeiculo() != null && motorista.getVeiculo().isAdaptado());

            if (veiculoCompativel && motorista.aceitarCorrida(corrida)) {
                corrida.calcularValor();
                return motorista;
            }
        }

        return null;
    }

    public void finalizar(Corrida corrida) {
        if (corrida != null) {
            corrida.finalizarCorrida();
        }
    }

    public ArrayList<Corrida> listar() {
        return new ArrayList<>(corridas);
    }

    @Override
    public String toString() {
        return "CorridaService{"
                + "corridas=" + corridas
                + '}';
    }
}
