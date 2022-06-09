package com.mj.vetsystem.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mj.vetsystem.api.assembler.PacienteModelAssembler;
import com.mj.vetsystem.api.disassembler.PacienteInputDisassembler;
import com.mj.vetsystem.api.model.PacienteModel;
import com.mj.vetsystem.api.model.input.PacienteInput;
import com.mj.vetsystem.domain.model.Paciente;
import com.mj.vetsystem.domain.service.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@Autowired
	private PacienteModelAssembler pacienteModelAssembler;

	@Autowired
	private PacienteInputDisassembler pacienteInputDisassembler;    

	@GetMapping
	public List<PacienteModel> listar() {
		List<Paciente> todosPacientes =  pacienteService.listar();
		return pacienteModelAssembler.toCollectionModel(todosPacientes);
	}

	@GetMapping("/{pacienteId}")
	public PacienteModel buscar(@PathVariable Long pacienteId) {
		Paciente paciente = pacienteService.buscarOuFalhar(pacienteId);
		return pacienteModelAssembler.toModel(paciente);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PacienteModel adicionar(@RequestBody @Valid PacienteInput pacienteInput) {
		Paciente paciente = pacienteInputDisassembler.toDomainObject(pacienteInput);
	    
	    paciente = pacienteService.salvar(paciente);
	    
	    return pacienteModelAssembler.toModel(paciente);
	}

	@PutMapping("/{pacienteId}")
	public PacienteModel atualizar(@PathVariable Long pacienteId,
	        @RequestBody @Valid PacienteInput pacienteInput) {
	    Paciente pacienteAtual = pacienteService.buscarOuFalhar(pacienteId);
	    
	    pacienteInputDisassembler.copyToDomainObject(pacienteInput, pacienteAtual);
	    
	    pacienteAtual = pacienteService.salvar(pacienteAtual);
	    
	    return pacienteModelAssembler.toModel(pacienteAtual);
	}       

}
