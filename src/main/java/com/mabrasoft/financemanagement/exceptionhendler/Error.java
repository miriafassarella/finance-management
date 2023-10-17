package com.mabrasoft.financemanagement.exceptionhendler;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Error {
	private LocalDateTime dateHour;
	private String message;
	
	
}