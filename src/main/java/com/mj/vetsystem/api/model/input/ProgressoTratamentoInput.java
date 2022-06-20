package com.mj.vetsystem.api.model.input;

import java.time.OffsetDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.mj.vetsystem.domain.model.enums.ViaMedicamento;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProgressoTratamentoInput {

	@NotBlank
	private String nome;
	
	@PastOrPresent
    private OffsetDateTime data;
	
    @Positive
	private Integer dose;
	
	private ViaMedicamento viaMedicamento;
}