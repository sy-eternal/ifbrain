package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ShoppingmallCommodity;

public interface ShoppingmallCommodityRepository extends AdvancedJpaRepository<ShoppingmallCommodity, Integer>
{
	//获得实用类的sql语句
	@Query("select sum(h.sumPrice) as price from ChildShoppingmall as h inner join h.child as c inner  join h.shoppingmallCommodity as sh inner join sh.commodityMall cm  where c.id = :childId and cm.type=0")
	 public Object[] findByChildIdAndDemandlevel(@Param("childId")Integer childId);
	//获得思想类的sql语句
	@Query("select sum(h.sumPrice) as price from ChildShoppingmall as h inner join h.child as c inner  join h.shoppingmallCommodity as sh inner join sh.commodityMall cm  where c.id = :childId and cm.type=1")
	 public Object[] findByChildIdAndDemandlevel1(@Param("childId")Integer childId);
	

	
}
