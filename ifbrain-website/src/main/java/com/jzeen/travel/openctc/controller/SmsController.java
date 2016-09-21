package com.jzeen.travel.openctc.controller;

import com.jzeen.travel.data.entity.ActivationCode;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ActivationCodeRepository;
import com.jzeen.travel.data.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Title: X2OUR_TRAVEL
 * Description:
 * Date: 2015年 09月 01日
 * CopyRight (c) 2015 X2OUR
 *
 * @Author limin.tony@x2our.com
 */
@Controller
@RequestMapping("/openctcsms")
public class SmsController {

    private static Logger log = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    ActivationCodeRepository _activationCodeRepository;
    @Autowired
    UserRepository  _userRepository;

    public static Map<String, String> CODE_MAP = new HashMap<String, String>();

    /**
     * 回调接口
     *
     * @param rand_code
     * @param identifier
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/recall", method = RequestMethod.POST)
    public String recall(@RequestParam String rand_code, @RequestParam String identifier, HttpServletRequest request) {
        String res_code = "0";
        log.info("短信验证码回调 rand_code:{}  identifier:{}", rand_code, identifier);

        try {
           // List<ActivationCode> activationCodeList = _activationCodeRepository.findByIdentifier(identifier);
        	User user = _userRepository.findByIdentifier(identifier);
           /* if (activationCodeList != null && activationCodeList.size() != 0) {
                ActivationCode activationCode;
                if (activationCodeList.size() == 1) {
                    activationCode = activationCodeList.get(0);
                } else {
                    activationCode = getMaxRecode(activationCodeList);
                }
                activationCode.setCode(rand_code);
                _activationCodeRepository.save(activationCode);
                log.info("短信验证码回调接口成功 rand_code:{}  identifier:{}", rand_code, identifier);
            }*/ 
        	if(user!=null){
            	user.setCode(rand_code);
            	_userRepository.save(user);
            }else {
                CODE_MAP.put(identifier, rand_code);
                log.info("短信验证码回调接口异常, 数据库无记录，数据保存到内存中");
            }

        } catch (Exception e) {
            log.error("短信验证码回调接口异常, ERROR:{}", e.toString());
            res_code = "1";
        }

        return res_code;
    }


    /**
     * 状态报告接口
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/statereport", method = RequestMethod.GET)
    public String statereport(HttpServletRequest request) {
        log.info("短信验证码,状态报告回调接口");

        return "";
    }


    /**
     * 返回ID最大的记录,即有重复的identifier,返回最新的一个identifier记录
     *
     * @param activationCodeList
     * @return
     */
    private ActivationCode getMaxRecode(List<ActivationCode> activationCodeList) {
        int maxId = 0;
        int index = 0;

        for (int i = 0; i < activationCodeList.size(); i++) {
            if (activationCodeList.get(i).getId() > maxId) {
                maxId = activationCodeList.get(i).getId();
                index = i;
            }
        }

        return activationCodeList.get(index);
    }


}