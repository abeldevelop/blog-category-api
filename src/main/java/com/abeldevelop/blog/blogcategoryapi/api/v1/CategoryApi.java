package com.abeldevelop.blog.blogcategoryapi.api.v1;

import org.springframework.web.bind.annotation.PathVariable;

import com.abeldevelop.blog.blogcategoryapi.config.SpringFoxConfiguration;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryPaginationResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.CreateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.resource.ErrorResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.UpdateCategoryRequestResource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags= {"Categories"})
public interface CategoryApi {

	@ApiOperation(value = "Create new category")
	@ApiResponses({ 
		@ApiResponse(code = 201, response = CategoryResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_201_MESSAGE), 
		@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_400_MESSAGE),
		@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_500_MESSAGE)
	})
	public CategoryResponseResource executeCreate(@ApiParam(name="category", value="Category to create", required = true) CreateCategoryRequestResource createCategoryRequestResource);
	
	@ApiOperation(value = "Update a category")
	@ApiResponses({ 
		@ApiResponse(code = 200, response = CategoryResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_200_MESSAGE), 
		@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_400_MESSAGE),
		@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_404_MESSAGE),
		@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_500_MESSAGE)
	})
	public CategoryResponseResource executeUpdate(
			@ApiParam(name="code", value="Code of the category", required = true, example="first-category") String code,
			@ApiParam(name="category", value="Category to updated", required = true) UpdateCategoryRequestResource updateCategoryRequestResource);
	
	@ApiOperation(value = "Delete a category")
	@ApiResponses({ 
		@ApiResponse(code = 204, response = CategoryResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_204_MESSAGE), 
		@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_404_MESSAGE),
		@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_500_MESSAGE)
	})
	public void executeDelete(@PathVariable("code") String code);
	
	@ApiOperation(value = "Find category by code")
	@ApiResponses({ 
		@ApiResponse(code = 200, response = CategoryResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_200_MESSAGE), 
		@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_404_MESSAGE),
		@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_500_MESSAGE)
	})
	public CategoryResponseResource executeFindByCode(@ApiParam(name="code", value="Code of the category", required = true, example="first-category") String code);
	
	@ApiOperation(value = "Find all categories")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "Number of page", required = false, example="1", defaultValue="1", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "size", value = "Size of page", required = false, example="1", defaultValue="10", dataType = "int", paramType = "query"),
		@ApiImplicitParam(name = "query", value = "Name or part of category name to search", required = false, example="fir", dataType = "string", paramType = "query")
	})
	@ApiResponses({ 
		@ApiResponse(code = 200, response = CategoryPaginationResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_200_MESSAGE),
		@ApiResponse(code = 400, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_400_MESSAGE),
		@ApiResponse(code = 404, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_404_MESSAGE),
		@ApiResponse(code = 500, response = ErrorResponseResource.class, message = SpringFoxConfiguration.API_RESPONSE_CODE_500_MESSAGE)
	})
	public CategoryPaginationResponseResource executeFindAll(Integer page, Integer size, String query);
	
}
