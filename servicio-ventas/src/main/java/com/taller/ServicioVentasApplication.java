package com.taller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ServicioVentasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioVentasApplication.class, args);
    }

    // Bean para inyectar WebClient, que se usará para la comunicación con otros microservicios
    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}