package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.BuyPlan;
import com.jzeen.travel.data.entity.CourseOrder;
import com.jzeen.travel.data.entity.GuideRate;
import com.jzeen.travel.data.entity.IfbrainIndex;
import com.jzeen.travel.data.entity.IfbrainVar;


public interface IfbrainVarReponsitory extends AdvancedJpaRepository<IfbrainVar, Integer>
{
	@Query("select i.varName as  varName, i.varValue as varValue from IfbrainVar as i inner join i.ifbrainIndex as c inner join c.child as h where h.id =:childId and c.ordinalNumber =:ordinalNumber and i.incomeType=:incomeType and c.courseLevel =:courseLevel")
	List <Object[]> findByvarnameAndvalue(@Param("childId") Integer childid,@Param("ordinalNumber") Integer ordinalNumber,@Param("incomeType") Integer incomeType,@Param("courseLevel") String courseLevel);
	
	
}
