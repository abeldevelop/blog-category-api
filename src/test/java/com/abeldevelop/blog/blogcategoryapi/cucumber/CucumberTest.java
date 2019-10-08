package com.abeldevelop.blog.blogcategoryapi.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features", 
		plugin = {
				"progress", 
				"json:target/cucumber-report.json"
		}
)
public class CucumberTest {

}
