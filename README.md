# Mob10

Projeto acadêmico em Java desenvolvido para a A3 com foco em mobilidade urbana acessível.

## Descrição

O **Mob10** é uma solução inspirada em aplicativos de transporte, mas voltada para pessoas com mobilidade reduzida, como cadeirantes, idosos e usuários em recuperação médica. A proposta é conectar passageiros a motoristas com veículos compatíveis, oferecendo mais autonomia, inclusão e segurança.

## Estrutura do projeto

O código foi organizado conforme os requisitos do checkpoint 2:

- `src/model`: classes de domínio do sistema
- `src/service`: classes de serviço com listas `ArrayList`
- `src/view`: classe principal para execução e demonstração

## Funcionalidades já implementadas

- Estrutura de pacotes `model`, `service` e `view`
- Atributos privados em todas as classes
- Construtores para inicialização dos objetos
- Getters e setters
- Sobrescrita de `toString()`
- Serviços com armazenamento em `ArrayList`
- Herança entre `Usuario`, `Motorista` e `Passageiro`
- Herança entre `Veiculo`, `CarroComum`, `CarroAdaptado` e `Van`

## Classes do projeto

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

## Como executar

Com o Java configurado no ambiente:

```bash
javac -d bin src/model/*.java src/service/*.java src/view/*.java
java -cp bin view.Main
```

## Status do checkpoint 2

- Código parcial estruturado e compilável
- Pronto para ser publicado em um repositório GitHub público
- Base preparada para evolução do checkpoint 3
