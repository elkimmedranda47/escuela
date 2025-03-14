package com.restApi.prueba.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.restApi.prueba.http_errors.NotFoundException;
import com.restApi.prueba.models.Persona;
import com.restApi.prueba.repositorys.PersonaRepository;
import com.restApi.prueba.resources.Dtos.UsuarioDTO;
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
        usuario.setContraseña(passwordEncoder.encode(usuario.getContrasena()));
        return personaRepository.save(usuario);
    }

    public List<Persona> getAllUsuario() {
        return personaRepository.findAll();
    }

    public UsuarioDTO getUsuarioById(Long id) {
        Optional<Persona> optionalUsuario = personaRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            throw new NotFoundException("Usuario no encontrado con ID: " + id);
        }
        Persona usuario = optionalUsuario.get();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setCorreo(usuario.getCorreo());
        usuarioDTO.setComunidad(usuario.getComunidad());
        return usuarioDTO;
    }

    public Persona updateUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional<Persona> optionalUsuario = personaRepository.findById(id);
        if (optionalUsuario.isEmpty()) {
            throw new NotFoundException("Usuario no encontrado con ID: " + id);
        }
        Persona existingUsuario = optionalUsuario.get();
        existingUsuario.setNombre(usuarioDTO.getNombre());
        existingUsuario.setCorreo(usuarioDTO.getCorreo());
        existingUsuario.setContraseña(usuarioDTO.getContrasena());
        existingUsuario.setComunidad(usuarioDTO.getComunidad());

        try {
            String json = objectMapper.writeValueAsString(usuarioDTO);
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
        Optional<Persona> optionalUsuario = personaRepository.findByCorreo(correo);
        if (optionalUsuario.isEmpty()) {
            throw new NotFoundException("Usuario no encontrado con correo: " + correo);
        }
        Persona usuario = optionalUsuario.get();
        return passwordEncoder.matches(contrasena, usuario.getContrasena());
    }
}