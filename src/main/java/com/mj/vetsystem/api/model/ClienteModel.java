package com.mj.vetsystem.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteModel {


	private Long id;
	private String nome;
	private EnderecoModel endereco;
}
