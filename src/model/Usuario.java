package model;

public class Usuario {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private Regiao regiao;

    public Usuario(int id, String nome, String telefone, String email, String endereco, Regiao regiao) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.regiao = regiao;
    }

    public String exibirPerfil() {
        return nome + " | " + email + " | " + telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Regiao getRegiao() {
        return regiao;
    }

    public void setRegiao(Regiao regiao) {
        this.regiao = regiao;
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "id=" + id
                + ", nome='" + nome + '\''
                + ", telefone='" + telefone + '\''
                + ", email='" + email + '\''
                + ", endereco='" + endereco + '\''
                + ", regiao=" + (regiao != null ? regiao.getNome() : "sem regiao")
                + '}';
    }
}
