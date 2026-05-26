package service;

import java.util.ArrayList;

import model.Pagamento;

public class PagamentoService {
    private ArrayList<Pagamento> pagamentos;

    public PagamentoService() {
        this.pagamentos = new ArrayList<>();
    }

    public boolean processarPagamento(Pagamento pagamento) {
        if (pagamento == null || pagamento.getCorrida() == null || buscarPorId(pagamento.getId()) != null
                || buscarPorCorridaId(pagamento.getCorrida().getId()) != null) {
            return false;
        }

        if (!"FINALIZADA".equalsIgnoreCase(pagamento.getCorrida().getStatus())) {
            return false;
        }

        pagamento.processarPagamento();
        pagamentos.add(pagamento);
        return true;
    }

    public boolean atualizar(Pagamento pagamentoAtualizado) {
        if (pagamentoAtualizado == null) {
            return false;
        }

        for (int i = 0; i < pagamentos.size(); i++) {
            if (pagamentos.get(i).getId() == pagamentoAtualizado.getId()) {
                pagamentos.set(i, pagamentoAtualizado);
                return true;
            }
        }

        return false;
    }

    public boolean atualizarMetodoPagamento(int id, String metodoPagamento) {
        Pagamento pagamento = buscarPorId(id);
        if (pagamento == null) {
            return false;
        }

        pagamento.setMetodoPagamento(metodoPagamento);
        return true;
    }

    public boolean removerPorId(int id) {
        Pagamento pagamento = buscarPorId(id);
        if (pagamento == null) {
            return false;
        }
        return pagamentos.remove(pagamento);
    }

    public Pagamento buscarPorId(int id) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getId() == id) {
                return pagamento;
            }
        }
        return null;
    }

    public Pagamento buscarPorCorridaId(int corridaId) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getCorrida() != null && pagamento.getCorrida().getId() == corridaId) {
                return pagamento;
            }
        }
        return null;
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
