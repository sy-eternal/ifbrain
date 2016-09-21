package com.jzeen.travel.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
import com.jzeen.travel.data.entity.Route;
import com.jzeen.travel.data.entity.RouteDays;
import com.jzeen.travel.data.entity.RouteTag;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.RouteDaysRepository;
import com.jzeen.travel.data.repository.RouteRepository;
import com.jzeen.travel.data.repository.RouteTagsRepository;


@Controller
@RequestMapping("/route")
public class RouteController
{
    
    @Autowired
    private RouteRepository _routeRepository;
    @Autowired
    private FileUploadSetting _fileUploadSetting;
    @Autowired
   private  ImageRepository  _imageRepository;
    @Autowired
  private RouteTagsRepository  _routeTagsRepository;
    @Autowired
    private RouteDaysRepository  _routeDaysRepository;
    
    
    
    /**
     * 默认跳转页面
     */
    
    @RequestMapping(method=RequestMethod.GET)
    public String index(Model model, HttpServletRequest request){
        
        List<Route> list = _routeRepository.findAll();
        model.addAttribute("list", list);
        return "/route/index";
    }
    
    
    /**
     * 跳转到线路产品新增页面
     */
    @RequestMapping(value="/create" , method=RequestMethod.GET)
    public String createInit(@ModelAttribute Route route,Model model)
    {
        Integer maxId = _routeRepository.getMaxId();
        
        if(maxId==null){
            maxId = 1;
        }else{
            maxId= _routeRepository.getMaxId()+1;
        }
        route.setRouteNum("RT00"+maxId);
        return "/route/create";
    }
    //线路产品新增
    @RequestMapping(value="/create",method=RequestMethod.POST)
    public String create(Route route,Model model, HttpServletRequest request,@RequestParam("file") MultipartFile file){
    
        
        //路径
     //windows本地图片测试     
      String filePath = _fileUploadSetting.getRootPath() + _fileUploadSetting.getRoutePath();
        
      //linux环境上测试
     //  String filePath = "." + File.separator + _fileUploadSetting.getRoutePath();  
        
        if(!file.isEmpty()){
            route.setCreateTime(new Date());
            try
            {
                byte[] bytes=file.getBytes();
               // File directory = new File(_fileUploadSetting.getRootPath()+filePath);     // linux环境上测试  
                File directory = new File(filePath);//windows本地图片测试
                if (!directory.exists())
                {
                    directory.mkdirs();
                }
                String fileName =file.getOriginalFilename();
                String nowpath=filePath + fileName;
 
//                String lujing="."+File.separator + fileName;//此打印出来的路径是/文件名
                //存储图片
                Image image = new Image();
                image.setFileName(fileName);
                image.setFilePath(nowpath);
                image.setCreateTime(new Date());
               _imageRepository.save(image);
                
                //保存到酒店活动的图片列
               route.setImage(image);
                
                //写入
                //BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath()+nowpath))); //linux环境上测试
               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(nowpath)));//   windows本地图片测试 
                stream.write(bytes);
                stream.close();
                //保存酒店活动
                _routeRepository.save(route);
                
                
                //标签保存
                String[] tags=request.getParameterValues("tag");
                
                for(int i=0;i<tags.length;i++){
                if(!(tags[i].equals(null)) && (!(tags[i].equals("")))){
                    RouteTag routeTags=new RouteTag();
                    routeTags.setRoute(route);
                    routeTags.setTag(tags[i]);
                    routeTags.setCreateTime(new Date());
                    _routeTagsRepository.save(routeTags);
                    }
                }
                
            }
            catch (IOException e)
            {
                model.addAttribute("errorMessage", "上传文件失败");
                e.printStackTrace();
                return "redirect:/route/create";
            }
        }
        return "redirect:/route";
    }
    
    
    /**
     * 删除产品路线
     */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
    public String delete(@PathVariable String id,Model model){
        
        Route route = _routeRepository.findOne(Integer.parseInt(id));
     /*   Image image = route.getImage();
        _imageRepository.delete(image);*/
        _routeRepository.delete(route);
        return "redirect:/route";
    }
    
    
    /**
     * 初始化修改页面
     */
    
    @RequestMapping(value="/initUpdate/{id}",method=RequestMethod.GET)
    public String initUpdate(@PathVariable String id,Model model){
        
        Route route = _routeRepository.findOne(Integer.parseInt(id));
        model.addAttribute("route", route);
        List<RouteTag> routeTags = _routeTagsRepository.findByroute(route);
        
        model.addAttribute("routeTags", routeTags);
        
       return  "/route/update";
    }
      
    /**
     * 产品路线修改页面
     */
    
    @RequestMapping(value="/update",method=RequestMethod.POST)
    public String update(Route route, @RequestParam("file") MultipartFile file,Model model,HttpServletRequest request){
        Route route2 = _routeRepository.findOne(route.getId());
        if(file.isEmpty()){
          Image image = _imageRepository.findOne(route2.getImage().getId());
          _imageRepository.save(image);
          route2.setCreateTime(new Date());
          route2.setImage(image);
          _routeRepository.save(route2);
          
          //删除旧的标签
          List<RouteTag> routeTags = _routeTagsRepository.findByroute(route2);
          for(int i=0;i<routeTags.size();i++){
              _routeTagsRepository.delete(routeTags.get(i));
          }
          
          //获得新的标签保存
          String[] tags=request.getParameterValues("tag");
          
          for(int i=0;i<tags.length;i++){
          if(!(tags[i].equals(null)) && (!(tags[i].equals("")))){
              RouteTag routeTag=new RouteTag();
              routeTag.setRoute(route);
              routeTag.setTag(tags[i]);
              routeTag.setCreateTime(new Date());
              _routeTagsRepository.save(routeTag);
              }
          }
          
          
          
            
        }else{
            
            
          //windows本地图片测试     
            String filePath = _fileUploadSetting.getRootPath() + _fileUploadSetting.getRoutePath();
              
            //linux环境上测试
           //  String filePath = "." + File.separator + _fileUploadSetting.getRoutePath();  
            try
            {
                byte[] bytes=file.getBytes();
               // File directory = new File(_fileUploadSetting.getRootPath()+filePath);     // linux环境上测试  
                File directory = new File(filePath);//windows本地图片测试
                if (!directory.exists())
                {
                    directory.mkdirs();
                }
                String fileName =file.getOriginalFilename();
                String nowpath=filePath + fileName;
 
//                String lujing="."+File.separator + fileName;//此打印出来的路径是/文件名
                //存储图片
                Image image=_imageRepository.findOne(route2.getImage().getId());
                image.setFileName(fileName);
                image.setUpdateTime(new Date());
                image.setFilePath(nowpath);
               _imageRepository.save(image);
                
                //保存到酒店活动的图片列
               route2.setImage(image);
                route2.setCreateTime(new Date());
                //写入
                //BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath()+nowpath))); //linux环境上测试
               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(nowpath)));//   windows本地图片测试 
                stream.write(bytes);
                stream.close();
                //保存酒店活动
                _routeRepository.save(route2);
                
                //删除旧的标签
                List<RouteTag> routeTags = _routeTagsRepository.findByroute(route2);
                for(int i=0;i<routeTags.size();i++){
                    _routeTagsRepository.delete(routeTags.get(i));
                }
                 
                
                //获得新的标签保存
                String[] tags=request.getParameterValues("tag");
                
                for(int i=0;i<tags.length;i++){
                if(!(tags[i].equals(null)) && (!(tags[i].equals("")))){
                    RouteTag routeTag=new RouteTag();
                    routeTag.setRoute(route);
                    routeTag.setTag(tags[i]);
                    routeTag.setCreateTime(new Date());
                    _routeTagsRepository.save(routeTag);
                    }
                }
                
            }
            catch (IOException e)
            {
                model.addAttribute("errorMessage", "上传文件失败");
                e.printStackTrace();
                return "redirect:/route/create";
            }
        }
        return "redirect:/route";
    }
    
    /**
     * 产品路线详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value="/detail/{id}",method=RequestMethod.GET)
    public String detail(@PathVariable String id,Model model){
        Route route = _routeRepository.findOne(Integer.parseInt(id));
        model.addAttribute("route", route);
        List<RouteDays> routeDays = route.getRouteDays();
        if(routeDays.size()>0){
            model.addAttribute("routeDays", routeDays);
        }else{
        Integer maxId = _routeDaysRepository.getMaxId();
        if(maxId==null){
           maxId=1; 
        }else{
            maxId+=1;
        }
        RouteDays   routeDay=new RouteDays();
        routeDay.setCreateTime(new Date());
        routeDay.setRoute(route);
        routeDay.setDayId(maxId);
        _routeDaysRepository.save(routeDay);
        List<RouteDays> list = _routeDaysRepository.findAll();
        model.addAttribute("routeDays", list);
        }
       return  "/route/detail";
    }
    
    
   /**
    * 删除行程 
    * @param model
    * @param request
    * @return
    */
    @RequestMapping(value="/deleteByRouteDayId",method=RequestMethod.GET)
    public String deleteByRouteDayId(Model model,HttpServletRequest request){
        String id = request.getParameter("id");
        RouteDays routeDays = _routeDaysRepository.findOne(Integer.parseInt(id));
        _routeDaysRepository.delete(routeDays);
        String routeId = request.getParameter("routeId");
        Route route = _routeRepository.findOne(Integer.parseInt(routeId));
       return  "redirect:/route/detail/"+route.getId();
    }
    
    
    /**
     * 添加行程
     */
    
    @RequestMapping(value="/addRouteDay/{id}",method=RequestMethod.GET)
    public String addRouteDay(@PathVariable String id,Model model){
        
        Route route = _routeRepository.findOne(Integer.parseInt(id));
        model.addAttribute("route", route);
        Integer maxId = _routeDaysRepository.getMaxId();
        if(maxId==null){
           maxId=1; 
        }else{
            maxId+=1;
        }
        RouteDays   routeDay=new RouteDays();
        routeDay.setCreateTime(new Date());
        routeDay.setRoute(route);
        routeDay.setDayId(maxId);
        _routeDaysRepository.save(routeDay);
       return  "redirect:/route/detail/"+route.getId();
    }
    
}
