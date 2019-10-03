package com.abeldevelop.blog.blogcategoryapi.exception.client;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.abeldevelop.blog.blogcategoryapi.exception.AbelDevelopException;

public class BadRequestException extends AbelDevelopException {

	private static final long serialVersionUID = -4094493114956396096L;

	private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
	
	public BadRequestException(String message) {
		super(message, STATUS);
	}

	public BadRequestException(String message, List<Object> arguments) {
		super(message, STATUS, arguments);
	}
	
	public BadRequestException(String message, Throwable cause) {
		super(message, STATUS, cause);
	}
	
	public BadRequestException(String message, List<Object> arguments, Throwable cause) {
		super(message, STATUS, arguments, cause);
	}
}