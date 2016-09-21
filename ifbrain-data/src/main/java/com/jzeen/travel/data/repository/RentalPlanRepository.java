package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.FilghtPlan;
import com.jzeen.travel.data.entity.InsurancePlan;
import com.jzeen.travel.data.entity.RentalPlan;

public interface RentalPlanRepository extends AdvancedJpaRepository<RentalPlan, Integer>
{

    public RentalPlan findById(Integer id);
    @Query("select d from RentalPlan as d inner join d.datePlan as datePlan  inner join datePlan.order as order  where order.id=:orderid and datePlan.id=:datePlanId")
    Iterable<RentalPlan> findRentalPlanDatePlanId(@Param("orderid")Integer orderid,@Param("datePlanId")Integer datePlanId);
}
