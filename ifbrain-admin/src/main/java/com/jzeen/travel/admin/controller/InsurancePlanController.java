package com.jzeen.travel.admin.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jzeen.travel.data.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jzeen.travel.data.repository.InsuranceActivityRepository;
import com.jzeen.travel.data.repository.InsurancePlanRepository;
import com.jzeen.travel.data.repository.InsuranceRateRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.SupplierRepository;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
// 保险规划
@Controller
@RequestMapping("/insurancePlan")
public class InsurancePlanController {
    @Autowired
    private InsurancePlanRepository _insurancePlanRepository;
    
    @Autowired
    private OrderRepository _orderRepository;

	@Autowired
	private InsuranceActivityRepository _insuranceActivityRepository;

	@Autowired
	private InsuranceRateRepository _insuranceRateRepository;

	@Autowired 
	private SupplierRepository _supplierRepository;

	@RequestMapping(value="/editPlan",method = RequestMethod.GET)
	public String create(@RequestParam("orderId") Integer orderId,HttpServletRequest request,Model model) {
		model.addAttribute("orderId",orderId);
		List<InsuranceRate> insuranceRates = _insuranceRateRepository.findInsuranceDuration();
		List<InsuranceRate> insuranceRates2 = _insuranceRateRepository.findHolderType();
		model.addAttribute("insuranceRates",insuranceRates);
		model.addAttribute("insuranceRates2",insuranceRates2);
		return "/insuranceplan/create";
	}
	
	@RequestMapping(value="/update",method = RequestMethod.GET)
	public String update(@RequestParam("id") Integer id,@RequestParam("orderId") Integer orderId,HttpServletRequest request,Model model) {
		
		List<InsuranceRate> insuranceRates = _insuranceRateRepository.findInsuranceDuration();
		List<InsuranceRate> insuranceRates2 = _insuranceRateRepository.findHolderType();
		InsurancePlan insurancePlan = _insurancePlanRepository.findById(id);
		//保费
		BigDecimal personCount = new BigDecimal(insurancePlan.getPersonCount());
		BigDecimal salePrice = insurancePlan.getSubTotalAmount().divide(personCount,2,BigDecimal.ROUND_HALF_EVEN);
		//合计
		model.addAttribute("insurancePlanId",id);
		model.addAttribute("orderId",orderId);
		model.addAttribute("insurancePlan",insurancePlan);
		model.addAttribute("salePrice",salePrice);
		model.addAttribute("insuranceRates",insuranceRates);
		model.addAttribute("insuranceRates2",insuranceRates2);
		return "/insuranceplan/update";
	}
	
	/**
	 * 保险活动列表显示
	 */

	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Iterable<InsuranceRate> findAll(HttpServletRequest request,Model model) {
		//保险期间
		String insuranceDuration = request.getParameter("insuranceDuration");
		//保险类型
		String holderType = request.getParameter("holderType");
		if (insuranceDuration.equals("-1") && holderType.equals("-1")) {
			Iterable<InsuranceRate> insuranceRates = _insuranceRateRepository.findAll();
			return insuranceRates;
		}else {
			QInsuranceRate InsuranceRate = QInsuranceRate.insuranceRate;   
			BooleanExpression predicate = InsuranceRate.isNotNull();
			if(!insuranceDuration.equals("-1")){
				predicate = predicate.and(InsuranceRate.insuranceDuration.contains(insuranceDuration));
			}
			if(!holderType.equals("-1")){
				predicate = predicate.and(InsuranceRate.holderType.contains(holderType));
			}
			Iterable<InsuranceRate> insuranceRates = _insuranceRateRepository.findAll(predicate);
			return insuranceRates;
		}	
	}
	
