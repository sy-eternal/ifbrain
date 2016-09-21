package com.jzeen.travel.admin.controller;

import com.jzeen.travel.core.util.Constant;
import com.jzeen.travel.core.util.DateUtil;
import com.jzeen.travel.data.entity.ExcursionGuide;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ExcursionGuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/excursionGuide")
public class ExcursionGuideController {
    @Autowired
    ExcursionGuideRepository _excursionGuideRepository;

    @ResponseBody
    @RequestMapping(value = "/getUnoccupiedCount", method = RequestMethod.GET)
    public int getUnoccupiedCount(HttpServletRequest request) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(request.getParameter("date"));
        Integer cityId = Integer.parseInt(request.getParameter("cityId"));
        Integer vehicleTypeCode = Integer.parseInt(request.getParameter("vehicleTypeCode"));
        List<ExcursionGuide> excursionGuides;
        if (vehicleTypeCode != null && vehicleTypeCode == Constant.CODE_TYPE_TRAFFIC_SHORT_CAR) {
            // 短途导游专车
            excursionGuides = _excursionGuideRepository.getUnoccupiedHasCar(date, cityId);
        } else {
            excursionGuides = _excursionGuideRepository.getUnoccupied(date, cityId);
        }

        return excursionGuides.size();
    }


    /**
     * 出发地临时接送导游显示全部 导游主地点为该出发城市的 有车的、愿意短途接送-是、出发日期 未被占用的导游姓名
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "/getUnoccupied", method = RequestMethod.GET)
    public List<User> getUnoccupied(HttpServletRequest request) throws ParseException {

        //获取出发城市
        String fromCity = request.getParameter("fromCity");
        if (fromCity.equals("undefined")) {
            fromCity = "0";
        }
        Integer fromCityInt = Integer.parseInt(fromCity);
        //获取开始日期
        String startDate = request.getParameter("startDate");

        List<User> guide = _excursionGuideRepository.getUnoccupiedDiffenrenJiaotong(DateUtil.gmtDateFormat(startDate), fromCityInt);
        System.out.println(guide.size());
        return guide;
    }

    /**
     * 到达地临时接送导游显示全部 导游主地点为该到达城市的 有车的、愿意短途接送-是、到达日期 未被占用的导游姓名
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "/getUnoccupiedArrive", method = RequestMethod.GET)
    public List<User> getUnoccupiedArrive(HttpServletRequest request) throws ParseException {

        //获取到达城市
        String toCity = request.getParameter("toCity");
        if (toCity.equals("")) {
            toCity = "0";
        }
        Integer toCityInt = Integer.parseInt(toCity);

        String endDate = request.getParameter("endDate");

        List<User> guide = _excursionGuideRepository.getUnoccupiedArrive(DateUtil.gmtDateFormat(endDate), toCityInt);
        return guide;
    }

}
