package com.jzeen.travel.website.controller.visitorCenter;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.GuideOrder;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/guidecenter")
public class GuideCenterController {

	@Autowired
	OrderRepository _orderRepository;
	
	@Autowired
	PlanOrderRepository _planOrderRepository;
	
	   @Autowired
	   CityRepository _cityRepository;
	
	@Autowired
	ExchangeRateRepository   _exchangeRateRepository;

	@Autowired
	StandardGuideOccupiedRepository   _standardGuideOccupiedRepository;
	@Autowired
	ExcursionGuideOccupiedRepository _excursionGuideOccupiedRepository;
	@Autowired
	ConfiremedTravelerRepository _confiremedTravelerRepository;
	@Autowired
    GuideOrderRepository _guideorderRespository;
	@RequestMapping(method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model)
	{
		User user =(User)WebUtils.getSessionAttribute(request, "user");
		GuideOrder guideorder =(GuideOrder)WebUtils.getSessionAttribute(request, "guideorder");
		WebUtils.setSessionAttribute(request, "usertyoes", user.getFirstName()+user.getLastName());
		WebUtils.setSessionAttribute(request, "usertyoe", user.getUserType());
		//WebUtils.setSessionAttribute(request, "order", order);
		if(user==null){
			return"redirect:/user/login";
		}
		return "/guide/orderlist";
	}

	/**
	 * 我的订单
	 */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<GuideOrder> search(HttpServletRequest request)
    {
        User user =(User)WebUtils.getSessionAttribute(request, "user");
        List<GuideOrder> data = _guideorderRespository.findByUser(user);
        return data;
    }

    //详细信息
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailInit(@PathVariable int id, Model model)
    {
        GuideOrder guideorder = _guideorderRespository.findOne(id);
        model.addAttribute("guideorder", guideorder);
        List<City> city = _cityRepository.findAll();
        model.addAttribute("city", city);
        return "/guide/orderdetail";
    }
    

    /*
     * 删除订单
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _guideorderRespository.delete(id);

    }



	
}
