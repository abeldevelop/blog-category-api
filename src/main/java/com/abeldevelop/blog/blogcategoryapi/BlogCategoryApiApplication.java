package com.abeldevelop.blog.blogcategoryapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BlogCategoryApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogCategoryApiApplication.class, args);
	}

}
