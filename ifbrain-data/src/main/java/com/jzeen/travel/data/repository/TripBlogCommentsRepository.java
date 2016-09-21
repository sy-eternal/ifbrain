package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.TripBlog;
import com.jzeen.travel.data.entity.TripBlogComments;


public interface TripBlogCommentsRepository  extends AdvancedJpaRepository<TripBlogComments, Integer>{

	
	List<TripBlogComments> findBytripBlog(TripBlog tripBlog);
}
