package com.abeldevelop.blog.blogcategoryapi.cucumber.steps;

import com.abeldevelop.blog.blogcategoryapi.cucumber.common.BaseSteps;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	@When("Make {string} call to the endpoint {string}")
	public void make_call_to_the_endpoint(String method, String endpoint) throws Exception {
		log.info("Make {} call to the endpoint {}", method, endpoint);
		generateTheFullEndpoint(endpoint);
		makeCall(method);
	}
	
	private void generateTheFullEndpoint(String endpoint) {
		//TODO With request params make the full endpoint
//		testContext().getRequestParams();
		
		testContext().setRequestEndpoint(endpoint);
	}
	
	@Then("I verify the {int} response code")
	public void i_verify_the_response_code(Integer expectedStatusCode) {
		log.info("I verify the {} response code", expectedStatusCode);
		testContext().getResponseStatus();
		log.info("I verify the {} response code", expectedStatusCode);
	}
	
	@And("I verify the error response message {}.")
	public void i_verify_the_error_response_message(String errorResponseMessage) {
		log.info("I verify the error response message {}.", errorResponseMessage);
	}
}
