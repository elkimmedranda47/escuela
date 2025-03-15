package com.restApi.prueba.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


    @Entity
    @Table(name = "matricula")

    public class Matricula {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_matricula")
        private Long idMatricula;

        @ManyToOne
        @JoinColumn(name = "id_estudiante", nullable = false)
        private Estudiante estudiante;

        @ManyToOne
        @JoinColumn(name = "id_curso", nullable = false)
        private Curso curso;

        @Column(name = "fecha_matricula", nullable = false)
        private LocalDate fechaMatricula;

        // Getters y Setters
    }