	/*
	 * 新增保险规划
	 */
	@RequestMapping(value="/addInsurancePlan",method = RequestMethod.POST)
	public String addInsurancePlan(@RequestParam("orderId") Integer orderId,HttpServletRequest request,Model model) {
		Order order = _orderRepository.findById(orderId);
		String insuranceDuration = request.getParameter("insuranceDuration");
		String holderType = request.getParameter("holderType");
		String[] insuranceactivityIds =request.getParameter("insuranceactivityIds").split(",");
		String[] personCounts =request.getParameter("personCounts").split(",");
		String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");
		
		for (int i = 0; i < insuranceactivityIds.length; i++) {
			if(!personCounts[i].equals("0")){
				InsuranceRate insuranceRate = _insuranceRateRepository.findById(new Integer(insuranceactivityIds[i]));
				InsuranceActivity insuranceActivity = _insuranceActivityRepository.findById(insuranceRate.getInsuranceActivity().getId());
	            InsurancePlan insurancePlan = new InsurancePlan();
	            insurancePlan.setOrder(order);
	            insurancePlan.setInsuranceActivity(insuranceActivity);
	            if(!holderType.equals("-1")){
	            	 insurancePlan.setHolderType(holderType);
	            }
	            else {
	            	 insurancePlan.setHolderType(insuranceRate.getHolderType());
				}
	            if(!insuranceDuration.equals("-1")){
	            	 insurancePlan.setInsuranceDuration(insuranceDuration);
	            }else
	            {
	            	insurancePlan.setInsuranceDuration(insuranceRate.getInsuranceDuration());
				}
	            insurancePlan.setInsuranceName(insuranceActivity.getInsuranceName());
	            insurancePlan.setPersonCount(new Integer(personCounts[i]));
	            insurancePlan.setSubTotalAmount(new BigDecimal(subTotalAmounts[i]));
	            insurancePlan.setCreateTime(new Date());
	            _insurancePlanRepository.save(insurancePlan);
			}
		}
		return "redirect:/order/" + orderId + "/plan?_step1";
	}
	
	@RequestMapping(value="/updateInsurancePlan",method = RequestMethod.POST)
	public String updateInsurancePlan(@RequestParam("orderId") Integer orderId,@RequestParam("insurancePlanId") Integer insurancePlanId,HttpServletRequest request,Model model) {
		
		String insuranceDuration = request.getParameter("insuranceDuration");
		String holderType = request.getParameter("holderType");
		String[] insuranceactivityIds =request.getParameter("insuranceactivityIds").split(",");
		String[] personCounts =request.getParameter("personCounts").split(",");
		String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");
		
		for (int i = 0; i < insuranceactivityIds.length; i++) {
			if(!personCounts[i].equals("0")){
				InsuranceRate insuranceRate = _insuranceRateRepository.findById(new Integer(insuranceactivityIds[i]));
				InsuranceActivity insuranceActivity = _insuranceActivityRepository.findById(insuranceRate.getInsuranceActivity().getId());
	            InsurancePlan insurancePlan = _insurancePlanRepository.findById(insurancePlanId);            
	            insurancePlan.setInsuranceActivity(insuranceActivity);
	            if(!holderType.equals("-1")){
	            	 insurancePlan.setHolderType(holderType);
	            }
	            else {
	            	 insurancePlan.setHolderType(insuranceRate.getHolderType());
				}
	            if(!insuranceDuration.equals("-1")){
	            	 insurancePlan.setInsuranceDuration(insuranceDuration);
	            }else
	            {
	            	insurancePlan.setInsuranceDuration(insuranceRate.getInsuranceDuration());
				}
	            insurancePlan.setInsuranceName(insuranceActivity.getInsuranceName());
	            insurancePlan.setPersonCount(new Integer(personCounts[i]));
	            insurancePlan.setSubTotalAmount(new BigDecimal(subTotalAmounts[i]));
	            insurancePlan.setCreateTime(new Date());
	            _insurancePlanRepository.save(insurancePlan);
			}
		}
		return "redirect:/order/" + orderId + "/plan?_step1";
	}

    /*
     * 通过id查找保险规划
     */
    @ResponseBody
    @RequestMapping(value = "/findByOrder", method = RequestMethod.GET)
    public Iterable<InsurancePlan> findByOrderId(@RequestParam Integer orderId) {
    	 QInsurancePlan insurancePlan=QInsurancePlan.insurancePlan;
         Predicate predicate = insurancePlan.order.id.eq(orderId);
        Iterable<InsurancePlan> insurancePlans = _insurancePlanRepository.findAll(predicate);
        return insurancePlans;
    }
    
    @ResponseBody
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Iterable<InsurancePlan> findById(@RequestParam Integer orderId) {
    	 QInsurancePlan insurancePlan=QInsurancePlan.insurancePlan;
         Predicate predicate = insurancePlan.order.id.eq(orderId);
        Iterable<InsurancePlan> insurancePlans = _insurancePlanRepository.findAll(predicate);
        return insurancePlans;
    }

    //删除
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String  delete(@PathVariable int id)
    {
        _insurancePlanRepository.delete(id);
        String result="删除成功";
        return result;
    }
    
    

}
