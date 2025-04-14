package com.restApi.prueba.http_errors;

import com.restApi.prueba.configuration.ExceptionRaisingAuthenticationEntryPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ExceptionRaisingAuthenticationEntryPoint.AuthenticationFailedException.class)
    public ResponseEntity<Object> handleAuthenticationFailedException(ExceptionRaisingAuthenticationEntryPoint.AuthenticationFailedException ex) {
        // Personaliza la respuesta de error aquí
        System.out.println("*****************************fin**********************************************");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                String.format("{\"error\": \"%s\", \"message\": \"%s\"}",
                        HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage()));
    }


/*
    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<Map<String, String>> handleCredentialsExpired(CredentialsExpiredException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Token inválido o expirado");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
*/
    // Puedes añadir más handlers para otras excepciones si deseas
}
