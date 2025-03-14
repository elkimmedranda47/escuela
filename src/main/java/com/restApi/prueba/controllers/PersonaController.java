package com.restApi.prueba.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restApi.prueba.http_errors.BadRequestException;
import com.restApi.prueba.resources.Dtos.UsuarioDTO;
import com.restApi.prueba.services.PersonaService;
import com.restApi.prueba.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/api/persona") // Corrected and added RequestMapping
public class PersonaController {

    private final PersonaService personaService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public PersonaController(PersonaService usuarioService) {
        this.personaService = usuarioService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Persona createUsuario(@RequestBody Persona usuario) {
        return personaService.createUsuario(usuario);
    }

    @GetMapping
    public List<Persona> getAllUsuarios() {
        return personaService.getAllUsuario();
    }

    @GetMapping("/{id}")
    public UsuarioDTO getUsuarioById(@PathVariable Long id) {
        return personaService.getUsuarioById(id);
    }

    @PutMapping("/{id}")
    public Persona updateUsuarios(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        System.out.println("id del Usuario: " + id);

        if (usuarioDTO == null || usuarioDTO.getNombre() == null || usuarioDTO.getNombre().isEmpty()) {
            throw new BadRequestException("El nombre del usuario no puede estar vacío.");
        }

        try {
            String json = objectMapper.writeValueAsString(usuarioDTO);
            System.out.println("Usuario actualizado (JSON): " + json);
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir el objeto a JSON: " + e.getMessage());
        }

        return personaService.updateUsuario(id, usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        System.err.println("controller idUsuario: " + id);
        personaService.deleteUsuario(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }
}