package com.abeldevelop.blog.blogcategoryapi.repository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;

@Repository
public interface CategorySpringDataRepository extends JpaRepository<CategoryEntity, String> {

}
