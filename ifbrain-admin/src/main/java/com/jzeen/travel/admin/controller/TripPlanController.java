package com.jzeen.travel.admin.controller;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;





































import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.AirPlanInfo;
import com.jzeen.travel.data.entity.Airline;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.repository.AirlineRepository;
import com.jzeen.travel.data.repository.CityRepository;


import com.jzeen.travel.data.repository.HotelPlanRepository;
import com.jzeen.travel.data.repository.HotelPlanRoomRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.RentalPlanRepository;
import com.jzeen.travel.data.repository.SpotPlanRepository;
/*import com.jzeen.travel.data.repository.StandardGuidePlanRepository;*/
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.ExcursionGuidePlan;
import com.jzeen.travel.data.entity.FilghtPlan;
import com.jzeen.travel.data.entity.FlightDetails;
import com.jzeen.travel.data.entity.HotelPlan;
import com.jzeen.travel.data.entity.HotelPlanRoom;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.QExcursionGuidePlan;
import com.jzeen.travel.data.entity.QFilghtPlan;
import com.jzeen.travel.data.entity.QHotelPlan;
import com.jzeen.travel.data.entity.QHotelPlanRoom;
import com.jzeen.travel.data.entity.QRentalPlan;
import com.jzeen.travel.data.entity.QSpotPlan;
import com.jzeen.travel.data.entity.QStandardGuidePlan;
import com.jzeen.travel.data.entity.RentalPlan;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.data.entity.SpotThemeRelate;
import com.jzeen.travel.data.entity.StandardGuidePlan;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.ThemeActive;
import com.jzeen.travel.data.repository.FilghtDetailsRepository;
import com.jzeen.travel.data.repository.FilghtPlanRepository;
import com.jzeen.travel.data.repository.SupplierRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/tripplan")
public class TripPlanController
{

    @Autowired
    private CityRepository _cityRepository;
    @Autowired
    AirlineRepository  _airlineRepository;

    @Autowired
    FilghtDetailsRepository _FilghtDetailsRepository;
    
    @Autowired
    FilghtPlanRepository _FilghtPlanRepository;
/*    @Autowired
    private DatePlanRepository _datePlanRepository;*/
    
    @Autowired
    private RentalPlanRepository _rRentalPlanRepository;
    
/*    @Autowired
    private StandardGuidePlanRepository _GuidePlanRepository;*/
    
    @Autowired
    private HotelPlanRepository _HotelPlanRepository;
    
    @Autowired
    private HotelPlanRoomRepository _HotelPlanRoomRepository;
    
    @Autowired
    private SpotPlanRepository _SpotPlanRepository;
    @Autowired
    private OrderRepository _orderRepository;
    
    
    //飞机规划
    @ResponseBody
    @RequestMapping(value = "/findByDatePlanId", method = RequestMethod.GET)
    public Iterable<FilghtPlan> findByDatePlanId(HttpServletRequest request) throws ParseException {
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
        System.out.println("datePlan"+datePlanId);
        Iterable<FilghtPlan> data = _FilghtPlanRepository.findFilghtPlanDatePlanId(orderId, datePlanId);
        return data;
    }
    
    //飞机规划中的，根据航班查询的详情
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public String findById(HttpServletRequest request,Model model) {
        
        String id = request.getParameter("id");
        FilghtPlan filghtPlan = _FilghtPlanRepository.findOne(Integer.parseInt(id));
        List<FlightDetails> flightDetails = _FilghtDetailsRepository.findById(Integer.parseInt(id));

        model.addAttribute("flightDetails", flightDetails);
        model.addAttribute("flightPlan", filghtPlan);
        return "/flightplan/detail";
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//租车规划
    @ResponseBody
    @RequestMapping(value = "/rentalFindByDatePlanId", method = RequestMethod.GET)
    public Iterable<RentalPlan> rentalFindByDatePlanId(HttpServletRequest request) {
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
        Iterable<RentalPlan> data = _rRentalPlanRepository.findRentalPlanDatePlanId(orderId, datePlanId);
        return data;
    }
    
  //导游规划
/*    @ResponseBody
    @RequestMapping(value = "/guideFindByDatePlanId", method = RequestMethod.GET)
    public Iterable<StandardGuidePlan> guideFindByDatePlanId(HttpServletRequest request) {
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
        Iterable<StandardGuidePlan> data = _GuidePlanRepository.findStandardGuidePlanDatePlanId(orderId, datePlanId);
        return data;
    }*/
    //酒店规划
    @ResponseBody
    @RequestMapping(value = "/hotelFindByDatePlanId", method = RequestMethod.GET)
    public Iterable<HotelPlanRoom> hotelFindByDatePlanId(HttpServletRequest request) {
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
        Iterable<HotelPlanRoom> data = _HotelPlanRoomRepository.findHotelPlanRoomDatePlanId(orderId, datePlanId);
        
        return data;
    }
    //景点规划
    @ResponseBody
    @RequestMapping(value = "/SpotFindByDatePlanId", method = RequestMethod.GET)
    public Iterable<SpotPlan> SpotFindByDatePlanId(HttpServletRequest request) {
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
        Iterable<SpotPlan> data = _SpotPlanRepository.findSpotPlanDatePlanId(orderId, datePlanId);
        return data;
    }
  
    
    
}
