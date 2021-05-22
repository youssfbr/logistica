package com.github.youssfbr.logistica.domain.services.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ClienteNaoEncontradoException(String msg) {
		super(msg);	
	}
}
