package com.jzeen.travel.website.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.SupplierActive;
import com.jzeen.travel.data.entity.SupplierPriceRule;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.UserRepository;

@Controller
@RequestMapping("/visitorcenter")
public class VisitorController {
	@RequestMapping(method = RequestMethod.GET)
	public String index()
	{
		return "/partial/visitorcenter";
	}

//	 @ResponseBody
//     @RequestMapping(value = "/index", method = RequestMethod.GET)
//     public User index(HttpServletRequest request,Integer userType)
//     {
//	        User user = _UserRepository.findByUserType(userType);
//	        return user;
//     } 
	 
//	 @RequestMapping(value = "/index", method = RequestMethod.GET)
//	    public String index(@PathVariable int userType, Model model)
//	    {
//	        User user = _UserRepository.findByUserType(userType);
//	        model.addAttribute("user", user);
//	        return "/supplier/detail";
//	    }
}

