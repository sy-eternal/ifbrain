package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseCode;


public interface CourseReponsitory extends AdvancedJpaRepository<Course, Integer>
{
	Course findPriceById(Integer id);
	@Query("select distinct courseLevel from Course ")
	List<Course> findByCourseList();/*
	List<Course> findOrdinalNumberByCourseLevel(String courseLevel);
	List<Course> findOrdinalNumberByCourseLevel(String courseLevel);*/
	@Query("select c  from Course as c where c.id =:id ")
	Course findByCourseLevel(@Param("id") Integer id);
	Course findByCourseLevel(String courseLevel);
	//根据课程级别查询按课程序号排序的课程序号
	@Query("select c  from CourseCode as c inner join c.courseId as cs where cs.id =:id order by c.ordinalNumber asc")
	List<CourseCode> findByCourseCodeid(@Param("id") Integer id);
	
	String findCourseLevelById(Integer id);
	List<Course> findByType(String type);
	
	@Query("select c  from Course as c where c.id =:id and c.type =:type")
	Course findByCourseLevelList(@Param("id") Integer id,@Param("type") String type);
}
