package com.jzeen.travel.data.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.IfbrainIndex;


public interface IfbrainIndexReponsitory extends AdvancedJpaRepository<IfbrainIndex, Integer>
{
	@Query("select i from IfbrainIndex as i inner join i.child as c where c.id = :childid and i.courseLevel = :courseLevel  and i.ordinalNumber = :ordinalNumber and i.classId =:classId")
	List <IfbrainIndex> findByCheck(@Param("childid") Integer childid,@Param("courseLevel") String courseLevel,@Param("ordinalNumber") Integer ordinalNumber,@Param("classId") Integer classId);

	//and cla.id=:classid and i.ordinalNumber=:ordinalnumber and c.id=:childId
	@Query("select i from IfbrainIndex as i inner join i.child as c inner join c.courseClass as cla where c.id = i.child.id and cla.id=:classid and i.ordinalNumber=:ordinalnumber and c.id=:childId and i.courseLevel=:courselevel")
	IfbrainIndex findByTiaojian(@Param("classid")Integer classid,@Param("ordinalnumber")Integer ordinalnumber,@Param("childId")Integer childId,@Param("courselevel")String courselevel);
	@Query("select i from IfbrainIndex as i  where i.classId=:classid")
	List<IfbrainIndex> findByClassId(@Param("classid")Integer classid);
	@Query("select i from IfbrainIndex as i inner join i.child as c where c.id=:id and i.courseLevel=:courselevel order by i.ordinalNumber asc")
	List<IfbrainIndex> findByChildIdAndCourseLevel(@Param("id")Integer id,@Param("courselevel")String courselevel);
	
	IfbrainIndex findByOrdinalNumberAndClassIdAndChildIdAndCourseLevel(Integer ordinalNumber,Integer classId,Integer childId,String courseLevel);

	/*@Query("select i from IfbrainIndex as i inner join i.child as c where c.id = :childid")
	List<IfbrainIndex> findByChildIdAndCourseLevel(@Param("childid") Integer childid);*/
	@Query("select i from IfbrainIndex as i inner join i.child as c where i.ordinalNumber = :ordinalNumber and c.id =:childId and i.courseLevel =:courseLevel")
	IfbrainIndex findByOrdinalNumberAndChildIdAndCourseLevel(@Param("ordinalNumber")Integer ordinalNumber,@Param("childId")Integer childId,@Param("courseLevel")String courseLevel);
	
	@Query("select i.ordinalNumber as  ordinalNumber, i.income as income from IfbrainIndex as i inner join i.child as c  where c.id =:childId  and i.courseLevel =:courseLevel order by i.ordinalNumber desc")
	List <Object[]> findByincome(@Param("childId") Integer childid,@Param("courseLevel") String courseLevel);
	@Query("select i.ordinalNumber as  ordinalNumber, i.expense as expense from IfbrainIndex as i inner join i.child as c  where c.id =:childId  and i.courseLevel =:courseLevel order by i.ordinalNumber desc")
	List <Object[]> findByexpense(@Param("childId") Integer childid,@Param("courseLevel") String courseLevel);

	
	@Query("select i from IfbrainIndex as i inner join i.child as c where  i.ordinalNumber <= :ordinalNumber  and  i.courseLevel = :courseLevel and c.id= :childId  order by i.ordinalNumber asc")
     List<IfbrainIndex>  findByOrdinalNumberAndChildIdAndCourseLevel1(@Param("ordinalNumber")Integer ordinalNumber,@Param("childId")Integer childId,@Param("courseLevel")String courseLevel);
	@Query("select i from IfbrainIndex as i inner join i.child as c where  i.courseLevel = :courseLevel and c.id= :childId  order by i.ordinalNumber asc")
    List<IfbrainIndex>  findByOrdinalNumberAndChildIdAndCourseLevel2(@Param("childId")Integer childId,@Param("courseLevel")String courseLevel);
	@Query("select i from IfbrainIndex as i inner join i.child as c where  i.courseLevel = :courseLevel and c.id= :childId  order by i.ordinalNumber asc")
    List<IfbrainIndex>  findByOrdinalNumberAndChildIdAndCourseLevel3(@Param("childId")Integer childId,@Param("courseLevel")String courseLevel);
	

	
	@Query("select (sum(i.income)-sum(i.expense)) as expense from IfbrainIndex as i inner join i.child as c where   c.id= :childId")
	BigDecimal findSumPriceByChildIds(@Param("childId") Integer childid);
	
