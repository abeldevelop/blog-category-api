package com.abeldevelop.blog.blogcategoryapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Builder
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Category {

	@EqualsAndHashCode.Include
	@NonNull
	private String code;
	
	@NonNull
	private String name;
	
}
