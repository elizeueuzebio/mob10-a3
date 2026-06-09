package view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.IntFunction;
import java.util.regex.Pattern;

import control.AvaliacaoControl;
import control.CorridaControl;
import control.PagamentoControl;
import control.UsuarioControl;
import model.Avaliacao;
import model.CarroAdaptado;
import model.CarroComum;
import model.Corrida;
import model.Motorista;
import model.Pagamento;
import model.Passageiro;
import model.Regiao;
import model.Usuario;
import model.Van;
import model.Veiculo;

public class MenuAplicacao {
    private static final String REPO_GITHUB = "https://github.com/elizeueuzebio/mob10-a3";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PLACA_PATTERN = Pattern.compile("^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$");
    private static final Pattern TELEFONE_DIGITOS = Pattern.compile("^\\d{10,11}$");
    private static final Pattern CNH_DIGITOS = Pattern.compile("^\\d{11}$");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm")
            .withResolverStyle(ResolverStyle.STRICT);

    private final Scanner scanner;
    private final UsuarioControl usuarioControl;
    private final CorridaControl corridaControl;
    private final PagamentoControl pagamentoControl;
    private final AvaliacaoControl avaliacaoControl;

    public MenuAplicacao() {
        this.scanner = new Scanner(System.in);
        this.usuarioControl = new UsuarioControl();
        this.corridaControl = new CorridaControl();
        this.pagamentoControl = new PagamentoControl();
        this.avaliacaoControl = new AvaliacaoControl();
        carregarDadosIniciais();
    }

    public void executar() {
        boolean executando = true;

        while (executando) {
            exibirCabecalho("MOB10 - CHECKPOINT 3");
            System.out.println("1. Passageiros");
            System.out.println("2. Motoristas");
            System.out.println("3. Corridas");
            System.out.println("4. Pagamentos");
            System.out.println("5. Avaliacoes");
            System.out.println("6. Listar usuarios");
            System.out.println("7. Resumo do checkpoint");
            System.out.println("0. Sair");

            int opcao = lerInteiroNoIntervalo("Escolha uma opcao: ", 0, 7);

            switch (opcao) {
                case 1:
                    menuPassageiros();
                    break;
                case 2:
                    menuMotoristas();
                    break;
                case 3:
                    menuCorridas();
                    break;
                case 4:
                    menuPagamentos();
                    break;
                case 5:
                    menuAvaliacoes();
                    break;
                case 6:
                    listarUsuarios();
                    pausar();
                    break;
                case 7:
                    exibirResumoCheckpoint();
                    pausar();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Aplicacao finalizada.");
                    break;
                default:
                    break;
            }
        }

        scanner.close();
    }

