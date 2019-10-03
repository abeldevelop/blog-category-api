package com.abeldevelop.blog.blogcategoryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class PaginationRequest {

	private Integer page;
	private Integer size;
	
}
