package com.jzeen.travel.wechat.service;

import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.website.setting.WebUrlSetting;
import com.jzeen.travel.wechat.utils.SignUtil;
import com.jzeen.travel.wechat.conf.WeChatConts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * Title: X2OUR_TRAVEL
 * Description: 用户服务类
 * Date: 2015年08月02日
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebUrlSetting webUrlSetting;

    public String bindOpenId(String openId) {

        System.out.println("user " + openId + "开始构造绑定链接");

        String content = "";

        User user = userRepository.findByWechat(openId);

        String webRoot = webUrlSetting.getRootUrl();


        if (user == null) {

            String timestamp = String.valueOf(new Date().getTime() + WeChatConts.expTime);
            String signature = SignUtil.genSHA(WeChatConts.bindToken, timestamp, openId);

            String url = webRoot + WeChatConts.bindURL + "?openId=" + openId + "&signature=" + signature + "&timestamp=" + timestamp;

            System.out.println(" url  " + url);

            content = "您还未通过嘻游网用户认证，请先点击 <a href=\"" + url + "\">用户认证</a> ，认证成功后可直接使用各种服务!该链接有效时间为30分钟。";

        } else {
            content = "您已经完成用户认证，谢谢使用！";
        }

        return content;
    }

    public String getLoginContent(String openId) {

        System.out.println("user " + openId + "开始构造登陆链接");


        String content = "";

        User user = userRepository.findByWechat(openId);

        String webRoot = webUrlSetting.getRootUrl();


        if (user == null) {

            String timestamp = String.valueOf(new Date().getTime() + WeChatConts.expTime);
            String signature = SignUtil.genSHA(WeChatConts.bindToken, timestamp, openId);

            String url = webRoot + WeChatConts.bindURL + "?openId=" + openId + "&signature=" + signature + "&timestamp=" + timestamp;

            System.out.println(" url  " + url);

            content = "您还未通过嘻游网用户认证，请先点击 <a href=\"" + url + "\">用户认证</a> ，认证成功后可直接使用各种服务!该链接有效时间为30分钟。";

        } else {

            String signature = SignUtil.genSHADoubleStr(WeChatConts.bindToken, openId);
            String url = webRoot + "/home/wechatindex" + "?userId=" + user.getId() + "&signature=" + signature;

            System.out.println(" url  " + url);
            content = user.getFirstName() + user.getLastName() + "您好！，请点击 <a href=\"" + url + "\">登陆首页</a>";
        }

        return content;
    }

    /**
     * 是否已经绑定，如果未绑定返回flase，否则返回true
     */
    public boolean hasBindOpenId(String openId) {
        return userRepository.findByWechat(openId) == null ? false : true;
    }

    /**
     * 如果未绑定，获取绑定的content，包括URL信息
     */
    public String getBindtent(String openId) {

        String content = "";
        String timestamp = String.valueOf(new Date().getTime() + WeChatConts.expTime);
        String signature = SignUtil.genSHA(WeChatConts.bindToken, timestamp, openId);

        String webRoot = webUrlSetting.getRootUrl();
        String url = webRoot + WeChatConts.bindURL + "?openId=" + openId + "&signature=" + signature + "&timestamp=" + timestamp;

        content = "您还未通过嘻游网用户认证，请先点击 <a href=\"" + url + "\">用户认证</a> ，认证成功后可直接使用各种服务!该链接有效时间为30分钟。";

        return content;
    }
}
