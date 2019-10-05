package com.abeldevelop.blog.blogcategoryapi.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.abeldevelop.blog.blogcategoryapi.exception.client.custom.ValidationRequestException;
import com.abeldevelop.blog.blogcategoryapi.interfaces.ErrorMessages;
import com.abeldevelop.blog.blogcategoryapi.mapper.StackTraceMapper;
import com.abeldevelop.blog.blogcategoryapi.resource.ErrorResponseResource;

@RunWith(MockitoJUnitRunner.class)
public class CustomizedResponseEntityExceptionHandlerTest {

	private CustomizedResponseEntityExceptionHandler customizedResponseEntityExceptionHandler;
	
	@Mock
	private ErrorMessages errorMessages;
	
	@Mock
	private StackTraceMapper stackTraceMapper;
	
	@Before
	public void setUp() {
		customizedResponseEntityExceptionHandler = new CustomizedResponseEntityExceptionHandler(errorMessages, stackTraceMapper);
		customizedResponseEntityExceptionHandler.addExceptionSensitiveInformation = true;
	}
	
	@Test
	public void handleAllExceptionsTest() {
		ErrorResponseResource expectedResutl = ErrorResponseResource.builder()
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
				.detail(new RuntimeException().getClass().getCanonicalName())
				.message("error")
				.build();
		
		ErrorResponseResource result = (ErrorResponseResource) customizedResponseEntityExceptionHandler.handleAllExceptions(new RuntimeException("error"), null).getBody();
		
	    assertThat(result.getStatus()).isEqualTo(expectedResutl.getStatus());
	    assertThat(result.getError()).isEqualTo(expectedResutl.getError());
	    assertThat(result.getMessage()).isEqualTo(expectedResutl.getMessage());
	    assertThat(result.getDetail()).isEqualTo(expectedResutl.getDetail());
		
	}
	
	@Test
	public void handleAbelDevelopExceptionTest() {
		ErrorResponseResource expectedResutl = ErrorResponseResource.builder()
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.getReasonPhrase())
				.detail(new ValidationRequestException("error").getClass().getCanonicalName())
				.message("error")
				.build();
		
		ErrorResponseResource result = (ErrorResponseResource) customizedResponseEntityExceptionHandler.handleAllExceptions(new ValidationRequestException("error"), null).getBody();
		
	    assertThat(result.getStatus()).isEqualTo(expectedResutl.getStatus());
	    assertThat(result.getError()).isEqualTo(expectedResutl.getError());
	    assertThat(result.getMessage()).isEqualTo(expectedResutl.getMessage());
	    assertThat(result.getDetail()).isEqualTo(expectedResutl.getDetail());
		
	}
}
