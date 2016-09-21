package com.jzeen.travel.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Title: X2OUR_TRAVEL
 * Description: 用户点击菜单事件控制器（图文消息链接）
 * Date: 2015-08-02
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 */

@Controller
@RequestMapping("/wechatartical")
public class ArticalController {

    /**
     * 学校专业测试
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public String school(HttpServletRequest request) {

        return "/wechat/school";
    }

}
