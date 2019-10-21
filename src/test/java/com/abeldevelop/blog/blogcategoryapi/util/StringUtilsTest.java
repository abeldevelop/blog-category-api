package com.abeldevelop.blog.blogcategoryapi.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class StringUtilsTest {

	@Test
	public void normalizeTest() {
		
		String expectedResutl = "Aei";
		
		String result = StringUtils.normalize("Áéí");
		
		assertThat(result).isEqualTo(expectedResutl);
	}
	
	@Test
	public void generateUrlNameTest() {
		
		String expectedResutl = "a-a-a";
		
		String result = StringUtils.generateUrlName("a a a");
				
		assertThat(result).isEqualTo(expectedResutl);
	}
}
