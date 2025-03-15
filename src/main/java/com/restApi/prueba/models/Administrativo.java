package com.restApi.prueba.models;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "administrativos")
@PrimaryKeyJoinColumn(name = "id_persona")
public class Administrativo extends Persona {
    @NotBlank(message = "Cargo es obligatorio")
    private String cargo;

    @NotBlank(message = "Departamento es obligatorio")
    private String departamento;

    // Getters y Setters
}