package com.abeldevelop.blog.blogcategoryapi.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.abeldevelop.blog.blogcategoryapi.interfaces.ErrorMessages;

@Component
public class ErrorMessageProperties implements ErrorMessages {

	public static final String CATEGORY_PAGINATION_RESPONSE_RESOURCE_PAGINATION_RESPONSE_RESOURCE_NOT_NULL = "categoryPaginationResponseResourcePaginationResponseResourceNotNull";
	
	public static final String CATEGORY_CODE_NOT_NULL = "categoryCodeNotNull";
	public static final String CATEGORY_CODE_SIZE = "categoryCodeSize";
	public static final String CATEGORY_NAME_NOT_NULL = "categoryNameNotNull";
	public static final String CATEGORY_NAME_SIZE = "categoryNameSize";
	
	public static final String PAGINATION_MIN_PAGE_ERROR = "paginationMinPageError";
	public static final String PAGINATION_MIN_SIZE_ERROR = "paginationMinSizeError";
	private Map<String, String> errorMessages;
	
	@Override
	public Optional<String> getErrorMessage(String code) {
		init();
		if(errorMessages.containsKey(code)) {
			return Optional.of(errorMessages.get(code));
		} else {
			return Optional.empty();
		}
	}

	
	private void init() {
		if(errorMessages != null) {
			return;
		}
		errorMessages = new HashMap<>();
		addMessagesToMap();
	}
	
	
	private void addMessagesToMap() {
		errorMessages.put(CATEGORY_PAGINATION_RESPONSE_RESOURCE_PAGINATION_RESPONSE_RESOURCE_NOT_NULL, "The pagination cannot be null");
		
		errorMessages.put(CATEGORY_CODE_NOT_NULL, "The category code cannot be null");
		errorMessages.put(CATEGORY_CODE_SIZE, "The category name must be between 3 and 25 characters");
		errorMessages.put(CATEGORY_NAME_NOT_NULL, "The category name cannot be null");
		errorMessages.put(CATEGORY_NAME_SIZE, "The category name must be between 3 and 25 characters");

		errorMessages.put(PAGINATION_MIN_PAGE_ERROR, "The page number can not be less than {}");
		errorMessages.put(PAGINATION_MIN_SIZE_ERROR, "The page size can not be less than {}");
		
	}
}
