package com.abeldevelop.blog.blogcategoryapi.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfiguration {

	/**
	 * Take the version from the POM
	 */
	@Value("${app.version}")
	private String projectVersion;
	
	public static final String API_RESPONSE_CODE_200_MESSAGE = "Successful operation";
	public static final String API_RESPONSE_CODE_201_MESSAGE = "Resource created";
	public static final String API_RESPONSE_CODE_204_MESSAGE = "Successful operation without content";
	public static final String API_RESPONSE_CODE_400_MESSAGE = "Error in Request";
	public static final String API_RESPONSE_CODE_404_MESSAGE = "Resource Not Found";
	public static final String API_RESPONSE_CODE_500_MESSAGE = "Internal Server Error";
	
	private static final String API_TITLE = "Blog Category API";
	private static final String API_DESCRIPTION = "Api to manage the categories for the blog application";
	
	private static final String API_TERMS_OF_SERVICE_URL = "https://github.com/abeldevelop/blog-category-api/blob/develop/LICENSE";
	private static final String API_LICENSE = "Apache License 2.0";
	private static final String API_LICENSE_URL = "https://github.com/abeldevelop/blog-category-api/blob/develop/LICENSE";

	
	@Bean
	public Docket apiDocket() {
		 return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.abeldevelop.blog.blogcategoryapi.controller.v1"))
			.paths(PathSelectors.any())
			.build()
			.produces(new HashSet<>(Arrays.asList(MediaType.APPLICATION_JSON_VALUE)))
			.consumes(new HashSet<>(Arrays.asList(MediaType.APPLICATION_JSON_VALUE)))
			.useDefaultResponseMessages(false)
			.apiInfo(getApiInfo())
			.tags(getTag());
	}
	
	private ApiInfo getApiInfo() {
	    return new ApiInfo(
	    		API_TITLE,
	    		API_DESCRIPTION,
	    		projectVersion,
	    		API_TERMS_OF_SERVICE_URL,
	            getContact(),
	            API_LICENSE,
	            API_LICENSE_URL,
	            Collections.emptyList());
	}
	
	private Contact getContact() {
		return new Contact("Abel Fernández", "http://www.abeldevelop.com/","abeldevelop@abeldevelop.com");
	}
	
	private Tag getTag() {
		return new Tag("Category", "Operations about categories");
	}
}
