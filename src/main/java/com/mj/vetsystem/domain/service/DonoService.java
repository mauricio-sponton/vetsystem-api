package com.mj.vetsystem.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mj.vetsystem.domain.exception.DonoNaoEncontradoException;
import com.mj.vetsystem.domain.exception.EntidadeEmUsoException;
import com.mj.vetsystem.domain.model.Cidade;
import com.mj.vetsystem.domain.model.Dono;
import com.mj.vetsystem.domain.repository.DonoRepository;

@Service
public class DonoService {

	private static final String MSG_DONO_EM_USO = "Dono de código %d não pode ser removido, pois está em uso";

	@Autowired
	private DonoRepository donoRepository;

	@Autowired
	private CidadeService cidadeService;
	
	public Page<Dono> listar(Pageable pageable){
		return donoRepository.findAll(pageable);
	}

	@Transactional
	public Dono salvar(Dono dono) {
		
		Long cidadeId = dono.getEndereco().getCidade().getId();
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		
		dono.getEndereco().setCidade(cidade);
		
		return donoRepository.save(dono);
	}

	@Transactional
	public void excluir(Long donoId) {
		try {
			donoRepository.deleteById(donoId);
			donoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new DonoNaoEncontradoException(donoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_DONO_EM_USO, donoId));
		}
	}

	public Dono buscarOuFalhar(Long donoId) {
		return donoRepository.findById(donoId).orElseThrow(
				() -> new DonoNaoEncontradoException(donoId));
	}
}
