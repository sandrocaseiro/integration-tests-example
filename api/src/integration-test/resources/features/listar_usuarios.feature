# language: pt
Funcionalidade: Cenários de teste para listagem de usuários
  Testes relacionados ao endpoint de listagem de usuários

  Cenário: Listar usuários sem autenticação
    Quando eu chamo '/v1/usuarios' usando GET
    Então eu devo receber uma resposta com código 403
    E a resposta contém o código de erro 403

  Cenário: Listar usuários
    Dado que estou autenticado
    Quando eu chamo '/v1/usuarios' usando GET
    Entao eu devo receber uma resposta com código 200
    E a resposta tem uma lista 'data.usuarios' com 2 itens
    E a resposta deve conter a propriedade 'data.usuarios[0].id' com o valor 1
    E a resposta deve conter a propriedade 'data.usuarios[0].nome' com o valor 'usuario1'
    E a resposta deve conter a propriedade 'data.usuarios[1].id' com o valor 2
    E a resposta deve conter a propriedade 'data.usuarios[1].nome' com o valor 'usuario2'
