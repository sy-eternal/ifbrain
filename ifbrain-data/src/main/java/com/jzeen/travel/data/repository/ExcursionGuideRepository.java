package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import com.jzeen.travel.data.entity.StandardGuide;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ExcursionGuide;
import com.jzeen.travel.data.entity.User;

public interface ExcursionGuideRepository extends AdvancedJpaRepository<ExcursionGuide, Integer>
{
    /**
     * 获取指定日期和城市中未被占用的短途导游。
     * 
     * @param date
     *            日期。
     * @param cityId
     *            城市标识。
     * @return 指定日期和城市中未被占用的短途导游。
     */
    @Query("select eg from ExcursionGuide as eg inner join eg.guide as u where u.hostCity.id = :cityId and u.approvalstatus = 2 and not exists (select ego from ExcursionGuideOccupied as ego where ego.excursionGuide.id = eg.id and ego.occupiedDate = :date)")
    List<ExcursionGuide> getUnoccupied(@Param("date") Date date, @Param("cityId") Integer cityId);
    
    /**
     * 获取指定日期和城市中未被占用的有车的短途导游。
     *
     * modify by limin
     * 增加导游为已经审核的导游条件
     *
     * @param date
     *            日期。
     * @param cityId
     *            城市标识。
     * @return 指定日期和城市中未被占用的有车的短途导游。
     */
    /**
     * zyy
     * */
    @Query("select eg from ExcursionGuide as eg inner join eg.guide as u where u.hostCity.id = :cityId and u.approvalstatus = 2 and eg.hasCar = 1 and not exists (select ego from ExcursionGuideOccupied as ego where ego.excursionGuide.id = eg.id and ego.occupiedDate = :date)")
    List<ExcursionGuide> getUnoccupiedHasCar(@Param("date") Date date, @Param("cityId") Integer cityId);
    
    
    
    /**
     * 出发地临时接送导游显示全部 导游主地点为该出发城市的 有车的、愿意短途接送-是、出发日期 未被占用的导游姓名
     * @param date2
     * @param fromCityInt
     * @return
     */
    /**
     * zyy
     * */
    @Query("select u from ExcursionGuide as sg inner join sg.guide as u inner join u.guideCar as car inner join u.user as s  where u.hostCity.id = :fromCityInt and s.userType = 2 and sg.hasCar=1 and car.excursionStatus=1 and not exists (select sgo from ExcursionGuideOccupied as sgo where sgo.excursionGuide.id = sg.id and sgo.occupiedDate = :date2)")
    List<User> getUnoccupiedDiffenrenJiaotong(@Param("date2") Date date2, @Param("fromCityInt") Integer fromCityInt);
    
    /**
     *到达地临时接送导游显示全部 导游主地点为该到达城市的 有车的、愿意短途接送-是、到达日期 未被占用的导游姓名
     * @param date2
     * @param fromCityInt
     * @return
     */
    @Query("select u from ExcursionGuide as sg inner join sg.guide as u inner join u.guideCar as car inner join u.user as s where u.hostCity.id = :toCityInt and s.userType = 2  and  sg.hasCar=1 and car.excursionStatus=1 and not exists (select sgo from ExcursionGuideOccupied as sgo where sgo.excursionGuide.id = sg.id and sgo.occupiedDate = :date2)")
    List<User> getUnoccupiedArrive(@Param("date2") Date date2, @Param("toCityInt") Integer toCityInt);


    @Query("select c from ExcursionGuide as c inner join c.guide as u where  u.id in :ids")
    List<ExcursionGuide> findByUserIds(@Param("ids") List<Integer> ids);

}
