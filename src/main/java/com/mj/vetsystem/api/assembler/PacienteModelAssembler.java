package com.mj.vetsystem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.PacienteModel;
import com.mj.vetsystem.domain.model.Paciente;

@Component
public class PacienteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public PacienteModel toModel(Paciente paciente) {
        return modelMapper.map(paciente, PacienteModel.class);
    }
    
    public List<PacienteModel> toCollectionModel(List<Paciente> pacientes) {
        return pacientes.stream()
                .map(paciente -> toModel(paciente))
                .collect(Collectors.toList());
    }
    
}