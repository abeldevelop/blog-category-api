package com.abeldevelop.blog.blogcategoryapi.interfaces;

import java.util.Optional;

public interface ErrorMessages {

	public Optional<String> getErrorMessage(String code);
	
}
