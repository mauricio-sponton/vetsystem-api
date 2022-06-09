package com.mj.vetsystem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.ClienteResumoModel;
import com.mj.vetsystem.domain.model.Cliente;

@Component
public class ClienteResumoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public ClienteResumoModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteResumoModel.class);
    }
    
    public List<ClienteResumoModel> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(cliente -> toModel(cliente))
                .collect(Collectors.toList());
    }
}