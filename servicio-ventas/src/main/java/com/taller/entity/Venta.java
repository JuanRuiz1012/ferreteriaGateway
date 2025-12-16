package com.taller.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    private LocalDate fecha = LocalDate.now();

    @Column(precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO; // Cambiado de Double a BigDecimal
}