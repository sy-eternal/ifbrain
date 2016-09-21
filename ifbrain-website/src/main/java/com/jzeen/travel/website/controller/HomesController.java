package com.jzeen.travel.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.entity.CarouselImg;
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.NavigationbarModule;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.CarouselImgRepository;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.NavigationbarModuleRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomesController {
	@Autowired
	CarouselImgRepository _CarouselImgRepository;
	@Autowired
	NavigationbarModuleRepository _navigationbarModuleRepository;
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginInit(Model model, HttpServletRequest request) {
    	String returnUrl = request.getParameter("returnUrl");
		model.addAttribute("returnUrl", returnUrl);
		List <CarouselImg> carouselimg = _CarouselImgRepository.getAllImgOrderBySortNumber();
    	model.addAttribute("carouselimg", carouselimg);
    	List<NavigationbarModule> navigationbarModule= _navigationbarModuleRepository.findByNavigationBarTypeOrderByTypeAsc(3);
    	if(navigationbarModule.size()>0){
    		model.addAttribute("navigationbarModule", navigationbarModule);
    	}else{
    		model.addAttribute("navigationbarModule", "");
    	}
    	
    	return "/home/index";
    }

}
