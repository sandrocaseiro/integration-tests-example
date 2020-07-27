package dev.sandrocaseiro.springbootitExample.models.dto.cep;

import dev.sandrocaseiro.springbootitExample.models.DResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class DPesquisaCepResp {
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;

    @Schema(name = "DResponse<DPesquisaCepResp>", description = "Resposta da busca de cep")
    public static class DResponseDPesquisaCepResp extends DResponse<DPesquisaCepResp> {
        public DResponseDPesquisaCepResp(List<Error> errors, DPesquisaCepResp data) {
            super(errors, data);
        }
    }
}
