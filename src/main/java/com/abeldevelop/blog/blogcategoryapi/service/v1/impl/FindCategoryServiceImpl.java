package com.abeldevelop.blog.blogcategoryapi.service.v1.impl;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.abeldevelop.blog.blogcategoryapi.component.ErrorMessageProperties;
import com.abeldevelop.blog.blogcategoryapi.domain.Category;
import com.abeldevelop.blog.blogcategoryapi.domain.PageRequest;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResult;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
import com.abeldevelop.blog.blogcategoryapi.exception.category.CategoryNotFoundException;
import com.abeldevelop.blog.blogcategoryapi.mapper.CategoryMapper;
import com.abeldevelop.blog.blogcategoryapi.repository.CategoryRepository;
import com.abeldevelop.blog.blogcategoryapi.service.v1.FindCategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FindCategoryServiceImpl implements FindCategoryService {
	
	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;
	
	@Override
	public Category executeFindByCode(String code) {
		Optional<CategoryEntity> categoryEntityOptional = categoryRepository.executeFindById(code);
		if(!categoryEntityOptional.isPresent()) {
			throw new CategoryNotFoundException(ErrorMessageProperties.CATEGORY_NOT_FOUND, Arrays.asList(code));
		}
		return categoryMapper.mapEntityToDomain(categoryEntityOptional.get());
	}

	@Override
	public PaginationResult<Category> executeFindAll(PageRequest pageRequest, String query) {
		PaginationResult<CategoryEntity> listCategories = categoryRepository.executeFindAll(pageRequest, query);
		return new PaginationResult<>(
				listCategories.getPagination(), 
				listCategories.getResults().stream().map(categoryMapper::mapEntityToDomain).collect(Collectors.toList()));
	}
	
}
