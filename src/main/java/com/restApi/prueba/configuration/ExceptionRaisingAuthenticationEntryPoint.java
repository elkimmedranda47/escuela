package com.restApi.prueba.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionRaisingAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public static class AuthenticationFailedException extends RuntimeException {

        public AuthenticationFailedException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        throw new AuthenticationFailedException("Authentication failed: " + authException.getMessage(), authException);
    }
}