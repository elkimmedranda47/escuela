package com.restApi.prueba.controllers;

import com.restApi.prueba.resources.Dtos.EstudianteDTO;
import com.restApi.prueba.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

//@RestController
@RestController
@RequestMapping("/api/estudiante")
//@Secured({"ADMINISTRATIVO", "ESTUDIANTE","PROFESOR"})

 class EstudianteController {

    @Autowired
    private EstudianteService service;

    @GetMapping
    public List<EstudianteDTO> getAll() {
        System.out.println("llegamos al recurso controller");
        return service.getAll();
    }

    @GetMapping("/{id}")
    public EstudianteDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public EstudianteDTO create(@Valid @RequestBody EstudianteDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public EstudianteDTO update(@PathVariable Long id, @Valid @RequestBody EstudianteDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Función especial: buscar por número de matrícula
    @GetMapping("/matricula/{numero}")
    public EstudianteDTO getByNumeroMatricula(@PathVariable String numero) {
        return service.findByNumeroMatricula(numero);
    }
}