package com.abeldevelop.blog.blogcategoryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@Getter
@ToString
public class PaginationResponse {

	private int page;
	private int size;
	private int numberOfElements;
	private long totalElements;

}