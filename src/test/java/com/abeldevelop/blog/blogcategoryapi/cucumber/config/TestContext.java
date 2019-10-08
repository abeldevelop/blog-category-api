package com.abeldevelop.blog.blogcategoryapi.cucumber.config;

import static java.lang.ThreadLocal.withInitial;

import java.util.HashMap;
import java.util.Map;

public enum TestContext {

	CONTEXT;

	private static final String REQUEST_ENDPOINT = "REQUEST_ENDPOINT";
	private static final String REQUEST_HEADERS = "REQUEST_HEADERS";
	private static final String REQUEST_PARAMS = "REQUEST_PARAMS";
	private static final String REQUEST_BODY = "REQUEST_BODY";
	private static final String RESPONSE_STATUS = "RESPONSE_STATUS";
	private static final String RESPONSE_HEADERS = "RESPONSE_HEADERS";
	private static final String RESPONSE_BODY = "RESPONSE_BODY";
	private final ThreadLocal<Map<String, Object>> testContexts = withInitial(HashMap::new);

	@SuppressWarnings("unchecked")
	private <T> T get(String name) {
		return (T) testContexts.get().get(name);
	}

	private <T> T set(String name, T object) {
		testContexts.get().put(name, object);
		return object;
	}
	
	public void setRequestEndpoint(String requestEndpoint) {
		set(REQUEST_ENDPOINT, requestEndpoint);
	}
	
	public String getRequestEndpoint() {
		return get(REQUEST_ENDPOINT);
	}
	
	public void setRequestHeaders(Object requestHeaders) {
		set(REQUEST_HEADERS, requestHeaders);
	}
	
	public String getRequestHeaders() {
		return get(REQUEST_HEADERS);
	}
	
	public void setRequestBody(Object requestBody) {
		set(REQUEST_BODY, requestBody);
	}
	
	public String getRequestBody() {
		return get(REQUEST_BODY);
	}
	
	public void setRequestParams(Object requestParams) {
		set(REQUEST_PARAMS, requestParams);
	}
	
	public String getRequestParams() {
		return get(REQUEST_PARAMS);
	}
	
	public void setResponseStatus(Object responseStatus) {
		set(RESPONSE_STATUS, responseStatus);
	}
	
	public String getResponseStatus() {
		return get(RESPONSE_STATUS);
	}
	
	public void setResponseHeaders(Object responseHeaders) {
		set(RESPONSE_HEADERS, responseHeaders);
	}
	
	public String getResponseHeaders() {
		return get(RESPONSE_HEADERS);
	}
	
	public void setResponseBody(Object responseBody) {
		set(RESPONSE_HEADERS, responseBody);
	}
	
	public String getResponseBody() {
		return get(RESPONSE_BODY);
	}

}