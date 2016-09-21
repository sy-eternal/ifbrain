package com.jzeen.travel.openwechat.controller;


import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.WeChatUser;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.WeChatUserRepository;
import com.jzeen.travel.openwechat.utils.LoginUtil;
import com.jzeen.travel.openwechat.utils.Oauth2Util;
import com.jzeen.travel.wechat.pojo.OAuth2Token;
import com.jzeen.travel.wechat.pojo.SNSUserInfo;
import com.jzeen.travel.wechat.utils.SignUtil;
import com.jzeen.travel.wechat.conf.WeChatConts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * Title: X2OUR_TRAVEL
 * Description: 授权后的回调请求处理Controller
 * Date:
 * CopyRight (c) 2015 X2OUR
 *
 * @author sunyan.sunny@x2our.com
 */
@Controller
@RequestMapping("/openwechat")
public class LoginController {
	@Autowired
	private ChildRepository _cChildRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private WeChatUserRepository weChatUserRepository;
    
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
            OAuth2Token oAuth2Token = LoginUtil.getOauth2AccessToken(code);

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

            WeChatUser dbUser = weChatUserRepository.findByOpenid(openId);

            if (dbUser != null) {
                User user = userRepository.findByWechat(openId);
                if(user != null){
                WebUtils.setSessionAttribute(request, "user", user);
                WebUtils.setSessionAttribute(request, "usertyoe", user.getUserType());
                WebUtils.setSessionAttribute(request, "usertyoes", user.getFirstName() + user.getLastName());
                WebUtils.setSessionAttribute(request, "userwechat",snsUserInfo.getHeadImgUrl());
                List<Child> childlist = _cChildRepository.findByUser(user);
                if(childlist.size()>0){
                	WebUtils.setSessionAttribute(request, "childids",childlist.get(0).getId());
                }else{
                	WebUtils.setSessionAttribute(request, "childids","");
                }
                
                return "redirect:/";
                }
            }else{
                User user = new User();
                user.setFirstName("");
                user.setLastName(snsUserInfo.getNickname());
                user.setWechat(snsUserInfo.getOpenId());
                user.setUserType(1);
                user.setActiveStatus(1);
                user.setCreateTime(new Date());
                /**
                 * zyy 9-1 17:00
                 * */
//                user.setSex(snsUserInfo.getSex());
//                user.setNationality(snsUserInfo.getCountry());
                user.setWechat(snsUserInfo.getOpenId());
                userRepository.save(user);
                dbUser=new WeChatUser();
                dbUser.setUserId(user.getId());
                dbUser.setCity(snsUserInfo.getCity());
                dbUser.setLanguage(snsUserInfo.getLanguage());
                dbUser.setCountry(snsUserInfo.getCountry());
                dbUser.setCreate_time(new Date());
                dbUser.setNickname(snsUserInfo.getNickname());
                dbUser.setOpenid(snsUserInfo.getOpenId());
                dbUser.setProvince(snsUserInfo.getProvince());
                dbUser.setHeadimgurl(snsUserInfo.getHeadImgUrl());
                dbUser.setUnionid(snsUserInfo.getUnionid());
                dbUser.setSex(snsUserInfo.getSex());
                weChatUserRepository.save(dbUser);
                WebUtils.setSessionAttribute(request, "childids","");
                WebUtils.setSessionAttribute(request, "user", user);
                WebUtils.setSessionAttribute(request, "usertyoe", user.getUserType());
                WebUtils.setSessionAttribute(request, "usertyoes", user.getFirstName() + user.getLastName());
                WebUtils.setSessionAttribute(request, "userwechat",snsUserInfo.getHeadImgUrl());
                return "redirect:/"; 
            }

        }
        return "/home/index";
    }


   

}
