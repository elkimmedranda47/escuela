package com.restApi.prueba.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        // Personaliza el mensaje de error según necesites
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("error", "No autorizado");
        errorDetails.put("mensaje", authException.getMessage());
        errorDetails.put("status", HttpStatus.UNAUTHORIZED.value());

        response.getOutputStream().println(objectMapper.writeValueAsString(errorDetails));
    }
}