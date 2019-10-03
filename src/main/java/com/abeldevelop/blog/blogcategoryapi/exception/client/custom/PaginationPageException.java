package com.abeldevelop.blog.blogcategoryapi.exception.client.custom;

import java.util.List;

import com.abeldevelop.blog.blogcategoryapi.exception.client.BadRequestException;

public class PaginationPageException extends BadRequestException {

	private static final long serialVersionUID = -7238073841537015251L;

	public PaginationPageException(String message) {
		super(message);
	}
	
	public PaginationPageException(String message, List<Object> arguments) {
		super(message, arguments);
	}
	
	public PaginationPageException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PaginationPageException(String message, Throwable cause, List<Object> arguments) {
		super(message, arguments, cause);
	}
	
}
