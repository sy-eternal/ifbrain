package com.jzeen.travel.data.repository;

import java.math.BigDecimal;

import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.Route;
import com.jzeen.travel.data.entity.RouteMarginPlan;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RouteMarginPlanRepository extends AdvancedJpaRepository<RouteMarginPlan,Integer> {
   
    @Query("select ma.count from RouteMarginPlan as ma inner join  ma.margin as m inner join ma.route as o where m.margintype = :marginid and o.id = :routeId")
    public Integer findByCount(@Param("marginid")String marginid,@Param("routeId")Integer routeId);
    
    @Query("select ma.id from RouteMarginPlan as ma inner join ma.margin as m where m.margintype = :marginid ")
    public Integer findByCounts(@Param("marginid")String marginid);
    
   /* public MarginPlan findByMargintotalprice(BigDecimal orderIdBig);*/
    public RouteMarginPlan findByroute(Route route);
    
}
