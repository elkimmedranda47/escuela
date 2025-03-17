package com.restApi.prueba.repositorys;

import com.restApi.prueba.models.Curso;
import com.restApi.prueba.models.Estudiante;
import com.restApi.prueba.models.Matricula;
import com.restApi.prueba.resources.Dtos.MatriculaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    boolean existsByEstudianteAndCurso(Estudiante estudiante, Curso curso);
   // List<Matricula> findByEstudiante_IdPersona(Long idEstudiante);

}
