package com.mj.vetsystem.domain.model.enums;

public enum PortePaciente {

	MINI("Mini"),
	PEQUENO("Pequeno"), 
	MEDIO("Médio"),
	GRANDE("Grande"),
	GIGANTE("Gigante");
	
	private String descricao;
	
	private PortePaciente(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
