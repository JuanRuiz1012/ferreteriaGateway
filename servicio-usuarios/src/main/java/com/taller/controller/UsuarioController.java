package com.taller.controller;


import com.taller.dto.UsuarioRequestDto;
import com.taller.dto.UsuarioResponseDto;
import com.taller.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // 1. LISTADO (READ - All)
    // El controller devuelve el DTO de respuesta
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listarUsuarios() {
        List<UsuarioResponseDto> usuarios = usuarioService.findAllDto();
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    // 2. REGISTRO (CREATE)
    // El controller recibe el DTO de request
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> crearUsuario(@RequestBody UsuarioRequestDto usuarioDto) {
        UsuarioResponseDto nuevoUsuario = usuarioService.saveFromDto(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }


}