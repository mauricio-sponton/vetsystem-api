package com.mj.vetsystem.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mj.vetsystem.domain.exception.PacienteNaoEncontradoException;
import com.mj.vetsystem.domain.model.Cliente;
import com.mj.vetsystem.domain.model.Paciente;
import com.mj.vetsystem.domain.repository.PacienteRepository;

@Service
public class PacienteService {


	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public List<Paciente> listar(){
		return pacienteRepository.findAll();
	}

	@Transactional 
	public Paciente salvar(Paciente paciente) {
		
		Long clienteId = paciente.getDono().getId();
		
		Cliente cliente = clienteService.buscarOuFalhar(clienteId);
		paciente.setDono(cliente);
		
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
