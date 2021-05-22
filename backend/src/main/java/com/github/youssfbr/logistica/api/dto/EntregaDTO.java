package com.github.youssfbr.logistica.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.github.youssfbr.logistica.domain.models.Entrega;
import com.github.youssfbr.logistica.domain.models.enums.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaDTO {

	private Long id;
	private String nomeCliente;
	private DestinatarioDTO destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;
	
	public EntregaDTO(Entrega entity) {
		id = entity.getId();
		nomeCliente = entity.getCliente().getNome();
		destinatario = new DestinatarioDTO();
		destinatario.setNome(entity.getDestinatario().getNome());
		destinatario.setLogradouro(entity.getDestinatario().getLogradouro());
		destinatario.setNumero(entity.getDestinatario().getNumero());
		destinatario.setComplemento(entity.getDestinatario().getComplemento());
		destinatario.setBairro(entity.getDestinatario().getBairro());
		
		taxa = entity.getTaxa();
		status = entity.getStatus();
		dataPedido = entity.getDataPedido();				
		dataFinalizacao = entity.getDataFinalizacao();	
	}
	
}
