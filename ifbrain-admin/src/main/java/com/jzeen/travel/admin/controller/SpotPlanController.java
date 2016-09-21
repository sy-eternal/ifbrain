package com.jzeen.travel.admin.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.jzeen.travel.core.util.Constant;
import com.jzeen.travel.data.entity.*;
import com.jzeen.travel.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.SpotPlanRepository;
import com.jzeen.travel.data.repository.SpotPlanTicketRepository;
import com.jzeen.travel.data.repository.SpotRepository;
import com.jzeen.travel.data.repository.SpotTagsRepository;
import com.jzeen.travel.data.repository.SpotThemeRelateRepository;
import com.jzeen.travel.data.repository.SpotTicketTypeRepository;
import com.jzeen.travel.data.repository.ThemeActiveRepository;
import com.mysema.query.types.expr.BooleanExpression;

// 景点规划
@Controller
@RequestMapping("/spotplan")
public class SpotPlanController {
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

	@Autowired
	private OrderService _orderService;

	@Autowired
	private SpotPlanTicketRepository _spotPlanTicketRepository;

	@Autowired
	private SpotTagsRepository _spotTagsRepository;

	@Autowired
	SpotThemeRelateRepository _spotThemeRelateRepository;

	@Autowired
	ThemeActiveRepository _themeActiveRepository;

	@ResponseBody
	@RequestMapping(value = "/findByDatePlanOrderId", method = RequestMethod.GET)
	public List<SpotPlan> findByDatePlanOrderId(@RequestParam Integer orderId) {
		List<SpotPlan> spotPlans = _spotPlanRepository.findByDatePlanOrderId(orderId);
		return spotPlans;
	}

