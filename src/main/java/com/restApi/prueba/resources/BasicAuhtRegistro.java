/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restApi.prueba.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restApi.prueba.models.Persona;
import com.restApi.prueba.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicAuhtRegistro {

    private final PersonaService personaService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public BasicAuhtRegistro(PersonaService usuarioService) {
        this.personaService = usuarioService;
    }

    @PostMapping("/auth/registro")
    @ResponseStatus(HttpStatus.CREATED)
    public Persona createUsuario(@RequestBody Persona usuarioDTO) {

        System.out.println("llegamos ");
        try {
            String json = objectMapper.writeValueAsString(usuarioDTO);
            System.out.println("Usuario creado (JSON): " + json);
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir el objeto a JSON: " + e.getMessage());
        }

        return personaService.createUsuario(usuarioDTO);
    }
}
    
    

