package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.FreeVideo;

public interface FreeVideoRepository extends AdvancedJpaRepository<FreeVideo, Integer>
{
	 @Query("select f from FreeVideo as f inner join f.courseLevel as u inner join f.courseCode as code inner join  f.materialType as type where u.id =:courseid and code.id =:coursecodeid  and type.id =:typeid order by code.ordinalNumber asc")
	 List < FreeVideo> findByCouseCodeAndCourseId( @Param("courseid") Integer courseid,@Param("coursecodeid") Integer coursecodeid,@Param("typeid") Integer typeid);
	 
	 
	 @Query("select f from FreeVideo as f inner join f.courseLevel as u inner join f.courseCode as code inner join  f.materialType as type where u.id =:courseid   and type.id =:typeid order by code.ordinalNumber asc")
	List < FreeVideo> findByCouseLevelAndType( @Param("courseid") Integer courseid,@Param("typeid") Integer typeid);
	 
	 @Query("select f from FreeVideo as f inner join f.courseLevel as u inner join f.courseCode as code where u.id =:courseid  and u.type =:utype   order by code.ordinalNumber asc")
		List < FreeVideo> findByCouseLevel( @Param("courseid") Integer courseid,@Param("utype") String utype);
	 
	 @Query("select f from FreeVideo as f inner join f.courseLevel as u inner join f.courseCode as code where u.id =:courseid and code.id =:codeid")
		List < FreeVideo> findByCouseLevelAndOrdinalNumber( @Param("courseid") Integer courseid,@Param("codeid") Integer codeid);
	 @Query("select f from FreeVideo as f inner join f.materialType  as material inner join f.courseLevel as u inner join f.courseCode as code where u.id =:courseid and code.id =:codeid and material.id =:materialid")
		List < FreeVideo> findByCouseLevelAndOrdinalNumberAndMaterialType( @Param("courseid") Integer courseid,@Param("codeid") Integer codeid,@Param("materialid") Integer materialid);


}
