package com.abeldevelop.blog.blogcategoryapi.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "classpath:features", 
		plugin = {
				"progress", 
				"json:target/cucumber-report.json",
				"de.monochromata.cucumber.report.PrettyReports:target/pretty-cucumber"
		}
)
public class CucumberTest {

}
