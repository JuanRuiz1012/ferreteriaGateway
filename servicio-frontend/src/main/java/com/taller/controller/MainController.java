package com.taller.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${api.gateway.url}")
    private String gatewayUrl;

    @GetMapping("/")
    public String index(Model model) {
        WebClient client = webClientBuilder.baseUrl(gatewayUrl).build();


        Mono<List<Producto>> productosMono = client.get()
                .uri("/api/inventario/productos")
                .retrieve()
                .bodyToFlux(Producto.class)
                .collectList();


        Mono<List<Usuario>> usuariosMono = client.get()
                .uri("/api/usuarios/usuarios")
                .retrieve()
                .bodyToFlux(Usuario.class)
                .collectList();


        model.addAttribute("productos", productosMono.block());
        model.addAttribute("usuarios", usuariosMono.block());

        return "index";
    }


    @Data
    public static class Producto {
        private Long id;
        private String nombre;
        private Double precio;
        private Integer stock;
    }

    @Data
    public static class Usuario {
        private Long id;
        private String username;
        private String rol;
    }
}