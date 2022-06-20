package com.mj.vetsystem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.InternacaoModel;
import com.mj.vetsystem.domain.model.Internacao;

@Component
public class InternacaoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public InternacaoModel toModel(Internacao internacao) {
        return modelMapper.map(internacao, InternacaoModel.class);
    }
    
    public List<InternacaoModel> toCollectionModel(List<Internacao> internacaos) {
        return internacaos.stream()
                .map(internacao -> toModel(internacao))
                .collect(Collectors.toList());
    }
}