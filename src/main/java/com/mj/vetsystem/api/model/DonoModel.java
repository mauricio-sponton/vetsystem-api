package com.mj.vetsystem.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DonoModel {


	private Long id;
	private String nome;
	private EnderecoModel endereco;
}
