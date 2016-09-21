package com.jzeen.travel.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.GuideActivity;
import com.jzeen.travel.data.entity.GuideActivityPlan;
import com.jzeen.travel.data.entity.GuideGuidetypePlanRelate;
import com.jzeen.travel.data.entity.GuideTypePlan;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.GuideActivityPlanRepository;
import com.jzeen.travel.data.repository.GuideGuideTypePlanRateRepository;
import com.jzeen.travel.data.repository.GuideRepository;
import com.jzeen.travel.data.repository.GuideTypePlanRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.UserRepository;

@Controller
@RequestMapping("/guideTypePlan")
public class GuideTypePlanController {
	@Autowired
	private OrderRepository _orderRepository;
	@Autowired
	private GuideTypePlanRepository _guideTypePlanRepository;
	@Autowired
	private CityRepository _cityRepository;
	@Autowired
	private GuideRepository _guideRepository;
	@Autowired
	private UserRepository _userRepository;
	@Autowired
	private GuideGuideTypePlanRateRepository _guideGuideTypePlanRateRepository;
	@Autowired
	private DatePlanRepository _datePlanRepository;
	@Autowired
	private GuideActivityPlanRepository _guideActivityPlanRepository;

	

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String plan(Model model, HttpServletRequest request) {

		// 获取状态为“已提交”的订单

		List<Order> orders = _orderRepository.findByOrderStatus(3);
		String as = "";
		for (Order order : orders) {// / 遍历每一个订单号，
			// 根据订单号查询是否有未派单

			List<GuideTypePlan> guideTypePlan = _guideTypePlanRepository
					.findByOrderNumAndop(order.getOrderNumber());
			// 如果有未派单的数据，那说明这个订单还有未派单，显示未派单
			if (guideTypePlan.isEmpty()) {
				order.setHasSent("已派单");
			} else {
				order.setHasSent("未派单");

			}
		}

		/*
		 * List<GuideTypePlan> guideTypePlan =
		 * _guideTypePlanRepository.findByOrder();
		 */
		// model.addAttribute("guideTypePlan", guideTypePlan);

		model.addAttribute("orders", orders);
		return "/guideTypePlan/list";
	}

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Integer id, Model model,
			HttpServletRequest request) {
		List<DatePlan> datePlans = _datePlanRepository.findByOrderId(id);
	/*	List<GuideActivityPlan> guideActivityPlan= _guideActivityPlanRepository.findByOrderId(id)*/
		Order order = _orderRepository.findById(id);
		/*if (datePlans != null) {
			for (DatePlan datePlan : datePlans) {
				List<GuideActivityPlan> guideActivityPlans = datePlan.getGuideActivityPlans();
				if (guideActivityPlans != null) {
					for (GuideActivityPlan guideActivityPlan :guideActivityPlans) {
						List<GuideTypePlan> guideTypePlans =guideActivityPlan.getGuideTypePlans();
						if (guideTypePlans != null) {
							for (GuideTypePlan guideTypePlan :guideTypePlans) {
								if(guideTypePlan.getGuideGuidetypePlanRelate() != null&&guideTypePlan.getGuideGuidetypePlanRelate().size() == 0) {
									guideTypePlan.setGuideGuidetypePlanRelate(null);
								}

							}
						}
					}
				}
			}
		}*/
		String dateDatePlans="";
		for(DatePlan datePlan : datePlans){
			List<GuideActivityPlan> guideActivityPlans=datePlan.getGuideActivityPlans();
			if(guideActivityPlans==null){
				dateDatePlans="";
				datePlan.setDateDatePlans(dateDatePlans);
			}else{
				dateDatePlans="0";
				datePlan.setDateDatePlans(dateDatePlans);
			}
		}
		String name="";
		for (int a=0;a<datePlans.size();a++){
			List<GuideActivityPlan> guideActivityPlans = datePlans.get(a).getGuideActivityPlans();
			for(int b=0;b<guideActivityPlans.size();b++){
				List<GuideTypePlan> guideTypePlans =guideActivityPlans.get(b).getGuideTypePlans();
				for(int c=0;c<guideTypePlans.size();c++){
					if(guideTypePlans.get(c).getGuideGuidetypePlanRelate().size()==2){
						name=guideTypePlans.get(c).getGuideGuidetypePlanRelate().get(c).getGuide().getUser().getFirstName()+guideTypePlans.get(c).getGuideGuidetypePlanRelate().get(c).getGuide().getUser().getLastName()+','+guideTypePlans.get(c).getGuideGuidetypePlanRelate().get(c+1).getGuide().getUser().getFirstName()+guideTypePlans.get(c).getGuideGuidetypePlanRelate().get(c+1).getGuide().getUser().getLastName();
						guideTypePlans.get(c).setName(name);
						System.out.println("if---------"+name);
					}else if(guideTypePlans.get(c).getGuideGuidetypePlanRelate().size()==1){
						name=guideTypePlans.get(c).getGuideGuidetypePlanRelate().get(c).getGuide().getUser().getFirstName()+guideTypePlans.get(c).getGuideGuidetypePlanRelate().get(c).getGuide().getUser().getLastName();
						guideTypePlans.get(c).setName(name);
						System.out.println("else if---------"+name);}
					else{
						name="未安排";
						guideTypePlans.get(c).setName(name);
						System.out.println("else---------"+name);
					}
				}
			}
		}
		System.out.println(datePlans);
		model.addAttribute("name",name);
		model.addAttribute("dateDatePlans",dateDatePlans);
		model.addAttribute("order", order);
		model.addAttribute("dataplans", datePlans);
		return "/guideTypePlan/details";

	}

	@RequestMapping(value = "/add/{id}/{orderId}", method = RequestMethod.GET)
	public String add(@PathVariable Integer id, @PathVariable Integer orderId,
			Model

			model, HttpServletRequest request) {
		List<City> city = _cityRepository.findAll();
		/*
		 * List<User> user= _userRepository.findAll();
		 * model.addAttribute("users",user);
		 */
		List<Guide> guide = _guideRepository.findAll();
		model.addAttribute("guide", guide);
		model.addAttribute("citys", city);
		model.addAttribute("guideTypeId", id);
		model.addAttribute("orderId", orderId);
		return "/guideTypePlan/create";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String create(
			@Valid GuideGuidetypePlanRelate guideGuidetypePlanRelate,
			BindingResult bindingResult, Model model, HttpServletRequest request) {
		// 获得导游类型规划主键
		String guideTypeId = request.getParameter("guideTypeId");
		String orderId = request.getParameter("orderId");
		int guideTypeid = Integer.parseInt(guideTypeId);
		GuideTypePlan guideTypePlan = _guideTypePlanRepository
				.findOne(guideTypeid);
		// 获得导游姓名
		List<GuideTypePlan> guideTypePlans = _guideTypePlanRepository
				.findByid(guideTypeid);
		String guideId = request.getParameter("guideName");
		System.out.println("======="+guideId);
		int guideid = Integer.parseInt(guideId);
		Guide guide = _guideRepository.findByUserId(guideid);
		guideTypePlan.setAppointedStatus(1);
		_guideTypePlanRepository.save(guideTypePlans);
		guideGuidetypePlanRelate.setCreateTime(new Date());
		guideGuidetypePlanRelate.setGuideTypePlan(guideTypePlan);
		guideGuidetypePlanRelate.setGuide(guide);
		_guideGuideTypePlanRateRepository.save(guideGuidetypePlanRelate);
	
		return "redirect:/guideTypePlan/details/" + orderId;
	}
	@ResponseBody
	@RequestMapping(value="/selectCity/{id}",method = RequestMethod.GET)
	public List<User> select(Model model,@PathVariable String id){
		List<Guide> guide= _guideRepository.findByCity(Integer.parseInt(id));
		List<User> list=new ArrayList<User>();
		for(int i=0;i<guide.size();i++){
			
			list.add(guide.get(i).getUser());
		}
		/*List<String> name=new ArrayList<String>();
		JSONArray jsonArray =new  JSONArray();
		for(int i=0;i<guide.size();i++){
			JSONObject json=new JSONObject();
			json.put("name",guide.get(i).getUser().getFirstName()+guide.get(i).getUser().getLastName());
			json.put("id",guide.get(i).getUser().getId());
			jsonArray.put(json);
		}
		String string = jsonArray.toString();
		System.out.println("json:");
		System.out.println(string);*/
		return list;
		
	}
}
