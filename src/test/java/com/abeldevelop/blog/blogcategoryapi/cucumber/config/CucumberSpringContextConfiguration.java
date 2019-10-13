package com.abeldevelop.blog.blogcategoryapi.cucumber.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.Before;
import lombok.extern.slf4j.Slf4j;

import static com.abeldevelop.blog.blogcategoryapi.cucumber.config.TestContext.CONTEXT;
@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class CucumberSpringContextConfiguration {

	@LocalServerPort
    int randomServerPort;
	
	@Before
	public void setUp(Scenario scenario) {
		CONTEXT.setTestCase(scenario.getName().substring(scenario.getName().lastIndexOf(" ")).trim());
		log.info("");log.info("");log.info("");log.info("");
		log.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
		log.info("");log.info("");log.info("");log.info("");
	}
}
