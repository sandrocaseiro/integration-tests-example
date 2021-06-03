# integration-tests-example

Um projeto SPA de exemplo, implementando testes de integração em ambas camadas.
O projeto utiliza [Cucumber](https://cucumber.io/), [Spring Boot](https://spring.io/), [Rest Assured](http://rest-assured.io/), [WireMock](http://wiremock.org/), [React](https://reactjs.org/) e [Cypress](https://www.cypress.io/).

## Uso

### API

A API necessita de um banco de dados PostgreSQL para funcionar em seu profile default. Caso queira, também é possível executar a API no modo mockado, pelo profile `test`.

Todos os arquivos relacionados aos testes podem ser encontrados na pasta `src/integration-test`

Para executar os testes, execute o comando:

```bash
mvn integration-test
```

Ou, caso queira executar a API com os dados de teste e API externa mockadas, execute o comando:

```bash
mvn spring-boot:run -Ptest
```

Ou caso queira debugá-la:
```bash
mvn spring-boot:run -Dspring-boot.run.fork=false -Ptest
```

### Dados de teste

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
| Outros | 500 | NA |

---
### Front-End

O front-end espera que a API esteja sendo executada na porta padrão (`8080`).

Para executar os testes, primeiro inicialize a aplicação com:
```bash
npm install
npm start
```

E então execute os testes com:
```bash
npm run test:it
```
