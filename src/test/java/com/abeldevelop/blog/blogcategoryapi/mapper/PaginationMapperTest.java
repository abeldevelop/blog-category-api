package com.abeldevelop.blog.blogcategoryapi.mapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.abeldevelop.blog.blogcategoryapi.domain.PaginationRequest;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResponse;
import com.abeldevelop.blog.blogcategoryapi.exception.client.custom.PaginationPageException;
import com.abeldevelop.blog.blogcategoryapi.exception.client.custom.PaginationSizeException;
import com.abeldevelop.blog.blogcategoryapi.resource.PaginationResponseResource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PaginationMapperTest {

	private PaginationMapper paginationMapper;
	
	@BeforeEach
	public void setUp() {
		paginationMapper = new PaginationMapper();
	}
	
	@Test
	public void mapToPaginationRequestDefaultPageAndSizeTest() {
		PaginationRequest expectedResutl = PaginationRequest.builder().page(0).size(10).build(); 
		
		PaginationRequest result = paginationMapper.map(null, null);
		
		assertThat(result).isEqualToComparingFieldByField(expectedResutl);
	}
	
	@Test
	public void mapToPaginationRequestWrongPageTest() {
		assertThrows(PaginationPageException.class, () -> paginationMapper.map(0, null));
		
	}
	
	@Test
	public void mapToPaginationRequestWrongSizeTest() {
		assertThrows(PaginationSizeException.class, () -> paginationMapper.map(null, 0));
		
	}
	
	@Test
	public void mapToPaginationRequestTest() {
		PaginationRequest expectedResutl = PaginationRequest.builder().page(0).size(1).build(); 
		
		PaginationRequest result = paginationMapper.map(1, 1);
		
		assertThat(result).isEqualToComparingFieldByField(expectedResutl);
	}
	
	@Test
	public void mapDomainToResourceFirstPageTest() {
		
		PaginationResponse paginationResponse = PaginationResponse.builder().page(0).size(10).numberOfElements(10).totalElements(19).build();
		PaginationResponseResource expectedResutl = PaginationResponseResource.builder()
				.page(1)
				.size(10)
				.numberOfElements(10)
				.totalPages(2)
				.totalElements(19)
				.first(true)
				.last(false)
				.build();
		
		PaginationResponseResource result = paginationMapper.map(paginationResponse);
		
		assertThat(result).isEqualToComparingFieldByField(expectedResutl);
	}
	
	@Test
	public void mapDomainToResourceLastPageTest() {
		
		PaginationResponse paginationResponse = PaginationResponse.builder().page(1).size(10).numberOfElements(9).totalElements(19).build();
		PaginationResponseResource expectedResutl = PaginationResponseResource.builder()
				.page(2)
				.size(10)
				.numberOfElements(9)
				.totalPages(2)
				.totalElements(19)
				.first(false)
				.last(true)
				.build();
		
		PaginationResponseResource result = paginationMapper.map(paginationResponse);
		
		assertThat(result).isEqualToComparingFieldByField(expectedResutl);
	}
}
