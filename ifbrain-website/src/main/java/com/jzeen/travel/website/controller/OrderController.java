package com.jzeen.travel.website.controller;

import com.jzeen.travel.data.entity.*;
import com.jzeen.travel.data.repository.*;
import com.jzeen.travel.service.ExchangeRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	StandardGuideOccupiedRepository _standardGuideOccupiedRepository;

	@Autowired
	ExchangeRateRepository _exchangeRateRepository;

	@Autowired
	private OrderRepository _orderRepository;

	@Autowired
	private DatePlanRepository _datePlanRepository;

	@Autowired
	private ExchangeRateService _exchangeRateService;

	@Autowired
	private AirlineRepository _airlineRepository;// 航空公司表(t_airline)

	// 交通JPA
	@Autowired
	private VehiclePlanRepository _vehiclePlanRepository;

	// 景点JPA
	@Autowired
	private SpotRepository _spotRepository;

	// 行程规划
	@Autowired
	private VorderDatePlanViewRepository _vorderDatePlanViewRepository;

	// 行程规划
	@Autowired
	private VcustomerReportViewRepository _vcustomerReportViewRepository;

	@Autowired
	private VdatePlanViewRepository _vdatePlanViewRepository;

	@Autowired
	private MarginPlanRepository _marginPlanRepository;

	@Autowired
	private SpotPlanRepository _spotPlanRepository;

	@Autowired
	private SpotTagsRepository _spotTagsRepository;

	@Autowired
	private SpotPlanTicketRepository _spotPlanTicketRepository;

	@Autowired
	private FilghtDetailsRepository _filghtDetailRepository;
	@Autowired
	private FilghtPlanRepository _filghtPlanRepository;
	

	/*
	 * 显示已结算订单详情
	 */
	@RequestMapping(value = "/{id}/plan2")
	public String step2(@PathVariable Integer id, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");

		
		//获取美元和人民币的兑换比率
		//ExchangeRate exchangerate = _exchangeRateRepository.getByExchangerate();
		
		
		BigDecimal all = new BigDecimal(0.0);
		//String orderId = request.getParameter("orderId");

		Order order = _orderRepository.findOne(id);

		model.addAttribute("order", order);
		
		
		BigDecimal insurancePlanPrice=new BigDecimal(0.0);
		BigDecimal marginplanPrice=new BigDecimal(0.0);
		
		// 获得保险规划
		List<InsurancePlan> insurancePlan = order.getInsurancePlan();
		for(int i=0;i<insurancePlan.size();i++){
		    insurancePlanPrice=insurancePlanPrice.add(insurancePlan.get(i).getSubTotalAmount());
		}
		model.addAttribute("insurancePlan", insurancePlan);
		
		
		

		// 获得保证金规划
		List<MarginPlan> marginplan = order.getMarginplan();
		
		for(int j=0;j<marginplan.size();j++){
		    marginplanPrice=marginplanPrice.add(marginplan.get(j).getMargintotalprice());
		}
		model.addAttribute("marginplan", marginplan);

		List<DatePlan> listDatePlan = _orderRepository.findbyOrderId(id);
		
		
		for(int j=0;j<listDatePlan.size();j++){
		  List<HotelPlan> hotelPlan = listDatePlan.get(j).getHotelPlan();
		  List<GuideActivityPlan> guideActivityPlans = listDatePlan.get(j).getGuideActivityPlans();
		  List<RentalPlan> rentalPlan = listDatePlan.get(j).getRentalPlan();
		  List<FilghtPlan> filghtPlan = listDatePlan.get(j).getFilghtPlan();
		  List<SpotPlan> spotPlan = listDatePlan.get(j).getSpotPlan();

		  
		  if(hotelPlan == null || hotelPlan.size() ==0){
		      listDatePlan.get(j).setHotelPlan(null);
          }
		  
		  if(guideActivityPlans == null || guideActivityPlans.size() ==0){
              listDatePlan.get(j).setGuideActivityPlans(null);
          }
		  if(rentalPlan == null || rentalPlan.size() ==0){
              listDatePlan.get(j).setRentalPlan(null);
          }
		  if(filghtPlan == null || filghtPlan.size() ==0){
		      listDatePlan.get(j).setFilghtPlan(null);
          }
		  if(spotPlan == null || spotPlan.size() ==0){
              listDatePlan.get(j).setSpotPlan(null);
          }
		  
		}
		
		
		
		List<DatePlan> listDatePlanA = new ArrayList<DatePlan>();
		for (int i = 0; i < listDatePlan.size(); i++) {
			DatePlan datePlan = listDatePlan.get(i);
			StringBuffer str = new StringBuffer();
			
			
			Date date = listDatePlan.get(i).getDate();
            
            if(date!=null){
            String datetime = date.toString();
			String replace = datetime.substring(0, 9).replace("0", "");
			String[] split = replace.split("-");
			str.append(split[0] + "月" + split[1] + "日");
			datePlan.setDateString(str.toString());
            }
            else{
                datePlan.setDateString(""); 
            }
			
			
			//获取当天的小计
			datePlan.setDayTotalAmount(datePlan.getSubTotal().multiply(order.getExchangerate()).setScale(2, BigDecimal.ROUND_HALF_UP));
			// 获得每一条的日期规划的小计,并计算总价格
			all = all.add(datePlan.getSubTotal());
			listDatePlanA.add(datePlan);
		}
		
		model.addAttribute("listDatePlan", listDatePlanA);

		

		
		all=all.add(insurancePlanPrice).add(marginplanPrice);
		all = all.multiply(order.getExchangerate());
		// 保存所有的日期规划的价格总和

		model.addAttribute("alltotalPrice", all.setScale(2, BigDecimal.ROUND_HALF_UP));

		List<DatePlan> datePlanList = new ArrayList<DatePlan>();
		for (int i = 0; i < listDatePlan.size(); i++) {
			DatePlan datePlan = listDatePlan.get(i);
			
			Date date = listDatePlan.get(i).getDate();
			
			if(date!=null){
			String datetime = date.toString();
			String replace =datetime.substring(0, 9).replace("0", "");
			StringBuffer str = new StringBuffer();
			String[] split = replace.split("-");
			str.append(split[0] + "月" + split[1] + "日");
			datePlan.setDateString(str.toString());
			}
			else{
			    datePlan.setDateString("");
			}
			
			
			
			datePlanList.add(datePlan);
		}
		model.addAttribute("datePlanList", datePlanList);
		return "/order/orderdetails";
	}

	/*
	 * 订单详情
	 */
	@RequestMapping(value = "/{id}/message")
	public String message(@PathVariable Integer id, Model model) {
		System.out.println("+++++message+++");
		Order order = _orderRepository.findOne(id);
		model.addAttribute("order", order);
		return "/order/planningmessage";
	}

	/*
	 * 已过期订单
	 */
	@RequestMapping(value = "/{id}/tomessage")
	public String tomessage(@PathVariable Integer id, Model model) {
		Order order = _orderRepository.findOne(id);
		model.addAttribute("order", order);
		return "/order/reapplymessage";
	}

	/*
	 * 提交反馈，订单状态更改为重新规划
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@RequestParam Integer id,
			@RequestParam Integer orderId, @RequestParam String feedback,
			HttpServletRequest request) {
		String result = "";
		/*
		 * String oId = request.getParameter("orderId"); Integer orderId =
		 * Integer.parseInt(oId);
		 */
		// 将订单状态改为“重新规划”
		System.out.println(id + "------------" + orderId + "------------"
				+ feedback);
		DatePlan datePlan = _datePlanRepository.findOne(id);
		List<DatePlan> datePlans = _datePlanRepository.findByEndDate(
				datePlan.getStartDate(), datePlan.getEndDate(), orderId);
		for (int i = 0; i < datePlans.size(); i++) {
			datePlans.get(i).setFeedback(feedback);
		}
		_datePlanRepository.save(datePlans);

		Order order = _orderRepository.findOne(orderId);
		order.setOrderStatus(6);
		_orderRepository.save(order);
		result = "反馈理由提交成功";
		return result;
	}

	/*
	 * 邀请码填写页面
	 */
	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String orderDetail(@PathVariable Integer id,
			HttpServletRequest request, Model model) {
		Order order = _orderRepository.findOne(id);
		model.addAttribute("order", order);
		return "/order/orderactive";
	}

	/*
	 * 支付失败页面
	 */
	@RequestMapping(value = "/alipayErr", method = RequestMethod.GET)
	public String alipayErr(HttpServletRequest request, Model model) {
		return "/order/alipayErr";
	}

	/**
	 * 
	 * 导游未完成订单
	 * 
	 * 
	 */

	@RequestMapping(value = "/{id}/guideOrder")
	public String stepGuideOrder(@PathVariable Integer id, Model model,
			HttpServletRequest request) {
		User userLogin = (User) WebUtils.getSessionAttribute(request, "user");
		String email = userLogin.getEmail();
		List<DatePlan> pan = new ArrayList<DatePlan>();
		Order order = _orderRepository.findOne(id);
		model.addAttribute("order", order);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String format = fmt.format(new Date());
		List<StandardGuideOccupied> getstandardGuideOccupied1 = new ArrayList<StandardGuideOccupied>();
		List<StandardGuideOccupied> getstandardGuideOccupied = _orderRepository
				.getstandardGuideOccupied(id, userLogin.getId());
		for (int i = 0; i < getstandardGuideOccupied.size(); i++) {
			Date occupiedDate = getstandardGuideOccupied.get(i)
					.getOccupiedDate();
			String format2 = fmt.format(occupiedDate);
			if (format2.equals(format)) {
				getstandardGuideOccupied1.add(getstandardGuideOccupied.get(i));
			}
		}
		int size = getstandardGuideOccupied.size();
		model.addAttribute("getstandardGuideOccupied",
				getstandardGuideOccupied1);
		return "/guideoutstandingorders/guideOrder";
	}

	@ResponseBody
	@RequestMapping(value = "/overTime", method = RequestMethod.POST)
	public String overTime(@RequestParam("day") Integer day,
			@RequestParam("OccupiedId") Integer OccupiedId, Model model,
			HttpServletRequest request) {
		String result = "";
		StandardGuideOccupied standardGuideOccupied = _standardGuideOccupiedRepository
				.findOne(OccupiedId);
		standardGuideOccupied.setOtTime(day);
		standardGuideOccupied.setOtStatus(true);
		_standardGuideOccupiedRepository.save(standardGuideOccupied);
		result = "加班成功";
		return result;
	}

	/**
	 * 导游已完成订单
	 */
	@RequestMapping(value = "/{id}/guideInOrder")
	public String stepGuideInOrder(@PathVariable Integer id, Model model,
			HttpServletRequest request) {
		User userLogin = (User) WebUtils.getSessionAttribute(request, "user");
		String email = userLogin.getEmail();
		List<DatePlan> pan = new ArrayList<DatePlan>();
		Order order = _orderRepository.findOne(id);
		model.addAttribute("order", order);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		String format = fmt.format(new Date());
		List<StandardGuideOccupied> getstandardGuideOccupied1 = new ArrayList<StandardGuideOccupied>();
		List<StandardGuideOccupied> getstandardGuideOccupied = _orderRepository
				.getstandardGuideOccupied(id, userLogin.getId());
		for (int i = 0; i < getstandardGuideOccupied.size(); i++) {
			Date occupiedDate = getstandardGuideOccupied.get(i)
					.getOccupiedDate();
			String format2 = fmt.format(occupiedDate);
			if (format2.equals(format)) {
				getstandardGuideOccupied1.add(getstandardGuideOccupied.get(i));
			}
		}
		int size = getstandardGuideOccupied.size();
		model.addAttribute("getstandardGuideOccupied",
				getstandardGuideOccupied1);
		return "/guideinstandingorders/guideOrder";
	}

	/**
	 * 根据飞机ID，查询飞机相关信息
	 */
