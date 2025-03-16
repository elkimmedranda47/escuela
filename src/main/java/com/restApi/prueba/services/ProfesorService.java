package com.restApi.prueba.services;

import com.restApi.prueba.http_errors.NotFoundException;
import com.restApi.prueba.models.Profesor;
import com.restApi.prueba.resources.Dtos.ProfesorDTO;

import com.restApi.prueba.repositorys.ProfesorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProfesorDTO> getAll() {
        return repository.findAll().stream()
                .map(prof -> modelMapper.map(prof, ProfesorDTO.class))
                .collect(Collectors.toList());
    }

    public ProfesorDTO getById(Long id) {
        Profesor profesor = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado con id: " + id));
        return modelMapper.map(profesor, ProfesorDTO.class);
    }

    public ProfesorDTO create(ProfesorDTO dto) {
        Profesor profesor = modelMapper.map(dto, Profesor.class);
        profesor = repository.save(profesor);
        return modelMapper.map(profesor, ProfesorDTO.class);
    }

    public ProfesorDTO update(Long id, ProfesorDTO dto) {
        Profesor existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado con id: " + id));
        existing.setNombre(dto.getNombre());
        existing.setApellido(dto.getApellido());
        existing.setFechaNacimiento(dto.getFechaNacimiento());
        existing.setEmail(dto.getEmail());
        existing.setTelefono(dto.getTelefono());
        existing.setEspecialidad(dto.getEspecialidad());
        existing.setFechaContratacion(dto.getFechaContratacion());
        repository.save(existing);
        return modelMapper.map(existing, ProfesorDTO.class);
    }

    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new NotFoundException("Profesor no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}