package com.jzeen.travel.wechat.pojo;

import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description: 关注用户列表
 * Date: 2015年 08月 03日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class WeChatUserSummary {
    // 公众账号的总关注用户数
    private int total;

    // 获取的OpenID个数
    private int count;

    // OpenID列表
    private List<String> openIdList;
    // 拉取列表的后一个用户的OPENID

    private String nextOpenId;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getOpenIdList() {
        return openIdList;
    }

    public void setOpenIdList(List<String> openIdList) {
        this.openIdList = openIdList;
    }

    public String getNextOpenId() {
        return nextOpenId;
    }

    public void setNextOpenId(String nextOpenId) {
        this.nextOpenId = nextOpenId;
    }
}
