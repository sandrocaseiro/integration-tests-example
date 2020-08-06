Feature: Visualizar Usuário
  Testar funcionalidades da tela visulização de usuários

  Scenario: Visualizar usuário iniciando pela lista
    Given que estou na tela de lista de usuários
    When eu clico no botão para visualizar o usuário 1
    Then devo ser redirecionado para "/usuarios/1"
    And o mostrador de progresso será mostrado
    And os dados do usuário será mostrado
    And o campo "id" do usuário terá o valor "1"
    And o campo "nome" do usuário terá o valor "usuario1"
    And o botão salvar usuário não deve estar visível

  Scenario: Visualizar usuário acessando pela URL
    When eu acesso a tela de visualizar o usuário 1
    Then o mostrador de progresso será mostrado
    And os dados do usuário será mostrado
    And o campo "id" do usuário terá o valor "1"
    And o campo "nome" do usuário terá o valor "usuario1"
    And o botão salvar usuário não deve estar visível

    Scenario: Visualizar usuário não existente
    When eu acesso a tela de visualizar o usuário 99
    Then o mostrador de progresso será mostrado
    And o alerta de erro será mostrado
