package com.mj.vetsystem.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.mj.vetsystem.domain.exception.CidadeNaoEncontradaException;
import com.mj.vetsystem.domain.exception.EntidadeEmUsoException;
import com.mj.vetsystem.domain.model.Cidade;
import com.mj.vetsystem.domain.model.Estado;
import com.mj.vetsystem.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoService estadoService;
	
	public List<Cidade> listar(){
		return cidadeRepository.findAll();
	}

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();

		Estado estado = estadoService.buscarOuFalhar(estadoId);

		cidade.setEstado(estado);

		return cidadeRepository.save(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);

		} catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException(cidadeId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}

	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(
				() -> new CidadeNaoEncontradaException(cidadeId));
	}
}
