package com.mj.vetsystem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.HistoricoPesoPacienteModel;
import com.mj.vetsystem.domain.model.HistoricoPeso;

@Component
public class HistoricoPesoPacienteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public HistoricoPesoPacienteModel toModel(HistoricoPeso historicoPesoPaciente) {
        return modelMapper.map(historicoPesoPaciente, HistoricoPesoPacienteModel.class);
    }
    
    public List<HistoricoPesoPacienteModel> toCollectionModel(List<HistoricoPeso> historicoPesoPacientes) {
        return historicoPesoPacientes.stream()
                .map(historicoPesoPaciente -> toModel(historicoPesoPaciente))
                .collect(Collectors.toList());
    }
    
}