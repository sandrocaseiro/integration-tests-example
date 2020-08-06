Feature: Home e navegação
  Acessar a home do site e navegar pelo menu

  Scenario: Visualizar a página inicial
    Given que acesso ao site
    Then devo ver a mensagem da home
    And devo ver o menu
    And o menu deve conter 3 items

  Scenario Outline: Navegar para diferentes telas pelo menu
    Given que acesso ao site
    When eu clico no menu "<titulo>"
    Then devo ser redirecionado para "<url>"

    Examples:
      | titulo   | url       |
      | CEP      | /cep      |
      | Usuários | /usuarios |
