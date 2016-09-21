package com.jzeen.travel.data.repository;


import com.jzeen.travel.data.entity.City;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jzeen.travel.data.entity.Spot;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "spot", path = "spot")
public interface SpotRepository extends AdvancedJpaRepository<Spot, Integer> {
    @Query("select max(id) from Spot")
    public Integer find();

    public Spot findById(Integer id);

    Spot findBySpotsname(String spotsname);

     List<Spot> findByCityid(City cityid);
     /*  @Query("select s from spot as s inner join ")
    public List<Spot> findByThemeId(Integer id);*/
}
