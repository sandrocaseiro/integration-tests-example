# integration-tests-example

Um projeto SPA de exemplo, implementando testes de integração em ambas camadas.
O projeto utiliza [Cucumber](https://cucumber.io/), [Spring Boot](https://spring.io/), [Rest Assured](http://rest-assured.io/), [WireMock](http://wiremock.org/), [React](https://reactjs.org/) e [Cypress](https://www.cypress.io/).

## Uso

### API

A API necessita de um banco de dados PostgreSQL para funcionar em seu profile default. Os testes integrados devem ser executados usando o profile `test`.
Todos os arquivos relacionados aos testes podem ser encontrados na pasta `src/integration-test`

Para executar os testes, execute os comandos:

```shell
mvn install
mvn install -Ptest
mvn integration-test -Ptest
```

### Front-End

O front-end espera que a API esteja sendo executada na porta padrão (`8080`).

Para executar os testes, execute os comandos:
```shell
npm install
```
