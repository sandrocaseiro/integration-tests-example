package dev.sandrocaseiro.springbootitExample.models.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ACep {
    private String cep;
    private String logradouro;
    private String bairro;
    @JsonProperty("localidade")
    private String cidade;
    private String uf;
}
