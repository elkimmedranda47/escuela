package com.restApi.prueba.resources.Dtos;

import com.restApi.prueba.models.TipoPersona;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {
    private Long idPersona;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Apellido es obligatorio")
    private String apellido;

    @Past(message = "Fecha de nacimiento debe ser pasada")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Formato de email inválido")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Teléfono no válido")
    private String telefono;

    @NotBlank(message = "Password es obligatorio")
    private String password;

    private TipoPersona tipo; // ESTUDIANTE, PROFESOR, ADMINISTRATIVO

    // Getters y Setters
}