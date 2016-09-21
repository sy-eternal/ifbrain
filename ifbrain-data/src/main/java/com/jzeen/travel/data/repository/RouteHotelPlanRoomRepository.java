package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.HotelPlan;
import com.jzeen.travel.data.entity.HotelPlanRoom;
import com.jzeen.travel.data.entity.HotelRoomType;
import com.jzeen.travel.data.entity.RouteHotelPlanRoom;

public interface RouteHotelPlanRoomRepository extends AdvancedJpaRepository<RouteHotelPlanRoom, Integer>
{/*
    @Query("select d from HotelPlanRoom as d inner join d.hotelPlan as o inner join o.datePlan as dataPlan  inner join dataPlan.order as order  where order.id=:orderid")
    Iterable<HotelPlanRoom> findHotelPlanRoom(@Param("orderid")Integer orderid);
    
    
    @Query("select d from HotelPlanRoom as d inner join d.hotelPlan as o inner join o.datePlan as dataPlan  inner join dataPlan.order as order  where order.id=:orderid and dataPlan.id=:datePlanId")
    Iterable<HotelPlanRoom> findHotelPlanRoomDatePlanId(@Param("orderid")Integer orderid,@Param("datePlanId")Integer datePlanId);
    
    public List<HotelPlanRoom> findByHotelPlan(HotelPlan hotelPlan);
    public List<HotelPlanRoom> findByHotelRoomType(HotelRoomType hotelRoomType);*/
}