	//计算余额
	
		@Query("select (sum(i.income)-sum(i.expense)) as expense from IfbrainIndex as i inner join i.child as c where   c.id= :childId  and i.courseLevel= :courseLevel  and i.ordinalNumber <= :ordinalnumber")
		BigDecimal findSumPriceByChildIdsAndCourselevelAndOrdernumber(@Param("childId") Integer childid,@Param("courseLevel") String courseLevel,@Param("ordinalnumber")Integer ordinalnumber);
		@Query("select sum(i.expense) as expense from IfbrainIndex as i inner join i.child as c where   c.id= :childId  and i.courseLevel= :courseLevel  and i.ordinalNumber <= :ordinalnumber")
		BigDecimal findExpendseByChildIdsAndCourselevelAndOrdernumber(@Param("childId") Integer childid,@Param("courseLevel") String courseLevel,@Param("ordinalnumber")Integer ordinalnumber);
		@Query("select sum(i.income) as income from IfbrainIndex as i inner join i.child as c where   c.id= :childId  and i.courseLevel= :courseLevel  and i.ordinalNumber <= :ordinalnumber")
		BigDecimal findSumpriceByChildIdsAndCourselevelAndOrdernumber(@Param("childId") Integer childid,@Param("courseLevel") String courseLevel,@Param("ordinalnumber")Integer ordinalnumber);
		
		//查询最后一条记录
	@Query("select i from IfbrainIndex as i inner join i.child as c where c.id=:id and i.courseLevel=:courselevel order by i.ordinalNumber desc")
	List<IfbrainIndex> findByChildIdAndCourseLeveldesc(@Param("id")Integer id,@Param("courselevel")String courselevel);
	//根据id查询最后一条记录
	@Query("select i from IfbrainIndex as i inner join i.child as c where c.id=:childId order by i.id desc")
	List<IfbrainIndex> findByIfbrainIdDesc(@Param("childId")Integer id);
	
	@Query("select i from IfbrainIndex as i inner join i.child as c where  i.ordinalNumber >= :ordinalNumber  and  i.courseLevel = :courseLevel and c.id= :childId  order by i.ordinalNumber asc")
    List<IfbrainIndex>  findByOrdinalNumberAndChildIdAndCourseLevelCheckShop(@Param("ordinalNumber")Integer ordinalNumber,@Param("childId")Integer childId,@Param("courseLevel")String courseLevel);
	
	
	@Query("select i from IfbrainIndex as i inner join i.child as c where i.ordinalNumber = :ordinalNumber  and i.courseLevel =:courseLevel")
	IfbrainIndex findByOrdinalNumberAndCourseLevel(@Param("ordinalNumber")Integer ordinalNumber,@Param("courseLevel")String courseLevel);
	
	IfbrainIndex findByOrdinalNumberAndClassIdAndCourseLevel(Integer ordinalNumber,Integer classId,String courseLevel);
	 @Query("select  distinct i.classId,i.classId from IfbrainIndex as i inner join i.child as c  where c.id =:childId  ")
	List<Object[]> findClassIdByChildId(@Param("childId")Integer childId);
	
	
	@Query("select i from IfbrainIndex as i inner join i.child as c where c.id=:id and i.classId=:classId order by i.ordinalNumber asc")
	List<IfbrainIndex> findByChildIdAndClassId(@Param("id")Integer id,@Param("classId")Integer courselevel);

}
