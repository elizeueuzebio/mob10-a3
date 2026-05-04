package model;

public class Regiao {
    private String nome;

    public Regiao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Regiao{"
                + "nome='" + nome + '\''
                + '}';
    }
}
