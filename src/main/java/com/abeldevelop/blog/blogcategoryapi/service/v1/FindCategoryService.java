package com.abeldevelop.blog.blogcategoryapi.service.v1;

import com.abeldevelop.blog.blogcategoryapi.domain.Category;

public interface FindCategoryService {

	public Category executeFindByCode(String code);
	
}
