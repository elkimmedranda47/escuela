/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//package com.restApi.prueba.configuration;


/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;*/
/**
 *
 * @author ekn47
 */
/*@Configuration
public class CorsConfig {
    @Bean
    public WebFluxConfigurer corsConfigurer() {
        return new WebFluxConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                
                
                registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200")  // Cambia según tu frontend
                    .allowedHeaders("Authorization", "Content-Type")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .exposedHeaders("Authorization");  // Permitir acceso a la cabecera Authorization
            
            System.out.print("llegamos al archivo CorsConfig");
            
            }
        };
    }
}
*/


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
/*

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:8080"); // Ajusta según tu frontend
        configuration.addAllowedMethod("*"); // Permite todos los métodos HTTP
        configuration.addAllowedHeader("*"); // Permite todos los headers
        configuration.setAllowCredentials(true); // Permitir credenciales

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

*/


//}

