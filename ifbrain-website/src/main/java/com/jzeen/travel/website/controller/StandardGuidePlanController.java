package com.jzeen.travel.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.QStandardGuidePlan;
import com.jzeen.travel.data.entity.StandardGuidePlan;
import com.jzeen.travel.data.repository.StandardGuidePlanRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/standardGuidePlan")
public class StandardGuidePlanController
{
    @Autowired
    private StandardGuidePlanRepository _standardGuidePlanRepository;

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Iterable<StandardGuidePlan> search(HttpServletRequest request)
    {
        // 使用QueryDsl构造查询条件
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        QStandardGuidePlan standardGuidePlan = QStandardGuidePlan.standardGuidePlan;
        Predicate predicate = standardGuidePlan.datePlan.order.id.eq(orderId);

        Iterable<StandardGuidePlan> data = _standardGuidePlanRepository.findAll(predicate);

        return data;
    }
}
