package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jzeen.travel.data.entity.TripBlog;

public interface TripBlogRepository extends AdvancedJpaRepository<TripBlog, Integer> {

	@Query("select c from TripBlog as c order by createTime desc")
	List<TripBlog> findFirst2();
} 
