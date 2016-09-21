package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.Activity;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.CityPlan;

public interface ActivityRepository extends AdvancedJpaRepository<Activity, Integer>
{
	  @Query("select a from Activity as a where  a.activityType =:activityType")
	    public List<Activity> findByActivityType(@Param("activityType") Integer activityType);
}
