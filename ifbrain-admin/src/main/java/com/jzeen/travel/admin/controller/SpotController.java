package com.jzeen.travel.admin.controller;

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
import com.jzeen.travel.data.entity.AccompanyMemberAge;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.SpotThemeRelate;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.ThemeActive;
import com.jzeen.travel.data.entity.ThemeRelate;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.SpotRepository;
import com.jzeen.travel.data.repository.SpotThemeRelateRepository;
import com.jzeen.travel.data.repository.SpotTicketTypeRepository;
import com.jzeen.travel.data.repository.SupplierRepository;
import com.jzeen.travel.data.repository.ThemeActiveRepository;

@Controller
@RequestMapping("/spots")
public class SpotController
{
    @Autowired
    private SpotRepository _spotRepository;

    @Autowired
    private ImageRepository _imageRepository;

    @Autowired
    private FileUploadSetting _fileUploadSetting;

    @Autowired
    private CityRepository _cityactive;

    @Autowired
    private SupplierRepository _supplierRepository;

    @Autowired
    private ThemeActiveRepository _themeRepository;

    @Autowired
    private SpotThemeRelateRepository _themeRelateRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/spots/list";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Spot> search()
    {
        List<Spot> data = _spotRepository.findAll();
        return data;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute Spot spots, Model model)
    {
        List<City> cityid = _cityactive.findAll();
        model.addAttribute("cityid", cityid);
        int status=1;
        List<Supplier> supplierid = _supplierRepository.findBySupplierStatus(status);
        model.addAttribute("supplierid", supplierid);
        List<ThemeActive> theme = _themeRepository.findAll();
        model.addAttribute("theme", theme);
        model.addAttribute("spots", spots);
        
        return "/spots/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid Spot spots, BindingResult bindingResult, Model model, HttpServletRequest request,
            @RequestParam("file") MultipartFile file)
    {
        List <SpotThemeRelate> spo =new ArrayList<SpotThemeRelate>();
        if (bindingResult.hasErrors())
        {
            int status=1;
            List<Supplier> supplierid = _supplierRepository.findBySupplierStatus(status);
            model.addAttribute("supplierid", supplierid);

            List<City> cityid = _cityactive.findAll();
            model.addAttribute("cityid", cityid);

            List<ThemeActive> theme = _themeRepository.findAll();
            model.addAttribute("theme", theme);
            return "redirect:/spots";
        }
        String filePath = _fileUploadSetting.getRootPath();
//        String filePath = "."+File.separator;
        //String filePath = FileUtil.getUploadFolder(folderName);
        if (!file.isEmpty())
        {
            // 获取城市
            String cityid = request.getParameter("cityid");
            int cityId = Integer.parseInt(cityid);
            City city = _cityactive.findOne(cityId);

            // 获取供应商
            String supplierid = request.getParameter("supplierid");
            int supplierId = Integer.parseInt(supplierid);
            Supplier supplier = _supplierRepository.findOne(supplierId);
            
            //主题
            
             String theme = request.getParameter("tripAim");
           String[] themeId = theme.split(",");
            try
            {
                byte[] bytes = file.getBytes();
                File directory = new File(filePath);
                if (!directory.exists())
                {
                    directory.mkdirs();
                }
                String fileName =file.getOriginalFilename();
//                filePath += File.separator + fileName;
//                String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
        
//                String lujing="."+filePath+fileName;
                String imglujing="."+File.separator+fileName;
                
                Image image = new Image();
                image.setCreateTime(new Date());
                image.setFileName(fileName);
                image.setFilePath(imglujing);
                _imageRepository.save(image);

                spots.setImage(image);
                spots.setCreateTime(new Date());
                spots.setCityid(city);
                spots.setSupplierid(supplier);
                _spotRepository.save(spots);
                
               for (int i = 0; i < themeId.length; i++)
                {
                   SpotThemeRelate spotThemeRelate = new SpotThemeRelate();
                    spotThemeRelate.setTheme(_themeRepository.findByTheme(themeId[i]));
                    System.out.println(spotThemeRelate.getTheme());
                    spotThemeRelate.setCreateTime(new Date());
                    spotThemeRelate.setSpot(spots);
                    _themeRelateRepository.save(spotThemeRelate);
                    }
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+fileName)));
                stream.write(bytes);
                stream.close();
                return "redirect:/spots";
            }
            catch (Exception e)
            {
                model.addAttribute("errorMessage", "上传文件失败");
                model.addAttribute("spots",  _spotRepository.findOne(spots.getId()));
                return "/spots/create";
            }
        }
        else
        {
            model.addAttribute("errorMessage", "请选择上传文件");
            return "/spots/create";
        }
    }

    // 景点规划时修改景点信息
    @ResponseBody
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public void update(@PathVariable Integer id, HttpServletRequest request) {
        String spotsdescription = request.getParameter("spotsdescription");
        String spotssummary = request.getParameter("spotssummary");
        String specialnotes = request.getParameter("specialnotes");
        String clothingtips = request.getParameter("clothingtips");
        String spendingtips = request.getParameter("spendingtips");
        String weathertips = request.getParameter("weathertips");

        Spot spot = _spotRepository.findOne(id);
        spot.setSpotsdescription(spotsdescription);
        spot.setSpotssummary(spotssummary);
        spot.setSpecialnotes(specialnotes);
        spot.setClothingtips(clothingtips);
        spot.setSpendingtips(spendingtips);
        spot.setWeathertips(weathertips);
        _spotRepository.save(spot);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        Spot spots = _spotRepository.findOne(id);
        model.addAttribute("spots", spots);

        List<City> cityid = _cityactive.findAll();
        model.addAttribute("cityid", cityid);

        int status=1;
        List<Supplier> supplierid = _supplierRepository.findBySupplierStatus(status);
        model.addAttribute("supplierid", supplierid);
        
        List<ThemeActive> theme = _themeRepository.findAll();
        model.addAttribute("theme", theme);
        return "/spots/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("file") MultipartFile file,@Valid Spot spots, BindingResult bindingResult, Model model, HttpServletRequest request)
    {
        if (bindingResult.hasErrors())
        { 
            List<City> cityid = _cityactive.findAll();
            model.addAttribute("cityid", cityid);
            int status=1;
            List<Supplier> supplierid = _supplierRepository.findBySupplierStatus(status);
            model.addAttribute("supplierid", supplierid);
            List<ThemeActive> theme = _themeRepository.findAll();
            model.addAttribute("theme", theme);
            return "/spots/update";
        }
        Spot oldspots =_spotRepository.findOne(spots.getId());
        String filePath = _fileUploadSetting.getRootPath();
        spots.setCreateTime(new Date());
        if (!file.isEmpty())
        {
         // 获取城市
            String cityid = request.getParameter("cityid");
            int cityId = Integer.parseInt(cityid);
            City city = _cityactive.findOne(cityId);

            // 获取供应商
            String supplierid = request.getParameter("supplierid");
            int supplierId = Integer.parseInt(supplierid);
            Supplier supplier = _supplierRepository.findOne(supplierId);
            //主题
            String id=request.getParameter("id");
            int ids = Integer.parseInt(id);
            Spot spo =_spotRepository.findOne(ids);
            if(spo!=null){
                _themeRelateRepository.deleteBySpotId(ids);
            }
//            String theme = request.getParameter("themeRelates");
//            int themeId = Integer.parseInt(theme);
//            ThemeActive themes = _themeRepository.findOne(themeId);
            String theme = request.getParameter("tripAim");
            String[] themeId = theme.split(",");
            
            try
            {
                byte[] bytes = file.getBytes();
                File directory = new File(filePath);
                if (!directory.exists())
                {
                    directory.mkdirs();
                }
                String fileName =file.getOriginalFilename();
//                filePath += File.separator + fileName;
//                String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
              String lujing="."+File.separator+fileName;
                Image image = _imageRepository.findOne(oldspots.getImage().getId());
                image.setCreateTime(new Date());
                image.setFileName(fileName);
                image.setFilePath(lujing);
                _imageRepository.save(image);
                spots.setImage(image);
                oldspots.setCreateTime(new Date());
                spots.setCityid(city);
                spots.setSupplierid(supplier);
            /*    SpotThemeRelate spotThemeRelate = new SpotThemeRelate();
                spotThemeRelate.setTheme(themes);
                spotThemeRelate.setSpot(spots);
                spotThemeRelate.setCreateTime(new Date());*/
                
                for (int i = 0; i < themeId.length; i++)
                {
                   SpotThemeRelate spotThemeRelate = new SpotThemeRelate();
                    spotThemeRelate.setTheme(_themeRepository.findByTheme(themeId[i]));
                    System.out.println(spotThemeRelate.getTheme());
                    spotThemeRelate.setCreateTime(new Date());
                    spotThemeRelate.setSpot(spots);
                    _themeRelateRepository.save(spotThemeRelate);
                    }
                _spotRepository.save(spots);
//                _themeRelateRepository.save(spotThemeRelate);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+fileName)));
                stream.write(bytes);
                stream.close();
                return "redirect:/spots";
            }
            catch (Exception e)
            {
                model.addAttribute("errorMessage", "上传文件失败");
                model.addAttribute("spots",  _spotRepository.findOne(spots.getId()));
                return "/spots/update";
            }
        }
        else
        {
            model.addAttribute("errorMessage", "请选择上传文件");
            
            return "/spots/update";
    }
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String  delete(@PathVariable int id)
    {
        _spotRepository.delete(id);
        String result="删除成功";
        return result;
    }

    
    
    
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailInit(@PathVariable int id, Model model)
    {
        List<ThemeActive> themeActive=new ArrayList<ThemeActive>();
        Spot spots = _spotRepository.findOne(id);
        model.addAttribute("spots", spots);
        List<City> cityid = _cityactive.findAll();
        model.addAttribute("cityid", cityid);
        List<Supplier> supplierid = _supplierRepository.findAll();
        model.addAttribute("supplierid", supplierid);
        
      
        List<SpotThemeRelate> themeRelates = spots.getThemeRelates();
        for(int i=0;i<themeRelates.size();i++){
            themeActive.add(themeRelates.get(i).getTheme());
        }
        model.addAttribute("theme", themeActive);
       // List <ThemeRelate> themeRelates =_themeRelateRepository.
       Image image = spots.getImage();
        model.addAttribute("image", image);
        return "/spots/detail";
    }

    @RequestMapping(value = "/tickettype/{id}", method = RequestMethod.GET)
    public String spotstickettypeInit(@PathVariable int id, Model model)
    {
        Spot spots = _spotRepository.findOne(id);
        model.addAttribute("spots", spots);
        return "/spotstickettype/list";
    }

    

}
