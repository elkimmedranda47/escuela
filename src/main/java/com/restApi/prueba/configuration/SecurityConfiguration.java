package com.restApi.prueba.configuration;

import com.restApi.prueba.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
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
                //.csrf(csrf -> csrf.disable())
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/basic-auth/login")) // Desactiva CSRF para /basic-auth/login
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                )

 /*               .httpBasic(httpBasic -> httpBasic
                        .authenticationEntryPoint(new ExceptionRaisingAuthenticationEntryPoint())
                )
*/
/*               .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(new ExceptionRaisingAuthenticationEntryPoint())
                )

 */
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/basic-auth/login", "/basic-auth/Acceso/ValidarToken", "/auth/registro").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthenticationFilter(jwtService,customUserDetailsService), UsernamePasswordAuthenticationFilter.class );



            return http.build();
        }



        /*
        * @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/basic-auth/login")) // Desactiva CSRF para /basic-auth/login
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception ->
                        exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/public/**", "/basic-auth/login", "/signup",
                                "/basic-auth/Acceso/ValidarToken", "/auth/registro").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthenticationFilter(jwtService, customUserDetailsService),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
        *
        *
        *
        * */



/*
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .csrf(csrf -> csrf.ignoringRequestMatchers("/basic-auth/login"))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .exceptionHandling(exception -> exception.authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
            .authorizeHttpRequests(auth -> auth

                    .requestMatchers(RolePermissions.PUBLIC_ENDPOINTS).permitAll()
                .requestMatchers(RolePermissions.ADMIN_ENDPOINTS).hasRole("ADMIN")
                .requestMatchers(RolePermissions.OPERATOR_ENDPOINTS).hasRole("OPERATOR")
                .requestMatchers(RolePermissions.CUSTOMER_ENDPOINTS).hasRole("CUSTOMER")

                .anyRequest().authenticated()
            )
            .addFilterBefore(new JwtAuthenticationFilter(jwtService, customUserDetailsService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
*/






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