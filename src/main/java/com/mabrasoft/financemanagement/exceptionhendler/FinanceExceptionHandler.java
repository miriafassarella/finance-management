package com.mabrasoft.financemanagement.exceptionhendler;


import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice //it observes the entire application
public class FinanceExceptionHandler extends ResponseEntityExceptionHandler {

	
		
		@ExceptionHandler({DataIntegrityViolationException.class})
		public ResponseEntity<?> treatDataIntegrity(DataIntegrityViolationException ex, WebRequest request){
			return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
			
		}
		
		@Override
		protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
			
			body = Error.builder()
					.dateHour(LocalDateTime.now())
					.message(ex.getMessage())
					.build();
		
		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
		}
			
	}

