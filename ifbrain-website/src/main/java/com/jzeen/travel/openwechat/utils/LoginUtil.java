package com.jzeen.travel.openwechat.utils;


import com.jzeen.travel.openwechat.conf.WeChatConts;
import com.jzeen.travel.wechat.pojo.OAuth2Token;
import com.jzeen.travel.wechat.pojo.SNSUserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Title: X2OUR_TRAVEL
 * Description: 网页授权获取用户基本信息工具类
 * Date: 2015年 08月 03日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class LoginUtil {

    private static Logger log = LoggerFactory.getLogger(LoginUtil.class);

    /**
     * 获取网页授权凭证
     *
     * @param code
     * @return WeixinAouth2Token
     */
    public static OAuth2Token getOauth2AccessToken(String code) {
        OAuth2Token wat = null;
        // 拼接请求地址
        String requestUrl = WeChatConts.codeGetTokenUrl;
        requestUrl = requestUrl.replace("APPID", WeChatConts.appID);
        requestUrl = requestUrl.replace("SECRET", WeChatConts.appsecret);
        requestUrl = requestUrl.replace("CODE", code);

        // 获取网页授权凭证
        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                wat = new OAuth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }

    /**
     * 刷新网页授权凭证
     *
     * @param refreshToken
     * @return OAuth2Token
     *
     */
    public static OAuth2Token refreshOauth2AccessToken(String refreshToken) {
        OAuth2Token wat = null;
        // 拼接请求地址
        String requestUrl = WeChatConts.refreshTokenUrl;
        requestUrl = requestUrl.replace("APPID", WeChatConts.appID);
        requestUrl = requestUrl.replace("REFRESH_TOKEN", refreshToken);

        // 刷新网页授权凭证
        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                wat = new OAuth2Token();
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInt("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("刷新网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }

    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken
     *            网页授权接口调用凭证
     * @param openId
     *            用户标识
     * @return SNSUserInfo
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null;

        String requestUrl = WeChatConts.oauthGetUserInfoUrl;

        // 拼接请求地址
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);

        // 通过网页授权获取用户信息
        JSONObject jsonObject =HttpRequestUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));

            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return snsUserInfo;
    }




}
