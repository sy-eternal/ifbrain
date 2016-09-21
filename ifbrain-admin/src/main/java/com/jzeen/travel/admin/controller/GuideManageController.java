package com.jzeen.travel.admin.controller;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.admin.setting.MailSetting;
import com.jzeen.travel.admin.setting.WebUrlSetting;
import com.jzeen.travel.core.util.MailContentUtil;
import com.jzeen.travel.core.util.MailUtil;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.GuideImageRelate;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.QUser;
import com.jzeen.travel.data.entity.StandardGuide;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.VisaInstruction;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.GuideImageRelateRepository;
import com.jzeen.travel.data.repository.GuideRepository;
import com.jzeen.travel.data.repository.StandardGuideRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.mysema.query.types.Predicate;


//导游未审核通过的Controller
@Controller
@RequestMapping("/guidemanage")

public class GuideManageController {
    @Autowired
    WebUrlSetting _webUrlSetting;
	@Autowired
	UserRepository _userRepository;
	 @Autowired
	MailSetting _mailSetting;
	 @Autowired
	    GuideRepository _gGuideRepository;
	 
	 @Autowired
	    CodeRepository _codeRepository;
	@Autowired
	CityRepository _cityRepository;
	
	@Autowired
	StandardGuideRepository _standardGuideRepository;
	@Autowired
	GuideImageRelateRepository _guideImageRelateRepository;
	
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Iterable<User> search(HttpServletRequest request) {
		
		// 使用QueryDsl构造查询条件
		QUser user = QUser.user;
		Predicate predicate = user.guide.approvalstatus.eq(1).and(user.userType.eq(2));
		 Iterable<User> dataUser = _userRepository.findAll(predicate);
		
		
		return dataUser;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "/guidemanage/index";
	}
	

	
	@RequestMapping(value = "/audit/{id}", method = RequestMethod.GET)
	public String auditInit(@PathVariable int id, Model model,HttpServletRequest request) {
		User user = _userRepository.findOne(id);
		Guide guide =_gGuideRepository.findByUserId(user.getId());
		System.out.println("guideid"+guide.getId());
		//驾照扫描
		   GuideImageRelate guideimage = _guideImageRelateRepository.findByTypes(2,guide.getId());
	        Image image1 = guideimage.getImage();
	        model.addAttribute("image1", image1.getId());
	        //身份证或护照信息
	        GuideImageRelate guideimage2 = _guideImageRelateRepository.findByTypes(1,guide.getId());
            Image image2 = guideimage2.getImage();
            model.addAttribute("image2", image2.getId());
            
            
    		model.addAttribute("user", user);
    		model.addAttribute("guide", guide);
    		model.addAttribute("id",id);
    		Integer payMethodCode = guide.getPaymethodcode();
    		Code code = _codeRepository.findByTypeAndValue("支付方式", payMethodCode);
    		model.addAttribute("code", code);
		
		return "/guidemanage/audit";
	}
    // 邮箱激活

    @RequestMapping(value = "/emailActivation", method = RequestMethod.GET)
    public String emailActivation(HttpServletRequest request) throws Exception
    {
	    String result="";
    	//激活码
        String activation = request.getParameter("activationCode");
       String id =request.getParameter("id");
        //激活码有效期
        User user = _userRepository.findByGuideId(Integer.parseInt(id));
        Date activityValidity = user.getActivityValidity();
        Date currentTime = new Date();
        currentTime.before(user.getCreateTime());
        //判断激活码是否过期
        if(currentTime.before(user.getActivityValidity())&&user.getActivitycode().equals(activation)){
            //导游激活
            user.setActiveStatus(1);
            _userRepository.save(user);
            request.setAttribute("success","恭喜你，审核通过，已激活!");
        }
      return  "/emailActivation/success";
        /*//当前日期
        Date nowdate=new Date();
        
        if(nowdate.getTime()-activityValidity.getTime()> 24 * 60 * 60 * 1000){
        	model.addAttribute("message","验证码有效期过期");
        	return "/emailActivation/fail";
        }*/
    }

    //在邮箱激活页面，需要链接重新注册页面，由于主页面还没有暂定
	@RequestMapping(value = "/auditnotpass/{id}", method = RequestMethod.GET)
	public String auditnotpass(@PathVariable int id, HttpServletRequest request) {
		User user = _userRepository.findOne(id);
		 MailUtil mailUtil = new MailUtil(_mailSetting.getHost(), _mailSetting.getPort(), _mailSetting.getUsername(),_mailSetting.getPassword());
	        String to = user.getEmail();
	       String subject = "导游审核未通过,请重新注册";
	       String webRoot = _webUrlSetting.getRootUrl();
	        String text = "您提交的申请未通过审核，请重新注册!";
	        mailUtil.sendHtmlMail(_mailSetting.getFrom(), to, subject, text);
		   _userRepository.delete(user);
		return "/guidemanage/index";
	}

	
	@RequestMapping(value = "/audit", method = RequestMethod.PUT)
	public String update(@Valid Guide guide, BindingResult bindingResult,HttpServletRequest request) {
	    Guide guideA = _gGuideRepository.findOne(guide.getId());
	    User userA =_userRepository.findByGuideId(guide.getId());
		String activationCode =userA.getActivitycode();
		Integer id = guide.getId();
		 String webRoot = _webUrlSetting.getRootUrl();
		String url=webRoot+"/guidemanage/emailActivation?activationCode="+activationCode+ "&r=" + Math.random()+"&id="+id;
		
		if (bindingResult.hasErrors()) {
			return "/guidemanage/audit";
		}

      MailUtil mailUtil = new MailUtil(_mailSetting.getHost(), _mailSetting.getPort(), _mailSetting.getUsername(),_mailSetting.getPassword());
	        String to = guideA.getUser().getEmail();
	        String subject = "嘻游网导游账户激活，我要接单";
	        String text = MailContentUtil.buildUserRegMailContent(guideA.getUser().getFirstName() + " " + guideA.getUser().getLastName(), webRoot, url);
	        mailUtil.sendHtmlMail(_mailSetting.getFrom(), to, subject, text);
		    //注册导游审核通过
             guideA.setApprovalstatus(2);
			_gGuideRepository.save(guideA);
			_userRepository.save(userA);
		return "redirect:/guidemanage";
	}
	
	
	

}