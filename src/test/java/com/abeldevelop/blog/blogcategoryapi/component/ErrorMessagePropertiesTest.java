package com.abeldevelop.blog.blogcategoryapi.component;


import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ErrorMessagePropertiesTest {

	private static ErrorMessageProperties errorMessage;
	
	@BeforeAll
	public static void setUp() {
		errorMessage = new ErrorMessageProperties();
	}
	
	@Test
	public void getErrorMessageFoundTest() {
		
		Optional<String> expectedResutl = Optional.of("The category code is mandatory");
		
		Optional<String> result = errorMessage.getErrorMessage("categoryCodeNotNull");
		
		assertThat(result).isEqualTo(expectedResutl);
	}
	
	@Test
	public void getErrorMessageNotFoundTest() {
		
		Optional<String> expectedResutl = Optional.empty();
		
		Optional<String> result = errorMessage.getErrorMessage("code");
		
		assertThat(result).isEqualTo(expectedResutl);
	}
}
