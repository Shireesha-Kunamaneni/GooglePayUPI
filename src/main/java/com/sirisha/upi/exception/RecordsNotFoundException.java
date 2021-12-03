package com.sirisha.upi.exception;

public class RecordsNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
final String message;
public RecordsNotFoundException(String message) {
	super(message);
	this.message = message;
}

}
