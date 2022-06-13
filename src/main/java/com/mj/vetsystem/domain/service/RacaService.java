package com.mj.vetsystem.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mj.vetsystem.domain.exception.EntidadeEmUsoException;
import com.mj.vetsystem.domain.exception.NegocioException;
import com.mj.vetsystem.domain.exception.RacaNaoEncontradaException;
import com.mj.vetsystem.domain.model.Especie;
import com.mj.vetsystem.domain.model.Raca;
import com.mj.vetsystem.domain.repository.RacaRepository;

@Service
public class RacaService {

	private static final String MSG_RACA_EM_USO = "Raça de código %d não pode ser removida, pois está em uso";

	@Autowired
	private RacaRepository racaRepository;

	@Autowired
	private EspecieService especieService;
	
	public Page<Raca> listar(Pageable pageable){
		return racaRepository.findAll(pageable);
	}

	@Transactional
	public Raca salvar(Raca raca) {
		
		
		Long especieId = raca.getEspecie().getId();
		Especie especie = especieService.buscarOuFalhar(especieId);
		
		racaRepository.detach(raca);
		Optional<Raca> racaExistente = racaRepository.findByNomeAndEspecie(raca.getNome(), especieId);
		
		if(racaExistente.isPresent() && !racaExistente.get().equals(raca)) {
			throw new NegocioException(
					String.format("Já existe uma raça cadastrada com o nome %s na espécie %s", raca.getNome(), especie.getNome()));
		}

		raca.setEspecie(especie);

		return racaRepository.save(raca);
	}

	@Transactional
	public void excluir(Long racaId) {
		try {
			racaRepository.deleteById(racaId);
			racaRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new RacaNaoEncontradaException(racaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_RACA_EM_USO, racaId));
		}
	}

	public Raca buscarOuFalhar(Long racaId) {
		return racaRepository.findById(racaId).orElseThrow(
				() -> new RacaNaoEncontradaException(racaId));
	}
}
