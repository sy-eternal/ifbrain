/*package com.jzeen.travel.admin.controller;

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

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.GuideRepository;
import com.jzeen.travel.data.repository.UserRepository;

@Controller
@RequestMapping("/guideinfo")
public class GuideInfoController {
	@Autowired
	GuideRepository _guideRepository;
	
	@Autowired
	UserRepository _userRepository;
	@Autowired
	CityRepository _cityRepository;
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public DataTable<Guide, Integer> search(HttpServletRequest request) {
		// 使用QueryDsl构造查询条件
		DataTable<Guide, Integer> dataTable = DataTable.fromRequest(request,
				_guideRepository);
		return dataTable;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "/guideinfo/index";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateInit(@PathVariable int id, Model model) {
		Guide guide = _guideRepository.findOne(id);
		model.addAttribute("guide", guide);
		return "/guideinfo/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT  局部替换而不是整体更新 )
	public String update(@Valid Guide guide, BindingResult bindingResult,Model model,HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return "/guideinfo/update";
		}
		
		Guide guideA = _guideRepository.findOne(guide.getId());
		guide.setUserRegister(guideA.getUserRegister());
		Date createTime = guideA.getCreateTime();
		guide.setCreateTime(createTime);
		_guideRepository.save(guide);
		
		
		String cityName=request.getParameter("cityName");
		City hostCity =guideA.getHostCity();
		hostCity.setCityName(cityName);
		_cityRepository.save(hostCity);
		
		

        String firstname=request.getParameter("firstName");
		String lastname=request.getParameter("lastName");
		String email=request.getParameter("email");
		User userRegister = guideA.getUserRegister();
		userRegister.setFirstName(firstname);
		userRegister.setLastName(lastname);
		userRegister.setEmail(email);
		_userRepository.save(userRegister);

		return "redirect:/guideinfo";
	}
}
*/