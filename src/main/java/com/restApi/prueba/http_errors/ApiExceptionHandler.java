package com.restApi.prueba.http_errors;

import com.restApi.prueba.configuration.ExceptionRaisingAuthenticationEntryPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    //No tiene el rol para acceder a ese Recurso
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDenied(AccessDeniedException ex) {
        System.out.println("*****************************AccessDeniedException**********************************************");
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.FORBIDDEN);
    }
/*
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestException ex) {
        System.out.println("*****************************BadRequestException**********************************************");
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
*/
    //Registro invalido correo ya existe, se lanza y captura con un archivo ya existente ConflictException http_errores
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(ConflictException ex) {
        System.out.println("*****************************ConflictException**********************************************");

        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    //Login invalido correo incorrecto
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        System.out.println("*****************************UsernameNotFoundException**********************************************");
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.UNAUTHORIZED.value()); // Or HttpStatus.UNAUTHORIZED
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED); // Or UNAUTHORIZED
    }

    // El recurso url no existe
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorMessage> handleNoResourceFoundException(NoResourceFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
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

