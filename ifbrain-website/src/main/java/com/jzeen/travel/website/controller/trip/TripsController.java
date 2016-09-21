package com.jzeen.travel.website.controller.trip;

import com.jzeen.travel.data.entity.*;
import com.jzeen.travel.data.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/trips")
public class TripsController
{
	@Autowired
	PlanOrderRepository _planOrderRepository;
	
    @Autowired
    UserRepository _userRepository;

    @Autowired
    CountryRepository _countryRepository;

    @Autowired
    CityRepository _cityRepository;

    @Autowired
    OrderRepository _OrderRepository;

    @Autowired
    PurposeRepository _purpostRepository;

    @Autowired
    AccompanyTypeRepository _accompanyTypeRepository;

    @Autowired
    ThemeRepository _themeRepository;

    @Autowired
    PurposeRelateRepository _purposeRelateRepository;

    @Autowired
    AccompanyRelateRepository _accompanyRelateRepository;

    @Autowired
    ThemeRelateRepository _themeRelateRepository;

    @Autowired
    CityConfirmedRepository _cityConffirmedRepository;

    @Autowired
    InterestedCityRelateRepository _interestedCityRelateRepository;

    @Autowired
    InnerCityTrafficRelateRepository _innerCityTrafficRelateRepository;

    @Autowired
    CodeRepository _codeRepository;
    @Autowired
    WeChatUserRepository  weChatUserRepository;
    @Autowired
    GuideOrderRepository _guideOrderRepository;
    
    @Autowired
    GuideRateRepository _guideRateRepository;

    @Autowired
    CodeOrderRelateRepository _codeOrderRelateRepotitory;
    @Autowired
    AccompanyMemberAgeRepository _AccompanyMemberAge;
    @Autowired
	private ChildRepository _cChildRepository;
    @RequestMapping(method = RequestMethod.GET)
    public String index(@ModelAttribute User user, Model model, HttpServletRequest request, HttpSession session, @RequestParam Integer id) {
        TripPlan tripPlan = (TripPlan) session.getAttribute("tripPlan");
        if (tripPlan != null) {

            //行程规划主题的ID
//            Integer id=Integer.parseInt(request.getParameter("id"));
            tripPlan.setId(id);
            model.addAttribute("tripPlan", tripPlan);
        } else {
            TripPlan tp = new TripPlan();
//        	 Integer id=Integer.parseInt(request.getParameter("id"));
            tp.setId(id);
            model.addAttribute("tripPlan", tp);
        }
        user = (User) WebUtils.getSessionAttribute(request, "user");
        if (user == null) {
            model.addAttribute("user", null);
//            return "redirect:/user/login";
        } else {
            model.addAttribute("user", user.getId());
            List<Order> orderList = _OrderRepository.findByTraveler(user);
            for (int i = 0; i < orderList.size(); i++) {
                Order order = orderList.get(i);
                if (order.getOrderStatus() == 1 || order.getOrderStatus() == 6 || order.getOrderStatus() == 0 || order.getOrderStatus() == 2) {
                    return "redirect:/center";
                }
                WebUtils.setSessionAttribute(request, "ordersstatus", order.getOrderStatus());
            }
        }
        // 获得国家
        Country countryList = _countryRepository.findByName("美国");
        model.addAttribute("countryList", countryList);

        // 获得城市
        List<City> cityList = _cityRepository.findByCountryId(2);
        model.addAttribute("cityList", cityList);

        // 出行目的
        List<Purpose> purpostList = _purpostRepository.findAll();
        model.addAttribute("purpostList", purpostList);
        for (int i = 0; i < purpostList.size(); i++) {
        }
        // 随行人员
        List<AccompanyType> accompanyList = _accompanyTypeRepository.findAll();
        model.addAttribute("accompanyList", accompanyList);

        // 旅行主题
        List<Theme> themeList = _themeRepository.findAll();
        model.addAttribute("themeList", themeList);

        // 出发城市
        List<City> startCityList = _cityRepository.findAll();
        model.addAttribute("startCityList", startCityList);

        // 感兴趣城市
        List<City> inCityList = _cityRepository.findByCountryId(2);
        model.addAttribute("inCityList", inCityList);

        // 城市间交通
        List<Code> innerCity = _codeRepository.findByTypeOrderByValueAsc("交通方式");
        model.addAttribute("innerCityList", innerCity);

        // 酒店标准
        List<Code> hotelList = _codeRepository.findByTypeOrderByValueAsc("酒店");
        for (int i = 0; i < hotelList.size(); i++) {
        }
        model.addAttribute("hotelList", hotelList);

        // 航班标准
        List<Code> flightList = _codeRepository.findByTypeOrderByValueAsc("航班标准");
        model.addAttribute("flightList", flightList);

        // 租车喜好
        List<Code> rentCarList = _codeRepository.findByTypeOrderByValueAsc("租车喜好");
        model.addAttribute("rentCarList", rentCarList);

        return "/trip/index";
    }

