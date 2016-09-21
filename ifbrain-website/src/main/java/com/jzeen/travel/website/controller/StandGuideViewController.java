package com.jzeen.travel.website.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.entity.QStandGuideView;
import com.jzeen.travel.data.entity.StandGuideView;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.StandViewGuideRepository;
import com.mysema.query.types.Predicate;


@Controller
@RequestMapping("/standGuideView")
public class StandGuideViewController
{
    @Autowired
    private StandViewGuideRepository _standViewGuideRepository;

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Iterable<StandGuideView> search(HttpServletRequest request)
    {
        //获得登录用户的id
        User userLogin=(User)WebUtils.getSessionAttribute(request, "user");
        Integer userId = userLogin.getId();
        // 使用QueryDsl构造查询条件
     Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        QStandGuideView standGuideView = QStandGuideView.standGuideView;
        Predicate predicate = standGuideView.orderId.eq(orderId).and(standGuideView.guidePk.eq(userId));

        Iterable<StandGuideView> data = _standViewGuideRepository.findAll(predicate);

        return data;
    }
}
