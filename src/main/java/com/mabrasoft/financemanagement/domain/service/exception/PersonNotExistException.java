package com.mabrasoft.financemanagement.domain.service.exception;

public class PersonNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PersonNotExistException(String message) {
		super(message);
	}

}
