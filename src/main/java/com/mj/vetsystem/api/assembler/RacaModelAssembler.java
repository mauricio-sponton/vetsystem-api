package com.mj.vetsystem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.RacaModel;
import com.mj.vetsystem.domain.model.Raca;

@Component
public class RacaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public RacaModel toModel(Raca raca) {
        return modelMapper.map(raca, RacaModel.class);
    }
    
    public List<RacaModel> toCollectionModel(List<Raca> racas) {
        return racas.stream()
                .map(raca -> toModel(raca))
                .collect(Collectors.toList());
    }
}