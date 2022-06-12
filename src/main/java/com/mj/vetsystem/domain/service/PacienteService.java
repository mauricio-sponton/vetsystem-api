package com.mj.vetsystem.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mj.vetsystem.domain.exception.PacienteNaoEncontradoException;
import com.mj.vetsystem.domain.model.Cliente;
import com.mj.vetsystem.domain.model.Paciente;
import com.mj.vetsystem.domain.model.Raca;
import com.mj.vetsystem.domain.repository.PacienteRepository;

@Service
public class PacienteService {


	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private RacaService racaService;
	
	public Page<Paciente> listar(Pageable pageable){
		return pacienteRepository.findAll(pageable);
	}

	@Transactional 
	public Paciente salvar(Paciente paciente) {
		
		Long clienteId = paciente.getDono().getId();
		Long racaId = paciente.getRaca().getId();
		
		Cliente cliente = clienteService.buscarOuFalhar(clienteId);
		Raca raca = racaService.buscarOuFalhar(racaId);
		
		paciente.setDono(cliente);
		paciente.setRaca(raca);
		
		if(paciente.getPeso() != null) {
			paciente.registrarPeso();
		}
		
		return pacienteRepository.save(paciente);
	}

	public Paciente buscarOuFalhar(Long pacienteId) {
		return pacienteRepository.findById(pacienteId).orElseThrow(
				() -> new PacienteNaoEncontradoException(pacienteId));
	}
}
