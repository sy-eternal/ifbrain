package com.jzeen.travel.admin.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.GuideOrder;
import com.jzeen.travel.data.entity.GuideOrderGuideRelate;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.GuideActivityPlanRepository;
import com.jzeen.travel.data.repository.GuideGuideTypePlanRateRepository;
import com.jzeen.travel.data.repository.GuideOrderGuideRelateRepository;
import com.jzeen.travel.data.repository.GuideOrderRepository;
import com.jzeen.travel.data.repository.GuideRepository;
import com.jzeen.travel.data.repository.GuideTypePlanRepository;
import com.jzeen.travel.data.repository.UserRepository;

@Controller
@RequestMapping("/guideItemOrder")
public class GuideItemOrderController {
    @Autowired
    private GuideOrderRepository _guideOrderRepository;
    @Autowired
    private GuideTypePlanRepository _guideTypePlanRepository;
    @Autowired
    private CityRepository _cityRepository;
    @Autowired
    private GuideRepository _guideRepository;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private GuideGuideTypePlanRateRepository _guideGuideTypePlanRateRepository;
    @Autowired
    private DatePlanRepository _datePlanRepository;
    @Autowired
    private GuideActivityPlanRepository _guideActivityPlanRepository;
    @Autowired
    private GuideOrderGuideRelateRepository _guideOrderGuideRelate;
    

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String plan(Model model, HttpServletRequest request) {

        // 获取状态为“已提交”的订单

      List<GuideOrder> guideItemOrders = _guideOrderRepository.findByorderstatus(2);
        model.addAttribute("guideItemOrders", guideItemOrders);
        return "/guideItem/list";
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable Integer id, Model model,
            HttpServletRequest request) {
        List<City> city = _cityRepository.findAll();
        model.addAttribute("city", city);
        GuideOrder guideOrder = _guideOrderRepository.findOne(id);
        model.addAttribute("guideOrder", guideOrder);
        return "/guideItem/details";

    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String add(Model model, HttpServletRequest request) {
       String guideOrderId = request.getParameter("guideOrderId");
        GuideOrder guideOrder = _guideOrderRepository.findOne(Integer.parseInt(guideOrderId));
        guideOrder.setAppointedstatus(1);
        _guideOrderRepository.save(guideOrder);
        
        String guideIds = request.getParameter("guideIds");
        String substring = guideIds.substring(0, guideIds.length()-1);
        String[] split = substring.split(",");
        
        for(int i=0;i<split.length;i++){
            Guide guide = _guideRepository.findOne(Integer.parseInt(split[i]));
            GuideOrderGuideRelate  guideOrderGuideRelate=new GuideOrderGuideRelate();
            guideOrderGuideRelate.setCreateTime(new Date());
            guideOrderGuideRelate.setGuide(guide);
            guideOrderGuideRelate.setGuideOrder(guideOrder);
            _guideOrderGuideRelate.save(guideOrderGuideRelate);
        }
        
        return "redirect:/guideItemOrder/list";
    }

    @ResponseBody
    @RequestMapping(value="/selectCity/{id}",method = RequestMethod.GET)
    public List<User> select(Model model,@PathVariable String id){
        List<Guide> guide= _guideRepository.findByCity(Integer.parseInt(id));
        List<User> list=new ArrayList<User>();
        for(int i=0;i<guide.size();i++){
            
            list.add(guide.get(i).getUser());
        }
        return list;
        
    }
    
    /**
     * 根据id获取名字
     */
    @ResponseBody
    @RequestMapping(value = "/findName", method = RequestMethod.GET)
    public String findUserNameByGuideId( Model model, HttpServletRequest request) {
        String guideId = request.getParameter("guideId");
       String name="";
        Guide guide = _guideRepository.findOne(Integer.parseInt(guideId));
        User user = guide.getUser();
        name=user.getFirstName()+user.getLastName();
      return   name;
    }
    
 
}
