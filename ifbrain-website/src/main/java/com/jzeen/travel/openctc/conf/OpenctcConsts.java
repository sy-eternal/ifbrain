package com.jzeen.travel.openctc.conf;

import com.jzeen.travel.openctc.util.CommonUtil;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 08月 31日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class OpenctcConsts {

    public static final String app_id = "291575930000248102";

    public static final String app_secret = "be322acdff8e746cbd6203e90f5c7488";

    public static String AccessToken;

    //*****auth授权接口
    public static final String AUTH_API = "https://oauth.api.189.cn/emp/oauth2/authorize";

    //****token
    public static final String ACCESS_TOKEN_API = "https://oauth.api.189.cn/emp/oauth2/v3/access_token";

    public static String getTokenInst() {
        if (OpenctcConsts.AccessToken == null) {
            OpenctcConsts.AccessToken = CommonUtil.getAccessToken();
        }
        return OpenctcConsts.AccessToken;
    }
}