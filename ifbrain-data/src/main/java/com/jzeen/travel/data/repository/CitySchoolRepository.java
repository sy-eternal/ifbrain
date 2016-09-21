package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.CitySchool;

public interface CitySchoolRepository extends AdvancedJpaRepository<CitySchool, Integer>
{
    //判断城市名称是否重复
	
	   @Query("select count(*) from CitySchool as c where c.cityName = :cityName ")
	    public long findByCity(@Param("cityName")String cityName);
    
}
