package dev.sandrocaseiro.springbootitExample.models.dto.user;

import dev.sandrocaseiro.springbootitExample.models.DResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class DBuscarUsuarioPorIdResp {
    @Schema(description = "Id do usuário", example = "1")
    private int id;
    @Schema(description = "Nome do usuário", example = "user1")
    private String nome;

    @Schema(name = "DResponse<DBuscarUsuarioPorIdResp>", description = "Resposta para busca de usuário")
    public static class DResponseDBuscarUsuarioPorIdResp extends DResponse<DBuscarUsuarioPorIdResp> {
        public DResponseDBuscarUsuarioPorIdResp(List<Error> errors, DBuscarUsuarioPorIdResp data) {
            super(errors, data);
        }
    }
}
