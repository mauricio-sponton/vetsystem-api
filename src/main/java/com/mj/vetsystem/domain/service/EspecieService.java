package com.mj.vetsystem.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mj.vetsystem.domain.exception.EntidadeEmUsoException;
import com.mj.vetsystem.domain.exception.EspecieNaoEncontradaException;
import com.mj.vetsystem.domain.model.Especie;
import com.mj.vetsystem.domain.repository.EspecieRepository;

@Service
public class EspecieService {

	private static final String MSG_ESTADO_EM_USO = "Espécie de código %d não pode ser removida, pois está em uso";

	@Autowired
	private EspecieRepository especieRepository;
	
	public Page<Especie> listar(Pageable pageable){
		return especieRepository.findAll(pageable);
	}

	@Transactional
	public Especie salvar(Especie especie) {
		return especieRepository.save(especie);
	}

	@Transactional
	public void excluir(Long especieId) {
		try {
			especieRepository.deleteById(especieId);
			especieRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new EspecieNaoEncontradaException(especieId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, especieId));
		}
	}

	public Especie buscarOuFalhar(Long especieId) {
		return especieRepository.findById(especieId).orElseThrow(
				() -> new EspecieNaoEncontradaException(especieId));
	}
}
