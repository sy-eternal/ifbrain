package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.VcustomerReportView;
import com.jzeen.travel.data.entity.VdatePlanView;
import com.jzeen.travel.data.entity.VorderDatePlanView;

public interface VdatePlanViewRepository extends AdvancedJpaRepository<VdatePlanView, Integer>
{
	public List<VdatePlanView> findByTOrderId( Integer tOrderId); 
	
	@Query("select v from VdatePlanView as v where v.tOrderId = :tOrderId order by v.startDate asc")
	public List<VdatePlanView> getDatePlanViews(@Param("tOrderId") Integer tOrderId);
}
