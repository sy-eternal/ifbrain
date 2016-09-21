package com.jzeen.travel.website.controller;

import com.jzeen.travel.core.util.CipherUtil;
import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.ActivationCode;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.QCode;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ActivationCodeRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.openctc.service.SmsService;
import com.mysema.query.types.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/code")
public class CodeController {

    private static Logger log = LoggerFactory.getLogger(CodeController.class);

    @Autowired
    CodeRepository _codeRepository;

    @Autowired
    ActivationCodeRepository _activationCodeRepository;

    @Autowired
    UserRepository _userrepository;

    @Autowired
    SmsService smsService;

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "/code/index";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<Code, Integer> search(HttpServletRequest request) {
        String keyword = request.getParameter("search[value]");
        QCode qCode = QCode.code;
        Predicate predicate = qCode.type.containsIgnoreCase(keyword);
        DataTable<Code, Integer> dataTable = DataTable.fromRequest(request, _codeRepository, predicate);
        return dataTable;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model) {
        Code code = _codeRepository.findOne(id);
        model.addAttribute("code", code);
        return "/code/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@Valid Code code, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/code/update";
        }
        code.setCreateTime(new Date());
        _codeRepository.save(code);
        return "redirect:/code";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute Code code) {
        return "/code/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid Code code, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/code/create";
        }
        code.setCreateTime(new Date());
        _codeRepository.save(code);

        return "redirect:/code";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id) {
        _codeRepository.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/findByTypeAndValue", method = RequestMethod.GET)
    public Code findByTypeAndValue(@RequestParam String type, @RequestParam Integer value) {
        Code code = _codeRepository.findByTypeAndValue(type, value);
        return code;
    }

    @ResponseBody
    @RequestMapping(value = "/valueistrue", method = RequestMethod.POST)
    public Boolean valueistrue(String type, int value) {
        Code code = _codeRepository.findByTypeAndValue(type, value);
        return code == null;
    }

    //手机短信获取验证码方法
    @ResponseBody
    @RequestMapping(value = "/getsmsvalidation", method = RequestMethod.POST)
    public String smsvalidation(HttpServletRequest request, Model model) {
        String mobile = request.getParameter("mobile");
        //List<ActivationCode> activationCodeList = _activationCodeRepository.findByTypeAndPhone("1", mobile);
        //查询该电话号码的用户是否存在
        User user = _userrepository.findByMobile(mobile);
        String result = "";
        String jsonresult = "";
        Date date = new Date();
        long currentTime = date.getTime();//当前时间
        if (user != null) {
                    result = "1";
                    jsonresult = "{\"result\":\"" + result + "\"}";
                    return jsonresult;
        }
       /* if (activationCodeList != null) {
            for (ActivationCode activationCode : activationCodeList) {
                if (currentTime < activationCode.getValidation().getTime()) {
                    result = "1";
                    jsonresult = "{\"result\":\"" + result + "\"}";
                    return jsonresult;
                }
            }
        }*/
        //发送验证码，手机号码、类型为1、 有效期为1分钟
        smsService.sendSms(mobile, "1", "1");
        result = "验证码已发送!";
        jsonresult = "{\"result\":\"" + result + "\"}";
        return jsonresult;
    }

    /**
     * 手机找回密码
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getsmsvalidationAgain", method = RequestMethod.POST)
    public String smsvalidationAgain(HttpServletRequest request, Model model) {
        String mobile = request.getParameter("mobile");
        String jsonresult = "";
        smsService.sendSms(mobile, "1", "1");
        jsonresult = "{\"result\":\"" + 1 + "\"}";
        return jsonresult;
    }
    
    @ResponseBody
    @RequestMapping(value = "/smssubmit", method = RequestMethod.POST)
    public String smssubmit(HttpServletRequest request, Model model) {
        String mobile = request.getParameter("mobile");
        String code = request.getParameter("amp;code");
        String result = "";
        String jsonresult = "";
        try {
            //List<ActivationCode> activationcodeList = _activationCodeRepository.findByTypeAndPhoneAndCode("1", mobile, code);
        	User user = _userrepository.findByMobileAndCode(mobile, code);
        	/*if (activationcodeList == null || activationcodeList.size() == 0) {
                result = "验证码错误!";
                jsonresult = "{\"result\":\"" + result + "\"}";
                return jsonresult;
            } else {
                long currentTime = new Date().getTime();//当前时间
                for (ActivationCode activationCode : activationcodeList) {
                	//验证短信验证码是否过期,如果当前时间小于验证码的验证时间,没有过期;
                    if (currentTime < activationCode.getValidation().getTime()) {
                        result = "1";
                        jsonresult = "{\"result\":\"" + result + "\"}";
                        return jsonresult;
                    }
                }
                //验证码已经过期
                result = "验证码已经过期";
                jsonresult = "{\"result\":\"" + result + "\"}";
                return jsonresult;
            }*/
        	if (user == null) {
                result = "0";
                jsonresult = "{\"result\":\"" +result + "\"}";
                return jsonresult;
            } else {
                long currentTime = new Date().getTime();//当前时间
                	//验证短信验证码是否过期,如果当前时间小于验证码的验证时间,没有过期;
                    if (currentTime < user.getValidation().getTime()) {
                        result = "1";
                        jsonresult = "{\"result\":\"" + result + "\"}";
                        return jsonresult;
                    }
                //验证码已经过期,就删除用户
                _userrepository.delete(user);
                result = "2";
                jsonresult = "{\"result\":\"" + result + "\"}";
                return jsonresult;
            }
        } catch (Exception e) {
            result = "验证码错误";
            jsonresult = "{\"result\":\"" + result + "\"}";
            return jsonresult;
        }
     }



    @RequestMapping(value = "/submitsmsvalidation", method = RequestMethod.POST)
    public String smsvValidation(HttpServletRequest request, Model model) {
       /* String mobile = request.getParameter("mobile");
        User dbUser = _userrepository.findByMobile(mobile);
        if (dbUser != null) {
            return "redirect:/user/phonelogin/" + dbUser.getId();//已有用户注册后进入密码页面
        } else {
            //新用户登陆
            User user = new User();
            user.setMobile(mobile);
            _userrepository.save(user);
            return "redirect:/user/phonepassword/" + user.getId();
        }*/
    	String mobile = request.getParameter("phonenumber");
        User user = _userrepository.findByMobile(mobile);
        String phonefirstname=request.getParameter("phonefirstname");
    	String password = request.getParameter("phonepassword");
    	user.setPassword(CipherUtil.generatePassword(password));
    	user.setFirstName(phonefirstname);
    	user.setLastName("");
    	user.setActiveStatus(1);
    	_userrepository.save(user);
    	model.addAttribute("message", "恭喜你注册成功!您可以登录了!");
    	return "/user/login";
    }

}
