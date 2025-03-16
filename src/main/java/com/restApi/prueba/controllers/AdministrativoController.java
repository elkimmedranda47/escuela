package com.restApi.prueba.controllers;

import com.restApi.prueba.resources.Dtos.AdministrativoDTO;
import com.restApi.prueba.services.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/administrativo")
public class AdministrativoController {

    @Autowired
    private AdministrativoService service;

    @GetMapping
    public ResponseEntity<List<AdministrativoDTO>> getAllAdministrativos() {
        List<AdministrativoDTO> administrativos = service.getAll();
        return new ResponseEntity<>(administrativos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministrativoDTO> getAdministrativoById(@PathVariable Long id) {
        AdministrativoDTO administrativo = service.getById(id);
        return new ResponseEntity<>(administrativo, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AdministrativoDTO> createAdministrativo( @RequestBody AdministrativoDTO administrativoDTO) {
        AdministrativoDTO createdAdministrativo = service.create(administrativoDTO);
        return new ResponseEntity<>(createdAdministrativo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministrativoDTO> updateAdministrativo(@PathVariable Long id, @Valid @RequestBody AdministrativoDTO administrativoDTO) {
        AdministrativoDTO updatedAdministrativo = service.update(id, administrativoDTO);
        return new ResponseEntity<>(updatedAdministrativo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrativo(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}