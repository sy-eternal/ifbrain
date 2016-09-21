package com.jzeen.travel.website.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.GuideOutstandingOrders;
import com.jzeen.travel.data.entity.QGuideOutstandingOrders;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.GuideOutstandingOrdersRepository;
import com.mysema.query.types.Predicate;



@Controller
@RequestMapping("/guideoutstandingorders")
public class GuideOutstandingOrdersController {
	  @Autowired
	  GuideOutstandingOrdersRepository _guideOutstandingOrdersRepository;
	  
	    @ResponseBody
	    @RequestMapping(value = "/search", method = RequestMethod.GET)
	    public DataTable<GuideOutstandingOrders, Integer> search(HttpServletRequest request)
	    {
	        User userLogin=(User)WebUtils.getSessionAttribute(request, "user");
	        Integer userId = userLogin.getId();
	        // 使用QueryDsl构造查询条件
            String keyword = request.getParameter("search[value]");
	    	QGuideOutstandingOrders order = QGuideOutstandingOrders.guideOutstandingOrders;
			Predicate predicate = order.endDate.after(new Date()).and(order.orderStatus.eq(3)).and(order.guidePk.eq(userId)).or(order.orderNumber.eq(keyword.trim()));
	        DataTable<GuideOutstandingOrders, Integer> dataTable = DataTable.fromRequest(request, _guideOutstandingOrdersRepository, predicate);
	        return dataTable;
	    }
	  
	  @RequestMapping(method = RequestMethod.GET)
	    public String index()
	    {
	        return "/guideoutstandingorders/index";
	    }
	

}
