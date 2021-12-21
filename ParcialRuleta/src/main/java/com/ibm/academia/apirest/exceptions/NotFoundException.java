package com.ibm.academia.apirest.exceptions;

public class NotFoundException extends RuntimeException{
	public NotFoundException(String message) {
		super(message);
	}
	
	private static final long serialVersionUID = -6769032736375478607L;
}
