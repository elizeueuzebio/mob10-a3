package control;

import java.util.ArrayList;

import model.Corrida;
import model.Pagamento;

public class PagamentoControl {
    private final ArrayList<Pagamento> pagamentos;

    public PagamentoControl() {
        this.pagamentos = new ArrayList<>();
    }

    public boolean processarPagamento(Pagamento pagamento) {
        if (pagamento == null || pagamento.getCorrida() == null || pagamento.getValor() <= 0
                || buscarPorId(pagamento.getId()) != null
                || buscarPorCorridaId(pagamento.getCorrida().getId()) != null) {
            return false;
        }

        if (!pagamento.getCorrida().estaComStatus(Corrida.STATUS_FINALIZADA)) {
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

        if (textoVazio(metodoPagamento)) {
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

    private boolean textoVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "PagamentoControl{"
                + "pagamentos=" + pagamentos
                + '}';
    }
}
