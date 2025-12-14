package com.taller.dto;

import lombok.Data;

@Data
public class UsuarioRequestDto {
    private String username;
    private String password; // Se necesita para crear
    private String nombre;
    private String email;
    private String rol;
}