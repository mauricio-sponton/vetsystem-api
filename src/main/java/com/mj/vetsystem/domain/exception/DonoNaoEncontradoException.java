package com.mj.vetsystem.domain.exception;

public class DonoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public DonoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public DonoNaoEncontradoException(Long donoId) {
		this(String.format("Não existe um cadastro de dono com código %d", donoId));
	}

}