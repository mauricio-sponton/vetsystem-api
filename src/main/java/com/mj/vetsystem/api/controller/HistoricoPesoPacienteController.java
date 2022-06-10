package com.mj.vetsystem.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mj.vetsystem.api.assembler.HistoricoPesoPacienteModelAssembler;
import com.mj.vetsystem.api.model.HistoricoPesoPacienteModel;
import com.mj.vetsystem.domain.model.HistoricoPeso;
import com.mj.vetsystem.domain.model.Paciente;
import com.mj.vetsystem.domain.service.HistoricoPesoPacienteService;
import com.mj.vetsystem.domain.service.PacienteService;

@RestController
@RequestMapping("/pacientes/{pacienteId}/historico-peso")
public class HistoricoPesoPacienteController {

	@Autowired
	private HistoricoPesoPacienteService historicoPesoPacienteService;
	
	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private HistoricoPesoPacienteModelAssembler historicoPesoPacienteModelAssembler;

	@GetMapping
	public List<HistoricoPesoPacienteModel> listar(@PathVariable Long pacienteId) {
		
		Paciente paciente = pacienteService.buscarOuFalhar(pacienteId);
		
		List<HistoricoPeso> historicoPeso =  historicoPesoPacienteService.listar(paciente.getId());
		return historicoPesoPacienteModelAssembler.toCollectionModel(historicoPeso);
	}

}
