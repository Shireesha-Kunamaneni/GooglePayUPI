package com.sirisha.upi.exception;

public class BalanceInsufficientException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String message;
	public BalanceInsufficientException(String message) {
		super(message);
		this.message = message;
	}
	

}
