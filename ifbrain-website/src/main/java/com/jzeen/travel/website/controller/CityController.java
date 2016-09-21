package com.jzeen.travel.website.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.core.util.FileUtil;
import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.DemoUser;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.QCity;
import com.jzeen.travel.data.entity.QDemoUser;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.ThemeActive;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CountryRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/cityactive")
public class CityController
{
    @Autowired
    private CityRepository _cityRepository;
    
    @Autowired
    private CountryRepository _countryRepository;
    
    @Autowired
    private ImageRepository _imageRepository;
        
    /**
     * 根据机场代码,获得机场所在城市
     */
    
    @ResponseBody
    @RequestMapping(value = "/findByAirPortCode", method = RequestMethod.POST)
    public String findByAirPortCode( Model model,HttpServletRequest request)
    {
        //获得出发机场
        String DepartureAirport = request.getParameter("DepartureAirport");
        City DepartureCity = _cityRepository.findByAirportCode(DepartureAirport);
        
        String DepartureName = DepartureCity.getCityName();
        
        //获得到达机场
        String stopArrivalAirport = request.getParameter("stopArrivalAirport");
         City stopArrivalCity = _cityRepository.findByAirportCode(stopArrivalAirport);
         String ArrivalName = stopArrivalCity.getCityName();
         
         
         String result="{\"DepartureName\":\""+DepartureName+"\",\"ArrivalName\":\""+ArrivalName+"\"}";
         
         
        return result;
    }
    
    
    
    /**
     * 根据返程查询,根据出发到达城市，查询
     */
    
    @ResponseBody
    @RequestMapping(value = "/findByCityName", method = RequestMethod.POST)
    public String findByCityName( Model model,HttpServletRequest request)
    {
        
        //获得出发城市
        String fromCityName = request.getParameter("fromCity");
        City fromCity = _cityRepository.findByCityName(fromCityName);
       
        //获得到达城市
        String arriveCityName = request.getParameter("toCity");
         City arriveCity = _cityRepository.findByCityName(arriveCityName);
         String result="{\"originLocationCode\":\""+fromCity.getAirportCode()+"\",\"destinationLocationCode\":\""+arriveCity.getAirportCode()+"\"}";
         
         
        return result;
    }
    
    
}
