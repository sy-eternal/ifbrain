package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.WeChatUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 08月 11日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public interface WeChatUserRepository extends AdvancedJpaRepository<WeChatUser, Integer> {
    WeChatUser findByOpenid(String openid);

    @Query("select w from WeChatUser as w where  w.subscribe = 1 and  w.first_subscribe_time > :fromTime")
    List<WeChatUser> findSendRedPackUser(@Param("fromTime") int fromTime);
    
    WeChatUser findByUserId(Integer userid);
}