package com.taller.service;

import com.taller.dto.ItemVentaDto;
import com.taller.dto.VentaRequestDto;
import com.taller.dto.VentaResponseDto;
import com.taller.entity.DetalleVenta;
import com.taller.entity.Venta;
import com.taller.repository.DetalleVentaRepository;
import com.taller.repository.VentaRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Inyecta la URL del microservicio de Inventario desde application.properties
    @Value("${microservice.inventario.url}")
    private String inventarioServiceUrl;

    @Override
    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> findById(Long id) {
        return ventaRepository.findById(id);
    }

    // El método más importante: crear la venta y descontar el stock
    @Override
    @Transactional // Asegura que si falla el stock, la venta se revierte
    public VentaResponseDto crearVenta(VentaRequestDto ventaDto) {

        Venta venta = new Venta();
        venta.setIdUsuario(ventaDto.getIdUsuario());
        Venta ventaGuardada = ventaRepository.save(venta); // Guarda el encabezado de la venta

        BigDecimal totalVenta = BigDecimal.ZERO;

        // 1. Procesar cada ítem de la venta
        for (ItemVentaDto item : ventaDto.getItems()) {

            // 2. Comunicación Microservicio: Obtener el precio y descontar stock en el servicio de Inventario (Puerto 8083)
            // Se asume que el endpoint /productos/{id}/stock/{cantidad} devuelve el producto actualizado

            ProductoDto producto = webClientBuilder.build().put()
                    .uri(inventarioServiceUrl + "/" + item.getIdProducto() + "/stock/" + item.getCantidad())
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(),
                            response -> Mono.error(new RuntimeException("Error: Producto no encontrado o stock insuficiente")))
                    .bodyToMono(ProductoDto.class)
                    .block(); // Bloquea temporalmente para obtener el resultado (simplificación)

            if (producto == null) {
                // En un sistema real se lanzaría una excepción que revierte la transacción (@Transactional)
                throw new RuntimeException("Error al actualizar el stock del producto: " + item.getIdProducto());
            }

            // 3. Calcular y guardar el detalle
            DetalleVenta detalle = new DetalleVenta();
            detalle.setIdVenta(ventaGuardada.getId());
            detalle.setIdProducto(item.getIdProducto());
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio()); // Usamos el precio del producto (BigDecimal)

            // Calcular subtotal: precio * cantidad
            BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(item.getCantidad()));
            detalle.setSubtotal(subtotal);

            detalleVentaRepository.save(detalle);
            totalVenta = totalVenta.add(subtotal);
        }

        // 4. Actualizar el total en el encabezado de la venta
        ventaGuardada.setTotal(totalVenta);
        ventaRepository.save(ventaGuardada);

        // 5. Construir la respuesta DTO
        VentaResponseDto response = new VentaResponseDto();
        response.setId(ventaGuardada.getId());
        response.setFecha(ventaGuardada.getFecha());
        response.setIdUsuario(ventaGuardada.getIdUsuario());
        response.setTotal(ventaGuardada.getTotal());
        response.setDetalles(detalleVentaRepository.findByIdVenta(ventaGuardada.getId()));

        return response;
    }

    // DTO de Producto usado internamente para deserializar la respuesta del Servicio de Inventario
    @Data
    public static class ProductoDto {
        private Long id;
        private BigDecimal precio; // Cambiado a BigDecimal
        private Integer stock;
    }
}