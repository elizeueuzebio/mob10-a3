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
- `src/control`: regras de negocio, listas `ArrayList` e operacoes CRUD
- `src/view`: menu interativo e fluxo de execucao
- `docs`: material de apoio para a entrega do checkpoint 3

## Requisitos do checkpoint 3 atendidos

- heranca entre `Usuario`, `Motorista` e `Passageiro`
- heranca entre `Veiculo`, `CarroComum`, `CarroAdaptado` e `Van`
- polimorfismo com `override` em `exibirPerfil()`, `exibirDetalhes()`, `calcularTarifa()` e `toString()`
- sobrecarga em construtores e metodos de `Motorista`, `Passageiro`, `Corrida`, `Pagamento` e `Avaliacao`
- menu interativo funcional por entidade
- validacao de entrada para ID, email, telefone, CNH, placa, data, horario e nota
- CRUD com `ArrayList` nos controles de usuarios, corridas, pagamentos e avaliacoes
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

### Control

- `UsuarioControl`
- `CorridaControl`
- `PagamentoControl`
- `AvaliacaoControl`

### View

- `Main`
- `MenuAplicacao`

## Como executar com Java recente

Recomendado: JDK 17 ou superior. O projeto tambem funciona com JDK 21.

No PowerShell, use:

```powershell
.\run.ps1
```

Esse script compila e executa usando o JDK mais recente encontrado na maquina. Ele evita o erro de compilar com `javac` novo e executar com um `java` antigo no PATH.

Se a politica de execucao do PowerShell bloquear scripts locais, use:

```powershell
powershell -ExecutionPolicy Bypass -File .\run.ps1
```

Para compilar e executar manualmente:

```bash
javac -d bin src/model/*.java src/control/*.java src/view/*.java
java -cp bin view.Main
```

Se houver mais de uma instalacao do Java no computador, confirme se `javac` e `java` apontam para a mesma versao recente:

```bash
javac -version
java -version
```

No Windows, tambem vale conferir a ordem do PATH:

```bash
where javac
where java
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
