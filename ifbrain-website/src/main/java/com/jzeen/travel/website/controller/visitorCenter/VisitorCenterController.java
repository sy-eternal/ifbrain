package com.jzeen.travel.website.controller.visitorCenter;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import scala.annotation.meta.getter;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.ConfiremedTraveler;
import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.ExchangeRate;
import com.jzeen.travel.data.entity.ExcursionGuideOccupied;
import com.jzeen.travel.data.entity.FilghtPlan;
import com.jzeen.travel.data.entity.GuideActivityPlan;
import com.jzeen.travel.data.entity.HotelPlan;
import com.jzeen.travel.data.entity.InsurancePlan;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.PlanOrder;
import com.jzeen.travel.data.entity.QOrder;
import com.jzeen.travel.data.entity.RentalPlan;
import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.data.entity.StandardGuideOccupied;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ConfiremedTravelerRepository;
import com.jzeen.travel.data.repository.ExchangeRateRepository;
import com.jzeen.travel.data.repository.ExcursionGuideOccupiedRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.PlanOrderRepository;
import com.jzeen.travel.data.repository.StandardGuideOccupiedRepository;
import com.mysema.query.types.Predicate;
import com.jzeen.travel.core.util.StringUtil;

@Controller
@RequestMapping("/center")
public class VisitorCenterController {

	@Autowired
	OrderRepository _orderRepository;
	
	@Autowired
	PlanOrderRepository _planOrderRepository;
	
	
	
	@Autowired
	ExchangeRateRepository   _exchangeRateRepository;

	@Autowired
	StandardGuideOccupiedRepository   _standardGuideOccupiedRepository;
	@Autowired
	ExcursionGuideOccupiedRepository _excursionGuideOccupiedRepository;
	@Autowired
	ConfiremedTravelerRepository _confiremedTravelerRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model)
	{
		/*if(request.getSession() == null){
            System.out.println("daye+++++++++++++++");
        }
		request.getSession(false);*/
		User user =(User)WebUtils.getSessionAttribute(request, "user");
		Order order =(Order)WebUtils.getSessionAttribute(request, "order");
		System.out.println("user"+user.getFirstName());
		WebUtils.setSessionAttribute(request, "usertyoes", user.getFirstName()+user.getLastName());
		WebUtils.setSessionAttribute(request, "usertyoe", user.getUserType());
		//WebUtils.setSessionAttribute(request, "order", order);
		if(user==null){
			return"redirect:/user/login";
		}
		return "/order/orderlist";
	}

	/**
	 * 我的订单
	 */
