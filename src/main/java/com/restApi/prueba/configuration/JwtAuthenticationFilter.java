package com.restApi.prueba.configuration;

import com.restApi.prueba.CustomUserDetailsService;
import com.restApi.prueba.http_errors.UnauthorizedException;
import com.auth0.jwt.exceptions.JWTVerificationException; // Asegúrate de importar la correcta
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


//@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService detailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService detailsService) {
        this.jwtService = jwtService;
        this.detailsService = detailsService;
    }

    @Override
    protected void doFilterInternal(
            //HttpServletRequest: objeto que representa la petición HTTP que un cliente.
            @NonNull HttpServletRequest request,

            @NonNull  HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

/*        if (request.getServletPath().equals("/basic-auth/login")) {
            //PASAR AL SIGUIENTE FILTRO
            filterChain.doFilter(request, response);
            return;
        }
*/
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("LA PETICION NO TIENE Authorization O NO TIENE Bearer");
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        try {
            //aqui se vlaida todas las peticiones todos lo objetos HttpServletRequest que vengan de un cliente detro de jwtService.extractUsername()
            userEmail = jwtService.extractUsername(jwt);
        } catch (JWTVerificationException e) {
            throw new UnauthorizedException("Token inválido"); // Mensaje genérico para simplificar
        }


        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.detailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt)) { // Valida el token *después* de extraer el usuario
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}