/*	@RequestMapping(value = "/vehicleplan", method = RequestMethod.GET)
	public String vehicleplan(@RequestParam("id") int id, Model model) {
		VehiclePlan vehiclePlan = _vehiclePlanRepository.findOne(id);
		// System.out.println("代码值为："+vehiclePlan.getAirline());
		Airline airline = _airlineRepository.findByAirlineCode(vehiclePlan
				.getAirline());
		// System.out.println("航空公司英文名字："+airline.getAirlineEnglishName());
		model.addAttribute("airlineEnglishName",
				airline.getAirlineEnglishName());
		model.addAttribute("vehicleplan", vehiclePlan);
		return "/order/vehicleplan";
	}*/

	/**
	 * 根据景点id，查询景点相关信息
	 */
	@RequestMapping(value = "/spot", method = RequestMethod.GET)
	public String spot(@RequestParam("id") int id, Model model) {
		Spot spot = _spotRepository.findOne(id);
		model.addAttribute("spot", spot);
		model.addAttribute("cityname", spot.getCityid().getCityName());
		model.addAttribute("suppliername", spot.getSupplierid().getCnName());

		System.out.println("供应商名称为" + spot.getSupplierid().getCnName());

		return "/order/spot";
	}

	/**
	 * 根据订单id，查询订单详情相关信息
	 * @throws Exception 
	 */
	@RequestMapping(value = "/orderdetail", method = RequestMethod.GET)
	public String orderdetail(Model model, HttpServletRequest request) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");

		
		//获取美元和人民币的兑换比率
		ExchangeRate exchangerate = _exchangeRateRepository.getByExchangerate();
		
		
		BigDecimal all = new BigDecimal(0.0);
		String orderId = request.getParameter("orderId");

		Order order = _orderRepository.findOne(Integer.parseInt(orderId));

		model.addAttribute("order", order);
		
		
		BigDecimal insurancePlanPrice=new BigDecimal(0.0);
		BigDecimal marginplanPrice=new BigDecimal(0.0);
		
		// 获得保险规划
		List<InsurancePlan> insurancePlan = order.getInsurancePlan();
		for(int i=0;i<insurancePlan.size();i++){
		    insurancePlanPrice=insurancePlanPrice.add(insurancePlan.get(i).getSubTotalAmount());
		}
		model.addAttribute("insurancePlan", insurancePlan);
		
		
		

		// 获得保证金规划
		List<MarginPlan> marginplan = order.getMarginplan();
		
		for(int j=0;j<marginplan.size();j++){
		    marginplanPrice=marginplanPrice.add(marginplan.get(j).getMargintotalprice());
		}
		model.addAttribute("marginplan", marginplan);

		List<DatePlan> listDatePlan = _orderRepository.findbyOrderIdAndOrderStatus(Integer.parseInt(orderId));
		
		
		for(int j=0;j<listDatePlan.size();j++){
		  List<HotelPlan> hotelPlan = listDatePlan.get(j).getHotelPlan();
		  List<GuideActivityPlan> guideActivityPlans = listDatePlan.get(j).getGuideActivityPlans();
		  List<RentalPlan> rentalPlan = listDatePlan.get(j).getRentalPlan();
		  List<FilghtPlan> filghtPlan = listDatePlan.get(j).getFilghtPlan();
		  List<SpotPlan> spotPlan = listDatePlan.get(j).getSpotPlan();

		  
		  if(hotelPlan == null || hotelPlan.size() ==0){
		      listDatePlan.get(j).setHotelPlan(null);
          }
		  
		  if(guideActivityPlans == null || guideActivityPlans.size() ==0){
              listDatePlan.get(j).setGuideActivityPlans(null);
          }
		  if(rentalPlan == null || rentalPlan.size() ==0){
              listDatePlan.get(j).setRentalPlan(null);
          }
		  if(filghtPlan == null || filghtPlan.size() ==0){
		      listDatePlan.get(j).setFilghtPlan(null);
          }
		  if(spotPlan == null || spotPlan.size() ==0){
              listDatePlan.get(j).setSpotPlan(null);
          }
		  
		}
		
		
		
		List<DatePlan> listDatePlanA = new ArrayList<DatePlan>();
		for (int i = 0; i < listDatePlan.size(); i++) {
			DatePlan datePlan = listDatePlan.get(i);
			StringBuffer str = new StringBuffer();
			
			
			Date date = listDatePlan.get(i).getDate();
            
            if(date!=null){
            String datetime = date.toString();
			String replace = datetime.substring(0, 9).replace("0", "");
			String[] split = replace.split("-");
			str.append(split[0] + "月" + split[1] + "日");
			datePlan.setDateString(str.toString());
            }
            else{
                datePlan.setDateString(""); 
            }
			
			
			//获取当天的小计
			datePlan.setDayTotalAmount(datePlan.getSubTotal().multiply(exchangerate.getExchangerate()).setScale(2, BigDecimal.ROUND_HALF_UP));
			// 获得每一条的日期规划的小计,并计算总价格
			all = all.add(datePlan.getSubTotal());
			listDatePlanA.add(datePlan);
		}
		
		model.addAttribute("listDatePlan", listDatePlanA);

		

		
		all=all.add(insurancePlanPrice).add(marginplanPrice);
		all = all.multiply(exchangerate.getExchangerate());
		// 保存所有的日期规划的价格总和

		model.addAttribute("alltotalPrice", all.setScale(2, BigDecimal.ROUND_HALF_UP));

		List<DatePlan> datePlanList = new ArrayList<DatePlan>();
		for (int i = 0; i < listDatePlan.size(); i++) {
			DatePlan datePlan = listDatePlan.get(i);
			
			Date date = listDatePlan.get(i).getDate();
			
			if(date!=null){
			String datetime = date.toString();
			String replace =datetime.substring(0, 9).replace("0", "");
			StringBuffer str = new StringBuffer();
			String[] split = replace.split("-");
			str.append(split[0] + "月" + split[1] + "日");
			datePlan.setDateString(str.toString());
			}
			else{
			    datePlan.setDateString("");
			}
			
			
			
			datePlanList.add(datePlan);
		}
		model.addAttribute("datePlanList", datePlanList);
		return "/order/orderdetail";
	}

	/**
	 * 为日期规划添加反馈信息
	 */
	@ResponseBody
	@RequestMapping(value = "/addFeedBack", method = RequestMethod.POST)
	public String dataPlanFeedBack(Model model, HttpServletRequest request) {
	    
	    String orderId = request.getParameter("orderId");
	    
	    Order order = _orderRepository.findOne(Integer.parseInt(orderId));
	    order.setOrderStatus(6);
	    _orderRepository.save(order);
	    
		String result = "";
		String id = request.getParameter("id");
		String content = request.getParameter("content");

		DatePlan datePlan = _datePlanRepository.findById(Integer.parseInt(id));
		datePlan.setFeedback(content);

		_datePlanRepository.save(datePlan);
		result = "反馈成功!";

		return result;
	}

	/**
	 * 根据景点的ID，查询景点的详情
	 * 
	 * @return 景点详情页面
	 */
	@RequestMapping(value = "/spotdetail/{id}", method = RequestMethod.GET)
	public String spotdetail(@PathVariable Integer id, Model model,
			HttpServletRequest request) {
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		System.out.println(orderId);
		model.addAttribute("orderId", orderId);
		SpotPlan spotPlan = _spotPlanRepository.findOne(id);
		model.addAttribute("spotPlan", spotPlan);
		Spot spot = _spotRepository.findOne(spotPlan.getSpot().getId());
		model.addAttribute("spot", spot);
		List<SpotTags> spotTags = _spotTagsRepository.findBySpot(spot);
		model.addAttribute("spotTags", spotTags);

		List<SpotPlanTicket> SpotPlanTicket = _spotPlanTicketRepository
				.findBySpotPlan(spotPlan);
		model.addAttribute("SpotPlanTicket", SpotPlanTicket);
		return "/order/spotdetail";
	}

	@RequestMapping(value = "/flightDetail/{id}", method = RequestMethod.GET)
	public String flight(@PathVariable Integer id, Model model,
			HttpServletRequest request) {
		FilghtPlan flightPlan=_filghtPlanRepository.findOne(id);
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		model.addAttribute("orderId", orderId);
		
		List<FlightDetails> filghtDetail = _filghtDetailRepository.findById(id);
		model.addAttribute("flightPlan", flightPlan);
		model.addAttribute("flightDetail", filghtDetail);
		return "/order/flightDetail";
	}
	
}
