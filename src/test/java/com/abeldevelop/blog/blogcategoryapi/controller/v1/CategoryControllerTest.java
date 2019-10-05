package com.abeldevelop.blog.blogcategoryapi.controller.v1;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.abeldevelop.blog.blogcategoryapi.domain.Category;
import com.abeldevelop.blog.blogcategoryapi.mapper.CategoryMapper;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.CreateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.resource.UpdateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.service.v1.CreateCategoryService;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

	@Mock
	private CategoryMapper categoryMapper;
	
	@Mock
	private CreateCategoryService createCategoryService;

	CategoryController categoryController;
	
	@Before
	public void setUp() {
		categoryController = new CategoryController(categoryMapper, createCategoryService);
	}
	
	@Test
	public void executeCreateTestOk() {
		
		//given
		CategoryResponseResource expectedResutl = CategoryResponseResource.builder().code("first-category").name("First Category").build();
		CreateCategoryRequestResource createCategoryRequestResource = CreateCategoryRequestResource.builder().name("First Category").build();
		Category category = Category.builder().code("first-category").name("First Category").build();
		
		//when
		Mockito.when(categoryMapper.mapResourceToDomain(Mockito.any(CreateCategoryRequestResource.class))).thenReturn(category);
		Mockito.when(categoryMapper.mapDomainToResource(Mockito.any(Category.class))).thenReturn(expectedResutl);
		Mockito.when(createCategoryService.executeCreate(Mockito.any(Category.class))).thenReturn(category);
		
		CategoryResponseResource result = categoryController.executeCreate(createCategoryRequestResource);
		
		//then
		assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedResutl);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void executeUpdateTestOk() {
		UpdateCategoryRequestResource updateCategoryRequestResource = UpdateCategoryRequestResource.builder().name("First Category").build();
		categoryController.executeUpdate("first-category", updateCategoryRequestResource);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void executeDeleteTestOk() {
		categoryController.executeDelete("first-category");
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void executeFindByCodeTestOk() {
		categoryController.executeFindByCode("first-category");
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void executeFindAllTestOk() {
		categoryController.executeFindAll(null, null, null);
	}
}
