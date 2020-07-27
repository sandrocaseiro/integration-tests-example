package dev.sandrocaseiro.springbootitExample.mappers;

import dev.sandrocaseiro.springbootitExample.models.domain.EUsuario;
import dev.sandrocaseiro.springbootitExample.models.dto.user.DBuscarUsuarioPorIdResp;
import dev.sandrocaseiro.springbootitExample.models.dto.user.DCriarUsuarioReq;
import dev.sandrocaseiro.springbootitExample.models.dto.user.DCriarUsuarioResp;
import dev.sandrocaseiro.springbootitExample.models.dto.user.DListarTodosUsuariosResp;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UsuarioMapper {
    default DListarTodosUsuariosResp toDListarTodosUsuariosResp(List<EUsuario> usuarios) {
        return new DListarTodosUsuariosResp(usuarios.stream().map(this::toListarTodosUsuariosRespUsuario).collect(Collectors.toList()));
    }

    DListarTodosUsuariosResp.Usuario toListarTodosUsuariosRespUsuario(EUsuario usuario);

    EUsuario toEUsuario(DCriarUsuarioReq model);

    DCriarUsuarioResp toDCriarUsuarioResp(EUsuario model);

    DBuscarUsuarioPorIdResp toDBuscarUsuarioPorIdResp(EUsuario model);
}
