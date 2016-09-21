package com.jzeen.travel.service.wecaht;

import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.WeChatUser;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.WeChatUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Title: X2OUR_TRAVEL
 * Description: 用户信息操作服务
 * Date: 2015年 08月 11日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
@Service
public class WeChatUserService {
    @Autowired
    private WeChatUserRepository weChatUserRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * 用户关注事件
     *
     * @param weChatUser
     * @return
     */
    public  void userSubscribe(WeChatUser weChatUser) {
        if (weChatUser == null || weChatUser.getOpenid() == null){
            return;
        }

        String openId = weChatUser.getOpenid();

        //查询微信用户表中是否有数据
        WeChatUser dbUser = weChatUserRepository.findByOpenid(openId);
        if (dbUser == null) {
            // 没有数据，新增一条记录
            User user = new User();
            user.setFirstName("");
            user.setLastName(weChatUser.getNickname());
            user.setWechat(weChatUser.getOpenid());
            user.setUserType(1);
            user.setActiveStatus(1);
            user.setCreateTime(new Date());
            userRepository.save(user);
            weChatUser.setUserId(user.getId());
            weChatUser.setCreate_time(new Date());
            weChatUser.setFirst_subscribe_time(weChatUser.getSubscribe_time());
            weChatUserRepository.save(weChatUser);
        }else {
            // 更新数据
            weChatUser.setId(dbUser.getId());
            weChatUser.setUserId(dbUser.getUserId());
            weChatUser.setFirst_subscribe_time(dbUser.getFirst_subscribe_time());
            weChatUserRepository.save(weChatUser);
        }
    }
    /**
     * 用户取消关注事件
     *
     * @param openId
     * @return
     */
    public void userUnSubscribe(String openId) {
        //查询微信用户表中是否有数据
        WeChatUser weChatUser = weChatUserRepository.findByOpenid(openId);
        if (weChatUser == null) {
            return;
        }

        weChatUser.setCancel_time(Long.valueOf(new Date().getTime() / 1000).intValue());
        weChatUser.setSubscribe(0);
        weChatUserRepository.save(weChatUser);
    }

      public static void main(String[] args) {
    	  
    	 
    	 
	}
    
    
}