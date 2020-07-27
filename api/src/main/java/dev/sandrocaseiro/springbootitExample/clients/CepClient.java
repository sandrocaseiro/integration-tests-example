package dev.sandrocaseiro.springbootitExample.clients;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cep", url = "${endpoints.cep.base-url}")
public interface CepClient {
    @GetMapping(value = "/{cep}/json/")
    Response buscarEnderecoPorCep(@PathVariable("cep") String cep);
}
