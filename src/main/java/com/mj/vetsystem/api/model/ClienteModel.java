package com.mj.vetsystem.api.model;

import java.time.OffsetDateTime;

import com.mj.vetsystem.domain.model.SexoCliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteModel {

	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private OffsetDateTime dataNascimento;
	private SexoCliente sexo;
	private EnderecoModel endereco;
	
	public String getSexo() {
    	return sexo.getDescricao();
    }
}
