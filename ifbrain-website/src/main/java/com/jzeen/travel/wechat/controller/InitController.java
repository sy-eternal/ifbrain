package com.jzeen.travel.wechat.controller;


import com.jzeen.travel.wechat.service.TokenThread;
import com.jzeen.travel.wechat.conf.WeChatConts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Title: X2OUR_TRAVEL
 * Description: 初始化servlet, 暂未使用
 * Date: 2015-08-02
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */
@Component("initialServlet")
public class InitController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(InitController.class);

    @Override
    public void init() throws ServletException {

        log.info("weixin api appid:{}", WeChatConts.appID);
        log.info("weixin api appsecret:{}", WeChatConts.appsecret);

        // 未配置appid、appsecret时给出提示
        if ("".equals(WeChatConts.appID) || "".equals(WeChatConts.appsecret)) {
            log.error("appid and appsecret configuration error, please check carefully.");
        } else {

            // 启动定时获取access_token的线程
            new Thread(new TokenThread()).start();
        }
    }


    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}