

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
import com.jzeen.travel.data.entity.RouteDays;
import com.jzeen.travel.data.entity.RouteHotelPlan;
import com.jzeen.travel.data.entity.RouteHotelPlanRoom;
import com.jzeen.travel.data.entity.SpotPlan;
import com.jzeen.travel.data.entity.SpotPlanTicket;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.HotelActivityRepository;
import com.jzeen.travel.data.repository.HotelPlanRepository;
import com.jzeen.travel.data.repository.HotelPlanRoomRepository;
import com.jzeen.travel.data.repository.HotelRoomTypeRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.RouteDaysRepository;
import com.jzeen.travel.data.repository.RouteHotelPlanRepository;
import com.jzeen.travel.data.repository.RouteHotelPlanRoomRepository;
@Controller
@RequestMapping("/routehotelPlan")
public class RouteHotelPlanController
{
    @Autowired
    HotelRoomTypeRepository _hotelRoomTypeRepository;
    @Autowired
    private CityRepository _cityRepository;
 
    
   
    @Autowired
    HotelActivityRepository  _hotelActivityRepository;
 
    @Autowired
    OrderRepository   _orderRepository;
    @Autowired
    RouteDaysRepository  _routeDaysRepository;
    @Autowired
    RouteHotelPlanRepository   _routeHotelPlanRepository;
    @Autowired
    RouteHotelPlanRoomRepository _routeHotelPlanRoomRepository;
    
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, HttpServletRequest request)
    {
        
        
        String routeId = request.getParameter("routeId");
        model.addAttribute("routeId", routeId);
        
        
        String routedayId = request.getParameter("routedayId");
        model.addAttribute("routedayId", routedayId);

        // 获取城市数据/datePlan/create"
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);
        return "/routehotelplan/list";
    }
    
    
    //酒店房间类型
    
    
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    public String type(Model model, HttpServletRequest request)
    {
       
   String routeId = request.getParameter("routeId");
     model.addAttribute("routeId", routeId);
     
     String routedayId = request.getParameter("routedayId");
     model.addAttribute("routedayId", routedayId);
     
    
    
    String toCity = request.getParameter("toCity");
    model.addAttribute("toCity", toCity);
    
    String startDate = request.getParameter("startDate");
    model.addAttribute("startDate", startDate);
   
   
    String endDate = request.getParameter("endDate");
    model.addAttribute("endDate", endDate);
    

   
    String id = request.getParameter("id");
    model.addAttribute("hotelId", id);
        return "/routehotelplan/hotelTypeList";
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
       
        
        
        String routeId = request.getParameter("routeId");
        model.addAttribute("routeId", routeId);
        String routedayId = request.getParameter("routedayId");
        model.addAttribute("routedayId", routedayId);
      
        
        
        
        List<HotelActivity> hotelActivityList=new ArrayList<HotelActivity>();
        String toCity = request.getParameter("toCity");
        City cityName = _cityRepository.findOne(Integer.parseInt(toCity));
        model.addAttribute("toCity", toCity);
        model.addAttribute("cityName", cityName.getCityName());
        
        
        String startDate = request.getParameter("startDate");
        model.addAttribute("startDate", startDate);
        String endDate = request.getParameter("endDate");
        model.addAttribute("endDate", endDate);
        
       
      
       
        
        City city = _cityRepository.findOne(Integer.parseInt(toCity));
        List<HotelActivity> hotelActivity = _hotelActivityRepository.findAll(city.getId());
        for(int i=0;i<hotelActivity.size();i++){
            HotelActivity activity = hotelActivity.get(i);
               String perNightPrice = _hotelActivityRepository.find(activity.getId());
               activity.setPerNightPrice(new BigDecimal(perNightPrice).setScale(2,BigDecimal.ROUND_HALF_UP));
               hotelActivityList.add(activity);
                                                }
        model.addAttribute("listHotelPlan", hotelActivityList);
        return "/routehotelplan/searchList";
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
        
        //线路id
        String routeId = request.getParameter("amp;routeId");
        model.addAttribute("routeId", routeId);
        
        //线路日期id
        String routedayId = request.getParameter("amp;routedayId");
       model.addAttribute("routedayId", routedayId);
       
       
       
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
       
        return "/routehotelplan/searchList";
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
    
    
    /*
     * 删除线路酒店规划
     */
    @RequestMapping(value = "/delRouteHotelPlan")
    public String deleteHotelPlan(@RequestParam("routeId") Integer routeId,@RequestParam("routehotelPlanId") Integer routehotelPlanId,@RequestParam("hotelPlanRoomId") Integer hotelPlanRoomId,HttpServletRequest request,Model model){
        RouteHotelPlan routehotelPlan = _routeHotelPlanRepository.findOne(routehotelPlanId);
       /* List<HotelPlanRoom> hotelPlanRooms = _hotelPlanRoomRepository.findByHotelPlan(hotelPlan);*/
        RouteHotelPlanRoom routehotelPlanRoom = _routeHotelPlanRoomRepository.findOne(hotelPlanRoomId);
        routehotelPlan.setSubtotalAmount(routehotelPlan.getSubtotalAmount().subtract(routehotelPlanRoom.getSalePrice()));
        _routeHotelPlanRepository.save(routehotelPlan);
        _routeHotelPlanRoomRepository.delete(routehotelPlanRoom);
      /*  if(hotelPlanRooms.size()==1){
            _hotelPlanRepository.delete(hotelPlan);
        }*/

        return "redirect:/route/detail/" + routeId ;
    }
  
    /**
     * 线路酒店新增
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
        
        String routedayId = request.getParameter("routedayId");
         
        
        //获得线路日期主键id
        RouteDays routeDays = _routeDaysRepository.findOne(Integer.parseInt(routedayId));
        
        //获得路线id
        String routeId = request.getParameter("routeId");

        //入住日期
        String startDate = request.getParameter("startDate");
        Date startdate = formate.parse(startDate);
        //退房日期
        String endDate = request.getParameter("endDate");
        Date enddate = formate.parse(endDate);

        Date  startDay= formate.parse(startDate);
        Date  endDay =formate.parse(endDate);  
       long day = (startDay.getTime() - endDay.getTime()) / (24 * 60 * 60 * 1000);
       long allDay = Math.abs(day);
       BigDecimal Day=new BigDecimal(allDay); 
         
        String[] roomTypeIds=request.getParameter("roomTypeIds").split(",");
        String[] roomCounts=request.getParameter("roomCounts").split(",");
        String[] subTotalAmounts =request.getParameter("subTotalAmounts").split(",");
      String hotelActivityId=request.getParameter("hotelActivityId");
        
      HotelActivity hotelActivity=_hotelActivityRepository.findOne(Integer.parseInt(hotelActivityId));
            String totalAmount=request.getParameter("totalprices");
            BigDecimal totalamount=new BigDecimal(totalAmount);
            
            
            
            
            RouteHotelPlan routehotelPlan=new RouteHotelPlan();
            routehotelPlan.setCheckInDate(startdate);
            routehotelPlan.setCheckOutDate(enddate);
            routehotelPlan.setCreateTime(new Date());
            routehotelPlan.setCity(hotelActivity.getCity());
            routehotelPlan.setHotelChaName(hotelActivity.getHotelChName());
            routehotelPlan.setHotelEngName(hotelActivity.getHotelEngName());
            routehotelPlan.setHotelActivity(hotelActivity);
            routehotelPlan.setSubtotalAmount(totalamount.multiply(Day));
            routehotelPlan.setRouteDays(routeDays);;
            _routeHotelPlanRepository.save(routehotelPlan);
            for(int k=0;k<roomTypeIds.length;k++){
         HotelRoomType hotelRoomType = _hotelRoomTypeRepository.findOne(Integer.parseInt(roomTypeIds[k]));
         
         BigDecimal subtotalamounts=new BigDecimal(subTotalAmounts[k]);
         
            RouteHotelPlanRoom routehotelPlanRoom=new RouteHotelPlanRoom();
            routehotelPlanRoom.setRouteHotelPlan(routehotelPlan);
            routehotelPlanRoom.setHotelRoomType(hotelRoomType);
            routehotelPlanRoom.setCreateTime(new Date());
            routehotelPlanRoom.setRoomCount(Integer.parseInt(roomCounts[k]));
            routehotelPlanRoom.setSalePrice(subtotalamounts.multiply(Day));
            _routeHotelPlanRoomRepository.save(routehotelPlanRoom);
            } 
                             return "redirect:/route/detail/" + routeId;
    }
   /**
    * 酒店修改 
    * @param hotelPlanId
    * @param request
    * @param model
    * @return
    */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request,Model model){
   
        Integer routeId = Integer.parseInt(request.getParameter("routeId"));
        model.addAttribute("routeId",routeId);
        Integer routedayId = Integer.parseInt(request.getParameter("routedayId"));
        Integer hotelPlanRoomId = Integer.parseInt(request.getParameter("hotelPlanRoomId"));
        Integer hotelPlanId = Integer.parseInt(request.getParameter("hotelPlanId"));
        model.addAttribute("hotelPlanId",hotelPlanId);
        
        RouteHotelPlan routeHotelPlan = _routeHotelPlanRepository.findOne(hotelPlanId);
        RouteHotelPlanRoom routeHotelPlanRoom= _routeHotelPlanRoomRepository.findOne(hotelPlanRoomId);
        model.addAttribute("routeHotelPlan",routeHotelPlan);
        model.addAttribute("routeHotelPlanRoom",routeHotelPlanRoom);
        model.addAttribute("routehotelPlanRoomId",hotelPlanRoomId);
        model.addAttribute("routedayId",routedayId);
        model.addAttribute("routeId",routeId);
        return "/routehotelplan/updatehotelType";
    }
    /*
     * 修改酒店规划
     */
    @RequestMapping(value="/updateHotelPlan",method = RequestMethod.POST)
    public String updateHotelPlan(HttpServletRequest request,Model model) throws Exception { 
        String routeId = request.getParameter("routeId");
        Integer roomCount =Integer.parseInt(request.getParameter("roomCount"));
        BigDecimal salePrice =new BigDecimal(request.getParameter("salePrice"));
        Integer id = Integer.parseInt(request.getParameter("routehotelPlanRoomId"));
        RouteHotelPlan routehotepPlan = _routeHotelPlanRepository.findOne(Integer.parseInt( request.getParameter("routehotelPlanId")));
        SimpleDateFormat formate=new SimpleDateFormat("yy-MM-dd");
        
       Date checkInDate =routehotepPlan.getCheckInDate();
       Date checkOutDate = routehotepPlan.getCheckOutDate();
       
        
        
        long day = (checkInDate.getTime() - checkOutDate.getTime()) / (24 * 60 * 60 * 1000);
        long allDay = Math.abs(day);
        BigDecimal Day=new BigDecimal(allDay); 
        
        RouteHotelPlanRoom routehotelPlanRoom = _routeHotelPlanRoomRepository.findOne(id);
        routehotelPlanRoom.setRoomCount(roomCount);
        routehotelPlanRoom.setSalePrice(salePrice.multiply(Day));
        _routeHotelPlanRoomRepository.save(routehotelPlanRoom);
        RouteHotelPlan routeHotelPlan = routehotelPlanRoom.getRouteHotelPlan();
        routeHotelPlan.setSubtotalAmount(new BigDecimal(0.0));
      List<RouteHotelPlanRoom> hotelPlanRooms = routeHotelPlan.getHotelPlanRooms();
        for (int i = 0; i < hotelPlanRooms.size(); i++) {
            routeHotelPlan.setSubtotalAmount(routeHotelPlan.getSubtotalAmount().add(hotelPlanRooms.get(i).getSalePrice()));
        }
        _routeHotelPlanRepository.save(routeHotelPlan);
        return "redirect:/route/detail/" + routeId ;
    }
    
    
    
    
    
    /*
     * 通过酒店规划id查看酒店规划详情
     */
    @RequestMapping(value = "/hotelDetail", method = RequestMethod.GET)
    public String hotelDetail(@RequestParam("routehotelPlanId") Integer routehotelPlanId,HttpServletRequest request,Model model){
    	RouteHotelPlan routehotelPlan = _routeHotelPlanRepository.findOne(routehotelPlanId);
    	model.addAttribute("routehotelPlan",routehotelPlan);
    	return "/routehotelplan/showHotelPlanDetail";
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