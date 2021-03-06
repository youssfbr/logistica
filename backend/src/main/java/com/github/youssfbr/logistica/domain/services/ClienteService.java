package com.github.youssfbr.logistica.domain.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.youssfbr.logistica.domain.models.Cliente;
import com.github.youssfbr.logistica.domain.repositories.ClienteRepository;
import com.github.youssfbr.logistica.domain.services.exceptions.ClienteNaoEncontradoException;
import com.github.youssfbr.logistica.domain.services.exceptions.DatabaseException;
import com.github.youssfbr.logistica.domain.services.exceptions.EmailException;
import com.github.youssfbr.logistica.domain.services.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {

	private final ClienteRepository repository;
	
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {	
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {		
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não encontrado!"));		
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {	
		
		emailCheck(cliente);		
		
		return repository.save(cliente);
	}
	
	@Transactional
	public Cliente update(Long id, Cliente cliente) {
		try {
			
			emailCheck(cliente);
			
			Cliente entity = repository.getOne(id);
			entity.setNome(cliente.getNome());
			entity.setEmail(cliente.getEmail());
			entity.setTelefone(cliente.getTelefone());			
			entity = repository.save(entity);
			
			return entity;
		} 
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id " + id + " não encontrado!");
		}
	}	
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} 
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id " + id + " não encontrado!");
		} 
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Um recurso está associado a outro recurso! Não é possível deletar!");
		}		
	}
	
	public Cliente buscarCliente(Long clienteId) {
		
		return repository.findById(clienteId)
				.orElseThrow(() -> new ClienteNaoEncontradoException("Cliente com Id " 
						+ clienteId + " não encontrado!"));
	}
	
	private void emailCheck(Cliente cliente) {
		
		boolean emailEmUso =  repository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new EmailException("Já existe um cliente cadastrado com esse e-mail.");
		}
	}
	
}
