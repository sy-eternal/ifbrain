package com.jzeen.travel.wechat.controller;

import com.jzeen.travel.core.util.CipherUtil;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.wechat.utils.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Title: X2OUR_TRAVEL
 * Description: 用户控制器
 * Date: 2015-08-02
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 *
 */

@Controller
@RequestMapping("/wechatuser")
public class WeChatUserController {

    @Autowired
    UserRepository _userRepository;

    @RequestMapping(value = "/bind", method = RequestMethod.GET)
    public String bindInit(Model model, HttpServletRequest request) {
        System.out.println("into checking ..........");

        // openId
        String openId = request.getParameter("openId");

        // 微信加密签名
        String signature = request.getParameter("signature");

        // 时间戳
        String timestamp = request.getParameter("timestamp");

        System.out.println("checking ..........signature " + signature + " timestamp " + timestamp + " openId " + openId);

        String errMeg = "";

        if (new Date().getTime() > Long.valueOf(timestamp)) {
            errMeg = "该链接已经失效，请重新点击绑定按钮";
        } else if (SignUtil.checkSignature(signature, timestamp, openId)) {
            errMeg = "非法链接，请重新点击绑定按钮";
        }

        model.addAttribute("errMeg", errMeg);
        model.addAttribute("openId", openId);
        return "/wechat/userbind";
    }


    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public String bind(Model model, HttpServletRequest request, HttpSession session) {
        //获取页面传过来的邮箱和密码的值
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String openId = request.getParameter("openId");

        System.out.println("绑定 openId . " + openId);

        User user = _userRepository.findByEmailAndPassword(email, CipherUtil.generatePassword(password));

        if (user != null) {
            if (user.getActiveStatus() == 1) {

                user.setWechat(openId);
                _userRepository.save(user);

                System.out.println("绑定 成功 . " + openId);

                return "/wechat/userbindsuceess";
            } else {
                model.addAttribute("message", "抱歉，用户没有进行激活无法登录");
                return "/wechat/userbind";
            }
        } else {
            model.addAttribute("message", "邮箱或密码错误，请重新填写。");
            return "/wechat/userbind";
        }
    }

}