	@RequestMapping(value = "/plan",method=RequestMethod.GET)
	public String step2(@RequestParam("orderId") Integer orderId, Model model,HttpServletRequest request) {
		Order order = _orderRepository.findOne(orderId);
		model.addAttribute("order", order);
		//Integer spotPlanId = Integer.parseInt(request.getParameter("spotPlanId"));
		//Integer spotPlanTicketId = Integer.parseInt(request.getParameter("spotPlanTicketId"));
		//model.addAttribute("spotPlanTicketId",spotPlanTicketId);
		//model.addAttribute("spotPlanId",spotPlanId);
		
		//获取城市数据
		List<City> citys = _cityRepository.findAll();
		model.addAttribute("citys", citys);
		Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
		model.addAttribute("datePlanId",datePlanId);
		//年龄
		model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));
		//model.addAttribute("orderId",orderId);
		return "/spotsplan/editSpotPlan";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editInit(Model model, HttpServletRequest request) {
		//获取订单详情
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		Order order = _orderRepository.findOne(orderId);
		model.addAttribute("order", order);
		//获取城市数据
		List<City> citys = _cityRepository.findAll();
		model.addAttribute("citys", citys);
		// 获取日期规划
		Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
		//获取目的城市
		String cityName = request.getParameter("city");
		City city = _cityRepository.findByCityName(cityName);
		List<Spot> spots = _spotRepository.findByCityid(city);
		model.addAttribute("spots",spots);
		model.addAttribute("datePlanId",datePlanId);
		model.addAttribute("cityName",cityName);
		List<ThemeActive> themeActives = _themeActiveRepository.findAll();
		model.addAttribute("themeActives",themeActives);
		model.addAttribute("city",city);
		model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));
		return "/spotsplan/editSpotPlanDetail";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
		//获取订单详情
		Integer orderId = Integer.parseInt(request.getParameter("amp;orderId"));
		Order order = _orderRepository.findOne(orderId);
		model.addAttribute("order", order);
		//获取城市数据
		List<City> citys = _cityRepository.findAll();
		model.addAttribute("citys", citys);
		// 获取日期规划
		Integer datePlanId = Integer.parseInt(request.getParameter("amp;datePlanId"));
		//获取目的城市
		String cityName = request.getParameter("amp;cityName");
		//cityName = new String(cityName.getBytes("ISO-8859-1"),"utf-8" ); 
		City city = _cityRepository.findByCityName(cityName);
		List<Spot> spots = _spotRepository.findByCityid(city);
		model.addAttribute("spots",spots);
		model.addAttribute("datePlanId",datePlanId);
		model.addAttribute("cityName",cityName);
		List<ThemeActive> themeActives = _themeActiveRepository.findAll();
		model.addAttribute("themeActives",themeActives);
		model.addAttribute("city",city);
		model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));
		return "/spotsplan/editSpotPlanDetail";
	}

	/*
	 * 通过多个景点主题查找景点
	 */
	@RequestMapping(value = "/searchByTheme", method = RequestMethod.POST)
	public String searchByTheme(HttpServletRequest request,Model model){
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		Order order = _orderRepository.findOne(orderId);
		model.addAttribute("order", order);
		//获取城市数据
		List<City> citys = _cityRepository.findAll();
		model.addAttribute("citys", citys);
		// 获取日期规划
		Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
		model.addAttribute("datePlanId",datePlanId);
		String cityName = request.getParameter("cityName");
		City city = _cityRepository.findByCityName(cityName);
		//model.addAttribute("city",city);
		model.addAttribute("cityName",cityName);
		List<Spot> spots = _spotRepository.findByCityid(city);
		List<ThemeActive> themeActives2 = _themeActiveRepository.findAll();
		model.addAttribute("themeActives",themeActives2);
		String[] spotThemes = request.getParameter("spotThemes").split(",");
		List<ThemeActive> themeActives = new ArrayList<ThemeActive>();
		List<Integer> themeIds = new ArrayList<Integer>();
		List<Spot> spots2 = new ArrayList<Spot>();
		List<Integer> themeIds2 = new ArrayList<Integer>();
       if(spotThemes.length!=0){
		for (int i = 0; i < spotThemes.length; i++) {
			ThemeActive themeActive = _themeActiveRepository.findByTheme(spotThemes[i]);
			themeIds.add(themeActive.getId());
			themeActives.add(themeActive);
		}
		for (int i = 0; i < spots.size(); i++) {

			List<SpotThemeRelate> spotThemeRelates = _spotThemeRelateRepository.findBySpot(spots.get(i).getId());
			if(spotThemeRelates.size()>0){
				int j=0;
				for ( j = 0; j < spotThemeRelates.size(); j++) {
					themeIds2.add(spotThemeRelates.get(j).getTheme().getId());
				}
				if (themeIds.equals(themeIds2)) {
					spots2.add(spotThemeRelates.get(j-1).getSpot());
				}
			}
			themeIds2 = new ArrayList<Integer>();
		}
		model.addAttribute("spots",spots2);
       }else {
    	   model.addAttribute("spots",spots);
	}

		return "/spotsplan/editSpotPlanDetail";
	}

	@RequestMapping(value = "/ticketType", method = RequestMethod.GET)
	public String ticketType(@RequestParam("orderId") Integer orderId,HttpServletRequest request,Model model){
		Integer spotId = Integer.parseInt(request.getParameter("spotId"));
		model.addAttribute("spotId",spotId);
		/*Integer spotPlanId = Integer.parseInt(request.getParameter("spotPlanId"));
		model.addAttribute("spotPlanId",spotPlanId);*/
		Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
		/*Integer spotPlanTicketId = Integer.parseInt(request.getParameter("spotPlanTicketId"));
		model.addAttribute("spotPlanTicketId",spotPlanTicketId);*/
		model.addAttribute("datePlanId",datePlanId);
		model.addAttribute("orderId",orderId);
		return "/spotsplan/ticketType";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(@RequestParam("orderId") Integer orderId,HttpServletRequest request,Model model){
		Integer spotId = Integer.parseInt(request.getParameter("spotId"));
		model.addAttribute("spotId",spotId);
		Integer spotPlanId = Integer.parseInt(request.getParameter("spotPlanId"));
		model.addAttribute("spotPlanId",spotPlanId);
		Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
		Integer spotPlanTicketId = Integer.parseInt(request.getParameter("spotPlanTicketId"));
		SpotPlan spotPlan = _spotPlanRepository.findOne(spotPlanId);
		SpotPlanTicket spotPlanTicket = _spotPlanTicketRepository.findOne(spotPlanTicketId);
		model.addAttribute("spotPlan",spotPlan);
		model.addAttribute("spotPlanTicket",spotPlanTicket);
		model.addAttribute("spotPlanTicketId",spotPlanTicketId);
		model.addAttribute("datePlanId",datePlanId);
		model.addAttribute("orderId",orderId);
		return "/spotsplan/updateticketType";
	}

	@ResponseBody
	@RequestMapping(value = "/searchTicketType", method = RequestMethod.GET)
	public Iterable<SpotTicketType> ticketTypes(HttpServletRequest request,Model model){
		Integer spotId = Integer.parseInt(request.getParameter("spotId"));
		model.addAttribute("spotId",spotId);
		Iterable<SpotTicketType> spotIterable = _spotTicketTypeRepository.findBySpotsId(spotId);
		return  spotIterable;
	}

	/*
	 * 新增景点规划
	 */
	@RequestMapping(value="/addSpotPlan",method = RequestMethod.POST)
	public String addSpotPlan(@RequestParam("orderId") Integer orderId,HttpServletRequest request,Model model) {
		/*Order order = _orderRepository.findById(orderId);*/
		/*String insuranceDuration = request.getParameter("insuranceDuration");
		String holderType = request.getParameter("holderType");*/
		String[] spotTicketTypeIds =request.getParameter("spotTicketTypeIds").split(",");
		String[] personCounts =request.getParameter("personCounts").split(",");
		String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");
		Integer spotId = Integer.parseInt(request.getParameter("spotId"));
		Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
		String totalPrice = request.getParameter("totalprices");
		/*Integer spotPlanId = Integer.parseInt(request.getParameter("spotPlanId"));
		Integer spotPlanTicketId = Integer.parseInt(request.getParameter("spotPlanTicketId"));*/
		DatePlan datePlan = _datePlanRepository.findById(datePlanId);
		Spot spot = _spotRepository.findById(spotId);
		SpotPlan spotPlan = new SpotPlan();
			spotPlan.setDatePlan(datePlan);
			spotPlan.setCreateTime(new Date());
			spotPlan.setSpot(spot);
			spotPlan.setSubtotalAmount(new BigDecimal(totalPrice));
			_spotPlanRepository.save(spotPlan);
			for (int i = 0; i < spotTicketTypeIds.length; i++) {
				  if(!personCounts[i].equals("0")){
				//通过门票类型id找到门票类型
				SpotTicketType spotTicketType = _spotTicketTypeRepository.findOne(new Integer(spotTicketTypeIds[i]));
				//景点门票规划
				SpotPlanTicket spotPlanTicket = new SpotPlanTicket();
				//景点规划
				//景点门票规划
				spotPlanTicket.setSpotPlan(spotPlan);
				spotPlanTicket.setSalePrice(new BigDecimal(subTotalAmounts[i]));
				spotPlanTicket.setPersonCount(new Integer(personCounts[i]));
				spotPlanTicket.setSpotTicketType(spotTicketType);
				spotPlanTicket.setCreateTime(new Date());
				_spotPlanTicketRepository.save(spotPlanTicket);
				  }
			}
		return "redirect:/order/" + orderId + "/plan?_step1";
	}
	
	/*
	 * 修改景点规划
	 */
	@RequestMapping(value="/updateSpotPlan",method = RequestMethod.POST)
	public String updateSpotPlan(@RequestParam("orderId") Integer orderId,HttpServletRequest request,Model model) {	
		Integer personCount =Integer.parseInt(request.getParameter("personCount"));
		BigDecimal salePrice =new BigDecimal(request.getParameter("salePrice"));
		Integer id = Integer.parseInt(request.getParameter("spotPlanTicketId"));
		SpotPlanTicket spotPlanTicket = _spotPlanTicketRepository.findOne(id);
		spotPlanTicket.setPersonCount(personCount);
		spotPlanTicket.setSalePrice(salePrice);
		SpotPlan spotPlan = spotPlanTicket.getSpotPlan();
		spotPlan.setSubtotalAmount(new BigDecimal(0));
		_spotPlanTicketRepository.save(spotPlanTicket);
		List<SpotPlanTicket> spotPlanTickets = _spotPlanTicketRepository.findBySpotPlan(spotPlan);
		for (int i = 0; i < spotPlanTickets.size(); i++) {
			spotPlan.setSubtotalAmount(spotPlan.getSubtotalAmount().add(spotPlanTickets.get(i).getSalePrice()));
		}
		_spotPlanRepository.save(spotPlan);
		return "redirect:/order/" + orderId + "/plan?_step1";
	}


	//修改景点规划，添加内部订单号
	@RequestMapping(value = "/updateSpotPlan/{id}", method = RequestMethod.GET)
	public String updateSpotPlan(@PathVariable Integer id, Model model) {
		SpotPlan spotPlan = _spotPlanRepository.findOne(id);
		model.addAttribute("spotPlan", spotPlan);

		return "/datePlan/updateSpot";
	}
/*
	@RequestMapping(value = "/updateSpotProviderOrder", method = RequestMethod.POST)
	public String updateSpotProviderOrder(SpotPlan spotPlan) {

		SpotPlan newSpotPlan = _spotPlanRepository.findOne(spotPlan.getId());
		newSpotPlan.setSpotProviderOrder(spotPlan.getSpotProviderOrder());
		_spotPlanRepository.save(newSpotPlan);

		Integer orderId = newSpotPlan.getDatePlan().getOrder().getId();
		return "redirect:/order/" + orderId + "/planedInfo";
	}
*/
	@RequestMapping(value = "/updateDetail", method = RequestMethod.GET)
	public String updateSpot(HttpServletRequest request,Model model){
		Integer id = Integer.parseInt(request.getParameter("spotId"));
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
		String cityName = request.getParameter("cityName");
		model.addAttribute("orderId",orderId);
		model.addAttribute("datePlanId",datePlanId);
		model.addAttribute("cityName",cityName);
		Spot spot = _spotRepository.findById(id);
		List<SpotTags> spotTags = _spotTagsRepository.findBySpot(spot);
		model.addAttribute("spot",spot);
		model.addAttribute("spotTags",spotTags);
		return "/spotsplan/update";
	}

	@RequestMapping(value = "/updateDetail", method = RequestMethod.POST)
	public String updateDetail(@Valid Spot spot,Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		//修改景点的详细信息
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
		String cityName = request.getParameter("cityName");
		System.out.println("**********"+cityName);
		cityName = new String(cityName.getBytes("UTF-8"), "ISO-8859-1");
		
		Spot spots = _spotRepository.findById(id);
		spots.setEnlocation(spot.getEnlocation());
		spots.setLocation(spot.getLocation());
		spots.setSpotsdescription(spot.getSpotsdescription());
		spots.setSpotssummary(spot.getSpotssummary());
		spots.setSpecialnotes(spot.getSpecialnotes());
		spots.setClothingtips(spot.getClothingtips());
		spots.setSpendingtips(spot.getSpendingtips());
		spots.setWeathertips(spot.getWeathertips());
		_spotRepository.save(spots);

		List<SpotTags> hotelTag=_spotTagsRepository.findBySpot(spot);
		for(int i=0;i<hotelTag.size();i++){
			_spotTagsRepository.delete(hotelTag.get(i).getId());
		}

		//获得页面的标签从新插入
		String[] tags=request.getParameterValues("tag");

		for(int i=0;i<tags.length;i++){
			if(!(tags[i].equals(null)) && (!(tags[i].equals("")))){
				SpotTags spotTags = new SpotTags();
				spotTags.setSpot(spot);
				spotTags.setTag(tags[i]);
				spotTags.setCreatetime(new Date());
				_spotTagsRepository.save(spotTags);
			}
		}
		return "redirect:/spotplan/edit?&amp;orderId="+orderId+"&amp;datePlanId="+datePlanId+"&amp;cityName="+cityName;
	}

	/*
	 * 通过景点规划id查看景点规划详情
	 */
	@RequestMapping(value = "/showSpotDetail", method = RequestMethod.GET)
	public String showSpotDetail(@RequestParam("spotId") Integer spotId,HttpServletRequest request,Model model){
		SpotPlan spotPlan = _spotPlanRepository.findOne(spotId);
		model.addAttribute("spotPlan",spotPlan);
		return "/spotsplan/showSpotPlanDetail";
	}


	//计算时间规划中的导游人头费
	private BigDecimal caculSpotSalePrice(BigDecimal avaCommision, Integer count) {

		return Constant.decimalFormat(avaCommision.multiply(new BigDecimal(count)));
	}
}
