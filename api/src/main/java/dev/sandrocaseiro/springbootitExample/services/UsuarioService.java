package dev.sandrocaseiro.springbootitExample.services;

import dev.sandrocaseiro.springbootitExample.exceptions.AppErrors;
import dev.sandrocaseiro.springbootitExample.exceptions.AppException;
import dev.sandrocaseiro.springbootitExample.models.domain.EUsuario;
import dev.sandrocaseiro.springbootitExample.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public EUsuario criar(EUsuario user) {
        if (usuarioRepository.existeUsuarioComEmail(user.getEmail()))
            throw AppException.of(AppErrors.EMAIL_ALREADY_EXISTS);

        return usuarioRepository.save(user);
    }

    public EUsuario buscarPorId(int id) {
        return usuarioRepository.findById(id).orElseThrow(() -> AppException.of(AppErrors.RESOURCE_NOT_FOUND_ERROR));
    }

    public List<EUsuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}
