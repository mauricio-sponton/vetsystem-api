package com.mj.vetsystem.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.input.DonoInput;
import com.mj.vetsystem.domain.model.Cidade;
import com.mj.vetsystem.domain.model.Dono;

@Component
public class DonoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Dono toDomainObject(DonoInput donoInput) {
        return modelMapper.map(donoInput, Dono.class);
    }
    
    public void copyToDomainObject(DonoInput donoInput, Dono dono) {
       
        if(dono.getEndereco() != null) {
        	dono.getEndereco().setCidade(new Cidade());
        }
        modelMapper.map(donoInput, dono);
    }
    
}