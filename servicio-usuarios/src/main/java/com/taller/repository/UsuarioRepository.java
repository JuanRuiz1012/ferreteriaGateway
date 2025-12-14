package com.taller.repository;


import com.taller.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Método de consulta personalizado para buscar por username
    Usuario findByUsername(String username);
}