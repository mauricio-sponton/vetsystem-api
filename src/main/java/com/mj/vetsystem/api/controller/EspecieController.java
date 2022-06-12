package com.mj.vetsystem.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

import com.mj.vetsystem.api.assembler.EspecieModelAssembler;
import com.mj.vetsystem.api.disassembler.EspecieInputDisassembler;
import com.mj.vetsystem.api.model.EspecieModel;
import com.mj.vetsystem.api.model.input.EspecieInput;
import com.mj.vetsystem.core.data.PageableTranslator;
import com.mj.vetsystem.domain.model.Especie;
import com.mj.vetsystem.domain.service.EspecieService;

@RestController
@RequestMapping("/especies")
public class EspecieController {

	@Autowired
	private EspecieService especieService;
	
	@Autowired
	private EspecieModelAssembler especieModelAssembler;

	@Autowired
	private EspecieInputDisassembler especieInputDisassembler;    

	@GetMapping
	public Page<EspecieModel> listar(Pageable pageable) {
		pageable = traduzirPageable(pageable);
		Page<Especie> todasEspecies = especieService.listar(pageable);
		List<EspecieModel> especiesModel = especieModelAssembler.toCollectionModel(todasEspecies.getContent());
		Page<EspecieModel> especieModelPage = new PageImpl<EspecieModel>(especiesModel, pageable, todasEspecies.getTotalElements());
		return especieModelPage;
	}

	@GetMapping("/{especieId}")
	public EspecieModel buscar(@PathVariable Long especieId) {
		Especie especie = especieService.buscarOuFalhar(especieId);
		return especieModelAssembler.toModel(especie);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EspecieModel adicionar(@RequestBody @Valid EspecieInput especieInput) {
		Especie especie = especieInputDisassembler.toDomainObject(especieInput);
	    
	    especie = especieService.salvar(especie);
	    
	    return especieModelAssembler.toModel(especie);
	}

	@PutMapping("/{especieId}")
	public EspecieModel atualizar(@PathVariable Long especieId,
	        @RequestBody @Valid EspecieInput especieInput) {
	    Especie especieAtual = especieService.buscarOuFalhar(especieId);
	    
	    especieInputDisassembler.copyToDomainObject(especieInput, especieAtual);
	    
	    especieAtual = especieService.salvar(especieAtual);
	    
	    return especieModelAssembler.toModel(especieAtual);
	}       

	@DeleteMapping("/{especieId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long especieId) {
		especieService.excluir(especieId);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"id", "id",
				"nome", "nome"
		);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
}
