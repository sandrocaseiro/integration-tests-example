# integration-tests-example - Spring Boot API

## Dados de teste (profile test)

Os dados são recriados a cada execução do projeto, utilizando uma base H2. As chamadas à API externa são mockadas.

### Usuários
| id | nome | email |
|---|---|---|
| 1 | usuario1 | usuario1@mail.com |
| 2 | usuario2 | usuario2@mail.com |

### CEP's
| CEP | Status | Resposta |
|---|---|---|
| 01451001 | 200 | Cep encontrado |
| 99999999 | 200 | Cep não encontrado |
| Outros | 404 | NA |
