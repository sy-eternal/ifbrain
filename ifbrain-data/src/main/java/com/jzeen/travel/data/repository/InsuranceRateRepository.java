package com.jzeen.travel.data.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jzeen.travel.data.entity.InsuranceActivity;
import com.jzeen.travel.data.entity.InsurancePlan;

import java.util.List;


import com.jzeen.travel.data.entity.InsuranceActivity;

import com.jzeen.travel.data.entity.InsuranceRate;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.SpotPlan;

public interface InsuranceRateRepository extends AdvancedJpaRepository<InsuranceRate, Integer>
{

	//通过order查询保险活动规划
//    public List<insurancePlans> findByOrderId(Order order);
	public InsuranceRate findById(Integer id);
	
	@Query("select i from InsuranceRate as i group by i.insuranceDuration ")
	public List<InsuranceRate> findInsuranceDuration();
	
	@Query("select i from InsuranceRate as i group by i.holderType ")
	public List<InsuranceRate> findHolderType();

    //查询有多个保险类型
    public List<InsuranceRate> findByInsuranceActivity(InsuranceActivity insuranceActivity);

}
