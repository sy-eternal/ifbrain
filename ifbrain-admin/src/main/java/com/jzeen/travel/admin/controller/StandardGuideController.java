package com.jzeen.travel.admin.controller;

import com.jzeen.travel.core.util.Constant;
import com.jzeen.travel.core.util.DateUtil;
import com.jzeen.travel.data.entity.StandardGuide;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.StandardGuideRepository;
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
@RequestMapping("/standardGuide")
public class StandardGuideController {
    @Autowired
    StandardGuideRepository _standardGuideRepository;

    @ResponseBody
    @RequestMapping(value = "/getUnoccupiedCount", method = RequestMethod.GET)
    public int getUnoccupiedCount(HttpServletRequest request) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(request.getParameter("date"));
        Integer cityId = Integer.parseInt(request.getParameter("cityId"));
        Integer vehicleTypeCode = Integer.parseInt(request.getParameter("vehicleTypeCode"));
        List<StandardGuide> standardGuides;
        if (vehicleTypeCode != null && vehicleTypeCode == Constant.CODE_TYPE_TRAFFIC_STAND_CAR) {
            // 标准导游专车
            standardGuides = _standardGuideRepository.getUnoccupiedHasCar(date, cityId);
        } else {
            standardGuides = _standardGuideRepository.getUnoccupied(date, cityId);
        }
        return standardGuides.size();
    }


    /**
     * 出发城市相同，游主地点为该出发城市的 出发日期 未被占用的导游姓名
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
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

        List<User> guideList = _standardGuideRepository.getUnoccupiedGuide(DateUtil.gmtDateFormat(startDate), fromCityInt);
        return usersAddGender(guideList);
    }


    /**
     * 出发城市相同，游主地点为该出发城市的 出发日期 未被占用的导游姓名，选择的 标准导游专车,有车的导游姓名；
     *
     * @param request
     * @return
     * @throws ParseException
     */

    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping(value = "/getUnoccupiedAndHasCar", method = RequestMethod.GET)
    public List<User> getUnoccupiedAndHasCar(HttpServletRequest request) throws ParseException {

        //获取出发城市
        String fromCity = request.getParameter("fromCity");
        if (fromCity.equals("")) {
            fromCity = "0";
        }
        Integer fromCityInt = Integer.parseInt(fromCity);
        //获取开始日期
        String startDate = request.getParameter("startDate");

        List<User> guideList = _standardGuideRepository.getUnoccupiedAndHasCar(DateUtil.gmtDateFormat(startDate), fromCityInt);
        return usersAddGender(guideList);
    }


    /**
     * 出发城市不相同，出发地导游显示全部 导游主地点为该出发城市的出发日期 未被占用的、愿意到其他地方-是 导游姓名
     *
     * @param request
     * @return
     * @throws ParseException
     */

    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping(value = "/getUnoccupiedDiffenren", method = RequestMethod.GET)
    public List<User> getUnoccupiedDiffenren(HttpServletRequest request) throws ParseException {

        //获取出发城市
        String fromCity = request.getParameter("fromCity");
        if (fromCity.equals("")) {
            fromCity = "0";
        }
        Integer fromCityInt = Integer.parseInt(fromCity);
        //获取开始日期
        String startDate = request.getParameter("startDate");

        List<User> guideList = _standardGuideRepository.getUnoccupiedAndDifferent(DateUtil.gmtDateFormat(startDate), fromCityInt);
        return usersAddGender(guideList);
    }


    /**
     * 出发城市不相同，出发地导游显示全部 导游主地点为该出发城市的出发日期,选择的 标准导游专车, 未被占用的、愿意到其他地方-是 有车的导游姓名；
     *
     * @param request
     * @return
     * @throws ParseException
     */

    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping(value = "/getUnoccupiedDiffenrenJiaotong", method = RequestMethod.GET)
    public List<User> getUnoccupiedDiffenrenJiaotong(HttpServletRequest request) throws ParseException {

        //获取出发城市
        String fromCity = request.getParameter("fromCity");
        if (fromCity.equals("")) {
            fromCity = "0";
        }
        Integer fromCityInt = Integer.parseInt(fromCity);
        //获取开始日期
        String startDate = request.getParameter("startDate");

        List<User> guideList = _standardGuideRepository.getUnoccupiedDiffenrenJiaotong(DateUtil.gmtDateFormat(startDate), fromCityInt);
        return usersAddGender(guideList);
    }

    /**
     * 到达地导游显示全部 导游主地点为该到达城市的 到达日期 未被占用的导游姓名;
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping(value = "/getUnoccupiedArriveCity", method = RequestMethod.GET)
    public List<User> getUnoccupiedArriveCity(HttpServletRequest request) throws ParseException {

        //获取到达城市
        String toCity = request.getParameter("toCity");
        Integer toCityInt = Integer.parseInt(toCity);

        String endDate = request.getParameter("endDate");

        List<User> guideList = _standardGuideRepository.getUnoccupiedArriveCity(DateUtil.gmtDateFormat(endDate), toCityInt);
        return usersAddGender(guideList);
    }

    /**
     * 如果交通方式 选择的 标准导游专车 只显示导游主地点为该到达城市的未被占用的、有车的导游姓名。
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @SuppressWarnings("deprecation")
    @ResponseBody
    @RequestMapping(value = "/getUnoccupiedArriveCityHasCar", method = RequestMethod.GET)
    public List<User> getUnoccupiedArriveCityHasCar(HttpServletRequest request) throws ParseException {

        //获取出发城市
        String toCity = request.getParameter("toCity");
        if (toCity.equals("")) {
            toCity = "0";
        }
        Integer toCityInt = Integer.parseInt(toCity);


        String endDate = request.getParameter("endDate");

        List<User> guideList = _standardGuideRepository.getUnoccupiedArriveCityHasCar(DateUtil.gmtDateFormat(endDate), toCityInt);
        return usersAddGender(guideList);
    }

    // 女性导游名字后添加性别显示
    private List<User> usersAddGender(List<User> users) {
        if (users == null) {
            return users;
        }
        //zyy
       /* for (User user : users) {
            if (user.getSex() != null && user.getSex().equals(Constant.USER_GANDER_FEMALE)) {
                user.setLastName(user.getLastName() + "(" + Constant.USER_GANDER_FEMALE_NAME + ")");
            }
        }*/
        for (User user : users) {
            if (user.getGuide().getSex() != null && user.getGuide().getSex().equals(Constant.USER_GANDER_FEMALE)) {
                user.setLastName(user.getLastName() + "(" + Constant.USER_GANDER_FEMALE_NAME + ")");
            }
        }
        return users;
    }
}
