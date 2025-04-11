package com.restApi.prueba.http_errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<Map<String, String>> handleCredentialsExpired(CredentialsExpiredException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Token inválido o expirado");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    // Puedes añadir más handlers para otras excepciones si deseas
}
