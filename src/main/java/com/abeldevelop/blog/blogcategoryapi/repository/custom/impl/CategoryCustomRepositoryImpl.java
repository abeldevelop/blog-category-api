package com.abeldevelop.blog.blogcategoryapi.repository.custom.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.abeldevelop.blog.blogcategoryapi.domain.PageRequest;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResponse;
import com.abeldevelop.blog.blogcategoryapi.domain.PaginationResult;
import com.abeldevelop.blog.blogcategoryapi.domain.SortDirection;
import com.abeldevelop.blog.blogcategoryapi.domain.SortRequest;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity;
import com.abeldevelop.blog.blogcategoryapi.entity.CategoryEntity_;
import com.abeldevelop.blog.blogcategoryapi.repository.custom.CategoryCustomRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

	private final EntityManager entityManager;
	
	@Override
	public PaginationResult<CategoryEntity> executeFindAll(PageRequest pageRequest, String query) {
		List<CategoryEntity> categories = getCategories(pageRequest, query);
		PaginationResponse pagination = getPagination(pageRequest, query, categories.size());
		return new PaginationResult<>(pagination, categories);
	}

	private PaginationResponse getPagination(PageRequest pageRequest, String query, int numberOfElements) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		Root<CategoryEntity> root = criteriaQuery.from(CategoryEntity.class);
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(criteriaBuilder.and(getPredicates(criteriaBuilder, root, query)));
		Long totalElements = entityManager.createQuery(criteriaQuery).getSingleResult();
		
		return PaginationResponse.builder()
				.page(pageRequest.getPagination().getPage())
				.size(pageRequest.getPagination().getSize())
				.numberOfElements(numberOfElements)
				.totalElements(totalElements)
				.build();
	}
	
	private List<CategoryEntity> getCategories(PageRequest page, String query) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CategoryEntity> criteriaQuery = criteriaBuilder.createQuery(CategoryEntity.class);
		Root<CategoryEntity> root = criteriaQuery.from(CategoryEntity.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.and(getPredicates(criteriaBuilder, root, query)));
		criteriaQuery.orderBy(getOrders(criteriaBuilder, root, page.getSort()));
		return entityManager
				.createQuery(criteriaQuery)
				.setFirstResult(page.getPagination().getPage() * page.getPagination().getSize())
				.setMaxResults(page.getPagination().getSize())
				.getResultList();
	}
	
	private List<Order> getOrders(CriteriaBuilder criteriaBuilder, Root<CategoryEntity> root, SortRequest sort) {
		
		List<Order> orderList = new ArrayList<>();
		
		if(StringUtils.isEmpty(sort)) {
			orderList.add(criteriaBuilder.desc(root.get(CategoryEntity_.name)));
			return orderList;
		}
		
		if(sort.getDirection().equals(SortDirection.ASC)) {
			orderList.add(criteriaBuilder.asc(root.get(sort.getColumn())));
		} else {
			orderList.add(criteriaBuilder.desc(root.get(sort.getColumn())));
		}
		
		return orderList;
	}
	
	private Predicate[] getPredicates(CriteriaBuilder criteriaBuilder, Root<CategoryEntity> root, String query) {
		List<Predicate> predicates = new ArrayList<>();
		addPredicateByQuery(criteriaBuilder, root, predicates, query);
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void addPredicateByQuery(CriteriaBuilder criteriaBuilder, Root<CategoryEntity> root, List<Predicate> predicates, String query) {
		List<Predicate> predicatesQuery = new ArrayList<>();
		if(query == null) {
			return;
		}
		predicatesQuery.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")), "%" + query.toUpperCase() + "%"));
		predicates.add(predicatesQuery.get(0));
	}
}
