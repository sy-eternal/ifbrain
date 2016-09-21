package com.jzeen.travel.openctc.service;

import com.jzeen.travel.openctc.conf.OpenctcConsts;
import com.jzeen.travel.openctc.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Title: X2OUR_TRAVEL
 * Description: 定时获取天翼微信平台access_token的线程
 * Date: 2015-09-02
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 */
public class OpenCtcTokenThread implements Runnable {

    private static Logger log = LoggerFactory.getLogger(OpenCtcTokenThread.class);

    public void run() {

        while (true) {
            if (CommonUtil.isProductServer()) {
                //判断是否为生产环境，如果不是生产环境，不获取Token

                try {
                    OpenctcConsts.AccessToken = CommonUtil.getAccessToken();

                    if (null != OpenctcConsts.AccessToken) {

                        log.info("获取中国电信天翼开放平台access_token成功，  token:{}", OpenctcConsts.AccessToken);

                        // 每天获取1次天翼开放平台access_token
                        Thread.sleep(1 * 24 * 60 * 60 * 1000);
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