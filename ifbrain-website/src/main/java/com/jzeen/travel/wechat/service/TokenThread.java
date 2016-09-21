package com.jzeen.travel.wechat.service;

import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: X2OUR_TRAVEL
 * Description: 定时获取微信access_token的线程
 * Date: 2015-08-02
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */
public class TokenThread implements Runnable {

    private static Logger log = LoggerFactory.getLogger(TokenThread.class);


  public void run() {

	  while (true) {
            if (CommonUtil.isProductServer()) {
                //判断是否为生产环境，如果不是生产环境，不获取Token

                try {
                    WeChatConts.accessToken = CommonUtil.getAccessToken(WeChatConts.appID, WeChatConts.appsecret);
                    if (null != WeChatConts.accessToken) {

                        log.info("获取access_token成功，有效时长{}秒 token:{}", WeChatConts.accessToken.getExpiresIn(), WeChatConts.accessToken.getToken());
                        // 休眠7000秒

                        Thread.sleep((WeChatConts.accessToken.getExpiresIn() - 200) * 1000);
                    } else {

                        // 如果access_token为null，60秒后再获取
                        Thread.sleep(60 * 1000);
                    }
                } catch (InterruptedException e) {
                    try {
                        Thread.sleep(60 * 1000);
                    } catch (InterruptedException e1) {
                        log.error("{}", e1);
                    }
                    log.error("{}", e);
                }
            } else {
                try {
                    if (CommonUtil.isTestServer()) {
                        // 如果为测试环境，直接跳出
                        break;
                    } else {
                        // 侦听域名是否切换，3分钟侦听一次
                        Thread.sleep(3 * 60 * 1000);
                        log.info("本机为非生产环境，3分钟侦听一次，判断域名是否产出切换");
                    }
                } catch (InterruptedException e) {
                    try {
                        Thread.sleep(3 * 60 * 1000);
                    } catch (InterruptedException e1) {
                        log.error("{}", e1);
                    }
                    log.error("{}", e);
                }
            }
        }
    }

  
 


}