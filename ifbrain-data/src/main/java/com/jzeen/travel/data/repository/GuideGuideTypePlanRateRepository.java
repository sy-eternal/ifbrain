package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.jzeen.travel.data.entity.GuideActivity;
import com.jzeen.travel.data.entity.GuideGuidetypePlanRelate;
import com.jzeen.travel.data.entity.GuideRate;
import com.jzeen.travel.data.entity.Order;

public interface GuideGuideTypePlanRateRepository extends AdvancedJpaRepository<GuideGuidetypePlanRelate, Integer>{

    
	/**
	 *按照时间查询
	 * 
	 */
    public List<GuideGuidetypePlanRelate> findByCreateTime(Date date);
	
}
