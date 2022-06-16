package com.mj.vetsystem.api.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mj.vetsystem.domain.model.enums.SexoCliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteModel {

	private Long id;
	private String nome;
	private String email;
	private String cpf;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
	private OffsetDateTime dataNascimento;
	private SexoCliente sexo;
	private EnderecoModel endereco;
	
	public String getSexo() {
    	return sexo.getDescricao();
    }
}
