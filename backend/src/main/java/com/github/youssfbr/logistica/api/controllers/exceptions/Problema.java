package com.github.youssfbr.logistica.api.controllers.exceptions;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Problema {
	
	private OffsetDateTime dataHora;
	private Integer status;
	private String erro;	
	
	private List<Campo> campos;	

}
