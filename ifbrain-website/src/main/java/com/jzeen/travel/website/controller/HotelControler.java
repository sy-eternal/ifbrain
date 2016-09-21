

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

import com.jzeen.travel.data.entity.HotelActivity;
import com.jzeen.travel.data.entity.HotelPlan;
import com.jzeen.travel.data.entity.HotelPlanRoom;
import com.jzeen.travel.data.entity.HotelRoomType;
import com.jzeen.travel.data.entity.HotelTags;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.QHotelActivity;
import com.jzeen.travel.data.entity.SpotThemeRelate;
import com.jzeen.travel.data.entity.Theme;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.HotelActivityRepository;
import com.jzeen.travel.data.repository.HotelPlanRepository;
import com.jzeen.travel.data.repository.HotelPlanRoomRepository;
import com.jzeen.travel.data.repository.HotelRoomTypeRepository;
import com.jzeen.travel.data.repository.HotelTagsRepository;
@Controller
@RequestMapping("/hotel")
public class HotelControler
{
    @Autowired
    HotelRoomTypeRepository _hotelRoomTypeRepository;
    @Autowired
    private CityRepository _cityRepository;
    @Autowired
    HotelPlanRepository  _hotelPlanRepository;
    
    @Autowired
    HotelPlanRoomRepository _hotelPlanRoomRepository;
    @Autowired
    HotelActivityRepository  _hotelActivityRepository;
    @Autowired
    DatePlanRepository _datePlanRepository;
    @Autowired
    HotelTagsRepository _hotelTagsRepository;
    @RequestMapping(value = "/searchhotel", method = RequestMethod.GET)
    public String hotelType(Model model,HttpServletRequest request){
        List<HotelActivity> hotelplan = _hotelActivityRepository.findAll();
        model.addAttribute("hotelplan",hotelplan);
        
    	return "/strategy/searchhotel";
    }
    @RequestMapping(value = "/hotelSpecific", method = RequestMethod.GET)
    public String hotelDetaill(Model model,HttpServletRequest request){
    	return "/strategy/hotel";
    }
}