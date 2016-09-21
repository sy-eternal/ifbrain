package com.jzeen.travel.admin.controller;

import com.jzeen.travel.core.util.Constant;
import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.ExcursionGuidePlan;
import com.jzeen.travel.data.entity.HotelPlan;
import com.jzeen.travel.data.entity.Margin;
import com.jzeen.travel.data.entity.MarginPlan;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.QDatePlan;
import com.jzeen.travel.data.entity.QExcursionGuidePlan;
import com.jzeen.travel.data.entity.QMarginPlan;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.MarginPlanRepository;
import com.jzeen.travel.data.repository.MarginRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.service.ExchangeRateService;
import com.jzeen.travel.service.OrderService;
import com.mysema.query.types.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/marginplan")
public class MarginPlanController
{
    @Autowired
    private MarginPlanRepository _MarginPlanRepository;
    @Autowired
    private DatePlanRepository _datePlanRepository;
    
    @Autowired
    MarginPlanRepository _mMarginplanRepository;
    @Autowired
    MarginRepository _mMarginRepository;
    @Autowired
    private OrderRepository _orderRepository;
    @Autowired
    private OrderService _orderService;
    
    @Autowired
    private ExchangeRateService _exchangeRateService;
   
    
    
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Iterable<MarginPlan> search(@RequestParam Integer orderId)
    { 
        QMarginPlan marginPlan=QMarginPlan.marginPlan;
        Predicate predicate = marginPlan.order.id.eq(orderId);
       Iterable<MarginPlan> marginPlans = _MarginPlanRepository.findAll(predicate);
       return marginPlans;
    }
    
    @SuppressWarnings("unused")
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public String edit(@Valid Order order,HttpServletRequest request,Model model) {
        Integer orderIds = Integer.valueOf(request.getParameter("orderId"));
        Order orderplan = _orderRepository.findOne(order.getId());
        MarginPlan marginplan = _MarginPlanRepository.findByOrderId(orderIds);
        Margin margin;
        //获得数量
        String counts = request.getParameter("counts");
        Integer userTypes = Integer.valueOf(request.getParameter("userTypes"));
        if(marginplan==null)
        {
             marginplan  =new MarginPlan();
            if(userTypes==2){
                String totalprice = request.getParameter("totalprices");
                BigDecimal bd=new BigDecimal(totalprice);
                String margintypes = "比率保证金";
                 margin=_mMarginRepository.findByMargintype(margintypes);
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
                margin=_mMarginRepository.findByMargintype(margintypes);
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
                marginplan = _MarginPlanRepository.findOne(id);
                if(userTypes==2){
                    String totalprice =  request.getParameter("totalprices");
                    BigDecimal bd=new BigDecimal(totalprice);
                    String margintypes = "比率保证金";
                     margin=_mMarginRepository.findByMargintype(margintypes);
                     marginplan.setCount(0);
                     marginplan.setMarginprice(margin.getMarginprice());
                     marginplan.setMargintotalprice(bd);
                     System.out.println("totalprice"+bd);
                }else{
                    String totalprice =  request.getParameter("totalprice");
                    BigDecimal bd=new BigDecimal(totalprice);
                    String margintypes = "定额保证金";
                    margin=_mMarginRepository.findByMargintype(margintypes);
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
            Integer orderId = orderplan.getId();
            marginplan.setMargin(margin);
            marginplan.setOrder(orderplan);
           _orderRepository.save(orderplan);
           _MarginPlanRepository.save(marginplan);
      return "redirect:/order/" + orderId + "/plan?_step6";
 
    

    }
    
    //删除
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String  delete(@PathVariable int id)
    {
        _MarginPlanRepository.delete(id);
        String result="删除成功";
        return result;
    }
}