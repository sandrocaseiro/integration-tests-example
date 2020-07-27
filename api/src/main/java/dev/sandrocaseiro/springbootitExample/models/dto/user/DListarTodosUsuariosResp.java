package dev.sandrocaseiro.springbootitExample.models.dto.user;

import dev.sandrocaseiro.springbootitExample.models.DResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class DListarTodosUsuariosResp {
    @Schema(description = "Lista dos usuários")
    private List<Usuario> usuarios;

    @Data
    public static class Usuario {
        @Schema(description = "Id do usuário", example = "1")
        private int id;
        @Schema(description = "Nome do usuário", example = "Usuario 1")
        private String nome;
    }

    @Schema(name = "DResponse<DListarTodosUsuariosResp>", description = "Retorno da listagem de usuários")
    public static class DResponseDListarTodosUsuariosResp extends DResponse<DListarTodosUsuariosResp> {
        public DResponseDListarTodosUsuariosResp(List<Error> errors, DListarTodosUsuariosResp data) {
            super(errors, data);
        }
    }
}
