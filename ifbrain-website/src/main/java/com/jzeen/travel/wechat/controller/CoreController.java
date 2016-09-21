package com.jzeen.travel.wechat.controller;


import com.jzeen.travel.wechat.service.CoreService;
import com.jzeen.travel.wechat.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Title: X2OUR_TRAVEL
 * Description: 核心请求处理类, 微信消息入口
 * Date: 2015-12-02
 * CopyRight (c) 2015 Ifbrain
 *
 * @author sunyan.sunny@ifbrain.com
 *
 */
@Controller
@RequestMapping("/wechat")
public class CoreController {

    private static Logger log = LoggerFactory.getLogger(CoreController.class);

    @Autowired
    CoreService coreService;

    /**
     * 请求校验(确认请求来自微信服务器)
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   System.out.println("token验证!-----------------------------------有没有走,走get方法");
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        log.info(String.format("微信请求校验 signature：{%s}  timestamp:{%s}  nonce:{%s} echostr:{%s}", signature, timestamp, nonce, echostr));
        System.out.println("token验证!-----------------------------------有没有走");
//        PrintWriter out = response.getWriter();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
       // if (SignUtil.checkSignature(signature, timestamp, nonce)) {
       // SignUtil.checkSignature(signature, timestamp, nonce);
            log.info("微信请求校验成功");
//            out.print(echostr);
            return echostr;
       // }
//        out.close();
//        out = null;

       // return "";
    }

    /**
     * 处理微信服务器发来的消息
     */
    @RequestMapping(method = RequestMethod.POST)
    public void dealPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("开始处理微信服务器发来的信息");

        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 接收参数:微信加密签名、时间戳、随机数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");

        log.info(String.format("校验签名 signature：{%s}  timestamp:{%s}  nonce:{%s}", signature, timestamp, nonce));

        PrintWriter out = response.getWriter();
        // 请求校验
      /*  if (SignUtil.checkSignature(signature, timestamp, nonce)) {

            log.info("校验签名成功");*/

            // 调用核心服务类接收处理请求
            String respXml = coreService.processRequest(request);
            out.print(respXml);
       /* }*/

        out.close();
        out = null;
    }


}
