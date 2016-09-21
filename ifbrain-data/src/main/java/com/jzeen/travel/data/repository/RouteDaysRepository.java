package com.jzeen.travel.data.repository;

import org.springframework.data.jpa.repository.Query;

import com.jzeen.travel.data.entity.RouteDays;


public interface RouteDaysRepository  extends AdvancedJpaRepository<RouteDays, Integer>
{
    @Query("select max(id) from RouteDays")
    public Integer getMaxId();
}
