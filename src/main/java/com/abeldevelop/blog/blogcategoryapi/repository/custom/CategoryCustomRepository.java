package com.abeldevelop.blog.blogcategoryapi.repository.custom;

import com.abeldevelop.blog.blogcategoryapi.domain.PageRequest;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResult;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;

public interface CategoryCustomRepository {

	public PaginationResult<CategoryEntity> executeFindAll(PageRequest pageRequest, String query);
	
}
