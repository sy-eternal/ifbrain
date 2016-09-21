package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.data.entity.TripBlogItem;
import com.jzeen.travel.data.entity.TripBlogItemImageRelate;

public interface TripBlogItemImageRelateRepository extends AdvancedJpaRepository<TripBlogItemImageRelate, Integer> {

	List<TripBlogItemImageRelate> findBytripblogItem (TripBlogItem tripblogItem);
}
