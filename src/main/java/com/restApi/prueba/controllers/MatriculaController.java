package com.restApi.prueba.controllers;

import com.restApi.prueba.services.MatriculaService;
import com.restApi.prueba.resources.Dtos.MatriculaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matricula")
public class MatriculaController {

    @Autowired
    private MatriculaService matriculaService;

    @GetMapping
    public List<MatriculaDTO> getAllMatriculas() {
        return matriculaService.getAll();
    }

    @GetMapping("/{id}")
    public MatriculaDTO getMatriculaById(@PathVariable Long id) {
        return matriculaService.getById(id);
    }

    //Listar Matriculas de Estudiante
    @GetMapping("/estudiante/{estudianteId}")
    public List<MatriculaDTO> getMatriculasByEstudiante(@PathVariable Long estudianteId) {
        return matriculaService.findByEstudianteId(estudianteId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MatriculaDTO createMatricula(@RequestBody MatriculaDTO dto) {
        return matriculaService.create(dto);
    }

    @PutMapping("/{id}")
    public MatriculaDTO updateMatricula(@PathVariable Long id, @RequestBody MatriculaDTO dto) {
        return matriculaService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMatricula(@PathVariable Long id) {
        matriculaService.delete(id);
    }




}