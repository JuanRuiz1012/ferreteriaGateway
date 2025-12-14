package com.taller.entity;

import jakarta.persistence.*;
import lombok.Data;

// Entidad principal (Tabla B)
@Entity
@Data
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String nombre;
    private String email;
    private String rol; // Ejemplo: ADMIN, EMPLEADO, CLIENTE
}