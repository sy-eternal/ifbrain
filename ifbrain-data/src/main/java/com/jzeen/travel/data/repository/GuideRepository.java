package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.User;

public interface GuideRepository extends AdvancedJpaRepository<Guide, Integer>
{
    @Query("select g from Guide as g inner join g.user as u where u.email=:email and u.userType=:usertype")
    Guide findByUser(@Param("email") String email,@Param("usertype") Integer usertype);
    Guide findByUserId(Integer userid);
    @Query("select g from Guide as g inner join g.hostCity as c where c.id=:id")
    List<Guide> findByCity(@Param("id") Integer id);
}
