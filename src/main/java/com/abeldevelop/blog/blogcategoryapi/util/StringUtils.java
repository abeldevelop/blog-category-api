package com.abeldevelop.blog.blogcategoryapi.util;

import java.text.Normalizer;

public class StringUtils {

	private StringUtils() {
		
	}
	
	/**
	 * Normalize a String deleting the accents
	 * @param string to rormalize
	 * @return the params string normalize
	 */
	public static String normalize(String string) {
		return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{M}", "");
	}
	
	/**
	 * Generate string for use in url
	 * @see StringUtils#methodB()
	 * @param string to generate url string
	 * @return the string for use in url
	 */
	public static String generateUrlName(String string) {
		return normalize(string.toLowerCase().replace(" ", "-"));
	}
	
}
