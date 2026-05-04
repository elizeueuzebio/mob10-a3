package model;

public class Pagamento {
    private int id;
    private Corrida corrida;
    private double valor;
    private String metodoPagamento;
    private String status;

    public Pagamento(int id, Corrida corrida, double valor, String metodoPagamento, String status) {
        this.id = id;
        this.corrida = corrida;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.status = status;
    }

    public void processarPagamento() {
        status = "PROCESSADO";
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pagamento{"
                + "id=" + id
                + ", corrida=" + (corrida != null ? corrida.getId() : 0)
                + ", valor=" + valor
                + ", metodoPagamento='" + metodoPagamento + '\''
                + ", status='" + status + '\''
                + '}';
    }
}