    private void menuPassageiros() {
        boolean voltar = false;

        while (!voltar) {
            exibirCabecalho("PASSAGEIROS");
            System.out.println("1. Cadastrar passageiro");
            System.out.println("2. Listar passageiros");
            System.out.println("3. Atualizar passageiro");
            System.out.println("4. Remover passageiro");
            System.out.println("0. Voltar");

            int opcao = lerInteiroNoIntervalo("Escolha uma opcao: ", 0, 4);
            switch (opcao) {
                case 1:
                    cadastrarPassageiro();
                    pausar();
                    break;
                case 2:
                    listarPassageiros();
                    pausar();
                    break;
                case 3:
                    atualizarPassageiro();
                    pausar();
                    break;
                case 4:
                    removerPassageiro();
                    pausar();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void menuMotoristas() {
        boolean voltar = false;

        while (!voltar) {
            exibirCabecalho("MOTORISTAS");
            System.out.println("1. Cadastrar motorista");
            System.out.println("2. Listar motoristas");
            System.out.println("3. Atualizar motorista");
            System.out.println("4. Remover motorista");
            System.out.println("0. Voltar");

            int opcao = lerInteiroNoIntervalo("Escolha uma opcao: ", 0, 4);
            switch (opcao) {
                case 1:
                    cadastrarMotorista();
                    pausar();
                    break;
                case 2:
                    listarMotoristas();
                    pausar();
                    break;
                case 3:
                    atualizarMotorista();
                    pausar();
                    break;
                case 4:
                    removerMotorista();
                    pausar();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void menuCorridas() {
        boolean voltar = false;

        while (!voltar) {
            exibirCabecalho("CORRIDAS");
            System.out.println("1. Solicitar corrida");
            System.out.println("2. Listar corridas");
            System.out.println("3. Atualizar rota da corrida");
            System.out.println("4. Despachar e iniciar corrida");
            System.out.println("5. Finalizar corrida");
            System.out.println("6. Cancelar corrida");
            System.out.println("7. Remover corrida");
            System.out.println("0. Voltar");

            int opcao = lerInteiroNoIntervalo("Escolha uma opcao: ", 0, 7);
            switch (opcao) {
                case 1:
                    solicitarCorrida();
                    pausar();
                    break;
                case 2:
                    listarCorridas();
                    pausar();
                    break;
                case 3:
                    atualizarCorrida();
                    pausar();
                    break;
                case 4:
                    despacharCorrida();
                    pausar();
                    break;
                case 5:
                    finalizarCorrida();
                    pausar();
                    break;
                case 6:
                    cancelarCorrida();
                    pausar();
                    break;
                case 7:
                    removerCorrida();
                    pausar();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void menuPagamentos() {
        boolean voltar = false;

        while (!voltar) {
            exibirCabecalho("PAGAMENTOS");
            System.out.println("1. Processar pagamento");
            System.out.println("2. Listar pagamentos");
            System.out.println("3. Atualizar metodo de pagamento");
            System.out.println("4. Remover pagamento");
            System.out.println("0. Voltar");

            int opcao = lerInteiroNoIntervalo("Escolha uma opcao: ", 0, 4);
            switch (opcao) {
                case 1:
                    processarPagamento();
                    pausar();
                    break;
                case 2:
                    listarPagamentos();
                    pausar();
                    break;
                case 3:
                    atualizarPagamento();
                    pausar();
                    break;
                case 4:
                    removerPagamento();
                    pausar();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void menuAvaliacoes() {
        boolean voltar = false;

        while (!voltar) {
            exibirCabecalho("AVALIACOES");
            System.out.println("1. Registrar avaliacao");
            System.out.println("2. Listar avaliacoes");
            System.out.println("3. Atualizar avaliacao");
            System.out.println("4. Remover avaliacao");
            System.out.println("0. Voltar");

            int opcao = lerInteiroNoIntervalo("Escolha uma opcao: ", 0, 4);
            switch (opcao) {
                case 1:
                    registrarAvaliacao();
                    pausar();
                    break;
                case 2:
                    listarAvaliacoes();
                    pausar();
                    break;
                case 3:
                    atualizarAvaliacao();
                    pausar();
                    break;
                case 4:
                    removerAvaliacao();
                    pausar();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void cadastrarPassageiro() {
        exibirCabecalho("CADASTRAR PASSAGEIRO");

        int id = lerNovoIdUsuario();
        String nome = lerTextoObrigatorio("Nome: ");
        String telefone = lerTelefoneValido();
        String email = lerEmailValido();
        String endereco = lerTextoObrigatorio("Endereco: ");
        Regiao regiao = new Regiao(lerTextoObrigatorio("Regiao: "));
        String necessidadeEspecial = lerTextoOpcional("Necessidade especial (enter para nenhuma): ");
        ArrayList<String> formasPagamento = lerFormasPagamento();

        Passageiro passageiro = new Passageiro(id, nome, telefone, email, endereco, regiao,
                estaVazio(necessidadeEspecial) ? "Nenhuma" : necessidadeEspecial,
                formasPagamento);

        if (usuarioControl.cadastrar(passageiro)) {
            System.out.println("Passageiro cadastrado com sucesso.");
        } else {
            System.out.println("Nao foi possivel cadastrar o passageiro.");
        }
    }

    private void listarPassageiros() {
        exibirCabecalho("LISTA DE PASSAGEIROS");
        ArrayList<Passageiro> passageiros = usuarioControl.listarPassageiros();
        if (passageiros.isEmpty()) {
            System.out.println("Nenhum passageiro cadastrado.");
            return;
        }

        for (Passageiro passageiro : passageiros) {
            System.out.println(passageiro.exibirPerfil());
            System.out.println(passageiro);
            System.out.println();
        }
    }

    private void atualizarPassageiro() {
        exibirCabecalho("ATUALIZAR PASSAGEIRO");
        listarPassageiros();

        int id = lerInteiroPositivo("Informe o ID do passageiro: ");
        Passageiro passageiroAtual = usuarioControl.buscarPassageiroPorId(id);

        if (passageiroAtual == null) {
            System.out.println("Passageiro nao encontrado.");
            return;
        }

        String nome = lerTextoObrigatorio("Novo nome: ");
        String telefone = lerTelefoneValido();
        String email = lerEmailValido();
        String endereco = lerTextoObrigatorio("Novo endereco: ");
        Regiao regiao = new Regiao(lerTextoObrigatorio("Nova regiao: "));
        String necessidadeEspecial = lerTextoOpcional("Necessidade especial (enter para nenhuma): ");
        ArrayList<String> formasPagamento = lerFormasPagamento();

        Passageiro passageiroAtualizado = new Passageiro(id, nome, telefone, email, endereco, regiao,
                estaVazio(necessidadeEspecial) ? "Nenhuma" : necessidadeEspecial,
                formasPagamento);
        passageiroAtualizado.setListaCorridas(passageiroAtual.getListaCorridas());

        if (usuarioControl.atualizar(passageiroAtualizado)) {
            System.out.println("Passageiro atualizado com sucesso.");
        } else {
            System.out.println("Nao foi possivel atualizar o passageiro.");
        }
    }

    private void removerPassageiro() {
        exibirCabecalho("REMOVER PASSAGEIRO");
        listarPassageiros();

        int id = lerInteiroPositivo("Informe o ID do passageiro: ");
        Passageiro passageiro = usuarioControl.buscarPassageiroPorId(id);

        if (passageiro == null) {
            System.out.println("Passageiro nao encontrado.");
            return;
        }

        if (usuarioControl.removerPorId(id)) {
            System.out.println("Passageiro removido com sucesso.");
        } else {
            System.out.println("Nao foi possivel remover o passageiro.");
        }
    }

    private void cadastrarMotorista() {
        exibirCabecalho("CADASTRAR MOTORISTA");

        int id = lerNovoIdUsuario();
        String nome = lerTextoObrigatorio("Nome: ");
        String telefone = lerTelefoneValido();
        String email = lerEmailValido();
        String endereco = lerTextoObrigatorio("Endereco: ");
        Regiao regiao = new Regiao(lerTextoObrigatorio("Regiao: "));
        String cnh = lerCnhValida();
        Veiculo veiculo = criarVeiculoPorEntrada();
        boolean disponibilidade = lerBooleano("Motorista disponivel agora? (S/N): ");

        Motorista motorista = new Motorista(id, nome, telefone, email, endereco, regiao, cnh, veiculo,
                disponibilidade);

        if (usuarioControl.cadastrar(motorista)) {
            System.out.println("Motorista cadastrado com sucesso.");
        } else {
            System.out.println("Nao foi possivel cadastrar o motorista.");
        }
    }

    private void listarMotoristas() {
        exibirCabecalho("LISTA DE MOTORISTAS");
        ArrayList<Motorista> motoristas = usuarioControl.listarMotoristas();
        if (motoristas.isEmpty()) {
            System.out.println("Nenhum motorista cadastrado.");
            return;
        }

        for (Motorista motorista : motoristas) {
            System.out.println(motorista.exibirPerfil());
            System.out.println(motorista);
            System.out.println();
        }
    }

    private void atualizarMotorista() {
        exibirCabecalho("ATUALIZAR MOTORISTA");
        listarMotoristas();

        int id = lerInteiroPositivo("Informe o ID do motorista: ");
        Motorista motoristaAtual = usuarioControl.buscarMotoristaPorId(id);

        if (motoristaAtual == null) {
            System.out.println("Motorista nao encontrado.");
            return;
        }

        String nome = lerTextoObrigatorio("Novo nome: ");
        String telefone = lerTelefoneValido();
        String email = lerEmailValido();
        String endereco = lerTextoObrigatorio("Novo endereco: ");
        Regiao regiao = new Regiao(lerTextoObrigatorio("Nova regiao: "));
        String cnh = lerCnhValida();
        Veiculo veiculo = criarVeiculoPorEntrada();
        boolean disponibilidade = lerBooleano("Motorista disponivel agora? (S/N): ");

        Motorista motoristaAtualizado = new Motorista(id, nome, telefone, email, endereco, regiao, cnh, veiculo,
                disponibilidade);
        motoristaAtualizado.setAvaliacaoMedia(motoristaAtual.getAvaliacaoMedia());
        motoristaAtualizado.setListaCorridas(motoristaAtual.getListaCorridas());

        if (usuarioControl.atualizar(motoristaAtualizado)) {
            System.out.println("Motorista atualizado com sucesso.");
        } else {
            System.out.println("Nao foi possivel atualizar o motorista.");
        }
    }

    private void removerMotorista() {
        exibirCabecalho("REMOVER MOTORISTA");
        listarMotoristas();

        int id = lerInteiroPositivo("Informe o ID do motorista: ");
        Motorista motorista = usuarioControl.buscarMotoristaPorId(id);

        if (motorista == null) {
            System.out.println("Motorista nao encontrado.");
            return;
        }

        if (usuarioControl.removerPorId(id)) {
            System.out.println("Motorista removido com sucesso.");
        } else {
            System.out.println("Nao foi possivel remover o motorista.");
        }
    }

    private void solicitarCorrida() {
        exibirCabecalho("SOLICITAR CORRIDA");

        if (usuarioControl.listarPassageiros().isEmpty()) {
            System.out.println("Cadastre pelo menos um passageiro antes de solicitar corridas.");
            return;
        }

        listarPassageiros();
        int idCorrida = lerNovoIdCorrida();
        int idPassageiro = lerInteiroPositivo("Informe o ID do passageiro: ");
        Passageiro passageiro = usuarioControl.buscarPassageiroPorId(idPassageiro);

        if (passageiro == null) {
            System.out.println("Passageiro nao encontrado.");
            return;
        }

        String origem = lerTextoObrigatorio("Origem: ");
        String destino = lerTextoObrigatorio("Destino: ");
        String data = lerDataValida();
        String horario = lerHorarioValido();

        Corrida corrida = new Corrida(idCorrida, passageiro, origem, destino, data, horario);

        if (corridaControl.solicitar(corrida)) {
            System.out.println("Corrida solicitada com sucesso.");
        } else {
            System.out.println("Nao foi possivel solicitar a corrida.");
        }
    }

    private void listarCorridas() {
        exibirCabecalho("LISTA DE CORRIDAS");
        ArrayList<Corrida> corridas = corridaControl.listar();
        if (corridas.isEmpty()) {
            System.out.println("Nenhuma corrida cadastrada.");
            return;
        }

        for (Corrida corrida : corridas) {
            System.out.println(corrida);
        }
    }

    private void atualizarCorrida() {
        exibirCabecalho("ATUALIZAR ROTA DA CORRIDA");
        listarCorridas();

        int id = lerInteiroPositivo("Informe o ID da corrida: ");
        Corrida corrida = corridaControl.buscarPorId(id);

        if (corrida == null) {
            System.out.println("Corrida nao encontrada.");
            return;
        }

        String origem = lerTextoObrigatorio("Nova origem: ");
        String destino = lerTextoObrigatorio("Novo destino: ");
        String data = lerDataValida();
        String horario = lerHorarioValido();

        if (corridaControl.atualizarRota(id, origem, destino, data, horario)) {
            System.out.println("Corrida atualizada com sucesso.");
        } else {
            System.out.println("Nao foi possivel atualizar a corrida.");
        }
    }

    private void despacharCorrida() {
        exibirCabecalho("DESPACHAR CORRIDA");
        listarCorridas();

        ArrayList<Motorista> motoristasDisponiveis = usuarioControl.listarMotoristasDisponiveis();
        if (motoristasDisponiveis.isEmpty()) {
            System.out.println("Nao ha motoristas disponiveis no momento.");
            return;
        }

        int id = lerInteiroPositivo("Informe o ID da corrida: ");
        Motorista motorista = corridaControl.despacharCorrida(id, motoristasDisponiveis);

        if (motorista == null) {
            System.out.println("Nao foi possivel despachar a corrida. Verifique status e compatibilidade.");
            return;
        }

        Corrida corrida = corridaControl.buscarPorId(id);
        System.out.println("Corrida iniciada com motorista " + motorista.getNome() + ".");
        System.out.printf("Valor calculado: R$ %.2f%n", corrida.getValor());
    }

    private void finalizarCorrida() {
        exibirCabecalho("FINALIZAR CORRIDA");
        listarCorridas();

        int id = lerInteiroPositivo("Informe o ID da corrida: ");
        if (corridaControl.finalizar(id)) {
            System.out.println("Corrida finalizada com sucesso.");
        } else {
            System.out.println("Nao foi possivel finalizar a corrida. A corrida precisa estar em andamento.");
        }
    }

    private void cancelarCorrida() {
        exibirCabecalho("CANCELAR CORRIDA");
        listarCorridas();

        int id = lerInteiroPositivo("Informe o ID da corrida: ");
        if (corridaControl.cancelar(id)) {
            System.out.println("Corrida cancelada com sucesso.");
        } else {
            System.out.println("Nao foi possivel cancelar a corrida.");
        }
    }

    private void removerCorrida() {
        exibirCabecalho("REMOVER CORRIDA");
        listarCorridas();

        int id = lerInteiroPositivo("Informe o ID da corrida: ");
        if (corridaControl.removerPorId(id)) {
            System.out.println("Corrida removida com sucesso.");
        } else {
            System.out.println("Nao foi possivel remover a corrida.");
        }
    }

    private void processarPagamento() {
        exibirCabecalho("PROCESSAR PAGAMENTO");
        listarCorridas();

        int idCorrida = lerInteiroPositivo("Informe o ID da corrida finalizada: ");
        Corrida corrida = corridaControl.buscarPorId(idCorrida);

        if (corrida == null) {
            System.out.println("Corrida nao encontrada.");
            return;
        }

        if (pagamentoControl.buscarPorCorridaId(idCorrida) != null) {
            System.out.println("Essa corrida ja possui pagamento registrado.");
            return;
        }

        int idPagamento = lerNovoIdPagamento();
        String metodoPagamento = lerMetodoPagamento();
        Pagamento pagamento = new Pagamento(idPagamento, corrida, corrida.getValor(), metodoPagamento);

        if (pagamentoControl.processarPagamento(pagamento)) {
            System.out.println("Pagamento processado com sucesso.");
        } else {
            System.out.println("Nao foi possivel processar o pagamento. Finalize uma corrida com valor calculado.");
        }
    }

    private void listarPagamentos() {
        exibirCabecalho("LISTA DE PAGAMENTOS");
        ArrayList<Pagamento> pagamentos = pagamentoControl.listar();
        if (pagamentos.isEmpty()) {
            System.out.println("Nenhum pagamento registrado.");
            return;
        }

        for (Pagamento pagamento : pagamentos) {
            System.out.println(pagamento);
        }
    }

    private void atualizarPagamento() {
        exibirCabecalho("ATUALIZAR PAGAMENTO");
        listarPagamentos();

        int id = lerInteiroPositivo("Informe o ID do pagamento: ");
        if (pagamentoControl.buscarPorId(id) == null) {
            System.out.println("Pagamento nao encontrado.");
            return;
        }

        String metodoPagamento = lerMetodoPagamento();
        if (pagamentoControl.atualizarMetodoPagamento(id, metodoPagamento)) {
            System.out.println("Pagamento atualizado com sucesso.");
        } else {
            System.out.println("Nao foi possivel atualizar o pagamento.");
        }
    }

    private void removerPagamento() {
        exibirCabecalho("REMOVER PAGAMENTO");
        listarPagamentos();

        int id = lerInteiroPositivo("Informe o ID do pagamento: ");
        if (pagamentoControl.removerPorId(id)) {
            System.out.println("Pagamento removido com sucesso.");
        } else {
            System.out.println("Nao foi possivel remover o pagamento.");
        }
    }

    private void registrarAvaliacao() {
        exibirCabecalho("REGISTRAR AVALIACAO");
        listarCorridas();

        int idCorrida = lerInteiroPositivo("Informe o ID da corrida finalizada: ");
        Corrida corrida = corridaControl.buscarPorId(idCorrida);

        if (corrida == null) {
            System.out.println("Corrida nao encontrada.");
            return;
        }

        if (avaliacaoControl.buscarPorCorridaId(idCorrida) != null) {
            System.out.println("Essa corrida ja possui avaliacao registrada.");
            return;
        }

        if (corrida.getPassageiro() == null) {
            System.out.println("A corrida nao possui passageiro vinculado.");
            return;
        }

        int idAvaliacao = lerNovoIdAvaliacao();
        int nota = lerNotaValida();
        String comentario = lerTextoOpcional("Comentario (opcional): ");

        Avaliacao avaliacao;
        if (estaVazio(comentario)) {
            avaliacao = corrida.getPassageiro().avaliarCorrida(idAvaliacao, corrida, nota);
        } else {
            avaliacao = corrida.getPassageiro().avaliarCorrida(idAvaliacao, corrida, nota, comentario);
        }

        if (avaliacaoControl.registrar(avaliacao)) {
            System.out.println("Avaliacao registrada com sucesso.");
        } else {
            System.out.println("Nao foi possivel registrar a avaliacao.");
        }
    }

    private void listarAvaliacoes() {
        exibirCabecalho("LISTA DE AVALIACOES");
        ArrayList<Avaliacao> avaliacoes = avaliacaoControl.listar();
        if (avaliacoes.isEmpty()) {
            System.out.println("Nenhuma avaliacao registrada.");
            return;
        }

        for (Avaliacao avaliacao : avaliacoes) {
            System.out.println(avaliacao);
        }
    }

    private void atualizarAvaliacao() {
        exibirCabecalho("ATUALIZAR AVALIACAO");
        listarAvaliacoes();

        int id = lerInteiroPositivo("Informe o ID da avaliacao: ");
        if (avaliacaoControl.buscarPorId(id) == null) {
            System.out.println("Avaliacao nao encontrada.");
            return;
        }

        int nota = lerNotaValida();
        String comentario = lerTextoOpcional("Novo comentario (opcional): ");

        if (avaliacaoControl.atualizarComentario(id, nota, comentario)) {
            System.out.println("Avaliacao atualizada com sucesso.");
        } else {
            System.out.println("Nao foi possivel atualizar a avaliacao.");
        }
    }

    private void removerAvaliacao() {
        exibirCabecalho("REMOVER AVALIACAO");
        listarAvaliacoes();

        int id = lerInteiroPositivo("Informe o ID da avaliacao: ");
        if (avaliacaoControl.removerPorId(id)) {
            System.out.println("Avaliacao removida com sucesso.");
        } else {
            System.out.println("Nao foi possivel remover a avaliacao.");
        }
    }

    private void listarUsuarios() {
        exibirCabecalho("LISTA GERAL DE USUARIOS");
        ArrayList<Usuario> usuarios = usuarioControl.listar();
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuario cadastrado.");
            return;
        }

        // O polimorfismo aparece aqui: cada subtipo responde ao mesmo metodo exibirPerfil().
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.exibirPerfil());
            System.out.println(usuario);
            System.out.println();
        }
    }

    private void exibirResumoCheckpoint() {
        exibirCabecalho("RESUMO DO CHECKPOINT 3");
        System.out.println("Repositorio GitHub: " + REPO_GITHUB);
        System.out.println("Modelagem POO: heranca em Usuario/Passageiro/Motorista e Veiculo/subclasses.");
        System.out.println("Polimorfismo: override em exibirPerfil, exibirDetalhes, calcularTarifa e toString.");
        System.out.println("Sobrecarga: construtores e metodos em Corrida, Motorista, Passageiro, Pagamento e Avaliacao.");
        System.out.println("CRUD: usuarios, corridas, pagamentos e avaliacoes com ArrayList nos controles.");
        System.out.println("Validacoes: ID unico, email, telefone, CNH, placa, data, horario e nota.");
        System.out.println("Comentario de codigo: metodos chave comentados em model, control e view.");
    }

    private void carregarDadosIniciais() {
        // Os dados iniciais aceleram a demonstracao do checkpoint sem depender de cadastro manual.
        Regiao centro = new Regiao("Centro");
        Regiao zonaSul = new Regiao("Zona Sul");

        ArrayList<Usuario> usuariosIniciais = new ArrayList<>();

        Motorista carlos = new Motorista(1, "Carlos Lima", "(11)999990001", "carlos@mob10.com",
                "Rua das Flores, 100", centro, "12345678901",
                new CarroAdaptado("Spin", "ABC1D23", 4), true);

        Motorista bruna = new Motorista(2, "Bruna Melo", "(11)999990002", "bruna@mob10.com",
                "Av. Acessivel, 200", zonaSul, "10987654321",
                new Van("Ducato", "BRA2E45", true, 6), true);

        ArrayList<String> formasPagamento = new ArrayList<>();
        formasPagamento.add(Pagamento.METODO_PIX);
        formasPagamento.add(Pagamento.METODO_CARTAO);

        Passageiro ana = new Passageiro(3, "Ana Souza", "(11)999990003", "ana@email.com",
                "Av. Central, 200", centro, "Cadeirante", formasPagamento);

        Passageiro joao = new Passageiro(4, "Joao Santos", "(11)999990004", "joao@email.com",
                "Rua do Parque, 50", zonaSul, "Nenhuma");

        usuariosIniciais.add(carlos);
        usuariosIniciais.add(bruna);
        usuariosIniciais.add(ana);
        usuariosIniciais.add(joao);

        usuarioControl.cadastrar(usuariosIniciais);
    }

    private Veiculo criarVeiculoPorEntrada() {
        System.out.println("Tipos de veiculo:");
        System.out.println("1. Carro comum");
        System.out.println("2. Carro adaptado");
        System.out.println("3. Van");

        int tipoVeiculo = lerInteiroNoIntervalo("Escolha o tipo de veiculo: ", 1, 3);
        String modelo = lerTextoObrigatorio("Modelo do veiculo: ");
        String placa = lerPlacaValida();
        int capacidade = lerInteiroPositivo("Capacidade de passageiros: ");

        switch (tipoVeiculo) {
            case 1:
                return new CarroComum(modelo, placa, capacidade);
            case 2:
                return new CarroAdaptado(modelo, placa, capacidade);
            case 3:
                boolean adaptada = lerBooleano("A van e adaptada? (S/N): ");
                return new Van(modelo, placa, adaptada, capacidade);
            default:
                return new CarroComum(modelo, placa, capacidade);
        }
    }

    private ArrayList<String> lerFormasPagamento() {
        ArrayList<String> formasPagamento = new ArrayList<>();
        String entrada = lerTextoOpcional("Formas de pagamento (separadas por virgula, enter para Pix): ");

        if (estaVazio(entrada)) {
            formasPagamento.add(Pagamento.METODO_PIX);
            return formasPagamento;
        }

        String[] partes = entrada.split(",");
        for (String parte : partes) {
            String forma = parte.trim();
            if (!estaVazio(forma)) {
                formasPagamento.add(forma);
            }
        }

        if (formasPagamento.isEmpty()) {
            formasPagamento.add(Pagamento.METODO_PIX);
        }

        return formasPagamento;
    }

    private String lerMetodoPagamento() {
        System.out.println("Metodos de pagamento:");
        System.out.println("1. Pix");
        System.out.println("2. Cartao");
        System.out.println("3. Dinheiro");

        int opcao = lerInteiroNoIntervalo("Escolha o metodo: ", 1, 3);
        switch (opcao) {
            case 1:
                return Pagamento.METODO_PIX;
            case 2:
                return Pagamento.METODO_CARTAO;
            case 3:
                return Pagamento.METODO_DINHEIRO;
            default:
                return Pagamento.METODO_PIX;
        }
    }

    private int lerNovoIdUsuario() {
        return lerNovoId("ID do usuario: ", id -> usuarioControl.buscarPorId(id),
                "Ja existe um usuario com esse ID.");
    }

    private int lerNovoIdCorrida() {
        return lerNovoId("ID da corrida: ", id -> corridaControl.buscarPorId(id),
                "Ja existe uma corrida com esse ID.");
    }

    private int lerNovoIdPagamento() {
        return lerNovoId("ID do pagamento: ", id -> pagamentoControl.buscarPorId(id),
                "Ja existe um pagamento com esse ID.");
    }

    private int lerNovoIdAvaliacao() {
        return lerNovoId("ID da avaliacao: ", id -> avaliacaoControl.buscarPorId(id),
                "Ja existe uma avaliacao com esse ID.");
    }

    private int lerNovoId(String mensagem, IntFunction<?> buscarPorId, String mensagemDuplicado) {
        int id;
        do {
            id = lerInteiroPositivo(mensagem);
            if (buscarPorId.apply(id) != null) {
                System.out.println(mensagemDuplicado);
            }
        } while (buscarPorId.apply(id) != null);
        return id;
    }

    private int lerInteiroNoIntervalo(String mensagem, int minimo, int maximo) {
        int valor;
        do {
            valor = lerInteiro(mensagem);
            if (valor < minimo || valor > maximo) {
                System.out.println("Informe um valor entre " + minimo + " e " + maximo + ".");
            }
        } while (valor < minimo || valor > maximo);
        return valor;
    }

    private int lerInteiroPositivo(String mensagem) {
        int valor;
        do {
            valor = lerInteiro(mensagem);
            if (valor <= 0) {
                System.out.println("Informe um numero positivo.");
            }
        } while (valor <= 0);
        return valor;
    }

    private int lerInteiro(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException exception) {
                System.out.println("Entrada invalida. Digite um numero inteiro.");
            }
        }
    }

    private String lerTextoObrigatorio(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            if (!estaVazio(entrada)) {
                return entrada;
            }
            System.out.println("Campo obrigatorio. Tente novamente.");
        }
    }

    private String lerTextoOpcional(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private String lerEmailValido() {
        while (true) {
            String email = lerTextoObrigatorio("Email: ");
            if (EMAIL_PATTERN.matcher(email).matches()) {
                return email;
            }
            System.out.println("Email invalido.");
        }
    }

    private String lerTelefoneValido() {
        while (true) {
            String telefone = lerTextoObrigatorio("Telefone: ");
            String somenteDigitos = telefone.replaceAll("\\D", "");
            if (TELEFONE_DIGITOS.matcher(somenteDigitos).matches()) {
                return telefone;
            }
            System.out.println("Telefone invalido. Use 10 ou 11 digitos.");
        }
    }

    private String lerCnhValida() {
        while (true) {
            String cnh = lerTextoObrigatorio("CNH (11 digitos): ").replaceAll("\\D", "");
            if (CNH_DIGITOS.matcher(cnh).matches()) {
                return cnh;
            }
            System.out.println("CNH invalida.");
        }
    }

    private String lerPlacaValida() {
        while (true) {
            String placa = lerTextoObrigatorio("Placa do veiculo: ").replace("-", "").toUpperCase();
            if (PLACA_PATTERN.matcher(placa).matches()) {
                return placa;
            }
            System.out.println("Placa invalida. Exemplo aceito: ABC1D23.");
        }
    }

    private String lerDataValida() {
        while (true) {
            String data = lerTextoObrigatorio("Data (dd/MM/aaaa): ");
            try {
                LocalDate.parse(data, DATE_FORMATTER);
                return data;
            } catch (Exception exception) {
                System.out.println("Data invalida.");
            }
        }
    }

    private String lerHorarioValido() {
        while (true) {
            String horario = lerTextoObrigatorio("Horario (HH:mm): ");
            try {
                LocalTime.parse(horario, TIME_FORMATTER);
                return horario;
            } catch (Exception exception) {
                System.out.println("Horario invalido.");
            }
        }
    }

    private int lerNotaValida() {
        return lerInteiroNoIntervalo("Nota da avaliacao (1 a 5): ", 1, 5);
    }

    private boolean lerBooleano(String mensagem) {
        while (true) {
            String entrada = lerTextoObrigatorio(mensagem).toUpperCase();
            if ("S".equals(entrada)) {
                return true;
            }
            if ("N".equals(entrada)) {
                return false;
            }
            System.out.println("Digite S para sim ou N para nao.");
        }
    }

    private boolean estaVazio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    private void exibirCabecalho(String titulo) {
        System.out.println();
        System.out.println("==========================================");
        System.out.println(titulo);
        System.out.println("==========================================");
    }

    private void pausar() {
        System.out.println();
        System.out.print("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
}
