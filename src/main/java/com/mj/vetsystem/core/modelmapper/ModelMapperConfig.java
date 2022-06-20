package com.mj.vetsystem.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mj.vetsystem.api.model.EnderecoModel;
import com.mj.vetsystem.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		
		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);
		
		enderecoToEnderecoModelTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
		
		return modelMapper;
	}
}
