package com.jzeen.travel.wechat;

import com.jzeen.travel.openctc.service.OpenCtcTokenThread;
import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.service.TemplateMsgThread;
import com.jzeen.travel.wechat.service.TokenThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * Title: X2OUR_TRAVEL
 * Description: 启动侦听器，启动线程
 * Date: 2015-08-02
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 */
@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    private static Logger log = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    TemplateMsgThread templateMsgThread;

    // 通过全局变量解决加载多次的问题
    private static int flag = 0;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (flag == 1) {
            log.info("多次加载侦听器，不执行");
            return;
        }

        log.info("wechat api appid:{}", WeChatConts.appID);

        // 未配置appid、appsecret时给出提示
        if ("".equals(WeChatConts.appID) || "".equals(WeChatConts.appsecret)) {

            log.error("appid and appsecret configuration error, please check carefully.");
        } else {
            // 启动定时获取微信开放平台 access_token的线程
            new Thread(new TokenThread()).start();

            // 启动定时获取中国电信天翼开放平台 access_token的线程
            new Thread(new OpenCtcTokenThread()).start();

            //启动时创建菜单，
            //屏蔽，启动时不再创建菜单
            // MenuManager.createMenu();

            // 启动微信模板消息侦听器
            new Thread(templateMsgThread).start();
        }

        flag = 1;
    }


}
