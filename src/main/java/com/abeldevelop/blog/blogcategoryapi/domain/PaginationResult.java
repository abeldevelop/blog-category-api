package com.abeldevelop.blog.blogcategoryapi.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class PaginationResult <T> {

	private PaginationResponse pagination;
	private List<T> results;
	
}