package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.InsuranceActivity;
import com.jzeen.travel.data.entity.InsurancePlan;
import com.jzeen.travel.data.entity.InsuranceRate;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.SpotPlan;

public interface InsuranceActivityRepository extends AdvancedJpaRepository<InsuranceActivity, Integer>
{
	//查询所有保险活动
	public List<InsuranceActivity> findAll();
	public InsuranceActivity findById(Integer id);
}
