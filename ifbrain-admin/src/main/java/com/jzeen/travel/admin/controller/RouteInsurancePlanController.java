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
import com.jzeen.travel.data.repository.RouteInsurancePlanRepository;
import com.jzeen.travel.data.repository.RouteRepository;
import com.jzeen.travel.data.repository.SupplierRepository;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
// 保险规划
@Controller
@RequestMapping("/routeinsurancePlan")
public class RouteInsurancePlanController {
    @Autowired
    private RouteInsurancePlanRepository _insurancePlanRepository;
    
    @Autowired
    private OrderRepository _orderRepository;
    
    @Autowired
    private RouteRepository _routeRepository;

	@Autowired
	private InsuranceActivityRepository _insuranceActivityRepository;

	@Autowired
	private InsuranceRateRepository _insuranceRateRepository;

	@Autowired 
	private SupplierRepository _supplierRepository;

	@RequestMapping(value="/editPlan",method = RequestMethod.GET)
	public String create(@RequestParam("routeId") Integer routeId,HttpServletRequest request,Model model) {
		model.addAttribute("routeId",routeId);
		List<InsuranceRate> insuranceRates = _insuranceRateRepository.findInsuranceDuration();
		List<InsuranceRate> insuranceRates2 = _insuranceRateRepository.findHolderType();
		model.addAttribute("insuranceRates",insuranceRates);
		model.addAttribute("insuranceRates2",insuranceRates2);
		return "/routeinsuranceplan/create";
	}
	
	@RequestMapping(value="/update",method = RequestMethod.GET)
	public String update(@RequestParam("id") Integer id,@RequestParam("routeId") Integer routeId,HttpServletRequest request,Model model) {
		
		List<InsuranceRate> insuranceRates = _insuranceRateRepository.findInsuranceDuration();
		List<InsuranceRate> insuranceRates2 = _insuranceRateRepository.findHolderType();
		RouteInsurancePlan insurancePlan = _insurancePlanRepository.findOne(id);
		//保费
		BigDecimal personCount = new BigDecimal(insurancePlan.getPersonCount());
		BigDecimal salePrice = insurancePlan.getSubTotalAmount().divide(personCount,2,BigDecimal.ROUND_HALF_EVEN);
		//合计
		model.addAttribute("insurancePlanId",id);
		model.addAttribute("routeId",routeId);
		model.addAttribute("insurancePlan",insurancePlan);
		model.addAttribute("salePrice",salePrice);
		model.addAttribute("insuranceRates",insuranceRates);
		model.addAttribute("insuranceRates2",insuranceRates2);
		return "/routeinsuranceplan/update";
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
	public String addInsurancePlan(@RequestParam("routeId") Integer routeId,HttpServletRequest request,Model model) {
		Route route = _routeRepository.findOne(routeId);
		String insuranceDuration = request.getParameter("insuranceDuration");
		String holderType = request.getParameter("holderType");
		String[] insuranceactivityIds =request.getParameter("insuranceactivityIds").split(",");
		String[] personCounts =request.getParameter("personCounts").split(",");
		String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");
		
		for (int i = 0; i < insuranceactivityIds.length; i++) {
			if(!personCounts[i].equals("0")){
				InsuranceRate insuranceRate = _insuranceRateRepository.findById(new Integer(insuranceactivityIds[i]));
				InsuranceActivity insuranceActivity = _insuranceActivityRepository.findById(insuranceRate.getInsuranceActivity().getId());
	            RouteInsurancePlan insurancePlan = new RouteInsurancePlan();
	            insurancePlan.setRoute(route);
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
		return "redirect:/route/detail/" + routeId ;
	}
	
	@RequestMapping(value="/updateInsurancePlan",method = RequestMethod.POST)
	public String updateInsurancePlan(@RequestParam("routeId") Integer routeId,@RequestParam("insurancePlanId") Integer insurancePlanId,HttpServletRequest request,Model model) {
		
		String insuranceDuration = request.getParameter("insuranceDuration");
		String holderType = request.getParameter("holderType");
		String[] insuranceactivityIds =request.getParameter("insuranceactivityIds").split(",");
		String[] personCounts =request.getParameter("personCounts").split(",");
		String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");
		
		for (int i = 0; i < insuranceactivityIds.length; i++) {
			if(!personCounts[i].equals("0")){
				InsuranceRate insuranceRate = _insuranceRateRepository.findById(new Integer(insuranceactivityIds[i]));
				InsuranceActivity insuranceActivity = _insuranceActivityRepository.findById(insuranceRate.getInsuranceActivity().getId());
	            RouteInsurancePlan insurancePlan = _insurancePlanRepository.findOne(insurancePlanId);           
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
		return "redirect:/route/detail/" + routeId ;
	}

    /*
     * 通过id查找保险规划
     */
    @ResponseBody
    @RequestMapping(value = "/findByOrder", method = RequestMethod.GET)
    public Iterable<RouteInsurancePlan> findByOrderId(@RequestParam Integer routeId) {
    	 QRouteInsurancePlan insurancePlan=QRouteInsurancePlan.routeInsurancePlan;
         Predicate predicate = insurancePlan.route.id.eq(routeId);
        Iterable<RouteInsurancePlan> insurancePlans = _insurancePlanRepository.findAll(predicate);
        return insurancePlans;
    }
    
    @ResponseBody
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Iterable<RouteInsurancePlan> findById(@RequestParam Integer routeId) {
    	QRouteInsurancePlan insurancePlan=QRouteInsurancePlan.routeInsurancePlan;
    	Predicate predicate = insurancePlan.route.id.eq(routeId);
        Iterable<RouteInsurancePlan> insurancePlans = _insurancePlanRepository.findAll(predicate);
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
