package com.jzeen.travel.admin.controller;

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

import com.jzeen.travel.admin.setting.FileUploadSetting;
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
    
    @Autowired
    private FileUploadSetting _fileUploadSetting;

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/cityactive/list";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<City> search()
    {
        List<City> data = _cityRepository.findAll();
        return data;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute City cityactive, Model model)
    {
        List<Country> country = _countryRepository.findAll();
        model.addAttribute("country", country);
        model.addAttribute("cityactive", cityactive);
        return "/cityactive/create";

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid City cityactive, BindingResult bindingResult, Model model, HttpServletRequest request,
            @RequestParam("file") MultipartFile file)
    {
        if (bindingResult.hasErrors())
        {
            return "/cityactive/create";
        }
        String filePath = _fileUploadSetting.getRootPath();
        cityactive.setCreateTime(new Date());
        if (!file.isEmpty())
        {
            Integer id = Integer.parseInt(request.getParameter("country"));
            Country country = _countryRepository.findOne(id);
            try
            {
                byte[] bytes = file.getBytes();
                File directory = new File(filePath);
                if (!directory.exists())
                {
                    directory.mkdirs();
                }
                String fileName =file.getOriginalFilename();

                filePath += File.separator + fileName;
                String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
                Image image = new Image();
                image.setCreateTime(new Date());
                image.setFileName(fileName);
                image.setFilePath(lujing);
                _imageRepository.save(image);
                cityactive.setImage(image);
                cityactive.setCountry(country);
                cityactive.setCreateTime(new Date());
                _cityRepository.save(cityactive);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(bytes);
                stream.close();
                return "redirect:/cityactive";
            }
            catch (Exception e)
            {
                model.addAttribute("errorMessage", "上传文件失败");
                model.addAttribute("cityactive", _cityRepository.findOne(cityactive.getId()));
                return "/cityactive/create";
            }
        }

        else
        {
            model.addAttribute("errorMessage", "请选择上传文件");
            return "/cityactive/create";
        }

    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _cityRepository.delete(id);
        ;
    }

    
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        City cityactive = _cityRepository.findOne(id);
        model.addAttribute("cityactive",cityactive);
        List<Country> country = _countryRepository.findAll();
        model.addAttribute("country", country);
        Image image = cityactive.getImage();
        model.addAttribute("image", image);
        return "/cityactive/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("file") MultipartFile file,@Valid City cityactive, BindingResult bindingResult, Model model)
    {
        
        if (bindingResult.hasErrors())
        { 
            List<Country> country = _countryRepository.findAll();
            model.addAttribute("country", country);
            return "/cityactive/update";
        }
        City oldcityactive =_cityRepository.findOne(cityactive.getId());
        Country country =oldcityactive.getCountry();
        String filePath = _fileUploadSetting.getRootPath();
        cityactive.setCreateTime(new Date());
        if (!file.isEmpty())
        {
            try
            {
                byte[] bytes = file.getBytes();
                File directory = new File(filePath);
                if (!directory.exists())
                {
                    directory.mkdirs();
                }
                String fileName =file.getOriginalFilename();
                filePath += File.separator + fileName;
                String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
                Image image = new Image();
                image.setCreateTime(new Date());
                image.setFileName(fileName);
                image.setFilePath(lujing);
                _imageRepository.save(image);
                cityactive.setImage(image);
                oldcityactive.setCountry(country);
                oldcityactive.setCreateTime(new Date());
                _cityRepository.save(cityactive);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(bytes);
                stream.close();
                return "redirect:/cityactive";
            }
            catch (Exception e)
            {
                model.addAttribute("errorMessage", "上传文件失败");
                model.addAttribute("cityactive", _cityRepository.findOne(cityactive.getId()));
                return "/cityactive/update";
            }
    } else
    {
        model.addAttribute("errorMessage", "请选择上传文件");
        return "/cityactive/update";
    }

}


    //详细信息
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailInit(@PathVariable int id, Model model)
    {
        City cityactive = _cityRepository.findOne(id);
        model.addAttribute("cityactive", cityactive);
        List<Country> country = _countryRepository.findAll();
        model.addAttribute("country", country);
        Image image = cityactive.getImage();
        model.addAttribute("image", image);
        return "/cityactive/detail";
    }
    
   // 城市名称重复提交判断
    @ResponseBody
    @RequestMapping(value = "/valueistrue", method = RequestMethod.POST)
    public boolean valueistrue(String cityName,int country)
    {
        long citys = _cityRepository.findByCountry(cityName,country);
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
  /*  //机场代码管理
    @RequestMapping(value = "/airport/{id}", method = RequestMethod.GET)
    public String airport(@PathVariable int id, Model model)
    {
        City cityactive = _cityRepository.findOne(id);
        model.addAttribute("cityactive", cityactive);
        return "/airport/index";
    }*/
    
    
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
    @RequestMapping(value = "/listtest", method = RequestMethod.GET)
    public String listtest( Model model)
    {
        
        return "/cityactive/listtest";

    }
    
}
