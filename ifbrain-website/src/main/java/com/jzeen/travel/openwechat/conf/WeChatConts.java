package com.jzeen.travel.openwechat.conf;

import com.jzeen.travel.wechat.pojo.AccessToken;
import com.jzeen.travel.wechat.utils.CommonUtil;
import com.jzeen.travel.wechat.utils.HttpRequestUtil;


/**
 * Title: X2OUR_TRAVEL
 * Description: 常量类
 * Date: 2015年 08月 03日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author sunyan.sunny@x2our.com
 */
public class WeChatConts {


    // ----Oauth页面授权start-----------------------------------------------------------------
   
    public static final String appID = "wx2c5e95fda2b3dff5";
    public static final String appsecret =  "d4624c36b6795d1d99dcf0547af5443d";
    
    // 通过code换取页面授权 access_token URL
    public static final String codeGetTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

    // 刷新access_token
    public static final String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

    // 通过网页授权获取用户信息
    public static final String oauthGetUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";

    
}
