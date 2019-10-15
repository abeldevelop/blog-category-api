package com.abeldevelop.blog.blogcategoryapi.api.v1.impl;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abeldevelop.blog.blogcategoryapi.api.v1.CategoryApi;
import com.abeldevelop.blog.blogcategoryapi.domain.Category;
import com.abeldevelop.blog.blogcategoryapi.domain.PageRequest;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResult;
import com.abeldevelop.blog.blogcategoryapi.mapper.CategoryMapper;
import com.abeldevelop.blog.blogcategoryapi.mapper.PaginationMapper;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryPaginationResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.CreateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.resource.UpdateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.service.v1.CreateCategoryService;
import com.abeldevelop.blog.blogcategoryapi.service.v1.DeleteCategoryService;
import com.abeldevelop.blog.blogcategoryapi.service.v1.FindCategoryService;
import com.abeldevelop.blog.blogcategoryapi.service.v1.UpdateCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/categories")
public class CategoryApiController implements CategoryApi {

	private static final String LOG_DATA_IN = "{} Data IN => ";
	private static final String LOG_DATA_OUT = "{} Data IN => ";
	
	private static final String EXECUTE_CREATE_METHOD_NAME = "executeCreate";
	private static final String EXECUTE_UPDATE_METHOD_NAME = "executeUpdate";
	private static final String EXECUTE_DELETE_METHOD_NAME = "executeDelete";
	private static final String EXECUTE_FIND_BY_CODE_METHOD_NAME = "executeFindByCode";
	private static final String EXECUTE_FIND_ALL_METHOD_NAME = "executeFindAll";
	
	private final CreateCategoryService createCategoryService;
	private final UpdateCategoryService updateCategoryService;
	private final DeleteCategoryService deleteCategoryService;
	private final FindCategoryService findCategoryService;
	
	private final CategoryMapper categoryMapper;
	private final PaginationMapper paginationMapper;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryResponseResource executeCreate(@Valid @RequestBody CreateCategoryRequestResource createCategoryRequestResource) {
		
		log.info(LOG_DATA_IN + "createCategoryRequestResource: {}", EXECUTE_CREATE_METHOD_NAME, createCategoryRequestResource);
		
		CategoryResponseResource categoryResponseResource = categoryMapper.mapDomainToResource(createCategoryService.executeCreate(categoryMapper.mapResourceToDomain(createCategoryRequestResource)));
		
		log.info(LOG_DATA_OUT + "categoryResponseResource: {}", EXECUTE_CREATE_METHOD_NAME, categoryResponseResource);

		return categoryResponseResource;
	}
	
	
	@PutMapping("/{code}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponseResource executeUpdate(@PathVariable("code") String code, @Valid @RequestBody UpdateCategoryRequestResource updateCategoryRequestResource) {
		
		log.info(LOG_DATA_IN + "code: {}, updateCategoryRequestResource: {}", EXECUTE_UPDATE_METHOD_NAME, code, updateCategoryRequestResource);
		
		CategoryResponseResource categoryResponseResource = categoryMapper.mapDomainToResource(updateCategoryService.executeUpdate(code, categoryMapper.mapResourceToDomain(updateCategoryRequestResource)));
		
		log.info(LOG_DATA_OUT + "categoryResponseResource: {}", EXECUTE_UPDATE_METHOD_NAME, categoryResponseResource);
		
		return categoryResponseResource;
	}
	
	
	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeDelete(@PathVariable("code") String code) {
		
		log.info(LOG_DATA_IN + "code: {}", EXECUTE_DELETE_METHOD_NAME, code);

		deleteCategoryService.executeDeleteByCode(code);
		
		log.info(LOG_DATA_OUT, EXECUTE_DELETE_METHOD_NAME);
	}


	@GetMapping("/{code}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponseResource executeFindByCode(@PathVariable("code") String code) {

		log.info(LOG_DATA_IN + "code: {}", EXECUTE_FIND_BY_CODE_METHOD_NAME, code);
		
		CategoryResponseResource categoryResponseResource = categoryMapper.mapDomainToResource(findCategoryService.executeFindByCode(code));
		
		log.info(LOG_DATA_OUT + "categoryResponseResource: {}", EXECUTE_FIND_BY_CODE_METHOD_NAME, categoryResponseResource);
		
		return categoryResponseResource;
	}
	
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CategoryPaginationResponseResource executeFindAll(
			@RequestParam(name = "page", required = false) Integer page, 
			@RequestParam(name = "size", required = false) Integer size, 
			@RequestParam(name = "query", required = false) String query) {
		log.info(LOG_DATA_IN + "page: {}, size: {}, query: {}", EXECUTE_FIND_ALL_METHOD_NAME, page, size, query);
		
		PageRequest pageRequest = PageRequest.builder().pagination(paginationMapper.map(page, size)).build();
		
		PaginationResult<Category> paginationResult = findCategoryService.executeFindAll(pageRequest, query);
		
		CategoryPaginationResponseResource categoryPaginationResponseResource = CategoryPaginationResponseResource.builder()
				.pagination(paginationMapper.map(paginationResult.getPagination()))
				.categories(paginationResult.getResults().stream().map(categoryMapper::mapDomainToResource).collect(Collectors.toList()))
				.build();
		
		log.info(LOG_DATA_OUT + "categoryResponseResource: {}", EXECUTE_FIND_ALL_METHOD_NAME, categoryPaginationResponseResource);

		return categoryPaginationResponseResource;
	}
}
