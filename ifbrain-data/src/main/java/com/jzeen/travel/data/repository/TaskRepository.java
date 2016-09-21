package com.jzeen.travel.data.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.InsuranceRate;
import com.jzeen.travel.data.entity.Task;
public interface TaskRepository extends AdvancedJpaRepository<Task, Integer> {
	public Task findById(Integer id);
	
	public Task findByName(String name);
	 @Query("select w.name from Task as w inner join w.workTask as c where c.id = :childId")
	  public String findBytaskNameId(@Param("childId")Integer id);
	 
	 
	 @Query("select w.name from Task as w inner join w.workTask as c where c.id = :cid")
	  public String findBytaskNameIds(@Param("cid")Integer id);
	
}
