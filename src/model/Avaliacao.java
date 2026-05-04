package model;

public class Avaliacao {
    private int id;
    private Corrida corrida;
    private int nota;
    private String comentario;

    public Avaliacao(int id, Corrida corrida, int nota, String comentario) {
        this.id = id;
        this.corrida = corrida;
        this.nota = nota;
        this.comentario = comentario;
    }

    public String registrar() {
        return "Avaliacao registrada com nota " + nota;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Corrida getCorrida() {
        return corrida;
    }

    public void setCorrida(Corrida corrida) {
        this.corrida = corrida;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "Avaliacao{"
                + "id=" + id
                + ", corrida=" + (corrida != null ? corrida.getId() : 0)
                + ", nota=" + nota
                + ", comentario='" + comentario + '\''
                + '}';
    }
}
