package com.googlepay.exception;

public class StatementsNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	final String message;

	public StatementsNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	

}
