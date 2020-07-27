package dev.sandrocaseiro.springbootitExample.controllers;

import dev.sandrocaseiro.springbootitExample.exceptions.BindValidationException;
import dev.sandrocaseiro.springbootitExample.mappers.UsuarioMapper;
import dev.sandrocaseiro.springbootitExample.models.DResponse;
import dev.sandrocaseiro.springbootitExample.models.domain.EUsuario;
import dev.sandrocaseiro.springbootitExample.models.dto.user.*;
import dev.sandrocaseiro.springbootitExample.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestScope
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Operações para usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @GetMapping("/v1/usuarios")
    @Operation(summary = "Listar usuários", description = "Listar todos os usuários", responses = {
        @ApiResponse(responseCode = "200", description = "OK", content = {@Content(schema = @Schema(implementation = DListarTodosUsuariosResp.DResponseDListarTodosUsuariosResp.class))}),
        @ApiResponse(responseCode = "500", description = "Erro no servidor", content = {@Content(schema = @Schema(implementation = DResponse.class))})
    })
    public DListarTodosUsuariosResp listarTodos() {
        List<EUsuario> usuarios = usuarioService.listarTodos();

        return usuarioMapper.toDListarTodosUsuariosResp(usuarios);
    }

    @PostMapping("/v1/usuarios")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create User", description = "Criar um novo usuário", responses = {
        @ApiResponse(responseCode = "201", description = "Criado", content = {@Content(schema = @Schema(implementation = DCriarUsuarioResp.DResponseDCriarUsuarioResp.class))}),
        @ApiResponse(responseCode = "400", description = "Requisição inválida", content = {@Content(schema = @Schema(implementation = DResponse.class))}),
        @ApiResponse(responseCode = "422", description = "Entidade não processada", content = {@Content(schema = @Schema(implementation = DResponse.class))}),
        @ApiResponse(responseCode = "500", description = "Erro no servidor", content = {@Content(schema = @Schema(implementation = DResponse.class))})
    })
    public DCriarUsuarioResp criarUsuario(@RequestBody @Valid DCriarUsuarioReq dto,
                                          Errors bindingErrors) {
        if (bindingErrors.hasErrors())
            throw new BindValidationException(bindingErrors);

        EUsuario usuario = usuarioMapper.toEUsuario(dto);
        usuario = usuarioService.criar(usuario);

        return usuarioMapper.toDCriarUsuarioResp(usuario);
    }

    @GetMapping("/v1/usuarios/{id}")
    @Operation(summary = "Buscar usuário", description = "Buscar usuário por Id", responses = {
        @ApiResponse(responseCode = "200", description = "OK", content = {@Content(schema = @Schema(implementation = DBuscarUsuarioPorIdResp.DResponseDBuscarUsuarioPorIdResp.class))}),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = {@Content(schema = @Schema(implementation = DResponse.class))}),
        @ApiResponse(responseCode = "500", description = "Erro no servidor", content = {@Content(schema = @Schema(implementation = DResponse.class))})
    })
    public DBuscarUsuarioPorIdResp findUser(@Parameter(description = "Id do usuário", in = ParameterIn.PATH, required = true, example = "1")
                                            @PathVariable int id) {
        EUsuario usuario = usuarioService.buscarPorId(id);

        return usuarioMapper.toDBuscarUsuarioPorIdResp(usuario);
    }
}
