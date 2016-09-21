package com.jzeen.travel.data.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.GuideCarManage;

public interface GuideCarManageRepository extends AdvancedJpaRepository<GuideCarManage, Integer>
{
    /**
     * 获取用户的导游专车类型。
     * 
     * @param userId
     *            用户主键。
     * @return 用户的导游专车类型。
     */
    @Query("select gc.guideCarManage from GuideCar as gc inner join gc.user as u where u.id = :userId")
    GuideCarManage findByUserId(@Param("userId") Integer userId);
}
