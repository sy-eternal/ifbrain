package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.Airline;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.FilghtPlan;
import com.jzeen.travel.data.entity.FlightDetails;
import com.jzeen.travel.data.entity.HotelPlanRoom;
import com.jzeen.travel.data.entity.RouteFilghtPlan;

public interface RouteFilghtPlanRepository extends AdvancedJpaRepository<RouteFilghtPlan, Integer>
{
    /*@Query("select d from RouteFilghtPlan as d inner join d.route as route  inner join datePlan.order as order  where order.id=:orderid and datePlan.startDate > :startDates and datePlan.endDate < :endDates")
    Iterable<RouteFilghtPlan> findFilghtPlan(@Param("orderid")Integer orderid,@Param("startDates")Date startDates,@Param("endDates")Date endDates);
    @Query("select d from RouteFilghtPlan as d inner join d.datePlan as datePlan  inner join datePlan.order as order  where order.id=:orderid and datePlan.id=:datePlanId ")
    Iterable<RouteFilghtPlan> findFilghtPlanDatePlanId(@Param("orderid")Integer orderid,@Param("datePlanId")Integer datePlanId);
    @Query("select d from FlightPlan as d inner join fd.filghtDetails as d   where d.id = :id")
    List<FlightDetails> findById(@Param("id") Integer id);
    
    //根据航空公司代码查询
    RouteFilghtPlan  findByairlineCodes(Airline airline);*/
}
