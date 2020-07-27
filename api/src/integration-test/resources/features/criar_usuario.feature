# language: pt
Funcionalidade: Cenários de testes para criação de usuário
  Testes relacionados ao endpoint de criação de usuário

  Cenário: Criar usuário sem autenticação
    Quando eu uso o payload:
      """
      {
        "nome": "usuario3",
        "email": "usuario3@mail.com"
      }
      """
    E eu chamo '/v1/usuarios' usando POST
    Então eu devo receber uma resposta com código 403
    E a resposta contém o código de erro 403

  Cenário: Criar usuário com valores vazios
    Dado que estou autenticado
    Quando eu uso o payload:
      """
      {
        "nome": "",
        "email": ""
      }
      """
    E eu chamo '/v1/usuarios' usando POST
    Então eu devo receber uma resposta com código 422
    E a resposta contém 1 erro com o código 900 contendo 'nome'
    E a resposta contém 1 erro com o código 900 contendo 'email'

  Esquema do Cenário: Criar usuário com e-mails inválidos
    Dado que estou autenticado
    Quando eu uso o payload:
      """
      {
        "nome": "usuario3",
        "email": "<email>"
      }
      """
    E eu chamo '/v1/usuarios' usando POST
    Então eu devo receber uma resposta com código 422
    E a resposta contém 1 erro com o código 900 contendo 'email'

    Exemplos:
      | email            |
      | usuario3mail.com |
      | usuario3@        |
      | usuario3@.com    |

  Cenário: Criar usuário com e-mail já existente
    Dado que estou autenticado
    Quando eu uso o payload:
      """
      {
        "nome": "usuario3",
        "email": "usuario2@mail.com"
      }
      """
    E eu chamo '/v1/usuarios' usando POST
    Então eu devo receber uma resposta com código 400
    E a resposta contém o código de erro 903

  Cenário: Criar usuário com sucesso
    Dado que estou autenticado
    Quando eu uso o payload:
      """
      {
        "nome": "usuario3",
        "email": "usuario3@mail.com"
      }
      """
    E eu chamo '/v1/usuarios' usando POST
    Então eu devo receber uma resposta com código 201
    E a resposta deve conter dos dados do usuário criado
    E o usuário deve existir no banco de dados
