package com.jzeen.travel.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jzeen.travel.data.entity.InsurancePlan;
import com.jzeen.travel.data.repository.InsurancePlanRepository;

@Controller
@RequestMapping("/insurancePlan")
public class InsurancePlanControler {
	@Autowired
	private InsurancePlanRepository  _insurancePlanRepository;
	
	@RequestMapping(value = "/insuranceplandetial/{id}", method = RequestMethod.GET)
	public String insuranceDetail(@PathVariable Integer id,Model model){
		InsurancePlan insurancePlan = _insurancePlanRepository.findById(id);
		model.addAttribute("insurancePlan",insurancePlan);
		return "/order/insurancetail";
	}
}
