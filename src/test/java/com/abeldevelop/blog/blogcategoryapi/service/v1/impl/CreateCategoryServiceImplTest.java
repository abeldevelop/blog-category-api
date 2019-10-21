package com.abeldevelop.blog.blogcategoryapi.service.v1.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import com.abeldevelop.blog.blogcategoryapi.domain.Category;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
import com.abeldevelop.blog.blogcategoryapi.exception.category.CategoryExistException;
import com.abeldevelop.blog.blogcategoryapi.mapper.CategoryMapper;
import com.abeldevelop.blog.blogcategoryapi.repository.CategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateCategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private CategoryMapper categoryMapper;
	
	private CreateCategoryServiceImpl createCategoryServiceImpl;
	
	@BeforeEach
	public void setUp() {
		createCategoryServiceImpl = new CreateCategoryServiceImpl(categoryRepository, categoryMapper);
	}
	
	@Test
	public void executeCreateTestOk() {
		//given
		CategoryEntity expectedResutl = CategoryEntity.builder().code("first-category").name("First Category").build();
		Category category = Category.builder().code("first-category").name("First Category").build();
		
		//when
		Mockito.when(categoryMapper.mapDomainToEntity(Mockito.any(Category.class))).thenReturn(expectedResutl);
		Mockito.when(categoryMapper.mapEntityToDomain(Mockito.any(CategoryEntity.class))).thenReturn(category);
		Mockito.when(categoryRepository.executeFindById(Mockito.any(String.class))).thenReturn(Optional.empty());
		Mockito.when(categoryRepository.executeSave(Mockito.any(CategoryEntity.class))).thenReturn(expectedResutl);
		Category result = createCategoryServiceImpl.executeCreate(category);
		
		//then
		assertThat(result).isEqualToComparingFieldByField(expectedResutl);
	}
	
	@Test
	public void executeCreateTestKo() {
		//given
		CategoryEntity expectedResutl = CategoryEntity.builder().code("first-category").name("First Category").build();
		Category category = Category.builder().code("first-category").name("First Category").build();
		
		//when
		Mockito.when(categoryRepository.executeFindById(Mockito.any(String.class))).thenReturn(Optional.of(expectedResutl));
        assertThrows(CategoryExistException.class, () -> createCategoryServiceImpl.executeCreate(category));
		
		//then
	}
}
