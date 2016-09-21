package com.jzeen.travel.admin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.QOrder;
import com.jzeen.travel.data.repository.OrderRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/orderActive")
public class OrderActiveController
{
    @Autowired
    OrderRepository _orderRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/orderactive/index";
    }
    
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Order> search(HttpServletRequest request)
    {
//     // 使用QueryDsl构造查询条件
//        String keyword = request.getParameter("search[value]");
//        QOrder order=QOrder.order;
//        Predicate predicate=order.traveler.firstName.containsIgnoreCase(keyword);
        List<Order> data = _orderRepository.findAll();
        return data;
    }

}
