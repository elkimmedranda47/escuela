package com.restApi.prueba.resources.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfesorDTO extends PersonaDTO {
    @NotBlank(message = "La especialidad es obligatoria")
    private String especialidad;

    @PastOrPresent(message = "La fecha de contratación debe ser hoy o en el pasado")
    private LocalDate fechaContratacion;

    // Getters y setters...
}