package com.taller.dto;

import lombok.Data;

// Mínimo DTO para el patrón DTO + Services + Repository
@Data
public class ProductoDto {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
}