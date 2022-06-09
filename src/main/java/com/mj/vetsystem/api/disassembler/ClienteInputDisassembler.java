package com.mj.vetsystem.api.disassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mj.vetsystem.api.model.input.ClienteInput;
import com.mj.vetsystem.domain.model.Cidade;
import com.mj.vetsystem.domain.model.Cliente;

@Component
public class ClienteInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;
    
    public Cliente toDomainObject(ClienteInput clienteInput) {
        return modelMapper.map(clienteInput, Cliente.class);
    }
    
    public void copyToDomainObject(ClienteInput clienteInput, Cliente cliente) {
       
        if(cliente.getEndereco() != null) {
        	cliente.getEndereco().setCidade(new Cidade());
        }
        modelMapper.map(clienteInput, cliente);
    }
    
}