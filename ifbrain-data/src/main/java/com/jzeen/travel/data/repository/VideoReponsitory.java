package com.jzeen.travel.data.repository;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.BuyPlan;
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.CourseOrder;
import com.jzeen.travel.data.entity.GuideRate;
import com.jzeen.travel.data.entity.Video;


public interface VideoReponsitory extends AdvancedJpaRepository<Video, Integer>
{
	List<Video> findByClassId(Integer classid);
	Video findByOrdinalNumberAndChildIdAndCourseLevelAndClassId(Integer ordinalNumber,Integer classId2,String courselevel,Integer classid);
	Video findByOrdinalNumberAndChildIdAndCourseLevel(Integer ordinalNumber,Integer classId2,String courselevel);
	
	List<Video> findByOrdinalNumberAndCourseLevelAndClassId(Integer ordinalNumber,String courselevel,Integer classid);
}
