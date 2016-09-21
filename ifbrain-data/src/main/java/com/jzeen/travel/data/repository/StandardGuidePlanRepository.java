package com.jzeen.travel.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.RentalPlan;
import com.jzeen.travel.data.entity.StandardGuidePlan;

public interface StandardGuidePlanRepository extends AdvancedJpaRepository<StandardGuidePlan, Integer>
{
    @Query("select d from StandardGuidePlan as d inner join d.datePlan as datePlan  inner join datePlan.order as order  where order.id=:orderid and datePlan.id=:datePlanId")
    Iterable<StandardGuidePlan> findStandardGuidePlanDatePlanId(@Param("orderid")Integer orderid,@Param("datePlanId")Integer datePlanId);
}
