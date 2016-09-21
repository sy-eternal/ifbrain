package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.FilghtPlan;
import com.jzeen.travel.data.entity.FlightDetails;
import com.jzeen.travel.data.entity.RouteFlightDetails;

public interface RouteFilghtDetailsRepository extends AdvancedJpaRepository<RouteFlightDetails, Integer>
{
   /* @Query("select fd from RouteFlightDetails as fd inner join fd.routeFilghtPlan as d   where d.id = :id")
    List<RouteFlightDetails> findById(@Param("id") Integer id);
    */
}
