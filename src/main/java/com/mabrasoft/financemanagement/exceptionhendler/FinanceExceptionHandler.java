package com.mabrasoft.financemanagement.exceptionhendler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.Data;

@ControllerAdvice //it observes the entire application
public class FinanceExceptionHandler extends ResponseEntityExceptionHandler {

		@Autowired
		private MessageSource messageSource;

		@Override
		protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
				HttpHeaders headers, HttpStatusCode status, WebRequest request) {
			String developerMessage = ex.getCause().toString();
			String userMessage = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
			List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
			return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
		}
		
		@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<Error> errors = createListErrors(ex.getBindingResult());
		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
		}
		
		@ExceptionHandler({NoSuchElementException.class})
		public ResponseEntity<Object> handleEmptyResultDataException(NoSuchElementException ex, WebRequest request) {
			String developerMessage = ex.toString();
			String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
			List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
			return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		}
		
		@ExceptionHandler({DataIntegrityViolationException.class})
		public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
			String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
			String userMessage = messageSource.getMessage("operation.not-allowed", null, LocaleContextHolder.getLocale());
			List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));
			return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		}
		
		
		//Sent a list of errors
		private List<Error> createListErrors(BindingResult bindingResult){
			List<Error> errors = new ArrayList<>();
			
			for(FieldError fieldError : bindingResult.getFieldErrors()) {
				String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
				String developerMessage = fieldError.toString();
				errors.add(new Error(userMessage, developerMessage));
			}
			return errors;
		}
		
		@Data
		public static class Error {
			
			private String userMessage;
			private String developerMessage;
			
			public Error(String userMessage, String developerMessage) {
				this.userMessage = userMessage;
				this.developerMessage = developerMessage;
				
			}
		}
	}

