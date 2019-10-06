package com.abeldevelop.blog.blogcategoryapi.repository;

import java.util.Optional;

import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;

public interface CategoryRepository {

	public CategoryEntity executeSave(CategoryEntity categoryEntity);
	
	public Optional<CategoryEntity> executeFindById(String id);
	
	public void executeDeleteById(String code);
	
}
