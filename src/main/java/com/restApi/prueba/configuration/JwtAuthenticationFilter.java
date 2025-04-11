package com.restApi.prueba.configuration;

import com.restApi.prueba.CustomUserDetailsService;
import com.restApi.prueba.http_errors.UnauthorizedException;
import com.auth0.jwt.exceptions.JWTVerificationException; // Asegúrate de importar la correcta
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private CustomUserDetailsService detailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService detailsService) {
        this.jwtService = jwtService;
        this.detailsService = detailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (request.getServletPath().equals("/basic-auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);

        try {
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







/*
package com.restApi.prueba.configuration;

import com.restApi.prueba.CustomUserDetailsService;
import com.restApi.prueba.http_errors.UnauthorizedException;
//import io.jsonwebtoken.ExpiredJwtException; // Importa ExpiredJwtException
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private CustomUserDetailsService detailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService detailsService) {
        this.jwtService = jwtService;
        this.detailsService = detailsService;
        System.out.println("NconstructorN******************GGGG**********************NN");
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println("N11N******************GG//77////GG**********************NN");
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (request.getServletPath().equals("/basic-auth/login")) {
            System.out.println("N11N******************GGEXCLUSIÓNGG**********************NN");
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            System.out.println("N11N******************GGuGG**********************NN");
            return;
        }

        System.out.println("N11N******************GGGG**********************NN");
        jwt = authHeader.substring(7);

        try {
            userEmail = jwtService.extractUsername(jwt);
        } catch (JWTVerificationException e) { // Captura ExpiredJwtException
            System.out.println("N11N******************GGTOKEN EXPIRADOGG**********************NN");
            throw new UnauthorizedException("Token ha expirado");
        } catch (Exception e) {
            System.out.println("N11N******************GGTOKEN INVALIDOGG**********************NN");
            throw new UnauthorizedException("Token inválido");
        }


        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("N11N******************GGooGG**********************NN");
            UserDetails userDetails = this.detailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            System.out.println("N11N******************GGxGG**********************NN");
        }

        System.out.println("N11N******************GGfGG**********************NN");
        filterChain.doFilter(request, response);
    }
}

*/






/*


package com.restApi.prueba.configuration;
import com.restApi.prueba.CustomUserDetailsService;

import com.restApi.prueba.http_errors.UnauthorizedException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private  CustomUserDetailsService detailsService;

    public JwtAuthenticationFilter(JwtService jwtService,CustomUserDetailsService detailsService) {
        this.jwtService = jwtService;
        this.detailsService = detailsService;

        System.out.println("NconstructorN******************GGGG**********************NN");
    }
    //Este Filtro se Ejecuta Cuando Ocurre una Peticcion al Servidor // Funcion Transversal//


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        System.out.println("N11N******************GG//77////GG**********************NN");
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (request.getServletPath().equals("/basic-auth/login")) {
            System.out.println("N11N******************GGEXCLUSIÓNGG**********************NN");// ¡EXCLUSIÓN!
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            System.out.println("N11N******************GGuGG**********************NN");
            return;
        }
System.out.println("N11N******************GGGG**********************NN");
        jwt = authHeader.substring(7);
        userEmail = jwtService.extractUsername(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            System.out.println("N11N******************GGooGG**********************NN");
            UserDetails userDetails = this.detailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            System.out.println("N11N******************GGxGG**********************NN");
        }

        System.out.println("N11N******************GGfGG**********************NN");
        filterChain.doFilter(request, response);
    }


    /*@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
    System.out.println("+|+|+|**********************************MI PRIMER Filtro: JwtAuthenticationFilter()-->OncePerRequestFilter  FUNCCION_TRANSVERSAL***********************************************+|+|+");
        String token = jwtService.extraerTokenBearer(request.getHeader(HttpHeaders.AUTHORIZATION));

        if (token != null && jwtService.isTokenValid(token)) {
            Claims claims = jwtService.getClaims(token);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    claims.getSubject(), token, claims.gAuthorities());

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);

        }else {
            // Lanza la excepción si el token no es válido
            throw new UnauthorizedException("Token JWT no válido");
        }

        chain.doFilter(request, response);
    }
    */
//}