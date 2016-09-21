package com.jzeen.travel.data.repository;





import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.IfbrainTask;
import com.jzeen.travel.data.entity.MaterialType;
public interface IfbrainTaskRepository extends AdvancedJpaRepository<IfbrainTask, Integer>
{
	  @Query("select c from IfbrainTask as c inner join c.courseLevel as u inner join c.courseCode as code where u.id =:courseid and code.id =:coursecodeid  and c.type='1'")
	  List<IfbrainTask> findByCouseCodeAndCourseId( @Param("courseid") Integer courseid,@Param("coursecodeid") Integer coursecodeid);
      
	  
	  /*@Query("select c from IfbrainTask as c where c.id =:id")
	  List<IfbrainTask> findByTaskId( @Param("id") Integer id);*/
	  
	  @Query("select m from  IfbrainTask as m   where m.type =:type ")
	    List<IfbrainTask> findByifbraintask(@Param("type") String type);
	  
	  @Query("select i from IfbrainTask as i inner join i.child as c inner join i.courseLevel as u inner join i.courseCode as code inner join i.materialType as ma  where  c.id =:cid and u.id =:courseid and code.id =:coursecodeid and ma.id =:materialtypeid and i.type =:type")
	  IfbrainTask findidandcourseTask(  @Param("cid") Integer cid, @Param("courseid") Integer courseid, @Param("coursecodeid") Integer coursecodeid, @Param("materialtypeid") Integer materialtypeid,@Param("type") String type);
	  @Query("select i from IfbrainTask as i inner join i.courseLevel as u inner join i.courseCode as code where u.id =:courseid and code.id =:coursecodeid and i.type =:type")
	  IfbrainTask check(@Param("courseid") Integer courseid, @Param("coursecodeid") Integer coursecodeid,@Param("type") String type);
	  
	  @Query("select i from IfbrainTask as i inner join i.child as c inner join i.courseLevel as u inner join i.courseCode as code inner join i.materialType as ma  where    u.id =:courseid and code.id =:coursecodeid and ma.id =:materialtypeid and i.type =:type")
	  IfbrainTask findidandcourseTasks(  @Param("courseid") Integer courseid, @Param("coursecodeid") Integer coursecodeid, @Param("materialtypeid") Integer materialtypeid,@Param("type") String type);
}
