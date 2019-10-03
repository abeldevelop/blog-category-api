package com.abeldevelop.blog.blogcategoryapi.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description="Category resource")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryResponseResource {

	@ApiModelProperty(notes="Code of the category", example="first-category", required = true, position = 0)
	@NotNull(message = "The category code cannot be null")
	@Size(min = 3, max = 25, message = "The category name must be between 3 and 25 characters")
	private String code;
	
	@ApiModelProperty(notes="Name of the category", example="First Category", required = true, position = 1)
	@NotNull(message = "The category name cannot be null")
	@Size(min = 3, max = 25, message = "The category name must be between 3 and 25 characters")
	private String name;
	
}
