package com.abeldevelop.blog.blogcategoryapi.exception;

import java.util.List;

public class PaginationSizeException extends RuntimeException {

	private static final long serialVersionUID = -7335321389018695434L;

	public PaginationSizeException(String message) {
		super(message);
	}
	
	public PaginationSizeException(String message, List<Object> arguments) {
		super(message);
	}
	
	public PaginationSizeException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public PaginationSizeException(String message, Throwable cause, List<Object> arguments) {
		super(message, cause);
	}
	
}
