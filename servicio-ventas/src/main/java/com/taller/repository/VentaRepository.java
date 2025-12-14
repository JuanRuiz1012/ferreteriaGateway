// venta/repository/VentaRepository.java
package com.taller.repository;


import com.taller.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaRepository extends JpaRepository<Venta, Long> {
}