package com.mj.vetsystem.domain.model.enums;

public enum SexoCliente {

	MASCULINO("Masculino"), 
	FEMININO("Feminino");
	
	private String descricao;
	
	private SexoCliente(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
