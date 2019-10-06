package com.abeldevelop.blog.blogcategoryapi.resource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.abeldevelop.blog.blogcategoryapi.component.ErrorMessageProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description="Update Category resource")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateCategoryRequestResource {

	@ApiModelProperty(notes="Name of the category", example="First Category", required = true, position = 0)
	@NotNull(message = ErrorMessageProperties.CATEGORY_NAME_NOT_NULL)
	@Size(min = 3, max = 25, message = ErrorMessageProperties.CATEGORY_NAME_SIZE)
	private String name;
	
}
