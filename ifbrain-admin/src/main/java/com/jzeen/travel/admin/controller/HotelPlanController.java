

package com.jzeen.travel.admin.controller;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.HotelActivity;
import com.jzeen.travel.data.entity.HotelPlan;
import com.jzeen.travel.data.entity.HotelPlanRoom;
import com.jzeen.travel.data.entity.HotelRoomType;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.data.entity.SpotPlanTicket;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.HotelActivityRepository;
import com.jzeen.travel.data.repository.HotelPlanRepository;
import com.jzeen.travel.data.repository.HotelPlanRoomRepository;
import com.jzeen.travel.data.repository.HotelRoomTypeRepository;
import com.jzeen.travel.data.repository.OrderRepository;
@Controller
@RequestMapping("/hotelPlan")
public class HotelPlanController
{
    @Autowired
    HotelRoomTypeRepository _hotelRoomTypeRepository;
    @Autowired
    private CityRepository _cityRepository;
    @Autowired
    HotelPlanRepository  _hotelPlanRepository;
    
    @Autowired
    HotelPlanRoomRepository _hotelPlanRoomRepository;
    @Autowired
    HotelActivityRepository  _hotelActivityRepository;
    @Autowired
    DatePlanRepository _datePlanRepository;
    @Autowired
    OrderRepository   _orderRepository;
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request)
    {
        String datePlanId = request.getParameter("datePlanId");
        model.addAttribute("datePlanId", datePlanId);
        
        String orderId=request.getParameter("orderId");
        model.addAttribute("orderId", orderId);
       
        String hotelPlanId= request.getParameter("hotelPlanId");
        model.addAttribute("hotelPlanId", hotelPlanId);
        
        
        
        
        
        
        String hotelRoomTypeId = request.getParameter("hotelRoomTypeId");
        model.addAttribute("hotelRoomTypeId", hotelRoomTypeId);
        // 获取城市数据/datePlan/create"
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);
        return "/hotelplan/list";
    }
    
    
    //酒店房间类型
    
    
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public String type(Model model, HttpServletRequest request)
    {
       
   String datePlanId = request.getParameter("datePlanId");
     model.addAttribute("datePlanId", datePlanId);
     
     String hotelPlanId = request.getParameter("hotelPlanId");
     model.addAttribute("hotelPlanId", hotelPlanId);
     
    String orderId = request.getParameter("orderId");
    model.addAttribute("orderId", orderId);
    
    String toCity = request.getParameter("toCity");
    model.addAttribute("toCity", toCity);
    
    String startDate = request.getParameter("startDate");
    model.addAttribute("startDate", startDate);
   
   
    String endDate = request.getParameter("endDate");
    model.addAttribute("endDate", endDate);
    
    String roomNum = request.getParameter("roomNum");
    model.addAttribute("roomNum", roomNum);
   
    String id = request.getParameter("id");
    model.addAttribute("hotelId", id);
        return "/hotelplan/hotelTypeList";
    }
    
    @ResponseBody
    @RequestMapping(value = "/searchType", method = RequestMethod.GET)
    public List<HotelRoomType> searchType(Model model, HttpServletRequest request)
    {
        String id = request.getParameter("id");

        String roomNum = request.getParameter("roomNum");
        model.addAttribute("roomNum", roomNum);
        
        String startDate = request.getParameter("startDate");
        model.addAttribute("startDate", startDate);
        
        String endDate = request.getParameter("endDate");
        model.addAttribute("endDate", endDate);
        List<HotelRoomType> hotelRoomType = _hotelActivityRepository.findHotelRoomType(Integer.parseInt(id));
        
        return hotelRoomType;
    }
    
    
    
    
    
    
    
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Model model, HttpServletRequest request)
    {
        
        // 获取城市数据/datePlan/create"
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);
       
        String datePlanId = request.getParameter("datePlanId");
        model.addAttribute("datePlanId", datePlanId);
        
        
        
        
        
        
        String hotelPlanId = request.getParameter("hotelPlanId");
        model.addAttribute("hotelPlanId", hotelPlanId);
        
        String hotelRoomTypeId = request.getParameter("hotelRoomTypeId");
        model.addAttribute("hotelRoomTypeId", hotelRoomTypeId);
        
        String orderId = request.getParameter("orderId");
        Order order = _orderRepository.findOne(Integer.parseInt(orderId));
        model.addAttribute("order", order);
        
        
        
        
        List<HotelActivity> hotelActivityList=new ArrayList<HotelActivity>();
        String toCity = request.getParameter("toCity");
        City cityName = _cityRepository.findOne(Integer.parseInt(toCity));
        model.addAttribute("toCity", toCity);
        model.addAttribute("cityName", cityName.getCityName());
        
        
        String startDate = request.getParameter("startDate");
       model.addAttribute("startDate", startDate);
        String endDate = request.getParameter("endDate");
        
        model.addAttribute("endDate", endDate);
        String roomNum = request.getParameter("roomNum");
        
        model.addAttribute("roomNum", roomNum);
       
      
       
        
        City city = _cityRepository.findOne(Integer.parseInt(toCity));
        List<HotelActivity> hotelActivity = _hotelActivityRepository.findAll(city.getId());
        for(int i=0;i<hotelActivity.size();i++){
            HotelActivity activity = hotelActivity.get(i);
               String perNightPrice = _hotelActivityRepository.find(activity.getId());
               activity.setPerNightPrice(new BigDecimal(perNightPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
               hotelActivityList.add(activity);
                                                }
        model.addAttribute("listHotelPlan", hotelActivityList);
        return "/hotelplan/searchList";
    }
    
    //按酒店价格排序
    @RequestMapping(value = "/orderByPrice", method = RequestMethod.GET)
    public String orderByPrice(Model model, HttpServletRequest request)
    {
        
        // 获取城市数据/datePlan/create"
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);
       
        String datePlanId = request.getParameter("amp;datePlanId");
        model.addAttribute("datePlanId", datePlanId);
        
       
        
   
        
        String orderId = request.getParameter("amp;orderId");
        Order order = _orderRepository.findOne(Integer.parseInt(orderId));
        model.addAttribute("order", order);
        
        
        
        
//        List<HotelActivity> hotelActivityList=new ArrayList<HotelActivity>();
        String toCity = request.getParameter("toCity");
        
        model.addAttribute("toCity", toCity);
        
        
        String startDate = request.getParameter("amp;startDate");
       model.addAttribute("startDate", startDate);
        String endDate = request.getParameter("amp;endDate");
        
        model.addAttribute("endDate", endDate);
        String roomNum = request.getParameter("amp;roomNum");
        
        model.addAttribute("roomNum", roomNum);
       
      
       
        
        City city = _cityRepository.findOne(Integer.parseInt(toCity));
        List<HotelActivity> hotelActivityList = _hotelActivityRepository.findByPrice(city.getId());
        for(int i=0;i<hotelActivityList.size();i++){
            HotelActivity activity = hotelActivityList.get(i);
            
            
            List<HotelRoomType> hotelRoomTypes = activity.getHotelRoomType();
            BigDecimal minPrice = BigDecimal.ZERO;
            
            if (hotelRoomTypes!= null) {
                for (HotelRoomType roomType: hotelRoomTypes) {
                    if (roomType.getPerNightPrice().compareTo(minPrice) == -1
                            || minPrice.compareTo(BigDecimal.ZERO) == 0)
                    {
                        minPrice = roomType.getPerNightPrice();
                    }
                    
                }
            }
               activity.setPerNightPrice(minPrice);
               hotelActivityList.add(activity);   
               
       }
       
        Collections.sort(hotelActivityList, new Comparator<HotelActivity>() {  
            public int compare(HotelActivity arg0, HotelActivity arg1) {  
                BigDecimal perNightPrice = arg0.getPerNightPrice();
                BigDecimal perNightPrice2 = arg1.getPerNightPrice();  
                if (perNightPrice.compareTo(perNightPrice2)==1) {  
                    return 1;  
                } else if (perNightPrice.compareTo(perNightPrice2)==0) {  
                    return 0;  
                } else {  
                    return -1;  
                }  
            }  
        });
        model.addAttribute("listHotelPlan", hotelActivityList);
        return "/hotelplan/searchList";
    }
    //查询所有放入条件
    
    private List<HotelRoomType> sortRootType(List<HotelRoomType> hotelRoomTypes){
        List<HotelRoomType> newRoomTypes = new ArrayList<HotelRoomType>();
        
        
        
        
        
        return newRoomTypes;
        
    }
    
    
    
    @RequestMapping(value = "/searchAll", method = RequestMethod.GET)
    public String searchAll(Model model, HttpServletRequest request)
    {
        Integer[] freeBreakfastType={1,2};
        Integer[]    freeInternetType={1,2};
        Integer[] freeParkingType={1,2};
        Integer[] airportShuttleType={1,2};
         Integer[] fitnessCenterType={1,2};
        
        // 获取城市数据/datePlan/create"
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);
        
        List<HotelActivity> hotelActivityList=new ArrayList<HotelActivity>();
        List<HotelActivity> hotelActivity=new ArrayList<HotelActivity>();
        List<HotelActivity> hotelActivityOne=new ArrayList<HotelActivity>();
        String toCity = request.getParameter("amp;toCity");
        model.addAttribute("toCity", toCity);
        String startDate = request.getParameter("amp;startDate");
        model.addAttribute("startDate", startDate);
        String endDate = request.getParameter("amp;endDate");
        model.addAttribute("endDate", endDate);
        String datePlan = request.getParameter("amp;datePlan");
        model.addAttribute("datePlanId", datePlan);
        
        
        String orderId = request.getParameter("amp;orderId");
        Order order = _orderRepository.findOne(Integer.parseInt(orderId));
        model.addAttribute("order", order);
        
        String cityName = request.getParameter("amp;cityName");
        model.addAttribute("cityName", cityName);
        City city = _cityRepository.findOne(Integer.parseInt(toCity));
        String xingji = request.getParameter("xingji");
        String price = request.getParameter("amp;price");
        String free = request.getParameter("amp;free");
        
       
        if(xingji.equals("") && price.equals("") && free.equals("")){
            hotelActivity = _hotelActivityRepository.findAll(city.getId());
            for(int i=0;i<hotelActivity.size();i++){
                HotelActivity activity = hotelActivity.get(i);
                   String perNightPrice = _hotelActivityRepository.find(activity.getId());
                   activity.setPerNightPrice(new BigDecimal(perNightPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
                   hotelActivityList.add(activity);
                   model.addAttribute("listHotelPlan", hotelActivityList);
                   }
            }else if(xingji!=""&&free!=""&&price!=""){
                   //获得星级的查询条件 
                List<Integer> hotelClass=new ArrayList<Integer>();
                    String xingjiValue = xingji.substring(0, xingji.lastIndexOf(","));
                    
                    String[] xingjisplit = xingjiValue.split(",");
                    
                    Integer [] num=new Integer[xingjisplit.length];
                    for(int i=0;i<num.length;i++){
                        num[i]=Integer.parseInt(xingjisplit[i]);
                    }
                    for(int i=0;i<num.length;i++){
                        hotelClass.add(num[i]);
                    }
                    //获得免费服务查询条件
                    String freeValue = free.substring(0, free.lastIndexOf(","));
                    String[] freesplit = freeValue.split(",");
                    for(int n=0;n<freesplit.length;n++){
                        if(freesplit[n].equals("freeBreakfastType")){
                            freeBreakfastType=new Integer[]{2};
                        }else if(freesplit[n].equals("freeInternetType")){
                            freeInternetType=new Integer[]{2};
                        }else if(freesplit[n].equals("freeParkingType")){
                            freeParkingType=new Integer[]{2};
                        }else if(freesplit[n].equals("airportShuttleType")){
                            airportShuttleType=new Integer[]{2};
                        }else{
                            fitnessCenterType=new Integer[]{2};
                        }
                    }
                    
                    //获得价格的查询条件
                    String priceValue = price.substring(0, price.lastIndexOf(";"));
                    String[] pricesplit = priceValue.split(";");
                        for(int i=0;i<pricesplit.length;i++){
                            String[] split = pricesplit[i].split(",");
                            Integer [] pricenum=new Integer[split.length];
                            for(int j=0;j<pricenum.length;j++){
                                pricenum[j]=Integer.parseInt(split[j]);
                            }
                           if(pricenum.length>1){
                                Integer minPrice = pricenum[0];
                                Integer maxPrice = pricenum[1];
                                List<HotelActivity> findPriceAndXingjiAndFreeAll = _hotelActivityRepository.findPriceAndXingjiAndFreeAll(city.getId(), hotelClass, new BigDecimal(minPrice).setScale(2, BigDecimal.ROUND_HALF_UP), new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP),freeBreakfastType,freeInternetType,freeParkingType,airportShuttleType,fitnessCenterType);
                                      for(int k=0;k<findPriceAndXingjiAndFreeAll.size();k++){
                                          hotelActivityOne.add(findPriceAndXingjiAndFreeAll.get(k));
                                      }
                            }else{
                                    Integer maxPrice = pricenum[0];
                                    BigDecimal maxprice=new BigDecimal(maxPrice);
                                    BigDecimal minprice=new BigDecimal(300);
                                    if(maxprice.compareTo(minprice)==1){
                                        List<HotelActivity> findPriceAndXingjiAndFreeAllOne = _hotelActivityRepository.findPriceAndXingjiAndFreeAllOne(city.getId(), hotelClass, new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP),freeBreakfastType,freeInternetType,freeParkingType,airportShuttleType,fitnessCenterType);
                                        for(int k=0;k<findPriceAndXingjiAndFreeAllOne.size();k++){
                                            hotelActivityOne.add(findPriceAndXingjiAndFreeAllOne.get(k));
                                        }
                                    }else{
                                        List<HotelActivity> findPriceAndXingjiAndFreeAllTwo = _hotelActivityRepository.findPriceAndXingjiAndFreeAllTwo(city.getId(), hotelClass, new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP),freeBreakfastType,freeInternetType,freeParkingType,airportShuttleType,fitnessCenterType);
                                        for(int k=0;k<findPriceAndXingjiAndFreeAllTwo.size();k++){
                                            hotelActivityOne.add(findPriceAndXingjiAndFreeAllTwo.get(k));
                                        }
                                    }
                                          
                                            
                                            
                                }
                }
           
            }else if(xingji!=""&&free==""&&price!=""){
                //获得星级的查询条件 
                List<Integer> hotelClass=new ArrayList<Integer>();
                    String xingjiValue = xingji.substring(0, xingji.lastIndexOf(","));
                    
                    String[] xingjisplit = xingjiValue.split(",");
                    
                    Integer [] num=new Integer[xingjisplit.length];
                    for(int i=0;i<num.length;i++){
                        num[i]=Integer.parseInt(xingjisplit[i]);
                    }
                    for(int i=0;i<num.length;i++){
                        hotelClass.add(num[i]);
                    }
                
                    //获得价格的查询条件
                    String priceValue = price.substring(0, price.lastIndexOf(";"));
                    String[] pricesplit = priceValue.split(";");
                        for(int i=0;i<pricesplit.length;i++){
                            String[] split = pricesplit[i].split(",");
                            Integer [] pricenum=new Integer[split.length];
                            for(int j=0;j<pricenum.length;j++){
                                pricenum[j]=Integer.parseInt(split[j]);
                            }
                           if(pricenum.length>1){
                                Integer minPrice = pricenum[0];
                                Integer maxPrice = pricenum[1];
                                List<HotelActivity> findPriceAndXingjiAndFreeAll = _hotelActivityRepository.findPriceAndXingjiAndFreeAll(city.getId(), hotelClass, new BigDecimal(minPrice).setScale(2, BigDecimal.ROUND_HALF_UP), new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP),freeBreakfastType,freeInternetType,freeParkingType,airportShuttleType,fitnessCenterType);
                                      for(int k=0;k<findPriceAndXingjiAndFreeAll.size();k++){
                                          hotelActivityOne.add(findPriceAndXingjiAndFreeAll.get(k));
                                      }
                            }else{
                                    Integer maxPrice = pricenum[0];
                                    BigDecimal maxprice=new BigDecimal(maxPrice);
                                    BigDecimal minprice=new BigDecimal(300);
                                    if(maxprice.compareTo(minprice)==1){
                                        List<HotelActivity> findPriceAndXingjiAndFreeAllOne = _hotelActivityRepository.findPriceAndXingjiAndFreeAllOne(city.getId(), hotelClass, new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP),freeBreakfastType,freeInternetType,freeParkingType,airportShuttleType,fitnessCenterType);
                                        for(int k=0;k<findPriceAndXingjiAndFreeAllOne.size();k++){
                                            hotelActivityOne.add(findPriceAndXingjiAndFreeAllOne.get(k));
                                        }
                                    }else{
                                        List<HotelActivity> findPriceAndXingjiAndFreeAllTwo = _hotelActivityRepository.findPriceAndXingjiAndFreeAllTwo(city.getId(), hotelClass, new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP),freeBreakfastType,freeInternetType,freeParkingType,airportShuttleType,fitnessCenterType);
                                        for(int k=0;k<findPriceAndXingjiAndFreeAllTwo.size();k++){
                                            hotelActivityOne.add(findPriceAndXingjiAndFreeAllTwo.get(k));
                                        }
                                    }
                                }
                        }
            }else if(xingji!=""&&free!=""&&price==""){
                //获得星级的查询条件 
                List<Integer> hotelClass=new ArrayList<Integer>();
                    String xingjiValue = xingji.substring(0, xingji.lastIndexOf(","));
                    
                    String[] xingjisplit = xingjiValue.split(",");
                    
                    Integer [] num=new Integer[xingjisplit.length];
                    for(int i=0;i<num.length;i++){
                        num[i]=Integer.parseInt(xingjisplit[i]);
                    }
                    for(int i=0;i<num.length;i++){
                        hotelClass.add(num[i]);
                    }
                    //获得免费服务查询条件
                    String freeValue = free.substring(0, free.lastIndexOf(","));
                    String[] freesplit = freeValue.split(",");
                    for(int n=0;n<freesplit.length;n++){
                        if(freesplit[n].equals("freeBreakfastType")){
                            freeBreakfastType=new Integer[]{2};
                        }else if(freesplit[n].equals("freeInternetType")){
                            freeInternetType=new Integer[]{2};
                        }else if(freesplit[n].equals("freeParkingType")){
                            freeParkingType=new Integer[]{2};
                        }else if(freesplit[n].equals("airportShuttleType")){
                            airportShuttleType=new Integer[]{2};
                        }else{
                            fitnessCenterType=new Integer[]{2};
                        }
                    }
                    List<HotelActivity> findXingjiAndFreeAllOne = _hotelActivityRepository.findXingjiAndFreeAllOne(city.getId(), hotelClass, freeBreakfastType, freeInternetType, freeParkingType, airportShuttleType, fitnessCenterType);
                   for(int k=0;k<findXingjiAndFreeAllOne.size();k++){
                       hotelActivityOne.add(findXingjiAndFreeAllOne.get(k));
                       }
            }else if(xingji!=""&&free==""&&price==""){
                
                //获得星级的查询条件 
                List<Integer> hotelClass=new ArrayList<Integer>();
                    String xingjiValue = xingji.substring(0, xingji.lastIndexOf(","));
                    
                    String[] xingjisplit = xingjiValue.split(",");
                    
                    Integer [] num=new Integer[xingjisplit.length];
                    for(int i=0;i<num.length;i++){
                        num[i]=Integer.parseInt(xingjisplit[i]);
                    }
                    for(int i=0;i<num.length;i++){
                        hotelClass.add(num[i]);
                    }
                
                    List<HotelActivity> findXingji = _hotelActivityRepository.findXingji(city.getId(), hotelClass);
                    for(int k=0;k<findXingji.size();k++){
                        hotelActivityOne.add(findXingji.get(k));
                        }
            }else if(xingji==""&&free!=""&&price==""){
                
                
                //获得免费服务查询条件
                String freeValue = free.substring(0, free.lastIndexOf(","));
                String[] freesplit = freeValue.split(",");
                for(int n=0;n<freesplit.length;n++){
                    if(freesplit[n].equals("freeBreakfastType")){
                        freeBreakfastType=new Integer[]{2};
                    }else if(freesplit[n].equals("freeInternetType")){
                        freeInternetType=new Integer[]{2};
                    }else if(freesplit[n].equals("freeParkingType")){
                        freeParkingType=new Integer[]{2};
                    }else if(freesplit[n].equals("airportShuttleType")){
                        airportShuttleType=new Integer[]{2};
                    }else{
                        fitnessCenterType=new Integer[]{2};
                    }
                }
             
                List<HotelActivity> findXingjiAndFreeAllOne =_hotelActivityRepository.findFreeAllOne(city.getId(),freeBreakfastType, freeInternetType, freeParkingType, airportShuttleType, fitnessCenterType);
                for(int k=0;k<findXingjiAndFreeAllOne.size();k++){
                    hotelActivityOne.add(findXingjiAndFreeAllOne.get(k));
                    }
                
            }else if(xingji==""&&free==""&&price!=""){
                //获得价格的查询条件
                String priceValue = price.substring(0, price.lastIndexOf(";"));
                String[] pricesplit = priceValue.split(";");
                    for(int i=0;i<pricesplit.length;i++){
                        String[] split = pricesplit[i].split(",");
                        Integer [] pricenum=new Integer[split.length];
                        for(int j=0;j<pricenum.length;j++){
                            pricenum[j]=Integer.parseInt(split[j]);
                        }
                       if(pricenum.length>1){
                            Integer minPrice = pricenum[0];
                            Integer maxPrice = pricenum[1];
                            List<HotelActivity> findPriceAndXingjiAndFreeAll = _hotelActivityRepository.findFreeAllOne1(city.getId(),new BigDecimal(minPrice).setScale(2, BigDecimal.ROUND_HALF_UP), new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP));
                                  for(int k=0;k<findPriceAndXingjiAndFreeAll.size();k++){
                                      hotelActivityOne.add(findPriceAndXingjiAndFreeAll.get(k));
                                  }
                        }else{
                            
                                Integer maxPrice = pricenum[0];
                                BigDecimal maxprice=new BigDecimal(maxPrice);
                                BigDecimal minprice=new BigDecimal(300);
                            
                                
                                if(maxprice.compareTo(minprice)==1){
                                         List<HotelActivity> findPriceAndXingjiAndFreeAllOne = _hotelActivityRepository.findFreeAllOne2(city.getId(),new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP));
                                        for(int k=0;k<findPriceAndXingjiAndFreeAllOne.size();k++){
                                            hotelActivityOne.add(findPriceAndXingjiAndFreeAllOne.get(k));
                                        }
                                        
                                }
                                else{
                                    
                                    List<HotelActivity> findPriceAndXingjiAndFreeAllOne = _hotelActivityRepository.findFreeAllOne3(city.getId(),new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP));
                                    for(int k=0;k<findPriceAndXingjiAndFreeAllOne.size();k++){
                                        hotelActivityOne.add(findPriceAndXingjiAndFreeAllOne.get(k));
                                    }
                                
                                }
                            }
                    }
            }else if(xingji==""&&free!=""&&price!=""){
                //获得免费服务查询条件
                String freeValue = free.substring(0, free.lastIndexOf(","));
                String[] freesplit = freeValue.split(",");
                for(int n=0;n<freesplit.length;n++){
                    if(freesplit[n].equals("freeBreakfastType")){
                        freeBreakfastType=new Integer[]{2};
                    }else if(freesplit[n].equals("freeInternetType")){
                        freeInternetType=new Integer[]{2};
                    }else if(freesplit[n].equals("freeParkingType")){
                        freeParkingType=new Integer[]{2};
                    }else if(freesplit[n].equals("airportShuttleType")){
                        airportShuttleType=new Integer[]{2};
                    }else{
                        fitnessCenterType=new Integer[]{2};
                    }
                }
                
                //获得价格的查询条件
                String priceValue = price.substring(0, price.lastIndexOf(";"));
                String[] pricesplit = priceValue.split(";");
                    for(int i=0;i<pricesplit.length;i++){
                        String[] split = pricesplit[i].split(",");
                        Integer [] pricenum=new Integer[split.length];
                        for(int j=0;j<pricenum.length;j++){
                            pricenum[j]=Integer.parseInt(split[j]);
                        }
                       if(pricenum.length>1){
                            Integer minPrice = pricenum[0];
                            Integer maxPrice = pricenum[1];
                            List<HotelActivity> findPriceAndXingjiAndFreeAll = _hotelActivityRepository.findPriceAndFreeAll1(city.getId(),new BigDecimal(minPrice).setScale(2, BigDecimal.ROUND_HALF_UP), new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP),freeBreakfastType, freeInternetType, freeParkingType, airportShuttleType, fitnessCenterType);
                                  for(int k=0;k<findPriceAndXingjiAndFreeAll.size();k++){
                                      hotelActivityOne.add(findPriceAndXingjiAndFreeAll.get(k));
                                  }
                        }else{
                            Integer maxPrice = pricenum[0];
                            BigDecimal maxprice=new BigDecimal(maxPrice);
                            BigDecimal minprice=new BigDecimal(300);
                            
                            if(maxprice.compareTo(minprice)==1){
                                         List<HotelActivity> findPriceAndXingjiAndFreeAllOne = _hotelActivityRepository.findPriceAndFreeAll2(city.getId(),new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP),freeBreakfastType, freeInternetType, freeParkingType, airportShuttleType, fitnessCenterType);
                                        for(int k=0;k<findPriceAndXingjiAndFreeAllOne.size();k++){
                                            hotelActivityOne.add(findPriceAndXingjiAndFreeAllOne.get(k));
                                        }
                                }
                            else{
                                
                                        List<HotelActivity> findPriceAndXingjiAndFreeAllOne = _hotelActivityRepository.findPriceAndFreeAll3(city.getId(),new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP),freeBreakfastType, freeInternetType, freeParkingType, airportShuttleType, fitnessCenterType);
                                        for(int k=0;k<findPriceAndXingjiAndFreeAllOne.size();k++){
                                            hotelActivityOne.add(findPriceAndXingjiAndFreeAllOne.get(k));
                                        }
                                
                                  }
                            }
                    } 
            }
          
         for(int i=0;i<hotelActivityOne.size();i++){
                HotelActivity activity = hotelActivityOne.get(i);
                   String pernightprice = _hotelActivityRepository.find(activity.getId());
                   activity.setPerNightPrice(new BigDecimal(pernightprice).setScale(2,BigDecimal.ROUND_HALF_UP));
                   hotelActivityList.add(activity);
            }
        model.addAttribute("listHotelPlan", hotelActivityList);
       
        return "/hotelplan/searchList";
    }
    
    
    
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Model model, HttpServletRequest request)
    {
        
        // 获取城市数据/datePlan/create"
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);
       
        String datePlanId = request.getParameter("datePlanId");
        model.addAttribute("datePlanId", datePlanId);
        
        
        String orderId = request.getParameter("orderId");
        Order order = _orderRepository.findOne(Integer.parseInt(orderId));
        model.addAttribute("order", order);
        
        
        String hotelPlanId = request.getParameter("hotelPlanId");
        model.addAttribute("hotelPlanId", hotelPlanId);
        
        List<HotelActivity> hotelActivityList=new ArrayList<HotelActivity>();
        String toCity = request.getParameter("toCity");
        
        model.addAttribute("toCity", toCity);
        
        
        String startDate = request.getParameter("startDate");
       model.addAttribute("startDate", startDate);
        String endDate = request.getParameter("endDate");
        
        model.addAttribute("endDate", endDate);
        String roomNum = request.getParameter("roomNum");
        
        model.addAttribute("roomNum", roomNum);
       
      
       
        
        City city = _cityRepository.findOne(Integer.parseInt(toCity));
        List<HotelActivity> hotelActivity = _hotelActivityRepository.findAll(city.getId());
        for(int i=0;i<hotelActivity.size();i++){
            HotelActivity activity = hotelActivity.get(i);
               String perNightPrice = _hotelActivityRepository.find(activity.getId());
               activity.setPerNightPrice(new BigDecimal(perNightPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
               hotelActivityList.add(activity);
                                                }
        model.addAttribute("listHotelPlan", hotelActivityList);
        return "/hotelplan/searchList";
    }
    
    @RequestMapping(value = "/searchXingji", method = RequestMethod.GET)
    public String searchXingji(Model model, HttpServletRequest request)
    {
        // 获取城市数据/datePlan/create"
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);
        
        
        
        
        
        List<HotelActivity> hotelActivityList=new ArrayList<HotelActivity>();
        String toCity = request.getParameter("amp;toCity");
        model.addAttribute("toCity", toCity);
        String startDate = request.getParameter("amp;startDate");
       model.addAttribute("startDate", startDate);
        String endDate = request.getParameter("amp;endDate");
        
        model.addAttribute("endDate", endDate);
        String roomNum = request.getParameter("amp;roomNum");
        
        model.addAttribute("roomNum", roomNum);
       
        String value = request.getParameter("value");
        City city = _cityRepository.findOne(Integer.parseInt(toCity));
        if(value==null||value.equals("")){
        
        List<HotelActivity> hotelActivity = _hotelActivityRepository.findAll(city.getId());
        for(int i=0;i<hotelActivity.size();i++){
            HotelActivity activity = hotelActivity.get(i);
               String perNightPrice = _hotelActivityRepository.find(activity.getId());
               activity.setPerNightPrice(new BigDecimal(perNightPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
               hotelActivityList.add(activity);
               model.addAttribute("listHotelPlan", hotelActivityList);
               }
        }else{
        String subValue = value.substring(0, value.lastIndexOf(","));
        List<Integer> hotelClass=new ArrayList<Integer>();
        String[] split = subValue.split(",");
        
        Integer [] num=new Integer[split.length];
        for(int i=0;i<num.length;i++){
            num[i]=Integer.parseInt(split[i]);
        }
        
        for(int i=0;i<num.length;i++){
            hotelClass.add(num[i]);
        }
       
        List<HotelActivity> hotelActivity = _hotelActivityRepository.findListHotelClass(city.getId(), hotelClass);
        for(int i=0;i<hotelActivity.size();i++){
            HotelActivity activity = hotelActivity.get(i);
               String perNightPrice = _hotelActivityRepository.find(activity.getId());
               activity.setPerNightPrice(new BigDecimal(perNightPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
               hotelActivityList.add(activity);
               }
        }
        model.addAttribute("listHotelPlan", hotelActivityList);
        return "/hotelplan/searchList";
    }
    
    
   /* @RequestMapping(value = "/searchFree", method = RequestMethod.GET)
    public String searchFree(Model model, HttpServletRequest request)
    {
        
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);
        
        List<HotelActivity> hotelActivityList=new ArrayList<HotelActivity>();
        String toCity = request.getParameter("amp;toCity");
        model.addAttribute("toCity", toCity);
        String startDate = request.getParameter("amp;startDate");
       model.addAttribute("startDate", startDate);
        String endDate = request.getParameter("amp;endDate");
        
        model.addAttribute("endDate", endDate);
        String roomNum = request.getParameter("amp;roomNum");
        
        model.addAttribute("roomNum", roomNum);
       
        String value = request.getParameter("value");
        
        
        String indexValue = value.substring(0, value.lastIndexOf(","));
        String lastValue = value.substring(value.lastIndexOf(",")+1,value.length());
        
        
        City city = _cityRepository.findOne(Integer.parseInt(toCity));
        List<HotelActivity> hotelActivity =new ArrayList<HotelActivity>();
        if(lastValue.equals("freeBreakfastType")){
            hotelActivity=_hotelActivityRepository.findFreeBreakfastTypeId(city.getId(), Integer.parseInt(indexValue));
        }else if(lastValue.equals("freeInternetType")){
            hotelActivity=_hotelActivityRepository.findfreeInternetTypeId(city.getId(),Integer.parseInt(indexValue));
        }else if(lastValue.equals("freeParkingType")){
            hotelActivity=_hotelActivityRepository.findfreeParkingTypeId(city.getId(), Integer.parseInt(indexValue));
        }
        else if(lastValue.equals("airportShuttleType")){
            hotelActivity=_hotelActivityRepository.findairportShuttleTypeId(city.getId(), Integer.parseInt(indexValue));
        }else{
            hotelActivity=_hotelActivityRepository.findfitnessCenterTypeId(city.getId(), Integer.parseInt(indexValue));
        }
     
        for(int i=0;i<hotelActivity.size();i++){
            HotelActivity activity = hotelActivity.get(i);
               String perNightPrice = _hotelActivityRepository.find(activity.getId());
               activity.setPerNightPrice(new BigDecimal(perNightPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
               hotelActivityList.add(activity);
               }
        model.addAttribute("listHotelPlan", hotelActivityList);
        return "/hotel/searchList";
    }*/
    
  /*  @RequestMapping(value = "/searchPrice", method = RequestMethod.GET)
    public String searchPrice(Model model, HttpServletRequest request)
    {
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);
        
        
        
        
        
        List<HotelActivity> hotelActivityList=new ArrayList<HotelActivity>();
        String toCity = request.getParameter("amp;toCity");
        model.addAttribute("toCity", toCity);
        String startDate = request.getParameter("amp;startDate");
       model.addAttribute("startDate", startDate);
        String endDate = request.getParameter("amp;endDate");
        
        model.addAttribute("endDate", endDate);
        String roomNum = request.getParameter("amp;roomNum");
        
        model.addAttribute("roomNum", roomNum);
       
        String value = request.getParameter("value");
        City city = _cityRepository.findOne(Integer.parseInt(toCity));
        
        String subValue = value.substring(0, value.lastIndexOf(","));
        String[] split = subValue.split(",");
        Integer [] num=new Integer[split.length];
        List<HotelActivity> hotelActivity=new ArrayList<HotelActivity>();
        for(int i=0;i<num.length;i++){
            num[i]=Integer.parseInt(split[i]);
        }
        
        
        if(num.length>1){
        Integer minPrice = num[0];
        Integer maxPrice = num[1];
        hotelActivity = _hotelActivityRepository.findPrice(city.getId(), new BigDecimal(minPrice).setScale(2, BigDecimal.ROUND_HALF_UP), new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP));
        
        }else{
            Integer maxPrice = num[0];
           hotelActivity = _hotelActivityRepository.findMaxPrice(city.getId(), new BigDecimal(maxPrice).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
        
        for(int i=0;i<hotelActivity.size();i++){
            HotelActivity activity = hotelActivity.get(i);
               String perNightPrice = _hotelActivityRepository.find(activity.getId());
               activity.setPerNightPrice(new BigDecimal(perNightPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
               hotelActivityList.add(activity);
               }
       
        model.addAttribute("listHotelPlan", hotelActivityList);
        return "/hotel/searchList";
    }*/
  
    /**
     * 酒店新增
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Model model, HttpServletRequest request) throws Exception
    {
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);
        String all="";
        String result="";
        SimpleDateFormat formate=new SimpleDateFormat("yy-MM-dd");
        
        String datePlanId = request.getParameter("datePlanId");
        DatePlan dateplan = _datePlanRepository.findOne(Integer.parseInt(datePlanId));
        
       /* String roomNum = request.getParameter("roomNum");
        BigDecimal roomnum=new BigDecimal(roomNum);*/
        //入住日期
        String startDate = request.getParameter("startDate");
        //退房日期
        String endDate = request.getParameter("endDate");
        

        Date  startDay= formate.parse(startDate);
        Date  endDay =formate.parse(endDate);  
       long day = (startDay.getTime() - endDay.getTime()) / (24 * 60 * 60 * 1000);
       long allDay = Math.abs(day);
       BigDecimal Day=new BigDecimal(allDay); 
        //获得日规划主键  
        String orderId = request.getParameter("orderId");
        Order order = _orderRepository.findOne(Integer.parseInt(orderId));
        model.addAttribute("orderId", order.getId());      
        String[] roomTypeIds=request.getParameter("roomTypeIds").split(",");
        String[] roomCounts=request.getParameter("roomCounts").split(",");
        String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");
      String hotelActivityId=request.getParameter("hotelActivityId");
        
      HotelActivity hotelActivity=_hotelActivityRepository.findOne(Integer.parseInt(hotelActivityId));
            String totalAmount=request.getParameter("totalprices");
            BigDecimal totalamount=new BigDecimal(totalAmount);
            
            
            
            
            HotelPlan hotelPlan=new HotelPlan();
            hotelPlan.setCheckInDate(startDate);
            hotelPlan.setCheckOutDate(endDate);
            hotelPlan.setCreateTime(new Date());
            hotelPlan.setCity(hotelActivity.getCity());
            hotelPlan.setHotelChaName(hotelActivity.getHotelChName());
            hotelPlan.setHotelEngName(hotelActivity.getHotelEngName());
            hotelPlan.setHotelActivity(hotelActivity);
            hotelPlan.setSubtotalAmount(totalamount.multiply(Day));
            hotelPlan.setDatePlan(dateplan);
            _hotelPlanRepository.save(hotelPlan);
            for(int k=0;k<roomTypeIds.length;k++){
         HotelRoomType hotelRoomType = _hotelRoomTypeRepository.findOne(Integer.parseInt(roomTypeIds[k]));
         
         BigDecimal subtotalamounts=new BigDecimal(subTotalAmounts[k]);
         
            HotelPlanRoom hotelPlanRoom=new HotelPlanRoom();
            hotelPlanRoom.setHotelPlan(hotelPlan);
            hotelPlanRoom.setHotelRoomType(hotelRoomType);
            hotelPlanRoom.setCreateTime(new Date());
            hotelPlanRoom.setRoomCount(Integer.parseInt(roomCounts[k]));
            hotelPlanRoom.setSalePrice(subtotalamounts.multiply(Day));
            _hotelPlanRoomRepository.save(hotelPlanRoom);
            } 
                             return "redirect:/order/" + orderId + "/plan?_step1";
    }
   /**
    * 酒店修改 
    * @param hotelPlanId
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(@RequestParam("orderId") Integer orderId,HttpServletRequest request,Model model){
       /* Integer hotelActivityId=Integer.parseInt(request.getParameter("hotelActivityId"));
         model.addAttribute("hotelActivityId", hotelActivityId);*/
        Integer hotelPlanId = Integer.parseInt(request.getParameter("hotelPlanId"));
        model.addAttribute("hotelPlanId",hotelPlanId);
        Integer datePlanId = Integer.parseInt(request.getParameter("datePlanId"));
        Integer hotelPlanRoomId = Integer.parseInt(request.getParameter("hotelPlanRoomId"));
        HotelPlan hotelPlan = _hotelPlanRepository.findOne(hotelPlanId);
        HotelPlanRoom hotelPlanRoom= _hotelPlanRoomRepository.findOne(hotelPlanRoomId);
        model.addAttribute("hotelPlan",hotelPlan);
        model.addAttribute("hotelPlanRoom",hotelPlanRoom);
        model.addAttribute("hotelPlanRoomId",hotelPlanRoomId);
        model.addAttribute("datePlanId",datePlanId);
        model.addAttribute("orderId",orderId);
        return "/hotelplan/updatehotelType";
    }
    /*
     * 修改酒店规划
     */
    @RequestMapping(value="/updateHotelPlan",method = RequestMethod.POST)
    public String updateHotelPlan(@RequestParam("orderId") Integer orderId,HttpServletRequest request,Model model) throws Exception { 
        Integer roomCount =Integer.parseInt(request.getParameter("roomCount"));
        BigDecimal salePrice =new BigDecimal(request.getParameter("salePrice"));
        Integer id = Integer.parseInt(request.getParameter("hotelPlanRoomId"));
        HotelPlan hotepPlan = _hotelPlanRepository.findOne(Integer.parseInt( request.getParameter("hotelPlanId")));
        SimpleDateFormat formate=new SimpleDateFormat("yy-MM-dd");
        
       String checkInDate = hotepPlan.getCheckInDate();
       String checkOutDate = hotepPlan.getCheckOutDate();
       
       Date  startDay= formate.parse(checkInDate);
       Date  endDay =formate.parse(checkOutDate);  
        
        long day = (startDay.getTime() - endDay.getTime()) / (24 * 60 * 60 * 1000);
        long allDay = Math.abs(day);
        BigDecimal Day=new BigDecimal(allDay); 
        
        HotelPlanRoom hotelPlanRoom = _hotelPlanRoomRepository.findOne(id);
        hotelPlanRoom.setRoomCount(roomCount);
        hotelPlanRoom.setSalePrice(salePrice.multiply(Day));
        _hotelPlanRoomRepository.save(hotelPlanRoom);
        HotelPlan hotelPlan = hotelPlanRoom.getHotelPlan();
        hotelPlan.setSubtotalAmount(new BigDecimal(0.0));
        List<HotelPlanRoom> hotelPlanRooms=hotelPlan.getHotelPlanRooms();
        for (int i = 0; i < hotelPlanRooms.size(); i++) {
            hotelPlan.setSubtotalAmount(hotelPlan.getSubtotalAmount().add(hotelPlanRooms.get(i).getSalePrice()));
        }
        _hotelPlanRepository.save(hotelPlan);
        return "redirect:/order/" + orderId + "/plan?_step1";
    }
    
    
    
    
    
    /*
     * 通过酒店规划id查看酒店规划详情
     */
    @RequestMapping(value = "/hotelDetail", method = RequestMethod.GET)
    public String hotelDetail(@RequestParam("hotelPlanId") Integer hotelPlanId,HttpServletRequest request,Model model){
    	HotelPlan hotelPlan = _hotelPlanRepository.findOne(hotelPlanId);
    	model.addAttribute("hotelPlan",hotelPlan);
    	return "/hotelplan/showHotelPlanDetail";
    }
    
    
    public long getTwoDay(Date begin_date, Date end_date) {
        long day = 0;
        try {
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
         String sdate = format.format(Calendar.getInstance().getTime());
         
         if (begin_date == null) {
          begin_date = format.parse(sdate);
         }
         if (end_date == null) {
          end_date = format.parse(sdate);
         }
         day = (end_date.getTime() - begin_date.getTime())/(24 * 60 * 60 * 1000);
        } catch (Exception e) {
         return -1;
        }
        return day;
       }
    
    
    public long fromDateStringToLong(String inVal) { //此方法计算时间毫秒
        Date date = null;   //定义时间类型       
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-mm-dd"); 
        try { 
        date = inputFormat.parse(inVal); //将字符型转换成日期型
        } catch (Exception e) { 
        e.printStackTrace(); 
        } 
        return date.getTime();   //返回毫秒数
        } 
    
}

















































































/*package com.jzeen.travel.admin.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.HotelPlan;
import com.jzeen.travel.data.entity.SupplierPriceRule;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.HotelPlanRepository;
import com.jzeen.travel.data.repository.SupplierPriceRuleRepository;

// 酒店规划
@Controller
@RequestMapping("/hotelPlan")
public class HotelPlanController
{
    @Autowired
    private DatePlanRepository _datePlanRepository;

    @Autowired
    private HotelPlanRepository _hotelPlanRepository;

    @Autowired
    private SupplierPriceRuleRepository _supplierPriceRuleRepository;

    @Autowired
    private OrderService _orderService;

    @ResponseBody
    @RequestMapping(value = "/findByDatePlanOrderId", method = RequestMethod.GET)
    public List<HotelPlan> findByDatePlanOrderId(@RequestParam Integer orderId)
    {
        List<HotelPlan> hotelPlans = _hotelPlanRepository.findByDatePlanOrderId(orderId);
        return hotelPlans;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editInit(Model model, HttpServletRequest request)
    {
        // 获取日期规划
        String dataPlanId = request.getParameter("dataPlanId");
        DatePlan datePlan = _datePlanRepository.findOne(Integer.parseInt(dataPlanId));
        model.addAttribute(datePlan);
        model.addAttribute("order", datePlan.getOrder());

        List<SupplierPriceRule> supplierPriceRules = _supplierPriceRuleRepository.getBySupplierType("酒店");
        model.addAttribute("supplierPriceRules", supplierPriceRules);

        // 喜欢的酒店类型
        model.addAttribute("hotelTypeLike", _orderService.getHotelTypeLikeByOrderId(datePlan.getOrder().getId()));

        //年龄
        model.addAttribute("accompanyMemberAge", _orderService.getAccompanyMemberAgeStr(datePlan.getOrder().getId()));

        return "/order/editHotelPlan";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute DatePlan datePlan) {
        DatePlan datePlanInDb = _datePlanRepository.findOne(datePlan.getId());
        HotelPlan hotelPlan = datePlan.getHotelPlan();
        if (datePlanInDb.getHotelPlan() == null) {
            // 新增
            hotelPlan.setDatePlan(datePlanInDb);
            hotelPlan.setCreateTime(new Date());

            //计算销售价格（总价）
            hotelPlan.setHotelSalePrice(this.caculateHotelSalePrice(hotelPlan, hotelPlan.getHotelRoomCount()));

            // 保存酒店规划
            _hotelPlanRepository.save(hotelPlan);
        } else {
            // 修改
            HotelPlan hotelPlanInDb = datePlanInDb.getHotelPlan();
            hotelPlanInDb.setSupplierPriceRule(hotelPlan.getSupplierPriceRule());
            hotelPlanInDb.setHotelName(hotelPlan.getHotelName());
            hotelPlanInDb.setHotelAddress(hotelPlan.getHotelAddress());
            hotelPlanInDb.setHotelTel(hotelPlan.getHotelTel());
            hotelPlanInDb.setHotelRank(hotelPlan.getHotelRank());
            hotelPlanInDb.setHotelCostPrice(hotelPlan.getHotelCostPrice());
            hotelPlanInDb.setHotelRoomCount(hotelPlan.getHotelRoomCount());


            //计算销售价格（总价）
            hotelPlanInDb.setHotelSalePrice(this.caculateHotelSalePrice(hotelPlanInDb, hotelPlan.getHotelRoomCount()));

            // 保存酒店规划
            _hotelPlanRepository.save(hotelPlanInDb);
        }

        Integer orderId = datePlanInDb.getOrder().getId();
        return "redirect:/order/" + orderId + "/plan?_step3";
    }

    //修改景点规划，添加内部订单号
    @RequestMapping(value = "/updateHotelPlan/{id}", method = RequestMethod.GET)
    public String updateHotelPlan(@PathVariable Integer id, Model model) {
        HotelPlan hotelPlan = _hotelPlanRepository.findOne(id);
        model.addAttribute("hotelPlan", hotelPlan);

        return "/datePlan/updateHotel";
    }

    @RequestMapping(value = "/updateSupplierOrderNumber", method = RequestMethod.POST)
    public String updateSupplierOrderNumber(HotelPlan hotelPlan) {

        HotelPlan newHotelPlan = _hotelPlanRepository.findOne(hotelPlan.getId());
        newHotelPlan.setSupplierOrderNumber(hotelPlan.getSupplierOrderNumber());
        _hotelPlanRepository.save(newHotelPlan);

        Integer orderId = newHotelPlan.getDatePlan().getOrder().getId();
        return "redirect:/order/" + orderId + "/planedInfo";
    }

    //计算酒店价格
    private BigDecimal caculateHotelSalePrice(HotelPlan hotelPlan, Integer count) {
        if (hotelPlan.getHotelCostPrice() != null) {
            if (hotelPlan.getSupplierPriceRule() != null && hotelPlan.getSupplierPriceRule().getPriceCoefficient() != null) {
                BigDecimal priceCoefficient = hotelPlan.getSupplierPriceRule().getPriceCoefficient();
                return priceCoefficient.multiply(hotelPlan.getHotelCostPrice()).multiply(new BigDecimal(count));
            }
        }

        // 如果计算失败，则返回null值
        return null;
    }

}
*/