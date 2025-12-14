package com.taller.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "detalle_venta")
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK a la entidad Venta (Tabla D en el mismo servicio)
    @Column(name = "id_venta")
    private Long idVenta;

    // FK a la entidad Producto (Tabla A en otro servicio)
    @Column(name = "id_producto")
    private Long idProducto;

    private Integer cantidad;

    @Column(precision = 10, scale = 2)
    private Double precioUnitario; // Precio del producto al momento de la venta

    @Column(precision = 10, scale = 2)
    private Double subtotal;
}