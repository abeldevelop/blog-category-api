package com.abeldevelop.blog.blogcategoryapi.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SortRequest {

	private SortDirection direction;
	private String column;

	@Builder
	public SortRequest(SortDirection direction, String column) {
		this.direction = direction;
		this.column = column;
	}
	
}