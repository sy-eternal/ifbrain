package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.data.entity.WeChatUser;
import com.jzeen.travel.wechat.conf.WeChatConts;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Title: X2OUR_TRAVEL
 * Description:多客服工具类
 * Date: 2015年 08月 1９日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class MutiCustServUtil {

    private static Logger log = LoggerFactory.getLogger(MutiCustServUtil.class);

    /**
     * 获取用户会话状态
     *
     * @param openId
     *            用户标识
     * @return WeChatUserInfo
     */
    public static void getKfSession(String openId) {
        WeChatUser userInfo = null;
        // 拼接请求地址
        String requestUrl = WeChatConts.GET_KF_SESSION.replace("ACCESS_TOKEN", WeChatConts.getTokenInst().getToken()).replace("OPENID", openId);

        // 获取用户信息
        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {

                int createtime = jsonObject.getInt("createtime");
                int errcode = jsonObject.getInt("errcode");
                String errmsg = jsonObject.getString("errmsg");
                String kf_account = jsonObject.getString("kf_account");

                log.info("获取多客服会话信息，createtime:{} errcode:{} errmsg:{} kf_account:{}", createtime, errcode, errmsg, kf_account);

            } catch (Exception e) {
                int errcode = jsonObject.getInt("errcode");
                String errmsg = jsonObject.getString("errmsg");
                log.error("获取多客服会话信息失败， errcode:{} errmsg:{} ", errcode, errmsg);
            }
        }
    }

    /**
     * 关闭用户客服会话状态
     *
     * @param openid
     * @param kf_account
     */

    public static void closeKfSeesion(String openid, String kf_account) {
        // 拼接请求地址
        String requestUrl = WeChatConts.CLOSE_KF_SESSION.replace("ACCESS_TOKEN", WeChatConts.getTokenInst().getToken());

        // 需要提交的json数据
        String jsonData = "{\"kf_account\" :\"%s\",\"openid\" : \"%s\",\"text\" : \"%s\"}";

        log.info("关闭多客服会话，提交的数据是:{}", jsonData.toString());

        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "POST",
                String.format(jsonData, kf_account, openid, "系统强制中断客服会话"));

        if (null != jsonObject) {
            try {

                int errcode = jsonObject.getInt("errcode");
                String errmsg = jsonObject.getString("errmsg");

                log.info("关闭客服会话，　errcode:{} errmsg:{}　", errcode, errmsg);

            } catch (Exception e) {
                int errcode = jsonObject.getInt("errcode");
                String errmsg = jsonObject.getString("errmsg");
                log.error("关闭客服会话失败， errcode:{} errmsg:{} ", errcode, errmsg);
            }
        }
    }


    public static void main(String args[]) throws UnsupportedEncodingException {
        MutiCustServUtil.getKfSession("ovFm4uNOKKcB01_1vkEQSe6Mfxeg");
        MutiCustServUtil.closeKfSeesion("ovFm4uNOKKcB01_1vkEQSe6Mfxeg", "dingyi@yousee8090");
    }

}