package com.abeldevelop.blog.blogcategoryapi.service.v1.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
import com.abeldevelop.blog.blogcategoryapi.exception.category.CategoryNotFoundException;
import com.abeldevelop.blog.blogcategoryapi.repository.CategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteCategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;
	
	private DeleteCategoryServiceImpl deleteCategoryServiceImpl;
	
	@BeforeEach
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
	
	@Test
	public void executeDeleteByCodeNotFoundTestKo() {
		//given
		
		//when
		Mockito.when(categoryRepository.executeFindById(Mockito.any(String.class))).thenReturn(Optional.empty());
		
		assertThrows(CategoryNotFoundException.class, () -> deleteCategoryServiceImpl.executeDeleteByCode("first-category"));
		
		//then
	}
}
