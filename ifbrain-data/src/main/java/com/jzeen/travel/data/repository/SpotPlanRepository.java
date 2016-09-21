package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.data.entity.StandardGuidePlan;

public interface SpotPlanRepository extends AdvancedJpaRepository<SpotPlan, Integer>
{
    List<SpotPlan> findByDatePlanOrderId(Integer orderId);
    @Query("select d from SpotPlan as d inner join d.datePlan as datePlan  inner join datePlan.order as order  where order.id=:orderid and datePlan.id=:datePlanId")
    Iterable<SpotPlan> findSpotPlanDatePlanId(@Param("orderid")Integer orderid,@Param("datePlanId")Integer datePlanId);
    
    /**
     * 根据日期规划(datePlan)查询景点规划对象
     * @param datePlan
     * @return
     */
    public SpotPlan findBydatePlan(DatePlan datePlan);
}
