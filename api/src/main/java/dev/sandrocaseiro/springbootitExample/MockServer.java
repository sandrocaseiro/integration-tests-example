package dev.sandrocaseiro.springbootitExample;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

@Component
@Profile("test")
public class MockServer {
    public final WireMockServer mockServer;

    public MockServer() {
        mockServer = new WireMockServer(
            WireMockConfiguration.options()
                .port(8089)
                .usingFilesUnderClasspath("mocks")
        );
        mockServer.start();
        WireMock.configureFor(mockServer.port());
    }

    public void reset() {
        mockServer.resetMappings();
    }

    @PreDestroy
    public void dispose() {
        mockServer.shutdownServer();
        while (mockServer.isRunning()) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException e) {

            }
        }
    }

    public void registerStubs() {
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
