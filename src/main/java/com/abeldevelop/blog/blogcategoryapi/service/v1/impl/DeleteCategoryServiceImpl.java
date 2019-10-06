package com.abeldevelop.blog.blogcategoryapi.service.v1.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abeldevelop.blog.blogcategoryapi.component.ErrorMessageProperties;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
import com.abeldevelop.blog.blogcategoryapi.exception.category.CategoryNotFoundException;
import com.abeldevelop.blog.blogcategoryapi.repository.CategoryRepository;
import com.abeldevelop.blog.blogcategoryapi.service.v1.DeleteCategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DeleteCategoryServiceImpl implements DeleteCategoryService {
	
	private final CategoryRepository categoryRepository;
	
	@Override
	public void executeDeleteByCode(String code) {
		checkIfCategoryExist(code);
		
		categoryRepository.executeDeleteById(code);
	}

	private void checkIfCategoryExist(String code) {
		Optional<CategoryEntity> categoryEntityOptional = categoryRepository.executeFindById(code);
		if(!categoryEntityOptional.isPresent()) {
			throw new CategoryNotFoundException(ErrorMessageProperties.CATEGORY_NOT_FOUND, Arrays.asList(code));
		}
	}
}
