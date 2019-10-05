package com.abeldevelop.blog.blogcategoryapi.exception.client;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.abeldevelop.blog.blogcategoryapi.exception.AbelDevelopException;

public class BadRequestException extends AbelDevelopException {

	private static final long serialVersionUID = -4094493114956396096L;

	private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
	
	protected BadRequestException(String message) {
		super(message, STATUS);
	}

	protected BadRequestException(String message, List<Object> arguments) {
		super(message, STATUS, arguments);
	}
	
	protected BadRequestException(String message, Throwable cause) {
		super(message, STATUS, cause);
	}
	
	protected BadRequestException(String message, List<Object> arguments, Throwable cause) {
		super(message, STATUS, arguments, cause);
	}
}