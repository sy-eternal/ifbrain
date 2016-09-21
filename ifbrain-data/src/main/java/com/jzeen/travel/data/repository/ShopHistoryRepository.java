package com.jzeen.travel.data.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;




import com.jzeen.travel.data.entity.BuyPlan;
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.ShopHistory;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Commodity;
import com.jzeen.travel.data.entity.User;

public interface ShopHistoryRepository extends AdvancedJpaRepository<ShopHistory, Integer>
{
	List<ShopHistory> findByChild(Child childId);
	
	@Query("select b from ShopHistory as b inner join b.child as c  where c.id =:child order by b.price asc")
	List<ShopHistory> findByChildIdss(@Param("child")Integer child);
	
	  public void deleteByChildId(Integer id);
	
	 /**
	 * @param 查询各个分类的总金额
	 * @return
	 */
	@Query("select t.name as name,sum(h.price) as price  from ShopHistory as h inner join h.child as c inner join h.commodityType as t where c.id = :childId group by t.name")
	  public List<Object[]> findByChildId(@Param("childId")Integer childId);
	  
	  
	  @Query("select sum(h.price) as price  from ShopHistory as h inner join h.child as c  where c.id = :childId ")
	  public List<Object[]> findByChildIds(@Param("childId")Integer childId);
	  
	  /**
		 * @param 查询各个分类的购买数量
		 * @return
		 */
		@Query("select t.name as name,sum(h.buyNumber) as buyNumber  from ShopHistory as h inner join h.child as c inner join h.commodityType as t where c.id = :childId group by t.name")
		  public List<Object[]> findByChildId1(@Param("childId")Integer childId);
		  
		  
		  @Query("select sum(h.price) as price  from ShopHistory as h inner join h.child as c  where c.id = :childId ")
		  public List<Object[]> findByChildIds1(@Param("childId")Integer childId);
		  
		  @Query("select t.name as name,sum(h.price) as price  from ShopHistory as h inner join h.child as c inner join h.commodityType as t  where c.id = :childId group by t.name")
		  public List<Object[]> findshopByChildId(@Param("childId")Integer childId);
		  @Query("select sum(h.price) as price  from ShopHistory as h inner join h.child as c   where c.id = :childId")
		  public BigDecimal findshopSumprice(@Param("childId")Integer childId);
}
