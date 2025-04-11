package com.restApi.prueba.http_errors;

public class AccessDeniedException extends RuntimeException {
    private static final String DESCRIPTION = "Bad Request Exception";

    public AccessDeniedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}

