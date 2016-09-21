package com.jzeen.travel.wechat.pojo;

import com.jzeen.travel.data.entity.WeChatUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description:批量用户列表
 * Date: 2015年 08月 11日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class WeChatBatchUser {

    private List<WeChatUser> userList;

    public WeChatBatchUser(boolean needInit) {
        if (needInit) {
            if (this.userList == null) {
                this.userList = new ArrayList<WeChatUser>();
            }
        }
    }

    public void addUser(WeChatUser weChatUser) {
        this.userList.add(weChatUser);
    }

    public List<WeChatUser> getUserList() {
        return userList;
    }

    public void setUserList(List<WeChatUser> userList) {
        this.userList = userList;
    }
}