package com.jzeen.travel.website.controller.rentalCar.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

import com.jzeen.travel.website.controller.rentalCar.conf.RentalCarConfigure;
import com.jzeen.travel.website.controller.rentalCar.pojo.RentalCarToken;

public class RentalCarAccessToken
{
    /**
     * 获取网页授权凭证
     *
     * @param code
     * @return WeixinAouth2Token
     */
    
    private static Logger log = LoggerFactory.getLogger(RentalCarAccessToken.class);
    
    public static RentalCarToken getRenTalCarAccessToken() {
        
        RentalCarToken wat = null;
        // 拼接请求地址
        String requestUrl = RentalCarConfigure.getUrl();
        String authorization=RentalCarConfigure.getAuthorization();
        String contenttype = RentalCarConfigure.getContenttype();
        String data = RentalCarConfigure.getData();
        Map<String,String> map=new HashMap<String,String>();
        map.put("Authorization", authorization);
        map.put("Content-Type", contenttype);
        // 获取网页授权凭证
        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "POST", data,map);

        if (null != jsonObject) {
            try {
                wat = new RentalCarToken();
                wat.setAccessToken(jsonObject.getString("access_token"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }
}
