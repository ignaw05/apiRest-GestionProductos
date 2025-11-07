package com.utn.productos.config; // Asegúrate de que el paquete sea correcto

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/api/**") // Aplica a todas las rutas bajo /api/
                .allowedOrigins(
                        "http://localhost:3000", // Permite tu frontend de React
                        "https://ecommercefront-rho.vercel.app", // Permite tu frontend de Angular
                        "http://localhost:5173") // Permite tu frontend de Vite
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*") // Permite todos los headers
                .allowCredentials(true); // Permite el envío de cookies
    }
}