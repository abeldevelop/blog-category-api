package com.abeldevelop.blog.blogcategoryapi.service.v1.impl;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
import com.abeldevelop.blog.blogcategoryapi.exception.category.CategoryNotFoundException;
import com.abeldevelop.blog.blogcategoryapi.repository.CategoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class DeleteCategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;
	
	private DeleteCategoryServiceImpl deleteCategoryServiceImpl;
	
	@Before
	public void setUp() {
		deleteCategoryServiceImpl = new DeleteCategoryServiceImpl(categoryRepository);
	}
	
	@Test
	public void executeDeleteByCodeTestOk() {
		//given
		CategoryEntity categoryEntity = CategoryEntity.builder().code("first-category").name("First Category").build();
		
		//when
		Mockito.when(categoryRepository.executeFindById(Mockito.any(String.class))).thenReturn(Optional.of(categoryEntity));
		Mockito.doNothing().when(categoryRepository).executeDeleteById(Mockito.anyString());
		
		deleteCategoryServiceImpl.executeDeleteByCode("first-category");
		
		//then
		Mockito.verify(categoryRepository, Mockito.times(1)).executeDeleteById(Mockito.anyString());
	}
	
	@Test(expected = CategoryNotFoundException.class)
	public void executeDeleteByCodeNotFoundTestKo() {
		//given
		
		//when
		Mockito.when(categoryRepository.executeFindById(Mockito.any(String.class))).thenReturn(Optional.empty());
		
		deleteCategoryServiceImpl.executeDeleteByCode("first-category");
		
		//then
	}
}
