package model;

import java.util.ArrayList;

public class Passageiro extends Usuario {
    private String necessidadeEspecial;
    private ArrayList<String> formasPagamento;
    private ArrayList<Corrida> listaCorridas;

    public Passageiro(int id, String nome, String telefone, String email, String endereco, Regiao regiao,
            String necessidadeEspecial, ArrayList<String> formasPagamento) {
        super(id, nome, telefone, email, endereco, regiao);
        this.necessidadeEspecial = necessidadeEspecial;
        this.formasPagamento = formasPagamento != null ? formasPagamento : new ArrayList<>();
        this.listaCorridas = new ArrayList<>();
    }

    public void solicitarCorrida(Corrida corrida) {
        if (corrida != null && !listaCorridas.contains(corrida)) {
            listaCorridas.add(corrida);
        }
    }

    public boolean cancelarCorrida(Corrida corrida) {
        if (corrida == null) {
            return false;
        }

        corrida.setStatus("CANCELADA");
        return listaCorridas.remove(corrida);
    }

    public Avaliacao avaliarCorrida(int id, Corrida corrida, int nota, String comentario) {
        return new Avaliacao(id, corrida, nota, comentario);
    }

    public String getNecessidadeEspecial() {
        return necessidadeEspecial;
    }

    public void setNecessidadeEspecial(String necessidadeEspecial) {
        this.necessidadeEspecial = necessidadeEspecial;
    }

    public ArrayList<String> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(ArrayList<String> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

    public ArrayList<Corrida> getListaCorridas() {
        return listaCorridas;
    }

    public void setListaCorridas(ArrayList<Corrida> listaCorridas) {
        this.listaCorridas = listaCorridas;
    }

    @Override
    public String toString() {
        return "Passageiro{"
                + "id=" + getId()
                + ", nome='" + getNome() + '\''
                + ", necessidadeEspecial='" + necessidadeEspecial + '\''
                + ", formasPagamento=" + formasPagamento
                + ", corridas=" + listaCorridas.size()
                + '}';
    }
}
