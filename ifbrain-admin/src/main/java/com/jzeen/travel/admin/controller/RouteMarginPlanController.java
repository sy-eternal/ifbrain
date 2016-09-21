package com.jzeen.travel.admin.controller;

import com.jzeen.travel.core.util.Constant;
import com.jzeen.travel.data.entity.*;
import com.jzeen.travel.data.repository.*;
import com.jzeen.travel.service.ExchangeRateService;
import com.jzeen.travel.service.OrderService;
import com.mysema.query.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/routemarginplan")
public class RouteMarginPlanController {
	@Autowired
	private RouteMarginPlanRepository _routeMarginPlanRepository;

	@Autowired
	private RouteRepository _routeRepository;

	@Autowired
	private MarginRepository _marginRepository;
	
	@Autowired
	private OrderService _orderService;
	
	@RequestMapping(value = "/plan", method=RequestMethod.GET)
	public String plan(@RequestParam Integer routeId, Model model, HttpServletRequest request) {
		Route route = _routeRepository.findOne(routeId);
		model.addAttribute("order", route);
		RouteMarginPlan marginplan = _routeMarginPlanRepository.findByroute(route);
		//总价
		String margintype = "定额保证金";
		Margin margin = _marginRepository.findByMargintype(margintype);

		String margintypes = "比率保证金";
		Margin margins = _marginRepository.findByMargintype(margintypes);

		model.addAttribute("marginprice", margins.getMarginprice());
		model.addAttribute("marginprices", margin.getMarginprice());
		if (marginplan == null || marginplan.getCount() == 0) {
			model.addAttribute("count", 0);
		} else {
			marginplan.setCount(_routeMarginPlanRepository.findByCount(margintype, route.getId()));
			model.addAttribute("count", marginplan.getCount());
		}
		if (marginplan == null || marginplan.getCount() == 0) {
			model.addAttribute("count", 0);
		} else {
			marginplan.setCount(_routeMarginPlanRepository.findByCount(margintype, route.getId()));
			model.addAttribute("count", marginplan.getCount());
		}
		return "/routemarginplan/create";
	}

	@RequestMapping(value = "/edit", method=RequestMethod.PUT)
	public String editmarginplan(@RequestParam("routeId") Integer routeId,Model model, HttpServletRequest request) {
		Route route = _routeRepository.findOne(routeId);
		model.addAttribute("order", route);
		RouteMarginPlan marginplan = _routeMarginPlanRepository.findByroute(route);
        Margin margin;
        //获得数量
        String counts = request.getParameter("counts");
        Integer userTypes = Integer.valueOf(request.getParameter("userTypes"));
        if(marginplan==null)
        {
             marginplan  =new RouteMarginPlan();
            if(userTypes==2){
                String totalprice = request.getParameter("totalprices");
                BigDecimal bd=new BigDecimal(totalprice);
                String margintypes = "比率保证金";
                 margin=_marginRepository.findByMargintype(margintypes);
                 if(counts.equals("")){
                     marginplan.setCount(0);
                 }else{
                 marginplan.setCount(Integer.parseInt(counts));
                 }
                 marginplan.setMarginprice(margin.getMarginprice());
                 marginplan.setMargintotalprice(bd);
                 System.out.println("totalprice"+bd);
            }else{
                String totalprice =  request.getParameter("totalprice");
                BigDecimal bd=new BigDecimal(totalprice);
                String margintypes = "定额保证金";
                margin=_marginRepository.findByMargintype(margintypes);
                if(counts.equals("")){
                    marginplan.setCount(0);
                }else{
                marginplan.setCount(Integer.parseInt(counts));
                }
                marginplan.setMarginprice(margin.getMarginprice());
                marginplan.setMargintotalprice(bd);
                System.out.println("totalprice"+bd);
             }
            marginplan.setCreateTime(new Date());
        }else{
                Integer id = marginplan.getId();
                marginplan = _routeMarginPlanRepository.findOne(id);
                if(userTypes==2){
                    String totalprice =  request.getParameter("totalprices");
                    BigDecimal bd=new BigDecimal(totalprice);
                    String margintypes = "比率保证金";
                     margin=_marginRepository.findByMargintype(margintypes);
                     marginplan.setCount(0);
                     marginplan.setMarginprice(margin.getMarginprice());
                     marginplan.setMargintotalprice(bd);
                     System.out.println("totalprice"+bd);
                }else{
                    String totalprice =  request.getParameter("totalprice");
                    BigDecimal bd=new BigDecimal(totalprice);
                    String margintypes = "定额保证金";
                    margin=_marginRepository.findByMargintype(margintypes);
                    if(counts.equals("")){
                        marginplan.setCount(0);
                    }else{
                    marginplan.setCount(Integer.parseInt(counts));
                    }
                    marginplan.setMarginprice(margin.getMarginprice());
                    marginplan.setMargintotalprice(bd);
                    System.out.println("totalprice"+bd);
                }
            }
            marginplan.setMargin(margin);
            marginplan.setRoute(route);
           _routeMarginPlanRepository.save(marginplan);
           return "redirect:/route/detail/" + routeId ;
    }
	
}
