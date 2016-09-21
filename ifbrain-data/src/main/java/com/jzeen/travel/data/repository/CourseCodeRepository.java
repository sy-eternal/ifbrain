package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.CourseCode;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CourseCodeRepository extends AdvancedJpaRepository<CourseCode, Integer> {
	 @Query("select c from CourseCode as c inner join c.courseId as u where u.courseLevel =:courseLevel ")
     List<CourseCode> findbycourseCode(@Param("courseLevel") String courseLevel);
	 @Query("select c from CourseCode as c inner join c.courseId as u where u.id =:id ")
	  List<CourseCode> findbycourseCodeid(@Param("id") Integer id);
	 @Query("select c from CourseCode as c inner join c.courseId as u where u.id =:courseid and c.ordinalNumber =:OrdinalNumber and c.lessonName =:LessonName")
	  CourseCode findByOrdinalNumberAndLessonNameAndCourseId(@Param("OrdinalNumber") String OrdinalNumber,@Param("LessonName")String LessonName,@Param("courseid")Integer courseid);
	  @Query("select c from CourseCode as c inner join c.courseId as u where c.ordinalNumber =:ordinalNumber and u.id =:courseId")
	  CourseCode findByOrdinalNumberAndCourseId( @Param("ordinalNumber") String ordinalNumber,@Param("courseId") Integer courseId);
	/*  @Query("select c from CourseCode as c inner join c.courseId as u where c.ordinalNumber =:ordinalNumber and u.id =:courseId ")
	 List<CourseCode>  findByOrdinalNumberAndCourseId(@Param("ordinalNumber")String ordinalNumber,@Param("courseId")Integer courseId);*/
}
