package service;

import java.util.ArrayList;

import model.Pagamento;

public class PagamentoService {
    private ArrayList<Pagamento> pagamentos;

    public PagamentoService() {
        this.pagamentos = new ArrayList<>();
    }

    public void processarPagamento(Pagamento pagamento) {
        if (pagamento != null) {
            pagamento.processarPagamento();
            pagamentos.add(pagamento);
        }
    }

    public ArrayList<Pagamento> listar() {
        return new ArrayList<>(pagamentos);
    }

    @Override
    public String toString() {
        return "PagamentoService{"
                + "pagamentos=" + pagamentos
                + '}';
    }
}
