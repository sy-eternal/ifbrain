package com.jzeen.travel.website.controller.trip;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.entity.GuideOrder;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.PlanOrder;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.PlanOrderRepository;

@Controller
@RequestMapping("/detailtrip")
public class DetailTripsController {
	
	@Autowired
	private PlanOrderRepository _planOrderRepository;
	
	@Autowired
	private OrderRepository _orderRepository;

	/**
	 * 跳转到去结算页面
	 */
	@RequestMapping(value="/tosettlement",method=RequestMethod.GET)
	public String tosettlementInit(@RequestParam Integer orderid,@RequestParam Integer id,Model model,HttpServletRequest request){
		if(id==1){
			model.addAttribute("tripPlanId", "行程规划");
		}else if(id==2){
			model.addAttribute("tripPlanId", "行程规划+一站预定");
		}else if(id==3){
			model.addAttribute("tripPlanId", "行程规划+一站预定+导游随行");
		}else if(id==3){
            model.addAttribute("tripPlanId", "行程规划+一站预定+导游随行");
        }else if(id==4){
            model.addAttribute("tripPlanId","徒步导游");
           }
       else if(id==5){
           model.addAttribute("tripPlanId","司机兼导游");
          }
		Order order=_orderRepository.findOne(orderid);
		PlanOrder planOrder=_planOrderRepository.findByOrder(order);
		model.addAttribute("planOrderAmount", planOrder.getAmount());
		model.addAttribute("orderid", orderid);
		System.out.println(orderid);
		return "/trip/tosettlement";
	}
	   @RequestMapping(value="/tosettlementguide",method=RequestMethod.GET)
	    public String tosettlementInitguide(@RequestParam Integer id,Model model,HttpServletRequest request){
	         if(id==4){
	            model.addAttribute("tripPlanId","徒步导游");
	           }
	        else if(id==5){
	           model.addAttribute("tripPlanId","司机兼导游");
	          }
	         GuideOrder guideorder = (GuideOrder) WebUtils.getSessionAttribute(request, "guideorder");
	         model.addAttribute("guideamount",guideorder.getOrderamount());
	         model.addAttribute("guideid",guideorder.getId());
	        return "/trip/tosettlementguide";
	    }
}
