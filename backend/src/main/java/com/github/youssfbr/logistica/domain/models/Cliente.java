package com.github.youssfbr.logistica.domain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.github.youssfbr.logistica.domain.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_cliente")
public class Cliente {

	@NotNull(groups = ValidationGroups.ClienteId.class)
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank	
	@Size(max = 60)
	private String nome;
	
	@NotBlank	
	@Size(max = 255)
	@Email
	private String email;
	
	@NotBlank	
	@Size(max = 20)
	private String telefone;	
	
}
