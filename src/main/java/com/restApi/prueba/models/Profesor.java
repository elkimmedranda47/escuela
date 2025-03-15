package com.restApi.prueba.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
    @Entity
    @Table(name = "profesores")
    @PrimaryKeyJoinColumn(name = "id_persona")
    public class Profesor extends Persona {
        @NotBlank(message = "Especialidad es obligatoria")
        private String especialidad;

        @PastOrPresent(message = "Fecha de contratación no válida")
        @Column(name = "fecha_contratacion")
        private LocalDate fechaContratacion;

        // Getters y Setters
    }


