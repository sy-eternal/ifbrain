package com.jzeen.travel.wechat.controller;


import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.wechat.pojo.OAuth2Token;
import com.jzeen.travel.wechat.pojo.SNSUserInfo;
import com.jzeen.travel.wechat.utils.MenuUtil;
import com.jzeen.travel.wechat.utils.Oauth2Util;
import com.jzeen.travel.wechat.utils.SignUtil;
import com.jzeen.travel.wechat.conf.WeChatConts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;


/**
 * Title: X2OUR_TRAVEL
 * Description: 授权后的回调请求处理Controller
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author limin.tony@x2our.com
 */
@Controller
@RequestMapping("/wechatoauth")
public class OAuthController {
    @Autowired
    UserRepository userRepository;
	private static Logger log = LoggerFactory.getLogger(OAuthController.class);
    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String authRedirect(Model model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("***授权后的回调请求处理 ***");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");

        // 用户同意授权
        if (!"authdeny".equals(code)) {

            // 获取网页授权access_token
            OAuth2Token oAuth2Token = Oauth2Util.getOauth2AccessToken(code);

            // 网页授权接口访问凭证
            String accessToken = oAuth2Token.getAccessToken();
            // 用户标识
            String openId = oAuth2Token.getOpenId();

            // 获取用户信息
            SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);

            System.out.println("snsUserInfo {country, city, nickname, openId, sex}" + snsUserInfo.getCountry() + " " + snsUserInfo.getCity() + " "
                    + snsUserInfo.getNickname() + " " + snsUserInfo.getOpenId() + " " + snsUserInfo.getSex());

            // 设置要传递的参数
            // request.setAttribute("snsUserInfo", snsUserInfo);
            model.addAttribute("snsUserInfo", snsUserInfo);

            User user = userRepository.findByWechat(openId);

            if (user != null) {

                String signature = SignUtil.genSHADoubleStr(WeChatConts.bindToken, snsUserInfo.getOpenId());
                String url = "/home/wechatindex" + "?userId=" + user.getId() + "&signature=" + signature;

                System.out.println("redirect:" + url);

                return "redirect:" + url;
            }

        }
        return "/home/travelindex";
    }


    @RequestMapping(value = "/personalcenter", method = RequestMethod.GET)
    public String personalcenter(Model model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("***授权后的回调请求处理 ***");

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");

        // 用户同意授权
        if (!"authdeny".equals(code)) {

            // 获取网页授权access_token
            OAuth2Token oAuth2Token = Oauth2Util.getOauth2AccessToken(code);

            // 网页授权接口访问凭证
            String accessToken = oAuth2Token.getAccessToken();
            // 用户标识
            String openId = oAuth2Token.getOpenId();

            // 获取用户信息
            SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);

            // 设置要传递的参数
            model.addAttribute("snsUserInfo", snsUserInfo);

            System.out.println("snsUserInfo {country, city, nickname, openId, sex}" + snsUserInfo.getCountry() + " " + snsUserInfo.getCity() + " "
                    + snsUserInfo.getNickname() + " " + snsUserInfo.getOpenId() + " " + snsUserInfo.getSex());

            String url = "/user/forgotpassword";

            return "redirect:" + url;
        }

        return "/home/travelindex";
    }

    //微信菜单中的我的
    @RequestMapping(value = "/trips", method = RequestMethod.GET)
    public String trips(Model model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("***授权后的回调请求处理 ***");
        log.info("***授权后的回调请求处理 ***----------------你大爷的走授权了吗?");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");

        // 用户同意授权
        if (!"authdeny".equals(code)) {

            // 获取网页授权access_token
            OAuth2Token oAuth2Token = Oauth2Util.getOauth2AccessToken(code);
            // 网页授权接口访问凭证
            String accessToken = oAuth2Token.getAccessToken();
            // 用户标识
            String openId = oAuth2Token.getOpenId();


            // 获取用户信息
            SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);

            // 设置要传递的参数
            model.addAttribute("snsUserInfo", snsUserInfo);

            System.out.println("snsUserInfo {country, city, nickname, openId, sex}" + snsUserInfo.getCountry() + " " + snsUserInfo.getCity() + " "
                    + snsUserInfo.getNickname() + " " + snsUserInfo.getOpenId() + " " + snsUserInfo.getSex());

            String url = "/trips/wechattrips?openId=" + openId;

            return "redirect:" + url;
        }

        return "/home/index";
    }
    //微信菜单中的评估
    @RequestMapping(value = "/assementwechat", method = RequestMethod.GET)
    public String assementwechat(Model model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("***授权后的回调请求处理 ***");
        log.info("***授权后的回调请求处理 ***----------------你大爷的走授权了吗?");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");

        // 用户同意授权
        if (!"authdeny".equals(code)) {

            // 获取网页授权access_token
            OAuth2Token oAuth2Token = Oauth2Util.getOauth2AccessToken(code);
            // 网页授权接口访问凭证
            String accessToken = oAuth2Token.getAccessToken();
            // 用户标识
            String openId = oAuth2Token.getOpenId();


            // 获取用户信息
            SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);

            // 设置要传递的参数
            model.addAttribute("snsUserInfo", snsUserInfo);

            System.out.println("snsUserInfo {country, city, nickname, openId, sex}" + snsUserInfo.getCountry() + " " + snsUserInfo.getCity() + " "
                    + snsUserInfo.getNickname() + " " + snsUserInfo.getOpenId() + " " + snsUserInfo.getSex());

            String url = "/trips/wechatassement?openId=" + openId;

            return "redirect:" + url;
        }

        return "/home/index";
    }
    
    //微信菜单中的任务
    @RequestMapping(value = "/projectwechat", method = RequestMethod.GET)
    public String projectwechat(Model model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("***授权后的回调请求处理 ***");
        log.info("***授权后的回调请求处理 ***----------------你大爷的走授权了吗?");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");

        // 用户同意授权
        if (!"authdeny".equals(code)) {

            // 获取网页授权access_token
            OAuth2Token oAuth2Token = Oauth2Util.getOauth2AccessToken(code);
            // 网页授权接口访问凭证
            String accessToken = oAuth2Token.getAccessToken();
            // 用户标识
            String openId = oAuth2Token.getOpenId();


            // 获取用户信息
            SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);

            // 设置要传递的参数
            model.addAttribute("snsUserInfo", snsUserInfo);

            System.out.println("snsUserInfo {country, city, nickname, openId, sex}" + snsUserInfo.getCountry() + " " + snsUserInfo.getCity() + " "
                    + snsUserInfo.getNickname() + " " + snsUserInfo.getOpenId() + " " + snsUserInfo.getSex());

            String url = "/trips/wechatproject?openId=" + openId;

            return "redirect:" + url;
        }

        return "/home/index";
    }
    
    //微信菜单中的购物
    @RequestMapping(value = "/commoditywechat", method = RequestMethod.GET)
    public String commoditywechat(Model model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("***授权后的回调请求处理 ***");
        log.info("***授权后的回调请求处理 ***----------------你大爷的走授权了吗?");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");

        // 用户同意授权
        if (!"authdeny".equals(code)) {

            // 获取网页授权access_token
            OAuth2Token oAuth2Token = Oauth2Util.getOauth2AccessToken(code);
            // 网页授权接口访问凭证
            String accessToken = oAuth2Token.getAccessToken();
            // 用户标识
            String openId = oAuth2Token.getOpenId();


            // 获取用户信息
            SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);

            // 设置要传递的参数
            model.addAttribute("snsUserInfo", snsUserInfo);

            System.out.println("snsUserInfo {country, city, nickname, openId, sex}" + snsUserInfo.getCountry() + " " + snsUserInfo.getCity() + " "
                    + snsUserInfo.getNickname() + " " + snsUserInfo.getOpenId() + " " + snsUserInfo.getSex());

            String url = "/trips/wechatcommodity?openId=" + openId;

            return "redirect:" + url;
        }

        return "/home/index";
    }
  //微信菜单中的课程
    @RequestMapping(value = "/coursewechat", method = RequestMethod.GET)
    public String coursewechat(Model model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("***授权后的回调请求处理 ***");
        log.info("***授权后的回调请求处理 ***----------------你大爷的走授权了吗?");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");

        // 用户同意授权
        if (!"authdeny".equals(code)) {

            // 获取网页授权access_token
            OAuth2Token oAuth2Token = Oauth2Util.getOauth2AccessToken(code);
            // 网页授权接口访问凭证
            String accessToken = oAuth2Token.getAccessToken();
            // 用户标识
            String openId = oAuth2Token.getOpenId();


            // 获取用户信息
            SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);

            // 设置要传递的参数
            model.addAttribute("snsUserInfo", snsUserInfo);

            System.out.println("snsUserInfo {country, city, nickname, openId, sex}" + snsUserInfo.getCountry() + " " + snsUserInfo.getCity() + " "
                    + snsUserInfo.getNickname() + " " + snsUserInfo.getOpenId() + " " + snsUserInfo.getSex());

            String url = "/trips/wechatcourse?openId=" + openId;

            return "redirect:" + url;
        }

        return "/home/index";
    }
    
  //微信菜单中的活动
    @RequestMapping(value = "/activitywechat", method = RequestMethod.GET)
    public String activitywechat(Model model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("***授权后的回调请求处理 ***");
        log.info("***授权后的回调请求处理 ***----------------你大爷的走授权了吗?");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 用户同意授权后，能获取到code
        String code = request.getParameter("code");

        // 用户同意授权
        if (!"authdeny".equals(code)) {

            // 获取网页授权access_token
            OAuth2Token oAuth2Token = Oauth2Util.getOauth2AccessToken(code);
            // 网页授权接口访问凭证
            String accessToken = oAuth2Token.getAccessToken();
            // 用户标识
            String openId = oAuth2Token.getOpenId();


            // 获取用户信息
            SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);

            // 设置要传递的参数
            model.addAttribute("snsUserInfo", snsUserInfo);

            System.out.println("snsUserInfo {country, city, nickname, openId, sex}" + snsUserInfo.getCountry() + " " + snsUserInfo.getCity() + " "
                    + snsUserInfo.getNickname() + " " + snsUserInfo.getOpenId() + " " + snsUserInfo.getSex());

            String url = "/trips/wechatactivity?openId=" + openId;

            return "redirect:" + url;
        }

        return "/home/index";
    }
}
