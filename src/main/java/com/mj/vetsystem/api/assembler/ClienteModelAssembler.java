package com.mj.vetsystem.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.ClienteModel;
import com.mj.vetsystem.domain.model.Cliente;

@Component
public class ClienteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }
    
    public List<ClienteModel> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(dono -> toModel(dono))
                .collect(Collectors.toList());
    }
}