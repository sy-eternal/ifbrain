package com.jzeen.travel.data.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.StandardGuide;
import com.jzeen.travel.data.entity.User;

public interface StandardGuideRepository extends AdvancedJpaRepository<StandardGuide, Integer>
{
    /**
     * 获取指定日期和城市中未被占用的标准导游。
     * 
     * @param date
     *            日期。
     * @param cityId
     *            城市标识。
     * @return 指定日期和城市中未被占用的标准导游。
     */
    @Query("select sg from StandardGuide as sg inner join sg.user as u  where u.hostCity.id = :cityId and u.approvalstatus = 2 and not exists (select sgo from StandardGuideOccupied as sgo where sgo.standardGuide.id = sg.id and sgo.occupiedDate = :date)")
    List<StandardGuide> getUnoccupied(@Param("date") Date date, @Param("cityId") Integer cityId);
    
    /**
     * 获取指定日期和城市中未被占用的有车的标准导游。
     *
     * modify by limin
     * 增加导游为已经审核的导游条件
     * 
     * @param date
     *            日期。
     * @param cityId
     *            城市标识。
     * @return 指定日期和城市中未被占用的有车的标准导游。
     */
    @Query("select sg from StandardGuide as sg inner join sg.user as u where u.hostCity.id = :cityId and u.approvalstatus = 2 and sg.hasCar = 1 and not exists (select sgo from StandardGuideOccupied as sgo where sgo.standardGuide.id = sg.id and sgo.occupiedDate = :date)")
    List<StandardGuide> getUnoccupiedHasCar(@Param("date") Date date, @Param("cityId") Integer cityId);
    
    /**
     * 出发地导游
     * 出发城市和到达城市相同,获取导游主地点为该出发城市,出发日期未被占用的导游
     * 
     * 
     */
    @Query("select u from StandardGuide as sg inner join sg.user as u inner join u.user as s where u.hostCity.id = :fromCityInt and s.userType = 2 and not exists (select sgo from StandardGuideOccupied as sgo where sgo.standardGuide.id = sg.id and sgo.occupiedDate = :date2)")
    List<User> getUnoccupiedGuide(@Param("date2") Date date2, @Param("fromCityInt") Integer fromCityInt);
    
    
    /**
     * 出发城市和到达城市相同,获取导游主地点为该出发城市,出发日期未被占用和有车的导游
     * 
     * 
     */
    @Query("select u from StandardGuide as sg inner join sg.user as u inner join u.user as s where u.hostCity.id = :fromCityInt and s.userType = 2 and sg.hasCar=1 and not exists (select sgo from StandardGuideOccupied as sgo where sgo.standardGuide.id = sg.id and sgo.occupiedDate = :date2)")
    List<User> getUnoccupiedAndHasCar(@Param("date2") Date date2, @Param("fromCityInt") Integer fromCityInt);
    
    /**
     * 出发城市和到达城市不相同,获取导游主地点为该出发城市,出发日期未被占用和愿意到其他地方-是
     * 
     * 
     */
    @Query("select u from StandardGuide as sg inner join sg.user as u inner join u.user as s where u.hostCity.id = :fromCityInt and s.userType = 2 and u.toothercity=1 and not exists (select sgo from StandardGuideOccupied as sgo where sgo.standardGuide.id = sg.id and sgo.occupiedDate = :date2)")
    List<User> getUnoccupiedAndDifferent(@Param("date2") Date date2, @Param("fromCityInt") Integer fromCityInt);
    
    /**
     * 出发城市和到达城市不相同,获取导游主地点为该出发城市,选择的 标准导游专车,出发日期未被占用和愿意到其他地方-是和有车的导游
     * 
     * 
     */
    @Query("select u from StandardGuide as sg inner join sg.user as u  inner join u.user as s where u.hostCity.id = :fromCityInt and s.userType = 2 and u.toothercity=1 and  sg.hasCar=1 and not exists (select sgo from StandardGuideOccupied as sgo where sgo.standardGuide.id = sg.id and sgo.occupiedDate = :date2)")
    List<User> getUnoccupiedDiffenrenJiaotong(@Param("date2") Date date2, @Param("fromCityInt") Integer fromCityInt);
    
    /**
     * 到达地导游
     */
    /**
     * 到达地导游显示全部 导游主地点为该到达城市的 到达日期 未被占用的导游姓名
     * 
     */
    @Query("select u from StandardGuide as sg inner join sg.user as u inner join u.user as s where u.hostCity.id = :toCityInt and s.userType = 2  and not exists (select sgo from StandardGuideOccupied as sgo where sgo.standardGuide.id = sg.id and sgo.occupiedDate = :date2)")
    List<User> getUnoccupiedArriveCity(@Param("date2") Date date2,@Param("toCityInt") Integer toCityInt);
    
    /**
     * 到达地导游
     */
    /**
     *如果交通方式 选择的 标准导游专车 只显示导游主地点为该到达城市的未被占用的、有车的导游姓名。
     * 
     */
    @Query("select u from StandardGuide as sg inner join sg.user as u inner join u.user as s where u.hostCity.id = :toCityInt and s.userType = 2  and  sg.hasCar=1 and not exists (select sgo from StandardGuideOccupied as sgo where sgo.standardGuide.id = sg.id and sgo.occupiedDate = :date2)")
    List<User> getUnoccupiedArriveCityHasCar(@Param("date2") Date date2,@Param("toCityInt") Integer toCityInt);


    @Query("select c from StandardGuide as c inner join c.user as u where  u.id in :ids")
    List<StandardGuide> findByids(@Param("ids") List<Integer> ids);

}
