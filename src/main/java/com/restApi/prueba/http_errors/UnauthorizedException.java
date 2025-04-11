package com.restApi.prueba.http_errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED) // Esto es clave para el 401
public class UnauthorizedException extends RuntimeException {
    private static final String DESCRIPTION = "Unauthorized Exception - 401";

    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
