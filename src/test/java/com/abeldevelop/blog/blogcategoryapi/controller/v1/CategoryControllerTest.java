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
import com.abeldevelop.blog.blogcategoryapi.service.v1.DeleteCategoryService;
import com.abeldevelop.blog.blogcategoryapi.service.v1.FindCategoryService;
import com.abeldevelop.blog.blogcategoryapi.service.v1.UpdateCategoryService;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

	@Mock
	private CategoryMapper categoryMapper;
	
	@Mock
	private CreateCategoryService createCategoryService;

	@Mock
	private UpdateCategoryService updateCategoryService;
	
	@Mock
	private DeleteCategoryService deleteCategoryService;
	
	@Mock
	private FindCategoryService findCategoryService;
	
	CategoryController categoryController;
	
	@Before
	public void setUp() {
		categoryController = new CategoryController(categoryMapper, createCategoryService, updateCategoryService, deleteCategoryService, findCategoryService);
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
	
	@Test
	public void executeUpdateTestOk() {
		//given
		CategoryResponseResource expectedResutl = CategoryResponseResource.builder().code("updated-category").name("Updated Category").build();
		UpdateCategoryRequestResource updateCategoryRequestResource = UpdateCategoryRequestResource.builder().name("Updated Category").build();
		String code = "first-category";
		Category category = Category.builder().code("updated-category").name("Updated Category").build();
		
		//when
		Mockito.when(categoryMapper.mapResourceToDomain(Mockito.any(UpdateCategoryRequestResource.class))).thenReturn(category);
		Mockito.when(categoryMapper.mapDomainToResource(Mockito.any(Category.class))).thenReturn(expectedResutl);
		Mockito.when(updateCategoryService.executeUpdate(Mockito.anyString(), Mockito.any(Category.class))).thenReturn(category);
		
		CategoryResponseResource result = categoryController.executeUpdate(code, updateCategoryRequestResource);
		
		//then
		assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedResutl);
		
	}
	
	@Test
	public void executeDeleteTestOk() {
		//given
		
		//when
		Mockito.doNothing().when(deleteCategoryService).executeDeleteByCode(Mockito.anyString());
		
		categoryController.executeDelete("first-category");
		
		//then
		Mockito.verify(deleteCategoryService, Mockito.times(1)).executeDeleteByCode(Mockito.anyString());
		
	}
	
	@Test
	public void executeFindByCodeTestOk() {
		//given
		CategoryResponseResource expectedResutl = CategoryResponseResource.builder().code("first-category").name("First Category").build();
		Category category = Category.builder().code("first-category").name("First Category").build();
		
		//when
		Mockito.when(categoryMapper.mapDomainToResource(Mockito.any(Category.class))).thenReturn(expectedResutl);
		Mockito.when(findCategoryService.executeFindByCode(Mockito.anyString())).thenReturn(category);
		
		CategoryResponseResource result = categoryController.executeFindByCode("first-category");
		
		//then
		assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedResutl);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void executeFindAllTestOk() {
		categoryController.executeFindAll(null, null, null);
	}
}
