package dev.sandrocaseiro.springbootitExample.configs;

import dev.sandrocaseiro.springbootitExample.MockServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MockServerConfig {
    @Autowired(required = false)
    private MockServer mockServer;

    @PostConstruct
    public void init() {
        if (mockServer == null)
            return;

        mockServer.registerStubs();
    }
}
