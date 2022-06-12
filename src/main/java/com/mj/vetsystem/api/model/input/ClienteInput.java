package com.mj.vetsystem.api.model.input;

import java.time.OffsetDateTime;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.mj.vetsystem.domain.model.SexoCliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteInput {

	@NotBlank
	private String nome;
	
	@Email
	private String email;
	
	@CPF
	@NotBlank
	private String cpf;
	
	
	@NotNull
	private OffsetDateTime dataNascimento;
	
	@NotNull
	private SexoCliente sexo;
	
	@Valid
	@NotNull
	private EnderecoInput endereco;
}
