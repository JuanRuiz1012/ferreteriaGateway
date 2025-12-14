package com.taller.service;

import com.taller.dto.VentaRequestDto;
import com.taller.dto.VentaResponseDto;
import com.taller.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface VentaService {
    List<Venta> findAll();
    Optional<Venta> findById(Long id);
    VentaResponseDto crearVenta(VentaRequestDto ventaDto); // Método principal
}