package com.jzeen.travel.admin.controller.guide;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.GuideActivity;
import com.jzeen.travel.data.entity.GuideActivityPlan;
import com.jzeen.travel.data.entity.GuideRate;
import com.jzeen.travel.data.entity.GuideTypePlan;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.QGuideRate;
import com.jzeen.travel.data.entity.QStandardGuidePlan;
import com.jzeen.travel.data.entity.StandardGuidePlan;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.GuideActivityPlanRepository;
import com.jzeen.travel.data.repository.GuideActivityRepository;
import com.jzeen.travel.data.repository.GuideRateRepository;
import com.jzeen.travel.data.repository.GuideTypePlanRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.StandardGuideOccupiedRepository;
import com.jzeen.travel.data.repository.StandardGuidePlanRepository;
import com.jzeen.travel.data.repository.StandardGuideRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.service.OrderService;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;

@Controller
@RequestMapping("/standardGuidePlan")
public class StandardGuidePlanController
{
    @Autowired
    private StandardGuidePlanRepository _standardGuidePlanRepository;
    
    @Autowired
    private StandardGuideOccupiedRepository _standardGuideOccupiedRepository;
    
    @Autowired
    private UserRepository _userRepository;
    
    @Autowired
    private StandardGuideRepository _standardGuideRepository;
    
    @Autowired
    private OrderRepository _orderRepository;
    
    @Autowired
    private OrderService _orderService;
    
    @Autowired
    private CityRepository _cityRepository;
    
    @Autowired
    private DatePlanRepository _datePlanRepository;
    
    @Autowired
    private GuideTypePlanRepository _guideTypePlanRepository;
    
    @Autowired
    private GuideRateRepository _guideRateRepository;
    
    @Autowired
    private GuideActivityRepository _guideActivityRepository;
    
    @Autowired
    private GuideActivityPlanRepository _guideActivityPlanRepository;
    

//    @ResponseBody
//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public Iterable<StandardGuidePlan> search(HttpServletRequest request)
//    {
//        // 使用QueryDsl构造查询条件
//        Integer orderId = Integer.parseInt(request.getParameter("orderId"));
//        QStandardGuidePlan standardGuidePlan = QStandardGuidePlan.standardGuidePlan;
//        Predicate predicate = standardGuidePlan.datePlan.order.id.eq(orderId);
//
//        Iterable<StandardGuidePlan> data = _standardGuidePlanRepository.findAll(predicate);
//
//        return data;
//    }
    
//    @RequestMapping(value="/edit/{id}" ,method=RequestMethod.GET)
//    public String editInit(@PathVariable Integer id, Model model){
//        
//        //暂时固定DatePlan的Id为185
//        DatePlan datePlan= _datePlanRepository.findOne(184);
//        //查到Order(订单)，根据OrderId
//        Order order = _orderRepository.findOne(datePlan.getOrder().getId());
//        model.addAttribute("order", order);
//        // 城市间交通
//        model.addAttribute("citysTrafficStr", _orderService.getCitysTrafficByOrderId(order.getId()));
//        //年龄
//        model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));
//        //查询所有美国城市2为美国的城市，4为中国的城市
//        model.addAttribute("city", _cityRepository.findByCountryId(2));
//        //根据用户的类型和是否愿意成为临时导游查询
//        model.addAttribute("userType", _userRepository.findByUserTypeAndExcursionType(2, 1));
//        List<User> uu= (List<User>) _userRepository.findByUserTypeAndExcursionType(2, 1);
//        
//        for(int i=0;i<uu.size();i++){
//        System.out.println(uu.get(i).getFirstName()+uu.get(i).getLastName()+"\t"+uu.get(i).getId());
//        }
//        return "/standardguide/editStandardGuide";
//    }
    
