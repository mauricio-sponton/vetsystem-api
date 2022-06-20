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

import com.mj.vetsystem.api.assembler.InternacaoModelAssembler;
import com.mj.vetsystem.api.disassembler.InternacaoInputDisassembler;
import com.mj.vetsystem.api.model.InternacaoModel;
import com.mj.vetsystem.api.model.input.InternacaoInput;
import com.mj.vetsystem.core.data.PageableTranslator;
import com.mj.vetsystem.domain.exception.NegocioException;
import com.mj.vetsystem.domain.exception.PacienteNaoEncontradoException;
import com.mj.vetsystem.domain.model.Internacao;
import com.mj.vetsystem.domain.service.InternacaoService;

@RestController
@RequestMapping(value = "/internacoes")
public class InternacaoController {

	@Autowired
	private InternacaoService internacaoService;
	
	@Autowired
	private InternacaoModelAssembler internacaoModelAssembler;

	@Autowired
	private InternacaoInputDisassembler internacaoInputDisassembler; 

	@GetMapping
	public Page<InternacaoModel> listar(Pageable pageable) {
		pageable = traduzirPageable(pageable);
		Page<Internacao> todasInternacaos = internacaoService.listar(pageable);
		List<InternacaoModel> internacaosModel = internacaoModelAssembler.toCollectionModel(todasInternacaos.getContent());
		Page<InternacaoModel> internacaoModelPage = new PageImpl<InternacaoModel>(internacaosModel, pageable, todasInternacaos.getTotalElements());
		return internacaoModelPage;
	}

	@GetMapping("/{internacaoId}")
	public InternacaoModel buscar(@PathVariable Long internacaoId) {
	    Internacao internacao = internacaoService.buscarOuFalhar(internacaoId);
	    
	    return internacaoModelAssembler.toModel(internacao);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public InternacaoModel adicionar(@RequestBody @Valid InternacaoInput internacaoInput) {
	    try {
	        Internacao internacao = internacaoInputDisassembler.toDomainObject(internacaoInput);
	        
	        internacao = internacaoService.salvar(internacao);
	        
	        return internacaoModelAssembler.toModel(internacao);
	    } catch (PacienteNaoEncontradoException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}
	
	@PutMapping("/{internacaoId}")
	public InternacaoModel atualizar(@PathVariable Long internacaoId,
	        @RequestBody @Valid InternacaoInput internacaoInput) {
	    try {
	    	
	    	Internacao novaInternacao = internacaoInputDisassembler.toDomainObject(internacaoInput);
	    	novaInternacao = internacaoService.atualizar(internacaoId, novaInternacao);
	    	
	        
	        return internacaoModelAssembler.toModel(novaInternacao);
	    } catch (PacienteNaoEncontradoException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}


	@DeleteMapping("/{internacaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long internacaoId) {
		internacaoService.excluir(internacaoId);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"id", "id"
				
		);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}

}
