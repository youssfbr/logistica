package com.github.youssfbr.logistica.api.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.github.youssfbr.logistica.domain.models.Cliente;
import com.github.youssfbr.logistica.domain.services.ClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
		
	private final ClienteService service;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}	
	
	@GetMapping("{id}")
	public ResponseEntity<Cliente> findByID(@PathVariable Long id) {		 
		 return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente entity) {
		
		entity = service.insert(entity);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getId()).toUri();
				
		return ResponseEntity.created(uri).body(entity);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody Cliente entity) {
		
		entity = service.update(id, entity);		
				
		return ResponseEntity.ok(entity);
	}	
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {			
			
		service.delete(id);		
				
		return ResponseEntity.noContent().build();
	}	
	
}