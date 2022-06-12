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

import com.mj.vetsystem.api.assembler.RacaModelAssembler;
import com.mj.vetsystem.api.disassembler.RacaInputDisassembler;
import com.mj.vetsystem.api.model.RacaModel;
import com.mj.vetsystem.api.model.input.RacaInput;
import com.mj.vetsystem.core.data.PageableTranslator;
import com.mj.vetsystem.domain.exception.EspecieNaoEncontradaException;
import com.mj.vetsystem.domain.exception.NegocioException;
import com.mj.vetsystem.domain.model.Raca;
import com.mj.vetsystem.domain.service.RacaService;

@RestController
@RequestMapping(value = "/racas")
public class RacaController {

	@Autowired
	private RacaService racaService;
	
	@Autowired
	private RacaModelAssembler racaModelAssembler;

	@Autowired
	private RacaInputDisassembler racaInputDisassembler; 

	@GetMapping
	public Page<RacaModel> listar(Pageable pageable) {
		pageable = traduzirPageable(pageable);
		Page<Raca> todasRacas = racaService.listar(pageable);
		List<RacaModel> racasModel = racaModelAssembler.toCollectionModel(todasRacas.getContent());
		Page<RacaModel> racaModelPage = new PageImpl<RacaModel>(racasModel, pageable, todasRacas.getTotalElements());
		return racaModelPage;
	}

	@GetMapping("/{racaId}")
	public RacaModel buscar(@PathVariable Long racaId) {
	    Raca raca = racaService.buscarOuFalhar(racaId);
	    
	    return racaModelAssembler.toModel(raca);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RacaModel adicionar(@RequestBody @Valid RacaInput racaInput) {
	    try {
	        Raca raca = racaInputDisassembler.toDomainObject(racaInput);
	        
	        raca = racaService.salvar(raca);
	        
	        return racaModelAssembler.toModel(raca);
	    } catch (EspecieNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}

	@PutMapping("/{racaId}")
	public RacaModel atualizar(@PathVariable Long racaId,
	        @RequestBody @Valid RacaInput racaInput) {
	    try {
	        Raca racaAtual = racaService.buscarOuFalhar(racaId);
	        
	        racaInputDisassembler.copyToDomainObject(racaInput, racaAtual);
	        
	        racaAtual = racaService.salvar(racaAtual);
	        
	        return racaModelAssembler.toModel(racaAtual);
	    } catch (EspecieNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}

	@DeleteMapping("/{racaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long racaId) {
		racaService.excluir(racaId);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"id", "id",
				"nome", "nome",
				"especie.nome", "especie.nome",
				"especie.id", "especie.id"
		);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}

}
