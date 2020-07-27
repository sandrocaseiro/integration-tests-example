package dev.sandrocaseiro.springbootitExample.models.dto.user;

import dev.sandrocaseiro.springbootitExample.models.DResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class DCriarUsuarioResp {
    @Schema(description = "Id do usuário", example = "1")
    private int id;
    @Schema(description = "Nome do usuário", example = "user1")
    private String nome;

    @Schema(name = "DResponse<DCriarUsuarioResp>", description = "Resposta para usuário criado com sucesso")
    public static class DResponseDCriarUsuarioResp extends DResponse<DCriarUsuarioResp> {
        public DResponseDCriarUsuarioResp(List<Error> errors, DCriarUsuarioResp data) {
            super(errors, data);
        }
    }
}
