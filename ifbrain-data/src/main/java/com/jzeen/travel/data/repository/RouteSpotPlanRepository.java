package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.Route;
import com.jzeen.travel.data.entity.RouteSpotPlan;
import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.data.entity.StandardGuidePlan;

public interface RouteSpotPlanRepository extends AdvancedJpaRepository<RouteSpotPlan, Integer>
{
 
}
