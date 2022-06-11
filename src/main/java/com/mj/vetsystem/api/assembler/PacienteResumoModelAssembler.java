package com.mj.vetsystem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.PacienteResumoModel;
import com.mj.vetsystem.domain.model.Paciente;

@Component
public class PacienteResumoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public PacienteResumoModel toModel(Paciente paciente) {
        return modelMapper.map(paciente, PacienteResumoModel.class);
    }
    
    public List<PacienteResumoModel> toCollectionModel(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(paciente -> toModel(paciente))
                .collect(Collectors.toList());
    }
    
}