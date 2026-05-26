package service;

import java.util.ArrayList;

import model.Corrida;
import model.Motorista;
import model.Passageiro;
import model.Veiculo;

public class CorridaService {
    private ArrayList<Corrida> corridas;

    public CorridaService() {
        this.corridas = new ArrayList<>();
    }

    // O CRUD de corridas centraliza as regras de status para a lista nao ficar inconsistente.
    public boolean solicitar(Corrida corrida) {
        if (corrida == null || corrida.getPassageiro() == null || buscarPorId(corrida.getId()) != null) {
            return false;
        }

        corrida.setStatus("SOLICITADA");
        corridas.add(corrida);

        Passageiro passageiro = corrida.getPassageiro();
        passageiro.solicitarCorrida(corrida);
        return true;
    }

    public Motorista matchMotorista(Corrida corrida, ArrayList<Motorista> motoristas) {
        if (corrida == null || motoristas == null) {
            return null;
        }

        Passageiro passageiro = corrida.getPassageiro();
        String necessidadeEspecial = passageiro != null ? passageiro.getNecessidadeEspecial() : "";

        for (Motorista motorista : motoristas) {
            if (motorista == null || !motorista.isDisponibilidade()) {
                continue;
            }

            Veiculo veiculo = motorista.getVeiculo();
            boolean veiculoCompativel = veiculo != null && veiculo.suportaNecessidadeEspecial(necessidadeEspecial);

            if (veiculoCompativel && motorista.aceitarCorrida(corrida)) {
                corrida.calcularValor();
                return motorista;
            }
        }

        return null;
    }

    public Motorista despacharCorrida(int id, ArrayList<Motorista> motoristas) {
        Corrida corrida = buscarPorId(id);
        if (corrida == null || !"SOLICITADA".equalsIgnoreCase(corrida.getStatus())) {
            return null;
        }

        Motorista motorista = matchMotorista(corrida, motoristas);
        if (motorista != null) {
            corrida.iniciarCorrida();
        }
        return motorista;
    }

    public boolean atualizar(Corrida corridaAtualizada) {
        if (corridaAtualizada == null) {
            return false;
        }

        for (int i = 0; i < corridas.size(); i++) {
            if (corridas.get(i).getId() == corridaAtualizada.getId()) {
                corridas.set(i, corridaAtualizada);
                return true;
            }
        }

        return false;
    }

    public boolean atualizarRota(int id, String origem, String destino, String data, String horario) {
        Corrida corrida = buscarPorId(id);
        if (corrida == null || "FINALIZADA".equalsIgnoreCase(corrida.getStatus())
                || "CANCELADA".equalsIgnoreCase(corrida.getStatus())) {
            return false;
        }

        corrida.setOrigem(origem);
        corrida.setDestino(destino);
        corrida.setData(data);
        corrida.setHorario(horario);
        return true;
    }

    public boolean finalizar(int id) {
        Corrida corrida = buscarPorId(id);
        return finalizar(corrida);
    }

    public boolean finalizar(Corrida corrida) {
        if (corrida == null || "CANCELADA".equalsIgnoreCase(corrida.getStatus())) {
            return false;
        }
        corrida.finalizarCorrida();
        return true;
    }

    public boolean cancelar(int id) {
        Corrida corrida = buscarPorId(id);
        if (corrida == null || "FINALIZADA".equalsIgnoreCase(corrida.getStatus())) {
            return false;
        }

        Passageiro passageiro = corrida.getPassageiro();
        if (passageiro != null) {
            passageiro.cancelarCorrida(corrida);
        } else {
            corrida.cancelarCorrida();
        }
        return true;
    }

    public boolean removerPorId(int id) {
        Corrida corrida = buscarPorId(id);
        if (corrida == null) {
            return false;
        }

        if (corrida.getPassageiro() != null) {
            corrida.getPassageiro().getListaCorridas().remove(corrida);
        }

        if (corrida.getMotorista() != null) {
            corrida.getMotorista().getListaCorridas().remove(corrida);
            corrida.getMotorista().setDisponibilidade(true);
        }

        return corridas.remove(corrida);
    }

    public Corrida buscarPorId(int id) {
        for (Corrida corrida : corridas) {
            if (corrida.getId() == id) {
                return corrida;
            }
        }
        return null;
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
