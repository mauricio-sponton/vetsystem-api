package com.mj.vetsystem.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.input.PacienteInput;
import com.mj.vetsystem.domain.model.Cliente;
import com.mj.vetsystem.domain.model.Paciente;
import com.mj.vetsystem.domain.model.Raca;

@Component
public class PacienteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Paciente toDomainObject(PacienteInput pacienteInput) {
        return modelMapper.map(pacienteInput, Paciente.class);
    }
    
    public void copyToDomainObject(PacienteInput pacienteInput, Paciente paciente) {
    	paciente.setDono(new Cliente());
    	paciente.setRaca(new Raca());
        modelMapper.map(pacienteInput, paciente);
    }   
} 