package com.mj.vetsystem.domain.exception;

public class InternacaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public InternacaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public InternacaoNaoEncontradaException(Long internacaoId) {
		this(String.format("Não existe um cadastro de internação com código %d", internacaoId));
	}

}