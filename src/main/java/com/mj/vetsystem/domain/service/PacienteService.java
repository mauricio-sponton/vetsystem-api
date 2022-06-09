package com.mj.vetsystem.domain.service;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mj.vetsystem.domain.exception.PacienteNaoEncontradoException;
import com.mj.vetsystem.domain.model.HistoricoPeso;
import com.mj.vetsystem.domain.model.Paciente;
import com.mj.vetsystem.domain.repository.PacienteRepository;

@Service
public class PacienteService {


	@Autowired
	private PacienteRepository pacienteRepository;

	public List<Paciente> listar(){
		return pacienteRepository.findAll();
	}

	@Transactional
	public Paciente salvar(Paciente paciente) {
//		if(paciente.getPeso() != null) {
			//fazer via listener
//			paciente.getHistoricoPeso().forEach(p -> {
//				var historicoPeso = new HistoricoPeso();
//				historicoPeso.setPeso(paciente.getPeso());
//				historicoPeso.setDataCadastro(OffsetDateTime.now());
//				historicoPeso.setPaciente(paciente);
//			});
			
//		}
		return pacienteRepository.save(paciente);
	}

	public Paciente buscarOuFalhar(Long pacienteId) {
		return pacienteRepository.findById(pacienteId).orElseThrow(
				() -> new PacienteNaoEncontradoException(pacienteId));
	}
}
