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

import com.github.youssfbr.logistica.api.dto.EntregaDTO;
import com.github.youssfbr.logistica.domain.models.Entrega;
import com.github.youssfbr.logistica.domain.services.EntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
		
	private final EntregaService service;
	
	@GetMapping
	public ResponseEntity<List<EntregaDTO>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}	
	
	@GetMapping("{id}")
	public ResponseEntity<EntregaDTO> findByID(@PathVariable Long id) {		 
		 return ResponseEntity.ok(service.findById(id));
	}
	
	@PostMapping
	public ResponseEntity<Entrega> insert(@Valid @RequestBody Entrega entity) {
		
		entity = service.insert(entity);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(entity.getId()).toUri();
				
		return ResponseEntity.created(uri).body(entity);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Entrega> update(@PathVariable Long id, @Valid @RequestBody Entrega entity) {
		
		entity = service.update(id, entity);		
				
		return ResponseEntity.ok(entity);
	}	
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {			
			
		service.delete(id);		
				
		return ResponseEntity.noContent().build();
	}	
	
}