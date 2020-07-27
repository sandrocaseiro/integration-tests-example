package dev.sandrocaseiro.springbootitExample.steps;

import io.cucumber.java8.Pt;
import io.restassured.response.Response;

import static io.restassured.RestAssured.withArgs;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsIterableContaining.hasItem;

public class ComumSteps extends BaseSteps implements Pt {
    public ComumSteps() {
        Before(this::setupRestAssured);

        After(this::rebuildDbData);

        Quando("eu utilizo o content-type {string}", this::requestContentType);

        Quando("eu uso o header {string} com o valor {string}", (String nomeHeader, String valorHeader) -> {
            state.getRequest()
                .header(nomeHeader, valorHeader);
        });

        Quando("eu chamo {string} usando GET", (String url) -> {
            Response response = state.getRequest()
                .when()
                .get(url);

            state.setResponse(response);
        });

        Quando("eu chamo {string} usando POST", (String url) -> {
            Response response = state.getRequest()
                .when()
                .post(url);

            state.setResponse(response);
        });

        Quando("eu uso o payload:", (String payload) -> {
            state.getRequest()
                .body(payload);

            state.setRequestPayload(payload);
        });

        Entao("eu devo receber uma resposta com código {int}", (Integer codigoStatus) -> {
            state.getResponse()
                .then()
                .statusCode(codigoStatus);
        });

        Entao("a resposta contém o código de erro {int}", (Integer codigo) -> {
            state.getResponse().then()
                .body("errors.code", hasItem(codigo));
        });

        Entao("a resposta contém um erro com código {int} contendo {string}", (Integer codigo, String termo) -> {
            state.getResponse().then()
                .rootPath("errors.findAll { it.code == %s && it.description.contains('%s') }", withArgs(codigo, termo))
                .body("size()", greaterThan(0));
        });

        Entao("a resposta contém {int} erro(s) com o código {int} contendo {string}", (Integer qtd, Integer codigo, String termo) -> {
            state.getResponse().then()
                .rootPath("errors.findAll { it.code == %s && it.description.contains('%s') }", withArgs(codigo, termo))
                .body("size()", is(qtd));
        });

        Entao("a resposta deve conter a propriedade {string} com o valor {string}", (String propriedade, String valor) -> {
            if (valor.equalsIgnoreCase("true") || valor.equalsIgnoreCase("false"))
                state.getResponse().then()
                    .body(propriedade, is(Boolean.parseBoolean(valor)));
            else
                state.getResponse().then()
                    .body(propriedade, is(valor));
        });

        Entao("a resposta deve conter a propriedade {string} com o valor {int}", (String propriedade, Integer valor) -> {
            state.getResponse().then()
                .body(propriedade, is(valor));
        });

        Entao("a resposta tem uma lista {string} com {int} itens/item", (String propriedade, Integer qtd) -> {
            state.getResponse().then()
                .rootPath(propriedade)
                .body("size()", is(qtd));
        });
    }

    public void requestContentType(String contentType) {
        state.getRequest()
            .contentType(contentType);
    }
}
