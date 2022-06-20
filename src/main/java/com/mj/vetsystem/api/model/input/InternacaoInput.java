package com.mj.vetsystem.api.model.input;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InternacaoInput {

	@Valid
	@NotNull
	private PacienteIdInput paciente;

	@PastOrPresent
	@NotNull
	private OffsetDateTime dataAdmissao;

	private String prognostico;
	private String diagnostico;
	private String observacoes;

	@Positive
	private BigDecimal peso;

	@Positive
	private BigDecimal temperatura;

	
	@Valid
    @NotNull
	private List<ProgressoTratamentoInput> tratamentos;
}