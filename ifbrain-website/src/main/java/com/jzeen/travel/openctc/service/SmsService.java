package com.jzeen.travel.openctc.service;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jzeen.travel.data.entity.ActivationCode;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ActivationCodeRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.openctc.bean.RandcodeIdentifier;
import com.jzeen.travel.openctc.controller.SmsController;
import com.jzeen.travel.openctc.emp.tool.json.JSONException;
import com.jzeen.travel.openctc.util.CommonUtil;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 02日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
@Service
public class SmsService {

    private static Logger log = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    ActivationCodeRepository _activationCodeRepository;
    @Autowired
    UserRepository  _userRepository;
    

    /**
     * 发送短信接口,不传入超时时间,默认为1分钟失效
     * UserId为空，适合于还没有用户ID的情况，例如用户登陆注册/找回密码等，此时用户未登陆还没有UserID
     * @param phoneNo 手机号码(单个手机号码,格式 例如 13810011234)
     * @param type 类型: 例如 1 用户登陆 2 找回密码
     * @return
     */
    public boolean sendSms(String phoneNo, String type) {
        return sendSms(phoneNo, null, type, "1");
    }

    /**
     * 发送短信接口,不传入超时时间,默认为1分钟失效
     * @param phoneNo 手机号码(单个手机号码,格式 例如 13810011234)
     * @param userId 用户ID
     * @param type 类型: 例如 1 用户登陆 2 找回密码
     * @return
     */
    public boolean sendSms(String phoneNo, Integer userId, String type) {
        return sendSms(phoneNo, userId, type, "1");
    }

    /**
     * 发送短信接口
     * UserId为空，适合于还没有用户ID的情况，例如用户登陆注册/找回密码等，此时用户未登陆还没有UserID
     *
     * @param phoneNo   手机号码(单个手机号码,格式 例如 13810011234)
     * @param type      类型: 例如 1 用户登陆 2 找回密码
     * @param timeLimit 过期时间 单位分钟
     * @return
     */
    public boolean sendSms(String phoneNo, String type, String timeLimit) {
        return sendSms(phoneNo, null, type, timeLimit);
    }

    /**
     * 发送短信接口
     * @param phoneNo 手机号码(单个手机号码,格式 例如 13810011234)
     * @param userId 用户ID
     * @param type 类型: 例如 1 用户登陆 2 找回密码
     * @param timeLimit 过期时间 单位分钟
     * @return
     */
    public boolean sendSms(String phoneNo, Integer userId, String type, String timeLimit) {
        boolean result = false;
        try {
            // 发送短信息
            RandcodeIdentifier randcode = CommonUtil.sendSms(phoneNo, timeLimit);
            User usermobile = _userRepository.findByMobile(phoneNo);
            //找回密码时需要判断，通过电话号码判断该用户是否存在，如果存在就只保存该用户的电话验证的唯一标示,否则创建新用户
           
            if (randcode != null && "0".equals(randcode.getResCode())) {
                result = true;
                if(usermobile==null){
                // 超时时间
                long validationTime = new Date().getTime() + Integer.valueOf(timeLimit) * 60 * 1000;
                User user=new User();
                user.setMobile(phoneNo);
                user.setCreateTime(new Date());
                user.setSendtime(new Date());
                user.setValidation(new Date(validationTime));
                user.setFirstName(phoneNo);
                user.setIdentifier(randcode.getIdentifier());
                /*ActivationCode activationCode = new ActivationCode();
                activationCode.setUserId(userId);
                activationCode.setPhone(phoneNo);
                activationCode.setType(type);
                activationCode.setSendtime(new Date());
                activationCode.setCreatetime(new Date());
                activationCode.setValidation(new Date(validationTime));
                activationCode.setIdentifier(randcode.getIdentifier());*/
                // 判断内存中是否有值，如果有取内存中的CODE
                String code = SmsController.CODE_MAP.get(randcode.getIdentifier());
                if (code != null && "" != code) {
                   // activationCode.setCode(SmsController.CODE_MAP.get(randcode.getIdentifier()));
                	user.setCode(SmsController.CODE_MAP.get(randcode.getIdentifier()));
                    log.info("短信验证码发送, 保存内存中的验证码为, code:{}", code);
                    SmsController.CODE_MAP.remove(randcode.getIdentifier());
                }
                	_userRepository.save(user);
                }else{
                	 long validationTime = new Date().getTime() + Integer.valueOf(timeLimit) * 60 * 1000;
                	 usermobile.setValidation(new Date(validationTime));
                	usermobile.setCode(SmsController.CODE_MAP.get(randcode.getIdentifier()));
                	 _userRepository.save(usermobile); 
                }
                log.info("短信验证码发送成功, 信息保存到数据库, userId:{}, type:{}, Identifier:{}", userId, type, randcode.getIdentifier());
            }
          
        } catch (IOException e) {
            log.error("发送短信息异常, IOException: {}", e.toString());
        } catch (JSONException e) {
            log.error("发送短信息异常, JSONException: {}", e.toString());
        }

        return result;
    }
}