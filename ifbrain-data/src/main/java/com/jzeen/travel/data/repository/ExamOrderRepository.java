package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;







import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.ExamOrder;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.VisaOrder;

public interface ExamOrderRepository extends AdvancedJpaRepository<ExamOrder, Integer>
{
    public   ExamOrder  findByOrderNumber(String orderNumber);
    public   ExamOrder  findByChildIdAndOrderStatus(Integer user,Integer orderStatus);
    
    
    public   ExamOrder  findByUserLastNameAndOrderStatus(String user,Integer orderStatus);
}
