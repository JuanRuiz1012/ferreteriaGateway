package com.taller.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK a la entidad Usuario (Tabla B en otro servicio)
    @Column(name = "id_usuario")
    private Long idUsuario;

    private LocalDate fecha = LocalDate.now();

    @Column(precision = 10, scale = 2)
    private Double total = 0.0;
}