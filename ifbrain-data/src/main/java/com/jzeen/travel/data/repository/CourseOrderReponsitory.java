package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.BuyPlan;
import com.jzeen.travel.data.entity.CourseOrder;
import com.jzeen.travel.data.entity.GuideRate;


public interface CourseOrderReponsitory extends AdvancedJpaRepository<CourseOrder, Integer>
{
	/*  @Query("select c from CourseOrder as c inner join c.course as co  where co.id =:courseid ")
	  CourseOrder findByOrderId(@Param("child")Integer child);*/
  /* CourseOrder findByOrderId();*/
	

	
	@Query("select g from CourseOrder as g where g.orderNumber = :ordernumber")
    public CourseOrder findByOrderNumber(@Param("ordernumber")String ordernumber);
}
