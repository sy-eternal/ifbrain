package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.FilghtPlan;
import com.jzeen.travel.data.entity.FlightDetails;

public interface FilghtDetailsRepository extends AdvancedJpaRepository<FlightDetails, Integer>
{
    @Query("select fd from FlightDetails as fd inner join fd.filghtPlan as d   where d.id = :id")
    List<FlightDetails> findById(@Param("id") Integer id);
    
}
