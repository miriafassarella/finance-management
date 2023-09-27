package com.mabrasoft.financemanagement.exceptionhendler;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Error {
	private String userMessage;
	private String developerMessage;
	
	public Error(String userMessage, String developerMessage) {
		this.userMessage = userMessage;
		this.developerMessage = developerMessage;

}
}