/*	@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public DataTable<Order, Integer> search(HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("user");
		Integer userId = user.getId();
		// 使用QueryDsl构造查询条件
		String keyword = request.getParameter("search[value]");
		QOrder order=QOrder.order;
		Predicate predicate=order.orderNumber.containsIgnoreCase(keyword).and(order.traveler.id.eq(userId));
		DataTable<Order, Integer> dataTable = DataTable.fromRequest(request, _orderRepository,predicate);
		return dataTable;
	}*/



	/**
	 * 已规划订单
	 */

	@RequestMapping(value="orderPlan",method = RequestMethod.GET)
	public String orderPlan(HttpServletRequest request,Model model)
	{


		return "";
	}

	/**
	 *测试支付宝
	 */

	@RequestMapping(value="/doSubmit",method = RequestMethod.GET)
	public String doSubmit(HttpServletRequest request,Model model)
	{
		Integer orderID = 190;
		return "redirect:/AliPay/Submit/"+orderID;
	}


	/**
	 * 订单结算(添加随行人员)
	 */

	@RequestMapping(value="/addTra",method = RequestMethod.POST)
	public String addTra( @RequestParam("orderId") Integer orderId,HttpServletRequest request,Model model)
	{
		Order order = _orderRepository.findOne(orderId);
		Date toTime = order.getCommitOrderTime();
		Date nowTime = new Date();
		Date goTime = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(toTime);
		calendar.add(calendar.DATE,1);
		goTime = calendar.getTime();
		System.out.println("生成时间是"+toTime+"*****************");
		System.out.println("过期时间是"+goTime+"*****************");
		System.out.println("现在时间是"+nowTime+"*****************");
		nowTime.before(toTime);
		if(nowTime.before(goTime)){
			model.addAttribute("orderId",orderId);
			Integer personCount = order.getPersonCount();
			model.addAttribute("personCount", personCount);
			return "/visitorCenter/addTra";
		}else{
			order.setOrderStatus(4);
			_orderRepository.save(order);
			return "redirect:/center";
		}
	}
	
	@RequestMapping(value="/addTraA",method = RequestMethod.GET)
	public String addTraA( @RequestParam("orderId") Integer orderId,HttpServletRequest request,Model model)
	{
		Order order = _orderRepository.findOne(orderId);
		Date toTime = order.getCommitOrderTime();
		Date nowTime = new Date();
		Date goTime = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(toTime);
		calendar.add(calendar.DATE,1);
		goTime = calendar.getTime();
		System.out.println("生成时间是"+toTime+"*****************");
		System.out.println("过期时间是"+goTime+"*****************");
		System.out.println("现在时间是"+nowTime+"*****************");
		nowTime.before(toTime);
		if(nowTime.before(goTime)){
			model.addAttribute("orderId",orderId);
			Integer personCount = order.getPersonCount();
			model.addAttribute("personCount", personCount);
			
			ExchangeRate exchangerate = _exchangeRateRepository.getByExchangerate();
			
			BigDecimal changerate = exchangerate.getExchangerate();
			
			/*BigDecimal multiply = changerate.multiply(order.getOrderAmount());
			
			
			服务费     服务费2.5瞎编，这个值暂定
			BigDecimal result = multiply.multiply(new BigDecimal(2.5)).subtract(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_HALF_UP);*/
			
			/**
			 * 套餐服务折扣价
			 */
			BigDecimal GuidePrice=new BigDecimal(0.0);
			BigDecimal result=new BigDecimal(0.0);
			
			
			/**
			 * 去计算时的总价格
			 */
			BigDecimal checkOutAmount=new BigDecimal(0.0);
			//预订服务费
			BigDecimal reservationServiceFee=new BigDecimal(1000);
			
			
		
			
			
			List<DatePlan> datePlan = order.getDatePlan();	
		
			
			//保险
			BigDecimal insurance=new BigDecimal(0.0);
		    
            //保险费用
            List<InsurancePlan> insurancePlan = order.getInsurancePlan();
            for(int k=0;k<insurancePlan.size();k++){
                insurance=insurance.add(insurancePlan.get(k).getSubTotalAmount());
            }
            
            //酒店
            BigDecimal hotel=new BigDecimal(0.0);
            //租车
            BigDecimal rental=new BigDecimal(0.0);
            //景点
            BigDecimal spot=new BigDecimal(0.0);
            //飞机
            BigDecimal flight=new BigDecimal(0.0);
            PlanOrder planOrder;
            
            
            
            for(int i=0;i<datePlan.size();i++){
                //飞机费用
                List<FilghtPlan> filghtPlan = datePlan.get(i).getFilghtPlan();
                for(int o=0;o<filghtPlan.size();o++){
                    flight=flight.add(filghtPlan.get(o).getSubtotalAmount());
                }
                //酒店费用
                List<HotelPlan> hotelPlan = datePlan.get(i).getHotelPlan();
                for(int l=0;l<hotelPlan.size();l++){
                    hotel=hotel.add(hotelPlan.get(l).getSubtotalAmount());
                }
                //租车费用
                List<RentalPlan> rentalPlan = datePlan.get(i).getRentalPlan();
                for(int m=0;m<rentalPlan.size();m++){
                    rental=rental.add(rentalPlan.get(m).getSubtotalamount());
                }
                //景点费用
                List<SpotPlan> spotPlan = datePlan.get(i).getSpotPlan();
                
                for(int n=0;n<spotPlan.size();n++){
                    spot=spot.add(spotPlan.get(n).getSubtotalAmount());
                }      
            
      if(order.getPlanOrder().getPlanOfferName().equals("行程规划+一站预订+导游服务")){      
		
			    //计算导游费用
			    List<GuideActivityPlan> guideActivityPlans = datePlan.get(i).getGuideActivityPlans();
			    for(int j=0;j<guideActivityPlans.size();j++){
			        GuidePrice=GuidePrice.add(guideActivityPlans.get(j).getSubTotalAmount());
			        
		        	}
			
			//套餐服务折扣价 总价
			result=((GuidePrice.add(reservationServiceFee)).multiply(new BigDecimal(0.9))).setScale(2, BigDecimal.ROUND_HALF_UP);
			
			 planOrder = order.getPlanOrder();
			planOrder.setServiceAmount(result);
			model.addAttribute("planOrder", planOrder);
			_planOrderRepository.save(planOrder);
			//计算去结算时的总价
		         order.setCheckOutAmount((checkOutAmount.add(flight).add(spot).add(rental).add(hotel).add(insurance)).multiply(changerate).add(result));
      }else if(order.getPlanOrder().getPlanOfferName().equals("行程规划+一站预定")){
           planOrder = order.getPlanOrder();
          planOrder.setServiceAmount(reservationServiceFee);
          _planOrderRepository.save(planOrder);
          model.addAttribute("planOrder", planOrder);
          order.setCheckOutAmount(new BigDecimal(1000).add((checkOutAmount.add(flight).add(spot).add(rental).add(hotel).add(insurance)).multiply(changerate)));
      }
            }
			    _orderRepository.save(order);
			
			model.addAttribute("flight", flight);
			model.addAttribute("hotel", hotel);
			model.addAttribute("spot", spot);
			model.addAttribute("rental", rental);
			model.addAttribute("insurance", insurance);
			
			
			model.addAttribute("serviceFee", result);
			return "/visitorCenter/addTra";
		}else{
			order.setOrderStatus(4);
			_orderRepository.save(order);
			return "redirect:/center";
		}
	}

	@RequestMapping(value="doAddTra",method = RequestMethod.POST)
	public String doAddTra(@RequestParam("orderId") Integer orderId, AddTraS addTraS,HttpServletRequest request,Model model)
	{
		try {
			StringUtil stringUtil = new StringUtil();
			List<String> xins = stringUtil.splictDOTWithNull(addTraS.getXinss());
			List<String> mings = stringUtil.splictDOTWithNull(addTraS.getMingss());
			List<String> firstNames = stringUtil.splictDOTWithNull(addTraS.getFirstNamess());
			List<String> secondNames = stringUtil.splictDOTWithNull(addTraS.getSecondNamess());
			List<String> genders = stringUtil.splictDOTWithNull(addTraS.getGenderss());
			List<String> birthDates = stringUtil.splictDOTWithNull(addTraS.getBirthDatez());
			List<String> weichats = stringUtil.splictDOTWithNull(addTraS.getWeichatsz());
			List<String> emails = stringUtil.splictDOTWithNull(addTraS.getEmailsz());
			List<String> driveLicenseCodes = stringUtil.splictDOTWithNull(addTraS.getDriveLicenseCodesz());
			List<String> mobilNums = stringUtil.splictDOTWithNull(addTraS.getMobileNumz());
			List<String> passportNums =stringUtil.splictDOTWithNull(addTraS.getPassportNumz());
			Order order = _orderRepository.findById(orderId);
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
			for(int i=0;i<xins.size();i++)
			{
				ConfiremedTraveler ct=new ConfiremedTraveler();
				ct.setXin(xins.get(i));
				ct.setMing(mings.get(i));
				ct.setFirstName(firstNames.get(i));
				ct.setSecondName(secondNames.get(i));
				if(genders.get(i)!=null)
				{
					if("1".equals(genders.get(i)))
					{
						ct.setGender(1);
					}
					else if("2".equals(genders.get(i)))
					{
						ct.setGender(2);
					}
				}
				ct.setBirthDate(sf.parse(birthDates.get(i)));
				ct.setMobileNumber(mobilNums.get(i));
				ct.setPassportNumber(passportNums.get(i));
				ct.setDriveLicenseCode(driveLicenseCodes.get(i));
				ct.setWeichat(weichats.get(i));
				ct.setEmail(emails.get(i));
				ct.setCreateTime(new Date());
				ct.setOrder(order);
				_confiremedTravelerRepository.save(ct);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return "redirect:/AliPay/Submit/"+orderId;
	}




	/**
	 * 已付款订单
	 */
	@RequestMapping(value="payOrder",method = RequestMethod.GET)
	public String payOrder(HttpServletRequest request,Model model)
	{
		User user = (User)request.getSession().getAttribute("user");
		List<Order> jOrder = _orderRepository.findByTravelerAndOrderStatus(user, 3);//已结算
		List<Order> wOrder = _orderRepository.findByTravelerAndOrderStatus(user, 5);//已完成
		List<Order> orderlist=new ArrayList<Order>();
		orderlist.addAll(jOrder);
		orderlist.addAll(wOrder);
		model.addAttribute("orderlist", orderlist);

		return "/visitorCenter/payOrder";
	}






	/**
	 * 个人信息
	 */



	/*
	 * 删除订单
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id)
	{
		_orderRepository.delete(id);

	}
}
