package dev.sandrocaseiro.springbootitExample;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import dev.sandrocaseiro.springbootitExample.steps.ApiExternaSteps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class MockServer {
    public final WireMockServer mockServer;
    @Autowired
    public Environment env;

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

    @PostConstruct
    public void init() {
        if (!"true".equals(env.getProperty("isTest"))) {
            ApiExternaSteps.registerStubs();
        }
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
}
