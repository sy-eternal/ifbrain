package com.jzeen.travel.data.repository;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ChildShoppingmall;

public interface ChildShoppingmallRepository extends AdvancedJpaRepository<ChildShoppingmall, Integer>
{
//	@Query("select sum(c.sumPrice), d.demandName from ChildShoppingmall c inner join c.child ch inner join c.demandlevel d  where ch.id= :childid   group by  d.demandName")
//	List <Object[]> findByChildIdAndDemandlevel(@Param("childid") Integer childid);
	
//	 List<ChildShoppingmall> findByDemandlevelId(Integer demandlevel);
	
	@Query("select sum(h.sumPrice) as price , t.demandName as name from ChildShoppingmall as h inner join h.child as c inner  join h.shoppingmallCommodity as sh  inner join sh.demandLevel as t  where c.id = :childId and h.type=:type and h.payStatus=:payStatus group by t.demandName")
	 public List<Object[]> findByChildIdAndDemandlevel(@Param("childId")Integer childId,@Param("type")String type,@Param("payStatus")Integer payStatus);
	  
	//  @Query("select sum(h.price) as price  from ChildShoppingmall as h inner join h.child as c   where c.id = :childId")
	//  public BigDecimal findDemandNameSumprice(@Param("childId")Integer childId);
	 @Query("select h from ChildShoppingmall as h inner join h.child as c inner  join h.shoppingmallCommodity as sh  inner join sh.demandLevel as t  where c.id = :childId and t.id=:demandlevel and h.type=:type and h.payStatus=:payStatus")
	 List<ChildShoppingmall> findByDemandlevelIdAndChildIds( @Param("childId")Integer childId,@Param("demandlevel")Integer demandlevel,@Param("type")String type,@Param("payStatus")Integer payStatus);
	 
	
	 @Query("select  t.demandName as name,sum(h.sumPrice) as price  from ChildShoppingmall as h inner join h.child as c inner  join h.shoppingmallCommodity as sh  inner join sh.demandLevel as t  where c.id = :childId and h.type=:type and h.payStatus=:payStatus group by t.demandName")
	 public List<Object[]> findDemandNameByChildId(@Param("childId")Integer childId,@Param("type")String type,@Param("payStatus")Integer payStatus);
	
	 @Query("select sum(h.sumPrice) as price  from ChildShoppingmall as h inner join h.child as c   where c.id = :childId and h.type=:type and h.payStatus=:payStatus")
	  public BigDecimal findDemandNameSumprice(@Param("childId")Integer childId,@Param("type")String type,@Param("payStatus")Integer payStatus);
	 
	//查询产品分类环形图
	 @Query("select sum(h.sumPrice) as price ,t.commodityType as comm from ChildShoppingmall as h inner join h.child as c inner  join h.shoppingmallCommodity as sh  inner join sh.commodityMall as t  where c.id = :childId and h.type=:type and h.payStatus=:payStatus group by t.commodityType")
	 public List<Object[]> findByChildIdAndCommdity(@Param("childId")Integer childId,@Param("type")String type,@Param("payStatus")Integer payStatus);
	 
	 
	 @Query("select h from ChildShoppingmall as h inner join h.child as c   where c.id = :childId and h.type=:type")
	 List<ChildShoppingmall> findByDemandlevelIdAndChildIdsType( @Param("childId")Integer childId,@Param("type")String type);
	 
	 
	 
	 
	 
}
