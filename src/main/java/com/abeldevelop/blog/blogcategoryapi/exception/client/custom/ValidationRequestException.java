package com.abeldevelop.blog.blogcategoryapi.exception.client.custom;

import java.util.List;

import com.abeldevelop.blog.blogcategoryapi.exception.client.BadRequestException;

public class ValidationRequestException extends BadRequestException {

	private static final long serialVersionUID = -321979208013613486L;

	public ValidationRequestException(String message) {
		super(message);
	}

	public ValidationRequestException(String message, List<Object> arguments) {
		super(message, arguments);
	}
	
	public ValidationRequestException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ValidationRequestException(String message, List<Object> arguments, Throwable cause) {
		super(message, arguments, cause);
	}
	
}