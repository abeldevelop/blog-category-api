package com.abeldevelop.blog.blogcategoryapi.mapper;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackTraceMapperTest {

	public StackTraceMapper stackTraceMapper;
	
	@BeforeEach
	public void setUp() {
		stackTraceMapper = new StackTraceMapper();
	}
	
	@Test
	public void mapTest() {
		
		List<String> expectedResutl = Arrays.asList("com.abeldevelop.catalina:53");
		
		StackTraceElement[] stackTraceElements = new StackTraceElement[2];
		//String declaringClass, String methodName, String fileName, int lineNumber
		stackTraceElements [0] = new StackTraceElement("org.apache.catalina", "", "", 22);
		stackTraceElements [1] = new StackTraceElement("com.abeldevelop.catalina", "", "", 53);
	
		List<String> result = stackTraceMapper.map(stackTraceElements);
		
		


		assertThat(result).isEqualTo(expectedResutl);
	}
}
