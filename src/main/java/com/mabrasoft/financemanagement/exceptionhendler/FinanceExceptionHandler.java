package com.mabrasoft.financemanagement.exceptionhendler;

import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice //it observes the entire application
public class FinanceExceptionHandler extends ResponseEntityExceptionHandler {

		public static final String MSG_ERROR_USER
		= "An internal system error has occurred."
				+ " Try again and if the problem persists,"
				+ " contact your system administrator.";
		
		@Override
		protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
			
			ErrorType errorType = ErrorType.MESSAGE_INCOMPREHENSIBLE;
			String detail = ex.getMessage();
			
			Error error = createErrorBuilder(status, errorType, detail)
					.userMessage(MSG_ERROR_USER)
					.build();
		
			return handleExceptionInternal(ex, error, headers, status, request);
		}
		
		@ExceptionHandler({DataIntegrityViolationException.class})
		public ResponseEntity<?> handleDataIntegrity(DataIntegrityViolationException ex, WebRequest request){
			
			HttpStatusCode status = HttpStatus.BAD_REQUEST;
			ErrorType errorType = ErrorType.ENTITY_IN_USE;
			String detail = ex.getMessage();
			
			Error error = createErrorBuilder(status, errorType, detail)
					.userMessage(MSG_ERROR_USER)
					.build();
			
			return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
			
		}
		
		@ExceptionHandler(NoSuchElementException.class)
		public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException ex, WebRequest request){
			
			HttpStatusCode status = HttpStatus.NOT_FOUND;
			ErrorType errorType = ErrorType.ENTITY_NOT_FOUND;
			String detail = ex.getMessage();
			
			Error error = createErrorBuilder(status, errorType, detail)
					.userMessage(MSG_ERROR_USER)
					.build();
			
			
			return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
		}
		
		@Override
		protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
			
			if(body == null) {
			body = Error.builder()
					.title(ex.getMessage())
					.status(status.value())
					.build();
			}else if(body instanceof String) {
				body = Error.builder()
						.title((String) body)
						.status(status.value())
						.build();
			}
		return super.handleExceptionInternal(ex, body, headers, status, request);
		}
		
		private Error.ErrorBuilder createErrorBuilder(HttpStatusCode status,
				ErrorType errorType, String detail) {
			return Error.builder()
					.status(status.value())
					.type(errorType.getUri())
					.title(errorType.getTitle())
					.detail(detail);
		}
		
	}

