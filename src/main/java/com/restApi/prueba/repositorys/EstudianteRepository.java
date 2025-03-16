package com.restApi.prueba.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restApi.prueba.models.Estudiante;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    boolean existsByNumeroMatricula(String numeroMatricula);
}
