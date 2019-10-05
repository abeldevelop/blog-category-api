package com.abeldevelop.blog.blogcategoryapi.exception.server;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.abeldevelop.blog.blogcategoryapi.exception.AbelDevelopException;

public class InternalServerErrorException extends AbelDevelopException {

	
	private static final long serialVersionUID = -7911070580110933142L;
	
	private static final HttpStatus STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

	protected InternalServerErrorException(String message) {
		super(message, STATUS);
	}

	protected InternalServerErrorException(String message, List<Object> arguments) {
		super(message, STATUS, arguments);
	}

	protected InternalServerErrorException(String message, Throwable cause) {
		super(message, STATUS, cause);
	}

	protected InternalServerErrorException(String message, List<Object> arguments, Throwable cause) {
		super(message, STATUS, arguments, cause);
	}
}