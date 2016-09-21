package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.GuideActivityPlan;
import com.jzeen.travel.data.entity.GuideTypePlan;
import com.jzeen.travel.data.entity.Order;

public interface GuideTypePlanRepository extends AdvancedJpaRepository<GuideTypePlan, Integer>{

    /**
     * 根据城市和导游类型查询导游相关信息
     * @param guideType 导游类型
     * @param city  城市
     * @return  导游类型规划的集合
     */
    @Query("select gtp from GuideTypePlan as gtp inner join gtp.guideActivityPlan as gap where gap.city= :cityId and gtp.guideType=:guideType")
    List<GuideTypePlan> findByGuideTypeAndCity(@Param("guideType") String guideType,@Param("city")City city);
    
    /**
     * 根据订单号查询派单状态是否包含未派单
     */
    @Query("select gtp from GuideTypePlan as gtp inner join gtp.guideActivityPlan as gap inner join gap.datePlan as dp inner join dp.order as od where od.orderNumber=:orderNumber and gtp.appointedStatus=0")
    List<GuideTypePlan> findByOrderNumAndop(@Param("orderNumber") String orderNumber);
    /**
     * 根据订单状态查询
     */
    @Query("select gtp from GuideTypePlan as gtp inner join gtp.guideActivityPlan as gap inner join gap.datePlan as dp inner join dp.order as od where od.orderStatus=4 and gtp.appointedStatus=0")
    List<GuideTypePlan> findByOrder();
    /**
     * 根据导游活动规划主键查询导游类型规划
     */
//    public GuideTypePlan findByGuideActivityPlan(GuideActivityPlan guideActivityPlan);
    
    public List<GuideTypePlan> findByGuideActivityPlan(GuideActivityPlan guideActivityPlan);
    public List<GuideTypePlan> findByid(Integer id);
}
