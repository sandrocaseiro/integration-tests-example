Feature: Novo Usuário
  Testar funcionalidades da tela de novo usuário

  Scenario: Acessar tela de novo usuário iniciando pela lista
    Given que estou na tela de lista de usuários
    When eu clico no botão novo usuário
    Then devo ser redirecionado para "/usuarios/novo"
    And o formulário de cadastro de usuário deve ser mostrado
    And o botão salvar usuário deve estar visível

  Scenario: Acessar tela de novo usuário pela url
    When eu acesso a tela de novo usuário
    And o formulário de cadastro de usuário deve ser mostrado
    And o botão salvar usuário deve estar visível

  Scenario: Campos do formulário devem ser validados no onFocus
    Given que estou na tela de novo usuário
    When eu coloco o focus no campo "nome"
    And tiro o focus do campo "nome"
    And eu coloco o focus no campo "email"
    And tiro o focus do campo "email"
    Then o campo "nome" estará com o status de erro
    And o campo "nome" terá uma mensagem de erro contendo "obrigatório"
    And o campo "email" estará com o status de erro
    And o campo "email" terá uma mensagem de erro contendo "obrigatório"

  Scenario: Gravar usuário sem preencher formulário
    Given que estou na tela de novo usuário
    When eu clico no botão salvar usuário
    Then o campo "nome" estará com o status de erro
    And o campo "nome" terá uma mensagem de erro contendo "obrigatório"
    And o campo "email" estará com o status de erro
    And o campo "email" terá uma mensagem de erro contendo "obrigatório"

  Scenario Outline: Cadastrar usuário com e-mail inválido
    Given que estou na tela de novo usuário
    When eu digito o valor "usuario3" no campo "nome"
    And eu digito o valor "<email>" no campo "email"
    And eu clico no botão salvar usuário
    Then o campo "email" estará com o status de erro
    And o campo "email" terá uma mensagem de erro contendo "inválido"

    Examples:
      | email            |
      | usuario3mail.com |
      | usuario3@        |
      | @mail.com        |

  Scenario: Cadastrar usuário com sucesso
    Given que estou na tela de novo usuário
    When eu digito o valor "usuario4" no campo "nome"
    And eu digito o valor "usuario4@mail.com" no campo "email"
    And eu clico no botão salvar usuário
    Then o formulário de cadastro de usuário é submetido
    And eu sou redirecionado para a tela de lista de usuários
    And o alerta de sucesso de usuário criado será mostrado
