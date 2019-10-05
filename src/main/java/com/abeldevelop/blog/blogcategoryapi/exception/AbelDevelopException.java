package com.abeldevelop.blog.blogcategoryapi.exception;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class AbelDevelopException extends RuntimeException implements Serializable{

	private static final long serialVersionUID = -6581330406261507159L;
	private final HttpStatus status;
	private final List<Object> arguments;
	
	public AbelDevelopException(String message, HttpStatus status) {
		super(message);
		this.status = status;
		this.arguments = null;
	}

	public AbelDevelopException(String message, HttpStatus status, List<Object> arguments) {
		super(message);
		this.status = status;
		this.arguments = arguments;
	}
	
	public AbelDevelopException(String message, HttpStatus status, Throwable cause) {
		super(message, cause);
		this.status = status;
		this.arguments = null;
	}
	
	public AbelDevelopException(String message, HttpStatus status, List<Object> arguments, Throwable cause) {
		super(message, cause);
		this.status = status;
		this.arguments = arguments;
	}
	
}