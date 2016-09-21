package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.data.entity.SpotPlanTicket;

public interface SpotPlanTicketRepository extends AdvancedJpaRepository<SpotPlanTicket, Integer>
{
	public List<SpotPlanTicket> findBySpotPlan(SpotPlan spotPlan);
}
