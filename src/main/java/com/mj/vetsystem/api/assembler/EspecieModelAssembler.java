package com.mj.vetsystem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.EspecieModel;
import com.mj.vetsystem.domain.model.Especie;

@Component
public class EspecieModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public EspecieModel toModel(Especie especie) {
        return modelMapper.map(especie, EspecieModel.class);
    }
    
    public List<EspecieModel> toCollectionModel(List<Especie> especies) {
        return especies.stream()
                .map(especie -> toModel(especie))
                .collect(Collectors.toList());
    }
    
}