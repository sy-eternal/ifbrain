package com.jzeen.travel.website.controller.characteristic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.Material;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.DefineTaskRepository;
import com.jzeen.travel.data.repository.ShopHistoryRepository;

@Controller
@RequestMapping("/characteristic")

public class Characteristic {
	
	
	
	  @RequestMapping(value = "/index",method = RequestMethod.GET)
	    public String index(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
		   	
	        return "/characteristic/index";
	    } 
	  
	  @RequestMapping(value = "/vipindex",method = RequestMethod.GET)
	    public String vipindex(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
		   	
	        return "/characteristic/vipindex";
	    } 
}
