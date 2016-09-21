package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.pojo.AccessToken;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Title: X2OUR_TRAVEL
 * Description: 通用工具类
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */
public class CommonUtil {
    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 获取接口访问凭证
     *
     * @param appid     凭证
     * @param appsecret 密钥
     * @return
     */
    public static AccessToken getAccessToken(String appid, String appsecret) {
        AccessToken token = null;
        String requestUrl = WeChatConts.token_url.replace("APPID", appid).replace("APPSECRET", appsecret);

        // 发起GET请求获取凭证
        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                token = new AccessToken();
                token.setToken(jsonObject.getString("access_token"));
                token.setExpiresIn(jsonObject.getInt("expires_in"));
            } catch (JSONException e) {
                token = null;
                // 获取token失败
                log.error("获取token失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return token;
    }
    /**
     * 获取获取jsapi_ticket
     */
    public static String getJsapi(String accestoken) {
        String requestUrl = WeChatConts.getTicket.replace("ACCESS_TOKEN", accestoken);
        String jsapi_ticket="";
        // 发起GET请求获取凭证
        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
              jsapi_ticket = jsonObject.getString("access_token");
            } catch (JSONException e) {
                // 获取jsapi_ticket失败
                log.error("获取jsapi_ticket失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
            }
        }
        return jsapi_ticket;
    }

    /**
     * URL编码（utf-8）
     *
     * @param source
     * @return
     */
    public static String urlEncodeUTF8(String source) {
        String result = source;
        try {
            result = java.net.URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 是否为测试环境，如果是返回true，否则返回false
     *
     * @return boolean
     */
    public static boolean isTestServer() {
        return getLocalIP().startsWith("192.168.");
    }

    /**
     * 只有在生产环境才启动Thread获取Token
     * 解决生产环境有多个网卡IP的问题，如果是生产环境返回true，否则返回false
     *
     * @return boolean
     */
    public static boolean isProductServer() {
        String domainIP = getDomainIP(WeChatConts.DOMAIN_NAME);
        String localIp = getLocalIP();
        //"10.165.54.43".equals(domainIP) || 
        if ("121.42.37.146".equals(domainIP)) {
            return true;
            //"10.165.54.43".equals(localIp) || "121.42.37.146".equals(localIp)
        }/* else if ("10.163.210.95".equals(domainIP) || "115.29.100.19".equals(domainIP)) {
            return "10.163.210.95".equals(localIp) || "115.29.100.19".equals(localIp);
        } */else {
            return false;
        }
    }

    /**
     * 获取域名IP地址
     * @param name
     * @return
     */
    private static String getDomainIP(String name) {
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName(name);
            log.info("获取域名IP地址:{}", addr.getHostAddress().toString());
        } catch (UnknownHostException e) {
            log.error("获取域名的IP地址失败");
            e.printStackTrace();
        }
        return addr.getHostAddress().toString();
    }

    /**
     * 获取本机IP地址
     * @return
     */
    private static String getLocalIP() {

        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
            log.info("获取本机IP地址:{}", addr.getHostAddress().toString());
        }  catch (UnknownHostException e) {
            log.error("获取本机IP地址失败");
            e.printStackTrace();
        }
        return addr.getHostAddress().toString();
    }

}
