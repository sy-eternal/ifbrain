package com.jzeen.travel.wechat.utils;

import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.pojo.Menu;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: X2OUR_TRAVEL
 * Description: 自定义菜单帮助类
 * Date: 2015年 08月 04日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
public class MenuUtil {
    private static Logger log = LoggerFactory.getLogger(MenuUtil.class);

    /**
     * 创建菜单
     *
     * @param menu        菜单实例
     * @param accessToken 凭证
     * @return true成功 false失败
     */
    public static boolean createMenu(Menu menu, String accessToken) {
        boolean result = false;
        String url = WeChatConts.menu_create_url.replace("ACCESS_TOKEN", accessToken);
        // 将菜单对象转换成json字符串
        String jsonMenu = JSONObject.fromObject(menu).toString();
        // 发起POST请求创建菜单
        JSONObject jsonObject = HttpRequestUtil.httpsRequest(url, "POST", jsonMenu);

        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");

            if (0 == errorCode) {
                result = true;
                log.info("创建菜单成功 errcode:{} errmsg:{}", errorCode, errorMsg);
            } else {
                result = false;
                log.error("创建菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }

        return result;
    }


    /**
     * 查询菜单
     *
     * @param accessToken 凭证
     * @return
     */
    public static String getMenu(String accessToken) {
        String result = null;
        String requestUrl = WeChatConts.menu_get_url.replace("ACCESS_TOKEN", accessToken);
        // 发起GET请求查询菜单
        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            result = jsonObject.toString();
        }
        return result;
    }

    /**
     * 删除菜单
     *
     * @param accessToken 凭证
     * @return true成功 false失败
     */
    public static boolean deleteMenu(String accessToken) {
        boolean result = false;
        String requestUrl = WeChatConts.menu_delete_url
                .replace("ACCESS_TOKEN", accessToken);
        // 发起GET请求删除菜单
        JSONObject jsonObject = HttpRequestUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (0 == errorCode) {
                result = true;
            } else {
                result = false;
                log.error("删除菜单失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return result;
    }
}
