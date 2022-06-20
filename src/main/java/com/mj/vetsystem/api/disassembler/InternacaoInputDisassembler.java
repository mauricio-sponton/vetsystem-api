package com.mj.vetsystem.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.input.InternacaoInput;
import com.mj.vetsystem.domain.model.Internacao;
import com.mj.vetsystem.domain.model.Paciente;

@Component
public class InternacaoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Internacao toDomainObject(InternacaoInput internacaoInput) {
        return modelMapper.map(internacaoInput, Internacao.class);
    }
    
    public void copyToDomainObject(InternacaoInput internacaoInput, Internacao internacao) {

        internacao.setPaciente(new Paciente());
		modelMapper.map(internacaoInput, internacao);
    }
    
}