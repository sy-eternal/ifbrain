package com.jzeen.travel.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.entity.ExcursionGuideView;
import com.jzeen.travel.data.entity.QExcursionGuideView;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ExcursionViewGuideRepository;
import com.mysema.query.types.Predicate;
@Controller
@RequestMapping("/excursionGuideView")
public class ExcursionGuideViewController
{
    @Autowired
    private ExcursionViewGuideRepository _excursionViewGuideRepository;

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Iterable<ExcursionGuideView> search(HttpServletRequest request)
    {
        //获得登录用户的id
        User userLogin=(User)WebUtils.getSessionAttribute(request, "user");
        Integer userId = userLogin.getId();
        // 使用QueryDsl构造查询条件
     Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        QExcursionGuideView excursionGuideView = QExcursionGuideView.excursionGuideView;
        Predicate predicate = excursionGuideView.orderId.eq(orderId).and(excursionGuideView.guidePk.eq(userId));

        Iterable<ExcursionGuideView> data = _excursionViewGuideRepository.findAll(predicate);

        return data;
    }
}
