package com.restApi.prueba.controllers;

import com.restApi.prueba.resources.Dtos.CursoDTO;
import com.restApi.prueba.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<CursoDTO>> getAllCursos() {
        List<CursoDTO> cursos = cursoService.getAll();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> getCursoById(@PathVariable Long id) {
        CursoDTO curso = cursoService.getById(id);
        return new ResponseEntity<>(curso, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> createCurso(@Valid @RequestBody CursoDTO cursoDTO) {
        CursoDTO createdCurso = cursoService.create(cursoDTO);
        return new ResponseEntity<>(createdCurso, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> updateCurso(@PathVariable Long id, @Valid @RequestBody CursoDTO cursoDTO) {
        CursoDTO updatedCurso = cursoService.update(id, cursoDTO);
        return new ResponseEntity<>(updatedCurso, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/profesor/{profesorId}")
    public ResponseEntity<List<CursoDTO>> getCursosByProfesorId(@PathVariable Long profesorId) {
        List<CursoDTO> cursos = cursoService.findByProfesorId(profesorId);
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }
}