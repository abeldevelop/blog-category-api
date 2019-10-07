package com.abeldevelop.blog.blogcategoryapi.service.v1;

import com.abeldevelop.blog.blogcategoryapi.domain.Category;
import com.abeldevelop.blog.blogcategoryapi.domain.PageRequest;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResult;

public interface FindCategoryService {

	public Category executeFindByCode(String code);
	
	public PaginationResult<Category> executeFindAll(PageRequest pageRequest, String query);
	
}
