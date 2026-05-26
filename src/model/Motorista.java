package model;

import java.util.ArrayList;

public class Motorista extends Usuario {
    private String cnh;
    private Veiculo veiculo;
    private boolean disponibilidade;
    private double avaliacaoMedia;
    private ArrayList<Corrida> listaCorridas;

    public Motorista(int id, String nome, String telefone, String email, String endereco, Regiao regiao,
            String cnh, Veiculo veiculo) {
        this(id, nome, telefone, email, endereco, regiao, cnh, veiculo, true);
    }

    public Motorista(int id, String nome, String telefone, String email, String endereco, Regiao regiao,
            String cnh, Veiculo veiculo, boolean disponibilidade) {
        super(id, nome, telefone, email, endereco, regiao);
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.disponibilidade = disponibilidade;
        this.avaliacaoMedia = 0.0;
        this.listaCorridas = new ArrayList<>();
    }

    public boolean aceitarCorrida(Corrida corrida) {
        if (!disponibilidade || corrida == null || veiculo == null) {
            return false;
        }

        // O motorista assume a corrida e passa a ficar indisponivel ate a finalizacao.
        corrida.setMotorista(this);
        corrida.setStatus("ACEITA");
        if (!listaCorridas.contains(corrida)) {
            listaCorridas.add(corrida);
        }
        disponibilidade = false;
        return true;
    }

    public void finalizarCorrida(Corrida corrida) {
        if (corrida != null) {
            corrida.finalizarCorrida();
            disponibilidade = true;
        }
    }

    public String visualizarCorridas() {
        return listaCorridas.toString();
    }

    @Override
    public String getTipoUsuario() {
        return "Motorista";
    }

    @Override
    public String exibirPerfil() {
        String veiculoDescricao = veiculo != null ? veiculo.exibirDetalhes() : "sem veiculo";
        return super.exibirPerfil() + " | Veiculo: " + veiculoDescricao;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public void setAvaliacaoMedia(double avaliacaoMedia) {
        this.avaliacaoMedia = avaliacaoMedia;
    }

    public ArrayList<Corrida> getListaCorridas() {
        return listaCorridas;
    }

    public void setListaCorridas(ArrayList<Corrida> listaCorridas) {
        this.listaCorridas = listaCorridas;
    }

    @Override
    public String toString() {
        return "Motorista{"
                + "id=" + getId()
                + ", nome='" + getNome() + '\''
                + ", cnh='" + cnh + '\''
                + ", veiculo=" + (veiculo != null ? veiculo.exibirDetalhes() : "sem veiculo")
                + ", disponibilidade=" + disponibilidade
                + ", avaliacaoMedia=" + avaliacaoMedia
                + ", corridas=" + listaCorridas.size()
                + '}';
    }
}
