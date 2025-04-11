/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.restApi.prueba.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.restApi.prueba.models.Persona;
import com.restApi.prueba.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class BasicAuhtRegistro {

    private final PersonaService personaService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public BasicAuhtRegistro(PersonaService usuarioService) {
        this.personaService = usuarioService;
        objectMapper.registerModule(new JavaTimeModule());
    }


    @PostMapping("/auth/registro")
    @ResponseStatus(HttpStatus.CREATED)
  /*  public Persona createUsuario(@RequestBody Persona usuarioDTO) {

        System.out.println("llegamos ");
        try {
            String json = objectMapper.writeValueAsString(usuarioDTO);
            System.out.println("Usuario creado (JSON): " + json);
        } catch (JsonProcessingException e) {
            System.err.println("Error al convertir el objeto a JSON: " + e.getMessage());
        }

        // Verificar si el email ya está en uso
        if (personaService.existsByEmail(usuarioDTO.getEmail())) {
           // throw new RuntimeException("El email ya está en uso");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email ya está en uso");

        }else {

            System.out.println("No Puede entrar aqui**********************");
            return personaService.createUsuario(usuarioDTO);
        }
    }

*/

    public Persona createUsuario(@RequestBody Persona usuarioDTO) {
        System.out.println("Intentando registrar usuario con email: " + usuarioDTO.getEmail());

        boolean existe = personaService.existsByEmail(usuarioDTO.getEmail());
        System.out.println("¿El email ya existe en la BD? " + existe);

        if (existe) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email ya está en uso");
        }

        return personaService.createUsuario(usuarioDTO);
    }

}
    
    

