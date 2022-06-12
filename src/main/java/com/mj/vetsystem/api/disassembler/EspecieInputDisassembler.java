package com.mj.vetsystem.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.input.EspecieInput;
import com.mj.vetsystem.domain.model.Especie;

@Component
public class EspecieInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Especie toDomainObject(EspecieInput especieInput) {
        return modelMapper.map(especieInput, Especie.class);
    }
    
    public void copyToDomainObject(EspecieInput especieInput, Especie especie) {
        modelMapper.map(especieInput, especie);
    }   
} 