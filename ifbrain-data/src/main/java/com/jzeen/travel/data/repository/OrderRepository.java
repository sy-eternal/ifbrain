package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.ExcursionGuideOccupied;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.StandardGuideOccupied;
import com.jzeen.travel.data.entity.User;

public interface OrderRepository extends AdvancedJpaRepository<Order, Integer>
{
    @Query("select max(id) from Order")
    public Integer getMaxId();

    
    public List<Order> findByTraveler(User traveler);

    public List<Order> findByOrderStatus(Integer orderStatus);

    public List<Order> findByOrderStatusAndStatusTimeAfter(Integer orderStatus, Date date);

    public List<Order> findByOrderStatusAndCreateTimeAfter(Integer orderStatus, Date date);

    public List<Order> findByOrderStatusAndCommitOrderTimeAfter(Integer orderStatus, Date date);

    public List<Order> findByTravelerAndOrderStatus(User traveler, Integer orderStatus);

    public Order findByActivityCode(String activityCode);

    public Order findByOrderNumberAndOrderStatus(String orderNumber, Integer orderStatus); 
    
    public Order findByOrderNumber(String orderNumber);
    public Order  findById(Integer id);
    //zyy
  @Query("select so from Guide as ue inner join ue.standardGuide  as sg inner join sg.standardGuideOccupied as so inner join so.standardGuidePlan as sp inner join sp.datePlan as da  inner join da.order as ora where ora.id= :orderId and ue.id= :id")
  List<StandardGuideOccupied> getstandardGuideOccupied(@Param("orderId") Integer orderId,@Param("id") Integer id);
  
  //当订单过期时，删除改订单下的标准导游占用导游
  @Query("select so from StandardGuideOccupied as so inner join so.standardGuidePlan as sp inner join sp.datePlan as da  inner join da.order as ora where ora.id= :orderId")
  List<StandardGuideOccupied> outstandardGuideOccupied(@Param("orderId") Integer orderId);
  //当订单过期时，删除改订单下的短途导游占用导游
  @Query("select so from ExcursionGuideOccupied as so inner join so.excursionGuidePlan as sp inner join sp.datePlan as da  inner join da.order as ora where ora.id= :orderId")
  List<ExcursionGuideOccupied> outexecursionGuideOccupied(@Param("orderId") Integer orderId);
  
  @Query("select dateplan from DatePlan as dateplan inner join dateplan.order as order  where order.id= :orderId  and order.orderStatus=2")
  List<DatePlan> findbyOrderIdAndOrderStatus(@Param("orderId") Integer orderId);
  
  @Query("select o from Order as o where o.id= :orderId  and o.amountSetailStatus=0")
  public Order  findByOrderAmountdetailsstatus(@Param("orderId") Integer orderId);
  
  @Query("select dateplan from DatePlan as dateplan inner join dateplan.order as order  where order.id= :orderId")
  public List<DatePlan> findbyOrderId(@Param("orderId") Integer orderId);
  
}
