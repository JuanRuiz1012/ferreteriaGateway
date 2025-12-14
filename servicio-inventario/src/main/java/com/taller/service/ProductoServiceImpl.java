package com.taller.service;

import com.taller.entity.Producto;
import com.taller.repository.ProductoRepository;
import com.taller.ServicioInventarioApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }

    // Lógica para actualizar el stock (necesario para cuando se realiza una venta)
    @Override
    public Producto updateStock(Long id, Integer cantidad) {
        Optional<Producto> productoOpt = productoRepository.findById(id);
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            producto.setStock(producto.getStock() - cantidad);
            return productoRepository.save(producto);
        }
        return null; // Manejo de error si no existe el producto
    }
}