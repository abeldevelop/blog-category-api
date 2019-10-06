package com.abeldevelop.blog.blogcategoryapi.service.v1.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.abeldevelop.blog.blogcategoryapi.domain.Category;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
import com.abeldevelop.blog.blogcategoryapi.exception.category.CategoryNotFoundException;
import com.abeldevelop.blog.blogcategoryapi.mapper.CategoryMapper;
import com.abeldevelop.blog.blogcategoryapi.repository.CategoryRepository;

@RunWith(MockitoJUnitRunner.class)
public class FindCategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private CategoryMapper categoryMapper;
	
	private FindCategoryServiceImpl findCategoryServiceImpl;
	
	@Before
	public void setUp() {
		findCategoryServiceImpl = new FindCategoryServiceImpl(categoryRepository, categoryMapper);
	}
	
	@Test
	public void executeFindByCodeTestOk() {
		//given
		CategoryEntity expectedResutl = CategoryEntity.builder().code("first-category").name("First Category").build();
		Category category = Category.builder().code("first-category").name("First Category").build();
		CategoryEntity categoryEntity = CategoryEntity.builder().code("first-category").name("First Category").build();
		
		//when
		Mockito.when(categoryMapper.mapEntityToDomain(Mockito.any(CategoryEntity.class))).thenReturn(category);
		Mockito.when(categoryRepository.executeFindById(Mockito.any(String.class))).thenReturn(Optional.of(categoryEntity));
		
		Category result = findCategoryServiceImpl.executeFindByCode("first-category");
		
		//then
		assertThat(result).isEqualToComparingFieldByFieldRecursively(expectedResutl);
	}
	
	@Test(expected = CategoryNotFoundException.class)
	public void executeFindByCodeNotFoundTestKo() {
		//given
		
		//when
		Mockito.when(categoryRepository.executeFindById(Mockito.any(String.class))).thenReturn(Optional.empty());
		
		findCategoryServiceImpl.executeFindByCode("first-category");
		
		//then
	}
}
