package com.github.youssfbr.logistica.domain.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.youssfbr.logistica.domain.models.Cliente;
import com.github.youssfbr.logistica.domain.models.Entrega;
import com.github.youssfbr.logistica.domain.models.enums.StatusEntrega;
import com.github.youssfbr.logistica.domain.repositories.EntregaRepository;
import com.github.youssfbr.logistica.domain.services.exceptions.DatabaseException;
import com.github.youssfbr.logistica.domain.services.exceptions.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaService {

	private final EntregaRepository repository;
	private final ClienteService clienteService;
	
	@Transactional(readOnly = true)
	public List<Entrega> findAll() {	
		return repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Entrega findById(Long id) {		
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id " + id + " não encontrado!"));		
	}
	
	@Transactional
	public Entrega insert(Entrega entrega) {
		
			Cliente cliente = clienteService.buscarCliente(entrega.getCliente().getId());
			
			entrega.setCliente(cliente);
			entrega.setStatus(StatusEntrega.PENDENTE);
			entrega.setDataPedido(LocalDateTime.now());
			
			return repository.save(entrega);
	}
	
	@Transactional
	public Entrega update(Long id, Entrega entrega) {
		try {
				
			Entrega entity = repository.getOne(id);
		//	entity.setNome(entrega.getNome());
		//	entity.setEmail(entrega.getEmail());
		//	entity.setTelefone(entrega.getTelefone());			
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
