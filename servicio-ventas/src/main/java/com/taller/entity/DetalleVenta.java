package com.taller.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_venta")
    private Long idVenta;

    @Column(name = "id_producto")
    private Long idProducto;

    private Integer cantidad;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioUnitario; // Cambiado a BigDecimal

    @Column(precision = 10, scale = 2)
    private BigDecimal subtotal; // Cambiado a BigDecimal
}