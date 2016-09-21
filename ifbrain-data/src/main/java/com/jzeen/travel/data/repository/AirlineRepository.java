package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.Airline;


public interface AirlineRepository extends AdvancedJpaRepository<Airline, Integer> 
{

    /**
     * 根据航空公司代码查询航空公司
     */
    Airline findByAirlineCode(String airlineCode);
    
    /**
     * 根据航空公司查询航空公司
     * 
     */
    Airline  findByAirlineChinaName(String airlineChinaName);
    
    
    /**
     * 根据航空公司英文名查询
     */
    Airline findByAirlineEnglishName(String airlineEnglishName);
}
