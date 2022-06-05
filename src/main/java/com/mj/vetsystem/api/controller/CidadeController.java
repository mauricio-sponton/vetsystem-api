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

import com.mj.vetsystem.api.assembler.CidadeModelAssembler;
import com.mj.vetsystem.api.disassembler.CidadeInputDisassembler;
import com.mj.vetsystem.api.model.CidadeModel;
import com.mj.vetsystem.api.model.input.CidadeInput;
import com.mj.vetsystem.core.data.PageableTranslator;
import com.mj.vetsystem.domain.exception.EstadoNaoEncontradoException;
import com.mj.vetsystem.domain.exception.NegocioException;
import com.mj.vetsystem.domain.model.Cidade;
import com.mj.vetsystem.domain.service.CidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;

	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler; 

	@GetMapping
	public Page<CidadeModel> listar(Pageable pageable) {
		pageable = traduzirPageable(pageable);
		Page<Cidade> todasCidades = cidadeService.listar(pageable);
		List<CidadeModel> cidadesModel = cidadeModelAssembler.toCollectionModel(todasCidades.getContent());
		Page<CidadeModel> cidadeModelPage = new PageImpl<CidadeModel>(cidadesModel, pageable, todasCidades.getTotalElements());
		return cidadeModelPage;
	}

	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable Long cidadeId) {
	    Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
	    
	    return cidadeModelAssembler.toModel(cidade);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
	    try {
	        Cidade cidade = cidadeInputDisassembler.toDomainObject(cidadeInput);
	        
	        cidade = cidadeService.salvar(cidade);
	        
	        return cidadeModelAssembler.toModel(cidade);
	    } catch (EstadoNaoEncontradoException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}

	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@PathVariable Long cidadeId,
	        @RequestBody @Valid CidadeInput cidadeInput) {
	    try {
	        Cidade cidadeAtual = cidadeService.buscarOuFalhar(cidadeId);
	        
	        cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
	        
	        cidadeAtual = cidadeService.salvar(cidadeAtual);
	        
	        return cidadeModelAssembler.toModel(cidadeAtual);
	    } catch (EstadoNaoEncontradoException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cidadeService.excluir(cidadeId);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"id", "id",
				"nome", "nome",
				"estado.nome", "estado.nome",
				"estado.id", "estado.id"
		);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}

}
