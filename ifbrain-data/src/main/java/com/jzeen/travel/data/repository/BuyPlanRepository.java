package com.jzeen.travel.data.repository;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.BuyPlan;

public interface BuyPlanRepository extends AdvancedJpaRepository<BuyPlan, Integer>
{ 
	@Query("select b from BuyPlan as b inner join b.child as c  where c.id =:child   order by b.price asc")
	List<BuyPlan> findByChildIdss(@Param("child")Integer child);
	
	
	@Query("select b from BuyPlan as b inner join b.child as c  where c.id =:child and b.planStatus=0  order by b.price asc")
	List<BuyPlan> findByChildBuyPlanId(@Param("child")Integer child);
	
	 public void deleteByChildId(Integer id);
	 BuyPlan findByChildId(Integer id);
	 
}
