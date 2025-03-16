package com.restApi.prueba.services;

import com.restApi.prueba.http_errors.NotFoundException;
import com.restApi.prueba.resources.Dtos.AdministrativoDTO;
import com.restApi.prueba.models.Administrativo;

import com.restApi.prueba.repositorys.AdministrativoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdministrativoService {

    @Autowired
    private AdministrativoRepository administrativoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<AdministrativoDTO> getAll() {
        return administrativoRepository.findAll().stream()
                .map(admin -> modelMapper.map(admin, AdministrativoDTO.class))
                .collect(Collectors.toList());
    }

    public AdministrativoDTO getById(Long id) {
        Administrativo admin = administrativoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Administrativo no encontrado con id: " + id));
        return modelMapper.map(admin, AdministrativoDTO.class);
    }

    public AdministrativoDTO create(AdministrativoDTO dto) {
        Administrativo admin = modelMapper.map(dto, Administrativo.class);
        admin = administrativoRepository.save(admin);
        return modelMapper.map(admin, AdministrativoDTO.class);
    }

    public AdministrativoDTO update(Long id, AdministrativoDTO dto) {
        Administrativo existing = administrativoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Administrativo no encontrado con id: " + id));
        existing.setNombre(dto.getNombre());
        existing.setApellido(dto.getApellido());
        existing.setFechaNacimiento(dto.getFechaNacimiento());
        existing.setEmail(dto.getEmail());
        existing.setTelefono(dto.getTelefono());
        existing.setCargo(dto.getCargo());
        existing.setDepartamento(dto.getDepartamento());
        administrativoRepository.save(existing);
        return modelMapper.map(existing, AdministrativoDTO.class);
    }

    public void delete(Long id) {
        if(!administrativoRepository.existsById(id)) {
            throw new NotFoundException("Administrativo no encontrado con id: " + id);
        }
        administrativoRepository.deleteById(id);
    }
}