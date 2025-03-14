package com.restApi.prueba.configuration;

import com.restApi.prueba.http_errors.UnauthorizedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.List;

public class JwtAuthenticationManager implements AuthenticationManager {

    private final JwtService jwtService;

    public JwtAuthenticationManager(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getCredentials().toString();

        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("Las credenciales no pueden ser nulas o vacías");
        }

        if (!jwtService.isTokenValid(token)) {
            throw new UnauthorizedException("Token JWT inválido o expirado");
        }

        GrantedAuthority authority = new SimpleGrantedAuthority(jwtService.obtenerRol(token));
        return new UsernamePasswordAuthenticationToken(jwtService.obtenerMovil(token), token, List.of(authority));
    }
}
