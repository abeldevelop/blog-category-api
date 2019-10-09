package com.abeldevelop.blog.blogcategoryapi.cucumber.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.abeldevelop.blog.blogcategoryapi.cucumber.config.TestContext;

/**
 * Class to manage the call to the webservice
 *
 */
public class MakeRestCall {

	private HttpEntity<?> requestHttpEntity;
	private TestContext testContext;
	
	MakeRestCall(TestContext testContext) {
		this.testContext = testContext;
	}
	
	public void call(String method) throws Exception {
		switch(method) {
			case "POST":
				makeRestCall(HttpMethod.POST);
				break;
			case "PUT":
				makeRestCall(HttpMethod.PUT);
				break;
			case "GET":
				makeRestCall(HttpMethod.GET);
				break;
			case "DELETE":
				makeRestCall(HttpMethod.DELETE);
				break;
			default:
				throw new Exception("The method '" + method + "' is not supported");
		}
	}
	
	private void createRequestHttpEntity() {
		HttpHeaders requestHeaders = testContext.getRequestHeaders();
		if(requestHeaders == null) {
			requestHeaders = new HttpHeaders();
			requestHeaders.add("Content-Type", "application/json");
		}
		requestHttpEntity = new HttpEntity<Object>(testContext.getRequestBody(), requestHeaders);
	}
	
	private void makeRestCall(HttpMethod method) throws Exception {
		createRequestHttpEntity();
		try {
			ResponseEntity<String> response = new RestTemplate().exchange(testContext.getRequestEndpoint(), method, requestHttpEntity, String.class);
			addResponseInformationToContext(response);
		} catch (Exception e) {
			if(e instanceof HttpClientErrorException) {
				addResponseInformationToContext((HttpClientErrorException) e);
			} else {
				e.printStackTrace();
				assertThat(false).isEqualTo(true);
				throw e;
			}
		}
	}
	
	private void addResponseInformationToContext(ResponseEntity<String> response) throws Exception {
		this.testContext.setResponseHeaders(response.getHeaders());
		this.testContext.setResponseBody(response.getBody());
		this.testContext.setResponseStatus(response.getStatusCode().value());
	}
	
	private void addResponseInformationToContext(HttpClientErrorException response) throws Exception {
		this.testContext.setResponseHeaders(response.getResponseHeaders());
		this.testContext.setResponseBody(response.getResponseBodyAsString());
		this.testContext.setResponseStatus(response.getStatusCode().value());
	}
	
}