    @ResponseBody
    @RequestMapping(value = "/doCreate", method = RequestMethod.POST)
    public String doCreate(@Valid TripPlan tripPlan, @RequestParam("userId") String userId, HttpServletRequest request,HttpSession session)
    {
      
        
        if(userId=="" || userId==null){
        	Integer tripPlanId=Integer.parseInt(request.getParameter("tripPlanId"));
        	tripPlan.setId(tripPlanId);
        	System.out.println(tripPlan.getId());
            WebUtils.setSessionAttribute(request, "returnUrlTrip", "/trips");
            WebUtils.setSessionAttribute(request, "tripPlan", tripPlan);
        	System.out.println(tripPlan.getId());
            return "/user/login";
        }else{
            Integer userIds=Integer.parseInt(userId);
      
        User userInfo = _userRepository.findOne(userIds);
        // 游客订单
        Order tra = new Order();
        // 游客信息
        if (userInfo != null)
        {
            tra.setTraveler(userInfo);
        }
        Integer maxId= _OrderRepository.getMaxId();
       
        if(maxId==null){
            maxId = 1;
        }else{
            maxId= _OrderRepository.getMaxId()+1;
        }
        Date date = new Date();
        SimpleDateFormat sif = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sif.format(date);
        // 订单号
        tra.setOrderNumber("TP" + format + maxId);

        WebUtils.setSessionAttribute(request, "orderNumber", tra.getOrderNumber());
        Integer dateConfi = Integer.parseInt(tripPlan.getTripPlan());
        if (dateConfi != null)
        {
            // 日期明确
            tra.setDateConfirm(dateConfi);
            if (dateConfi == 0)
            {
                String startDate = tripPlan.getStartDate();
                List<Date> startDateList = getDate(startDate);
                // 起始日期
                tra.setStartDate(startDateList.get(0));
                // 结束日期
                tra.setEndDate(startDateList.get(1));
            }
            else if (dateConfi == 1)
            {
                String noDate = tripPlan.getNoDate();
                List<Date> startDateList = getDate(noDate);
                // 起始日期
                tra.setStartDate(startDateList.get(0));
                // 结束日期
                tra.setEndDate(startDateList.get(1));
            }
        }
        // 游客主键

        // 游玩天数
        tra.setDuration(tripPlan.getPlayDay());
        // 订单状态
        tra.setOrderStatus(0);
        // 状态时间
        tra.setStatusTime(new Date());
        // 创建时间
        tra.setCreateTime(new Date());
        //查看明细金额
        tra.setAmountSetailStatus(0);
        // 订单备注
        tra.setRemark(tripPlan.getRemark());
        // 国家
        tra.setCountry(_countryRepository.findByName(tripPlan.getSelectCountry()));
        // 出行人数
        tra.setPersonCount(tripPlan.getPeopleNum());
        // 出发城市
        tra.setStartCity(_cityRepository.findByCityName(tripPlan.getStartCity()));
        //返回城市
        tra.setBackCity(_cityRepository.findByCityName(tripPlan.getBackCity()));
        // 酒店房间数
        tra.setHotelRoomCount(tripPlan.getHourseNum());
        // 兴趣城市状态
        tra.setInterestedCityStatus(tripPlan.getCityS());
        // 邀请码
        int authCodeA = (int) (Math.random() * 9000 + 1000);
        String authCode = "E" + authCodeA;

        tra.setActivityCode(authCode);
        // 邀请码状态
        tra.setActivityStatus(2);
        _OrderRepository.save(tra);
        /**
         * 出行目的
         */
        String[] tripA = tripPlan.getTripAim().split(",");
        for (int i = 0; i < tripA.length; i++) {
            PurposeRelate purposeRelate = new PurposeRelate();
            Purpose purpose = _purpostRepository.findByPurpose(tripA[i]);
            if (purpose != null) {
                purposeRelate.setOrder(tra);
                purposeRelate.setPurposeName(purpose.getPurpose());
                purposeRelate.setPurpose(purpose);
                purposeRelate.setCreateTime(new Date());
            } else {
                purposeRelate.setPurposeName(tripA[i]);
                purposeRelate.setCreateTime(new Date());
            }
            _purposeRelateRepository.save(purposeRelate);
        }
        
        
        //年龄
        
        String[] ageA = tripPlan.getMemberage().split(",");
        for (int i = 0; i < ageA.length; i++)
        {
//            AccompanyMemberAge  accompanyMemberAgea = _AccompanyMemberAge.findByAccompanymemberage(tripA[i]);
            AccompanyMemberAge accompanyMemberAge =new AccompanyMemberAge();
            accompanyMemberAge.setOrder(tra);
            accompanyMemberAge.setCreateTime(new Date());
            accompanyMemberAge.setAccompanymemberage(ageA[i]);
             _AccompanyMemberAge.save(accompanyMemberAge);
        }
        
        
        

        /**
         * 随行人员*
         */
        String[] entourage = tripPlan.getEntourage().split(",");
        for (int i = 0; i < entourage.length; i++)
        {
            AccompanyRelate accompany = new AccompanyRelate();
            AccompanyType accompanyType = _accompanyTypeRepository.findByAccompanyType(entourage[i]);
            if (accompanyType != null)
            {
                accompany.setAccompanyType(accompanyType.getAccompanyType());
                accompany.setAccompanyTypePk(accompanyType.getId());
                accompany.setOrderPk(tra.getId());
                accompany.setCreateTime(new Date());
            }
            else
            {
                accompany.setAccompanyType(entourage[i]);
                accompany.setCreateTime(new Date());
            }
            _accompanyRelateRepository.save(accompany);
        }
        /**
         * 主题
         */
        String[] tripObject = tripPlan.getTripObject().split(",");
        for (int i = 0; i < tripObject.length; i++) {
            ThemeRelate themeR = new ThemeRelate();
            Theme theme = _themeRepository.findByTheme(tripObject[i]);
            if (theme != null) {
                themeR.setTheme(theme);
                themeR.setOrder(tra);
                themeR.setThemeName(tripObject[i]);
                themeR.setCreateTime(new Date());
            } else {
                themeR.setDefineTheme(tripObject[i]);
                themeR.setThemeName(tripObject[i]);
                themeR.setCreateTime(new Date());
            }
            _themeRelateRepository.save(themeR);
        }
        /**
         * 规划方式
         */
        Integer plan = tripPlan.getPlan();
        if (plan != null)
        {
            // 必去城市状态
            tra.setConfirmedCityStatus(tripPlan.getPlan());
            if (plan == 1)
            {
                String[] mCity = tripPlan.getAddress().split(",");
                // 日期
                String[] times = tripPlan.getTime().split(",");
                
                //备注
                String[] beizhu=tripPlan.getBeizhu().split(",");
                
                String[] isService=tripPlan.getIsService().split(",");
            
                
                for (int i = 0; i < mCity.length; i++)
                {
                    CityConfirmed cityCo = new CityConfirmed();
                    City city = _cityRepository.findByCityName(mCity[i]);
                    if (city != null)
                    {
                       
                        List<Date> timeList = getDate(times[i]);
                        cityCo.setStartDate(timeList.get(0));
                        cityCo.setEndDate(timeList.get(1));
                        cityCo.setCity(city);
                        cityCo.setOrder(tra);
                        cityCo.setCreateTime(new Date());
                        cityCo.setArrangedStatus(0);
                        if(beizhu[i]==null || beizhu[i]==""){
                            cityCo.setRemark(beizhu[i]);
                        }else{
                            cityCo.setRemark(beizhu[i]);
                        }
                        
                        _cityConffirmedRepository.save(cityCo);
                    }
                }
            }
        }
        /**
         * 感兴趣城市
         */
        String[] ICity = tripPlan.getCityI().split(",");
        for (int i = 0; i < ICity.length; i++)
        {
            InterestedCityRelate intCity = new InterestedCityRelate();
            City city = _cityRepository.findByCityName(ICity[i]);
            if (city != null)
            {
                intCity.setCity(city);
                intCity.setCreateTime(new Date());
                intCity.setOrder(tra);
                _interestedCityRelateRepository.save(intCity);
            }
        }
        /**
         * 城市交通
         * 城市交通用的是城市内交通，表数据库表为"t_inner_city_traffic_relate",对应的实体为"InnerCityTrafficRelate".
         */
        //获取页面数据
        String citytype=request.getParameter("citytype");
        String selfDriver=request.getParameter("selfDriver");
        //实例化城市交通对象
        InnerCityTrafficRelate innerCityTrafficRelate = new InnerCityTrafficRelate();
        innerCityTrafficRelate.setInnerCityTraffic(citytype);
        innerCityTrafficRelate.setCreateTime(new Date());
        innerCityTrafficRelate.setOrderPk(tra.getId());
        innerCityTrafficRelate.setRemark(selfDriver);
        _innerCityTrafficRelateRepository.save(innerCityTrafficRelate);
        
        /**
         * 酒店标准
         */
        Integer hourseS = tripPlan.getHourseS();
        List<Code> code = _codeRepository.findByTypeOrderByValueAsc("酒店");
        for (int i = 0; i < code.size(); i++)
        {
            if (code.get(i).getValue() == hourseS)
            {
                CodeOrderRelate codeOrder = new CodeOrderRelate();
                codeOrder.setCodePk(code.get(i).getId());
                codeOrder.setOrderPk(tra.getId());
                codeOrder.setCreateTime(new Date());
                _codeOrderRelateRepotitory.save(codeOrder);
            }
        }

        /**
         * 航班问题
         */
        Integer flightS = tripPlan.getFlightS();
        List<Code> fcode = _codeRepository.findByTypeOrderByValueAsc("航班标准");
        for (int i = 0; i < fcode.size(); i++)
        {
            if (fcode.get(i).getValue() == flightS)
            {
                CodeOrderRelate codeOrder = new CodeOrderRelate();
                codeOrder.setCodePk(fcode.get(i).getId());
                codeOrder.setOrderPk(tra.getId());
                codeOrder.setCreateTime(new Date());
                _codeOrderRelateRepotitory.save(codeOrder);
            }
        }
        /**
         * 租车标准
         */
        Integer rentCar = tripPlan.getRentCar();
        List<Code> rcode = _codeRepository.findByTypeOrderByValueAsc("租车喜好");
        for (int i = 0; i < rcode.size(); i++)
        {
            if (rcode.get(i).getValue() == rentCar)
            {
                CodeOrderRelate codeOrder = new CodeOrderRelate();
                codeOrder.setCodePk(rcode.get(i).getId());
                codeOrder.setOrderPk(tra.getId());
                codeOrder.setCreateTime(new Date());
                _codeOrderRelateRepotitory.save(codeOrder);
            }
        }
        }
        return "/center";
    }

