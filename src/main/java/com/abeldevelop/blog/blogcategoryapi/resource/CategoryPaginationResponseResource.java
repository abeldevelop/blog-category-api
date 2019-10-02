package com.abeldevelop.blog.blogcategoryapi.resource;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@ApiModel(description="Resource with pagination information")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryPaginationResponseResource {

	@ApiModelProperty(notes="Resource with pagination information", required = true, position = 0)
	@NotNull(message="The pagination cannot be null")
	private PaginationResponseResource pagination;	

	@ApiModelProperty(notes="List of categories", required = true, position = 1)
	@Singular
	private List<CategoryResponseResource> categories;
	
}
