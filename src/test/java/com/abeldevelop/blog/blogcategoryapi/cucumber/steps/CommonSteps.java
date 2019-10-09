package com.abeldevelop.blog.blogcategoryapi.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.abeldevelop.blog.blogcategoryapi.cucumber.common.BaseSteps;
import com.abeldevelop.blog.blogcategoryapi.resource.ErrorResponseResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonSteps extends BaseSteps {

	Class<?> requestResourceClazz;
	
	@Given("The resource {}")
	public void the_resource(String className) throws ClassNotFoundException {
		requestResourceClazz = Class.forName(className);
		log.info("The resource {}", className);
	}
	
	@And("The input data")
	public void the_input_data(DataTable datatable) throws JsonProcessingException {
		Object requestBody = datatable.asList(requestResourceClazz).get(0);
		String requestBodyInString = new ObjectMapper().writeValueAsString(requestBody);
		testContext().setRequestBody(requestBodyInString);
		log.info("the_input_data: " + requestBodyInString);
	}

	private void setNullValues(DataTable datatable) {
		//TODO nullValue set to null not empty
		List<Map<String, String>> datatableMapList = datatable.asMaps();
		List<String> fieldsToSetNull = new ArrayList<>();
		for(Map<String, String> datatableMap : datatableMapList) {
			for (Map.Entry<String, String> entry : datatableMap.entrySet()) {
				if(entry.getValue().equals("nullValue")) {
					fieldsToSetNull.add(entry.getKey());
				}
			}
		}
	}
	
	@When("Make {string} call to the endpoint {string}")
	public void make_call_to_the_endpoint(String method, String endpoint) throws Exception {
		log.info("Make {} call to the endpoint {}", method, endpoint);
		generateTheFullEndpoint(endpoint);
		makeCall(method);
		log.info("");
	}
	
	private void generateTheFullEndpoint(String endpoint) {
		//TODO With request params make the full endpoint
//		testContext().getRequestParams();
		
		testContext().setRequestEndpoint(baseUrl() + endpoint);
		
	}
	
	@Then("I verify the {int} response code")
	public void i_verify_the_response_code(Integer expectedStatusCode) {
		log.info("I verify the {} response code", expectedStatusCode);
		assertThat(testContext().getResponseStatus()).isEqualTo(expectedStatusCode);
		log.info("I verify the {} response code", expectedStatusCode);
	}
	
	@And("If response code not {int} i verify the error response message {}")
	public void i_verify_the_error_response_message(Integer expectedResponseCode, String errorResponseMessage) throws Exception {
		log.info("Expected response code: {}, actual response code: {}", expectedResponseCode, testContext().getResponseStatus());
		if(testContext().getResponseStatus() != expectedResponseCode) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			try {
				ErrorResponseResource errorResponseResource = mapper.readValue(testContext().getResponseBody(), ErrorResponseResource.class);
				assertThat(errorResponseResource.getMessage()).isEqualTo(errorResponseMessage);
				log.info("I verify the error response message {}.", errorResponseMessage);
			} catch (Exception e) {
				e.printStackTrace();
				assertThat(false).isEqualTo(true);
			}
		}
	}
}
