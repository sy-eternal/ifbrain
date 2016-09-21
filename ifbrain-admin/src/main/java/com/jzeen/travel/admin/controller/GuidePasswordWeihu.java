package com.jzeen.travel.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.UserRepository;
@Controller
@RequestMapping("/guidepassword")
public class GuidePasswordWeihu {
	@Autowired
	UserRepository _userRegisterRepository;
	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public DataTable<User, Integer> search(HttpServletRequest request) {
		// 使用QueryDsl构造查询条件
		DataTable<User, Integer> dataTable = DataTable.fromRequest(request, _userRegisterRepository);
		return dataTable;
	}
	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "/guidepassword/index";
	}
	
	
	
}
