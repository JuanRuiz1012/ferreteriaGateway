package com.taller.dto;

import com.taller.entity.DetalleVenta;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class VentaResponseDto {
    private Long id;
    private Long idUsuario;
    private LocalDate fecha;
    private BigDecimal total;
    private List<DetalleVenta> detalles; // Usamos la entidad DetalleVenta para simplicidad
}