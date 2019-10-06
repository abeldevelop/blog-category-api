package com.abeldevelop.blog.blogcategoryapi.service.v1.impl;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.abeldevelop.blog.blogcategoryapi.component.ErrorMessageProperties;
import com.abeldevelop.blog.blogcategoryapi.domain.Category;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
import com.abeldevelop.blog.blogcategoryapi.exception.category.CategoryExistException;
import com.abeldevelop.blog.blogcategoryapi.exception.category.CategoryNotFoundException;
import com.abeldevelop.blog.blogcategoryapi.mapper.CategoryMapper;
import com.abeldevelop.blog.blogcategoryapi.repository.CategoryRepository;
import com.abeldevelop.blog.blogcategoryapi.service.v1.UpdateCategoryService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UpdateCategoryServiceImpl implements UpdateCategoryService {

	private final CategoryRepository categoryRepository;
	private final CategoryMapper categoryMapper;
	
	
	@Override
	public Category executeUpdate(String code, Category category) {
		checkIfCategoryExist(code);
		checkIfNewCategoryExist(category);
		checkIfSubcategoryHaveTheCategory(code);
		checkIfPostHaveTheCategory(code);
		
		categoryRepository.executeDeleteById(code);
		return categoryMapper.mapEntityToDomain(categoryRepository.executeSave(categoryMapper.mapDomainToEntity(category)));
	}
	
	private void checkIfCategoryExist(String code) {
		Optional<CategoryEntity> categoryEntityOptional = categoryRepository.executeFindById(code);
		if(!categoryEntityOptional.isPresent()) {
			throw new CategoryNotFoundException(ErrorMessageProperties.CATEGORY_NOT_FOUND, Arrays.asList(code));
		}
	}
	
	private void checkIfNewCategoryExist(Category category) {
		Optional<CategoryEntity> categoryEntityOptional = categoryRepository.executeFindById(category.getCode());
		if(categoryEntityOptional.isPresent()) {
			throw new CategoryExistException(ErrorMessageProperties.CATEGORY_CODE_EXIST, Arrays.asList(category.getName()));
		}
	}
	
	private void checkIfSubcategoryHaveTheCategory(String code) {
		//TODO Call to MS subcategory and if exist subcategories, can't delete the category
	}
	
	private void checkIfPostHaveTheCategory(String code) {
		//TODO Call to MS post and if exist posts, can't delete the category
	}
}
