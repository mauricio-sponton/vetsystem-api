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

import com.mj.vetsystem.api.assembler.DonoModelAssembler;
import com.mj.vetsystem.api.disassembler.DonoInputDisassembler;
import com.mj.vetsystem.api.model.DonoModel;
import com.mj.vetsystem.api.model.TodosDonosModel;
import com.mj.vetsystem.api.model.input.DonoInput;
import com.mj.vetsystem.domain.exception.CidadeNaoEncontradaException;
import com.mj.vetsystem.domain.exception.NegocioException;
import com.mj.vetsystem.domain.model.Dono;
import com.mj.vetsystem.domain.service.DonoService;

@RestController
@RequestMapping(value = "/donos")
public class DonoController {

	@Autowired
	private DonoService donoService;
	
	@Autowired
	private DonoModelAssembler donoModelAssembler;

	@Autowired
	private DonoInputDisassembler donoInputDisassembler; 

	@GetMapping
	public List<TodosDonosModel> listar() {
		List<Dono> todosDonos = donoService.listar();
		return donoModelAssembler.toCollectionModel(todosDonos);
	}

	@GetMapping("/{donoId}")
	public DonoModel buscar(@PathVariable Long donoId) {
	    Dono dono = donoService.buscarOuFalhar(donoId);
	    
	    return donoModelAssembler.toModel(dono);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DonoModel adicionar(@RequestBody @Valid DonoInput donoInput) {
	    try {
	        Dono dono = donoInputDisassembler.toDomainObject(donoInput);
	        
	        dono = donoService.salvar(dono);
	        
	        return donoModelAssembler.toModel(dono);
	    } catch (CidadeNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}

	@PutMapping("/{donoId}")
	public DonoModel atualizar(@PathVariable Long donoId,
	        @RequestBody @Valid DonoInput donoInput) {
	    try {
	        Dono donoAtual = donoService.buscarOuFalhar(donoId);
	        
	        donoInputDisassembler.copyToDomainObject(donoInput, donoAtual);
	        
	        donoAtual = donoService.salvar(donoAtual);
	        
	        return donoModelAssembler.toModel(donoAtual);
	    } catch (CidadeNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}

	@DeleteMapping("/{donoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long donoId) {
		donoService.excluir(donoId);
	}

}
