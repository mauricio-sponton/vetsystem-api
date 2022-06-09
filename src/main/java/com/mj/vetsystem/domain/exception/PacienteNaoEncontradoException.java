package com.mj.vetsystem.domain.exception;

public class PacienteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public PacienteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public PacienteNaoEncontradoException(Long pacienteId) {
		this(String.format("Não existe um cadastro de paciente com código %d", pacienteId));
	}

}