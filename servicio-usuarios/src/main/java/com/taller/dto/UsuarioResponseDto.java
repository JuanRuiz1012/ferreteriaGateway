package com.taller.dto;

import lombok.Data;

@Data
public class UsuarioResponseDto {
    private Long id;
    private String username;
    private String nombre;
    private String email;
    private String rol;
}