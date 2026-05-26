package model;

public class Corrida {
    private int id;
    private Passageiro passageiro;
    private Motorista motorista;
    private String origem;
    private String destino;
    private String data;
    private String horario;
    private String status;
    private double valor;

    public Corrida(int id, Passageiro passageiro, String origem, String destino, String data, String horario) {
        this(id, passageiro, null, origem, destino, data, horario, "SOLICITADA", 0.0);
    }

    public Corrida(int id, Passageiro passageiro, Motorista motorista, String origem, String destino,
            String data, String horario, String status, double valor) {
        this.id = id;
        this.passageiro = passageiro;
        this.motorista = motorista;
        this.origem = origem;
        this.destino = destino;
        this.data = data;
        this.horario = horario;
        this.status = status;
        this.valor = valor;
    }

    public double calcularValor() {
        return calcularValor(18.0);
    }

    public double calcularValor(double tarifaBase) {
        // O calculo delega para o tipo concreto do veiculo, evidenciando polimorfismo.
        if (motorista != null && motorista.getVeiculo() != null) {
            valor = motorista.getVeiculo().calcularTarifa(tarifaBase);
        } else {
            valor = tarifaBase;
        }
        return valor;
    }

    public void iniciarCorrida() {
        status = "EM_ANDAMENTO";
    }

    public void finalizarCorrida() {
        status = "FINALIZADA";
        if (motorista != null) {
            motorista.setDisponibilidade(true);
        }
    }

    public void cancelarCorrida() {
        status = "CANCELADA";
        if (motorista != null) {
            motorista.setDisponibilidade(true);
        }
    }

    public boolean podeReceberAvaliacao() {
        return "FINALIZADA".equalsIgnoreCase(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Corrida{"
                + "id=" + id
                + ", passageiro=" + (passageiro != null ? passageiro.getNome() : "sem passageiro")
                + ", motorista=" + (motorista != null ? motorista.getNome() : "sem motorista")
                + ", origem='" + origem + '\''
                + ", destino='" + destino + '\''
                + ", data='" + data + '\''
                + ", horario='" + horario + '\''
                + ", status='" + status + '\''
                + ", valor=" + valor
                + '}';
    }
}
