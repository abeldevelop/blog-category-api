package com.abeldevelop.blog.blogcategoryapi.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.abeldevelop.blog.blogcategoryapi.cucumber.common.BaseSteps;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryPaginationResponseResource;
import com.abeldevelop.blog.blogcategoryapi.resource.CategoryResponseResource;

import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategorySteps extends BaseSteps {

	
	@And("If response code is {int} i verify the category code  {}")
	public void If_response_code_is_201_i_verify_the_category_code(Integer expectedResponseCode, String responseCategoryCode) throws Exception {
		if(testContext().getResponseStatus() == expectedResponseCode) {
			log.info("If_response_code_is_201_i_verify_the_category_code");
			CategoryResponseResource response = getResponseClass(CategoryResponseResource.class);
			assertThat(response.getCode()).isEqualTo(responseCategoryCode);
		}
	}
	

	@And("If response code is {int} i verify the categories result size {int}")
	public void if_response_code_is_200_i_verify_the_categories_result_size(Integer expectedResponseCode, Integer resultSize) throws Exception {
		if(testContext().getResponseStatus() == expectedResponseCode) {
			log.info("If_response_code_is_201_i_verify_the_category_code");
			CategoryPaginationResponseResource response = getResponseClass(CategoryPaginationResponseResource.class);
			assertThat(response.getCategories()).hasSize(resultSize);
		}
	}
	
}
