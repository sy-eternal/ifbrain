package com.jzeen.travel.admin.controller;

import com.jzeen.travel.core.util.Constant;
import com.jzeen.travel.data.entity.*;
import com.jzeen.travel.data.repository.*;
import com.jzeen.travel.service.ExchangeRateService;
import com.jzeen.travel.service.OrderService;
import com.mysema.query.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderRepository _orderRepository;

	@Autowired
	private DatePlanRepository _datePlanRepository;

	@Autowired
	private OrderService _orderService;

	@Autowired
	private ExchangeRateService _exchangeRateService;

	@Autowired
	SpotPlanRepository _spotPlanRepository;

	@Autowired
	SpotPlanTicketRepository _spotPlanTicketRepository;

	@Autowired
	FilghtPlanRepository _filghtPlanRepository;

	@Autowired
	HotelPlanRepository _hotelPlanRepository;

	@Autowired
	HotelPlanRoomRepository _hotelPlanRoomRepository;

	@Autowired
	GuideActivityPlanRepository _guideActivityPlanRepository;

	@Autowired
	GuideTypePlanRepository _guideTypePlanRepository;

	@Autowired
	RentalPlanRepository _rentalPlanRepository;

	@Autowired
	MarginRepository _mMarginRepository;

	@Autowired
	private CityRepository _cityRepository;
	@Autowired
	private MarginPlanRepository _MarginPlanRepository;
	@RequestMapping(value = "/toplan", method = RequestMethod.GET)
	public String plan(Model model, HttpServletRequest request) {
		WebUtils.setSessionAttribute(request, "businessType", "toplan");

		// 获取状态为“已结算”的订单
		List<Order> orders = _orderRepository.findByOrderStatus(3);
		model.addAttribute("orders", orders);

		return "/order/toplan";
	}

	@RequestMapping(value = "/replan", method = RequestMethod.GET)
	public String replan(Model model, HttpServletRequest request) {
		//        WebUtils.setSessionAttribute(request, "businessType", "replan");
		WebUtils.setSessionAttribute(request, "businessType", "replan");

		// 获取状态为“已规划”的订单
		List<Order> orders = _orderRepository.findByOrderStatus(Integer.valueOf(Constant.ORDER_TYPE_REPLAN));
		model.addAttribute("orders", orders);

		return "/order/replan";
	}



	@RequestMapping(value = "/{id}/plan", params = "_step1")
	public String step1(@PathVariable Integer id, Model model,HttpServletRequest request) throws ParseException {
		Order order = _orderRepository.findOne(id);
		model.addAttribute("order", order);


		/* List<DatePlan> feedbackDatePlans = _orderService.getFeedBackDatePlan(order.getId());
        model.addAttribute("feedbackDatePlans", feedbackDatePlans);*/

		// 城市间交通
		model.addAttribute("citysTrafficStr", _orderService.getCitysTrafficByOrderId(order.getId()));
		// 租车喜好
		model.addAttribute("carLikeType", _orderService.getCarLikeByOrderId(order.getId()));
		// 航班标准
		/*   model.addAttribute("airPlaneType", _orderService.getAirPlaneTypeByOrderId(order.getId()));*/

		//年龄
		model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));

		return "/order/dateplan";
	}

    
    /**
     * 已规划订单详细信息
     * @param id
     * @param startDates
     * @param numcount
     * @param btn2
     * @param endDates
     * @param dateplandates
     * @param model
     * @param request
     * @return
     * @throws ParseException
     */
    
    @RequestMapping(value = "/{id}/planedInfo")
    public String planedInfo(@PathVariable Integer id, Model model) {
        Order order = _orderRepository.findOne(id);
          model.addAttribute("order", order);

        // 城市间交通
        model.addAttribute("citysTrafficStr", _orderService.getCitysTrafficByOrderId(order.getId()));
        // 租车喜好
        model.addAttribute("carLikeType", _orderService.getCarLikeByOrderId(order.getId()));
        // 航班标准
     /*   model.addAttribute("airPlaneType", _orderService.getAirPlaneTypeByOrderId(order.getId()));*/

        //年龄
       model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));

        return "/order/planedInfo";
    }
    
    
    /**
     * 已规划订单详情页
     * @param id
     * @param startDates
     * @param numcount
     * @param btn2
     * @param endDates
     * @param dateplandates
     * @param model
     * @param request
     * @return
     * @throws ParseException
     */

    @RequestMapping(value = "/{id}/plan", params = "_planeddetail")
    public String planeddetail(@PathVariable Integer id, String startDates,Integer numcount,String btn2, String endDates,String dateplandates, Model model,HttpServletRequest request) throws ParseException {
        Order order = _orderRepository.findOne(id);

        BigDecimal exchangerate = _exchangeRateService.getCurrentExchangeRate();
        
        BigDecimal marginPrice=new BigDecimal(0.0);
        //获得保证金规划价格
        List<MarginPlan> marginplan = order.getMarginplan();
        for(int j=0;j<marginplan.size();j++){
            marginPrice=marginPrice.add(marginplan.get(j).getMargintotalprice());
            
        }
        model.addAttribute("marginPrice", marginPrice);
        
        //获得保险规划
        if(order.getAmountSetailStatus()==0||order.getAmountSetailStatus().equals(0)){
            String detailAmouut="显示明细金额";
            model.addAttribute("detailAmouut", detailAmouut);
        }else if(order.getAmountSetailStatus()==1||order.getAmountSetailStatus().equals(1)){
            String detailAmouut="不显示明细金额";
            model.addAttribute("detailAmouut", detailAmouut);
        }
        BigDecimal all=new BigDecimal(0.0);
        BigDecimal alls=new BigDecimal(0.0);
        List<InsurancePlan> insurancePlan = order.getInsurancePlan();
        for(int i=0;i<insurancePlan.size();i++){
            alls=alls.add(insurancePlan.get(i).getSubTotalAmount());
        }
        model.addAttribute("allTotal", alls);
        
        all=all.add(alls).add(marginPrice);

        order.setOrderAmount(all);
        model.addAttribute("all", all);
        model.addAttribute("totalprice", all.multiply(exchangerate));
        _orderRepository.save(order);
        
        model.addAttribute("order", order);
        List <DatePlan> datePlan=new ArrayList<DatePlan>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //起止日期
        btn2=request.getParameter("amp;btn2");
        List <DatePlan> dateplans =_datePlanRepository.findByOrderId(order.getId());
        //List<DatePlan> listDatePlanA=new ArrayList<DatePlan>();
        SimpleDateFormat sdf1 =   new SimpleDateFormat( "MM-dd" );
        BigDecimal flighttotalAmount=new BigDecimal(0.0);
        BigDecimal guidetotalAmount=new BigDecimal(0.0);
        BigDecimal rentaltotalAmount=new BigDecimal(0.0);
        BigDecimal spottotalAmount=new BigDecimal(0.0);
        BigDecimal hoteltalAmount = new BigDecimal(0.0);
        if(dateplans.size()>0){
            
                for(int i=0;i<dateplans.size();i++){
                    DatePlan datePlan1 = _datePlanRepository.findOne(dateplans.get(i).getId());
                    //计算每一条日期规划的相关的，飞机规划，酒店规划，景点规划，租车规划的,导游规划的价格
                    //飞机
                    List<FilghtPlan> filghtPlan = datePlan1.getFilghtPlan();  
                   
                    if (filghtPlan != null) {
                        for (FilghtPlan filghtPlans : filghtPlan) {
                            flighttotalAmount = flighttotalAmount.add(filghtPlans.getSubtotalAmount() == null ? BigDecimal.ZERO : filghtPlans.getSubtotalAmount());
                        }
                    }
                    //导游
                    List<GuideActivityPlan> guideActivityPlansList = datePlan1.getGuideActivityPlans();
                    if (guideActivityPlansList != null) {
                        for (GuideActivityPlan guideActivityPlan : guideActivityPlansList) {
                            guidetotalAmount = guidetotalAmount.add(guideActivityPlan.getSubTotalAmount() == null ? BigDecimal.ZERO : guideActivityPlan.getSubTotalAmount());
                        }
                    }
                    
                    //租车
                    List<RentalPlan> rentalPlanList = datePlan1.getRentalPlan();
                    if (rentalPlanList != null) {
                        for (RentalPlan rentalPlan : rentalPlanList) {
                            rentaltotalAmount = rentaltotalAmount.add(rentalPlan.getSubtotalamount() == null ? BigDecimal.ZERO : rentalPlan.getSubtotalamount());
                        }
                    }
                    //景点
                    // modify by limin.tony@x2our.com
                    // 如果价格数据为空，则小计增加0
                    List<SpotPlan> spotPlanList = datePlan1.getSpotPlan();
                    if (spotPlanList != null) {
                        for (SpotPlan spotPlan : spotPlanList) {
                            spottotalAmount = spottotalAmount.add(spotPlan.getSubtotalAmount() == null ? BigDecimal.ZERO : spotPlan.getSubtotalAmount());
                        }
                    }
                    //酒店
                    List<HotelPlan> hotelPlanList = datePlan1.getHotelPlan();
            
                    if (hotelPlanList != null) {
                        for (HotelPlan hotelPlan : hotelPlanList) {
                            hoteltalAmount = hoteltalAmount.add(hotelPlan.getSubtotalAmount() == null ? BigDecimal.ZERO : hotelPlan.getSubtotalAmount());
                        }
                    }
                    all=all.add(flighttotalAmount).add(guidetotalAmount).add(rentaltotalAmount).add(spottotalAmount).add(hoteltalAmount);
                    datePlan1.setDayTotalAmount(flighttotalAmount.add(guidetotalAmount).add(rentaltotalAmount).add(spottotalAmount).add(hoteltalAmount));
                    datePlan1.setSubTotal(flighttotalAmount.add(guidetotalAmount).add(rentaltotalAmount).add(spottotalAmount).add(hoteltalAmount));
                    _datePlanRepository.save(datePlan1);
                    datePlan1.setFlighttotalAmount(flighttotalAmount);
                    datePlan1.setGuidetotalAmount(guidetotalAmount);
                    datePlan1.setRentaltotalAmount(rentaltotalAmount);
                    datePlan1.setSpottotalAmount(spottotalAmount);
                    datePlan1.setHoteltalAmount(hoteltalAmount);
                    flighttotalAmount=new  BigDecimal(0.0);
                    guidetotalAmount=new BigDecimal(0.0);
                    spottotalAmount=new BigDecimal(0.0);
                    rentaltotalAmount=new BigDecimal(0.0);
                    hoteltalAmount=new BigDecimal(0.0);
                    datePlan.add(datePlan1);
                }
                
              //计算所有的总价格
                List<InsurancePlan> insurancePlans = order.getInsurancePlan();
                for(int i=0;i<insurancePlans.size();i++){
                    alls=alls.add(insurancePlans.get(i).getSubTotalAmount());
                }
                model.addAttribute("allTotal", alls);
                all=all.add(alls).add(marginPrice);
                order.setOrderAmount(all);
                model.addAttribute("all", all);
                model.addAttribute("datePlan", datePlan); 
                String  totalprice=    new java.text.DecimalFormat("#.00").format(all.multiply(exchangerate));
                model.addAttribute("totalprice", totalprice);
                _orderRepository.save(order);
            return "/order/planeddetail";
        }
        else {
        all=all.add(alls).add(marginPrice);
      order.setOrderAmount(all);
      model.addAttribute("all", all);
      model.addAttribute("datePlan", datePlan); //44444
      model.addAttribute("totalprice", all.multiply(exchangerate));
      _orderRepository.save(order);
        }
        return "/order/planeddetail";
    }
    
    @RequestMapping(value = "/{id}/settledInfo")
    public String settledInfo(@PathVariable Integer id, Model model) {
        Order order = _orderRepository.findOne(id);
          model.addAttribute("order", order);

        // 城市间交通
        model.addAttribute("citysTrafficStr", _orderService.getCitysTrafficByOrderId(order.getId()));
        // 租车喜好
        model.addAttribute("carLikeType", _orderService.getCarLikeByOrderId(order.getId()));
        // 航班标准
     /*   model.addAttribute("airPlaneType", _orderService.getAirPlaneTypeByOrderId(order.getId()));*/

        //年龄
       model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));

        return "/order/settledInfo";
    }
    

	/*
	 * 已结算订单详情
	 */
	@RequestMapping(value = "/{id}/plan", params = "_settleddetail")
	public String settledorderdetail(@PathVariable Integer id,Model model,HttpServletRequest request){
		 /*Integer id = Integer.parseInt(request.getParameter("id"));*/
        Order order = _orderRepository.findOne(id);    
        BigDecimal exchangerate = _exchangeRateService.getCurrentExchangeRate();
        
        BigDecimal marginPrice=new BigDecimal(0.0);
        //获得保证金规划价格
        List<MarginPlan> marginplan = order.getMarginplan();
        for(int j=0;j<marginplan.size();j++){
            marginPrice=marginPrice.add(marginplan.get(j).getMargintotalprice());   
        }
        model.addAttribute("marginPrice", marginPrice);
        
        //获得保险规划
        if(order.getAmountSetailStatus()==0||order.getAmountSetailStatus().equals(0)){
            String detailAmouut="显示明细金额";
            model.addAttribute("detailAmouut", detailAmouut);
        }else if(order.getAmountSetailStatus()==1||order.getAmountSetailStatus().equals(1)){
            String detailAmouut="不显示明细金额";
            model.addAttribute("detailAmouut", detailAmouut);
        }
        BigDecimal all=new BigDecimal(0.0);
        BigDecimal alls=new BigDecimal(0.0);
        List<InsurancePlan> insurancePlan = order.getInsurancePlan();
        for(int i=0;i<insurancePlan.size();i++){
            alls=alls.add(insurancePlan.get(i).getSubTotalAmount());
        }
        model.addAttribute("allTotal", alls);
        
        all=all.add(alls).add(marginPrice);

        order.setOrderAmount(all);
        model.addAttribute("all", all);
        model.addAttribute("totalprice", all.multiply(exchangerate));
        _orderRepository.save(order);
        
        model.addAttribute("order", order);
        List <DatePlan> datePlan=new ArrayList<DatePlan>();
      
        List <DatePlan> dateplans =_datePlanRepository.findByOrderId(order.getId());
        //List<DatePlan> listDatePlanA=new ArrayList<DatePlan>();
        BigDecimal flighttotalAmount=new BigDecimal(0.0);
        BigDecimal guidetotalAmount=new BigDecimal(0.0);
        BigDecimal rentaltotalAmount=new BigDecimal(0.0);
        BigDecimal spottotalAmount=new BigDecimal(0.0);
        BigDecimal hoteltalAmount = new BigDecimal(0.0);
        if(dateplans.size()>0){
            
                for(int i=0;i<dateplans.size();i++){
                    DatePlan datePlan1 = _datePlanRepository.findOne(dateplans.get(i).getId());
                    //计算每一条日期规划的相关的，飞机规划，酒店规划，景点规划，租车规划的,导游规划的价格
                    //飞机
                    List<FilghtPlan> filghtPlan = datePlan1.getFilghtPlan();  
                   
                    if (filghtPlan != null) {
                        for (FilghtPlan filghtPlans : filghtPlan) {
                            flighttotalAmount = flighttotalAmount.add(filghtPlans.getSubtotalAmount() == null ? BigDecimal.ZERO : filghtPlans.getSubtotalAmount());
                        }
                    }
                    //导游
                    List<GuideActivityPlan> guideActivityPlansList = datePlan1.getGuideActivityPlans();
                    if (guideActivityPlansList != null) {
                        for (GuideActivityPlan guideActivityPlan : guideActivityPlansList) {
                            guidetotalAmount = guidetotalAmount.add(guideActivityPlan.getSubTotalAmount() == null ? BigDecimal.ZERO : guideActivityPlan.getSubTotalAmount());
                        }
                    }
                    
                    //租车
                    List<RentalPlan> rentalPlanList = datePlan1.getRentalPlan();
                    if (rentalPlanList != null) {
                        for (RentalPlan rentalPlan : rentalPlanList) {
                            rentaltotalAmount = rentaltotalAmount.add(rentalPlan.getSubtotalamount() == null ? BigDecimal.ZERO : rentalPlan.getSubtotalamount());
                        }
                    }
                    //景点
                    // modify by limin.tony@x2our.com
                    // 如果价格数据为空，则小计增加0
                    List<SpotPlan> spotPlanList = datePlan1.getSpotPlan();
                    if (spotPlanList != null) {
                        for (SpotPlan spotPlan : spotPlanList) {
                            spottotalAmount = spottotalAmount.add(spotPlan.getSubtotalAmount() == null ? BigDecimal.ZERO : spotPlan.getSubtotalAmount());
                        }
                    }
                    //酒店
                    List<HotelPlan> hotelPlanList = datePlan1.getHotelPlan();
            
                    if (hotelPlanList != null) {
                        for (HotelPlan hotelPlan : hotelPlanList) {
                            hoteltalAmount = hoteltalAmount.add(hotelPlan.getSubtotalAmount() == null ? BigDecimal.ZERO : hotelPlan.getSubtotalAmount());
                        }
                    }
                    all=all.add(flighttotalAmount).add(guidetotalAmount).add(rentaltotalAmount).add(spottotalAmount).add(hoteltalAmount);
                    datePlan1.setDayTotalAmount(flighttotalAmount.add(guidetotalAmount).add(rentaltotalAmount).add(spottotalAmount).add(hoteltalAmount));
                    datePlan1.setSubTotal(flighttotalAmount.add(guidetotalAmount).add(rentaltotalAmount).add(spottotalAmount).add(hoteltalAmount));
                    _datePlanRepository.save(datePlan1);
                    datePlan1.setFlighttotalAmount(flighttotalAmount);
                    datePlan1.setGuidetotalAmount(guidetotalAmount);
                    datePlan1.setRentaltotalAmount(rentaltotalAmount);
                    datePlan1.setSpottotalAmount(spottotalAmount);
                    datePlan1.setHoteltalAmount(hoteltalAmount);
                    flighttotalAmount=new  BigDecimal(0.0);
                    guidetotalAmount=new BigDecimal(0.0);
                    spottotalAmount=new BigDecimal(0.0);
                    rentaltotalAmount=new BigDecimal(0.0);
                    hoteltalAmount=new BigDecimal(0.0);
                    datePlan.add(datePlan1);
                }
                
              //计算所有的总价格
                List<InsurancePlan> insurancePlans = order.getInsurancePlan();
                for(int i=0;i<insurancePlans.size();i++){
                    alls=alls.add(insurancePlans.get(i).getSubTotalAmount());
                }
                model.addAttribute("allTotal", alls);
                all=all.add(alls).add(marginPrice);
                order.setOrderAmount(all);
                model.addAttribute("all", all);
                model.addAttribute("datePlan", datePlan); 
                String  totalprice=    new java.text.DecimalFormat("#.00").format(all.multiply(exchangerate));
                model.addAttribute("totalprice", totalprice);
                _orderRepository.save(order);
            return "/order/settledorderdetail";
        }
        else {
        all=all.add(alls).add(marginPrice);
      order.setOrderAmount(all);
      model.addAttribute("all", all);
      model.addAttribute("datePlan", datePlan); //44444
      model.addAttribute("totalprice", all.multiply(exchangerate));
      _orderRepository.save(order);
        }
        return "/order/settledorderdetail";
    
	}
    
    
	/*
	 * 日期规划
	 */
	@RequestMapping(value = "/{id}/plan", params = "_tripplan")
	public String tripplan(@PathVariable Integer id, String startDates,Integer numcount,String btn2, String endDates,String dateplandates, Model model,HttpServletRequest request) throws ParseException {
		Order order = _orderRepository.findOne(id);

		BigDecimal exchangerate = _exchangeRateService.getCurrentExchangeRate();

		BigDecimal marginPrice=new BigDecimal(0.0);
		BigDecimal insurancePrice=new BigDecimal(0.0);
		//获得保证金规划价格
		List<MarginPlan> marginplan = order.getMarginplan();
		for(int j=0;j<marginplan.size();j++){
			marginPrice=marginPrice.add(marginplan.get(j).getMargintotalprice());

		}
		model.addAttribute("marginPrice", marginPrice);
		/*
		 * 获得保险规划价格
		 */
		List<InsurancePlan> insurancePlans = order.getInsurancePlan();
		for(int i=0;i<insurancePlans.size();i++){
			insurancePrice=insurancePrice.add(insurancePlans.get(i).getSubTotalAmount());
		}
		model.addAttribute("insurancePrice",insurancePrice);

		//获得保险规划
		if(order.getAmountSetailStatus()==0||order.getAmountSetailStatus().equals(0)){
			String detailAmouut="显示明细金额";
			model.addAttribute("detailAmouut", detailAmouut);
		}else if(order.getAmountSetailStatus()==1||order.getAmountSetailStatus().equals(1)){
			String detailAmouut="不显示明细金额";
			model.addAttribute("detailAmouut", detailAmouut);
		}

		BigDecimal all=new BigDecimal(0.0);
		BigDecimal alls=new BigDecimal(0.0);/*
        List<InsurancePlan> insurancePlan = order.getInsurancePlan();
        for(int i=0;i<insurancePlan.size();i++){
            alls=alls.add(insurancePlan.get(i).getSubTotalAmount());
        }*/
		model.addAttribute("allTotal", insurancePrice);
		all=all.add(insurancePrice);
		all=all.add(marginPrice);

		order.setOrderAmount(all);
		model.addAttribute("all", all);
		model.addAttribute("totalprice", all.multiply(exchangerate));
		_orderRepository.save(order);

		model.addAttribute("order", order);
		List <DatePlan> datePlan=new ArrayList<DatePlan>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//起止日期
		btn2=request.getParameter("amp;btn2");
		List <DatePlan> dateplans =_datePlanRepository.findByOrderId(order.getId());
		//List<DatePlan> listDatePlanA=new ArrayList<DatePlan>();
		SimpleDateFormat sdf1 =   new SimpleDateFormat( "MM-dd" );
		BigDecimal flighttotalAmount=new BigDecimal(0.0);
		BigDecimal guidetotalAmount=new BigDecimal(0.0);
		BigDecimal rentaltotalAmount=new BigDecimal(0.0);
		BigDecimal spottotalAmount=new BigDecimal(0.0);
		BigDecimal hoteltalAmount = new BigDecimal(0.0);
		//        BigDecimal all=new BigDecimal(0.0);
		if(dateplans.size()>0){
			if(btn2=="提交"||btn2.equals("提交")){
				_datePlanRepository.deleteByOrderId(order.getId());
				startDates=dateFormat.format(dateFormat.parse(request.getParameter("amp;startDates")));
				endDates = dateFormat.format(dateFormat.parse(request.getParameter("amp;endDates")));
				Date  startDate= dateFormat.parse(startDates);
				Date  endDate =dateFormat.parse(endDates);  
				long day = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
				if (day >= 0) {
					for (long i = 0; i <= day; i++) {
						Date date = new Date();
						SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
						date.setTime(startDate.getTime() + 24 * 60 * 60 * 1000 * i);
						String s1 = sdf.format(date); //2015-02-09 format()才是格式化
						//新增数据
						DatePlan datePlans = new DatePlan();
						datePlans.setStartDate(startDate);
						datePlans.setEndDate(endDate);
						datePlans.setOrder(order);
						datePlans.setCreateTime(new Date());
						datePlans.setDate(sdf.parse(s1));
						datePlan.add(datePlans);
						_datePlanRepository.save(datePlans);
						model.addAttribute("datePlan", datePlan);

					}

					//计算每个规划的总价
				}
				//                BigDecimal alls=new BigDecimal(0.0);
				/*model.addAttribute("allTotal", insurancePrice);

				all=all.add(marginPrice);
				all=all.add(insurancePrice);
				//                BigDecimal exchangerate=new BigDecimal(0.0);
				//                exchangerate=order.getExchangerate();
*/				order.setOrderAmount(all);
				model.addAttribute("all", all);
				model.addAttribute("datePlan", datePlan); //44444
				model.addAttribute("totalprice", all.multiply(exchangerate));
				_orderRepository.save(order);
			}else  if(btn2=="确定"||btn2.equals("确定")){
				_datePlanRepository.deleteByOrderId(order.getId());
				numcount =Integer.parseInt(request.getParameter("amp;numcount"));
				if (numcount >= 0) {
					for (long i = 1; i <= numcount; i++) {
						DatePlan datePlans = new DatePlan();
						datePlans.setOrder(order);
						datePlans.setCreateTime(new Date());
						datePlans.setOrdinatedday("day"+i);
						/*  if(btn2=="111"||btn2.equals("111")){
                                datePlans.setDate(dateplandate);
                            }*/
						datePlan.add(datePlans);
						_datePlanRepository.save(datePlans);
						model.addAttribute("datePlan", datePlan);
					}
				}/*
				all=all.add(insurancePrice);
				model.addAttribute("allTotal", insurancePrice);

				all=all.add(marginPrice);*/
				order.setOrderAmount(all);
				model.addAttribute("all", all);
				model.addAttribute("datePlan", datePlan); //44444
				model.addAttribute("totalprice", all.multiply(exchangerate));
				_orderRepository.save(order);
			}else{
				for(int i=0;i<dateplans.size();i++){
					DatePlan datePlan1 = _datePlanRepository.findOne(dateplans.get(i).getId());
					//计算每一条日期规划的相关的，飞机规划，酒店规划，景点规划，租车规划的,导游规划的价格
					//飞机
					List<FilghtPlan> filghtPlan = datePlan1.getFilghtPlan();  
					if (filghtPlan != null) {
						for (FilghtPlan filghtPlans : filghtPlan) {
							flighttotalAmount = flighttotalAmount.add(filghtPlans.getSubtotalAmount() == null ? BigDecimal.ZERO : filghtPlans.getSubtotalAmount());
						}
					}
					//导游
					List<GuideActivityPlan> guideActivityPlansList = datePlan1.getGuideActivityPlans();
					if (guideActivityPlansList != null) {
						for (GuideActivityPlan guideActivityPlan : guideActivityPlansList) {
							guidetotalAmount = guidetotalAmount.add(guideActivityPlan.getSubTotalAmount() == null ? BigDecimal.ZERO : guideActivityPlan.getSubTotalAmount());
						}
					}

					//租车
					List<RentalPlan> rentalPlanList = datePlan1.getRentalPlan();
					if (rentalPlanList != null) {
						for (RentalPlan rentalPlan : rentalPlanList) {
							rentaltotalAmount = rentaltotalAmount.add(rentalPlan.getSubtotalamount() == null ? BigDecimal.ZERO : rentalPlan.getSubtotalamount());
						}
					}
					//景点
					// modify by limin.tony@x2our.com
					// 如果价格数据为空，则小计增加0
					List<SpotPlan> spotPlanList = datePlan1.getSpotPlan();
					if (spotPlanList != null) {
						for (SpotPlan spotPlan : spotPlanList) {
							spottotalAmount = spottotalAmount.add(spotPlan.getSubtotalAmount() == null ? BigDecimal.ZERO : spotPlan.getSubtotalAmount());
						}
					}
					//酒店
					List<HotelPlan> hotelPlanList = datePlan1.getHotelPlan();
					/* for (int j = 0; j < hotelPlan.size(); j++) {
    					hoteltalAmount = hoteltalAmount.add(hotelPlan.get(j).getSubtotalAmount());
    				}*/
					if (hotelPlanList != null) {
						for (HotelPlan hotelPlan : hotelPlanList) {
							hoteltalAmount = hoteltalAmount.add(hotelPlan.getSubtotalAmount() == null ? BigDecimal.ZERO : hotelPlan.getSubtotalAmount());
						}
					}
					all=all.add(flighttotalAmount).add(guidetotalAmount).add(rentaltotalAmount).add(spottotalAmount).add(hoteltalAmount);
					datePlan1.setDayTotalAmount(flighttotalAmount.add(guidetotalAmount).add(rentaltotalAmount).add(spottotalAmount).add(hoteltalAmount));
					datePlan1.setSubTotal(flighttotalAmount.add(guidetotalAmount).add(rentaltotalAmount).add(spottotalAmount).add(hoteltalAmount));
					_datePlanRepository.save(datePlan1);
					datePlan1.setFlighttotalAmount(flighttotalAmount);
					datePlan1.setGuidetotalAmount(guidetotalAmount);
					datePlan1.setRentaltotalAmount(rentaltotalAmount);
					datePlan1.setSpottotalAmount(spottotalAmount);
					datePlan1.setHoteltalAmount(hoteltalAmount);
					flighttotalAmount=new  BigDecimal(0.0);
					guidetotalAmount=new BigDecimal(0.0);
					spottotalAmount=new BigDecimal(0.0);
					rentaltotalAmount=new BigDecimal(0.0);
					hoteltalAmount=new BigDecimal(0.0);
					datePlan.add(datePlan1);
				}

				/*//计算所有的总价格
				all=all.add(insurancePrice);
				model.addAttribute("allTotal", insurancePrice);
				all=all.add(marginPrice);*/
				//                BigDecimal exchangerate=new BigDecimal(0.0);
				//                exchangerate=order.getExchangerate();
				order.setOrderAmount(all);
				model.addAttribute("all", all);
				model.addAttribute("datePlan", datePlan); //44444
				String  totalprice=    new java.text.DecimalFormat("#.00").format(all.multiply(exchangerate));
				model.addAttribute("totalprice", totalprice);
				_orderRepository.save(order);

				return "/order/createplan";
			}
		}
		else {
			if(btn2=="提交"||btn2.equals("提交")){
				_datePlanRepository.deleteByOrderId(order.getId());
				startDates=dateFormat.format(dateFormat.parse(request.getParameter("amp;startDates")));
				endDates = dateFormat.format(dateFormat.parse(request.getParameter("amp;endDates")));
				Date  startDate= dateFormat.parse(startDates);
				Date  endDate =dateFormat.parse(endDates);  
				long day = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
				if (day >= 0) {
					for (long i = 0; i <= day; i++) {
						Date date = new Date();
						SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
						date.setTime(startDate.getTime() + 24 * 60 * 60 * 1000 * i);
						String s1 = sdf.format(date); //2015-02-09 format()才是格式化
						//新增数据
						DatePlan datePlans = new DatePlan();
						datePlans.setStartDate(startDate);
						datePlans.setEndDate(endDate);
						datePlans.setOrder(order);
						datePlans.setCreateTime(new Date());
						datePlans.setDate(sdf.parse(s1));
						datePlan.add(datePlans);
						_datePlanRepository.save(datePlans);
						model.addAttribute("datePlan", datePlan);
					}
				}
/*
				all=all.add(marginPrice);
				all=all.add(insurancePrice);*/
				order.setOrderAmount(all);
				model.addAttribute("all", all);
				model.addAttribute("datePlan", datePlan); //44444
				model.addAttribute("totalprice", all.multiply(exchangerate));
				_orderRepository.save(order);
			}else  if(btn2=="确定"||btn2.equals("确定")){
				//   _datePlanRepository.deleteByOrderId(order.getId());
				numcount =Integer.parseInt(request.getParameter("amp;numcount"));
				if (numcount >= 0) {
					for (long i = 1; i <= numcount; i++) {
						DatePlan datePlans = new DatePlan();
						datePlans.setOrder(order);
						datePlans.setCreateTime(new Date());
						datePlans.setOrdinatedday("Day"+i);
						datePlan.add(datePlans);
						_datePlanRepository.save(datePlans);
						model.addAttribute("datePlan", datePlan);
					}

				}else{
					return "/order/createplan";
				}
			}/*
			all=all.add(marginPrice);
			all=all.add(insurancePrice);*/
			order.setOrderAmount(all);
			model.addAttribute("all", all);
			model.addAttribute("datePlan", datePlan); //44444
			model.addAttribute("totalprice", all.multiply(exchangerate));
			_orderRepository.save(order);
		}
		return "/order/createplan";
	}


	@RequestMapping(value = "/{id}/plan", params = "_step2")
	public String step2(@PathVariable Integer id, Model model) {
		Order order = _orderRepository.findOne(id);
		model.addAttribute("order", order);
		//获取城市数据
		List<City> citys = _cityRepository.findAll();
		model.addAttribute("citys", citys);

		//年龄
		model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));

		return "/spotsPlan/editSpotPlan";
	}

	@RequestMapping(value = "/{id}/plan", params = "_step3")
	public String step3(@PathVariable Integer id, Model model) {
		Order order = _orderRepository.findOne(id);
		model.addAttribute("order", order);

		//        List<DatePlan> datePlans = _datePlanRepository.findByOrderId(order.getId());
		//        model.addAttribute("datePlans", datePlans);

		List<DatePlan> feedbackDatePlans = _orderService.getFeedBackDatePlan(order.getId());
		model.addAttribute("feedbackDatePlans", feedbackDatePlans);

		// 喜欢的酒店类型
		model.addAttribute("hotelTypeLike", _orderService.getHotelTypeLikeByOrderId(order.getId()));

		//年龄
		model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));

		return "/order/step3";
	}


	//显示金额明细
	@ResponseBody
	@RequestMapping(value = "/detailAmouut", method = RequestMethod.GET)
	public boolean detailAmouut(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		Order order = _orderRepository.findOne(Integer.parseInt(orderId));
		if (order.getAmountSetailStatus() == 0) {
			order.setAmountSetailStatus(1);
			_orderRepository.save(order);
			return true;
		} else {
			order.setAmountSetailStatus(0);
			_orderRepository.save(order);
			return false;
		}

	}


	@RequestMapping(value = "/{id}/plan", params = "_step5")
	public String step5(@PathVariable Integer id, Model model) {
		Order order = _orderRepository.findOne(id);
		model.addAttribute("order", order);
		if(order.getAmountSetailStatus()==0||order.getAmountSetailStatus().equals(0)){
			String detailAmouut="显示明细金额";
			model.addAttribute("detailAmouut", detailAmouut);
		}else if(order.getAmountSetailStatus()==1||order.getAmountSetailStatus().equals(1)){
			String detailAmouut="不显示明细金额";
			model.addAttribute("detailAmouut", detailAmouut);
		}
		BigDecimal all=new BigDecimal(0.0);
		List<InsurancePlan> insurancePlan = order.getInsurancePlan();
		for(int i=0;i<insurancePlan.size();i++){
			all=all.add(insurancePlan.get(i).getSubTotalAmount());
		}
		model.addAttribute("allTotal", all);

		/*  List <Order> orders=new ArrayList<Order>();
        BigDecimal margintalAmount=new BigDecimal(0.0);
        List <InsurancePlan> insurancePlans =_insurancePlanRepository.findByOrderId(order.getId());
       for(int i=0;i<insurancePlans.size();i++){
            Order order1 = _orderRepository.findOne(insurancePlans.get(i).getId());
        List<InsurancePlan> insurancePlanList = order1.getInsurancePlan();
        if (insurancePlanList != null) {
            for (InsurancePlan insurancePlan : insurancePlanList) {
                margintalAmount = margintalAmount.add(insurancePlan.getSubTotalAmount() == null ? BigDecimal.ZERO : insurancePlan.getSubTotalAmount());
            }
        }
        order1.setMargintalAmount(margintalAmount);
        margintalAmount=new  BigDecimal(0.0);
        orders.add(order1);
        }
        model.addAttribute("orders", orders);*/
		return "/order/step5";
	}


	@RequestMapping(value = "/{id}/plan", params = "_step7")
	public String step7(@PathVariable Integer id, Model model) {
		Order order = _orderRepository.findOne(id);
		model.addAttribute("order", order);

		return "/order/step6";
	}
	@RequestMapping(value = "/{id}/plan", params = "_step6")
	public String editmarginplan(@PathVariable Integer id, Model model, HttpServletRequest request) {
		Order order = _orderRepository.findOne(id);
		model.addAttribute("order", order);


		MarginPlan marginplan = _MarginPlanRepository.findByOrderId(order.getId());
		//            model.addAttribute("order", datePlan.getOrder());

		//总价
		BigDecimal totalPrice = _orderService.calculateTotalPrices(order);
		order.setOrderAmount(totalPrice);
		model.addAttribute("order", order);
		model.addAttribute("price", totalPrice.toString());

		String margintype = "定额保证金";
		Margin margin = _mMarginRepository.findByMargintype(margintype);

		String margintypes = "比率保证金";
		Margin margins = _mMarginRepository.findByMargintype(margintypes);

		model.addAttribute("marginprice", margins.getMarginprice());
		model.addAttribute("marginprices", margin.getMarginprice());
		if (marginplan == null || marginplan.getCount() == 0) {
			model.addAttribute("count", 0);
		} else {
			marginplan.setCount(_MarginPlanRepository.findByCount(margintype, order.getId()));
			model.addAttribute("count", marginplan.getCount());
		}
		//             model.addAttribute("datePlan", datePlan);
		//             model.addAttribute("order", datePlan.getOrder());
		if (marginplan == null || marginplan.getCount() == 0) {
			model.addAttribute("count", 0);
		} else {
			marginplan.setCount(_MarginPlanRepository.findByCount(margintype, order.getId()));
			model.addAttribute("count", marginplan.getCount());
		}

		return "/depositplan/create";
	}

	@RequestMapping(value = "/{id}/plan", params = "_step4")
	public String step4(@PathVariable Integer id, Model model) {
		Order order = _orderRepository.findOne(id);

		//        BigDecimal totalPrice = _orderService.calculateTotalPrice(order);
		//        order.setOrderAmount(totalPrice);
		model.addAttribute("order", order);

		//        BigDecimal cnkPrice = Constant.decimalFormat(totalPrice.multiply(_exchangeRateService.getCurrentExchangeRate()));
		model.addAttribute("order", order);
		//        model.addAttribute("totalPrice", cnkPrice.toString());


		return "/order/step4";
	}

	@RequestMapping(value = "/{id}/toplan", params = "_cancel")
	public String processCancel(SessionStatus status, HttpServletRequest request) {
		status.setComplete();

		String businessType = (String) WebUtils.getSessionAttribute(request, "businessType");
		return "redirect:/order/" + businessType;
	}

	@RequestMapping(value = "/{id}/toplan",method = RequestMethod.POST)
	public String finish(@PathVariable Integer id, SessionStatus status, HttpServletRequest request) {
		// 将订单状态改为“已规划”
		Order order = _orderRepository.findOne(id);
		order.setOrderStatus(2);
		order.setStatusTime(new Date());
		order.setCommitOrderTime(new Date());

		_orderRepository.save(order);
		// 计算总价格,需要改成直接从界面获取？
		//        order.setOrderAmount(_orderService.calculateTotalPrice(order));

		// 设置状态时间、规划提交时间
		/*
        _orderRepository.save(order);

        // 给游客发送通知邮件
        MailUtil mailUtil = new MailUtil(_mailSetting.getHost(), _mailSetting.getPort(), _mailSetting.getUsername(),
                _mailSetting.getPassword());
        String to = order.getTraveler().getEmail();
        String subject = "行程规划已完成";
        String text = "您的行程规划（订单号：" + order.getOrderNumber() + "）已完成，请在24小时之内查看您的行程规划。";

        mailUtil.sendHtmlMail(_mailSetting.getFrom(), to, subject, text);

        status.setComplete();

        String businessType = (String) WebUtils.getSessionAttribute(request, "businessType");
        return "redirect:/order/" + businessType;*/
		return "redirect:/order/toplan";
	}

	/**
	 * 已规划订单列表
	 */
	@RequestMapping(value = "/planedlist", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) {
		request.getSession(false);

		Member member = (Member) WebUtils.getSessionAttribute(request, "member");
		if (member == null) {
			return "redirect:/member/login";
		}
		return "/order/planedlist";
	}

	/**
	 * 已结算订单列表
	 */
	@RequestMapping(value = "/settledorder", method = RequestMethod.GET)
	public String settledlist(HttpServletRequest request, Model model) {
		request.getSession(false);
		Member member = (Member) WebUtils.getSessionAttribute(request, "member");
		if (member == null) {
			return "redirect:/member/login";
		}
		return "/order/settledorder";
	}


	/**
	 * 已规划订单
	 * add by limin
	 */
	@ResponseBody
	@RequestMapping(value = "/planedorder", method = RequestMethod.GET)
	public Iterable<Order> search(HttpServletRequest request) {
		// 使用QueryDsl构造查询条件
		String keyword = request.getParameter("search[value]");
		Predicate predicate;
		QOrder order = QOrder.order;
		// 获取状态为“已规划、已结算和已过期”的订单
		/*DataTable<Order, Integer> dataTable = DataTable.fromRequest(request, _orderRepository, predicate);*/
		if (keyword == null) {
			predicate = order.orderStatus.in(2, 3, 4);
		} else {
			predicate = order.orderStatus.in(2, 3, 4).or(order.orderNumber.containsIgnoreCase(keyword).or(order.traveler.firstName.append(order.traveler.lastName).contains(keyword.trim())));
		}
		Iterable<Order> data = _orderRepository.findAll(predicate);

		return data;
	}

	/**
	 * 已结算订单
	 * add by limin
	 */

	@ResponseBody
	@RequestMapping(value = "/settledorders", method = RequestMethod.GET)
	public Iterable<Order> settledorders(HttpServletRequest request) {
		// 使用QueryDsl构造查询条件
		String keyword = request.getParameter("search[value]");
		Predicate predicate;
		QOrder order = QOrder.order;
		if (keyword == null) {
			predicate = order.orderStatus.in(3);
		} else {
			predicate = order.orderStatus.in(3).or(order.orderNumber.containsIgnoreCase(keyword).or(order.traveler.firstName.append(order.traveler.lastName).contains(keyword.trim())));
		}
		Iterable<Order> data = _orderRepository.findAll(predicate);
		return data;
	}

	/*
	 * 以下几个方法是每个规划列表的删除规划方法
	 */

	/*
	 * 删除飞机规划
	 */

	@RequestMapping(value = "/deFilghtPlan")
	public String deleteFilghtPlan(@RequestParam("orderId")Integer orderId,@RequestParam("filghtPlanId") Integer filghtPlanId,HttpServletRequest request,Model model){
		//拿到当前的飞机规划
		FilghtPlan filghtPlan = _filghtPlanRepository.findOne(filghtPlanId);
		_filghtPlanRepository.delete(filghtPlan);
		return "redirect:/order/" + orderId + "/plan?_step1";
	}

	/*
	 * 删除租车规划
	 */
	@RequestMapping(value = "/deRentalPlan")
	public String deleteRentalPlan(@RequestParam("orderId")Integer orderId,@RequestParam("rentalPlanId") Integer rentalPlanId,HttpServletRequest request,Model model){
		//拿到当前的租车规划
		RentalPlan rentalPlan = _rentalPlanRepository.findOne(rentalPlanId);
		_rentalPlanRepository.delete(rentalPlan);
		return "redirect:/order/" + orderId + "/plan?_step1";
	}

	/*
	 * 删除导游规划
	 */
	@RequestMapping(value = "/deGuidePlan")
	public String deleteGuidePlan(@RequestParam("orderId")Integer orderId,@RequestParam("guidePlanId") Integer guidePlanId,@RequestParam("guideTypePlans") Integer guideTypePlans,HttpServletRequest request,Model model){
		GuideActivityPlan guideActivityPlan = _guideActivityPlanRepository.findOne(guidePlanId);
		List<GuideTypePlan> guideTypePlans1 =_guideTypePlanRepository.findByGuideActivityPlan(guideActivityPlan);
		GuideTypePlan GuideTypePlan = _guideTypePlanRepository.findOne(guideTypePlans);
		guideActivityPlan.setSubTotalAmount( guideActivityPlan.getSubTotalAmount().subtract(GuideTypePlan.getGuidePrice())) ;
		_guideActivityPlanRepository.save(guideActivityPlan);
		_guideTypePlanRepository.delete(GuideTypePlan);
		if(guideTypePlans1.size()==1){
			_guideActivityPlanRepository.delete(guideActivityPlan);
		}
		return "redirect:/order/" + orderId + "/plan?_step1";
	}

	/*
	 * 删除酒店规划
	 */
	@RequestMapping(value = "/deHotelPlan")
	public String deleteHotelPlan(@RequestParam("orderId")Integer orderId,@RequestParam("hotelPlanId") Integer hotelPlanId,@RequestParam("hotelplanRoomTypeId") Integer hotelplanRoomTypeId,HttpServletRequest request,Model model){
		HotelPlan hotelPlan = _hotelPlanRepository.findOne(hotelPlanId);
		List<HotelPlanRoom> hotelPlanRooms = _hotelPlanRoomRepository.findByHotelPlan(hotelPlan);
		HotelPlanRoom hotelPlanRoom = _hotelPlanRoomRepository.findOne(hotelplanRoomTypeId);
		hotelPlan.setSubtotalAmount(hotelPlan.getSubtotalAmount().subtract(hotelPlanRoom.getSalePrice()));
		_hotelPlanRepository.save(hotelPlan);
		_hotelPlanRoomRepository.delete(hotelPlanRoom);
		if(hotelPlanRooms.size()==1){
			_hotelPlanRepository.delete(hotelPlan);
		}

		return "redirect:/order/" + orderId + "/plan?_step1";
	}

	/*
	 * 删除景点规划
	 */
	@RequestMapping(value = "/deSpotPlan")
	public String deleteSpotPlan(@RequestParam("orderId")Integer orderId,@RequestParam("spotPlanId") Integer spotPlanId,@RequestParam("spotticketPlanId") Integer spotticketPlanId,HttpServletRequest request,Model model){
		SpotPlan spotPlan = _spotPlanRepository.findOne(spotPlanId);
		List<SpotPlanTicket> spotPlanTickets = _spotPlanTicketRepository.findBySpotPlan(spotPlan);
		SpotPlanTicket spotPlanTicket = _spotPlanTicketRepository.findOne(spotticketPlanId);
		spotPlan.setSubtotalAmount(spotPlan.getSubtotalAmount().subtract(spotPlanTicket.getSalePrice()));
		_spotPlanRepository.save(spotPlan);
		_spotPlanTicketRepository.delete(spotPlanTicket);
		if(spotPlanTickets.size()==1){
			_spotPlanRepository.delete(spotPlan);
		}
		return "redirect:/order/" + orderId + "/plan?_step1";
	}

	/**
	 * 为日期规划添加备注
	 */
	@ResponseBody
	@RequestMapping(value="/addRemark", method = RequestMethod.POST)
	public  String dataPlanFeedBack(Model model,HttpServletRequest request){
		String result="";
		String id = request.getParameter("id");
		String content = request.getParameter("content");

		DatePlan datePlan = _datePlanRepository.findById(Integer.parseInt(id));
		datePlan.setRemark(content);
		_datePlanRepository.save(datePlan);
		result="添加备注成功!";    
		return result;
	}
	
	/*
	 * 添加供应商订单号
	 */
	@ResponseBody
	@RequestMapping(value="/rentalsupplierNum", method = RequestMethod.POST)
	public  String rentalsupplierNum(Model model,HttpServletRequest request){
		String result="";
		Integer id = Integer.parseInt(request.getParameter("id"));
		String content = request.getParameter("content");
        RentalPlan rentalPlan = _rentalPlanRepository.findById(id);
		rentalPlan.setSupplierNum(content);
		_rentalPlanRepository.save(rentalPlan);
		result="添加供应商订单号成功!";    
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/flightsupplierNum", method = RequestMethod.POST)
	public  String flightsupplierNum(Model model,HttpServletRequest request){
		String result="";
		Integer id = Integer.parseInt(request.getParameter("id"));
		String content = request.getParameter("content");
		FilghtPlan filghtPlan = _filghtPlanRepository.findOne(id);
		filghtPlan.setSupplierNum(content);
		_filghtPlanRepository.save(filghtPlan);
		result="添加供应商订单号成功!";    
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/spotsupplierNum", method = RequestMethod.POST)
	public  String spotsupplierNum(Model model,HttpServletRequest request){
		String result="";
		Integer id = Integer.parseInt(request.getParameter("id"));
		String content = request.getParameter("content");
	    SpotPlanTicket spotPlanTicket = _spotPlanTicketRepository.findOne(id);
	    spotPlanTicket.setSupplierNum(content);
	    _spotPlanTicketRepository.save(spotPlanTicket);
		result="添加供应商订单号成功!";    
		return result;
	}
	

	@ResponseBody
	@RequestMapping(value="/hotelsupplierNum", method = RequestMethod.POST)
	public  String hotelsupplierNum(Model model,HttpServletRequest request){
		String result="";
		Integer id = Integer.parseInt(request.getParameter("id"));
		String content = request.getParameter("content");
		HotelPlanRoom hotelPlanRoom = _hotelPlanRoomRepository.findOne(id);
		hotelPlanRoom.setSupplierNum(content);
		_hotelPlanRoomRepository.save(hotelPlanRoom);
		result="添加供应商订单号成功!";    
		return result;
	}

}
