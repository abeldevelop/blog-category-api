package com.abeldevelop.blog.blogcategoryapi.repository.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.abeldevelop.blog.blogcategoryapi.domain.PageRequest;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResult;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
import com.abeldevelop.blog.blogcategoryapi.repository.CategoryRepository;
import com.abeldevelop.blog.blogcategoryapi.repository.custom.CategoryCustomRepository;
import com.abeldevelop.blog.blogcategoryapi.repository.springdata.CategorySpringDataRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

	private final CategorySpringDataRepository categorySpringDataRepository;
	private final CategoryCustomRepository categoryCustomRepository;
	
	@Override
	public CategoryEntity executeSave(CategoryEntity categoryEntity) {
		return categorySpringDataRepository.save(categoryEntity);
	}

	@Override
	public Optional<CategoryEntity> executeFindById(String id) {
		return categorySpringDataRepository.findById(id);
	}
	
	@Override
	public void executeDeleteById(String code) {
		categorySpringDataRepository.deleteById(code);
	}

	@Override
	public PaginationResult<CategoryEntity> executeFindAll(PageRequest pageRequest, String query) {
		return categoryCustomRepository.executeFindAll(pageRequest, query);
	}
	
}
