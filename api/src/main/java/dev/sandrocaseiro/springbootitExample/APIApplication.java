package dev.sandrocaseiro.springbootitExample;

import dev.sandrocaseiro.springbootitExample.configs.FeignConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableFeignClients(defaultConfiguration = FeignConfig.class)
public class APIApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(APIApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(APIApplication.class);
    }
}
