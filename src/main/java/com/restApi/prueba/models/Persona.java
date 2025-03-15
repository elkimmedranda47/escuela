package com.restApi.prueba.models;



import jakarta.persistence.*;
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

// src/main/java/com/testApi/prueba/models/Persona.java
@Entity
@Table(name = "personas")

// el Json de Persona que se devuelve des de el repository  tendra los datos de cada
//HERENCIA DE TABLAS ENTITY AFECTA EL JSON QUE SE DEVUELVE
// subtipo (Estudiante, Profesor, Administrativo) tiene su propia tabla con campos específicos
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long idPersona;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "Apellido es obligatorio")
    private String apellido;

    @Past(message = "Fecha de nacimiento no válida")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "Email es obligatorio")
    @Email(message = "Formato de email inválido")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Teléfono no válido")
    private String telefono;

    @NotBlank(message = "Password es obligatorio")
    private String password;

    @Enumerated(EnumType.STRING)
    private TipoPersona tipo;

    // Getters y Setters
}
/*
public enum TipoPersona {
    ESTUDIANTE, PROFESOR, ADMINISTRATIVO
}
*/