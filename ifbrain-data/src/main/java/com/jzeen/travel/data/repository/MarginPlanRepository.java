package com.jzeen.travel.data.repository;

import java.math.BigDecimal;

import com.jzeen.travel.data.entity.MarginPlan;
import com.jzeen.travel.data.entity.Order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MarginPlanRepository extends AdvancedJpaRepository<MarginPlan,Integer> {
    
    @Query("select ma.count from MarginPlan as ma inner join  ma.margin as m inner join ma.order as o where m.margintype = :marginid and o.id = :orderid")
    public Integer findByCount(@Param("marginid")String marginid,@Param("orderid")Integer orderid);
    
    @Query("select ma.id from MarginPlan as ma inner join ma.margin as m where m.margintype = :marginid ")
    public Integer findByCounts(@Param("marginid")String marginid);
    
    public MarginPlan findByOrder(Order orderId);
    
    public MarginPlan findByOrderId(Integer orderid);
    
   /* public MarginPlan findByMargintotalprice(BigDecimal orderIdBig);*/
    
    
}
