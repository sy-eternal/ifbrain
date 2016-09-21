package com.jzeen.travel.data.repository;

import org.springframework.data.jpa.repository.Query;

import com.jzeen.travel.data.entity.Route;


public interface RouteRepository extends AdvancedJpaRepository<Route, Integer>
{
    @Query("select max(id) from Route")
    public Integer getMaxId();
}
