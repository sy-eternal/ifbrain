package com.jzeen.travel.website.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;
import com.jzeen.travel.data.entity.ExcursionGuideOccupied;
import com.jzeen.travel.data.entity.StandardGuideOccupied;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.UserRepository;
@Controller
@RequestMapping("/guidetaskarrangementtime")
public class GuideTaskArrangementTimeController {
	@Autowired
	UserRepository _userRepository;
	@RequestMapping(value = "/userArrangementtime", method = RequestMethod.GET)
	public String userArrangementtime(Model model,HttpServletRequest request){
	   User user=(User)WebUtils.getSessionAttribute(request,"user");
	    Integer id = user.getId();
	    List<StandardGuideOccupied> StandardGuideOccupiedDate =_userRepository.getOccupiedDate(id);
		model.addAttribute("list",StandardGuideOccupiedDate);
		List<ExcursionGuideOccupied> ExcursionGuideOccupiedDate1 =_userRepository.getOccupiedDate1(id);
		model.addAttribute("list1",ExcursionGuideOccupiedDate1);
		return "/guidetaskarrangementtime/guidetaskarrangementtime";
	}
	
	
	
}
