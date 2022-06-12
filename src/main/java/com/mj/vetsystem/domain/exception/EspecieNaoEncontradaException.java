package com.mj.vetsystem.domain.exception;

public class EspecieNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EspecieNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public EspecieNaoEncontradaException(Long especieId) {
		this(String.format("Não existe um cadastro de espécie com código %d", especieId));
	}

}