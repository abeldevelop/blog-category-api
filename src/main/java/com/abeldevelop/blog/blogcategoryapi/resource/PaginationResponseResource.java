package com.abeldevelop.blog.blogcategoryapi.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description="Resource with pagination information")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PaginationResponseResource {

	@ApiModelProperty(notes="Number of the page", example="1", required = true, position = 0)
	private int page;
	
	@ApiModelProperty(notes="Number of records per page", example="10", required = true, position = 1)
	private int size;
	
	@ApiModelProperty(notes="Number of records returned", example="20", required = true, position = 2)
	private int numberOfElements;
	
	@ApiModelProperty(notes="Total of pages", example="5", required = true, position = 3)
	private int totalPages;
	
	@ApiModelProperty(notes="Total of elements", example="100", required = true, position = 4)
	private long totalElements;
	
}
