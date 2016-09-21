package com.jzeen.travel.website.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.AirPlanInfo;
import com.jzeen.travel.data.entity.Airline;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.repository.AirlineRepository;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.entity.AirPlanDetail;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.FilghtPlan;
import com.jzeen.travel.data.entity.FlightDetails;
import com.jzeen.travel.data.entity.GuideCarManage;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.SpotThemeRelate;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.SupplierPriceRule;
import com.jzeen.travel.data.entity.ThemeActive;
import com.jzeen.travel.data.repository.DatePlanRepository;
import com.jzeen.travel.data.repository.FilghtDetailsRepository;
import com.jzeen.travel.data.repository.FilghtPlanRepository;
import com.jzeen.travel.data.repository.SupplierPriceRuleRepository;
import com.jzeen.travel.data.repository.SupplierRepository;

@Controller
@RequestMapping("/flight")
public class FlightPlanController
{

    @Autowired
    private CityRepository _cityRepository;
    @Autowired
    AirlineRepository  _airlineRepository;

    @Autowired
    FilghtDetailsRepository _FilghtDetailsRepository;
    
    @Autowired
    FilghtPlanRepository _FilghtPlanRepository;
    
    @Autowired
    SupplierPriceRuleRepository _supplierPriceRuleRepository;

    @Autowired
    DatePlanRepository _datePlanRepository;
    
   
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model,HttpServletRequest request)
    {
        // 获取城市数据/datePlan/create"
        List<City> citys = _cityRepository.findAll();
        model.addAttribute("citys", citys);
        
        //获得航空公司
        
        List<Airline> AirlineList = _airlineRepository.findAll();
        
        String filghtPlanId = request.getParameter("filghtPlanId");
       
        //获得日期规划id
        String datePlanId = request.getParameter("datePlanId");
 /*       Integer orderId = Integer.parseInt(request.getParameter("orderId"));
        model.addAttribute("orderId", orderId);*/
        model.addAttribute("filghtPlanId", filghtPlanId);
        model.addAttribute("datePlanId", datePlanId);
        model.addAttribute("airline", AirlineList);
        return "/home/flightticket";
    }
    

    
    /**
     * 查询
     */
    
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public String search(Model model,HttpServletRequest request)
    {
        
        
        //获得出发城市
        String fromCity = request.getParameter("fromCity");
        City fromcity = _cityRepository.findOne(Integer.parseInt(fromCity));
        
        //获得到达城市
        String toCity = request.getParameter("toCity");
        City tocity = _cityRepository.findOne(Integer.parseInt(toCity));
        
        
        
        //获得日期规划ID
        String datePlanId = request.getParameter("datePlanId");
        
        //获得flightId
        String filghtPlanId = request.getParameter("filghtPlanId");
        
       
        
        
        //获得供应商价格系数
        String priceCoefficientID = request.getParameter("priceCoefficientID");
        
        //获得开始日期
        String startDate = request.getParameter("startDate");
        
        
      
        //获得返程日期
        String returnDate = request.getParameter("returnDate");
        
        
        //获得行程类型    单程/返程
        
        String tripType = request.getParameter("xingcheng");
        
        
        
        //获得返程时间
        String returndatetime=request.getParameter("returndatetime");
        
        //获得人数
        
        //获得成人
        String peopleNum = request.getParameter("peopleNum");
        if(peopleNum!=""){
            model.addAttribute("peopleNum", peopleNum);
        }
        
        
      //获得小孩
        String peopleNum1 = request.getParameter("peopleNum1");
       if(peopleNum1!=""){
         model.addAttribute("peopleNum", peopleNum1);
        }
        
        
        //获得人员类型
        String peopleType = request.getParameter("peopleType");
        
        //获得航程类型
        String xingcheng = request.getParameter("xingcheng");
        
        model.addAttribute("filghtPlanId", filghtPlanId);
        model.addAttribute("datePlanId", datePlanId);
        model.addAttribute("xingcheng", xingcheng);
        model.addAttribute("returnDate", returnDate);
        model.addAttribute("priceCoefficientID", priceCoefficientID);
        model.addAttribute("peopleType", peopleType);
        model.addAttribute("flightType", xingcheng);
        model.addAttribute("returndatetime", returndatetime);
        /**
         * 查询标示
         */
        model.addAttribute("iden", "search");
        
        
        List<AirPlanInfo> list=new ArrayList<AirPlanInfo>();
        List<AirPlanDetail>  listDetail=new ArrayList<AirPlanDetail>();
      
        
        //获得航空公司
        String airline = request.getParameter("airline");
       /* Airline airLine = _airlineRepository.findOne(Integer.parseInt(airline));*/
        
        
        String a = request.getParameter("vehicleInfos");
        if(a!=""){
        JSONArray jsonArray=JSONArray.fromObject(a);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            AirPlanInfo airPlanInfo=new AirPlanInfo();
            
            
            //出发机场代码
           String startAirport = jsonObject.getString("startAirport");
           airPlanInfo.setStartAirport(startAirport);
            
            //到机场代码
           String arriveAirport = jsonObject.getString("arriveAirport");
           airPlanInfo.setArriveAirport(arriveAirport);
            
            //保存单价
            double double1 = jsonObject.getDouble("costPrice");
            BigDecimal costPrice=new BigDecimal(double1);
            airPlanInfo.setCostPrice(costPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
            //保存出发城市和到达城市
            
            if(tripType.equals("返程")){
            if((i+1)%2!=0){
            airPlanInfo.setFromCity(fromcity.getCityName());
            airPlanInfo.setToCity(tocity.getCityName());
            }else{
            airPlanInfo.setFromCity(tocity.getCityName());
            airPlanInfo.setToCity(fromcity.getCityName());
                }
            }
            else{
                airPlanInfo.setFromCity(fromcity.getCityName());
                airPlanInfo.setToCity(tocity.getCityName());
            }
            
            //保存航空公司
            
            airPlanInfo.setAirline(jsonObject.getString("airline"));
            //保存出发时间和到达时间
            
            
            airPlanInfo.setDepartureTime(jsonObject.getString("departureTime")); 
            airPlanInfo.setArrivalTime(jsonObject.getString("arrivalTime"));
            
            
            
            
            //保存舱位
            airPlanInfo.setRank(jsonObject.getString("rank"));
            //保存总飞行时间
            airPlanInfo.setTotalTime(jsonObject.getString("elapsedTime"));
            //航空公司代码
            airPlanInfo.setAirlineCode(airline);
            //获得航班号
            airPlanInfo.setVehicleNumber(jsonObject.getString("vehicleNumber"));
            
            
            //获得机型
            airPlanInfo.setAirEquipType(jsonObject.getString("airEquipType"));
            airPlanInfo.setStartAirport(jsonObject.getString("startAirport"));
            airPlanInfo.setArriveAirport(jsonObject.getString("arriveAirport"));
           if(xingcheng.equals("单程")){
               //    单程
               model.addAttribute("single","出发城市:"+fromcity.getCityName()+" "+jsonObject.getString("startAirport")+"->"+"到达城市:"+tocity.getCityName()+" "+jsonObject.getString("arriveAirport"));
           }else{
               //返程
               model.addAttribute("single","出发城市:"+fromcity.getCityName()+" "+jsonObject.getString("arriveAirport")+"->"+"到达城市:"+tocity.getCityName()+" "+jsonObject.getString("startAirport"));
               model.addAttribute("return","出发城市:"+tocity.getCityName()+" "+jsonObject.getString("startAirport")+"->"+"到达城市:"+fromcity.getCityName()+" "+jsonObject.getString("arriveAirport"));
           } 
            
            
           
            //保存静听次数
            airPlanInfo.setLengthSeg(jsonObject.getInt("lengthSeg"));
            
            airPlanInfo.setSummary(jsonObject.getString("summary"));
            list.add(airPlanInfo);
        }
       model.addAttribute("vehicleInfos", list);
        }
        else{
        model.addAttribute("vehicleInfos", "");
        }
        return "/home/flightsearchdetail";
    }
    /**
     * 返程查询
     */
    
    @RequestMapping(value = "/returnSearch",method = RequestMethod.POST)
    public String returnSearch(Model model,HttpServletRequest request)
    {
        String vehicleInfos = request.getParameter("info");
        
        
        //获得日期规划id
        model.addAttribute("datePlanId",request.getParameter("datePlanId"));
        
        //获得飞机id
        String filghtPlanId = request.getParameter("filghtPlanId");
          model.addAttribute("filghtPlanId", filghtPlanId);
        
        //获得人数
      model.addAttribute("peopleNum", request.getParameter("peopleNum"));
        
        //获得供应商汇率
      model.addAttribute("priceCoefficientID",request.getParameter("priceCoefficientID"));
        
        //获得人员类型
       model.addAttribute("peopleType",request.getParameter("peopleType") );
        
        //获得航行类型(单程，返程)
       model.addAttribute("flightType",request.getParameter("flightType"));
        
       //获得返程类型
       
       
       String xingcheng = request.getParameter("xingcheng");
       model.addAttribute("xingcheng", xingcheng);
       /**
        * 查询标示
        */
       model.addAttribute("iden", "returnsearch");
       
       
       
      List<AirPlanInfo> list=new ArrayList<AirPlanInfo>();
        
        if(vehicleInfos!=""){
        JSONArray jsonArray=JSONArray.fromObject(vehicleInfos);
        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            AirPlanInfo airPlanInfo=new AirPlanInfo();
            //出发机场代码
           String startAirport = jsonObject.getString("startAirport");
           airPlanInfo.setStartAirport(startAirport);
            //到机场代码
           String arriveAirport = jsonObject.getString("arriveAirport");
           airPlanInfo.setArriveAirport(arriveAirport);
            //保存单价
            double double1 = jsonObject.getDouble("costPrice");
            BigDecimal costPrice=new BigDecimal(double1);
            airPlanInfo.setCostPrice(costPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
            //保存出发城市和到达城市
            City formcity = _cityRepository.findByAirportCode(startAirport);
            City tocity = _cityRepository.findByAirportCode(arriveAirport);
            
           
            
            airPlanInfo.setFromCity(formcity.getCityName());
            airPlanInfo.setToCity(tocity.getCityName());
            
            String airlineCode = jsonObject.getString("airline");
           /* Airline airline = _airlineRepository.findByAirlineCode(airlineCode);*/
           
            
            //保存航空公司
            airPlanInfo.setAirline(airlineCode);
            
            
            //保存出发时间和到达时间
            airPlanInfo.setDepartureTime(jsonObject.getString("departureTime")); 
            airPlanInfo.setArrivalTime(jsonObject.getString("arrivalTime"));
            
              model.addAttribute("fromCity",formcity.getCityName()+" "+startAirport+" "+jsonObject.getString("departureTime"));
            
            model.addAttribute("toCity", tocity.getCityName()+" "+arriveAirport+" "+jsonObject.getString("arrivalTime"));
            
            
            //保存舱位
            airPlanInfo.setRank(jsonObject.getString("rank"));
            //保存总飞行时间
            airPlanInfo.setTotalTime(jsonObject.getString("elapsedTime"));
            
            //航空公司代码
            airPlanInfo.setAirlineCode(airlineCode);
            //获得航班号
            airPlanInfo.setVehicleNumber(jsonObject.getString("vehicleNumber"));
            //获得机型
            airPlanInfo.setAirEquipType(jsonObject.getString("airEquipType"));
            //保存出发城市和到达城市的航站楼
            try
            {
                airPlanInfo.setDepartureTerminalId(jsonObject.getString("departureTerminalId"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                airPlanInfo.setArrivalTerminalId(jsonObject.getString("arrivalTerminalId"));
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
            
        //经停次数
            airPlanInfo.setLengthSeg(jsonObject.getInt("lengthSeg"));
            
            airPlanInfo.setSummary(jsonObject.getString("summary"));
            list.add(airPlanInfo);
        }
       model.addAttribute("vehicleInfos", list);
        }
        else{
        model.addAttribute("vehicleInfos", "");
        }
        return "/home/flightsearchdetail";
    }
    
    /**
     * 添加航班信息
     * @throws Exception 
     * 
     */
    
    
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(HttpServletRequest request,Model model) throws Exception {
        String result="";
        SimpleDateFormat formate=new SimpleDateFormat("yy-MM-dd");
        SimpleDateFormat formate1=new SimpleDateFormat("HH:mm");
        String jsonresult="";
       
        //获得日期规划id
       String datePlanId = request.getParameter("datePlanId");
       DatePlan datePlan = _datePlanRepository.findOne(Integer.parseInt(datePlanId));
       
       
      int orderId =  datePlan.getOrder().getId();
       String filghtPlanId = request.getParameter("filghtPlanId");
       
       
       if(filghtPlanId!=""){
          int filghtplanid = Integer.parseInt(filghtPlanId);
          
          
          
          List<FilghtPlan> filghtPlan = datePlan.getFilghtPlan();
          for(int i=0;i<filghtPlan.size();i++){
           FilghtPlan plan = filghtPlan.get(i);
           if(plan.getId()==filghtplanid){
               FilghtPlan flight = _FilghtPlanRepository.findOne(filghtplanid);
               //航班规划表
               String flyplanStr = request.getParameter("flyplanStr");
               JSONObject object=new JSONObject(flyplanStr);
               
               flight.setPersonalCount(Integer.parseInt(object.getString("peopleNum")));
               flight.setPersonalType(Integer.parseInt(object.getString("peopleType")));
               
               String string = object.getString("startDate");
             
               //Date startdate = formate.parse(string);
              // flight.setDepartureDate(startdate);
               flight.setDepartureDate(string);
               
               String startTime = object.getString("startTime");
               //Date starttime = formate1.parse(startTime);
               //flight.setDepartureTime(starttime);
               flight.setDepartureTime(startTime);
               
               
              //保存日期规划
               
               flight.setDatePlan(datePlan);
               model.addAttribute("datePlanId", datePlanId);
               
               
               
               //经停次数
               String lengthSeg=object.getString("lengthSeg");
               flight.setTransferFrequency(Integer.parseInt(lengthSeg));
               //flight.setLengthSeg(Integer.parseInt(lengthSeg));
               
               //单位成本
               Double double1 = object.getDouble("costPrice");
               BigDecimal costPrice=new BigDecimal(double1);
               flight.setPerCost(costPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
               
               //小计成本
              String peopleNum = object.getString("peopleNum");
              BigDecimal peoplenum=new BigDecimal(peopleNum);
              BigDecimal subtotalCost = costPrice.multiply(peoplenum);
              flight.setSubtotalCost(subtotalCost.setScale(2, BigDecimal.ROUND_HALF_UP));
               
              
              //获得供应商价格id
              
              String priceCoefficientID = object.getString("priceCoefficientID");
              SupplierPriceRule supplierPriceRule = _supplierPriceRuleRepository.findOne(Integer.parseInt(priceCoefficientID));
              
               //获得供应商价格系数
              BigDecimal pricecoefficient = supplierPriceRule.getPriceCoefficient();
            
              
              //计算单价
              flight.setPrice((costPrice.multiply(pricecoefficient)).setScale(2, BigDecimal.ROUND_HALF_UP));
              
              //计算小计金额
              flight.setSubtotalAmount((subtotalCost.multiply(pricecoefficient)).setScale(2, BigDecimal.ROUND_HALF_UP));
              
              
              //获得供应商
              flight.setSupplierId(supplierPriceRule.getSupplier().getId());
              
              //航空公司代码
            
            
             
              String airlineCode = object.getString("airline");
            /*  Airline airline = _airlineRepository.findByAirlineEnglishName(airlineEngLishName);*/
              flight.setAirlineCodes(airlineCode);
              
              
              
               String startAirport = object.getString("startAirport");
             /*  City departcity = _cityRepository.findByCityName(departCity);*/
               flight.setDepartureCityId(startAirport);
               //flight.setDepartureCity(departcity.getId());
               
               
               
               String arriveAirport = object.getString("arriveAirport");
              /* City arrivecity = _cityRepository.findByCityName(arriveCity);*/
               flight.setArrivalCityId(arriveAirport);
               //flight.setArrivalCity(arrivecity.getId());
               
               
               String endDate = object.getString("endDate");
              // Date enddate = formate.parse(endDate);
              // flight.setArrivalDate(enddate);
               flight.setArrivalDate(endDate);
               String endTime = object.getString("endTime");
              // Date endtime = formate1.parse(endTime);
               //flight.setArrivalTime(endtime);
               flight.setArrivalTime(endTime);
               
               String flyTime = object.getString("flyTime");
               
              /* BigDecimal time=new BigDecimal(flyTime);
               BigDecimal second=new BigDecimal(60);
               double doubleValue = time.divide(second,2, BigDecimal.ROUND_HALF_UP).doubleValue();
               
               String totalTime = String.valueOf(doubleValue);*/
               
               flight.setTotalFlightTime(flyTime);
               
               
               flight.setFlightType(object.getString("flightType"));
               flight.setCreateTime(new Date());
               _FilghtPlanRepository.save(flight);
               
               
             //航班详情
              String flighdetailArray = request.getParameter("flighdetailArray");
              JSONArray jsonArraySummary=JSONArray.fromObject(flighdetailArray);
              for(int j=0;j<jsonArraySummary.length();j++){
                  FlightDetails  flightDetails=new FlightDetails();
                  
                  
                  JSONObject jsonObject2 = jsonArraySummary.getJSONObject(j);
                  
                  String stopDepartureDate = jsonObject2.getString("stopDepartureDate");
                //  Date DepartureDate = formate.parse(stopDepartureDate);
                 // flightDetails.setDepartureDate(DepartureDate);
                  flightDetails.setDepartureDate(stopDepartureDate);
                  String stopDepartureTime = jsonObject2.getString("stopDepartureTime");
                 // Date DepartureTime = formate1.parse(stopDepartureTime);
                 // flightDetails.setDepartureTime(DepartureTime);
                  flightDetails.setDepartureTime(stopDepartureTime);
                  
                  String stopArrivalDate = jsonObject2.getString("stopArrivalDate");
                 // Date ArrivalDate = formate.parse(stopArrivalDate);
                 // flightDetails.setArrivalDate(ArrivalDate);
                  flightDetails.setArrivalDate(stopArrivalDate);
                  
                  String stopArrivalDateTime = jsonObject2.getString("stopArrivalDateTime");
                 // Date ArrivalDateTime = formate1.parse(stopArrivalDateTime);
                 // flightDetails.setArrivalTime(ArrivalDateTime);
                  flightDetails.setArrivalTime(stopArrivalDateTime);
                  
                  String DepartureCitycityname = jsonObject2.getString("DepartureCitycityname");
                 // City DepartureCity= _cityRepository.findByCityName(DepartureCitycityname);
                  flightDetails.setDepartureCityId(DepartureCitycityname);
                  //flightDetails.setDepartureCityId(DepartureCity.getId());
                  
                  
                  
                  String ArrivalCitycityname = jsonObject2.getString("ArrivalCity");
                  //City ArrivalCity= _cityRepository.findByCityName(ArrivalCitycityname);
                 // flightDetails.setArrivalCityId(ArrivalCity.getId());
                  flightDetails.setArrivalCityId(ArrivalCitycityname);
                  
                  //这里到底是机场名字还是机场代码
                  String DepartureAirport = jsonObject2.getString("DepartureAirport");
                  flightDetails.setDepartureAirportCode(DepartureAirport);
                  
                  //出发机场航站楼
                  
                  try
                  {
                      flightDetails.setDepartureTrminalId(jsonObject2.getString("departureTerminalId"));
                  }
                  catch (Exception e)
                  {
                      // TODO Auto-generated catch block
                      e.printStackTrace();
                  }
                  //到达机场航站楼
                  try
                {
                    flightDetails.setArrivalTerminalId(jsonObject2.getString("arrivalTerminalId"));
                }
                catch (Exception e1)
                {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                  
                  //到达机场
                  
                  String stopArrivalAirport = jsonObject2.getString("stopArrivalAirport");
                  
                  flightDetails.setArrivalAirportCode(stopArrivalAirport);
                  
                  
                  //航班号
                  String stopFlightNumber = jsonObject2.getString("stopFlightNumber");
                  
                  flightDetails.setFlightNumber(stopFlightNumber);
                  
                  
                  //级别
                  String stopCabin = jsonObject2.getString("stopCabin");
                  flightDetails.setCabin(stopCabin);
                  
                  //飞行时间
                  String stopElapsedTime = jsonObject2.getString("stopElapsedTime");
                  /*BigDecimal Elapsedtime=new BigDecimal(stopElapsedTime);
                  BigDecimal Elapsedsecond=new BigDecimal(60);
                  String Elapsedtotaltime = Elapsedtime.divide(Elapsedsecond,1,BigDecimal.ROUND_HALF_UP).toString();*/
                  
                  
                  flightDetails.setTravelTime(stopElapsedTime);
                  
                  //停留时间
                  try
                {
                    String stopMin = jsonObject2.getString("stopMin");
                      flightDetails.setLayoverTime(stopMin);
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                  
                  //机型
                  String stopEquipType = jsonObject2.getString("stopEquipType");
                  flightDetails.setFlightType(stopEquipType);
                  
                  flightDetails.setCreateTime(new Date());
                  flightDetails.setFilghtPlan(flight);
                  
                  _FilghtDetailsRepository.save(flightDetails);
              }
              
              result="预订成功!";
              
              // jsonresult="{\"result\":\""+result+"\",\"Id\":\""+datePlanId+"\",\"filghtPlanId\":\""+filghtPlanId+"\"}";
              /* jsonresult="{\"result\":\""+result+"\",\"orderId\":\""+orderId+"\",\"filghtPlanId\":\""+filghtPlanId+"\"}";*/
               jsonresult="{\"result\":\""+result+"\",\"datePlanId\":\""+datePlanId+"\",\"filghtPlanId\":\""+filghtPlanId+"\",\"orderId\":\""+orderId+"\"}";
               
               
           }
         }
      }else{
          
          //航班规划表
          String flyplanStr = request.getParameter("flyplanStr");
          JSONObject object=new JSONObject(flyplanStr);
          
          FilghtPlan flight=new FilghtPlan();
          flight.setPersonalCount(Integer.parseInt(object.getString("peopleNum")));
          flight.setPersonalType(Integer.parseInt(object.getString("peopleType")));
          
          String string = object.getString("startDate");
        
          //Date startdate = formate.parse(string);
         // flight.setDepartureDate(startdate);
          flight.setDepartureDate(string);
          
          String startTime = object.getString("startTime");
          //Date starttime = formate1.parse(startTime);
          //flight.setDepartureTime(starttime);
          flight.setDepartureTime(startTime);
          
          
         //保存日期规划
          
          flight.setDatePlan(datePlan);
          model.addAttribute("datePlanId", datePlanId);
          
          
          
          //经停次数
          String lengthSeg=object.getString("lengthSeg");
          flight.setTransferFrequency(Integer.parseInt(lengthSeg));
          //flight.setLengthSeg(Integer.parseInt(lengthSeg));
          
          //单位成本
          Double double1 = object.getDouble("costPrice");
          BigDecimal costPrice=new BigDecimal(double1);
          flight.setPerCost(costPrice.setScale(2, BigDecimal.ROUND_HALF_UP));
          
          //小计成本
         String peopleNum = object.getString("peopleNum");
         BigDecimal peoplenum=new BigDecimal(peopleNum);
         BigDecimal subtotalCost = costPrice.multiply(peoplenum);
         flight.setSubtotalCost(subtotalCost.setScale(2, BigDecimal.ROUND_HALF_UP));
          
         
         //获得供应商价格id
         
         String priceCoefficientID = object.getString("priceCoefficientID");
         SupplierPriceRule supplierPriceRule = _supplierPriceRuleRepository.findOne(Integer.parseInt(priceCoefficientID));
         
          //获得供应商价格系数
         BigDecimal pricecoefficient = supplierPriceRule.getPriceCoefficient();
       
         
         //计算单价
         flight.setPrice((costPrice.multiply(pricecoefficient)).setScale(2, BigDecimal.ROUND_HALF_UP));
         
         //计算小计金额
         flight.setSubtotalAmount((subtotalCost.multiply(pricecoefficient)).setScale(2, BigDecimal.ROUND_HALF_UP));
         
         
         //获得供应商
         flight.setSupplierId(supplierPriceRule.getSupplier().getId());
         
         //航空公司代码
         String airlineCode = object.getString("airline");
       //  Airline airline = _airlineRepository.findByAirlineEnglishName(airlineEngLishName);
         flight.setAirlineCodes(airlineCode);
         
          
          String departCity = object.getString("startAirport");
          flight.setDepartureCityId(departCity);
          /* City departcity = _cityRepository.findByCityName(departCity);
          flight.setDepartureCityId(departcity);*/
          //flight.setDepartureCity(departcity.getId());
          
          
          
          String arriveCity = object.getString("arriveAirport");
         /* City arrivecity = _cityRepository.findByCityName(arriveCity);*/
          flight.setArrivalCityId(arriveCity);
          //flight.setArrivalCity(arrivecity.getId());
          
          
          String endDate = object.getString("endDate");
         // Date enddate = formate.parse(endDate);
         // flight.setArrivalDate(enddate);
          flight.setArrivalDate(endDate);
          String endTime = object.getString("endTime");
         // Date endtime = formate1.parse(endTime);
          //flight.setArrivalTime(endtime);
          flight.setArrivalTime(endTime);
          
          String flyTime = object.getString("flyTime");
          
         /* BigDecimal time=new BigDecimal(flyTime);
          BigDecimal second=new BigDecimal(60);
          double doubleValue = time.divide(second,2, BigDecimal.ROUND_HALF_UP).doubleValue();
          
          String totalTime = String.valueOf(doubleValue);*/
          
          flight.setTotalFlightTime(flyTime);
          
          
          flight.setFlightType(object.getString("flightType"));
          flight.setCreateTime(new Date());
          _FilghtPlanRepository.save(flight);
          
          
        //航班详情
         String flighdetailArray = request.getParameter("flighdetailArray");
         JSONArray jsonArraySummary=JSONArray.fromObject(flighdetailArray);
         for(int j=0;j<jsonArraySummary.length();j++){
             FlightDetails  flightDetails=new FlightDetails();
             
             
             JSONObject jsonObject2 = jsonArraySummary.getJSONObject(j);
             
             String stopDepartureDate = jsonObject2.getString("stopDepartureDate");
           //  Date DepartureDate = formate.parse(stopDepartureDate);
            // flightDetails.setDepartureDate(DepartureDate);
             flightDetails.setDepartureDate(stopDepartureDate);
             String stopDepartureTime = jsonObject2.getString("stopDepartureTime");
            // Date DepartureTime = formate1.parse(stopDepartureTime);
            // flightDetails.setDepartureTime(DepartureTime);
             flightDetails.setDepartureTime(stopDepartureTime);
             
             String stopArrivalDate = jsonObject2.getString("stopArrivalDate");
            // Date ArrivalDate = formate.parse(stopArrivalDate);
            // flightDetails.setArrivalDate(ArrivalDate);
             flightDetails.setArrivalDate(stopArrivalDate);
             
             String stopArrivalDateTime = jsonObject2.getString("stopArrivalDateTime");
            // Date ArrivalDateTime = formate1.parse(stopArrivalDateTime);
            // flightDetails.setArrivalTime(ArrivalDateTime);
             flightDetails.setArrivalTime(stopArrivalDateTime);
             
             //待定
            String DepartureCitycityname = jsonObject2.getString("DepartureAirport");
            flightDetails.setDepartureCityId(DepartureCitycityname);
            /*  City DepartureCity= _cityRepository.findByCityName(DepartureCitycityname);
             flightDetails.setDepartureCityId(DepartureCity);*/
             //flightDetails.setDepartureCityId(DepartureCity.getId());
             
             
             
            String ArrivalCitycityname = jsonObject2.getString("stopArrivalAirport");
            flightDetails.setArrivalCityId(ArrivalCitycityname);
            /*  City ArrivalCity= _cityRepository.findByCityName(ArrivalCitycityname);
            // flightDetails.setArrivalCityId(ArrivalCity.getId());
             flightDetails.setArrivalCityId(ArrivalCity);*/
             
             //这里到底是机场名字还是机场代码
             String DepartureAirport = jsonObject2.getString("DepartureAirport");
             flightDetails.setDepartureAirportCode(DepartureAirport);
             
             //出发机场航站楼
             
             try
             {
                 flightDetails.setDepartureTrminalId(jsonObject2.getString("departureTerminalId"));
             }
             catch (Exception e)
             {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
             
             //到达机场
             
             String stopArrivalAirport = jsonObject2.getString("stopArrivalAirport");
             
             flightDetails.setArrivalAirportCode(stopArrivalAirport);
             
             
             //航班号
             String stopFlightNumber = jsonObject2.getString("stopFlightNumber");
             
             flightDetails.setFlightNumber(stopFlightNumber);
             
             
             //级别
             String stopCabin = jsonObject2.getString("stopCabin");
             flightDetails.setCabin(stopCabin);
             
             //飞行时间
             String stopElapsedTime = jsonObject2.getString("stopElapsedTime");
             /*BigDecimal Elapsedtime=new BigDecimal(stopElapsedTime);
             BigDecimal Elapsedsecond=new BigDecimal(60);
             String Elapsedtotaltime = Elapsedtime.divide(Elapsedsecond,1,BigDecimal.ROUND_HALF_UP).toString();*/
             
             
             flightDetails.setTravelTime(stopElapsedTime);
             
             //停留时间
             try
            {
                String stopMin = jsonObject2.getString("stopMin");
                 flightDetails.setLayoverTime(stopMin);
            }
            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             
             //机型
             String stopEquipType = jsonObject2.getString("stopEquipType");
             flightDetails.setFlightType(stopEquipType);
             
             flightDetails.setCreateTime(new Date());
             flightDetails.setFilghtPlan(flight);
             
             _FilghtDetailsRepository.save(flightDetails);
         }
         
         result="预订成功!";
         
           jsonresult="{\"result\":\""+result+"\",\"datePlanId\":\""+datePlanId+"\",\"filghtPlanId\":\""+filghtPlanId+"\",\"orderId\":\""+orderId+"\"}";
      }
        
      
        
        
        return jsonresult;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
