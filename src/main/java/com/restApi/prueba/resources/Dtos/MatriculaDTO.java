package com.restApi.prueba.resources.Dtos;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
 public class MatriculaDTO {
    private Long idMatricula;

    @NotNull(message = "El ID del estudiante es obligatorio")
    private Long idEstudiante;

    @NotNull(message = "El ID del curso es obligatorio")
    private Long idCurso;

    @NotNull(message = "La fecha de inscripción es obligatoria")
    private LocalDate fechaMatricula;

    // Getters y setters...
}