package com.restApi.prueba.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restApi.prueba.http_errors.NotFoundException;
import com.restApi.prueba.models.Persona;
import com.restApi.prueba.repositorys.PersonaRepository;
import com.restApi.prueba.resources.Dtos.PersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private PersonaRepository personaRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Persona createUsuario(Persona usuario) {

        //creamos el usuario encriptando su contraseña en db.
        //usuario.setContraseña(passwordEncoder.encode(usuario.getContrasena()));
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return personaRepository.save(usuario);
    }

    public List<Persona> getAllUsuario() {
        return personaRepository.findAll();
    }

    public PersonaDTO getUsuarioById(Long id) {
        Optional<Persona> optionalUsuario = personaRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            throw new NotFoundException("Usuario no encontrado con ID: " + id);
        }
        Persona persona = optionalUsuario.get();

        PersonaDTO PersonaDTO = new PersonaDTO();
       // PersonaDTO.setId(usuario.getId());
        PersonaDTO.setIdPersona(persona.getIdPersona());
        PersonaDTO.setNombre(persona.getNombre());
        PersonaDTO.setApellido(persona.getApellido());
        PersonaDTO.setFechaNacimiento(persona.getFechaNacimiento());
        PersonaDTO.setEmail(persona.getEmail());
        PersonaDTO.setTelefono(persona.getTelefono());
        PersonaDTO.setTipo(persona.getTipo());
        return PersonaDTO;
    }

    public Persona updateUsuario(Long id, PersonaDTO personaDTO) {
        Optional<Persona> optionalUsuario = personaRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            throw new NotFoundException("Usuario no encontrado con ID: " + id);
        }

        Persona existingUsuario = optionalUsuario.get();

        existingUsuario.setNombre(personaDTO.getNombre());
        existingUsuario.setApellido(personaDTO.getApellido());
        existingUsuario.setFechaNacimiento(personaDTO.getFechaNacimiento());
        existingUsuario.setEmail(personaDTO.getEmail());
        existingUsuario.setPassword(personaDTO.getPassword());
        existingUsuario.setTelefono(personaDTO.getTelefono());
        existingUsuario.setTipo(personaDTO.getTipo());

        try {
            String json = objectMapper.writeValueAsString(personaDTO);
            System.out.println("Usuario actualizado (JSON): " + json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return personaRepository.save(existingUsuario);
    }

    public void deleteUsuario(Long id) {
        Optional<Persona> optionalUsuario = personaRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            throw new NotFoundException("Usuario no encontrado con ID: " + id);
        }
        personaRepository.deleteById(id);
    }

    public boolean validarCredenciales(String correo, String contrasena) {
        Optional<Persona> optionalUsuario = personaRepository.findByEmail(correo);
        if (optionalUsuario.isEmpty()) {
            throw new NotFoundException("Usuario no encontrado con correo: " + correo);
        }
        Persona usuario = optionalUsuario.get();
        return passwordEncoder.matches(contrasena, usuario.getPassword());
    }
}