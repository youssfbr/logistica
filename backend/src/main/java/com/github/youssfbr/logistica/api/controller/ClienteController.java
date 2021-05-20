package com.github.youssfbr.logistica.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.youssfbr.logistica.domain.model.Cliente;
import com.github.youssfbr.logistica.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository repository;
	
	@GetMapping
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
}