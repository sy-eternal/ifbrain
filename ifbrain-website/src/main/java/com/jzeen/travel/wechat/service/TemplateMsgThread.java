package com.jzeen.travel.wechat.service;

import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.controller.RedPackActivityController;
import com.jzeen.travel.wechat.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Title: X2OUR_TRAVEL
 * Description: 定时获取微信access_token的线程
 * Date: 2015-08-02
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 */
@Component
public class TemplateMsgThread implements Runnable {

    private static Logger log = LoggerFactory.getLogger(TemplateMsgThread.class);

    @Autowired
    TemplateMsgService templateMsgService;


    public void run() {
        log.info("启动模板消息发送扫描线程。");

        while (true) {
            if (CommonUtil.isProductServer()) {
                //判断是否为生产环境，如果不是生产环境，不扫描
                try {

                    // 初始化红包数量
                    //  initRedPackSize();

                    templateMsgService.scanTemplateMsg();

                    Thread.sleep(WeChatConts.templateMsgScanTime);

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


    private void initRedPackSize() {
        Date dt = new Date();
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = matter1.format(dt);

        log.info("当前日期:{}，当前红包总数量:{}", strDate, RedPackActivityController.SIZE);

        if ("2015-08-28".equals(strDate)) {
            if (RedPackActivityController.SIZE != -1) {
                RedPackActivityController.SIZE = -1;
                log.info("红包总数量修改为: {}", RedPackActivityController.SIZE);
            }
        } else if ("2015-08-29".equals(strDate)) {
            if (RedPackActivityController.SIZE != 200) {
                RedPackActivityController.SIZE = 200;
                log.info("红包总数量修改为: {}", RedPackActivityController.SIZE);
            }
        } else if ("2015-08-30".equals(strDate)) {
            if (RedPackActivityController.SIZE != 400) {
                RedPackActivityController.SIZE = 400;
                log.info("红包总数量修改为: {}", RedPackActivityController.SIZE);
            }
        } else {
            if (RedPackActivityController.SIZE != -1) {
                RedPackActivityController.SIZE = -1;
                log.info("红包总数量修改为: {}", RedPackActivityController.SIZE);
            }
        }
    }


}