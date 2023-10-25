package com.mabrasoft.financemanagement.exceptionhendler;

import lombok.Getter;

@Getter
public enum ErrorType {

	ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
	ENTITY_IN_USE("/entity-in-use", "The entity is in use");
	
	private String title;
	private String uri;
	
	ErrorType(String path, String title){
		this.uri = "https://financemanagement.qc.ca/" + path;
		this.title = title;
	}
}
