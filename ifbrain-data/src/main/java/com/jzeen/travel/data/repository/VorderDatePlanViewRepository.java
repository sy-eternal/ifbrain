package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.VcustomerReportView;
import com.jzeen.travel.data.entity.VorderDatePlanView;

public interface VorderDatePlanViewRepository extends AdvancedJpaRepository<VorderDatePlanView, Integer>
{
	public List<VorderDatePlanView> findByTOrderId( Integer tOrderId); 
	
	
	@Query("select v from VorderDatePlanView as v where v.tOrderId = :tOrderId order by v.startDate asc")
	public List<VorderDatePlanView> getVorderDatePlanView(@Param("tOrderId") Integer tOrderId);
}
