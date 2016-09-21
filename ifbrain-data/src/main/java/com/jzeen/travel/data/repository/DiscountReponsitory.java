package com.jzeen.travel.data.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.BuyPlan;
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.CourseOrder;
import com.jzeen.travel.data.entity.Discount;
import com.jzeen.travel.data.entity.GuideRate;


public interface DiscountReponsitory extends AdvancedJpaRepository<Discount, Integer>
{
	Discount findDiscountPriceAndDiscountStatusByDiscountCode(String  discountcount);
	
}
