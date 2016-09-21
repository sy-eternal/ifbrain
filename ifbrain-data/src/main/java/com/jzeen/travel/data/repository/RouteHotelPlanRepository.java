package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.HotelActivity;
import com.jzeen.travel.data.entity.HotelPlan;
import com.jzeen.travel.data.entity.RouteHotelPlan;

public interface RouteHotelPlanRepository extends AdvancedJpaRepository<RouteHotelPlan, Integer> {/*
    List<HotelPlan> findByDatePlanOrderId(Integer orderId);
    
    List<HotelPlan>  findByCity(City city);*/
    
    
    
    
    
}
