package com.mj.vetsystem.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mj.vetsystem.domain.exception.EntidadeEmUsoException;
import com.mj.vetsystem.domain.exception.InternacaoNaoEncontradaException;
import com.mj.vetsystem.domain.exception.NegocioException;
import com.mj.vetsystem.domain.model.Internacao;
import com.mj.vetsystem.domain.model.Paciente;
import com.mj.vetsystem.domain.repository.InternacaoRepository;

@Service
public class InternacaoService {

	private static final String MSG_INTERNACAO_EM_USO = "Internacao de código %d não pode ser removida, pois está em uso";

	@Autowired
	private InternacaoRepository internacaoRepository;

	@Autowired
	private PacienteService pacienteService;

	public Page<Internacao> listar(Pageable pageable) {
		return internacaoRepository.findAll(pageable);
	}

	@Transactional
	public Internacao salvar(Internacao internacao) {
		
		boolean pacienteInternado = internacaoRepository.isPacienteInternado(internacao.getPaciente().getId());
		if(pacienteInternado) {
			throw new NegocioException(String.format("Paciente de código %d já está internado", internacao.getPaciente().getId()));
		}
		
		buscarPacienteOuFalhar(internacao);

		return internacaoRepository.save(internacao);
	}

	@Transactional
	public Internacao atualizar(Long internacaoId, Internacao internacaoParaAtualizar) {
		Internacao internacao = buscarOuFalhar(internacaoId);
		
		internacao.getTratamentos().clear();
		internacao.getTratamentos().addAll(internacaoParaAtualizar.getTratamentos());
		validarTratamentos(internacao);
		
		BeanUtils.copyProperties(internacaoParaAtualizar, internacao, "id", "tratamentos");
		
		buscarPacienteOuFalhar(internacao);

		return internacaoRepository.save(internacao);
	}

	@Transactional
	public void excluir(Long internacaoId) {
		try {
			internacaoRepository.deleteById(internacaoId);
			internacaoRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new InternacaoNaoEncontradaException(internacaoId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_INTERNACAO_EM_USO, internacaoId));
		}
	}

	public Internacao buscarOuFalhar(Long internacaoId) {
		return internacaoRepository.findById(internacaoId)
				.orElseThrow(() -> new InternacaoNaoEncontradaException(internacaoId));
	}
	
	private void validarTratamentos(Internacao internacao) {
		internacao.getTratamentos().forEach(tratamento -> {
			tratamento.setInternacao(internacao);
		});
	}
	
	
	private void buscarPacienteOuFalhar(Internacao internacao) {
		Long pacienteId = internacao.getPaciente().getId();
		Paciente paciente = pacienteService.buscarOuFalhar(pacienteId);
		internacao.setPaciente(paciente);
	}
}
