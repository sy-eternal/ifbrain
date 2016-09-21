package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.FilghtPlan;
import com.jzeen.travel.data.entity.Order;

public interface DatePlanRepository extends AdvancedJpaRepository<DatePlan, Integer>
{
    List<DatePlan> findByOrderId(Integer orderId);
    
    @Query("select max(d.id) from DatePlan as d inner join d.order  as o where o.id = :orderid")
    Integer findByOrderIds(@Param("orderid")Integer orderid);
    
    @Query("select d from DatePlan as d inner join d.order as o where d.startDate=:startDate and d.endDate=:endDate and o.id=:orderid")
    List<DatePlan> findByEndDate(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("orderid")Integer orderid);
   
    @Query("select d from DatePlan as d  inner join d.order as order  where order.id=:orderid and d.startDate > :startDates and d.endDate < :endDates")
    List<DatePlan> findStartDateAndEndDate(@Param("orderid")Integer orderid,@Param("startDates")Date startDates,@Param("endDates")Date endDates);
    
    public DatePlan  findById(Integer id);
    
    @Query("select d.id from DatePlan as d  inner join d.order as order  where order.id=:orderid and d.startDate > :startDates and d.endDate < :endDates")
    List<DatePlan> findStartDateAndEndDates(@Param("orderid")Integer orderid,@Param("startDates")Date startDates,@Param("endDates")Date endDates);
    
    @Query("select d from DatePlan as d inner join d.order  as o where o.id = :orderid")
    DatePlan findByOrderIdss(@Param("orderid")Integer orderid);
    
    @Query("select d.id from DatePlan as d  where  not exists (select ds from DatePlan as ds inner join ds.order as order WHERE ds.startDate>:startDates and ds.endDate <:endDates and order.id=:orderId)")
    Integer findNotStartDateAndEndDate(@Param("orderId")Integer orderid,@Param("startDates")Date startDates,@Param("endDates")Date endDates);
   
    @Query("select d.id from DatePlan as d  inner join d.order as order  where order.id=:orderid and d.startDate = :startDates and d.endDate = :endDates")
    List<DatePlan> findByStartDateAndEndDates(@Param("orderid")Integer orderid,@Param("startDates")Date startDates,@Param("endDates")Date endDates);

    public void deleteByOrderId(Integer id);
}