    /**
     * 跳转到新增临时导游或标准导游的页面
     * @return  跳转到
     */
    @RequestMapping(value="/creates" , method=RequestMethod.GET)
    public String createsInit(HttpServletRequest request,Model model)
    {
        
        //@RequestParam("dataPlanId") Integer dataPlanId,
        Integer datePlanId=Integer.parseInt(request.getParameter("datePlanId"));
        
        
        DatePlan datePlan= _datePlanRepository.findOne(datePlanId);
        model.addAttribute("datePlan", datePlan);
        //查到Order(订单)，根据OrderId
        Order order = _orderRepository.findOne(datePlan.getOrder().getId());
        model.addAttribute("order", order);
        // 城市间交通
        model.addAttribute("citysTrafficStr", _orderService.getCitysTrafficByOrderId(order.getId()));
        //年龄
        model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));
        //查询所有美国城市2为美国的城市，4为中国的城市
        model.addAttribute("city", _cityRepository.findByCountryId(2));
        
        //导游活动
        model.addAttribute("guideActivity", _guideActivityRepository.findAll());
        
       model.addAttribute("guideRate", _guideRateRepository.findAll());
        return "/guideplan/create";
    }
    
    /**
     * 新增导游信息
     */
    @RequestMapping(value="creates",method=RequestMethod.POST)
    public String creates(@RequestParam("orderId") Integer orderId1,HttpServletRequest request,Model model){
        //根据订单ID查找订单
        Integer orderId=Integer.parseInt(request.getParameter("orderId"));
        //获取规划订单(datePlan)的ID
        Integer datePlanId=Integer.parseInt(request.getParameter("datePlanId"));
        DatePlan datePlan=_datePlanRepository.findById(datePlanId);
        
        Integer cityId=Integer.parseInt(request.getParameter("cityId"));
        City city=_cityRepository.findOne(cityId);
        String guideActivityType=request.getParameter("guideActivityType");
        
        GuideActivity guideActivity=_guideActivityRepository.findByCityAndGuideActivityType(city, guideActivityType);
        
        //导游活动规划保存
        GuideActivityPlan guideActivityPlan=new GuideActivityPlan();
        guideActivityPlan.setGuideActity(guideActivity);
        guideActivityPlan.setDatePlan(datePlan);
        guideActivityPlan.setGuideActivityType(guideActivity.getGuideActivityType());
        guideActivityPlan.setCity(guideActivity.getCity());
        guideActivityPlan.setUpdateTime(new Date());
        guideActivityPlan.setGuideCount(0);
        guideActivityPlan.setSubTotalAmount(BigDecimal.ZERO);
        _guideActivityPlanRepository.save(guideActivityPlan);
        try
        {
            //将页面的多选信息分割
            String[] guideactivityIds=request.getParameter("guideactivityIds").split(",");
            String[] guideCounts=request.getParameter("guideCounts").split(",");
            String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");

            //循环保存到导游活动规划和导游类型规划中
            for (int i = 0; i < guideactivityIds.length; i++) {
                if(!guideCounts[i].equals("0")){
                    GuideRate guideRate = _guideRateRepository.findOne(new Integer(guideactivityIds[i]));
                    //导游类型规划保存
                    GuideTypePlan guideTypePlan=new GuideTypePlan();
                    guideTypePlan.setGuideActivityPlan(guideActivityPlan);
                    guideTypePlan.setGuideCost(guideRate.getGuideRateCost().multiply(new BigDecimal(guideCounts[i])));
                    guideTypePlan.setGuidePrice(guideRate.getGuideRatePrice().multiply(new BigDecimal(guideCounts[i])));
                    guideTypePlan.setGuideRate(guideRate);
                    guideTypePlan.setUpdateTime(new Date());
                    guideTypePlan.setGuideCount(Integer.parseInt(guideCounts[i]));
                    guideTypePlan.setGuideType(guideRate.getGuideType());
                    _guideTypePlanRepository.save(guideTypePlan);
                    
                    //因导游活动规划总价不正确，从新从导游类型规划中赋值
//                    guideActivityPlan.setSubTotalAmount(guideTypePlan.getGuidePrice());
                    guideActivityPlan.setSubTotalAmount(guideActivityPlan.getSubTotalAmount().add(guideTypePlan.getGuidePrice()));
                    guideActivityPlan.setGuideCount(guideActivityPlan.getGuideCount()+guideTypePlan.getGuideCount());
                    _guideActivityPlanRepository.save(guideActivityPlan);
                }
            }
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
            return "redirect:/order/" + orderId + "/plan?_step1";
        }
        return "redirect:/order/" + orderId + "/plan?_step1";
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/searchs", method = RequestMethod.GET)
    public Iterable<GuideRate> searchs(HttpServletRequest request,Model model){
      //城市ID
      Integer cityId=Integer.parseInt(request.getParameter("cityId"));
      //导游类型
      String guideActivityType=request.getParameter("guideActivityType");
      
      //判断查询
      if(cityId==-1 && guideActivityType.equals("-1")){
          Iterable<GuideRate> guideRates = null;
          return guideRates;
      }else{
          QGuideRate guideRate = QGuideRate.guideRate;
          BooleanExpression predicate=guideRate.isNotNull();
          if(!guideActivityType.equals("-1")){
              predicate=predicate.and(guideRate.guideActivity.guideActivityType.eq(guideActivityType));
          }
          if(cityId!=-1){
              City city= _cityRepository.findOne(cityId);
              predicate=predicate.and(guideRate.guideActivity.city.eq(city));
          }
          Iterable<GuideRate> guideRates= _guideRateRepository.findAll(predicate);
          return guideRates;
      }
      }
    
    /**
     * 跳转到导游活动规划修改页面
     */
    @RequestMapping(value="/update" , method=RequestMethod.GET)
    public String updateInit(HttpServletRequest request,Model model){
        
        //根据导游活动规划ID查询导游活动信息
        Integer guideActivityPlanId=Integer.parseInt(request.getParameter("guideActivityPlanId"));
        
        Integer guideTypePlanId=Integer.parseInt(request.getParameter("guideTypePlans"));
        GuideTypePlan guideTypePlan=_guideTypePlanRepository.findOne(guideTypePlanId);
        model.addAttribute("guideTypePlan", guideTypePlan);
        model.addAttribute("guideTypePlanId", guideTypePlanId);
        
        //导游活动规划对象
        GuideActivityPlan guideActivityPlan= _guideActivityPlanRepository.findOne(guideActivityPlanId);
        model.addAttribute("guideActivityPlan", guideActivityPlan);
       
        //根据导游活动ID得到DatePlan对象
        DatePlan datePlan=_datePlanRepository.findOne(guideActivityPlan.getDatePlan().getId());
        model.addAttribute("datePlan", datePlan);
        
        //根据导游费率表得到导游活动对象
        List<GuideActivity> guideActivity=_guideActivityRepository.findAll();
        model.addAttribute("guideActivity", guideActivity);
        
      //导游活动
        model.addAttribute("guideActivitys", _guideActivityRepository.findAll());
        
        //查到Order(订单)，根据OrderId
        Order order = _orderRepository.findOne(datePlan.getOrder().getId());
        model.addAttribute("order", order);
        // 城市间交通
        model.addAttribute("citysTrafficStr", _orderService.getCitysTrafficByOrderId(order.getId()));
        //年龄
        model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(order.getId()));
        //查询所有美国城市2为美国的城市，4为中国的城市
        model.addAttribute("city", _cityRepository.findByCountryId(2));
        
        return "/guideplan/update";
    }
    
    /**
     * 导游活动规划修改
     * @return 生成规划页面
     */
    @RequestMapping(value="/update",method=RequestMethod.PUT)
    public String update(HttpServletRequest request,Model model){
        
        //日期规划ID
       Integer datePlanId=Integer.parseInt(request.getParameter("datePlanId"));
       
       //导游活动规划ID
       Integer guideActivityPlanId=Integer.parseInt(request.getParameter("guideActivityPlanId"));
       GuideActivityPlan guideActivityPlan=_guideActivityPlanRepository.findOne(guideActivityPlanId);
       
       //人数
       Integer guideCount=Integer.parseInt(request.getParameter("guideCount"));
       
       //导游类型规划
       Integer guideTypePlanId=Integer.parseInt(request.getParameter("guideTypePlanId"));
       GuideTypePlan guideTypePlan= _guideTypePlanRepository.findOne(guideTypePlanId);
       guideTypePlan.setGuideCount(guideCount);
       guideTypePlan.setGuidePrice(guideTypePlan.getGuideRate().getGuideRatePrice().multiply(BigDecimal.valueOf((guideCount))));
       guideTypePlan.setGuideCost(guideTypePlan.getGuideRate().getGuideRateCost().multiply(BigDecimal.valueOf((guideCount))));
       _guideTypePlanRepository.save(guideTypePlan);
       
       //根据导游活动ID得到DatePlan对象
       DatePlan datePlan= _datePlanRepository.findOne(datePlanId);
       
       guideActivityPlan.setGuideActity(guideActivityPlan.getGuideActity());
       guideActivityPlan.setDatePlan(datePlan);
       guideActivityPlan.setGuideActivityType(guideActivityPlan.getGuideActivityType());
       guideActivityPlan.setCity(guideActivityPlan.getCity());
       guideActivityPlan.setUpdateTime(new Date());
       guideActivityPlan.setGuideCount(0);
       guideActivityPlan.setSubTotalAmount(BigDecimal.ZERO);
       _guideActivityPlanRepository.save(guideActivityPlan);
       
       //累加数值给导游活动规划
       List<GuideTypePlan> guideTypePlanOld=_guideTypePlanRepository.findByGuideActivityPlan(guideActivityPlan);
       for(int i=0;i<guideTypePlanOld.size();i++){
           guideActivityPlan.setGuideCount(guideTypePlanOld.get(i).getGuideCount()+guideActivityPlan.getGuideCount());
           guideActivityPlan.setSubTotalAmount(guideTypePlanOld.get(i).getGuidePrice().add(guideActivityPlan.getSubTotalAmount()));
           _guideActivityPlanRepository.save(guideActivityPlan);
       }
       
       return "redirect:/order/" + datePlan.getOrder().getId() + "/plan?_step1";
    }
}
