package com.jzeen.travel.data.repository;

import com.jzeen.travel.data.entity.WeChatRedpackActivity;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 08月 11日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public interface WeChatRedpackActivityRepository extends AdvancedJpaRepository<WeChatRedpackActivity, Integer> {
    WeChatRedpackActivity findByOpenid(String openid);

    WeChatRedpackActivity findByOpenidAndActType(String openid, String actType);


}