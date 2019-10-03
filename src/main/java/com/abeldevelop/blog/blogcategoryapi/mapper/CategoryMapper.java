package com.abeldevelop.blog.blogcategoryapi.mapper;

import org.springframework.stereotype.Component;

import com.abeldevelop.blog.blogcategoryapi.domain.Category;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.CreateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.resource.UpdateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.util.StringUtils;

@Component
public class CategoryMapper {

	public Category mapResourceToDomain(CreateCategoryRequestResource createCategoryRequestResource) {
		return Category.builder()
				.code(StringUtils.generateUrlName(createCategoryRequestResource.getName()))
				.name(createCategoryRequestResource.getName())
				.build();
	}
	
	public Category mapResourceToDomain(UpdateCategoryRequestResource updateCategoryRequestResource) {
		return Category.builder()
				.code(StringUtils.generateUrlName(updateCategoryRequestResource.getName()))
				.name(updateCategoryRequestResource.getName())
				.build();
	}
	
	public CategoryEntity mapDomainToEntity(Category category) {
		return CategoryEntity.builder()
				.code(category.getCode())
				.name(category.getName())
				.build();
	}
	
	public CategoryResponseResource mapDomainToResource(Category category) {
		return CategoryResponseResource.builder()
				.code(category.getCode())
				.name(category.getName())
				.build();
	}
}
