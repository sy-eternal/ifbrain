package com.jzeen.travel.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.HotelActivity;
import com.jzeen.travel.data.entity.HotelTags;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.HotelActivityRepository;
import com.jzeen.travel.data.repository.HotelTagsRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.SupplierRepository;

@Controller
@RequestMapping("/hotelActivity")
public class HotelActivityController
{
    @Autowired
    private HotelActivityRepository _hotelActivityRepository;
    
    @Autowired
    private CodeRepository _codeRepository;
    
    @Autowired
    private SupplierRepository _supplierRepository;
    
    @Autowired
    private CityRepository _cityRepository;
    
    @Autowired
    private FileUploadSetting _fileUploadSetting;
    
    @Autowired
    private ImageRepository _imageRepository;
    
    @Autowired
    private HotelTagsRepository _hotelTagsRepository;
    
    /**
     * 默认跳转页面
     */
    @RequestMapping(method=RequestMethod.GET)
    public String index(){
        return "/hotelactivity/index";
    }
    
    /**
     * 酒店活动列表显示
     */
    @ResponseBody
    @RequestMapping(value="/search",method=RequestMethod.GET)
    public List<HotelActivity> search(){
        
        List<HotelActivity> data=_hotelActivityRepository.findAll();
        return data;
    }
    
    /**
     * 跳转到酒店新增页面
     */
    @RequestMapping(value="/create" , method=RequestMethod.GET)
    public String createInit(@ModelAttribute HotelActivity hotelActivity,Model model)
    {
        //查询代码表供应商为租车的对象（供应商和5写成固定参数了，写死了）
        Code code=_codeRepository.findByTypeAndValue("供应商",1);
        
        //根据代码表ID，查询供应商信息
        List<Supplier> supplier=_supplierRepository.findByCodeId(code.getId());
        
        //查询所有美国城市
        List<City> city=_cityRepository.findByCountryId(2);
        model.addAttribute("city", city);
        model.addAttribute("supplier", supplier);
        return "/hotelactivity/create";
    }
    
    /**
     * 酒店新增
     */
    @RequestMapping(value="/create",method=RequestMethod.POST)
    public String create(@Valid HotelActivity hotelActivity, BindingResult bindingResult, Model model, HttpServletRequest request,
            @RequestParam("file") MultipartFile file){
        if (bindingResult.hasErrors())
        {
            return "/hotelActivity/create";
        }
        
        //路径
        /*String filePath = _fileUploadSetting.getRootPath() + _fileUploadSetting.getHotelPath();*/
        String filePath = "." + File.separator + _fileUploadSetting.getHotelPath();
        
        if(!file.isEmpty()){
            hotelActivity.setCreateTime(new Date());
            
            try
            {
                byte[] bytes=file.getBytes();
                File directory = new File(_fileUploadSetting.getRootPath()+filePath);
                if (!directory.exists())
                {
                    directory.mkdirs();
                }
                String fileName =file.getOriginalFilename();
                String nowpath=filePath + fileName;
 
//                String lujing="."+File.separator + fileName;//此打印出来的路径是/文件名
                //存储图片
                Image image = new Image();
                image.setUpdateTime(new Date());
                image.setFileName(fileName);
                image.setFilePath(nowpath);
                image.setCreateTime(new Date());
               _imageRepository.save(image);
                
                //保存到酒店活动的图片列
                hotelActivity.setImage(image);
                
                //写入
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath()+nowpath)));
                stream.write(bytes);
                stream.close();
                
                //保存酒店活动
                _hotelActivityRepository.save(hotelActivity);
                
                
                //标签保存
                String[] tags=request.getParameterValues("tag");
                
