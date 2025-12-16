package com.taller.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal; // Añadir este import

@Entity
@Data
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(precision = 10, scale = 2)
    private BigDecimal precio; // Cambiado de Double a BigDecimal

    @Column(nullable = false)
    private Integer stock;
}