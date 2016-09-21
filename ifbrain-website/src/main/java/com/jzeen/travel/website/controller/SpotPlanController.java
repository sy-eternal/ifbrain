package com.jzeen.travel.website.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.data.entity.SpotPlanTicket;
import com.jzeen.travel.data.entity.SpotTicketType;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.SpotPlanRepository;
import com.jzeen.travel.data.repository.SpotRepository;
import com.jzeen.travel.data.repository.SpotTicketTypeRepository;

// 景点规划
@Controller
@RequestMapping("/spotPlan")
public class SpotPlanController
{
    @Autowired
    private SpotPlanRepository _spotPlanRepository;

    @Autowired
    private DatePlanRepository _datePlanRepository;

    @Autowired
    private OrderRepository _orderRepository;

    @Autowired
    private CityRepository _cityRepository;

    @Autowired
    private CodeRepository _codeRepository;

    @Autowired
    private SpotRepository _spotRepository;

    @Autowired
    SpotTicketTypeRepository _spotTicketTypeRepository;

    @ResponseBody
    @RequestMapping(value = "/findByDatePlanOrderId", method = RequestMethod.GET)
    public List<SpotPlan> findByDatePlanOrderId(@RequestParam Integer orderId)
    {
        List<SpotPlan> spotPlans = _spotPlanRepository.findByDatePlanOrderId(orderId);
        return spotPlans;
    }

   /* @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editInit(Model model, HttpServletRequest request)
    {
        // 获取日期规划
        Integer dataPlanId = Integer.parseInt(request.getParameter("dataPlanId"));
        DatePlan datePlan = _datePlanRepository.findOne(dataPlanId);
        SpotPlan spotPlan = datePlan.getSpotPlan();
        if (spotPlan == null)
        {
            spotPlan = new SpotPlan();
            SpotPlanTicket spotPlanTicket = new SpotPlanTicket();
            spotPlanTicket.setSpotPlan(spotPlan);
            spotPlan.setSpotPlanTicket(spotPlanTicket);
            datePlan.setSpotPlan(spotPlan);
        }
        model.addAttribute("datePlan", datePlan);
        model.addAttribute("order", datePlan.getOrder());

        // 获取交通方式
        Integer vehicleTypeCode = datePlan.getVehiclePlan().getVehicleTypeCode();
        Code vehicleType = _codeRepository.findByTypeAndValue("交通方式", vehicleTypeCode);
        model.addAttribute("vehicleType", vehicleType);

        // 获取景点列表
        List<Spot> spots = _spotRepository.findAll();
        model.addAttribute("spots", spots);

        // 获取景点门票类型列表
        List<SpotTicketType> spotTicketTypes = _spotTicketTypeRepository.findAll();
        model.addAttribute("spotTicketTypes", spotTicketTypes);

        return "/order/editSpotPlan";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute DatePlan datePlan)
    {
        DatePlan datePlanInDb = _datePlanRepository.findOne(datePlan.getId());
        SpotPlan spotPlan = datePlan.getSpotPlan();
        if (datePlanInDb.getSpotPlan() == null)
        {
            // 新增
            Date now = new Date();
            spotPlan.setCreateTime(now);

            SpotPlanTicket spotPlanTicket = spotPlan.getSpotPlanTicket();
            spotPlanTicket.setSpotPlan(spotPlan);
            spotPlanTicket.setCreateTime(now);

            SpotTicketType spotTicketType = spotPlanTicket.getSpotTicketType();
            BigDecimal price = spotTicketType.getPrice();
            spotPlanTicket.setSalePrice(price);
            spotPlan.setDatePlan(datePlanInDb);

            // 保存景点规划
            _spotPlanRepository.save(spotPlan);
        }
        else
        {
            // 修改
            SpotPlan spotPlanInDb = datePlanInDb.getSpotPlan();
            spotPlanInDb.setSpot(spotPlan.getSpot());
            spotPlanInDb.getSpotPlanTicket().setSpotTicketType(spotPlan.getSpotPlanTicket().getSpotTicketType());
            spotPlanInDb.getSpotPlanTicket().setPersonCount(spotPlan.getSpotPlanTicket().getPersonCount());

            // 保存景点规划
            _spotPlanRepository.save(spotPlanInDb);
        }

        Integer orderId = datePlanInDb.getOrder().getId();
        return "redirect:/order/" + orderId + "/plan?_step2";
    }*/
}
