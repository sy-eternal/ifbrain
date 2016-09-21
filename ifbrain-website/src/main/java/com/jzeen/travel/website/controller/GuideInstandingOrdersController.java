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
import com.jzeen.travel.data.entity.GuideInstandingOrders;
import com.jzeen.travel.data.entity.GuideOutstandingOrders;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.QGuideInstandingOrders;
import com.jzeen.travel.data.entity.QGuideOutstandingOrders;
import com.jzeen.travel.data.entity.QOrder;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.GuideInstandingOrdersRepository;
import com.jzeen.travel.data.repository.GuideOutstandingOrdersRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.mysema.query.types.Predicate;
@Controller
@RequestMapping("/guideinstandingorders")
public class GuideInstandingOrdersController
{
    @Autowired
    GuideInstandingOrdersRepository _guideInstandingOrdersRepository;
    
      @ResponseBody
      @RequestMapping(value = "/search", method = RequestMethod.GET)
      public DataTable<GuideInstandingOrders, Integer> search(HttpServletRequest request)
      {
          User userLogin=(User)WebUtils.getSessionAttribute(request, "user");
          Integer userId = userLogin.getId();
       // 使用QueryDsl构造查询条件
          String keyword = request.getParameter("search[value]");
          QGuideInstandingOrders order = QGuideInstandingOrders.guideInstandingOrders;
          Predicate predicate = order.endDate.before(new Date()).and(order.orderStatus.eq(3)).and(order.guidePk.eq(userId)).or(order.orderNumber.eq(keyword.trim()));
          DataTable<GuideInstandingOrders, Integer> dataTable = DataTable.fromRequest(request, _guideInstandingOrdersRepository, predicate);
          return dataTable;
      }
    
    @RequestMapping(method = RequestMethod.GET)
      public String index()
      {
          return "/guideinstandingorders/index";
      }
}
