package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.RouteSpotPlan;
import com.jzeen.travel.data.entity.RouteSpotPlanTicket;
import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.data.entity.SpotPlanTicket;

public interface RouteSpotPlanTicketRepository extends AdvancedJpaRepository<RouteSpotPlanTicket, Integer>
{
	public List<RouteSpotPlanTicket> findByRouteSpotPlan(RouteSpotPlan routeSpotPlan);
}
