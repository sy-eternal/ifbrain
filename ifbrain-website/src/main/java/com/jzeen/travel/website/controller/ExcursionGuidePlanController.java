package com.jzeen.travel.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.ExcursionGuidePlan;
import com.jzeen.travel.data.entity.QExcursionGuidePlan;
import com.jzeen.travel.data.repository.ExcursionGuidePlanRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/excursionGuidePlan")
public class ExcursionGuidePlanController
{
    @Autowired
    private ExcursionGuidePlanRepository _excursionGuidePlanRepository;

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Iterable<ExcursionGuidePlan> search(HttpServletRequest request)
    {
        // 使用QueryDsl构造查询条件
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        QExcursionGuidePlan excursionGuidePlan = QExcursionGuidePlan.excursionGuidePlan;
        Predicate predicate = excursionGuidePlan.datePlan.order.id.eq(orderId);

        Iterable<ExcursionGuidePlan> data = _excursionGuidePlanRepository.findAll(predicate);

        return data;
    }
}
