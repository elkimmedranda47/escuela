package com.restApi.prueba.services;

import com.restApi.prueba.http_errors.NotFoundException;
import com.restApi.prueba.resources.Dtos.CursoDTO;
import com.restApi.prueba.models.Curso;
import com.restApi.prueba.models.Profesor;

import com.restApi.prueba.repositorys.CursoRepository;
import com.restApi.prueba.repositorys.ProfesorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CursoDTO> getAll() {
        return cursoRepository.findAll().stream()
                .map(curso -> {
                    CursoDTO dto = modelMapper.map(curso, CursoDTO.class);
                    dto.setIdProfesor(curso.getProfesor().getIdPersona());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public CursoDTO getById(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado con id: " + id));
        CursoDTO dto = modelMapper.map(curso, CursoDTO.class);
        dto.setIdProfesor(curso.getProfesor().getIdPersona());
        return dto;
    }

    public CursoDTO create(CursoDTO dto) {
        Profesor profesor = profesorRepository.findById(dto.getIdProfesor())
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado con id: " + dto.getIdProfesor()));
        Curso curso = modelMapper.map(dto, Curso.class);
        curso.setProfesor(profesor);
        curso = cursoRepository.save(curso);
        CursoDTO result = modelMapper.map(curso, CursoDTO.class);
        result.setIdProfesor(curso.getProfesor().getIdPersona());
        return result;
    }

    public CursoDTO update(Long id, CursoDTO dto) {
        Curso existing = cursoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Curso no encontrado con id: " + id));
        Profesor profesor = profesorRepository.findById(dto.getIdProfesor())
                .orElseThrow(() -> new NotFoundException("Profesor no encontrado con id: " + dto.getIdProfesor()));
        existing.setNombre(dto.getNombre());
        existing.setDescripcion(dto.getDescripcion());
        existing.setCreditos(dto.getCreditos());
        existing.setProfesor(profesor);
        cursoRepository.save(existing);
        CursoDTO result = modelMapper.map(existing, CursoDTO.class);
        result.setIdProfesor(profesor.getIdPersona());
        return result;
    }

    public void delete(Long id) {
        if(!cursoRepository.existsById(id)) {
            throw new NotFoundException("Curso no encontrado con id: " + id);
        }
        cursoRepository.deleteById(id);
    }

    // Función especial: obtener cursos asignados a un profesor
    public List<CursoDTO> findByProfesorId(Long profesorId) {
        return cursoRepository.findAll().stream()
                .filter(c -> c.getProfesor().getIdPersona().equals(profesorId))
                .map(curso -> {
                    CursoDTO dto = modelMapper.map(curso, CursoDTO.class);
                    dto.setIdProfesor(curso.getProfesor().getIdPersona());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}