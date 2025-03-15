package com.restApi.prueba.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
@Getter
@Setter

@Entity
    @Table(name = "estudiantes")
    @PrimaryKeyJoinColumn(name = "id_persona")
    public class Estudiante extends Persona {
        @NotBlank(message = "Matrícula es obligatoria")
        @Column(name = "numero_matricula", unique = true)
        private String numeroMatricula;

        @NotBlank(message = "Grado es obligatorio")
        private String grado;

        // Getters y Setters
    }



