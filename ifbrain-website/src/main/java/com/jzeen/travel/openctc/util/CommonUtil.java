package com.jzeen.travel.openctc.util;

import com.jzeen.travel.openctc.bean.RandcodeIdentifier;
import com.jzeen.travel.openctc.conf.OpenctcConsts;
import com.jzeen.travel.openctc.emp.tool.json.JSONException;
import com.jzeen.travel.openctc.emp.tool.json.JSONObject;
import com.jzeen.travel.wechat.conf.WeChatConts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

/**
 * Title: Ifbrain
 * Description:
 * Date: 2015年 11月 20日
 * CopyRight (c) 2015 Ifbrain
 *
 * @Author sunyan   @ifbrain.com
 */
public class CommonUtil {

    private static Logger log = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 获取接口访问凭证
     *
     * @return
     */
    public static String getAccessToken() {

        try {
            /*TreeMap<String, String> paramsMap1 = new TreeMap<String, String>();
            paramsMap1.put("grant_type", "client_credentials");
            paramsMap1.put("app_id", OpenctcConsts.app_id);
            paramsMap1.put("app_secret", OpenctcConsts.app_secret);
*/
            String postUrl = OpenctcConsts.ACCESS_TOKEN_API;
            String postEntity = "grant_type=" + "client_credentials"
                    + "&app_id=" + OpenctcConsts.app_id
                    + "&app_secret=" + OpenctcConsts.app_secret;

            String resJson = HttpInvoker.httpPost(postUrl, null, postEntity);

            JSONObject obj = new JSONObject(resJson);

            String accessToken = (String) obj.get("access_token");

            log.info("获取Access_token成功 access_token:{}", accessToken);
            return accessToken;
        } catch (Exception e) {
            log.error("获取短信验证码失败,错误信息:{}", e.toString());
        }

        return "";
    }


    /**
     * 发送短信验证码,默认时间为1分钟
     *
     * @param userPhone 手机号码
     * @return
     * @throws Exception
     */
    public static RandcodeIdentifier sendSms(String userPhone) throws IOException, JSONException {
        return sendSms(userPhone, "1");
    }

    /**
     * 发送短信验证码
     *
     * @param userPhone 手机号码
     * @param timeLimit 有效时间 单位分钟
     * @return
     * @throws Exception
     */
    public static RandcodeIdentifier sendSms(String userPhone, String timeLimit) throws IOException, JSONException {
    	/**
    	 * 获得accessToken
    	 */
    	String accessToken =  OpenctcConsts.getTokenInst();
    	/**
    	 * 获得信任码
    	 */
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = dateFormat.format(date);

        TreeMap<String, String> paramsMap = new TreeMap<String, String>();
        paramsMap.put("app_id", OpenctcConsts.app_id);
        paramsMap.put("access_token", accessToken);
        paramsMap.put("timestamp", timestamp);

        String getUrl = "http://api.189.cn/v2/dm/randcode/token?app_id=" + OpenctcConsts.app_id
                + "&access_token=" + accessToken + "&timestamp=" + timestamp + "&sign=" + ParamsSign.value(paramsMap, OpenctcConsts.app_secret);

        String resJson = HttpInvoker.httpGet(getUrl);
        JSONObject json = new JSONObject(resJson);
        String token = json.get("token").toString();
        log.info("发送短信验证码 获取信任码成功 token:{}", token);
        /**
         * 向手机发送手机验证码
         */
        TreeMap<String, String> paramsMap1 = new TreeMap<String, String>();
        paramsMap1.put("app_id", OpenctcConsts.app_id);
        paramsMap1.put("access_token", accessToken);
        paramsMap1.put("timestamp", timestamp);
        paramsMap1.put("token", token);
        paramsMap1.put("url", "http://ifbrain.com/openctcsms/recall");
        paramsMap1.put("phone", userPhone);
        paramsMap1.put("exp_time", timeLimit);
        String postUrl = "http://api.189.cn/v2/dm/randcode/send";
        String postEntity = "app_id=" + OpenctcConsts.app_id
                + "&access_token=" + accessToken
                + "&token=" + token
                + "&phone=" + userPhone
                + "&url=" + "http://ifbrain.com/openctcsms/recall"
                + "&exp_time=" + timeLimit
                + "&timestamp=" + timestamp
                + "&sign=" + ParamsSign.value(paramsMap1, OpenctcConsts.app_secret);
        String resJson1 = HttpInvoker.httpPost(postUrl, null, postEntity);
        JSONObject json1 = new JSONObject(resJson1);
        /**
         * resCode为0时表示发送成功,为1时表示发送失败.
         */
        String resCode = json1.get("res_code").toString();
        String identifier = json1.get("identifier").toString();
        String createAt = json1.get("create_at").toString();
        RandcodeIdentifier randcodeIdentifier = new RandcodeIdentifier(resCode, identifier, createAt);
        if ("0".equals(resCode)) {
            log.info("发送短信验证码 发送成功 res_code:{} identifier:{} create_at:{}", resCode, identifier, createAt);
        } else {
            log.error("发送短信验证码 发送失败 res_code:{} identifier:{} create_at:{}", resCode, identifier, createAt);
        }
        return randcodeIdentifier;
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
        if ("10.144.2.28".equals(domainIP) || "115.28.5.128".equals(domainIP)) {
            return "10.144.2.28".equals(localIp) || "115.28.5.128".equals(localIp);
        } else if ("10.163.210.95".equals(domainIP) || "115.29.100.19".equals(domainIP)) {
            return "10.163.210.95".equals(localIp) || "115.29.100.19".equals(localIp);
        } else {
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


    public static void main(String[] args) {

        try {
            CommonUtil.sendSms("15910667564");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}