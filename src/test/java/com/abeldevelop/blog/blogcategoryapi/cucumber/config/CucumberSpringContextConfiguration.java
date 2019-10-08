package com.abeldevelop.blog.blogcategoryapi.cucumber.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;

import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class CucumberSpringContextConfiguration {

	@LocalServerPort
    int randomServerPort;
	
	@Before
	public void setUp() {
		log.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
	}
}
