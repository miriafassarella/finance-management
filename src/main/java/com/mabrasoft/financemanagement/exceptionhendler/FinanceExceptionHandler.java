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

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice //it observes the entire application
public class FinanceExceptionHandler extends ResponseEntityExceptionHandler {

		@Autowired
		private MessageSource messageSource;

		@Override
		protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
				HttpHeaders headers, HttpStatusCode status, WebRequest request) {
			String developerMessage = ex.getCause().toString();
			String userMessage = messageSource.getMessage("message.invalid", null, LocaleContextHolder.getLocale());
			Error error = new Error(userMessage, developerMessage);
			return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
		}
		
		@ExceptionHandler({NoSuchElementException.class})
		public ResponseEntity<Object> handleEmptyResultDataException(NoSuchElementException ex, WebRequest request) {
			String developerMessage = ex.toString();
			String userMessage = messageSource.getMessage("resource.not-found", null, LocaleContextHolder.getLocale());
			Error error = new Error(userMessage, developerMessage);
			return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		}
		
		@ExceptionHandler({DataIntegrityViolationException.class})
		public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
			String developerMessage = ExceptionUtils.getRootCauseMessage(ex);
			String userMessage = messageSource.getMessage("operation.not-allowed", null, LocaleContextHolder.getLocale());
			Error error =  new Error(userMessage, developerMessage);
			return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		}
			
	}

