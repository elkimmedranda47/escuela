package com.restApi.prueba.http_errors;

import com.restApi.prueba.configuration.ExceptionRaisingAuthenticationEntryPoint;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    //No tiene el rol para acceder a ese Recurso
    // Maneja la excepción AccessDeniedException, que ocurre cuando un usuario autenticado intenta acceder a un recurso para el que no tiene los permisos necesarios.
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> handleAccessDenied(AccessDeniedException ex) {
        //System.out.println("*****************************AccessDeniedException**********************************************");
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
    // Maneja la excepción ConflictException, que se lanza cuando hay un conflicto con el estado actual del recurso (ej., intento de crear un registro con un correo electrónico ya existente).
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(ConflictException ex) {
     //   System.out.println("*****************************ConflictException**********************************************");

        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }

    //Login invalido correo incorrecto
    // Maneja la excepción UsernameNotFoundException, que ocurre cuando no se encuentra un usuario con el nombre de usuario (generalmente el correo electrónico) proporcionado durante la autenticación.
    @ExceptionHandler({UsernameNotFoundException.class
            /*,CredentialsExpiredException.class*/
    })
    public ResponseEntity<ErrorMessage> handleUsernameNotFoundException(UsernameNotFoundException ex) {
      //  System.out.println("*****************************UsernameNotFoundException**********************************************");
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.UNAUTHORIZED.value()); // Or HttpStatus.UNAUTHORIZED
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED); // Or UNAUTHORIZED
    }

    // El recurso url no existe
    // Maneja la excepción NoResourceFoundException, que ocurre cuando Spring MVC no puede encontrar un recurso estático que coincida con la URL solicitada.
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorMessage> handleNoResourceFoundException(NoResourceFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
    // Maneja tu excepción personalizada NotFoundException, que se lanza cuando un recurso específico (por ID, etc.) no se encuentra.
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex) {
        //System.out.println("*****************************NotFoundException**********************************************");
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    // Maneja la excepción DataIntegrityViolationException, que ocurre cuando una operación en la base de datos viola una restricción de integridad (ej., intento de eliminar un registro con claves foráneas existentes).
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
       // System.out.println("*****************************DataIntegrityViolationException**********************************************");
        ErrorMessage errorMessage = new ErrorMessage(ex, HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
    }
    // estructura de un body incompleta de un post peticion
    // Maneja la excepción MethodArgumentNotValidException, que ocurre cuando la validación de los argumentos de un método del controlador (anotados con @Valid) falla.

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    // Maneja la excepción ConstraintViolationException, que ocurre cuando la validación a nivel de entidad (a menudo antes de la persistencia en la base de datos) falla debido a las restricciones definidas con las anotaciones de Jakarta Bean Validation.
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(propertyPath, message);
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // Maneja la excepción HttpRequestMethodNotSupportedException, que ocurre cuando se realiza una solicitud HTTP con un método (GET, POST, PUT, DELETE, etc.) que no está soportado para la URL solicitada.
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity<ErrorMessage> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ErrorMessage errorMessage = new ErrorMessage("Método no permitido", "El método HTTP utilizado no está soportado para esta ruta.", HttpStatus.METHOD_NOT_ALLOWED.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.METHOD_NOT_ALLOWED);
    }



    // Maneja cualquier otra excepción que no haya sido capturada por los otros métodos @ExceptionHandler. Actúa como un "catch-all" para errores inesperados en el servidor.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGeneralException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage(new Exception("Error interno del servidor: Ocurrió un error inesperado."), HttpStatus.INTERNAL_SERVER_ERROR.value());
        // Es importante loggear la excepción aquí para depuración
        ex.printStackTrace(); // Considera usar un logger más robusto
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

