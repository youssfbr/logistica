package com.github.youssfbr.logistica.domain.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.youssfbr.logistica.domain.models.Cliente;
import com.github.youssfbr.logistica.domain.repositories.ClienteRepository;
import com.github.youssfbr.logistica.domain.services.exceptions.DatabaseException;
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
		return repository.save(cliente);
	}
	
	@Transactional
	public Cliente update(Long id, Cliente cliente) {
		try {
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
	
}
