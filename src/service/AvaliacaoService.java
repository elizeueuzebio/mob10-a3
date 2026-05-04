package service;

import java.util.ArrayList;

import model.Avaliacao;
import model.Motorista;

public class AvaliacaoService {
    private ArrayList<Avaliacao> avaliacoes;

    public AvaliacaoService() {
        this.avaliacoes = new ArrayList<>();
    }

    public void registrar(Avaliacao avaliacao) {
        if (avaliacao != null) {
            avaliacao.registrar();
            avaliacoes.add(avaliacao);
        }
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