    /**
     * 日期处理
     */
    public List<Date> getDate(String date)
    {
        // 2015年6月7日 至 2015年6月17日
        int indexOf = date.indexOf("至");
        String string = date.substring(0, indexOf);
        String string2 = date.substring(indexOf + 1);
        List<Date> list = new ArrayList<Date>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        try
        {

            Date parse = sdf.parse(string);
            Date parse1 = sdf.parse(string2);
            list.add(parse);
            list.add(parse1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam("authCode") String authCode, HttpServletRequest request, Model model)
    {

    	String orderNumber = (String)WebUtils.getSessionAttribute(request, "orderNumber");
    	Order order = _OrderRepository.findByOrderNumber(orderNumber);
    	String authCodeA = order.getActivityCode();
        if (authCodeA.equals(authCode))
        {
            order.setOrderStatus(1);
            order.setActivityStatus(1);
            _OrderRepository.save(order);
            return "输入正确";
        }
        else
        {
            return "请输入正确的邀请码";
        }
    }
    
    @RequestMapping(value = "/authCode", method = RequestMethod.POST)
    public String authCode(@Valid Order order, HttpServletRequest request, Model model)
    {

    	String orderNumber = order.getOrderNumber();
    	Order orderA = _OrderRepository.findByOrderNumber(orderNumber);
    	String authCodeA = order.getActivityCode();
    	String message = null;
        if (authCodeA.equals(orderA.getActivityCode()))
        {
            orderA.setOrderStatus(1);
            orderA.setActivityStatus(1);
            _OrderRepository.save(orderA);
            message="输入正确";
            return "/order/orderlist";
        }
        else
        {
            message="请输入正确的邀请码";
            return "/order/authCode";
        }
    }


    /**
     * 微信用户的我的
     *
     * @param request
     * @param session
     * @return
     * @author limin.tony@x2our.com
     */
    @RequestMapping(value = "/wechattrips", method = RequestMethod.GET)
    public String wechattrips(HttpServletRequest request, HttpSession session) {
        String openId = request.getParameter("openId");
        User user = _userRepository.findByWechat(openId);
        if (user != null) {
            WebUtils.setSessionAttribute(request, "user", user);
            WebUtils.setSessionAttribute(request, "usertyoe", user.getUserType());
            WebUtils.setSessionAttribute(request, "usertyoes", user.getFirstName() + user.getLastName());
        }
       // return "redirect:/trips?id=1";
        return "redirect:/myinformation/baby";
    }
    /**
     * 微信用户的评估
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/wechatassement", method = RequestMethod.GET)
    public String wechatassement(HttpServletRequest request, HttpSession session) {
        String openId = request.getParameter("openId");
        User user = _userRepository.findByWechat(openId);
        if (user != null) {
            WebUtils.setSessionAttribute(request, "user", user);
            WebUtils.setSessionAttribute(request, "usertyoe", user.getUserType());
            WebUtils.setSessionAttribute(request, "usertyoes", user.getFirstName() + user.getLastName());
        }
       // return "redirect:/trips?id=1";
        return "redirect:/assessment/list";
    }
    
    /**
     * 微信用户的任务
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/wechatproject", method = RequestMethod.GET)
    public String wechatproject(HttpServletRequest request, HttpSession session) {
        String openId = request.getParameter("openId");
        User user = _userRepository.findByWechat(openId);
        WeChatUser weChatUser = weChatUserRepository.findByOpenid(openId);
        if (user != null) {
            WebUtils.setSessionAttribute(request, "user", user);
            WebUtils.setSessionAttribute(request, "usertyoe", user.getUserType());
            WebUtils.setSessionAttribute(request, "usertyoes", user.getFirstName() + user.getLastName());
            WebUtils.setSessionAttribute(request, "userwechat",weChatUser.getHeadimgurl());
        }
        List<Child> childlist = _cChildRepository.findByUser(user);
		if (childlist.size() > 0) {
			WebUtils.setSessionAttribute(request, "childids",childlist.get(0).getId());
			 return "redirect:/project/li/"+childlist.get(0).getId();
		} else {
			WebUtils.setSessionAttribute(request, "childids", "");
			 return "redirect:/project/li";
		}
       // return "redirect:/trips?id=1";
       
    }
    /**
     * 微信用户的任务
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/wechatcommodity", method = RequestMethod.GET)
    public String wechatcommodity(HttpServletRequest request, HttpSession session) {
        String openId = request.getParameter("openId");
        User user = _userRepository.findByWechat(openId);
        if (user != null) {
            WebUtils.setSessionAttribute(request, "user", user);
            WebUtils.setSessionAttribute(request, "usertyoe", user.getUserType());
            WebUtils.setSessionAttribute(request, "usertyoes", user.getFirstName() + user.getLastName());
            List<Child> childlist = _cChildRepository.findByUser(user);
            //WebUtils.setSessionAttribute(request, "childids",childlist.get(0).getId());
			 return "redirect:/commodity/index/"+childlist.get(0).getId();
    		/*if (childlist.size() > 0) {
    			 WebUtils.setSessionAttribute(request, "childids",childlist.get(0).getId());
    			 return "redirect:/commodity/index/"+childlist.get(0).getId();
    		} else {
    			 WebUtils.setSessionAttribute(request, "childids","");
    			 return "redirect:/commodity/index";
    		}*/
        }
       
       return "redirect:/trips?id=1";
       
    }
    /**
     * 微信用户课程
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/wechatcourse", method = RequestMethod.GET)
    public String wechatcourse(HttpServletRequest request, HttpSession session) {
    	 String openId = request.getParameter("openId");
         User user = _userRepository.findByWechat(openId);
         if (user != null) {
             WebUtils.setSessionAttribute(request, "user", user);
             WebUtils.setSessionAttribute(request, "usertyoe", user.getUserType());
             WebUtils.setSessionAttribute(request, "usertyoes", user.getFirstName() + user.getLastName());
         }
        return "redirect:/course/index";
    }
    /**
     * 微信用户活动
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/wechatactivity", method = RequestMethod.GET)
    public String wechatactivity(HttpServletRequest request, HttpSession session) {
    	 String openId = request.getParameter("openId");
         User user = _userRepository.findByWechat(openId);
         if (user != null) {
             WebUtils.setSessionAttribute(request, "user", user);
             WebUtils.setSessionAttribute(request, "usertyoe", user.getUserType());
             WebUtils.setSessionAttribute(request, "usertyoes", user.getFirstName() + user.getLastName());
         }
        return "redirect:/activity/index";
    }
    
/*
 * 自由定制页面
 */
	@RequestMapping(value="/planofferlist",method = RequestMethod.GET)
     public String travel(HttpServletRequest request,Model model){
		return "/trip/planofferlist";
	}
	
