package view;

import java.util.ArrayList;

import model.Avaliacao;
import model.CarroAdaptado;
import model.Corrida;
import model.Motorista;
import model.Pagamento;
import model.Passageiro;
import model.Regiao;
import model.Veiculo;
import service.AvaliacaoService;
import service.CorridaService;
import service.PagamentoService;
import service.UsuarioService;

public class Main {
    public static void main(String[] args) {
        Regiao regiaoCentro = new Regiao("Centro");

        Veiculo veiculoAdaptado = new CarroAdaptado("Spin Adaptada", "ABC1D23", 4);

        Motorista motorista = new Motorista(
                1,
                "Carlos Lima",
                "(11) 99999-0001",
                "carlos@mob10.com",
                "Rua das Flores, 100",
                regiaoCentro,
                "12345678900",
                veiculoAdaptado,
                true);

        ArrayList<String> formasPagamento = new ArrayList<>();
        formasPagamento.add("Cartao");
        formasPagamento.add("Pix");

        Passageiro passageiro = new Passageiro(
                2,
                "Ana Souza",
                "(11) 99999-0002",
                "ana@email.com",
                "Av. Central, 200",
                regiaoCentro,
                "Cadeirante",
                formasPagamento);

        UsuarioService usuarioService = new UsuarioService();
        CorridaService corridaService = new CorridaService();
        PagamentoService pagamentoService = new PagamentoService();
        AvaliacaoService avaliacaoService = new AvaliacaoService();

        usuarioService.cadastrar(motorista);
        usuarioService.cadastrar(passageiro);

        Corrida corrida = new Corrida(
                101,
                passageiro,
                null,
                "Hospital Municipal",
                "Centro de Reabilitacao",
                "27/04/2026",
                "14:00",
                "PENDENTE",
                0.0);

        corridaService.solicitar(corrida);

        ArrayList<Motorista> motoristasDisponiveis = new ArrayList<>();
        motoristasDisponiveis.add(motorista);

        Motorista motoristaSelecionado = corridaService.matchMotorista(corrida, motoristasDisponiveis);

        if (motoristaSelecionado != null) {
            corrida.iniciarCorrida();
            corridaService.finalizar(corrida);

            Pagamento pagamento = new Pagamento(
                    201,
                    corrida,
                    corrida.getValor(),
                    "Pix",
                    "PENDENTE");
            pagamentoService.processarPagamento(pagamento);

            Avaliacao avaliacao = passageiro.avaliarCorrida(301, corrida, 5, "Motorista atencioso e pontual.");
            avaliacaoService.registrar(avaliacao);
            avaliacaoService.calcularMedia(motoristaSelecionado);
        }

        System.out.println("=== USUARIOS ===");
        for (Object usuario : usuarioService.listar()) {
            System.out.println(usuario);
        }

        System.out.println("\n=== CORRIDAS ===");
        for (Corrida item : corridaService.listar()) {
            System.out.println(item);
        }

        System.out.println("\n=== PAGAMENTOS ===");
        for (Pagamento item : pagamentoService.listar()) {
            System.out.println(item);
        }

        System.out.println("\n=== AVALIACOES ===");
        for (Avaliacao item : avaliacaoService.listar()) {
            System.out.println(item);
        }
    }
}
