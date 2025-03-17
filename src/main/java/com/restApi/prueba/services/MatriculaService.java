package com.restApi.prueba.services;

import com.restApi.prueba.http_errors.ConflictException;
import com.restApi.prueba.http_errors.NotFoundException;
import com.restApi.prueba.models.Curso;
import com.restApi.prueba.models.Estudiante;
import com.restApi.prueba.models.Matricula;
import com.restApi.prueba.repositorys.CursoRepository;
import com.restApi.prueba.repositorys.EstudianteRepository;
import com.restApi.prueba.repositorys.MatriculaRepository;
import com.restApi.prueba.resources.Dtos.MatriculaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatriculaService{

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MatriculaDTO> getAll() {
        return matriculaRepository.findAll().stream()
                .map(insc -> {
                    MatriculaDTO dto = modelMapper.map(insc, MatriculaDTO.class);
                    dto.setIdEstudiante(insc.getEstudiante().getIdPersona());
                    dto.setIdCurso(insc.getCurso().getIdCurso());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public MatriculaDTO getById(Long id) {
        Matricula insc = matriculaRepository.findById(id)

        .orElseThrow(() -> new NotFoundException("Inscripción no encontrada con id: " + id));
        MatriculaDTO dto = modelMapper.map(insc, MatriculaDTO.class);
        dto.setIdEstudiante(insc.getEstudiante().getIdPersona());
        dto.setIdCurso(insc.getCurso().getIdCurso());
        return dto;
    }

    public MatriculaDTO create(MatriculaDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(dto.getIdEstudiante())
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con id: " + dto.getIdEstudiante()));
        Curso curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() -> new NotFoundException("Curso no encontrado con id: " + dto.getIdCurso()));
        // Validar si ya está matriculado
        if (matriculaRepository.existsByEstudianteAndCurso(estudiante, curso)) {
            throw new ConflictException("El estudiante ya está matriculado en este curso");
        }
        Matricula insc = modelMapper.map(dto, Matricula.class);
        insc.setEstudiante(estudiante);
        insc.setCurso(curso);
        insc = matriculaRepository.save(insc);
        MatriculaDTO result = modelMapper.map(insc, MatriculaDTO.class);
        result.setIdEstudiante(estudiante.getIdPersona());
        result.setIdCurso(curso.getIdCurso());
        return result;
    }

    public MatriculaDTO update(Long id, MatriculaDTO dto) {
        Matricula existing = matriculaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Inscripción no encontrada con id: " + id));
        Estudiante estudiante = estudianteRepository.findById(dto.getIdEstudiante())
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con id: " + dto.getIdEstudiante()));
        Curso curso = cursoRepository.findById(dto.getIdCurso())
                .orElseThrow(() -> new NotFoundException("Curso no encontrado con id: " + dto.getIdCurso()));
        //existing.setFechaInscripcion(dto.getFechaInscripcion());
        existing.setFechaMatricula(dto.getFechaMatricula());
        existing.setEstudiante(estudiante);
        existing.setCurso(curso);
        matriculaRepository.save(existing);
        MatriculaDTO result = modelMapper.map(existing, MatriculaDTO.class);
        result.setIdEstudiante(estudiante.getIdPersona());
        result.setIdCurso(curso.getIdCurso());
        return result;
    }

    public void delete(Long id) {
        if(!matriculaRepository.existsById(id)) {
            throw new NotFoundException("Inscripción no encontrada con id: " + id);
        }
        matriculaRepository.deleteById(id);
    }

    // Función especial: obtener inscripciones por ID de estudiante
    public List<MatriculaDTO> findByEstudianteId(Long estudianteId) {
        return matriculaRepository.findAll().stream()
                .filter(insc -> insc.getEstudiante().getIdPersona().equals(estudianteId))
                .map(insc -> {
                    MatriculaDTO dto = modelMapper.map(insc, MatriculaDTO.class);
                    dto.setIdEstudiante(insc.getEstudiante().getIdPersona());
                    dto.setIdCurso(insc.getCurso().getIdCurso());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public MatriculaDTO matricularEstudiante(MatriculaDTO request) {
        // Validar existencia de Estudiante y Curso
        Estudiante estudiante = estudianteRepository.findById(request.getIdEstudiante())
                .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
        Curso curso = cursoRepository.findById(request.getIdCurso())
                .orElseThrow(() -> new NotFoundException("Curso no encontrado"));

        // Validar si ya está matriculado
        if (matriculaRepository.existsByEstudianteAndCurso(estudiante, curso)) {
            throw new ConflictException("El estudiante ya está matriculado en este curso");
        }

        // Crear matrícula
        Matricula matricula = new Matricula();
        matricula.setEstudiante(estudiante);
        matricula.setCurso(curso);
        matricula.setFechaMatricula(request.getFechaMatricula());
        matricula = matriculaRepository.save(matricula);

        return convertToResponse(matricula);
    }

    private MatriculaDTO convertToResponse(Matricula matricula) {
        // Usar ModelMapper o asignación manual
        MatriculaDTO response = new MatriculaDTO();
        response.setIdMatricula(matricula.getIdMatricula());
        response.setIdEstudiante(matricula.getEstudiante().getIdPersona());
        response.setIdCurso(matricula.getCurso().getIdCurso());
        response.setFechaMatricula(matricula.getFechaMatricula());

        return response;
    }
}