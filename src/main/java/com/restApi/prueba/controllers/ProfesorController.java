package com.restApi.prueba.controllers;

import com.restApi.prueba.resources.Dtos.ProfesorDTO;
import com.restApi.prueba.services.ProfesorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/profesor")
public class ProfesorController {

    @Autowired
    private ProfesorService service;

    @GetMapping
    public ResponseEntity<List<ProfesorDTO>> getAllProfesores() {
        List<ProfesorDTO> profesores = service.getAll();
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> getProfesorById(@PathVariable Long id) {
        ProfesorDTO profesor = service.getById(id);
        return new ResponseEntity<>(profesor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> createProfesor(@Valid @RequestBody ProfesorDTO profesorDTO) {
        ProfesorDTO createdProfesor = service.create(profesorDTO);
        return new ResponseEntity<>(createdProfesor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorDTO> updateProfesor(@PathVariable Long id, @Valid @RequestBody ProfesorDTO profesorDTO) {
        ProfesorDTO updatedProfesor = service.update(id, profesorDTO);
        return new ResponseEntity<>(updatedProfesor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}