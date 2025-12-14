package com.taller.service;


import com.taller.dto.UsuarioRequestDto;
import com.taller.dto.UsuarioResponseDto;
import com.taller.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    // Métodos CRUD originales (trabajan con entidades internamente)
    Optional<Usuario> findById(Long id);
    void deleteById(Long id);

    // Métodos que utilizan DTO para cumplir el patrón arquitectónico
    List<UsuarioResponseDto> findAllDto();
    UsuarioResponseDto saveFromDto(UsuarioRequestDto usuarioDto);
}