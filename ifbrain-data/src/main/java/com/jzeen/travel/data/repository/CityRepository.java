package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;

public interface CityRepository extends AdvancedJpaRepository<City, Integer>
{
    
    public City findByCityName(String cityName);
    public City  findByAirportCode(String airportCode);

    public List<City> findByCountryId(Integer countryId);
    
    @Query("select count(*) from City as c inner join c.country as co where c.cityName = :cnName and co.id = :name")
    public long findByCountry(@Param("cnName")String cnName,@Param("name")int name);
       
    @Query("select c from City as c where  c.id not in :ids")
    public List<City> find(@Param("ids") List<Integer> ids);
    
    @Query("select c from City as c  inner join c.country as co order by co.id desc")
    public List<City> findAll();
    
    @Query("select c from City as c  inner join c.country as co where co.name=:name")
    public List<City> findByCountryName(@Param("name")String name);
    
}
