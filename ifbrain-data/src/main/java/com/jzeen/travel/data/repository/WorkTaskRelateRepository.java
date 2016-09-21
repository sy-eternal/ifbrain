package com.jzeen.travel.data.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.WorkTaskRelate;

public interface WorkTaskRelateRepository extends AdvancedJpaRepository<WorkTaskRelate,Integer> {
	public List<WorkTaskRelate> findByUser(User user);
	
	  

	  public void deleteByChildId(Integer id);
	   
	  @Query("select w from WorkTaskRelate as w inner join w.child as c where c.id = :childId")
	 public List<WorkTaskRelate> findbycheck(@Param("childId")Integer childid); 
	  public List<WorkTaskRelate> findByUserAndStatus(User user,Integer Status);

	  public List<WorkTaskRelate> findByUserAndStatusAndChild(User user,Integer Status,Child child);

	  //public WorkTaskRelate findByChildId(Integer id);
	  public List<WorkTaskRelate> findByChildId(Integer id);
	  @Query("select w.timeAdd from WorkTaskRelate as w inner join w.child as c inner join w.task as t where c.id = :childid and t.id = :taskid")
	  public Integer findByEasyTaskId(@Param("childid")Integer childid,@Param("taskid")Integer taskid);
	  
	  
	  @Query("select w from WorkTaskRelate as w inner join w.child as c where c.id = :childId")
	  public List<WorkTaskRelate> findBychildIds(@Param("childId")Integer id);
	  
	  public WorkTaskRelate findById(Integer id);
	  
	  public Integer findByTimeAdd(Integer id);
	
	  public Integer findByTimes(Integer id);
	  
	  @Query("select w from WorkTaskRelate as w inner join w.child as c where c.id = :childId")
	  public  List<WorkTaskRelate> findBychildIdss(@Param("childId")Integer id);
	  
	  @Query("select w from WorkTaskRelate as w inner join w.child as c where c.id = :childId and w.status=0")
	  public  List<WorkTaskRelate> findBychildIdsss(@Param("childId")Integer id);
	 

}
