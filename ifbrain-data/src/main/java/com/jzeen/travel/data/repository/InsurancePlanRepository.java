package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.InsuranceActivity;
import com.jzeen.travel.data.entity.InsurancePlan;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.SpotPlan;

public interface InsurancePlanRepository extends AdvancedJpaRepository<InsurancePlan, Integer>
{
	//通过order查询保险活动规划
    public InsurancePlan findByOrderId(Order order);
    
    public InsurancePlan findById(Integer id);
}
