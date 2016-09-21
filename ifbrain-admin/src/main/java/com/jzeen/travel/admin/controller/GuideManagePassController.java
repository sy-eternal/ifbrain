package com.jzeen.travel.admin.controller;

import java.math.BigDecimal;

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

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.GuideImageRelate;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.QUser;
import com.jzeen.travel.data.entity.StandardGuide;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.GuideImageRelateRepository;
import com.jzeen.travel.data.repository.GuideRepository;
import com.jzeen.travel.data.repository.StandardGuideRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.mysema.query.types.Predicate;


//导游审核通过的Controller
@Controller
@RequestMapping("/guidemanagepass")
public class GuideManagePassController {
	@Autowired
	UserRepository _userRepository;
	 @Autowired
	 FileUploadSetting fileUploadSetting;
	@Autowired
	UserRepository _userRegisterRepository;
	@Autowired
	CityRepository _cityRepository;
	 @Autowired
     CodeRepository _codeRepository;
	 @Autowired
	 GuideRepository _gGuideRepository;
	@Autowired
	StandardGuideRepository _standardGuideRepository;
	   @Autowired
	    GuideImageRelateRepository _guideImageRelateRepository;
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Iterable<User> search(HttpServletRequest request) {
		// 使用QueryDsl构造查询条件
		QUser user = QUser.user;
		Predicate predicate = user.guide.approvalstatus.eq(2).and(user.userType.eq(2));
		 Iterable<User> dataUser = _userRepository.findAll(predicate);
		return dataUser;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "/guidemanagepass/index";
	}
	
	
	@RequestMapping(value = "/manage/{id}", method = RequestMethod.GET)
	public String auditInit(@PathVariable int id, Model model,
			HttpServletRequest request) {
		/*User user = _userRepository.findOne(id);
		String rootPath=fileUploadSetting.getRootPath();
		model.addAttribute("user",user);
		model.addAttribute("image",user.getImage());
		model.addAttribute("rootPath", fileUploadSetting.getRootPath());
		
		Integer payMethodCode = user.getPayMethodCode();
        Code code = _codeRepository.findByTypeAndValue("支付方式", payMethodCode);
        model.addAttribute("code", code);*/
		
        
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
         
//        model.addAttribute("image",guide.getImage());
        model.addAttribute("user", user);
        model.addAttribute("guide", guide);
        model.addAttribute("id",id);
        Integer payMethodCode = guide.getPaymethodcode();
        Code code = _codeRepository.findByTypeAndValue("支付方式", payMethodCode);
        model.addAttribute("code", code);
		
		
		return "/guidemanagepass/manage";
	}

	
	@RequestMapping(value = "/manage", method = RequestMethod.PUT)
	public String update(@Valid User user, BindingResult bindingResult,HttpServletRequest request) {
		String guidePrice = request.getParameter("guidePrice");
		if(guidePrice==null || "".equals(guidePrice)){
			guidePrice="0.0";
		}
		double parseguidePrice = Double.parseDouble(guidePrice);
		
		
		String rate = request.getParameter("rate");
		if(rate==null || "".equals(rate)){
			rate="0.0";
		}
		double parserate = Double.parseDouble(rate);
		
		
		String guideCost = request.getParameter("guideCost");
		if(guideCost==null || "".equals(guideCost)){
			guideCost="0.0";
		}
		double parseguideCost = Double.parseDouble(guideCost);
		
		


		
		String otCost = request.getParameter("otCost");
		if(otCost==null || "".equals(otCost)){
			otCost="0.0";
		}
		double parseotCost = Double.parseDouble(otCost);
		
		
		
		
	
		String otPrice = request.getParameter("otPrice");
		if(otPrice==null || "".equals(otPrice)){
			otPrice="0.0";
		}
		double parseotPrice = Double.parseDouble(otPrice);
		
		
		
		
		
		
		
		String commisionPercentage = request.getParameter("commisionPercentage");
		if(commisionPercentage==null || "".equals(commisionPercentage)){
			commisionPercentage="0.0";
		}
		double parsecommisionPercentage = Double.parseDouble(commisionPercentage);
		
		
		String commisionCost = request.getParameter("commisionCost");
        if(commisionCost==null || "".equals(commisionCost)){
            commisionCost="0.0";
        }
        double parsecommisionCost = Double.parseDouble(commisionCost);
		
		
		
		
		
		if (bindingResult.hasErrors()) {
			return "/guidemanage/audit";
		}
		User guideA = _userRepository.findOne(user.getId());
		/*StandardGuide standardGuide = guideA.getStandardGuide();
		  //字符串类型转换BigDecimal类型
        BigDecimal bdPercentage=new BigDecimal(parsecommisionPercentage);
        
        BigDecimal  bdCost=new BigDecimal(parseguideCost);
        
        BigDecimal   bdPrice=new BigDecimal(parseguidePrice);
        
        BigDecimal   bdotCost=new BigDecimal(parseotCost);
        
        BigDecimal   bdotPrice=new BigDecimal(parseotPrice);
        
        BigDecimal   bdparserate=new BigDecimal(parserate);
        
        BigDecimal   bdparsecommisionCost=new BigDecimal(parsecommisionCost);
        
        standardGuide.setCommisionPercentage(bdPercentage.setScale(2,BigDecimal.ROUND_HALF_UP));
        standardGuide.setGuideCost(bdCost.setScale(2,BigDecimal.ROUND_HALF_UP));
        standardGuide.setGuidePrice(bdPrice.setScale(2,BigDecimal.ROUND_HALF_UP));
        standardGuide.setOtCost(bdotCost.setScale(2,BigDecimal.ROUND_HALF_UP));
        standardGuide.setOtPrice(bdotPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
        standardGuide.setRate(bdparserate.setScale(2,  BigDecimal.ROUND_HALF_UP));
        standardGuide.setCommisionCost(bdparsecommisionCost.setScale(2,  BigDecimal.ROUND_HALF_UP));
		_standardGuideRepository.save(standardGuide);*/

		
		return "redirect:/guidemanagepass";
	}
	
	
}
