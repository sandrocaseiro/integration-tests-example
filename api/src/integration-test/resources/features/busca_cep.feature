# language: pt
Funcionalidade: Cenários de testes para busca de cep
  Testes relacionados ao endpoint de busca de cep

  Cenário: Buscar CEP sem autenticação
    Quando eu chamo '/v1/cep/01451001' usando GET
    Então eu devo receber uma resposta com código 403
    E a resposta contém o código de erro 403

  Cenário: Buscar CEP com API não funcional
    Dado que estou autenticado
    E que a API de CEP não está funcionando
    Quando eu chamo '/v1/cep/01451001' usando GET
    Então eu devo receber uma resposta com código 500
    E a resposta contém o código de erro 500

  Cenário: Buscar CEP inválido
    Dado que estou autenticado
    E que a API de CEP está funcionando
    Quando eu chamo '/v1/cep/1234' usando GET
    Então eu devo receber uma resposta com código 500
    E a resposta contém o código de erro 901

  Cenário: Buscar CEP não existente
    Dado que estou autenticado
    E que a API de CEP está funcionando
    Quando eu chamo '/v1/cep/99999999' usando GET
    Então eu devo receber uma resposta com código 404
    E a resposta contém o código de erro 902

  Cenário: Buscar CEP existente
    Dado que estou autenticado
    E que a API de CEP está funcionando
    Quando eu chamo '/v1/cep/01451001' usando GET
    Então eu devo receber uma resposta com código 200
    E a resposta deve conter a propriedade 'data.cep' com o valor '01451-001'
    E a resposta deve conter a propriedade 'data.logradouro' com o valor 'Avenida Brigadeiro Faria Lima'
    E a resposta deve conter a propriedade 'data.bairro' com o valor 'Jardim Paulistano'
    E a resposta deve conter a propriedade 'data.cidade' com o valor 'São Paulo'
    E a resposta deve conter a propriedade 'data.uf' com o valor 'SP'
