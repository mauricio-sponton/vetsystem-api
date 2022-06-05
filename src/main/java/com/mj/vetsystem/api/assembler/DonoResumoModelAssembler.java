package com.mj.vetsystem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.DonoResumoModel;
import com.mj.vetsystem.domain.model.Dono;

@Component
public class DonoResumoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public DonoResumoModel toModel(Dono dono) {
        return modelMapper.map(dono, DonoResumoModel.class);
    }
    
    public List<DonoResumoModel> toCollectionModel(List<Dono> donos) {
        return donos.stream()
                .map(dono -> toModel(dono))
                .collect(Collectors.toList());
    }
}