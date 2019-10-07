package com.abeldevelop.blog.blogcategoryapi.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class StackTraceMapper {

	private static final List<String> excludeClass = Arrays.asList(
			//Tomcat internals
			"org.apache.catalina", 
			"org.apache.tomcat",
			"java.util.concurrent.ThreadPoolExecutor.runWorker",
			"java.lang.Thread.run",
			//JDK internals
			"com.sun",
		    "sun.net",
		    //dynamic invocation
		    "net.sf.cglib.proxy.MethodProxy.invoke",
		    "org.springframework.cglib.",
		    "org.springframework.transaction.",
		    "org.springframework.validation.",
		    "org.springframework.app.",
		    "org.springframework.aop.",
		    "java.lang.reflect.Method.invoke",
		    //Spring plumbing
		    "org.springframework.ws.transport",
		    "org.springframework.ws.soap.saaj.SaajSoapMessage",
		    "org.springframework.ws.client.core.WebServiceTemplate",
		    "org.springframework.web.filter"
			);
	
	public List<String> map(StackTraceElement[] stackTraceElements) {
		List<String> stackTrace = new ArrayList<>();
		for(StackTraceElement stackTraceElement : stackTraceElements) {
			if(!excludeTrace(stackTraceElement.getClassName())) {
				stackTrace.add(new StringBuilder()
		    			.append(stackTraceElement.getClassName())
		    			.append(":")
		    			.append(stackTraceElement.getLineNumber())
		    			.toString());
			}
		}
    	return stackTrace;
    }
	
	private boolean excludeTrace(String className) {
		return excludeClass.stream().anyMatch(className::startsWith);
	}
}