package com.restApi.prueba.http_errors;

import com.restApi.prueba.configuration.ExceptionRaisingAuthenticationEntryPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

//@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDenied(AccessDeniedException ex) {
        System.out.println("*****************************fin2**********************************************");
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CredentialsExpiredException.class)
    public ResponseEntity<ErrorMessage> handleCredentialsExpired(CredentialsExpiredException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({
            UnauthorizedException.class,
            AuthenticationException.class
    })
    public ResponseEntity<ErrorMessage> handleUnauthorized(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(org.springframework.security.authentication.InsufficientAuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleInsufficientAuthentication(org.springframework.security.authentication.InsufficientAuthenticationException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExceptionRaisingAuthenticationEntryPoint.AuthenticationFailedException.class)
    public ResponseEntity<Object> handleAuthenticationFailedException(ExceptionRaisingAuthenticationEntryPoint.AuthenticationFailedException ex) {
        // Personaliza la respuesta de error aquí
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                String.format("{\"error\": \"%s\", \"message\": \"%s\"}",
                        HttpStatus.UNAUTHORIZED.getReasonPhrase(), ex.getMessage()));
    }



    // Captura cualquier otra excepción no manejada
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Exception("Error interno del servidor: Ocurrió un error inesperado."), HttpStatus.INTERNAL_SERVER_ERROR.value());
        // Es importante loggear la excepción aquí para depuración
        ex.printStackTrace(); // Considera usar un logger más robusto
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}









/*
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
*/