package com.mj.vetsystem.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mj.vetsystem.api.assembler.EstadoModelAssembler;
import com.mj.vetsystem.api.disassembler.EstadoInputDisassembler;
import com.mj.vetsystem.api.model.EstadoModel;
import com.mj.vetsystem.api.model.input.EstadoInput;
import com.mj.vetsystem.domain.model.Estado;
import com.mj.vetsystem.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;    

	@GetMapping
	public List<EstadoModel> listar() {
		List<Estado> todosEstados =  estadoService.listar();
		return estadoModelAssembler.toCollectionModel(todosEstados);
	}

	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable Long estadoId) {
		Estado estado = estadoService.buscarOuFalhar(estadoId);
		return estadoModelAssembler.toModel(estado);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
	    
	    estado = estadoService.salvar(estado);
	    
	    return estadoModelAssembler.toModel(estado);
	}

	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId,
	        @RequestBody @Valid EstadoInput estadoInput) {
	    Estado estadoAtual = estadoService.buscarOuFalhar(estadoId);
	    
	    estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
	    
	    estadoAtual = estadoService.salvar(estadoAtual);
	    
	    return estadoModelAssembler.toModel(estadoAtual);
	}       

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		estadoService.excluir(estadoId);
	}
}
