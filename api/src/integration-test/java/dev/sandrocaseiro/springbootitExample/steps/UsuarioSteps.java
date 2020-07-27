package dev.sandrocaseiro.springbootitExample.steps;

import com.fasterxml.jackson.databind.JsonNode;
import dev.sandrocaseiro.springbootitExample.models.domain.EUsuario;
import dev.sandrocaseiro.springbootitExample.repositories.UsuarioRepository;
import io.cucumber.java8.Pt;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class UsuarioSteps extends BaseSteps implements Pt {
    @Autowired
    public UsuarioRepository usuarioRepository;

    public UsuarioSteps() {
        Entao("a resposta deve conter dos dados do usuário criado", () -> {
            JsonNode reqUser = state.getRequestPayload();

            state.getResponse().then()
                .body("data.nome", equalTo(reqUser.get("nome").asText()))
            ;
        });

        Entao("o usuário deve existir no banco de dados", () -> {
            int id = state.getResponse().getBody().jsonPath().getInt("data.id");
            EUsuario usuario = usuarioRepository.findById(id).orElse(null);
            JsonNode payload = state.getRequestPayload();

            assertThat(usuario).isNotNull();
            assertThat(payload.get("nome").asText()).isEqualTo(usuario.getNome());
            assertThat(payload.get("email").asText()).isEqualTo(usuario.getEmail());
        });
    }
}
