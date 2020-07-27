package dev.sandrocaseiro.springbootitExample.configs;

import dev.sandrocaseiro.springbootitExample.exceptions.AppErrors;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Configuration
public class SwaggerConfig {
    private static final String SECURITY_SCHEME = "AUTH";

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
            .info(apiInfo())
            .components(addComponents())
            .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME))
            ;
    }

    @Bean
    public OpenApiCustomiser apiCustomizer() {
        return openApi -> {
            openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> {
                    operation.addParametersItem(
                        new HeaderParameter()
                            .name(HttpHeaders.ACCEPT_LANGUAGE)
                            .required(false)
                            .schema(
                                new StringSchema()
                                    ._default("pt-BR")
                                    .addEnumItem("en-US")
                                    .addEnumItem("pt-BR")
                            )
                    );
                });

            Schema errorSchema = openApi.getComponents().getSchemas().get("Error");
            if (errorSchema != null) {
                Schema codeSchema = (Schema) errorSchema.getProperties().get("code");
                StringBuilder builder = new StringBuilder(codeSchema.getDescription())
                    .append(System.lineSeparator()).append(System.lineSeparator())
                    .append(System.lineSeparator()).append(System.lineSeparator());
                for (AppErrors error : AppErrors.values()) {
                    codeSchema.addEnumItemObject(error.getCode());
                    builder
                        .append("* ")
                        .append(error.getCode())
                        .append(" - ")
                        .append(error.toString())
                        .append(System.lineSeparator()).append(System.lineSeparator());
                }
                codeSchema.description(builder.toString());
            }
        };
    }

    private Components addComponents() {
        return new Components().addSecuritySchemes(SECURITY_SCHEME,
            new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name(AUTHORIZATION)
        );
    }

    private Info apiInfo() {
        return new Info()
            .title("Spring Boot Integration Test Example")
            .description("Example project showing how to execute integration tests with Spring Boot")
            .version("1.0.0");
    }
}
