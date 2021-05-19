package com.github.youssfbr.logistica.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.youssfbr.logistica.domain.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@GetMapping
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Alisson");
		cliente1.setTelefone("85 99999-11111");
		cliente1.setEmail("youssfbr@gmail.com");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Link da Silva");
		cliente2.setTelefone("85 99999-34567");
		cliente2.setEmail("link@gmail.com");
		
		return Arrays.asList(cliente1, cliente2);
	}
	
}