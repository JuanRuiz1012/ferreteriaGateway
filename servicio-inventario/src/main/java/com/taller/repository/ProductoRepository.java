package com.taller.repository;

import com.taller.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repositorio JPA
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}