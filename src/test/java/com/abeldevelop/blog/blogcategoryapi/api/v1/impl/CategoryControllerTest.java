package com.abeldevelop.blog.blogcategoryapi.api.v1.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import com.abeldevelop.blog.blogcategoryapi.domain.Category;
import com.abeldevelop.blog.blogcategoryapi.domain.PageRequest;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationRequest;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResponse;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResult;
import com.abeldevelop.blog.blogcategoryapi.mapper.CategoryMapper;
import com.abeldevelop.blog.blogcategoryapi.mapper.PaginationMapper;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryPaginationResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.CreateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.resource.PaginationResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.UpdateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.service.v1.CreateCategoryService;
import com.abeldevelop.blog.blogcategoryapi.service.v1.DeleteCategoryService;
import com.abeldevelop.blog.blogcategoryapi.service.v1.FindCategoryService;
import com.abeldevelop.blog.blogcategoryapi.service.v1.UpdateCategoryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {

	@Mock
	private CreateCategoryService createCategoryService;

	@Mock
	private UpdateCategoryService updateCategoryService;
	
	@Mock
	private DeleteCategoryService deleteCategoryService;
	
	@Mock
	private FindCategoryService findCategoryService;

	@Mock
	private CategoryMapper categoryMapper;
	
	@Mock
	private PaginationMapper paginationMapper;
	
	CategoryApiController categoryController;
	
	@BeforeEach
	public void setUp() {
		categoryController = new CategoryApiController(createCategoryService, updateCategoryService, deleteCategoryService, findCategoryService, categoryMapper, paginationMapper);
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
		assertThat(result).isEqualToComparingFieldByField(expectedResutl);
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
		assertThat(result).isEqualToComparingFieldByField(expectedResutl);
		
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
		assertThat(result).isEqualToComparingFieldByField(expectedResutl);
	}
	
	@Test
	public void executeFindAllTestOk() {
		
		//given
		PaginationResponseResource paginationResponseResourceResult = PaginationResponseResource.builder().page(1).size(10).numberOfElements(1).totalPages(1).totalElements(1).first(true).last(true).build();
		CategoryResponseResource categoryResult = CategoryResponseResource.builder().code("first-category").name("First Category").build();
		CategoryPaginationResponseResource expectedResult = CategoryPaginationResponseResource.builder()
				.pagination(paginationResponseResourceResult)
				.category(categoryResult)
				.build();
		PaginationRequest paginationRequest = PaginationRequest.builder().page(0).size(10).build();
		PaginationResult<Category> paginationResult = new PaginationResult<Category>(
				PaginationResponse.builder()
					.page(0)
					.size(10)
					.numberOfElements(1)
					.totalElements(1)
					.build(),
				Arrays.asList(Category.builder().code("first-category").name("First Category").build()));
		
		//when
		Mockito.when(paginationMapper.map(Mockito.anyInt(), Mockito.anyInt())).thenReturn(paginationRequest);
		Mockito.when(findCategoryService.executeFindAll(Mockito.any(PageRequest.class), Mockito.anyString())).thenReturn(paginationResult);
		Mockito.when(paginationMapper.map(Mockito.any(PaginationResponse.class))).thenReturn(paginationResponseResourceResult);
		Mockito.when(categoryMapper.mapDomainToResource(Mockito.any(Category.class))).thenReturn(categoryResult);
		
		CategoryPaginationResponseResource result = categoryController.executeFindAll(1, 1, "");
		
		//then
		assertThat(result).isEqualToComparingFieldByField(expectedResult);
	}
}
