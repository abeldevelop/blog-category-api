package com.abeldevelop.blog.blogcategoryapi.cucumber.common;

import static com.abeldevelop.blog.blogcategoryapi.cucumber.config.TestContext.CONTEXT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.abeldevelop.blog.blogcategoryapi.cucumber.config.TestContext;

public class BaseSteps {

	@LocalServerPort
	private int port;

	MockMvc mvc;
	
	public String baseUrl() {
		return "http://localhost:" + port;
	}

	public TestContext testContext() {
		return CONTEXT;
	}
	
	//TODO Think to move all related with rest calls to another class
	protected void makeCall(String method) throws Exception {
		makePostCall();
		
	}
	
	private void makePostCall() throws Exception {
		String endpoint = testContext().getRequestEndpoint();
		String body = testContext().getRequestBody();
		
		MockHttpServletResponse response = this.mvc.perform(post(endpoint)
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(body)).andReturn().getResponse();
		
		testContext().setResponseStatus(response.getStatus());
	}
}
