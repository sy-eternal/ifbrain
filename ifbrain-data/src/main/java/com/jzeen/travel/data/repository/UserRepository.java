package com.jzeen.travel.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jzeen.travel.data.entity.ExcursionGuideOccupied;
import com.jzeen.travel.data.entity.StandardGuideOccupied;
import com.jzeen.travel.data.entity.User;

public interface UserRepository extends AdvancedJpaRepository<User, Integer> {
    User findByUserTypeAndEmailAndPassword(Integer userType, String email, String password);
    
    @Query("select u from User u where u.userType=:userType and u.email=:email and u.password=:password or u.userType=:userType and u.mobile=:email and u.password=:password")
    User findByUserTypeAndEmailAndMobile(@Param("userType")Integer userType, @Param("email")String email,@Param("password") String password);

   /* User findByEmailAndPassword(String email, String password);
    User findByMobileAndPassword(String mobile, String password);*/
    User findByEmailAndPassword(String email, String password);
    User findByMobileAndPassword(String mobile, String password);
   
    User findByFirstName(String firstName);
    User findByEmail(String email);
    User findByImageId(Integer userid);
    User findByEmailAndUserType(String email,Integer usertype);
    User findByUserType(Integer userType);
    
    
    User findByLastNameAndPassword(String lastname, String password);
    //User findByMobile(String mobile);
    
    User findByWechat(String wechat);
    
    //根据手机号码和手机短信验证码查询该用户是否存在
    User  findByMobileAndCode(String mobile,String code);
    
    
    @Query("select u from User u where u.mobile=:mobile")
    User  findByMobile(@Param("mobile") String mobile);
    /**
     * 根据电话短信的唯一标识查询
     * @param identifier
     * @return
     */
    User findByIdentifier(String identifier);
    @Query("select sgo from DatePlan as datePlan inner join datePlan.standardGuidePlans as sgp inner join sgp.standardGuideOccupieds as sgo inner join sgo.standardGuide as sg inner join sg.user as u where u.id = :Id and datePlan.order.orderStatus=3")
    List<StandardGuideOccupied> getOccupiedDate(@Param("Id") Integer Id);

    @Query("select ego from DatePlan as datePlan inner join datePlan.excursionGuidePlans as exp inner join exp.excursionGuideOccupieds as ego inner join ego.excursionGuide  as eg inner join eg.guide as u where u.id = :Id and datePlan.order.orderStatus=3 ")
    List<ExcursionGuideOccupied> getOccupiedDate1(@Param("Id") Integer Id);
    User findByGuideId(Integer guideId);
    User findImageById(Integer id);
}
