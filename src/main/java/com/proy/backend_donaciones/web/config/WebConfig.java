package com.proy.backend_donaciones.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // ATENCIÓN: Esta configuración es permisiva para desarrollo.
        // En producción, deberías restringir 'allowedOrigins' a la URL de tu frontend.
        registry.addMapping("/**") // Aplica a todos los endpoints
                .allowedOrigins("http://localhost:3000") // Orígenes permitidos (React, Angular, etc.)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(true);
    }
}