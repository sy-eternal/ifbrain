package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.EasyTask;


public interface EasyTaskRepository extends AdvancedJpaRepository<EasyTask, Integer>
{
	EasyTask findById(Integer id);
	
	//类型获取难以程度
	@Query("select e.id from EasyTask as e where e.id=:id")
	public Integer findbyids(@Param("id")Integer id);
	/*
	@Query("select e from EasyTask as e inner join e.workTaskRelate as w where w.id=:id")*/
	public EasyTask findByWorkTaskRelateId(Integer id);
	
}
