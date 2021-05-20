package com.github.youssfbr.logistica.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.youssfbr.logistica.domain.model.Cliente;
import com.github.youssfbr.logistica.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		return ResponseEntity.ok(repository.findAll());
	}	
	
	@GetMapping("{id}")
	public ResponseEntity<Cliente> findByID(@PathVariable Long id) {
		return repository.findById(id)				
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public ResponseEntity<Cliente> insert(@RequestBody Cliente entity) {
		entity = repository.save(entity);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getId()).toUri();
				
		return ResponseEntity.created(uri).body(entity);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente entity) {
		
		if (!repository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		entity.setId(id);		
		entity = repository.save(entity);		
				
		return ResponseEntity.ok(entity);
	}	
	
}