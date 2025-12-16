package com.taller.service;


import com.taller.dto.UsuarioRequestDto;
import com.taller.dto.UsuarioResponseDto;
import com.taller.entity.Usuario;
import com.taller.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }


    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<UsuarioResponseDto> findAllDto() {
        return usuarioRepository.findAll().stream().map(usuario -> {
            UsuarioResponseDto dto = new UsuarioResponseDto();
            dto.setId(usuario.getId());
            dto.setUsername(usuario.getUsername());
            dto.setNombre(usuario.getNombre());
            dto.setEmail(usuario.getEmail());
            dto.setRol(usuario.getRol());
            return dto;
        }).toList();
    }

    @Override
    public UsuarioResponseDto saveFromDto(UsuarioRequestDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setPassword(usuarioDto.getPassword());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setRol(usuarioDto.getRol());

        Usuario guardado = usuarioRepository.save(usuario);

        UsuarioResponseDto response = new UsuarioResponseDto();
        response.setId(guardado.getId());
        response.setUsername(guardado.getUsername());
        response.setNombre(guardado.getNombre());
        response.setEmail(guardado.getEmail());
        response.setRol(guardado.getRol());

        return response;
    }


    public Optional<Usuario> findByUsername(String username) {
        return Optional.ofNullable(usuarioRepository.findByUsername(username));
    }
}