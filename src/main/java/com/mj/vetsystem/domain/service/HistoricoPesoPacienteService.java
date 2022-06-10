package com.mj.vetsystem.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mj.vetsystem.domain.model.HistoricoPeso;
import com.mj.vetsystem.domain.repository.PacienteRepository;

@Service
public class HistoricoPesoPacienteService {


	@Autowired
	private PacienteRepository pacienteRepository;
	
	public List<HistoricoPeso> listar(Long pacienteId){
		return pacienteRepository.findAllByPaciente(pacienteId);
	}

}
