package com.mj.vetsystem.domain.model;

public enum SexoPaciente {

	MACHO("Macho"), 
	FEMEA("Fêmea");
	
	private String descricao;
	
	private SexoPaciente(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
