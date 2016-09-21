package com.jzeen.travel.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ActivityOrder;

public interface ActivityOrderRepository extends AdvancedJpaRepository<ActivityOrder, Integer>
{
	@Query("select g from ActivityOrder as g where g.orderNumber = :ordernumber")
    public ActivityOrder findByOrderNumber(@Param("ordernumber")String ordernumber);
}
