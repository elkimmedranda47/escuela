package com.restApi.prueba.http_errors;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMessage handleAccessDenied(AccessDeniedException ex) {


        return new ErrorMessage(ex, HttpStatus.UNAUTHORIZED.value());
    }



    @ExceptionHandler(CredentialsExpiredException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMessage handleCredentialsExpired(CredentialsExpiredException ex) {

        return new ErrorMessage(ex, HttpStatus.UNAUTHORIZED.value());
    }



    @ExceptionHandler({
            UnauthorizedException.class,
            AuthenticationException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorMessage handleUnauthorized(Exception ex) {

        return new ErrorMessage(ex, HttpStatus.UNAUTHORIZED.value());
    }

}
