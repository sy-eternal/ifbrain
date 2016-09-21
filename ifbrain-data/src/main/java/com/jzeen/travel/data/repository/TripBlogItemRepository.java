package com.jzeen.travel.data.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.GuideImageRelate;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.SpotThemeRelate;
import com.jzeen.travel.data.entity.TripBlog;
import com.jzeen.travel.data.entity.TripBlogItem;

public interface TripBlogItemRepository extends AdvancedJpaRepository<TripBlogItem, Integer> {

	@Query("select c from TripBlogItem as c  inner join c.tripBlog as tb where tb.id=:tripBlogId ")
	public List<TripBlogItem> findBytripBlogId(@Param("tripBlogId") Integer id);
	
/*	@Query("select c from TripBlogItem as c  inner join c.tripBlog as tb where tb.id=:tripBlogId")*/
	public List<TripBlogItem> findFirst2BytripBlogId(Integer id);
}
