package com.sirisha.upi.exception;

public class UserFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 final String message;
	public UserFoundException(String message) {
		super(message);
		this.message = message;
	}
	

}
