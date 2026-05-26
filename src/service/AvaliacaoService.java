package service;

import java.util.ArrayList;

import model.Avaliacao;
import model.Corrida;
import model.Motorista;

public class AvaliacaoService {
    private ArrayList<Avaliacao> avaliacoes;

    public AvaliacaoService() {
        this.avaliacoes = new ArrayList<>();
    }

    public boolean registrar(Avaliacao avaliacao) {
        if (avaliacao == null || avaliacao.getCorrida() == null || buscarPorId(avaliacao.getId()) != null
                || buscarPorCorridaId(avaliacao.getCorrida().getId()) != null) {
            return false;
        }

        // A avaliacao so entra na lista quando a corrida ja esta concluida e a nota e valida.
        if (!avaliacao.getCorrida().podeReceberAvaliacao() || avaliacao.getNota() < 1 || avaliacao.getNota() > 5) {
            return false;
        }

        avaliacao.registrar();
        avaliacoes.add(avaliacao);

        Motorista motorista = avaliacao.getCorrida().getMotorista();
        if (motorista != null) {
            calcularMedia(motorista);
        }

        return true;
    }

    public boolean atualizar(Avaliacao avaliacaoAtualizada) {
        if (avaliacaoAtualizada == null || avaliacaoAtualizada.getNota() < 1 || avaliacaoAtualizada.getNota() > 5) {
            return false;
        }

        for (int i = 0; i < avaliacoes.size(); i++) {
            if (avaliacoes.get(i).getId() == avaliacaoAtualizada.getId()) {
                avaliacoes.set(i, avaliacaoAtualizada);
                Motorista motorista = avaliacaoAtualizada.getCorrida() != null
                        ? avaliacaoAtualizada.getCorrida().getMotorista()
                        : null;
                if (motorista != null) {
                    calcularMedia(motorista);
                }
                return true;
            }
        }

        return false;
    }

    public boolean atualizarComentario(int id, int nota, String comentario) {
        Avaliacao avaliacao = buscarPorId(id);
        if (avaliacao == null || nota < 1 || nota > 5) {
            return false;
        }

        avaliacao.setNota(nota);
        avaliacao.setComentario(comentario);

        Corrida corrida = avaliacao.getCorrida();
        if (corrida != null && corrida.getMotorista() != null) {
            calcularMedia(corrida.getMotorista());
        }
        return true;
    }

    public boolean removerPorId(int id) {
        Avaliacao avaliacao = buscarPorId(id);
        if (avaliacao == null) {
            return false;
        }

        Motorista motorista = avaliacao.getCorrida() != null ? avaliacao.getCorrida().getMotorista() : null;
        boolean removeu = avaliacoes.remove(avaliacao);

        if (removeu && motorista != null) {
            calcularMedia(motorista);
        }

        return removeu;
    }

    public double calcularMedia(Motorista motorista) {
        if (motorista == null) {
            return 0.0;
        }

        int somaNotas = 0;
        int totalAvaliacoes = 0;

        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getCorrida() != null && avaliacao.getCorrida().getMotorista() == motorista) {
                somaNotas += avaliacao.getNota();
                totalAvaliacoes++;
            }
        }

        double media = totalAvaliacoes == 0 ? 0.0 : (double) somaNotas / totalAvaliacoes;
        motorista.setAvaliacaoMedia(media);
        return media;
    }

    public Avaliacao buscarPorId(int id) {
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getId() == id) {
                return avaliacao;
            }
        }
        return null;
    }

    public Avaliacao buscarPorCorridaId(int corridaId) {
        for (Avaliacao avaliacao : avaliacoes) {
            if (avaliacao.getCorrida() != null && avaliacao.getCorrida().getId() == corridaId) {
                return avaliacao;
            }
        }
        return null;
    }

    public ArrayList<Avaliacao> listar() {
        return new ArrayList<>(avaliacoes);
    }

    @Override
    public String toString() {
        return "AvaliacaoService{"
                + "avaliacoes=" + avaliacoes
                + '}';
    }
}
