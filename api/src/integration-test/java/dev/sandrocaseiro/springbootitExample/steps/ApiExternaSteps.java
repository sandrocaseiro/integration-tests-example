package dev.sandrocaseiro.springbootitExample.steps;

import io.cucumber.java8.Pt;
import org.springframework.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class ApiExternaSteps extends BaseSteps implements Pt {
    public ApiExternaSteps() {
        Before(() -> mockServer.reset());

        Dado("(que )a API de CEP não está funcionando", this::stubNotWorking);

        Dado("que a API de CEP está funcionando", ApiExternaSteps::registerStubs);
    }

    public void stubNotWorking() {
        stubFor(
          any(urlPathMatching("/cep/.*"))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .withFixedDelay(5000)
            )
        );
    }

    public static void registerStubs() {
        stubFor(
            get(urlPathMatching("/cep/01451001/.*"))
                .atPriority(1)
                .willReturn(
                    aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withBodyFile("/cep.json")
                )
        );


        stubFor(
            get(urlPathMatching("/cep/99999999/.*"))
                .atPriority(1)
                .willReturn(
                    aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withBodyFile("/cep-not-found.json")
                )
        );

        stubFor(
            any(urlPathMatching("/cep/.*"))
                .atPriority(99)
                .willReturn(
                    aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())
                )
        );
    }
}
