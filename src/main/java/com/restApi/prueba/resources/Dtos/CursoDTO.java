package com.restApi.prueba.resources.Dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CursoDTO {
    private Long idCurso;

    @NotBlank(message = "El nombre del curso es obligatorio")
    private String nombre;

    private String descripcion;

    @Min(value = 1, message = "Los créditos deben ser al menos 1")
    private int creditos;

    @NotNull(message = "El ID del profesor es obligatorio")
    private Long idProfesor;

    // Getters y setters...
}