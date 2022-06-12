package com.mj.vetsystem.domain.exception;

public class RacaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public RacaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public RacaNaoEncontradaException(Long racaId) {
		this(String.format("Não existe um cadastro de raça com código %d", racaId));
	}

}