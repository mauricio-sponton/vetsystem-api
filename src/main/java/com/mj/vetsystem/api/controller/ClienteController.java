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

import com.mj.vetsystem.api.assembler.ClienteModelAssembler;
import com.mj.vetsystem.api.assembler.ClienteResumoModelAssembler;
import com.mj.vetsystem.api.disassembler.ClienteInputDisassembler;
import com.mj.vetsystem.api.model.ClienteModel;
import com.mj.vetsystem.api.model.ClienteResumoModel;
import com.mj.vetsystem.api.model.input.ClienteInput;
import com.mj.vetsystem.core.data.PageableTranslator;
import com.mj.vetsystem.domain.exception.CidadeNaoEncontradaException;
import com.mj.vetsystem.domain.exception.NegocioException;
import com.mj.vetsystem.domain.model.Cliente;
import com.mj.vetsystem.domain.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteModelAssembler clienteModelAssembler;
	
	@Autowired
	private ClienteResumoModelAssembler clienteResumoModelAssembler;

	@Autowired
	private ClienteInputDisassembler clienteInputDisassembler; 

	@GetMapping
	public Page<ClienteResumoModel> listar(Pageable pageable) {
		pageable = traduzirPageable(pageable);
		Page<Cliente> todosClientes = clienteService.listar(pageable);
		List<ClienteResumoModel> clientesModel = clienteResumoModelAssembler.toCollectionModel(todosClientes.getContent());
		Page<ClienteResumoModel> clienteModelPage = new PageImpl<ClienteResumoModel>(clientesModel, pageable, todosClientes.getTotalElements());
		return clienteModelPage;
	}

	@GetMapping("/{clienteId}")
	public ClienteModel buscar(@PathVariable Long clienteId) {
	    Cliente cliente = clienteService.buscarOuFalhar(clienteId);
	    
	    return clienteModelAssembler.toModel(cliente);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel adicionar(@RequestBody @Valid ClienteInput clienteInput) {
	    try {
	        Cliente cliente = clienteInputDisassembler.toDomainObject(clienteInput);
	        
	        cliente = clienteService.salvar(cliente);
	        
	        return clienteModelAssembler.toModel(cliente);
	    } catch (CidadeNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}

	@PutMapping("/{clienteId}")
	public ClienteModel atualizar(@PathVariable Long clienteId,
	        @RequestBody @Valid ClienteInput clienteInput) {
	    try {
	        Cliente clienteAtual = clienteService.buscarOuFalhar(clienteId);
	        
	        clienteInputDisassembler.copyToDomainObject(clienteInput, clienteAtual);
	        
	        clienteAtual = clienteService.salvar(clienteAtual);
	        
	        return clienteModelAssembler.toModel(clienteAtual);
	    } catch (CidadeNaoEncontradaException e) {
	        throw new NegocioException(e.getMessage(), e);
	    }
	}

	@DeleteMapping("/{clienteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long clienteId) {
		clienteService.excluir(clienteId);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = Map.of(
				"id", "id",
				"nome", "nome"
		);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}

}
