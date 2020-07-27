package dev.sandrocaseiro.springbootitExample.models.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Schema(description = "Modelo para criação de usuário")
public class DCriarUsuarioReq {
    @NotEmpty
    @Size(max = 50)
    @Schema(description = "Nome do usuário", required = true, example = "user1")
    private String nome;

    @NotEmpty
    @Email
    @Size(max = 150)
    @Schema(description = "E-mail do usuário", required = true, example = "user1@mail.com")
    private String email;
}
