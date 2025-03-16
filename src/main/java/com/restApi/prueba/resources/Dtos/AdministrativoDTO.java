package com.restApi.prueba.resources.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdministrativoDTO extends PersonaDTO {
    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;

    @NotBlank(message = "El departamento es obligatorio")
    private String departamento;

    // Getters y setters...
}