package com.abeldevelop.blog.blogcategoryapi.mapper;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.abeldevelop.blog.blogcategoryapi.component.ErrorMessageProperties;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationRequest;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResponse;
import com.abeldevelop.blog.blogcategoryapi.exception.client.custom.PaginationPageException;
import com.abeldevelop.blog.blogcategoryapi.exception.client.custom.PaginationSizeException;
import com.abeldevelop.blog.blogcategoryapi.resource.PaginationResponseResource;

@Component
public class PaginationMapper {

	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 10;
	private static final Integer MIN_PAGE = 1;
	private static final Integer MIN_PAGE_SIZE = 1;
	private static final Integer ADD_NUMBER_TO_PAGE = 1;
	private static final Integer SUBTRACT_NUMBER_TO_PAGE = 1;
	
	
	public PaginationRequest map(Integer page, Integer size) {
		return PaginationRequest.builder()
				.page(validatePageIn(page))
				.size(validateSizeIn(size))
				.build();
	}
	
	public PaginationResponseResource map(PaginationResponse paginationResponse) {
		return PaginationResponseResource.builder()
				.page(paginationResponse.getPage() + ADD_NUMBER_TO_PAGE)
				.size(paginationResponse.getSize())
				.numberOfElements(paginationResponse.getNumberOfElements())
				.totalPages(calculateTotalPages(paginationResponse.getTotalElements(), paginationResponse.getSize()))
				.totalElements(paginationResponse.getTotalElements())
				.first(calculateIsFirstPage(paginationResponse.getPage() + ADD_NUMBER_TO_PAGE))
				.last(calculateIsLastPage(paginationResponse.getTotalElements(), paginationResponse.getSize(), paginationResponse.getPage() + ADD_NUMBER_TO_PAGE))
				.build();
	}
	
	private Integer validatePageIn(Integer page) {
		if(page == null) {
			return DEFAULT_PAGE_NUMBER;
		} else if(page.intValue() < MIN_PAGE) {
			throw new PaginationPageException(ErrorMessageProperties.PAGINATION_MIN_PAGE_ERROR, Arrays.asList(MIN_PAGE));
		} else {
			return page - SUBTRACT_NUMBER_TO_PAGE;
		}
	}
	
	private Integer validateSizeIn(Integer size) {
		if(size == null) {
			return DEFAULT_PAGE_SIZE;
		} else if(size.intValue() < MIN_PAGE_SIZE) {
			throw new PaginationSizeException(ErrorMessageProperties.PAGINATION_MIN_SIZE_ERROR, Arrays.asList(MIN_PAGE_SIZE));
		} else {
			return size;
		}
	}
	
	private int calculateTotalPages(long totalElements, int size) {
		return (int) Math.ceil((double)totalElements / (double)size);
	}
	
	private boolean calculateIsFirstPage(int page) {
		return MIN_PAGE == page;
	}
	
	private boolean calculateIsLastPage(long totalElements, int size, int page) {
		return (calculateTotalPages(totalElements, size) == page);
	}
}
