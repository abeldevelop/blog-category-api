package com.abeldevelop.blog.blogcategoryapi.repository.springdata;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;

@Repository
public interface CategorySpringDataRepository extends JpaRepository<CategoryEntity, String> {

	@Query(value = "SELECT * FROM CATEGORIES WHERE name = :query", countQuery = "SELECT count(*) FROM CATEGORIES WHERE LASTNAME = ?1", nativeQuery = true)
	Page<CategoryEntity> findByLastname(@Param("query") String query, Pageable pageable);

}
