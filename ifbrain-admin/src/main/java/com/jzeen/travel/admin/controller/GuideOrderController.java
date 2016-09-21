/*package com.jzeen.travel.admin.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.ExcursionGuide;
import com.jzeen.travel.data.entity.ExcursionGuideOccupied;
import com.jzeen.travel.data.entity.ExcursionGuidePlan;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.QOrder;
import com.jzeen.travel.data.entity.StandardGuide;
import com.jzeen.travel.data.entity.StandardGuideOccupied;
import com.jzeen.travel.data.entity.StandardGuidePlan;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.ExcursionGuideOccupiedRepository;
import com.jzeen.travel.data.repository.ExcursionGuidePlanRepository;
import com.jzeen.travel.data.repository.ExcursionGuideRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.StandardGuideOccupiedRepository;
import com.jzeen.travel.data.repository.StandardGuidePlanRepository;
import com.jzeen.travel.data.repository.StandardGuideRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.mysema.query.types.expr.BooleanExpression;

@Controller
@RequestMapping("/guideorder")
public class GuideOrderController
{
    @Autowired
    private DatePlanRepository _datePlanRepository;
    @Autowired
    private OrderRepository _orderRepository;
    @Autowired
    ExcursionGuideRepository _excursionGuideRepository;
    @Autowired
    StandardGuideRepository _standardGuideRepository;
    @Autowired
    UserRepository _userRepository;

    @Autowired
    ExcursionGuidePlanRepository _excursionGuidePlanRepository;

    @Autowired
    ExcursionGuideOccupiedRepository _excursionGuideOccupiedRepository;

    @Autowired
    StandardGuidePlanRepository _standardGuidePlanRepository;
    @Autowired
    StandardGuideOccupiedRepository _standardGuideOccupiedRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model)
    {
        return "/guideorder/index";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<Order, Integer> search(HttpServletRequest request)
    {
        
        
        String orderNumber = request.getParameter("orderNumber");
        String orderStatus = request.getParameter("orderStatus");
        if (orderNumber.equals("") && orderStatus.equals("8"))
        {
            DataTable<Order, Integer> dataTable = DataTable.fromRequest(request, _orderRepository);
            return dataTable;
        }
        else
        {
            // 使用QueryDsl构造查询条件
            QOrder order = QOrder.order;
            BooleanExpression predicate = order.isNotNull();
            if (!orderNumber.equals(""))
            {
                predicate = predicate.and(order.orderNumber.eq(orderNumber));
            }
            if (!orderStatus.equals("8"))
            {
                predicate = predicate.and(order.orderStatus.eq(Integer.parseInt(orderStatus)));
            }
            DataTable<Order, Integer> dataTable = DataTable.fromRequest(request, _orderRepository, predicate);
            return dataTable;
        }
        
        
    }

    @ResponseBody
    @RequestMapping(value = "/changeguide", method = RequestMethod.GET)
    public Order changeguide(@RequestParam String orderNumber, @RequestParam Integer orderStatus)
    {
        Order order = _orderRepository.findByOrderNumberAndOrderStatus(orderNumber, orderStatus);
        return order;
    }

    @RequestMapping(value = "/detail")
    public String detailInit(@RequestParam Integer orderId, Model model)
    {
        Order order = _orderRepository.findOne(orderId);
        model.addAttribute("order", order);

        return "/guideorder/detail";
    }

    ---------------------------------------------------------------------------------- 标准导游---------------------------------------------------------
    *//**
     * 出发地接送导游姓名
     * 
     * @param dataPlanId
     * @param response
     * @throws IOException
     *//*
    @ResponseBody
    @RequestMapping(value = "/findFirstNameAndLastName", method = RequestMethod.GET)
    public String findFirstNameAndLastName(@RequestParam Integer dataPlanId, HttpServletResponse response)
            throws IOException
    {
        String string="";
        String result="";
        StringBuffer stringBuffer = new StringBuffer();
        DatePlan datePlan = _datePlanRepository.findOne(dataPlanId);

        List<StandardGuidePlan> standardGuidePlans = datePlan.getStandardGuidePlans();

        for (int i = 0; i < standardGuidePlans.size(); i++)
        {

            if (standardGuidePlans.get(i).getStartOrEnd())
            {

                List<StandardGuideOccupied> standardGuideOccupieds = standardGuidePlans.get(i)
                        .getStandardGuideOccupieds();

                for (int j = 0; j < standardGuideOccupieds.size(); j++)
                {

                    StandardGuide standardGuide = standardGuideOccupieds.get(j).getStandardGuide();
                    String firstName = standardGuide.getUser().getFirstName();
                    String lastName = standardGuide.getUser().getLastName();
                    result+="," +firstName + lastName;
                }
            }
        }
        if(result!=""){
            string= result.substring(1);
        }
       
        return string;

    }

    *//**
     * 可选出发地接送导游姓名
     * 
     * @param response
     * @param request
     * @throws Exception
     *//*
     @ResponseBody
    @RequestMapping(value = "/findOptionFirstNameAndLastName", method = RequestMethod.GET)
    public String findOptionFirstNameAndLastName(@RequestParam String fromCityId, @RequestParam String startDate,HttpServletResponse response, HttpServletRequest request)
            throws Exception
    {
         String string="";
         String result="";
        int cityId = Integer.parseInt(fromCityId);
        StringBuffer stringBuffer = new StringBuffer();
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(startDate);
        List<StandardGuide> unoccupied = _standardGuideRepository.getUnoccupied(date, cityId);
        for (int i = 0; i < unoccupied.size(); i++)
        {
            User user = unoccupied.get(i).getUser();
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            result+="," +firstName + lastName;
        }
     if(result!=""){        
      string=result.substring(1);
     }
        return string;

    }

    *//***
     * 到达地接送导游姓名
     * 
     * @param model
     * @param request
     * @return
     *//*
     @ResponseBody
    @RequestMapping(value = "/findToFirstNameAndLastName", method = RequestMethod.GET)
    public String  findToFirstNameAndLastName(@RequestParam Integer dataPlanId, HttpServletResponse response)
            throws IOException
    {
         String string="";
         String result="";
        StringBuffer stringBuffer = new StringBuffer();
        DatePlan datePlan = _datePlanRepository.findOne(dataPlanId);

        List<StandardGuidePlan> standardGuidePlans = datePlan.getStandardGuidePlans();

        for (int i = 0; i < standardGuidePlans.size(); i++)
        {

            if (!standardGuidePlans.get(i).getStartOrEnd())
            {

                List<StandardGuideOccupied> standardGuideOccupieds = standardGuidePlans.get(i)
                        .getStandardGuideOccupieds();

                for (int j = 0; j < standardGuideOccupieds.size(); j++)
                {

                    StandardGuide standardGuide = standardGuideOccupieds.get(j).getStandardGuide();
                    String firstName = standardGuide.getUser().getFirstName();
                    String lastName = standardGuide.getUser().getLastName();
                    String name = "";
                    result+="," +firstName + lastName;
                }
            }
        }

       if(result!=""){
           string=result.substring(1);
       }
     
      return string;

    }

    
     * 可选到达地接送导游姓名
     
    @ResponseBody
    @RequestMapping(value = "/findToOptionFirstNameAndLastName", method = RequestMethod.GET)
    public String findToOptionFirstNameAndLastName(@RequestParam String toCityId, @RequestParam String endDate,HttpServletResponse response, HttpServletRequest request)
            throws Exception
    {
        String string="";
        String result="";
        int cityId = Integer.parseInt(toCityId);
        String startDate = request.getParameter("endDate");
        StringBuffer stringBuffer = new StringBuffer();
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(startDate);
        List<StandardGuide> unoccupied = _standardGuideRepository.getUnoccupied(date, cityId);
        for (int i = 0; i < unoccupied.size(); i++)
        {
            User user = unoccupied.get(i).getUser();
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            result+="," +firstName + lastName;
            
        }
         if(result!=""){
             string=result.substring(1);
         }
        
         return string;

    }

    ------------------------------------------------------------------------------短途导游-----------------------------------------------------------------------------------------------

    *//**
     * 出发地接送导游姓名
     * 
     * @param dataPlanId
     * @param response
     * @throws IOException
     *//*
    @ResponseBody
    @RequestMapping(value = "/findshortFirstNameAndLastName", method = RequestMethod.GET)
    public String findshortFirstNameAndLastName(@RequestParam Integer dataPlanId, HttpServletResponse response)
            throws IOException
    {
        String result="";
        String string="";
        StringBuffer stringBuffer = new StringBuffer();
        DatePlan datePlan = _datePlanRepository.findOne(dataPlanId);

        List<ExcursionGuidePlan> excursionGuidePlans = datePlan.getExcursionGuidePlans();

        for (int i = 0; i < excursionGuidePlans.size(); i++)
        {

            if (excursionGuidePlans.get(i).getStartOrEnd())
            {

                List<ExcursionGuideOccupied> excursionGuideOccupieds = excursionGuidePlans.get(i)
                        .getExcursionGuideOccupieds();

                for (int j = 0; j < excursionGuideOccupieds.size(); j++)
                {

                    ExcursionGuide excursionGuide = excursionGuideOccupieds.get(j).getExcursionGuide();
                   result+="," +excursionGuide.getGuide().getFirstName()+excursionGuide.getGuide().getLastName();
                }
            }
        }
            if(result!=""){
             string = result.substring(1);
            }
              return string;
    }

    *//**
     * 可选出发地导游
     * 
     * @param response
     * @param request
     * @throws Exception
     *//*
    @ResponseBody
    @RequestMapping(value = "/findshortOptionFirstNameAndLastName", method = RequestMethod.GET)
    public String findshortOptionFirstNameAndLastName(@RequestParam String fromCityId, @RequestParam String startDate,HttpServletResponse response, HttpServletRequest request)
            throws Exception
    {
        String result="";
        String string="";
        int cityId = Integer.parseInt(fromCityId);
        StringBuffer stringBuffer = new StringBuffer();
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(startDate);
        List<ExcursionGuide> unoccupied = _excursionGuideRepository.getUnoccupied(date, cityId);
        for (int i = 0; i < unoccupied.size(); i++)
        {
            User user = unoccupied.get(i).getGuide();
            result+="," +user.getFirstName()+user.getLastName();
        }
            if(result!=""){
                string=result.substring(1);
            }
      return string;
    }

    *//**
     * 到达地导游姓名
     * 
     * @param model
     * @param request
     * @return
     *//*
   @ResponseBody
    @RequestMapping(value = "/findshortToFirstNameAndLastName", method = RequestMethod.GET)
    public String findshortToFirstNameAndLastName(@RequestParam Integer dataPlanId, HttpServletResponse response)
            throws IOException
    {
        String result="";
        String string="";
        StringBuffer stringBuffer = new StringBuffer();
        DatePlan datePlan = _datePlanRepository.findOne(dataPlanId);

        List<ExcursionGuidePlan> excursionGuidePlans = datePlan.getExcursionGuidePlans();

        for (int i = 0; i < excursionGuidePlans.size(); i++)
        {

            if (!excursionGuidePlans.get(i).getStartOrEnd())
            {

                List<ExcursionGuideOccupied> excursionGuideOccupieds = excursionGuidePlans.get(i)
                        .getExcursionGuideOccupieds();

                for (int j = 0; j < excursionGuideOccupieds.size(); j++)
                {

                    ExcursionGuide excursionGuide = excursionGuideOccupieds.get(j).getExcursionGuide();
                    String firstName = excursionGuide.getGuide().getFirstName();
                    String lastName = excursionGuide.getGuide().getLastName();
                    result+="," +firstName + lastName;
                }
            }
        }

      if(result!=""){
         string= result.substring(1);
      }
                 return string;
    }

    *//**
     * 可选到达地导游
     *  
     * @param response
     * @param request
     * @throws Exception
     *//*
    @ResponseBody
    @RequestMapping(value = "/findshortToOptionFirstNameAndLastName", method = RequestMethod.GET)
    public String findshortToOptionFirstNameAndLastName(@RequestParam String toCityId, @RequestParam String endDate,HttpServletResponse response, HttpServletRequest request)
            throws ParseException
    {
        Integer cityId = Integer.parseInt(toCityId);
         String string="";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(endDate);

        List<ExcursionGuide> excursionGuides = _excursionGuideRepository.getUnoccupied(date, cityId);

        String excursionGuideNames = "";
        for (ExcursionGuide excursionGuide : excursionGuides)
        {
            excursionGuideNames += "," +excursionGuide.getGuide().getFirstName()
                    + excursionGuide.getGuide().getLastName();
        }
        if (excursionGuideNames!="")
        {
            string = excursionGuideNames.substring(1);
        }

        return string;
    }
    

    @RequestMapping(value = "/detailupdate", method = RequestMethod.GET)
    public String detailupdate(Model model, HttpServletRequest request) throws Exception
    {
        //获取开始日期和结束日期
        String astartDate = request.getParameter("startDate");
        String aendDate = request.getParameter("endDate");
        
        
        List<User> listName = new ArrayList<User>();
        List<User> listName1 = new ArrayList<User>();
        List<User> listName2 = new ArrayList<User>();
        List<User> listName3 = new ArrayList<User>();
        List<User> listName4 = new ArrayList<User>();
        List<User> listName5 = new ArrayList<User>();
        List<User> listName6 = new ArrayList<User>();
        List<User> listName7 = new ArrayList<User>();
        String orderId = request.getParameter("orderId");
        // 获取订单
        Order order = _orderRepository.findOne(Integer.parseInt(orderId));
        model.addAttribute("order", order);

        // 获取日期规划
        String dataPlanId = request.getParameter("dataPlanId");
        DatePlan datePlan = _datePlanRepository.findOne(Integer.parseInt(dataPlanId));
        System.out.println("--------------"+datePlan.getStartDate());
        model.addAttribute("startDate", astartDate);
        model.addAttribute("endDate", aendDate);
        model.addAttribute(datePlan);

        
         * ------------------------------------------------------------------短途导游
         * ----------------------------------------------------------
         

        // 出发地接送导游姓名
        
        List<ExcursionGuidePlan> excursionGuidePlans = datePlan.getExcursionGuidePlans();

        for (int i = 0; i < excursionGuidePlans.size(); i++)
        {
            if (excursionGuidePlans.get(i).getStartOrEnd())
            {
                List<ExcursionGuideOccupied> excursionGuideOccupieds = excursionGuidePlans.get(i)
                        .getExcursionGuideOccupieds();
                for (int j = 0; j < excursionGuideOccupieds.size(); j++)
                {
                    listName.add(excursionGuideOccupieds.get(j).getExcursionGuide().getGuide());
                }
            }
        }
        model.addAttribute("listName", listName);
        // 可选出发地导游姓名

        String fromCityId = request.getParameter("fromCityId");
        int cityId = Integer.parseInt(fromCityId);
        String startDate = request.getParameter("startDate");
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = fmt.parse(startDate);

        List<ExcursionGuide> unoccupied = _excursionGuideRepository.getUnoccupied(date, cityId);
        for (int i = 0; i < unoccupied.size(); i++)
        {
            listName1.add(unoccupied.get(i).getGuide());
        }
        model.addAttribute("listName1", listName1);
        

        // 到达地导游姓名

        for (int i = 0; i < excursionGuidePlans.size(); i++)
        {
            if (!excursionGuidePlans.get(i).getStartOrEnd())
            {
                List<ExcursionGuideOccupied> excursionGuideOccupieds = excursionGuidePlans.get(i)
                        .getExcursionGuideOccupieds();

                for (int j = 0; j < excursionGuideOccupieds.size(); j++)
                {

                    ExcursionGuide excursionGuide = excursionGuideOccupieds.get(j).getExcursionGuide();
                    listName2.add(excursionGuide.getGuide());

                }
            }
        }

        model.addAttribute("listName2", listName2);

        // 可选到达地导游姓名

        String toCityId = request.getParameter("toCityId");
        int cityId1 = Integer.parseInt(toCityId);
        String endDate = request.getParameter("endDate");
        StringBuffer stringBuffer = new StringBuffer();
        DateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = fmt.parse(endDate);
       List<ExcursionGuide> unoccupied1 = _excursionGuideRepository.getUnoccupied(date1, cityId1);
        for (int i = 0; i < unoccupied1.size(); i++)
        {
            listName3.add(unoccupied1.get(i).getGuide());
        }
        model.addAttribute("listName3", listName3);

        -----------------------------------------------------------------------------标准导游-----------------------------------------------------------------------------

        // 出发地接送导游
        List<StandardGuidePlan> standardGuidePlans = datePlan.getStandardGuidePlans();

        for (int i = 0; i < standardGuidePlans.size(); i++)
        {

            if (standardGuidePlans.get(i).getStartOrEnd())
            {

                List<StandardGuideOccupied> standardGuideOccupieds = standardGuidePlans.get(i)
                        .getStandardGuideOccupieds();

                for (int j = 0; j < standardGuideOccupieds.size(); j++)
                {

                    listName4.add(standardGuideOccupieds.get(j).getStandardGuide().getUser());

                }
            }
        }
        model.addAttribute("listName4", listName4);

        // 可选出发地接送导游
        String fromCityIds = request.getParameter("fromCityId");
        int cityIds = Integer.parseInt(fromCityIds);
        String startDates = request.getParameter("startDate");
        DateFormat fmts = new SimpleDateFormat("yyyy-MM-dd");
        Date dates = fmts.parse(startDates);
        List<StandardGuide> unoccupieds = _standardGuideRepository.getUnoccupied(dates, cityIds);
        for (int i = 0; i < unoccupieds.size(); i++)
        {
            listName5.add(unoccupieds.get(i).getUser());
        }

        model.addAttribute("listName5", listName5);

        // 到达地接送导游

        for (int i = 0; i < standardGuidePlans.size(); i++)
        {

            if (!standardGuidePlans.get(i).getStartOrEnd())
            {

                List<StandardGuideOccupied> standardGuideOccupieds = standardGuidePlans.get(i)
                        .getStandardGuideOccupieds();

                for (int j = 0; j < standardGuideOccupieds.size(); j++)
                {
                    listName6.add(standardGuideOccupieds.get(j).getStandardGuide().getUser());
                }
            }
        }
        model.addAttribute("listName6", listName6);

        // 可选到达地接送导游
        String toCityIds = request.getParameter("toCityId");
        int tocityIds = Integer.parseInt(toCityIds);
        String endDate1 = request.getParameter("endDate");
        DateFormat fmt2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = fmt.parse(endDate1);
        List<StandardGuide> unoccupied2 = _standardGuideRepository.getUnoccupied(date2, tocityIds);
        for (int i = 0; i < unoccupied2.size(); i++)
        {
            listName7.add(unoccupied2.get(i).getUser());
        }

        model.addAttribute("listName7", listName7);

        return "/guideorder/createGuideOrder";
    }

    
     * --------------------------------------------------------------------------
     * --
     * -----------------更换导游----------------------------------------------------
     * ---------------------------
     
----------------------------------------------------更换短途导游-----------------------------------------
    // 更换出发地接送导游短途导游操作
    @ResponseBody
    @RequestMapping(value = "/exchange", method = RequestMethod.GET)
    public String exchange(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String result="";
        String dataPlanId = request.getParameter("dataPlanId");
        int parseDataPlanId = Integer.parseInt(dataPlanId);
        // 获取日期规划
        DatePlan datePlan = _datePlanRepository.findOne(Integer.parseInt(dataPlanId));

        String fromId = request.getParameter("fromId");

        String changeId = request.getParameter("changeId");
        
        String startDate=request.getParameter("startDate");
         SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
         Date date = fmt.parse(startDate);
        // 被更换导游
        int FromId = Integer.parseInt(fromId);
        User userFrom = _userRepository.findOne(FromId);

        // 更换导游
        int ChangeId = Integer.parseInt(changeId);
        User userChange = _userRepository.findOne(ChangeId);
        ExcursionGuide excursionGuide = userChange.getExcursionGuide();

        // 获得短途导游规划
        List<ExcursionGuidePlan> excursionGuidePlans = datePlan.getExcursionGuidePlans();

        // 遍历短途导游规划，获得短途导游占用
        for (int i = 0; i < excursionGuidePlans.size(); i++)
        {
            if (excursionGuidePlans.get(i).getStartOrEnd())
            {
                List<ExcursionGuideOccupied> excursionGuideOccupieds = excursionGuidePlans.get(i)
                        .getExcursionGuideOccupieds();

                // 遍历短途导游占用，获得短途导游
                for (int j = 0; j < excursionGuideOccupieds.size(); j++)
                {

                    // 获得短途占用中的短途导游
                    ExcursionGuide excursionGuide2 = excursionGuideOccupieds.get(j).getExcursionGuide();
                  
                    // 判断短途占用中的短途导游是否是被更换的短途导游,如果是并替换为要更换的短途导游
                    if (excursionGuide2.getId() == userFrom.getExcursionGuide().getId())
                    {
                        //更换  被更换的导游
                        excursionGuideOccupieds.get(j).setExcursionGuide(excursionGuide);
                        _excursionGuideOccupiedRepository.save(excursionGuideOccupieds.get(j));
                        result="更换成功";
                    }
                }
            }
        }
      
            return result;
    }

    // 更换到达地接送导游短途导游操作
    @ResponseBody
    @RequestMapping(value = "/exchange1", method = RequestMethod.GET)
    public String exchange1(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String result="";
        String dataPlanId = request.getParameter("dataPlanId");
        int parseDataPlanId = Integer.parseInt(dataPlanId);
        // 获取日期规划
        DatePlan datePlan = _datePlanRepository.findOne(Integer.parseInt(dataPlanId));

        String toNameId = request.getParameter("ToNameId");

        String tochangeNameId = request.getParameter("TochangeNameId");

        // 被更换导游
        int ToNameId = Integer.parseInt(toNameId);
        User userFrom = _userRepository.findOne(ToNameId);

        // 更换导游
        int TochangeNameId = Integer.parseInt(tochangeNameId);
        User userChange = _userRepository.findOne(TochangeNameId);
        ExcursionGuide excursionGuide = userChange.getExcursionGuide();

        // 获得短途导游规划
        List<ExcursionGuidePlan> excursionGuidePlans = datePlan.getExcursionGuidePlans();

        // 遍历短途导游规划，获得短途导游占用
        for (int i = 0; i < excursionGuidePlans.size(); i++)
        {
            if (!excursionGuidePlans.get(i).getStartOrEnd())
            {
                List<ExcursionGuideOccupied> excursionGuideOccupieds = excursionGuidePlans.get(i)
                        .getExcursionGuideOccupieds();

                // 遍历短途导游占用，获得短途导游
                for (int j = 0; j < excursionGuideOccupieds.size(); j++)
                {

                    // 获得短途占用中的短途导游
                    ExcursionGuide excursionGuide2 = excursionGuideOccupieds.get(j).getExcursionGuide();

                    // 判断短途占用中的短途导游是否是被更换的短途导游,如果是并替换为要更换的短途导游
                    if (excursionGuide2.getId() == userFrom.getExcursionGuide().getId())
                    {
                        excursionGuideOccupieds.get(j).setExcursionGuide(excursionGuide);
                        _excursionGuideOccupiedRepository.save(excursionGuideOccupieds.get(j));
                        result="更换成功";
                    }
                }
            }
        }
         return result ;
    }
---------------------------------------------------------------------------更换标准导游------------------------------------------
    // 更换出发地接送导游标准导游操作
    @ResponseBody
    @RequestMapping(value = "/exchange2", method = RequestMethod.GET)
    public String exchange2(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String result="";
        String dataPlanId = request.getParameter("dataPlanId");
        int parseDataPlanId = Integer.parseInt(dataPlanId);
        // 获取日期规划
        DatePlan datePlan = _datePlanRepository.findOne(Integer.parseInt(dataPlanId));

        String fromId = request.getParameter("fromId");

        String changeId = request.getParameter("changeId");

        // 被更换导游
        int FromId = Integer.parseInt(fromId);
        User userFrom = _userRepository.findOne(FromId);

        // 更换导游
        int ChangeId = Integer.parseInt(changeId);
        User userChange = _userRepository.findOne(ChangeId);
        StandardGuide standardGuide = userChange.getStandardGuide();

        // 获得标准导游规划
        List<StandardGuidePlan> standardGuidePlans = datePlan.getStandardGuidePlans();

        // 遍历标准导游规划，获得标准导游占用
        for (int i = 0; i < standardGuidePlans.size(); i++)
        {
            if (standardGuidePlans.get(i).getStartOrEnd())
            {
                List<StandardGuideOccupied> standardGuideOccupieds = standardGuidePlans.get(i)
                        .getStandardGuideOccupieds();

                // 遍历标准导游占用，获得标准导游
                for (int j = 0; j < standardGuideOccupieds.size(); j++)
                {

                    // 获得短途占用中的短途导游
                    StandardGuide standardGuide2 = standardGuideOccupieds.get(j).getStandardGuide();

                    // 判断标准占用中的标准导游是否是被更换的标准导游,如果是并替换为要更换的标准导游
                    if (standardGuide2.getId() == userFrom.getStandardGuide().getId())
                    {
                        standardGuideOccupieds.get(j).setStandardGuide(standardGuide);
                        _standardGuideOccupiedRepository.save(standardGuideOccupieds.get(j));
                        result="更换成功";
                    }
                }
            }
        }
       return result;
    }

    // 更换到达地接送导游标准导游操作
    @ResponseBody
    @RequestMapping(value = "/exchange3", method = RequestMethod.GET)
    public String exchange3(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        String result="";
        String dataPlanId = request.getParameter("dataPlanId");
        int parseDataPlanId = Integer.parseInt(dataPlanId);
        // 获取日期规划
        DatePlan datePlan = _datePlanRepository.findOne(Integer.parseInt(dataPlanId));

        String toNameId = request.getParameter("ToNameId");

        String tochangeNameId = request.getParameter("TochangeNameId");

        // 被更换导游
        int ToNameId = Integer.parseInt(toNameId);
        User userFrom = _userRepository.findOne(ToNameId);

        // 更换导游
        int TochangeNameId = Integer.parseInt(tochangeNameId);
        User userChange = _userRepository.findOne(TochangeNameId);
        StandardGuide standardGuide = userChange.getStandardGuide();

        // 获得短途导游规划
        List<StandardGuidePlan> standardGuidePlans = datePlan.getStandardGuidePlans();

        // 遍历标准导游规划，获得标准导游占用
        for (int i = 0; i < standardGuidePlans.size(); i++)
        {
            if (!standardGuidePlans.get(i).getStartOrEnd())
            {
                List<StandardGuideOccupied> standardGuideOccupieds = standardGuidePlans.get(i).getStandardGuideOccupieds();

                // 遍历标准导游占用，获得标准导游
                for (int j = 0; j < standardGuideOccupieds.size(); j++)
                {
                    // 获得标准占用中的标准导游
                    StandardGuide standardGuide2 = standardGuideOccupieds.get(j).getStandardGuide();

                    // 判断标准占用中的标准导游是否是被更换的标准导游,如果是并替换为要更换的标准导游
                    if (standardGuide2.getId() == userFrom.getStandardGuide().getId())
                    {
                        standardGuideOccupieds.get(j).setStandardGuide(standardGuide);
                        _standardGuideOccupiedRepository.save(standardGuideOccupieds.get(j));
                        result="更换成功";
                    }
                }
            }
        }
        return result;
      
    }

}
*/