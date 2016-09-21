package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.GuideOrder;
import com.jzeen.travel.data.entity.User;

public interface GuideOrderRepository extends AdvancedJpaRepository<GuideOrder, Integer>
{
    public GuideOrder findByGuideordernum(String orderNumber);
    public List<GuideOrder> findByUser(User user);
    //public List<GuideOrder> findByOrderStatus(Integer OrderStatus);
    public List<GuideOrder>  findByorderstatus(Integer orderStatus);
   
}
