package com.abeldevelop.blog.blogcategoryapi.config;

public enum Environments {

    LOCAL("local"),
    DEV("dev"),
    QA("qa"),
    PRO("pro");
	
	private String env;
	
	private Environments(String env) {
		this.env = env;
	}
	
	public String getEnvironment() {
		return env;
	}

}