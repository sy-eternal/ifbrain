package com.jzeen.travel.website.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.QDatePlan;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.service.DatePlanService;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/datePlan")
public class DatePlanController
{
    @Autowired
    private DatePlanRepository _datePlanRepository;

    @Autowired
    private DatePlanService _datePlanService;

    @Autowired
    private OrderRepository _orderRepository;

    @Autowired
    private CityRepository _cityRepository;

    @Autowired
    private CodeRepository _codeRepository;

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public DatePlan getById(@PathVariable Integer id)
    {
        return _datePlanRepository.findOne(id);
    }

    @ResponseBody
    @RequestMapping(value = "/findByOrderId", method = RequestMethod.GET)
    public Iterable<DatePlan> findByOrderId(@RequestParam Integer orderId)
    {
        List<DatePlan> datePlans = _datePlanRepository.findByOrderId(orderId);
        return datePlans;
    }

    @ResponseBody
    @RequestMapping(value = "/searchVehiclePlan", method = RequestMethod.GET)
    public Iterable<DatePlan> searchVehiclePlan(HttpServletRequest request)
    {
        // 使用QueryDsl构造查询条件
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        QDatePlan datePlan = QDatePlan.datePlan;
        Predicate predicate = datePlan.order.id.eq(orderId).and(datePlan.vehiclePlan.isNotNull());

        Iterable<DatePlan> datePlans = _datePlanRepository.findAll(predicate);
        return datePlans;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@RequestParam Integer orderId, @ModelAttribute DatePlan datePlan, Model model)
    {
        Order order = _orderRepository.findOne(orderId);
        model.addAttribute("order", order);

        // 获取城市数据
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);

        // 获取交通方式数据
        List<Code> vehicleTypes = _codeRepository.findByTypeOrderByValueAsc("交通方式");
        model.addAttribute("vehicleTypes", vehicleTypes);

        return "/datePlan/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam Integer orderId, DatePlan datePlan)
    {
        _datePlanService.create(orderId, datePlan);

        return "redirect:/order/" + orderId + "/plan?_step1";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable Integer id, Model model)
    {
        DatePlan datePlan = _datePlanRepository.findOne(id);
        model.addAttribute("datePlan", datePlan);

        Order order = datePlan.getOrder();
        model.addAttribute("order", order);

        // 获取城市数据
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);

        // 获取交通方式数据
        List<Code> vehicleTypes = _codeRepository.findByTypeOrderByValueAsc("交通方式");
        model.addAttribute("vehicleTypes", vehicleTypes);

        return "/datePlan/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(DatePlan datePlan)
    {
        _datePlanService.update(datePlan);

        Integer orderId = datePlan.getOrder().getId();
        return "redirect:/order/" + orderId + "/plan?_step1";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id)
    {
        // 因为对象的级联关系，此处会删除所有的下级相关对象，包括城市规划、交通规划、短途导游规划、标准导游规划等
        _datePlanRepository.delete(id);
    }
}
