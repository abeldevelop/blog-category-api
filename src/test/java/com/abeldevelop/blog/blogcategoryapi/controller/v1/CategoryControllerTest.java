package com.abeldevelop.blog.blogcategoryapi.controller.v1;

import org.junit.Before;
import org.junit.Test;

import com.abeldevelop.blog.blogcategoryapi.resource.CreateCategoryRequestResource;
import com.abeldevelop.blog.blogcategoryapi.resource.UpdateCategoryRequestResource;

public class CategoryControllerTest {

	CategoryController categoryController;

	@Before
	public void setUp() {
		categoryController = new CategoryController();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void executeCreateTestOk() {
		CreateCategoryRequestResource createCategoryRequestResource = CreateCategoryRequestResource.builder().name("First Category").build();
		categoryController.executeCreate(createCategoryRequestResource);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void executeUpdateTestOk() {
		UpdateCategoryRequestResource updateCategoryRequestResource = UpdateCategoryRequestResource.builder().name("First Category").build();
		categoryController.executeUpdate("first-category", updateCategoryRequestResource);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void executeDeleteTestOk() {
		categoryController.executeDelete("first-category");
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void executeFindByCodeTestOk() {
		categoryController.executeFindByCode("first-category");
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void executeFindAllTestOk() {
		categoryController.executeFindAll(null, null, null);
	}
}
