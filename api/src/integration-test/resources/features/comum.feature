# language: pt
Funcionalidade: Cenários de teste comuns
  Testes relacionados a funcionalidades comuns da API

  Cenário: Endpoint não existente
    Dado que estou autenticado
    Quando eu chamo '/v1/teste' usando GET
    Então eu devo receber uma resposta com código 404
    E a resposta contém o código de erro 404

  Cenário: Método HTTP Inválido
    Dado que estou autenticado
    Quando eu chamo '/v1/cep/01451001' usando POST
    Então eu devo receber uma resposta com código 405
    E a resposta contém o código de erro 405

  Cenário: Media type não suportado
    Dado que estou autenticado
    Quando eu utilizo o content-type 'image/gif'
    E eu chamo '/v1/usuarios' usando POST
    Então eu devo receber uma resposta com código 415
    E a resposta contém o código de erro 415

  Cenário: Media type não aceito
    Dado que estou autenticado
    Quando eu uso o header 'Accept' com o valor 'image/gif'
    E eu chamo '/v1/usuarios/1' usando GET
    Então eu devo receber uma resposta com código 406
    E a resposta contém o código de erro 406

  Cenário: Idioma padrão deve ser português
    Dado que estou autenticado
    Quando eu chamo '/v1/usuarios/1' usando GET
    Então a resposta contém um erro com código 200 contendo 'Sucesso'

  Esquema do Cenário: As respostas devem obedecer o idioma enviado no header Language
    Dado que estou autenticado
    Quando eu uso o header 'Accept-Language' com o valor '<idioma>'
    E eu chamo '/v1/usuarios/1' usando GET
    Então a resposta contém um erro com código 200 contendo '<resultado>'

    Exemplos:
      | idioma | resultado |
      | en-US  | Success   |
      | pt-BR  | Sucesso   |

  Cenário: Idioma inválido deve retornar o padrão português
    Dado que estou autenticado
    Quando eu uso o header 'Language' com o valor 'es-ES'
    E eu chamo '/v1/usuarios/1' usando GET
    Então a resposta contém um erro com código 200 contendo 'Sucesso'
