package com.abeldevelop.blog.blogcategoryapi.cucumber.steps;

import static org.assertj.core.api.Assertions.assertThat;

import com.abeldevelop.blog.blogcategoryapi.cucumber.common.BaseSteps;
import com.abeldevelop.blog.blogcategoryapi.cucumber.common.MakeRestCall;
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
	
	@Given("The endpoint {}")
	public void the_endpoint(String endpoint) throws ClassNotFoundException {
		generateTheFullEndpoint(endpoint.replaceAll("\"", ""));
	}
	
	@And("The resource {}")
	public void the_resource(String className) throws ClassNotFoundException {
		testContext().getTestCase();
		log.info("START STEP => the_resource {}", className);
		requestResourceClazz = Class.forName(className);
		log.info("END STEP => the_resource {}", className);
	}
	
	@And("The input data")
	public void the_input_data(DataTable datatable) throws JsonProcessingException {
		log.info("START STEP => the_input_data");
		log.info("datatable: {}", datatable);
		Object requestBody = datatable.asList(requestResourceClazz).get(0);
		String requestBodyInString = new ObjectMapper().writeValueAsString(requestBody);
		testContext().setRequestBody(requestBodyInString);
		log.info("requestBodyInString: " + requestBodyInString);
		log.info("END STEP => the_input_data");
	}

	@When("Make {string} call")
	public void make_call_to_the_endpoint(String method) throws Exception {
		log.info("START STEP => Make {} call", method);
		new MakeRestCall(testContext()).call(method);
		log.info("END STEP => Make {} call", method);
	}
	
	private void generateTheFullEndpoint(String endpoint) {
		testContext().setRequestEndpoint(baseUrl() + endpoint);
	}
	
	@Then("I verify the {int} response code")
	public void i_verify_the_response_code(Integer expectedStatusCode) {
		log.info("START STEP => I verify the {} response code", expectedStatusCode);
		assertThat(testContext().getResponseStatus()).isEqualTo(expectedStatusCode);
		log.info("END STEP => I verify the {} response code", expectedStatusCode);
	}
	
	@And("If response code not {int} i verify the error response message {}")
	public void i_verify_the_error_response_message(Integer expectedResponseCode, String errorResponseMessage) throws Exception {
		log.info("START STEP => If response code not {} i verify the error response message {}", expectedResponseCode, errorResponseMessage);
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
		log.info("END STEP => If response code not {} i verify the error response message {}", expectedResponseCode, errorResponseMessage);
	}
}
