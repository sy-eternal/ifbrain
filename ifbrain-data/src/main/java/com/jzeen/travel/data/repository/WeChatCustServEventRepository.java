package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.WeChatCustServEvent;
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
public interface WeChatCustServEventRepository extends AdvancedJpaRepository<WeChatCustServEvent, Integer> {

    @Query("select w from WeChatCustServEvent as w where  w.openid = :openid and  w.clickTime > :clickTime")
    List<WeChatCustServEvent> findByOpenidClickTime(@Param("openid") String openid, @Param("clickTime") int clickTime);

}