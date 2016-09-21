package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.BuyPlan;
import com.jzeen.travel.data.entity.CourseClass;
import com.jzeen.travel.data.entity.CourseOrder;
import com.jzeen.travel.data.entity.GuideRate;


public interface CourseClassReponsitory extends AdvancedJpaRepository<CourseClass, Integer>
{
	List <CourseClass> findByCourseLevel(String courselevel);
	@Query("select c from CourseClass as c  where c.id=:cid")
	List <CourseClass> findByclassId(@Param("cid")Integer cid);
}
