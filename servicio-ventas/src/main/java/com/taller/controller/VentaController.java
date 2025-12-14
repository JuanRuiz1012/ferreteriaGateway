package com.taller.controller;


import com.taller.dto.VentaRequestDto;
import com.taller.dto.VentaResponseDto;
import com.taller.entity.Venta;
import com.taller.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    // 1. LISTADO (READ - All)
    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        List<Venta> ventas = ventaService.findAll();
        if(ventas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }

    // 2. CREAR VENTA (CREATE)
    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody VentaRequestDto ventaDto) {
        try {
            VentaResponseDto nuevaVenta = ventaService.crearVenta(ventaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
        } catch (RuntimeException e) {
            // Captura errores como 'stock insuficiente' o 'producto no encontrado'
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}