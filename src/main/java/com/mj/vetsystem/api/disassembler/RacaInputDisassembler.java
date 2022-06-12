package com.mj.vetsystem.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.input.RacaInput;
import com.mj.vetsystem.domain.model.Especie;
import com.mj.vetsystem.domain.model.Raca;

@Component
public class RacaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Raca toDomainObject(RacaInput racaInput) {
        return modelMapper.map(racaInput, Raca.class);
    }
    
    public void copyToDomainObject(RacaInput racaInput, Raca raca) {
        raca.setEspecie(new Especie());
        modelMapper.map(racaInput, raca);
    }
    
}