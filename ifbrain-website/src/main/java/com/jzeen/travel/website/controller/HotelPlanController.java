/*package com.jzeen.travel.website.controller;

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

import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.HotelPlan;
import com.jzeen.travel.data.entity.SupplierPriceRule;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.HotelPlanRepository;
import com.jzeen.travel.data.repository.SupplierPriceRuleRepository;

// 酒店规划
@Controller
@RequestMapping("/hotelPlan")
public class HotelPlanController
{
    @Autowired
    private DatePlanRepository _datePlanRepository;

    @Autowired
    private HotelPlanRepository _hotelPlanRepository;

    @Autowired
    private SupplierPriceRuleRepository _supplierPriceRuleRepository;

    @ResponseBody
    @RequestMapping(value = "/findByDatePlanOrderId", method = RequestMethod.GET)
    public List<HotelPlan> findByDatePlanOrderId(@RequestParam Integer orderId)
    {
        List<HotelPlan> hotelPlans = _hotelPlanRepository.findByDatePlanOrderId(orderId);
        return hotelPlans;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editInit(Model model, HttpServletRequest request)
    {
        // 获取日期规划
        String dataPlanId = request.getParameter("dataPlanId");
        DatePlan datePlan = _datePlanRepository.findOne(Integer.parseInt(dataPlanId));
        model.addAttribute(datePlan);
        model.addAttribute("order", datePlan.getOrder());

        List<SupplierPriceRule> supplierPriceRules = _supplierPriceRuleRepository.getBySupplierType("酒店");
        model.addAttribute("supplierPriceRules", supplierPriceRules);

        return "/order/editHotelPlan";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute DatePlan datePlan)
    {
        DatePlan datePlanInDb = _datePlanRepository.findOne(datePlan.getId());
        HotelPlan hotelPlan = datePlan.getHotelPlan();
        if (datePlanInDb.getHotelPlan() == null)
        {
            // 新增
            Date now = new Date();
            hotelPlan.setDatePlan(datePlanInDb);
            hotelPlan.setCreateTime(now);

            // 保存景点规划
            _hotelPlanRepository.save(hotelPlan);
        }
      /*  else
        {
            // 修改
            HotelPlan hotelPlanInDb = datePlanInDb.getHotelPlan();
            hotelPlanInDb.setSupplierPriceRule(hotelPlan.getSupplierPriceRule());
            hotelPlanInDb.setHotelName(hotelPlan.getHotelName());
            hotelPlanInDb.setHotelAddress(hotelPlan.getHotelAddress());
            hotelPlanInDb.setHotelTel(hotelPlan.getHotelTel());
            hotelPlanInDb.setHotelRank(hotelPlan.getHotelRank());
            hotelPlanInDb.setHotelCostPrice(hotelPlan.getHotelCostPrice());
            hotelPlanInDb.setHotelRoomCount(hotelPlan.getHotelRoomCount());

            // 保存景点规划
            _hotelPlanRepository.save(hotelPlanInDb);
        }*/

/*        Integer orderId = datePlanInDb.getOrder().getId();
        return "redirect:/order/" + orderId + "/plan?_step3";
    }
}
*/