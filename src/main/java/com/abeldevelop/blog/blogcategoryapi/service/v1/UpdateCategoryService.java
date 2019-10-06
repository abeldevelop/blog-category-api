package com.abeldevelop.blog.blogcategoryapi.service.v1;

import com.abeldevelop.blog.blogcategoryapi.domain.Category;

public interface UpdateCategoryService {

	public Category executeUpdate(String code, Category category);
	
}
