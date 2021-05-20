package com.github.youssfbr.logistica.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.youssfbr.logistica.domain.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	@PersistenceContext
	private EntityManager manager;	

	@GetMapping
	public List<Cliente> findAll() {
		return manager.createQuery("from Cliente", Cliente.class)
				.getResultList();
	}
	
}