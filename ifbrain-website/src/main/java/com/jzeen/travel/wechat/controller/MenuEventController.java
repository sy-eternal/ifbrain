package com.jzeen.travel.wechat.controller;

import com.jzeen.travel.wechat.conf.WeChatConts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("/wechatmenuevent")
public class MenuEventController {

    /**
     * 美国游记
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/notes", method = RequestMethod.GET)
    public String notes(HttpServletRequest request) {

        return "/wechat/notesofminicountry";
    }


    /**
     * 签证攻略
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/visains", method = RequestMethod.GET)
    public String visains(HttpServletRequest request) {

        return "/wechat/visainstrution";
    }


    /**
     * 关于我们
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/aboutus", method = RequestMethod.GET)
    public String aboutus(HttpServletRequest request) {

        return "/wechat/aboutus";
    }

    /**
     * 规划师介绍
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/guide", method = RequestMethod.GET)
    public String guide(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        String picurl = WeChatConts.webURL + "/wechatimage/menu/guide/" + name;
        model.addAttribute("picurl", picurl);

        return "/wechat/guidedesc";
    }

    /**
     * 随我规划--行程案例
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/plan", method = RequestMethod.GET)
    public String plan(Model model, HttpServletRequest request) {
        String name = request.getParameter("name");
        if ("w12days".equals(name)) {
            //美西12天行程
            return "/wechat/planw12days";
        } else if ("ew17days".equals(name)) {
            //东西17天行程
            return "/wechat/planew17days";
        } else {
            //其他情况返回美西12天行程
            return "/wechat/planw12days";
        }
    }

}
