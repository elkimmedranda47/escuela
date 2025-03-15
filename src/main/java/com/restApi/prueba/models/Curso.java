package com.restApi.prueba.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long idCurso;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @Min(value = 1, message = "Créditos debe ser mayor a 0")
    private Integer creditos;

    @ManyToOne
    @JoinColumn(name = "id_profesor", referencedColumnName = "id_persona")
    private Profesor profesor;

    // Getters y Setters
}