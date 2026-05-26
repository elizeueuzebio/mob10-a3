# Mob10

Projeto academico em Java para a A3 com foco em mobilidade urbana acessivel.

## Objetivo

O Mob10 simula um aplicativo de transporte voltado para passageiros com mobilidade reduzida, como cadeirantes, idosos e pessoas em recuperacao medica. O sistema conecta passageiros a motoristas com veiculos compativeis e registra o ciclo principal da corrida:

- cadastro de usuarios
- solicitacao de corridas
- despacho de motorista
- finalizacao da corrida
- processamento do pagamento
- registro de avaliacao

## Estrutura do projeto

- `src/model`: classes de dominio e relacionamentos POO
- `src/service`: regras de negocio, listas `ArrayList` e operacoes CRUD
- `src/view`: menu interativo e fluxo de execucao
- `docs`: material de apoio para a entrega do checkpoint 3

## Requisitos do checkpoint 3 atendidos

- heranca entre `Usuario`, `Motorista` e `Passageiro`
- heranca entre `Veiculo`, `CarroComum`, `CarroAdaptado` e `Van`
- polimorfismo com `override` em `exibirPerfil()`, `exibirDetalhes()`, `calcularTarifa()` e `toString()`
- sobrecarga em construtores e metodos de `Motorista`, `Passageiro`, `Corrida`, `Pagamento` e `Avaliacao`
- menu interativo funcional por entidade
- validacao de entrada para ID, email, telefone, CNH, placa, data, horario e nota
- CRUD com `ArrayList` nos services de usuarios, corridas, pagamentos e avaliacoes
- comentarios curtos explicando a logica central de POO e consistencia das listas
- codigo versionado no GitHub

## Classes principais

### Model

- `Usuario`
- `Motorista`
- `Passageiro`
- `Veiculo`
- `CarroComum`
- `CarroAdaptado`
- `Van`
- `Corrida`
- `Pagamento`
- `Avaliacao`
- `Regiao`

### Service

- `UsuarioService`
- `CorridaService`
- `PagamentoService`
- `AvaliacaoService`

### View

- `Main`
- `MenuAplicacao`

## Como executar

O projeto esta compativel com Java 8 ou superior.

```bash
javac -d bin src/model/*.java src/service/*.java src/view/*.java
java -cp bin view.Main
```

Se estiver compilando com um JDK mais novo e quiser garantir bytecode compativel com Java 8:

```bash
javac --release 8 -d bin src/model/*.java src/service/*.java src/view/*.java
java -cp bin view.Main
```

Se existir mais de uma instalacao do Java no computador, confirme a versao com:

```bash
javac -version
java -version
```

## Fluxo sugerido para demonstracao

1. Listar os usuarios iniciais.
2. Solicitar uma nova corrida para um passageiro.
3. Despachar a corrida para um motorista disponivel.
4. Finalizar a corrida.
5. Processar o pagamento.
6. Registrar a avaliacao.
7. Exibir o resumo do checkpoint no proprio menu.

## Repositorio GitHub

- Repo publico: `https://github.com/elizeueuzebio/mob10-a3`

## Documentacao do checkpoint 3

- Relatorio base: `docs/CHECKPOINT3_RELATORIO.md`
