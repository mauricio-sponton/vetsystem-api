package com.mj.vetsystem.api.model;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mj.vetsystem.domain.model.enums.ViaMedicamento;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProgressoTratamentoModel {

	private Long id;
	private String nome;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime data;
	
	private Integer dose;
	
	private ViaMedicamento viaMedicamento;
}
