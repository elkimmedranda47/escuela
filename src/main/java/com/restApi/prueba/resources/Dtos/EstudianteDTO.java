package com.restApi.prueba.resources.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteDTO extends PersonaDTO {
    @NotBlank(message = "El número de matrícula es obligatorio")
    private String numeroMatricula;

    @NotBlank(message = "El grado es obligatorio")
    private String grado;

    // Getters y setters...
}
