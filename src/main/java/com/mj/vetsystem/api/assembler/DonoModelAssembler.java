package com.mj.vetsystem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.DonoModel;
import com.mj.vetsystem.domain.model.Dono;

@Component
public class DonoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public DonoModel toModel(Dono dono) {
        return modelMapper.map(dono, DonoModel.class);
    }
    
    public List<DonoModel> toCollectionModel(List<Dono> donos) {
        return donos.stream()
                .map(dono -> toModel(dono))
                .collect(Collectors.toList());
    }
}