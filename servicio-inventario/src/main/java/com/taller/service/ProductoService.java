package com.taller.service;

import com.taller.entity.Producto;
import java.util.List;
import java.util.Optional;

// Parte del Patrón DTO + Services + Repository
public interface ProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
    Producto updateStock(Long id, Integer cantidad); // Nuevo método para Ventas
    void deleteById(Long id);
}