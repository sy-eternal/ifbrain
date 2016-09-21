package com.jzeen.travel.data.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.VcustomerReportView;

public interface VcustomerReportViewRepository extends AdvancedJpaRepository<VcustomerReportView, Integer>
{
	public List<VcustomerReportView> findByOrderId(Integer orderId);
	
	@Query("select v from VcustomerReportView as v where v.orderId = :orderId order by v.seq asc")
	public List<VcustomerReportView> getVcustomerReportViews(@Param("orderId") Integer orderId);
}
