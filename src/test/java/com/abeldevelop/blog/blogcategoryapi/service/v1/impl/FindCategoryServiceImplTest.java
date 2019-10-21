package com.abeldevelop.blog.blogcategoryapi.service.v1.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Optional;

import com.abeldevelop.blog.blogcategoryapi.domain.Category;
import com.abeldevelop.blog.blogcategoryapi.domain.PageRequest;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResponse;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResult;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
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
public class FindCategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;
	
	@Mock
	private CategoryMapper categoryMapper;
	
	private FindCategoryServiceImpl findCategoryServiceImpl;
	
	@BeforeEach
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
		assertThat(result).isEqualToComparingFieldByField(expectedResutl);
	}
	
	@Test
	public void executeFindByCodeNotFoundTestKo() {
		//given
		
		//when
		Mockito.when(categoryRepository.executeFindById(Mockito.any(String.class))).thenReturn(Optional.empty());
		
		assertThrows(CategoryNotFoundException.class, () -> findCategoryServiceImpl.executeFindByCode("first-category"));
		
		//then
	}
	
	@Test
	public void executeFindAllTestOk() {
		//given
		PaginationResult<Category> expectedResutl = new PaginationResult<Category>(PaginationResponse.builder()
				.page(0)
				.size(10)
				.numberOfElements(1)
				.totalElements(1)
				.build(), 
				Arrays.asList(Category.builder().code("first-category").name("First Category").build()));
		
		PaginationResult<CategoryEntity> listCategories = new PaginationResult<CategoryEntity>(PaginationResponse.builder()
				.page(0)
				.size(10)
				.numberOfElements(1)
				.totalElements(1)
				.build(), 
				Arrays.asList(CategoryEntity.builder().code("first-category").name("First Category").build()));
		Category category = Category.builder().code("first-category").name("First Category").build();
		
		//when
		Mockito.when(categoryRepository.executeFindAll(Mockito.any(PageRequest.class), Mockito.anyString())).thenReturn(listCategories);
		Mockito.when(categoryMapper.mapEntityToDomain(Mockito.any(CategoryEntity.class))).thenReturn(category);
		
		PaginationResult<Category> result = findCategoryServiceImpl.executeFindAll(new PageRequest(null, null), "");
		
        //then
        assertThat(result.getPagination()).isEqualToComparingFieldByField(expectedResutl.getPagination());
		assertThat(result.getResults().get(0)).isEqualToComparingFieldByField(expectedResutl.getResults().get(0));
	}
}