	/*
	 * 路书（样例）
	 */
	@RequestMapping(value="/journey",method = RequestMethod.GET)
	public String oldtimeline(HttpServletRequest request,Model model){
		return "/trip/journey";
	   // return "/googlemap/mapjspolyline";
	}
	
    /**
     * 保存之前用户填写的详细旅游规划信息，放入表中，然后跳转到去结算页面
     * @return 跳转到详细规划的
     */
    @ResponseBody
    @RequestMapping(value = "/dosettlement", method = RequestMethod.POST)
 public String dosettlement(@Valid TripPlan tripPlan, @RequestParam("userId") String userId, HttpServletRequest request,HttpSession session,Model model){
    	
    	 if(userId=="" || userId==null){
    		 Integer id=Integer.parseInt(request.getParameter("id"));
    		 tripPlan.setId(id);
             WebUtils.setSessionAttribute(request, "returnUrlTrip", "/trips");
             WebUtils.setSessionAttribute(request, "tripPlan", tripPlan);
             return "/user/login";
         }else{
        	 
     
        	  Integer userIds=Integer.parseInt(userId);
             
             User userInfo = _userRepository.findOne(userIds);
             // 游客订单
             Order tra = new Order();
             // 游客信息
             if (userInfo != null)
             {
                 tra.setTraveler(userInfo);
             }
             Integer maxId= _OrderRepository.getMaxId();
            
             if(maxId==null){
                 maxId = 1;
             }else{
                 maxId= _OrderRepository.getMaxId()+1;
             }
             Date date = new Date();
             SimpleDateFormat sif = new SimpleDateFormat("yyyyMMddHHmmss");
             String format = sif.format(date);
             // 订单号
             tra.setOrderNumber("TP" + format + maxId);

             WebUtils.setSessionAttribute(request, "orderNumber", tra.getOrderNumber());
             Integer dateConfi = Integer.parseInt(tripPlan.getTripPlan());
             if (dateConfi != null)
             {
                 // 日期明确
                 tra.setDateConfirm(dateConfi);
                 if (dateConfi == 0)
                 {
                     String startDate = tripPlan.getStartDate();
                     List<Date> startDateList = getDate(startDate);
                     // 起始日期
                     tra.setStartDate(startDateList.get(0));
                     // 结束日期
                     tra.setEndDate(startDateList.get(1));
                 }
                 else if (dateConfi == 1)
                 {
                     String noDate = tripPlan.getNoDate();
                     List<Date> startDateList = getDate(noDate);
                     // 起始日期
                     tra.setStartDate(startDateList.get(0));
                     // 结束日期
                     tra.setEndDate(startDateList.get(1));
                 }
             }
             // 游客主键

             // 游玩天数
             tra.setDuration(tripPlan.getPlayDay());
             // 订单状态
             tra.setOrderStatus(0);
             // 状态时间
             tra.setStatusTime(new Date());
             // 创建时间
             tra.setCreateTime(new Date());
             //查看明细金额
             tra.setAmountSetailStatus(0);
             // 订单备注
             tra.setRemark(tripPlan.getRemark());
             // 国家
             tra.setCountry(_countryRepository.findByName(tripPlan.getSelectCountry()));
             // 出行人数
             tra.setPersonCount(tripPlan.getPeopleNum());
             // 出发城市
             tra.setStartCity(_cityRepository.findByCityName(tripPlan.getStartCity()));
             //返回城市
             tra.setBackCity(_cityRepository.findByCityName(tripPlan.getBackCity()));
             // 酒店房间数
             tra.setHotelRoomCount(tripPlan.getHourseNum());
             // 兴趣城市状态
             tra.setInterestedCityStatus(tripPlan.getCityS());
             // 邀请码
             int authCodeA = (int) (Math.random() * 9000 + 1000);
             String authCode = "E" + authCodeA;

             tra.setActivityCode(authCode);
             // 邀请码状态
             tra.setActivityStatus(1);
//             tra.setOrderAmount(BigDecimal.valueOf(1));
             _OrderRepository.save(tra);
             
             model.addAttribute("order", tra);
          
             
             PlanOrder po=new PlanOrder();
             po.setAmount(BigDecimal.valueOf(0.01));
             po.setOrder(tra);
             po.setCreateTime(new Date());
             
            Integer id = Integer.parseInt(request.getParameter("id"));
             if(id == 1){
             po.setPlanOfferName("行程规划");
             }else if(id==2 || tripPlan.getId().equals(2)){
            	 po.setPlanOfferName("行程规划+一站预定");
             }else if(id==3 || tripPlan.getId().equals(3)){
            	 po.setPlanOfferName("行程规划+一站预定+导游随行");
             }
             _planOrderRepository.save(po);
             
             
             /**
              * 出行目的
              */
             String[] tripA = tripPlan.getTripAim().split(",");
             for (int i = 0; i < tripA.length; i++) {
                 PurposeRelate purposeRelate = new PurposeRelate();
                 Purpose purpose = _purpostRepository.findByPurpose(tripA[i]);
                 if (purpose != null) {
                     purposeRelate.setOrder(tra);
                     purposeRelate.setPurposeName(purpose.getPurpose());
                     purposeRelate.setPurpose(purpose);
                     purposeRelate.setCreateTime(new Date());
                 } else {
                     purposeRelate.setPurposeName(tripA[i]);
                     purposeRelate.setCreateTime(new Date());
                 }
                 _purposeRelateRepository.save(purposeRelate);
             }
             
             
             //年龄
             
             String[] ageA = tripPlan.getMemberage().split(",");
             for (int i = 0; i < ageA.length; i++)
             {
//                 AccompanyMemberAge  accompanyMemberAgea = _AccompanyMemberAge.findByAccompanymemberage(tripA[i]);
                 AccompanyMemberAge accompanyMemberAge =new AccompanyMemberAge();
                 accompanyMemberAge.setOrder(tra);
                 accompanyMemberAge.setCreateTime(new Date());
                 accompanyMemberAge.setAccompanymemberage(ageA[i]);
                  _AccompanyMemberAge.save(accompanyMemberAge);
             }
             
             
             

             /**
              * 随行人员*
              */
             String[] entourage = tripPlan.getEntourage().split(",");
             for (int i = 0; i < entourage.length; i++)
             {
                 AccompanyRelate accompany = new AccompanyRelate();
                 AccompanyType accompanyType = _accompanyTypeRepository.findByAccompanyType(entourage[i]);
                 if (accompanyType != null)
                 {
                     accompany.setAccompanyType(accompanyType.getAccompanyType());
                     accompany.setAccompanyTypePk(accompanyType.getId());
                     accompany.setOrderPk(tra.getId());
                     accompany.setCreateTime(new Date());
                 }
                 else
                 {
                     accompany.setAccompanyType(entourage[i]);
                     accompany.setCreateTime(new Date());
                 }
                 _accompanyRelateRepository.save(accompany);
             }
             /**
              * 主题
              */
             String[] tripObject = tripPlan.getTripObject().split(",");
             for (int i = 0; i < tripObject.length; i++) {
                 ThemeRelate themeR = new ThemeRelate();
                 Theme theme = _themeRepository.findByTheme(tripObject[i]);
                 if (theme != null) {
                     themeR.setTheme(theme);
                     themeR.setOrder(tra);
                     themeR.setThemeName(tripObject[i]);
                     themeR.setCreateTime(new Date());
                 } else {
                     themeR.setDefineTheme(tripObject[i]);
                     themeR.setThemeName(tripObject[i]);
                     themeR.setCreateTime(new Date());
                 }
                 _themeRelateRepository.save(themeR);
             }
             /**
              * 规划方式
              */
             Integer plan = tripPlan.getPlan();
             if (plan != null)
             {
                 // 必去城市状态
                 tra.setConfirmedCityStatus(tripPlan.getPlan());
                 if (plan == 1)
                 {
                     String[] mCity = tripPlan.getAddress().split(",");
                     // 日期
                     String[] times = tripPlan.getTime().split(",");
                     
                     //备注
                     String[] beizhu=tripPlan.getBeizhu().split(",");
                     
                     String[] isService=tripPlan.getIsService().split(",");
                 
                     
                     for (int i = 0; i < mCity.length; i++)
                     {
                         CityConfirmed cityCo = new CityConfirmed();
                         City city = _cityRepository.findByCityName(mCity[i]);
                         if (city != null)
                         {
                            
                             List<Date> timeList = getDate(times[i]);
                             cityCo.setStartDate(timeList.get(0));
                             cityCo.setEndDate(timeList.get(1));
                             cityCo.setCity(city);
                             cityCo.setOrder(tra);
                             cityCo.setCreateTime(new Date());
                             cityCo.setArrangedStatus(0);
                             if(beizhu[i]==null || beizhu[i]==""){
                                 cityCo.setRemark(beizhu[i]);
                             }else{
                                 cityCo.setRemark(beizhu[i]);
                             }
                             
                             _cityConffirmedRepository.save(cityCo);
                         }
                     }
                 }
             }
             /**
              * 感兴趣城市
              */
             String[] ICity = tripPlan.getCityI().split(",");
             for (int i = 0; i < ICity.length; i++)
             {
                 InterestedCityRelate intCity = new InterestedCityRelate();
                 City city = _cityRepository.findByCityName(ICity[i]);
                 if (city != null)
                 {
                     intCity.setCity(city);
                     intCity.setCreateTime(new Date());
                     intCity.setOrder(tra);
                     _interestedCityRelateRepository.save(intCity);
                 }
             }
             /**
              * 城市交通
              * 城市交通用的是城市内交通，表数据库表为"t_inner_city_traffic_relate",对应的实体为"InnerCityTrafficRelate".
              */
             //获取页面数据
             String citytype=request.getParameter("citytype");
             String selfDriver=request.getParameter("selfDriver");
             //实例化城市交通对象
             InnerCityTrafficRelate innerCityTrafficRelate = new InnerCityTrafficRelate();
             innerCityTrafficRelate.setInnerCityTraffic(citytype);
             innerCityTrafficRelate.setCreateTime(new Date());
             innerCityTrafficRelate.setOrderPk(tra.getId());
             innerCityTrafficRelate.setRemark(selfDriver);
             _innerCityTrafficRelateRepository.save(innerCityTrafficRelate);
             
             /**
              * 酒店标准
              */
             Integer hourseS = tripPlan.getHourseS();
             List<Code> code = _codeRepository.findByTypeOrderByValueAsc("酒店");
             for (int i = 0; i < code.size(); i++)
             {
                 if (code.get(i).getValue() == hourseS)
                 {
                     CodeOrderRelate codeOrder = new CodeOrderRelate();
                     codeOrder.setCodePk(code.get(i).getId());
                     codeOrder.setOrderPk(tra.getId());
                     codeOrder.setCreateTime(new Date());
                     _codeOrderRelateRepotitory.save(codeOrder);
                 }
             }

             /**
              * 航班问题
              */
             Integer flightS = tripPlan.getFlightS();
             List<Code> fcode = _codeRepository.findByTypeOrderByValueAsc("航班标准");
             for (int i = 0; i < fcode.size(); i++)
             {
                 if (fcode.get(i).getValue() == flightS)
                 {
                     CodeOrderRelate codeOrder = new CodeOrderRelate();
                     codeOrder.setCodePk(fcode.get(i).getId());
                     codeOrder.setOrderPk(tra.getId());
                     codeOrder.setCreateTime(new Date());
                     _codeOrderRelateRepotitory.save(codeOrder);
                 }
             }
             /**
              * 租车标准
              */
             Integer rentCar = tripPlan.getRentCar();
             List<Code> rcode = _codeRepository.findByTypeOrderByValueAsc("租车喜好");
             for (int i = 0; i < rcode.size(); i++)
             {
                 if (rcode.get(i).getValue() == rentCar)
                 {
                     CodeOrderRelate codeOrder = new CodeOrderRelate();
                     codeOrder.setCodePk(rcode.get(i).getId());
                     codeOrder.setOrderPk(tra.getId());
                     codeOrder.setCreateTime(new Date());
                     _codeOrderRelateRepotitory.save(codeOrder);
                 }
             }
             
             model.addAttribute("tripPlan", tripPlan);
             
             return "/detailtrip/tosettlement?orderid="+tra.getId()+"&id="+tripPlan.getId();
             }
    	 
         }
    /**
     * 跳转页面
     * @param order
     * @param request
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/helpplan", method = RequestMethod.GET)
    public String tripHelpPlan(@ModelAttribute Order order,HttpServletRequest request,HttpSession session,Model model) {
      // User user = (User) WebUtils.getSessionAttribute(request, "user");
       /*
        * 套餐id
        */
       Integer id = Integer.parseInt(request.getParameter("id"));
       model.addAttribute("id",id);
       /* User user=(User)session.getAttribute("user");
        Order order = new Order();
        order.setTraveler(user);
        model.addAttribute("order", order);*/
        return "/trip/helpplan";
    }
    /**
     * 保存快速规划的信息
     * @param request
     * @param session
     * @param model
     * @return
     */
	@RequestMapping(value = "/svaehelpplan", method = RequestMethod.POST)
	public String tripSaveHelpPlan(HttpSession session,Model model, HttpServletRequest request) {
		/*order.getStartDate();
		order.getDuration();*/
		/* //得到他之前是否有进行过快速规划
        String svaehelpplan = (String) session.getAttribute("svaehelpplan");
        if(svaehelpplan!=null){
	        if(svaehelpplan=="/svaehelpplan" || svaehelpplan.equals("/svaehelpplan")){
	        	Order order=(Order)session.getAttribute("order");
	        	_OrderRepository.save(order);
	        	return "redirect:/detailtrip/tosettlement";
	        }
        }*/
		SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
	
		User user=(User)session.getAttribute("user");
		//日期
		String startdate =  request.getParameter("startdate");
		//天数
		Integer duration =Integer.parseInt(request.getParameter("duration"));
		
		Integer id=Integer.parseInt(request.getParameter("id"));
				
		//long a = sdf.parse(startdate).getTime();会报错不懂
		Order order = new Order();
	
			try {
				SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				order.setStartDate(sdf.parse(startdate));
				long enddatelong= order.getStartDate().getTime();
				long duration1 = Long.valueOf(duration);
				long endmiao = duration1*86400000;
				enddatelong=enddatelong+endmiao;
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(enddatelong);
				String enddate = formatter.format(calendar.getTime());
				order.setEndDate(sdf1.parse(enddate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		order.setTraveler(user);
		order.setDuration(duration);
		order.setDateConfirm(0);
		order.setCreateTime(new Date());
		
		
		Integer maxId= _OrderRepository.getMaxId();
		
		if(maxId==null){
			maxId = 1;
		}else{
			maxId= _OrderRepository.getMaxId()+1;
		}
		Date date = new Date();
		SimpleDateFormat sif = new SimpleDateFormat("yyyyMMddHHmmss");
		String format = sif.format(date);
		// 订单号
		order.setOrderNumber("TP" + format + maxId);
		//订单状态
		order.setOrderStatus(1);
		//日期明确
		order.setDateConfirm(1);
		//人数是一个
		order.setPersonCount(1);
		
		_OrderRepository.save(order);
//		 return "redirect:/helpplan";
		PlanOrder po=new PlanOrder();
        po.setAmount(BigDecimal.valueOf(0.01));
        po.setOrder(order);
        po.setCreateTime(new Date());
        if(id == 1){
        po.setPlanOfferName("行程规划");
        }else if(id==2){
       	 po.setPlanOfferName("行程规划+一站预定");
        }else if(id==3){
       	 po.setPlanOfferName("行程规划+一站预定+导游随行");
        }
        _planOrderRepository.save(po);
        if(user==null){
            //order.setPackageId(id);//暂时存id
            WebUtils.setSessionAttribute(request, "svaehelpplan", "/svaehelpplan");
            WebUtils.setSessionAttribute(request, "order", order);
            WebUtils.setSessionAttribute(request, "id", id);
            return "/user/login";
        }
		return "redirect:/detailtrip/tosettlement?orderid="+order.getId()+"0&id="+id;
		}
	
	
	
	  @RequestMapping(value = "/tourreservation", method = RequestMethod.GET)
	    public String tourreservation(Model model,HttpServletRequest request){
	      List<City> city=_cityRepository.findByCountryName("美国");
	      model.addAttribute("city", city);
	      GuideRate guiderate1=_guideRateRepository.findByGuideTypes("徒步导游");
	      GuideRate guiderate2=_guideRateRepository.findByGuideTypes("司机兼导游");
	      GuideRate guiderate3=_guideRateRepository.findByGuideTypes("小费");
	      model.addAttribute("guiderate1", guiderate1);
	      model.addAttribute("guidetype2", guiderate2);
	      model.addAttribute("guidetype3", guiderate3);
	        return "/trip/tourreservation";
	    }
	  
	  @RequestMapping(value = "/guideorder", method = RequestMethod.POST)
	  public String guideorder(Model model,HttpServletRequest request,HttpSession session) throws ParseException{
	      GuideOrder guideorder =new GuideOrder();
	      //目的城市
	      System.out.println(request.getParameter("endCity"));
	      Integer ids = Integer.parseInt(request.getParameter("endCity"));
          City city=_cityRepository.findOne(ids);
	      //导游类型
	      String guidetype= request.getParameter("guidetype");
	      //出行日期
	      SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
	      Date startdate =sdf.parse(request.getParameter("startdate"));
	      //出行人数
	      Integer personcount =Integer.parseInt(request.getParameter("personcount"));
	      //出行天数
	      Integer duration=Integer.parseInt(request.getParameter("duration"));
	      //导游人数
	      Integer guidecount =Integer.parseInt(request.getParameter("guidecount"));
	      //导游服务费
	      String  guideserviceammount=request.getParameter("guideserviceammount");
	     //导游小费
	      String guidetipsammount =request.getParameter("guidetipsammount");
	      
	      //
	      BigDecimal guideserviceammount1=new BigDecimal(guideserviceammount);
	      BigDecimal guidetipsammount1=new BigDecimal(guidetipsammount);
	      User user=(User)session.getAttribute("user");
	      guideorder.setCity(city);
	      guideorder.setGuidetype(guidetype);
	      guideorder.setStartdate(startdate);
	      guideorder.setPersoncount(personcount);
	      guideorder.setDuration(duration);
	      guideorder.setGuidecount(guidecount);
	      guideorder.setGuideserviceammount(guideserviceammount1);
	      guideorder.setGuidetipsammount(guidetipsammount1);
	      guideorder.setCreatetime(new Date());
	      guideorder.setOrderstatus(1);
	      guideorder.setAppointedstatus(0);
	      guideorder.setOrderamount(guidetipsammount1.add(guideserviceammount1));
	      SimpleDateFormat sdf1=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	        Integer id=Integer.parseInt(request.getParameter("id"));
	        if(guideorder.getGuidetype().equals("徒步导游")){
	            id=4;
	        }else if(guideorder.getGuidetype().equals("司机兼导游")){
	            id=5;
	        }
	        Date date = new Date();
	        SimpleDateFormat sif = new SimpleDateFormat("yyyyMMddHHmmss");
	        String format = sif.format(date);
	        guideorder.setGuideordernum("GO"+format);
	        guideorder.setUser(user);
	        _guideOrderRepository.save(guideorder);
	        WebUtils.setSessionAttribute(request, "guideorder", guideorder);
	        if(user==null){
                WebUtils.setSessionAttribute(request, "id", id);
                return "/user/login";
            }
	      return "redirect:/detailtrip/tosettlementguide?id="+id;
	  }
}
