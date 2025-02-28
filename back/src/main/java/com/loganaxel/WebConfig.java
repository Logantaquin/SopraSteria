package com.loganaxel;  // Remplace ce package par le tien, si nécessaire

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Applique les règles CORS à toutes les routes
                .allowedOrigins("http://localhost:4200") // Ajoute l'URL de ton frontend Angular
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Définir les méthodes HTTP autorisées
                .allowedHeaders("*") // Autoriser tous les en-têtes
                .allowCredentials(true); // Si tu veux permettre l'envoi de cookies, tokens, etc.
    }
}

