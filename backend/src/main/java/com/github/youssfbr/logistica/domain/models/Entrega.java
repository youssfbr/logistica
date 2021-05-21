package com.github.youssfbr.logistica.domain.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.youssfbr.logistica.domain.models.enums.StatusEntrega;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tb_entrega")
public class Entrega {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@ManyToOne
	private Cliente cliente;
	
	@Embedded
	private Destinatario destinario;
	
	private BigDecimal taxa;
	
	@Enumerated(EnumType.STRING)
	private StatusEntrega status;
	
	private LocalDateTime dataPedido;
	private LocalDateTime dataFinalização;
}
