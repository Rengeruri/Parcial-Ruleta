package com.ibm.academia.apirest.exceptions;

public class BadRequestException extends RuntimeException{
	
	public BadRequestException(String message) {
		super(message);
	}
	
	
	private static final long serialVersionUID = 9094823650289796312L;
	
}