package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.InsuranceActivity;
import com.jzeen.travel.data.entity.InsurancePlan;
import com.jzeen.travel.data.entity.Route;
import com.jzeen.travel.data.entity.RouteInsurancePlan;
import com.jzeen.travel.data.entity.SpotPlan;

public interface RouteInsurancePlanRepository extends AdvancedJpaRepository<RouteInsurancePlan, Integer>
{ 
    public List<RouteInsurancePlan> findByRoute(Route route);
}
