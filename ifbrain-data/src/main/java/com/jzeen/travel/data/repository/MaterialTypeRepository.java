package com.jzeen.travel.data.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.IfbrainTask;
import com.jzeen.travel.data.entity.MaterialType;

public interface MaterialTypeRepository extends AdvancedJpaRepository<MaterialType, Integer>
{
	MaterialType findByMaterialName(String materialName);
	
	 @Query("select i from IfbrainTask i inner join i.courseCode as c inner join i.courseLevel as co where co.id=:id order by  c.ordinalNumber asc")
	 List<IfbrainTask> findifbraintaskbyType(@Param("id") Integer id);
	 
	 
	 MaterialType findById(Integer id);
/*	 @Query("select m from MaterialType m inner join m.ifbraintask as  i  where i.id=:id")
	 List<MaterialType> findifbrainCoursebyType(@Param("id") Integer id);*/
   
}
