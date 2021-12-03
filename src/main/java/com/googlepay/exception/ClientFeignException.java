package com.googlepay.exception;

public class ClientFeignException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String message;
	public ClientFeignException(String message) {
		super();
		this.message = message;
	}
	

}