                for(int i=0;i<tags.length;i++){
                if(!(tags[i].equals(null)) && (!(tags[i].equals("")))){
                HotelTags hotelTags=new HotelTags();
                hotelTags.setHotelActivity(hotelActivity);
                hotelTags.setTag(tags[i]);
                hotelTags.setCreatetime(new Date());
                _hotelTagsRepository.save(hotelTags);
                }
                
                }
                
            }
            catch (IOException e)
            {
                model.addAttribute("errorMessage", "上传文件失败");
                e.printStackTrace();
                return "redirect:/hotelActivity/create";
            }
        }
        return "redirect:/hotelActivity";
    }
    
    /**
     * 跳转到酒店活动修改页面
     */
    @RequestMapping(value="/update/{id}",method=RequestMethod.GET)
    public String updateInit(@PathVariable int id,Model model){
        
        
      //查询所有美国城市
        List<City> city=_cityRepository.findByCountryId(2);
        model.addAttribute("city", city);
        
        
      //查询代码表供应商为租车的对象（供应商和5写成固定参数了，写死了）
        Code code=_codeRepository.findByTypeAndValue("供应商",1);
        
        //根据代码表ID，查询供应商信息
        List<Supplier> supplier=_supplierRepository.findByCodeId(code.getId());
        model.addAttribute("supplier", supplier);
        
        //存取酒店活动对象
        HotelActivity hotelActivity=  _hotelActivityRepository.findOne(id);
        model.addAttribute("hotelActivity", hotelActivity);
        
        List<HotelTags> hotelTags=_hotelTagsRepository.findByhotelActivity(hotelActivity);
        for(int i=0;i<hotelTags.size();i++){
        System.out.println(hotelTags.get(i).getTag());        
        }
        
        model.addAttribute("hotelTags", hotelTags);
        return "/hotelactivity/update";
    }
    
    /**
     * 酒店活动修改
     */
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(@Valid HotelActivity hotelActivity, BindingResult bindingResult, @RequestParam("file") MultipartFile file,Model model,HttpServletRequest request)
    {
      //判断是否错误，如果错误，返回到update页面，如果正确继续执行
        if (bindingResult.hasErrors())
        {
            return "/hotelActivity/update"+hotelActivity.getId();
        }
        //如果上传文件为空，则保存之前的信息
        if(file.isEmpty()){
            //获得酒店活动对象
            HotelActivity  hotelActivityimg= _hotelActivityRepository.findOne(hotelActivity.getId());
            //保存酒店活动相应的图片信息
            Image image=_imageRepository.findOne(hotelActivityimg.getImage().getId());
            _imageRepository.save(image);
            
            //修改整个酒店活动对象
            hotelActivity.setImage(image);
            hotelActivity.setCreateTime(new Date());
            _hotelActivityRepository.save(hotelActivity);
            
            
            //删除已有的标签
            List<HotelTags> hotelTag=_hotelTagsRepository.findByhotelActivity(hotelActivity);
            for(int i=0;i<hotelTag.size();i++){
                _hotelTagsRepository.delete(hotelTag.get(i).getId());
            }
            
            //获得页面的标签从新插入
            String[] tags=request.getParameterValues("tag");
            
            for(int i=0;i<tags.length;i++){
            if(!(tags[i].equals(null)) && (!(tags[i].equals("")))){
            HotelTags hotelTags=new HotelTags();
            hotelTags.setHotelActivity(hotelActivity);
            hotelTags.setTag(tags[i]);
            hotelTags.setCreatetime(new Date());
            _hotelTagsRepository.save(hotelTags);
            }
        }
        }
        else if(!file.isEmpty()){
            HotelActivity  hotelActivityimg= _hotelActivityRepository.findOne(hotelActivity.getId());
            
            /*String filePath = _fileUploadSetting.getRootPath() + _fileUploadSetting.getHotelPath();  */ 
            String filePath ="."+ File.separator + _fileUploadSetting.getHotelPath();
          //保存上传图片
            try
            {
                byte[] bytes = file.getBytes();
                File directory = new File(_fileUploadSetting.getRootPath()+filePath);
                if (!directory.exists())
                {
                    directory.mkdirs();
                }
                String fileName =file.getOriginalFilename();
                String nowpath=filePath + fileName;
                
//                String lujing="."+File.separator + nowpath;//此打印出来的路径是/文件名
                //根据图片ID查找图片
                Image image=_imageRepository.findOne(hotelActivityimg.getImage().getId());
                image.setUpdateTime(new Date());
                image.setFileName(fileName);
                image.setFilePath(nowpath);
               _imageRepository.save(image);
               
               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath()+nowpath)));
               stream.write(bytes);
               stream.close();
               
               hotelActivity.setImage(image);
               hotelActivity.setCreateTime(new Date());
               _hotelActivityRepository.save(hotelActivity);
               
               //获得页面的标签，并修改标签
//               String tag=request.getParameter("tag");
//               HotelTags hotelTags= _hotelTagsRepository.findByhotelActivity(hotelActivity);
//                   hotelTags.setTag(tag);
//                   _hotelTagsRepository.save(hotelTags);
               
               //删除已有的标签
               List<HotelTags> hotelTag=_hotelTagsRepository.findByhotelActivity(hotelActivity);
               for(int i=0;i<hotelTag.size();i++){
                   _hotelTagsRepository.delete(hotelTag.get(i).getId());
               }
               
               //获得页面的标签从新插入
               String[] tags=request.getParameterValues("tag");
               
               for(int i=0;i<tags.length;i++){
               if(!(tags[i].equals(null)) && (!(tags[i].equals("")))){
               HotelTags hotelTags=new HotelTags();
               hotelTags.setHotelActivity(hotelActivity);
               hotelTags.setTag(tags[i]);
               hotelTags.setCreatetime(new Date());
               _hotelTagsRepository.save(hotelTags);
               }
           }
               
               
            }
            catch (Exception e)
            {
                model.addAttribute("errorMessage", "上传图片失败");
                return "redirect:/hotelActivity/update" + hotelActivity.getId();
            }
        }
        return "redirect:/hotelActivity";
    }
    
    
    /**
     * 酒店活动信息查询
     */
    @RequestMapping(value="/detail/{id}",method=RequestMethod.GET)
    public String detailInit(@PathVariable int id,Model model){
        
        
        //查询所有美国城市
          List<City> city=_cityRepository.findByCountryId(2);
          model.addAttribute("city", city);
          
          
        //查询代码表供应商为租车的对象（供应商和5写成固定参数了，写死了）
          Code code=_codeRepository.findByTypeAndValue("供应商",1);
          
          //根据代码表ID，查询供应商信息
          List<Supplier> supplier=_supplierRepository.findByCodeId(code.getId());
          model.addAttribute("supplier", supplier);
          
          //存取酒店活动对象
          HotelActivity hotelActivity=  _hotelActivityRepository.findOne(id);
          model.addAttribute("hotelActivity", hotelActivity);
          
          List<HotelTags> hotelTags=_hotelTagsRepository.findByhotelActivity(hotelActivity);
          for(int i=0;i<hotelTags.size();i++){
          System.out.println(hotelTags.get(i).getTag());        
          }
          
          model.addAttribute("hotelTags", hotelTags);
          return "/hotelactivity/detail";
    }
    
    /**
     * 根据酒店id查询酒店内容，传到房间类型管理列表
     */
    @RequestMapping(value="/hotelRoomType/{id}",method=RequestMethod.GET)
    public String hotelRoomTypeInit(@PathVariable int id,Model model){
        model.addAttribute("hotelActivity", _hotelActivityRepository.findOne(id));
        return "/hotelroomtype/index";
    }
    
    
    
}
