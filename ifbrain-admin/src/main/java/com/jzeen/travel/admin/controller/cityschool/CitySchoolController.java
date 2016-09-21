package com.jzeen.travel.admin.controller.cityschool;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
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

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.core.util.FileUtil;
import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.CitySchool;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.CourseCode;
import com.jzeen.travel.data.entity.DemoUser;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.QCity;
import com.jzeen.travel.data.entity.QDemoUser;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.ThemeActive;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CitySchoolRepository;
import com.jzeen.travel.data.repository.CountryRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/cityschool")
public class CitySchoolController
{
    @Autowired
    private CitySchoolRepository _CitySchoolRepository;
    
    @Autowired
    private CountryRepository _countryRepository;
    
    @Autowired
    private ImageRepository _imageRepository;
    
    @Autowired
    private FileUploadSetting _fileUploadSetting;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String index()
    {
        return "/cityschool/list";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<CitySchool> search()
    {
        List<CitySchool> data = _CitySchoolRepository.findAll();
        List<CitySchool> list =new ArrayList<CitySchool>(); 
        for(int i=0;i<data.size();i++){
        	CitySchool c=new CitySchool();
        	c.setCityName(data.get(i).getCityName());
        	c.setCreateTime(data.get(i).getCreateTime());
        	c.setId(data.get(i).getId());
        	list.add(c);
        }
        return data;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit( Model model)
    {
      
        return "/cityschool/create";

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create( Model model, HttpServletRequest request)
    {
    	CitySchool cityschool = new CitySchool();
    	System.out.println(request.getParameter("cityName"));
    	cityschool.setCityName(request.getParameter("cityName"));
    	cityschool.setCreateTime(new Date());
    
    	_CitySchoolRepository.save(cityschool);
    	
		return "redirect:/cityschool/list";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _CitySchoolRepository.delete(id);
        ;
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        CitySchool cityschool = _CitySchoolRepository.findOne(id);
        model.addAttribute("cityschool",cityschool);
       
        return "/cityschool/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid CitySchool cityschool, Model model,HttpServletRequest request)
    {
    	  CitySchool cityschoolupdate = _CitySchoolRepository.findOne(cityschool.getId());
    	  cityschoolupdate.setCityName(request.getParameter("cityName"));
    	  _CitySchoolRepository.save(cityschoolupdate);
    	return "redirect:/cityschool/list";
    }
    
    
    // 城市名称重复提交判断
    @ResponseBody
    @RequestMapping(value = "/valueistrue", method = RequestMethod.POST)
    public boolean valueistrue(String cityName)
    {
        long citys = _CitySchoolRepository.findByCity(cityName);
       if (citys == 0 )
        {
            System.out.println("true");
            return true;
        }
        else
        {
            System.out.println("false");
            return false;
        }
    }
}
