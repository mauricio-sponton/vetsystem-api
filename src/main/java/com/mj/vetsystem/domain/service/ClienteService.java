package com.mj.vetsystem.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mj.vetsystem.domain.exception.ClienteNaoEncontradoException;
import com.mj.vetsystem.domain.exception.EntidadeEmUsoException;
import com.mj.vetsystem.domain.model.Cidade;
import com.mj.vetsystem.domain.model.Cliente;
import com.mj.vetsystem.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	private static final String MSG_CLIENTE_EM_USO = "Cliente de código %d não pode ser removido, pois está em uso";

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private CidadeService cidadeService;
	
	public Page<Cliente> listar(Pageable pageable){
		return clienteRepository.findAll(pageable);
	}

	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		Long cidadeId = cliente.getEndereco().getCidade().getId();
		Cidade cidade = cidadeService.buscarOuFalhar(cidadeId);
		
		cliente.getEndereco().setCidade(cidade);
		
		return clienteRepository.save(cliente);
	}

	@Transactional
	public void excluir(Long clienteId) {
		try {
			clienteRepository.deleteById(clienteId);
			clienteRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new ClienteNaoEncontradoException(clienteId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_CLIENTE_EM_USO, clienteId));
		}
	}

	public Cliente buscarOuFalhar(Long clienteId) {
		return clienteRepository.findById(clienteId).orElseThrow(
				() -> new ClienteNaoEncontradoException(clienteId));
	}
}
