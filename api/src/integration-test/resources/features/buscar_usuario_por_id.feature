# language: pt
Funcionalidade: Cenários de teste para busca de usuário
  Testes relacionados ao endpoint de busca de usuário por id

  Cenário: Buscar usuário sem autenticação
    Quando eu chamo '/v1/usuarios/1' usando GET
    Então eu devo receber uma resposta com código 403
    E a resposta contém o código de erro 403

  Cenário: Buscar usuário com id inválido
    Dado que estou autenticado
    Quando eu chamo '/v1/usuarios/1xpto' usando GET
    Então eu devo receber uma resposta com código 400
    E a resposta contém o código de erro 400

  Cenário: Buscar usuário com id não existente
    Dado que estou autenticado
    Quando eu chamo '/v1/usuarios/99' usando GET
    Então eu devo receber uma resposta com código 404
    E a resposta contém o código de erro 902

  Esquema do Cenário: : Buscar usuário com id existente
    Dado que estou autenticado
    Quando eu chamo '/v1/usuarios/<id>' usando GET
    Então eu devo receber uma resposta com código 200
    E a resposta deve conter a propriedade 'data.id' com o valor <id>
    E a resposta deve conter a propriedade 'data.nome' com o valor '<nome>'

    Exemplos:
      | id | nome     |
      | 1  | usuario1 |
      | 2  | usuario2 |
