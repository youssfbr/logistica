package com.github.youssfbr.logistica.api.controller.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Problema {
	
	private LocalDateTime dataHora;
	private Integer status;
	private String erro;	
	
	private List<Campo> campos;	

}
