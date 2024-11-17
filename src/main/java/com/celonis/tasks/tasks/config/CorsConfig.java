package com.celonis.tasks.tasks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Allow credentials like cookies

        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization")); // Allow these headers
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allow these HTTP methods
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Allow frontend origin

        // Optional: Log applied CORS configuration for debugging
        System.out.println("CORS Config: Allowed Origins - " + config.getAllowedOrigins());
        System.out.println("CORS Config: Allowed Methods - " + config.getAllowedMethods());
        System.out.println("CORS Config: Allowed Headers - " + config.getAllowedHeaders());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Apply to all endpoints
        return new CorsFilter(source);
    }
}