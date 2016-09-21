

package com.jzeen.travel.website.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jzeen.travel.data.entity.HotelActivity;
import com.jzeen.travel.data.entity.HotelPlanRoom;
import com.jzeen.travel.data.entity.HotelRoomType;
import com.jzeen.travel.data.entity.HotelTags;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.HotelActivityRepository;
import com.jzeen.travel.data.repository.HotelPlanRepository;
import com.jzeen.travel.data.repository.HotelPlanRoomRepository;
import com.jzeen.travel.data.repository.HotelRoomTypeRepository;
import com.jzeen.travel.data.repository.HotelTagsRepository;
@Controller
@RequestMapping("/hotelPlan")
public class HotelPlanzControler
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
    /*查看酒店详情
     * */
    @RequestMapping(value = "/hotelplandetial/{id}", method = RequestMethod.GET)
    public String hotelDetaill(@PathVariable Integer id,Model model,HttpServletRequest request){
        Integer orderId=Integer.parseInt(request.getParameter("orderId"));
        model.addAttribute("orderId", orderId);
    	HotelActivity hotelActivity = _hotelActivityRepository.findOne(id);//酒店活动表
    	List<HotelTags> hotelTagslist = _hotelTagsRepository.findByhotelActivity(hotelActivity);//酒店标签表
    	List<HotelRoomType> hotelRoomTypelist =  _hotelRoomTypeRepository.findByHotelActivity(hotelActivity);//酒店房间类型表
    	List<HotelPlanRoom> hotelPlanRoomlist  = _hotelPlanRoomRepository.findByHotelRoomType(hotelRoomTypelist.get(0));//酒店规划表
    	 Image image = hotelActivity.getImage();
         model.addAttribute("image", image);
    	model.addAttribute("hotelActivity",hotelActivity);
    	model.addAttribute("hotelTagslist",hotelTagslist);
    	model.addAttribute("hotelRoomType",hotelRoomTypelist.get(0));    
    	model.addAttribute("HotelPlanRoomlist", hotelPlanRoomlist);
    	return "/order/hoteldetail";
    }   
}