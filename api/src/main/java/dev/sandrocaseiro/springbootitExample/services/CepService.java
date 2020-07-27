package dev.sandrocaseiro.springbootitExample.services;

import com.fasterxml.jackson.databind.JsonNode;
import dev.sandrocaseiro.springbootitExample.clients.CepClient;
import dev.sandrocaseiro.springbootitExample.exceptions.AppErrors;
import dev.sandrocaseiro.springbootitExample.exceptions.AppException;
import dev.sandrocaseiro.springbootitExample.models.api.ACep;
import dev.sandrocaseiro.springbootitExample.utils.JsonUtil;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CepService {
    private final CepClient cepClient;

    public ACep buscarEndereco(String cep) {
        Response resp = cepClient.buscarEnderecoPorCep(cep);
        if (HttpStatus.valueOf(resp.status()).isError())
            throw AppException.of(AppErrors.API_ERROR);

        JsonNode node = JsonUtil.deserializeTree(resp.body());
        if (node.has("erro"))
            throw AppException.of(AppErrors.RESOURCE_NOT_FOUND_ERROR);

        return JsonUtil.deserialize(node, ACep.class);
    }
}
