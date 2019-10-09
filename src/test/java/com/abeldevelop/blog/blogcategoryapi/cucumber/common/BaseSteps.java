package com.abeldevelop.blog.blogcategoryapi.cucumber.common;

import static com.abeldevelop.blog.blogcategoryapi.cucumber.config.TestContext.CONTEXT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import com.abeldevelop.blog.blogcategoryapi.cucumber.config.TestContext;

public class BaseSteps {

	@LocalServerPort
	private int port;

	public String baseUrl() {
		return "http://localhost:" + port;
	}

	public TestContext testContext() {
		return CONTEXT;
	}
	
	protected void makeCall(String method) throws Exception {
		new MakeRestCall(testContext()).call(method);;
	}
	
}
