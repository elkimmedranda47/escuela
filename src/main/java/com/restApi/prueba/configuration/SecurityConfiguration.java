package com.restApi.prueba.configuration;

import com.restApi.prueba.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true) // Habilita @Secured y @RolesAllowed

public class SecurityConfiguration {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    public SecurityConfiguration(JwtService jwtService, CustomUserDetailsService customUserDetailsService) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(new JwtAuthenticationFilter(jwtService,customUserDetailsService), UsernamePasswordAuthenticationFilter.class )
            .exceptionHandling(exception ->
                    exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint())//Capturar cualquier peticion que venga sin token, cambiar error de spring 403 Forbidden a 401 UNAUTHORIZED
            )
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers( "/basic-auth/login","/auth/registro","/basic-auth/Acceso/ValidarToken").permitAll()

                    .anyRequest().authenticated());
        return http.build();
        }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}


/*
que esta mos diciendo en esta linea {.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))}

En la línea .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) dentro de la configuración de Spring Security,
estamos configurando la gestión de sesiones de nuestra aplicación para que sea sin estado (STATELESS).
Desglosemos cada parte para entenderlo mejor:
.sessionManagement(...): Este método de la configuración de Spring Security se utiliza para definir cómo se gestionarán las sesiones HTTP en nuestra aplicación. Recibe como argumento un Consumer<SessionManagementConfigurer<HttpSecurity>.SessionCreationPolicyConfig> que nos permite configurar la política de creación de sesiones.
session -> session.sessionCreationPolicy(...): Dentro del sessionManagement, utilizamos una expresión lambda (session -> ...) para acceder al objeto SessionCreationPolicyConfig. Este objeto nos proporciona métodos para configurar la política de creación de sesiones.
.sessionCreationPolicy(SessionCreationPolicy.STATELESS): Este es el método clave. Establece la política de creación de sesiones a SessionCreationPolicy.STATELESS.
¿Qué significa SessionCreationPolicy.STATELESS?

Cuando configuramos la política de creación de sesiones como STATELESS, le estamos
diciendo a Spring Security que no cree ni utilice sesiones HTTP para gestionar la autenticación y
la autorización de los usuarios.

Implicaciones de una aplicación sin estado:

Cada petición es independiente: Cada vez que un cliente realiza una petición al servidor,
debe proporcionar las credenciales necesarias (generalmente a través de un token, como un JWT, en los
encabezados de la petición). El servidor no recordará ninguna información sobre la sesión del cliente entre peticiones.
No se utilizan cookies de sesión: Las aplicaciones con estado tradicionalmente utilizan cookies de sesión para identificar
 a los usuarios autenticados en las peticiones posteriores. En una aplicación sin estado, no se generan ni se utilizan estas cookies para la gestión de sesiones.
Escalabilidad mejorada: Al no tener que mantener el estado de las sesiones en el servidor, la aplicación se vuelve
más fácil de escalar horizontalmente. Cada instancia del servidor puede manejar cualquier petición sin depender del estado almacenado en otra instancia.
Ideal para APIs REST: Las arquitecturas RESTful suelen preferir el enfoque sin estado, ya que cada petición debe
 contener toda la información necesaria para que el servidor la procese.
En resumen, la línea .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) en
 la configuración de Spring Security indica que nuestra aplicación será sin estado. Esto significa que no se crearán ni utilizarán
 sesiones HTTP, y la autenticación y autorización se gestionarán en cada petición, generalmente mediante el uso de tokens.
*/