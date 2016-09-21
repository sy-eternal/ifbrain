package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.GuideActivityPlan;
import com.jzeen.travel.data.entity.RouteGuideActivityPlan;


public interface RouteGuideActivityPlanRepository extends AdvancedJpaRepository<RouteGuideActivityPlan, Integer>{
	 /**
     * 根据订单状态查询
     *//*
    @Query("select gap from GuideActivityPlan as gap inner join gap.datePlan as dp inner join dp.order as od where od.orderStatus=4")
    List<GuideActivityPlan> findByOrder();
    @Query("select gap from GuideActivityPlan as gap inner join gap.datePlan as dp inner join dp.order as od where od.id=:id")
    List<GuideActivityPlan> findByOrderId(@Param("id") Integer id);*/

}
