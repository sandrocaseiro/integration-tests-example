package dev.sandrocaseiro.springbootitExample.controllers;

import dev.sandrocaseiro.springbootitExample.models.DResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@RequiredArgsConstructor
@Tag(name = "Test", description = "Operações para execução de Testes")
public class TestController {
    private final Flyway flyway;

    @GetMapping("/v1/resetdb")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Reset de DB", description = "Reconstrói o banco de dados com a estrutura/dados iniciais", responses = {
        @ApiResponse(responseCode = "204", description = "Sem conteúdo", content = {@Content(schema = @Schema(implementation = DResponse.class))}),
        @ApiResponse(responseCode = "500", description = "Erro no Servidor", content = {@Content(schema = @Schema(implementation = DResponse.class))})
    })
    public void resetDb() {
        flyway.clean();
        flyway.migrate();
    }
}
