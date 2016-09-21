package com.jzeen.travel.data.repository;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.DefineTask;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.WorkTaskRelate;

public interface DefineTaskRepository extends AdvancedJpaRepository<DefineTask,Integer> {
	public List<DefineTask> findByUser(User user);
	public List<DefineTask> findByUserAndChild(User user,Child child);
	  public void deleteByChildId(Integer id);
/*	  @Query("select w from WorkTaskRelate as w inner join w.child as c where c.id = :childId")
	 public List<WorkTaskRelate> findbycheck(@Param("childId")Integer childid); 
	  public List<WorkTaskRelate> findByUserAndStatus(User user,Integer Status);*/
	  /*@Query("select w,sum(timeAdd) from HistoryTask as w where c.id = :childId")
	  public List<HistoryTask> findByChild();*/
/*
	  @Query("select h.taskName ,sum(h.timesAdd),sum(h.taskTimes) from HistoryTask as h inner join h.child as c where c.id = :childId group by h.taskName")
	  public List<HistoryTask> findByChildId(@Param("childId")Integer childId);*/

	 /* @Query("select  h.taskName as taskName,sum(h.timesAdd) as timesAdd ,sum(h.taskTimes) as taskTimes from HistoryTask as h inner join h.child as c where c.id = :childId group by h.taskName")
	  public List<Object[]> findByChildId(@Param("childId")Integer childId);*/
	  //根据id查询历史记录信息
	  @Query("select h from DefineTask as h inner join h.child as c where c.id = :childId")
	  public List<DefineTask> findByChildIds(@Param("childId")Integer childId);
	  
	  @Query("select h from DefineTask as h inner join h.child as c where c.id = :childId  and h.taskstatus = :status")
	  public List<DefineTask> findByChildAndTaskstatus(@Param("childId")Integer childId,@Param("status")Integer status);
 
	  public List<DefineTask>  findByChildId(@Param("childId")Integer childId);
	  
	  
	  //根据孩子
	  
	  
	 /* @Query("select sum(h.timesAdd) from HistoryTask as h inner join h.child  as c where c.id = :childId and h.taskName =:taskName")
	    Integer getsumadd(@Param("childId")Integer childId,@Param("taskName") String taskName);
	  
	  @Query("select sum(h.taskTimes) from HistoryTask as h inner join h.child  as c where c.id = :childId and h.taskName =:taskName")
	    Integer getsumtimes(@Param("childId")Integer childId,@Param("taskName") String taskName);*/
	  
	  /*@Query("select distinct h.taskName from HistoryTask as h inner join h.child as c where c.id = :childId")
	  String gettaskname(@Param("childId")Integer childId);*/
	  
	  //计算孩子对应的代币收入总和
	  @Query("select sum (h.tokennumbers) from DefineTask as h inner join h.child as c where c.id =:childid and h.taskstatus =:status and  h.taskTimes =:taskTimes" )
	  BigDecimal findSumTokenByChildId(@Param("childid")Integer childid,@Param("status")Integer status,@Param("taskTimes")Integer taskTimes);
	  @Query("select sum(d.tokennumbers)  from DefineTask as d  inner join d.child as c where c.id =:childid" )
	  BigDecimal findByTokennumbers(@Param("childid")Integer childid);
	  //money总数
	  @Query("select d.tokennumbers  from DefineTask as d  where d.id =:id  " )
      public  Integer findByTokennumbersmoney(@Param("id")Integer id);
	  
	 @Query("select d  from DefineTask as d  inner join  d.child as c where c.id =:childid" )
	  public List<DefineTask> findByTaskTimes(@Param("childid")Integer childid);

	 
	 
	 @Query("select d.timesAdd  from DefineTask as d  where d.id =:id" )
	  public Integer findBymoneyTaskTimes(@Param("id")Integer id);
	 @Query("select d.taskTimes  from DefineTask as d  where d.id =:id" )
	  public Integer findBymoneyTaskTimeAdds(@Param("id")Integer id);
	  //计算孩子对应的完成任务和总任务的，获得的代币数
	  @Query("select sum(h.tokennumbers)  from DefineTask as h  inner join h.child as c where c.id =:childid  and h.taskstatus =:status and h.taskTimes =h.timesAdd" )
	  BigDecimal findSumTokenByChildIdAndTaskstatus(@Param("childid")Integer childid,@Param("status")Integer status);
	  
	  @Query("select h  from DefineTask as h  inner join h.child as c where c.id =:childid  and h.taskstatus =:status and h.taskTimes =h.timesAdd" )
	  public List<DefineTask> findByChildIdAndTaskstatusAndTimesAdd(@Param("childid")Integer childid,@Param("status")Integer status);
}
