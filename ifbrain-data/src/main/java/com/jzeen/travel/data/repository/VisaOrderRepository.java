package com.jzeen.travel.data.repository;

import java.util.List;

import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.VisaOrder;

public interface VisaOrderRepository extends AdvancedJpaRepository<VisaOrder, Integer> {
   /* public List<VisaOrder> findByUser(User user);*/

    public VisaOrder findById(Integer id);
    
     public   VisaOrder  findByOrderNumber(String orderNumber);
    
}
