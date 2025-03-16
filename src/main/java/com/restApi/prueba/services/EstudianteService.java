package com.restApi.prueba.services;

// package com.example.school.service;

import com.restApi.prueba.http_errors.NotFoundException;
import com.restApi.prueba.repositorys.EstudianteRepository;
import com.restApi.prueba.resources.Dtos.EstudianteDTO;
import com.restApi.prueba.models.Estudiante;
//import com.example.school.exception.ResourceNotFoundException;
//import com.example.school.repository.EstudianteRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<EstudianteDTO> getAll() {

        System.out.println("llegamos al recurso estudianteServices");
        return repository.findAll().stream()
                .map(est -> modelMapper.map(est, EstudianteDTO.class))
                .collect(Collectors.toList());
    }

    public EstudianteDTO getById(Long id) {
        Estudiante estudiante = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con id: " + id));
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }

    public EstudianteDTO create(EstudianteDTO dto) {
        if(repository.existsByNumeroMatricula(dto.getNumeroMatricula())){
            throw new RuntimeException("El número de matrícula ya existe");
        }
        Estudiante estudiante = modelMapper.map(dto, Estudiante.class);
        estudiante = repository.save(estudiante);
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }

    public EstudianteDTO update(Long id, EstudianteDTO dto) {
        Estudiante existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con id: " + id));
        existing.setNombre(dto.getNombre());
        existing.setApellido(dto.getApellido());
        existing.setFechaNacimiento(dto.getFechaNacimiento());
        existing.setEmail(dto.getEmail());
        existing.setTelefono(dto.getTelefono());
        existing.setNumeroMatricula(dto.getNumeroMatricula());
        existing.setGrado(dto.getGrado());
        repository.save(existing);
        return modelMapper.map(existing, EstudianteDTO.class);
    }

    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new NotFoundException("Estudiante no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }

    // Función especial: buscar por número de matrícula
    public EstudianteDTO findByNumeroMatricula(String numeroMatricula) {
        Estudiante estudiante = repository.findAll().stream()
                .filter(e -> e.getNumeroMatricula().equals(numeroMatricula))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con número de matrícula: " + numeroMatricula));
        return modelMapper.map(estudiante, EstudianteDTO.class);
    }
}
