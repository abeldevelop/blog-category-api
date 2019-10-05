package com.abeldevelop.blog.blogcategoryapi.exception.client;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.abeldevelop.blog.blogcategoryapi.exception.AbelDevelopException;

public class NotFoundException extends AbelDevelopException {

	private static final long serialVersionUID = 7420272521977814747L;

	private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

	protected NotFoundException(String message) {
		super(message, STATUS);
	}

	protected NotFoundException(String message, List<Object> arguments) {
		super(message, STATUS, arguments);
	}

	protected NotFoundException(String message, Throwable cause) {
		super(message, STATUS, cause);
	}

	protected NotFoundException(String message, List<Object> arguments, Throwable cause) {
		super(message, STATUS, arguments, cause);
	}
}