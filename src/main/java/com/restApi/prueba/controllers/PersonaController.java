package com.restApi.prueba.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restApi.prueba.http_errors.BadRequestException;
import com.restApi.prueba.resources.Dtos.PersonaDTO;
import com.restApi.prueba.services.PersonaService;
import com.restApi.prueba.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@Secured({"ADMINISTRATIVO"})
@RequestMapping("/api/persona") // Corrected and added RequestMapping


public class PersonaController {

    private final PersonaService personaService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public PersonaController(PersonaService usuarioService) {
        this.personaService = usuarioService;
    }


    //ok
    @GetMapping
    public List<Persona> getAllUsuarios() {
        return personaService.getAllUsuario();
    }

   //ok
    @GetMapping("/{id}")
    public PersonaDTO getUsuarioById(@PathVariable Long id) {
        System.out.println("id del usairo: "+id);
        return personaService.getUsuarioById(id);
    }
    //http://localhost:8080/auth/registro url publica para registros de personas
    /*
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Persona createUsuario(@RequestBody Persona usuario) {
        return personaService.createUsuario(usuario);
    }
    */
    //ok
    @PutMapping("/{id}")
    public Persona updateUsuarios(@PathVariable Long id, @RequestBody PersonaDTO PersonaDTO) {
        System.out.println("id del Usuario: " + id);

        if (PersonaDTO == null || PersonaDTO.getNombre() == null || PersonaDTO.getNombre().isEmpty()) {
            throw new BadRequestException("El nombre del usuario no puede estar vacío.");
        }

        try {
            String json = objectMapper.writeValueAsString(PersonaDTO);
            System.out.println("Usuario actualizado (JSON): " + json);
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir el objeto a JSON: " + e.getMessage());
        }

        return personaService.updateUsuario(id, PersonaDTO);
    }
//ok
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        System.err.println("controller idUsuario: " + id);
        personaService.deleteUsuario(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content
    }


}