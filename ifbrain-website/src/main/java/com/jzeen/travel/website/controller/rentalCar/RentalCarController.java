package com.jzeen.travel.website.controller.rentalCar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.website.controller.rentalCar.pojo.RentalCarToken;
import com.jzeen.travel.website.controller.rentalCar.utils.RentalCarAccessToken;
import com.jzeen.travel.wechat.conf.WeChatConts;
import com.jzeen.travel.wechat.pojo.OAuth2Token;
import com.jzeen.travel.wechat.pojo.SNSUserInfo;
import com.jzeen.travel.wechat.utils.Oauth2Util;
import com.jzeen.travel.wechat.utils.SignUtil;


@Controller
@RequestMapping("/rentalcar")
public class RentalCarController
{
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String code = request.getParameter("code");


            RentalCarToken renTalCarAccessToken = RentalCarAccessToken.getRenTalCarAccessToken();

            // 网页授权接口访问凭证
            String accessToken = renTalCarAccessToken.getAccessToken();

            // 获取用户信息
           /* SNSUserInfo snsUserInfo = Oauth2Util.getSNSUserInfo(accessToken, openId);

            System.out.println("snsUserInfo {country, city, nickname, openId, sex}" + snsUserInfo.getCountry() + " " + snsUserInfo.getCity() + " "
                    + snsUserInfo.getNickname() + " " + snsUserInfo.getOpenId() + " " + snsUserInfo.getSex());
*/
            // 设置要传递的参数
            // request.setAttribute("snsUserInfo", snsUserInfo);
          //  model.addAttribute("snsUserInfo", snsUserInfo);

        return "/rentalcar/index";
    }
    
}
