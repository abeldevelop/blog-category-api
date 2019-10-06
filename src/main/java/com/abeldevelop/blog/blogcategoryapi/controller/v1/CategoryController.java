package com.abeldevelop.blog.blogcategoryapi.controller.v1;

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

import com.abeldevelop.blog.blogcategoryapi.config.SpringFoxConfiguration;
import com.abeldevelop.blog.blogcategoryapi.mapper.CategoryMapper;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryPaginationResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.CreateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.resource.ErrorResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.UpdateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.service.v1.CreateCategoryService;
import com.abeldevelop.blog.blogcategoryapi.service.v1.DeleteCategoryService;
import com.abeldevelop.blog.blogcategoryapi.service.v1.UpdateCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Api(tags= {"Category"})
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/categories")
public class CategoryController {

	private static final String LOG_DATA_IN = "{} Data IN => ";
	private static final String LOG_DATA_OUT = "{} Data IN => ";
	
	private static final String EXECUTE_CREATE_METHOD_NAME = "executeCreate";
	private static final String EXECUTE_UPDATE_METHOD_NAME = "executeUpdate";
	private static final String EXECUTE_DELETE_METHOD_NAME = "executeDelete";
	private static final String EXECUTE_FIND_BY_CODE_METHOD_NAME = "executeFindByCode";
	private static final String EXECUTE_FIND_ALL_METHOD_NAME = "executeFindAll";
	
	private final CategoryMapper categoryMapper;
	private final CreateCategoryService createCategoryService;
	private final UpdateCategoryService updateCategoryService;
	private final DeleteCategoryService deleteCategoryService;
	
	
	@ApiOperation(value = "Create new category")
	@ApiResponses({ 
		@ApiResponse(code = 201, response = CategoryResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_201_MESSAGE), 
		@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_400_MESSAGE),
		@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_500_MESSAGE)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryResponseResource executeCreate(@ApiParam(name="category", value="Category to create", required = true) @Valid @RequestBody CreateCategoryRequestResource createCategoryRequestResource) {
		
		log.info(LOG_DATA_IN + "createCategoryRequestResource: {}", EXECUTE_CREATE_METHOD_NAME, createCategoryRequestResource);
		
		CategoryResponseResource categoryResponseResource = categoryMapper.mapDomainToResource(createCategoryService.executeCreate(categoryMapper.mapResourceToDomain(createCategoryRequestResource)));
		
		log.info(LOG_DATA_OUT + "categoryResponseResource: {}", EXECUTE_CREATE_METHOD_NAME, categoryResponseResource);
		
		return categoryResponseResource;
	}
	
	@ApiOperation(value = "Update a category")
	@ApiResponses({ 
		@ApiResponse(code = 200, response = CategoryResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_200_MESSAGE), 
		@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_400_MESSAGE),
		@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_404_MESSAGE),
		@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_500_MESSAGE)
	})
	@PutMapping("/{code}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponseResource executeUpdate(
			@ApiParam(name="code", value="Code of the category", required = true, example="first-category") @PathVariable("code") String code,
			@ApiParam(name="category", value="Category to updated", required = true) @Valid @RequestBody UpdateCategoryRequestResource updateCategoryRequestResource) {
		
		log.info(LOG_DATA_IN + "code: {}, updateCategoryRequestResource: {}", EXECUTE_UPDATE_METHOD_NAME, code, updateCategoryRequestResource);
		
		CategoryResponseResource categoryResponseResource = categoryMapper.mapDomainToResource(updateCategoryService.executeUpdate(code, categoryMapper.mapResourceToDomain(updateCategoryRequestResource)));
		
		log.info(LOG_DATA_OUT + "categoryResponseResource: {}", EXECUTE_UPDATE_METHOD_NAME, categoryResponseResource);
		
		return categoryResponseResource;
	}
	
	@ApiOperation(value = "Delete a category")
	@ApiResponses({ 
		@ApiResponse(code = 204, response = CategoryResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_204_MESSAGE), 
		@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_404_MESSAGE),
		@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_500_MESSAGE)
	})
	@DeleteMapping("/{code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void executeDelete(@ApiParam(name="code", value="Code of the category", required = true, example="first-category") @PathVariable("code") String code) {
		
		log.info(LOG_DATA_IN + "code: {}", EXECUTE_DELETE_METHOD_NAME, code);

		deleteCategoryService.executeDeleteByCode(code);
		
		log.info(LOG_DATA_OUT, EXECUTE_DELETE_METHOD_NAME);
	}

	@ApiOperation(value = "Find category by code")
	@ApiResponses({ 
		@ApiResponse(code = 200, response = CategoryResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_200_MESSAGE), 
		@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_404_MESSAGE),
		@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_500_MESSAGE)
	})
	@GetMapping("/{code}")
	@ResponseStatus(HttpStatus.OK)
	public CategoryResponseResource executeFindByCode(@ApiParam(name="code", value="Code of the category", required = true, example="first-category") @PathVariable("code") String code) {

		log.info(LOG_DATA_IN + "code: {}", EXECUTE_FIND_BY_CODE_METHOD_NAME, code);
		
		//TODO Call the service to find a category by code
		CategoryResponseResource categoryResponseResource = null;
		
		log.info(LOG_DATA_OUT + "categoryResponseResource: {}", EXECUTE_FIND_BY_CODE_METHOD_NAME, categoryResponseResource);
		
		throw new UnsupportedOperationException("Not implemented, yet");
	}
	
	@ApiOperation(value = "Find all categories")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "Number of page", required = false, example="1", defaultValue="1", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "size", value = "Size of page", required = false, example="1", defaultValue="10", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "query", value = "Name or part of category name to search", required = false, example="fir", dataType = "string", paramType = "query")
	})
	@ApiResponses({ 
		@ApiResponse(code = 200, response = CategoryResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_200_MESSAGE),
		@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_400_MESSAGE),
		@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_404_MESSAGE),
		@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_500_MESSAGE)
	})
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public CategoryPaginationResponseResource executeFindAll(
			@RequestParam(name = "page", required = false) Integer page, 
			@RequestParam(name = "size", required = false) Integer size, 
			@RequestParam(name = "query", required = false) String query) {
		log.info(LOG_DATA_IN + "page: {}, size: {}, query: {}", EXECUTE_FIND_ALL_METHOD_NAME, page, size, query);
		
		//TODO Call the service to find all categories
		CategoryPaginationResponseResource categoryPaginationResponseResource = null;
		
		log.info(LOG_DATA_OUT + "categoryResponseResource: {}", EXECUTE_FIND_ALL_METHOD_NAME, categoryPaginationResponseResource);
		
		throw new UnsupportedOperationException("Not implemented, yet");
	}
}
