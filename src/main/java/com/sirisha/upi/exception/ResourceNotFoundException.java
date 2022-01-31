package com.sirisha.upi.exception;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	final String message;

	public ResourceNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	

}
