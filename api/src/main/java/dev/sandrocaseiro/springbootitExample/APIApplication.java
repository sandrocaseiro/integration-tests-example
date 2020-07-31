package dev.sandrocaseiro.springbootitExample;

import dev.sandrocaseiro.springbootitExample.configs.FeignConfig;
import dev.sandrocaseiro.springbootitExample.repositories.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableFeignClients(defaultConfiguration = FeignConfig.class)
@EnableConfigurationProperties({CorsProperties.class})
public class APIApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(APIApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(APIApplication.class);
    }
}
