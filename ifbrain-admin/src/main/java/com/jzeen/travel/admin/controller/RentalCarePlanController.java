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
import com.jzeen.travel.data.repository.SupplierRepository;
import com.jzeen.travel.service.OrderService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
// 租车规划
@Controller
@RequestMapping("/specialplancar")
public class RentalCarePlanController {
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

            Iterable<RentalRate> specialcarrate = _specialCarRateRepository.findAll();
            return specialcarrate;
        }   
    
   
    
    @RequestMapping(value="/editPlan",method = RequestMethod.GET)
    public String create(@RequestParam("orderId") Integer orderId,@RequestParam("datePlanId") Integer datePlanId,HttpServletRequest request,Model model) {
        // 获取日期规划
//        Integer dataPlanId = Integer.parseInt(request.getParameter("dataPlanId"));
//        DatePlan datePlan = _datePlanRepository.findOne(dataPlanId);
        Order order = _orderRepository.findOne(orderId);
        model.addAttribute("order", order);
        List<DatePlan> feedbackDatePlans = _orderService.getFeedBackDatePlan(order.getId());
        model.addAttribute("feedbackDatePlans", feedbackDatePlans);

        // 城市间交通
        model.addAttribute("citysTrafficStr", _orderService.getCitysTrafficByOrderId(order.getId()));
        // 租车喜好
        model.addAttribute("carLikeType", _orderService.getCarLikeByOrderId(order.getId()));
        // 航班标准
        model.addAttribute("airPlaneType", _orderService.getAirPlaneTypeByOrderId(order.getId()));

        //年龄
       model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));
        model.addAttribute("orderId",orderId);
        model.addAttribute("datePlanId",datePlanId);
        List<RentalRate> specialcarrate = _specialCarRateRepository.findAll();
        model.addAttribute("specialcarrate",specialcarrate);
        return "/rentalcarplan/create";
    }

    /*
     * 新增租车规划
     */
    @RequestMapping(value="/addSpecialCarPlan",method = RequestMethod.POST)
    public String addInsurancePlan(@RequestParam("orderId") Integer orderId,@RequestParam("datePlanId") Integer datePlanId,HttpServletRequest request,Model model) {
       DatePlan datePlan =_datePlanRepository.findById(datePlanId);
//        Order order = _orderRepository.findById(orderId);
        String[] specialcarIds =request.getParameter("specialcarIds").split(",");
        String[] personCounts =request.getParameter("personCounts").split(",");
        String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");
        
        for (int i = 0; i < specialcarIds.length; i++) {
            if(!personCounts[i].equals("0")){
                RentalRate specialcarrate = _specialCarRateRepository.findById(new Integer(specialcarIds[i]));
                RentalCar specialcar = _specialCarRepository.findById(specialcarrate.getSpecialcar().getId());
                RentalPlan specialcarplan = new RentalPlan();
                specialcarplan.setDatePlan(datePlan);
                specialcarplan.setSpecialcar(specialcar);
                specialcarplan.setCartype(specialcar.getCartype());
                specialcarplan.setCarprice(specialcarrate.getCarrateprice());
                specialcarplan.setCount(new Integer(personCounts[i]));
                specialcarplan.setSubtotalamount(new BigDecimal(subTotalAmounts[i]));
                specialcarplan.setCreateTime(new Date());
                _specialCarPlanRepository.save(specialcarplan);
            }
        }
        return "redirect:/order/" + orderId + "/plan?_step1";
    }
    
    
    
    //修改
    @RequestMapping(value="/update",method = RequestMethod.GET)
    public String update(@RequestParam("id") Integer id,@RequestParam("orderId") Integer orderId,@RequestParam("datePlanId") Integer datePlanId,HttpServletRequest request,Model model) {
        Order order = _orderRepository.findOne(orderId);
        model.addAttribute("order", order);
        
        List<DatePlan> feedbackDatePlans = _orderService.getFeedBackDatePlan(order.getId());
        model.addAttribute("feedbackDatePlans", feedbackDatePlans);

        // 城市间交通
        model.addAttribute("citysTrafficStr", _orderService.getCitysTrafficByOrderId(order.getId()));
        // 租车喜好
        model.addAttribute("carLikeType", _orderService.getCarLikeByOrderId(order.getId()));
        // 航班标准
        model.addAttribute("airPlaneType", _orderService.getAirPlaneTypeByOrderId(order.getId()));

        //年龄
       model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));
        RentalPlan specialcarplan = _specialCarPlanRepository.findById(id);
        //合计
        model.addAttribute("orderId",orderId);
        model.addAttribute("datePlanId",datePlanId);
        model.addAttribute("specialcarplan",specialcarplan);
        return "/rentalcarplan/update";
    }
    
    
   //修改提交
    @RequestMapping(value="/updateSpecialCarPlan",method = RequestMethod.POST)
    public String updateInsurancePlan(@RequestParam("orderId") Integer orderId, @RequestParam("datePlanId") Integer datePlanId,@RequestParam("specialcarplanId")Integer specialcarplanId,HttpServletRequest request,Model model) {
        
        String[] specialcarIds =request.getParameter("specialcarIds").split(",");
        String[] personCounts =request.getParameter("personCounts").split(",");
        String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");
        DatePlan datePlan = _datePlanRepository.findById(datePlanId);
        for (int i = 0; i < specialcarIds.length; i++) {
            if(!personCounts[i].equals("0")){
                RentalRate specialcarrate = _specialCarRateRepository.findById(new Integer(specialcarIds[i]));
                RentalCar specialcar = _specialCarRepository.findById(specialcarrate.getSpecialcar().getId());
//                SpecialCarPlan specialcarplan = _specialCarPlanRepository.findById(datePlanId);            
                RentalPlan specialcarplan = _specialCarPlanRepository.findById(specialcarplanId);
                specialcarplan.setDatePlan(datePlan);
              specialcarplan.setSpecialcar(specialcar);
              specialcarplan.setCartype(specialcar.getCartype());
              specialcarplan.setCarprice(specialcarrate.getCarrateprice());
              specialcarplan.setCount(new Integer(personCounts[i]));
              specialcarplan.setSubtotalamount(new BigDecimal(subTotalAmounts[i]));
              specialcarplan.setCreateTime(new Date());
                _specialCarPlanRepository.save(specialcarplan);
            }
        }
        return "redirect:/order/" + orderId + "/plan?_step1";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String  delete(@PathVariable int id)
    {
        _specialCarPlanRepository.delete(id);
        String result="删除成功";
        return result;
    }
    
}
