package com.abeldevelop.blog.blogcategoryapi.service.v1.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import com.abeldevelop.blog.blogcategoryapi.domain.Category;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
import com.abeldevelop.blog.blogcategoryapi.exception.category.CategoryExistException;
import com.abeldevelop.blog.blogcategoryapi.exception.category.CategoryNotFoundException;
import com.abeldevelop.blog.blogcategoryapi.mapper.CategoryMapper;
import com.abeldevelop.blog.blogcategoryapi.repository.CategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private CategoryMapper categoryMapper;
	
	private UpdateCategoryServiceImpl updateCategoryServiceImpl;
	
	@BeforeEach
	public void setUp() {
		updateCategoryServiceImpl = new UpdateCategoryServiceImpl(categoryRepository, categoryMapper);
	}
	
	@Test
	public void executeUpdateTestOk() {
		//given
		CategoryEntity firstCategoryEntity = CategoryEntity.builder().code("first-category").name("First Category").build();
		CategoryEntity expectedResutl = CategoryEntity.builder().code("update-category").name("Update Category").build();
		String code = "first-category";
		Category category = Category.builder().code("update-category").name("Update Category").build();
		
		//when
		Mockito.when(categoryMapper.mapDomainToEntity(Mockito.any(Category.class))).thenReturn(expectedResutl);
		Mockito.when(categoryMapper.mapEntityToDomain(Mockito.any(CategoryEntity.class))).thenReturn(category);
		Mockito.when(categoryRepository.executeFindById("first-category")).thenReturn(Optional.of(firstCategoryEntity));
		Mockito.when(categoryRepository.executeFindById("update-category")).thenReturn(Optional.empty());
		Mockito.when(categoryRepository.executeSave(Mockito.any(CategoryEntity.class))).thenReturn(expectedResutl);
		Category result = updateCategoryServiceImpl.executeUpdate(code, category);
		
		//then
		assertThat(result).isEqualToComparingFieldByField(expectedResutl);
	}
	
	@Test
	public void executeUpdateCategoryNotFoundTestKo() {
		//given
		String code = "first-category";
		Category category = Category.builder().code("update-category").name("Update Category").build();
		
		//when
		Mockito.when(categoryRepository.executeFindById("first-category")).thenReturn(Optional.empty());
		assertThrows(CategoryNotFoundException.class, () -> updateCategoryServiceImpl.executeUpdate(code, category));
		
		//then
	}
	
	@Test
	public void executeUpdateCategoryExistTestKo() {
		//given
		CategoryEntity firstCategoryEntity = CategoryEntity.builder().code("first-category").name("First Category").build();
		CategoryEntity expectedResutl = CategoryEntity.builder().code("update-category").name("Update Category").build();
		String code = "first-category";
		Category category = Category.builder().code("update-category").name("Update Category").build();
		
		//when
		Mockito.when(categoryRepository.executeFindById("first-category")).thenReturn(Optional.of(firstCategoryEntity));
		Mockito.when(categoryRepository.executeFindById("update-category")).thenReturn(Optional.of(expectedResutl));
		assertThrows(CategoryExistException.class, () -> updateCategoryServiceImpl.executeUpdate(code, category));
		
		//then
	}
}
