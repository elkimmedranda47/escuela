package com.restApi.prueba.http_errors;

import lombok.Getter;
import lombok.ToString;
import org.springframework.dao.DataIntegrityViolationException;

@Getter
@ToString
public class ErrorMessage {

    private final String error;
    private final String message;
    private final Integer code;

    ErrorMessage(Exception exception, Integer code) {
        this.error = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.code = code;
    }

    public ErrorMessage(String error, String message, Integer code) {
        this.error = error;
        this.message = message;
        this.code = code;
    }
    // se creean para conflictos con tablas que viole una regla
    //Permite crear un ErrorMessage con un mensaje de error completamente personalizado.
    public ErrorMessage(String error, Integer code) {
        this.error = error;
        this.message = null;
        this.code = code;
    }

    // Nuevo constructor específico para DataIntegrityViolationException
    // permite crear un ErrorMessage proporcionando solo el tipo de error y el código de estado, sin un mensaje detallado
    public ErrorMessage(DataIntegrityViolationException ex, Integer code) {
        this.error = "Error de integridad de datos";
        this.message = "No se puede realizar la operación debido a relaciones existentes.";
        this.code = code;
    }

}
