package com.abeldevelop.blog.blogcategoryapi.exception;

import java.util.List;

public class PaginationPageException extends RuntimeException {

	private static final long serialVersionUID = -7335321389018695434L;

	public PaginationPageException(String message) {
		super(message);
	}
	
	public PaginationPageException(String message, List<Object> arguments) {
		super(message);
	}
	
	public PaginationPageException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PaginationPageException(String message, Throwable cause, List<Object> arguments) {
		super(message, cause);
	}
	
}
