package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.GuideActivity;
import com.jzeen.travel.data.entity.GuideRate;



public interface GuideRateRepository extends AdvancedJpaRepository<GuideRate, Integer>{

    /**
     * 根据导游费率表的导游类型进行分组查询
     */
    @Query("select g from GuideRate as g group by g.guideType")
    public List<GuideRate> findByGuideType();

	/**
	 * 根据导游活动对象查询导游费率表
	 * @param guideActivity 导游活动对象
	 * @return
	 */
	public List<GuideRate> findByGuideActivity(GuideActivity guideActivity);
	
	@Query("select g from GuideRate as g where g.guideType =:guideType")
    public GuideRate findByGuideTypes(@Param("guideType")String guideType);
	
	   /*@Query("select g.guideRatePrice from GuideRate as g where g.guideType=:司机兼导游")
	    public GuideRate findByGuideTypess()*/
}
