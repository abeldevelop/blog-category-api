package com.abeldevelop.blog.blogcategoryapi.config;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.helpers.MessageFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.abeldevelop.blog.blogcategoryapi.exception.AbelDevelopException;
import com.abeldevelop.blog.blogcategoryapi.interfaces.ErrorMessages;
import com.abeldevelop.blog.blogcategoryapi.mapper.StackTraceMapper;
import com.abeldevelop.blog.blogcategoryapi.resource.ErrorResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.ErrorResponseResource.ErrorResponseResourceBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

private static final String ERROR_LOG_PREFIX = "ErrorResponseResource: {}";
	
	@Value("${add-exception-sensitive-information}")
	private boolean addExceptionSensitiveInformation;
	
	private final ErrorMessages errorMessages;
	private final StackTraceMapper stackTraceMapper;
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		ResponseEntity<Object> responseEntityError = handleResponseException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		log.error(ERROR_LOG_PREFIX, responseEntityError.getBody());
		return responseEntityError;
	}
	
	@ExceptionHandler(AbelDevelopException.class)
	public final ResponseEntity<Object> handleNotFoundException(AbelDevelopException ex, WebRequest request) {
		ResponseEntity<Object> responseEntityError = handleResponseException(ex, ex.getStatus());
		if(ex.getStatus().equals(HttpStatus.INTERNAL_SERVER_ERROR)) {
			log.error(ERROR_LOG_PREFIX, responseEntityError.getBody());
		} else {
			log.warn(ERROR_LOG_PREFIX, responseEntityError.getBody());
		}
		return responseEntityError;
	}
	
	private ResponseEntity<Object> handleResponseException(Exception ex, HttpStatus status) {
		return new ResponseEntity<>(createErrorResponseResource(ex, status), status);
	}
	
	private ErrorResponseResource createErrorResponseResource(Exception ex, HttpStatus status) {
		ErrorResponseResourceBuilder errorResponseResourceBuilder = ErrorResponseResource.builder();
		errorResponseResourceBuilder
					.timestamp(LocalDateTime.now())
					.status(status.value())
					.error(status.getReasonPhrase());	
		
		if(ex instanceof AbelDevelopException && ((AbelDevelopException) ex).getArguments() != null) {
			errorResponseResourceBuilder.message(messageFormatter(((AbelDevelopException) ex).getMessage(), ((AbelDevelopException) ex).getArguments()));
		} else {
			errorResponseResourceBuilder.message(getMessageFromProperties(ex.getMessage()));
		}
		
		addSensitiveInformation(ex, errorResponseResourceBuilder);
		return errorResponseResourceBuilder.build();
	}
	
	private String getMessageFromProperties(String code) {
		return errorMessages.getErrorMessage(code).orElse(code);
	}
	
	private String messageFormatter(String message, List<Object> arguments) {
		return MessageFormatter.arrayFormat(getMessageFromProperties(message), arguments.toArray()).getMessage();
	}
	
	private void addSensitiveInformation(Object ex, ErrorResponseResourceBuilder errorResponseResourceBuilder) {
		if(ex instanceof Exception && addExceptionSensitiveInformation) {
			Exception exception = (Exception) ex;
			if(exception.getCause() instanceof Exception) {
				errorResponseResourceBuilder.cause(createErrorResponseResource((Exception) exception.getCause(), HttpStatus.INTERNAL_SERVER_ERROR));
			}
			errorResponseResourceBuilder.detail(ex.getClass().getCanonicalName());
			errorResponseResourceBuilder.stackTrace(stackTraceMapper.map(exception.getStackTrace()));
		}
	}
	
}
