package dev.sandrocaseiro.springbootitExample.controllers;

import dev.sandrocaseiro.springbootitExample.mappers.CepMapper;
import dev.sandrocaseiro.springbootitExample.models.DResponse;
import dev.sandrocaseiro.springbootitExample.models.api.ACep;
import dev.sandrocaseiro.springbootitExample.models.dto.cep.DPesquisaCepResp;
import dev.sandrocaseiro.springbootitExample.services.CepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
@RequiredArgsConstructor
@Tag(name = "Cep", description = "Operações com CEP")
public class CepController {
    private final CepService cepService;
    private final CepMapper cepMapper;

    @GetMapping("/v1/cep/{cep}")
    @Operation(summary = "Pesquisa de CEP", description = "Busca endereço por CEP", responses = {
        @ApiResponse(responseCode = "200", description = "OK", content = {@Content(schema = @Schema(implementation = DPesquisaCepResp.DResponseDPesquisaCepResp.class))}),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = {@Content(schema = @Schema(implementation = DResponse.class))}),
        @ApiResponse(responseCode = "500", description = "Erro no Servidor", content = {@Content(schema = @Schema(implementation = DResponse.class))})
    })
    public DPesquisaCepResp pesquisaCep(@Parameter(description = "CEP a ser pesquisado", in = ParameterIn.PATH, required = true, example = "01451001")
                                      @PathVariable String cep) {
        ACep aCep = cepService.buscarEndereco(cep);
        return cepMapper.toDPesquisaCepResp(aCep);
    }
}
