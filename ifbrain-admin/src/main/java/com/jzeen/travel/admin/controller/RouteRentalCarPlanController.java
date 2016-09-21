package com.jzeen.travel.admin.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jzeen.travel.data.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.InsuranceActivityRepository;
import com.jzeen.travel.data.repository.InsurancePlanRepository;
import com.jzeen.travel.data.repository.InsuranceRateRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.RentalPlanRepository;
import com.jzeen.travel.data.repository.RentalRateRepository;
import com.jzeen.travel.data.repository.RentalRepository;
import com.jzeen.travel.data.repository.RouteDaysRepository;
import com.jzeen.travel.data.repository.RouteRentalPlanRepository;
import com.jzeen.travel.data.repository.RouteRepository;
import com.jzeen.travel.data.repository.SupplierRepository;
import com.jzeen.travel.service.OrderService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
// 租车规划
@Controller
@RequestMapping("/routeplancar")
public class RouteRentalCarPlanController {
    @Autowired
    private RentalPlanRepository _specialCarPlanRepository;
    
    @Autowired
    private OrderRepository _orderRepository;

	@Autowired
	private RentalRepository _specialCarRepository;

	@Autowired
	private RentalRateRepository _specialCarRateRepository;

	@Autowired 
	private SupplierRepository _supplierRepository;
    @Autowired
    private DatePlanRepository _datePlanRepository;
    
    @Autowired
    private CodeRepository _codeRepository;
    @Autowired
    private OrderService _orderService;    
    @Autowired
    private CityRepository _cityRepository;
    @Autowired
    private RouteRepository _routeRepository;
    @Autowired
    private RouteDaysRepository _routeDaysRepository;
    
    @Autowired
    private  RouteRentalPlanRepository _routeRentalPlanRepository;
    /*
     * 
     */
    @ResponseBody
    @RequestMapping(value = "/findByOrder", method = RequestMethod.GET)
    public Iterable<DatePlan> findByOrderId(@RequestParam Integer orderId) {
       List<DatePlan> specialcarplan = _datePlanRepository.findByOrderId(orderId);
        return specialcarplan;
    }
    
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Iterable<RentalRate> findAll(HttpServletRequest request,Model model) {

            Iterable<RentalRate> routecarplan = _specialCarRateRepository.findAll();
            return routecarplan;
        }   
    
   
    /**
     * 跳转到线路租车新增页面 
     * @param routeId
     * @param routeDayId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="/addCar",method = RequestMethod.GET)
    public String create(@RequestParam("routeId") String routeId,@RequestParam("routeDayId") String routeDayId,HttpServletRequest request,Model model) {
        
        model.addAttribute("routeId", routeId);
        List<RentalRate> specialcarrate = _specialCarRateRepository.findAll();
        model.addAttribute("specialcarrate",specialcarrate);
        RouteDays routeDay = _routeDaysRepository.findOne(Integer.parseInt(routeDayId));
        model.addAttribute("routeDay", routeDay);
        return "/routecarplan/create";
    }

    /*
     * 新增线路租车规划
     */
    @RequestMapping(value="/addRouteCarPlan",method = RequestMethod.POST)
    public String addRouteCarPlan(@RequestParam("routeId") Integer routeId,@RequestParam("routeDayId") Integer routeDayId,HttpServletRequest request,Model model) {
        
        Route route = _routeRepository.findOne(routeId);
        
        RouteDays routeDays = _routeDaysRepository.findOne(routeDayId);
        
        
        String[] specialcarIds =request.getParameter("specialcarIds").split(",");
        String[] personCounts =request.getParameter("personCounts").split(",");
        String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");
        
        for (int i = 0; i < specialcarIds.length; i++) {
            if(!personCounts[i].equals("0")){
                RentalRate specialcarrate = _specialCarRateRepository.findById(new Integer(specialcarIds[i]));
                RentalCar specialcar = _specialCarRepository.findById(specialcarrate.getSpecialcar().getId());
                RouteRentalPlan routecarplan = new RouteRentalPlan();
                routecarplan.setRouteDays(routeDays);
                routecarplan.setSpecialcar(specialcar);
                routecarplan.setCartype(specialcar.getCartype());
                routecarplan.setCarprice(specialcarrate.getCarrateprice());
                routecarplan.setCount(new Integer(personCounts[i]));
                routecarplan.setSubtotalamount(new BigDecimal(subTotalAmounts[i]));
                routecarplan.setCreateTime(new Date());
                _routeRentalPlanRepository.save(routecarplan);
            }
        }
        return "redirect:/route/detail/" +route.getId();
    }
    
    
    
    //修改
    @RequestMapping(value="/update",method = RequestMethod.GET)
    public String update(@RequestParam("routeId") Integer routeId,@RequestParam("id") Integer id,@RequestParam("routeDayId") Integer routeDayId,HttpServletRequest request,Model model) {
        
        RouteRentalPlan routeRentalPlan = _routeRentalPlanRepository.findOne(id);
        model.addAttribute("routeRentalPlan", routeRentalPlan);
        model.addAttribute("routeId", routeId);
        model.addAttribute("routeDayId", routeDayId);
        return "/routecarplan/update";
    }
    
    
   //修改提交
    @RequestMapping(value="/updateRouteCarPlan",method = RequestMethod.POST)
    public String updateInsurancePlan(@RequestParam("routeId") Integer routeId, @RequestParam("routeRentalPlanId") Integer routeRentalPlanId,@RequestParam("routeDayId")Integer routeDayId,HttpServletRequest request,Model model) {
        
        String[] specialcarIds =request.getParameter("specialcarIds").split(",");
        String[] personCounts =request.getParameter("personCounts").split(",");
        String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");
        
        RouteDays routeDays = _routeDaysRepository.findOne(routeDayId);
        
        for (int i = 0; i < specialcarIds.length; i++) {
            if(!personCounts[i].equals("0")){
                RentalRate specialcarrate = _specialCarRateRepository.findById(new Integer(specialcarIds[i]));
                RentalCar specialcar = _specialCarRepository.findById(specialcarrate.getSpecialcar().getId());
              RouteRentalPlan routeRentalPlan = _routeRentalPlanRepository.findOne(routeRentalPlanId);
              routeRentalPlan.setRouteDays(routeDays);
              routeRentalPlan.setSpecialcar(specialcar);
              routeRentalPlan.setCartype(specialcar.getCartype());
              routeRentalPlan.setCarprice(specialcarrate.getCarrateprice());
              routeRentalPlan.setCount(new Integer(personCounts[i]));
              routeRentalPlan.setSubtotalamount(new BigDecimal(subTotalAmounts[i]));
              routeRentalPlan.setCreateTime(new Date());
              _routeRentalPlanRepository.save(routeRentalPlan);
            }
        }
        return "redirect:/route/detail/" + routeId;
    }

    
    /**
     * 删除线路租车规划
     * @param id
     * @return
     */
  
    @RequestMapping(value = "/delRouteCarPlan")
    public String  delRouteCarPlan(@RequestParam("routeId") Integer routeId,@RequestParam("routeRentalPlanId") Integer routeRentalPlanId)
    {
        _routeRentalPlanRepository.delete(routeRentalPlanId);
        return "redirect:/route/detail/"+routeId;
    }
    
}
