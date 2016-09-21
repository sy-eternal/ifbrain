package com.jzeen.travel.data.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.PlanOrder;

public interface PlanOrderRepository extends AdvancedJpaRepository<PlanOrder, Integer>{

	/**
	 * 根据order查询主题规划表的信息
	 */
	public PlanOrder findByOrder(Order order);
	//徒步导游
//	@Query("select p from PlanOrder as p inner join p.order as o where o.id=:orderId and p.planOfferName=:'徒步旅游' or p.planOfferName=:'司机兼导游'")
//	public PlanOrder findByOrderId(@Param("orderId") Integer orderId);
}
