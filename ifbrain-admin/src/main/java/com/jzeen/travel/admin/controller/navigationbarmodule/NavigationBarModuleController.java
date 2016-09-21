package com.jzeen.travel.admin.controller.navigationbarmodule;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseCode;
import com.jzeen.travel.data.entity.HeadPortrait;
import com.jzeen.travel.data.entity.NavigationBar;
import com.jzeen.travel.data.entity.NavigationbarModule;
import com.jzeen.travel.data.entity.ParentManual;
import com.jzeen.travel.data.entity.ParentManualImage;
import com.jzeen.travel.data.repository.CourseClassReponsitory;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.HeadPortraitRepository;
import com.jzeen.travel.data.repository.IfbrainIndexReponsitory;
import com.jzeen.travel.data.repository.NavigationBarRepository;
import com.jzeen.travel.data.repository.NavigationbarModuleRepository;
import com.jzeen.travel.data.repository.ParentManualImageRepository;
import com.jzeen.travel.data.repository.ParentManualRepository;

@Controller
@RequestMapping("/navigationbarmodule")
public class NavigationBarModuleController {
	@Autowired
    private CourseClassReponsitory _cCourseClassReponsitory;
	@Autowired
    private CourseReponsitory _cCourseReponsitory;
	@Autowired
    private CourseCodeRepository _cCourseCodeRepository;
	@Autowired
	private IfbrainIndexReponsitory  _ifbrainIndexReponsitory;
	@Autowired
	HeadPortraitRepository  _headPortraitRepository;
	@Autowired
    FileUploadSetting _fileUploadSetting;
	@Autowired
	NavigationBarRepository  _navigationBarRepository;
	@Autowired
	NavigationbarModuleRepository _navigationbarModuleRepository;
	@Autowired
	ParentManualImageRepository _parentManualImageRepository;
	@Autowired
	ParentManualRepository  _parentManualRepository;
	@Autowired
	CourseReponsitory  _courseRepository;
	@Autowired
	CourseCodeRepository  _courseCodeRepository;
	//显示所有的文章列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
         return "/navigationbarmodule/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<NavigationbarModule> search()
    {
		List<NavigationbarModule> list = _navigationbarModuleRepository.findAll();
		 List<NavigationbarModule> newdata=new ArrayList<NavigationbarModule>();
         for(int i=0;i<list.size();i++){
        	 NavigationbarModule navigationbarModule=new NavigationbarModule();
        	 navigationbarModule.setAuthor(list.get(i).getAuthor());
        	 navigationbarModule.setNavigationBar(list.get(i).getNavigationBar());
        	 navigationbarModule.setCreateTime(list.get(i).getCreateTime());
        	 navigationbarModule.setId(list.get(i).getId());
        	 navigationbarModule.setTitle(list.get(i).getTitle());
        	 navigationbarModule.setHeadPortrait(list.get(i).getHeadPortrait());
        	 navigationbarModule.setType(list.get(i).getType());
        	 newdata.add(navigationbarModule);
         }
        return newdata;
    }
	 @RequestMapping(value = "/index", method = RequestMethod.GET)
	    public String loginInit(Model model, HttpServletRequest request)
	    {
		List<NavigationBar> list = _navigationBarRepository.findAll();
		 model.addAttribute("list", list);
	        return "/navigationbarmodule/index";
	    }
        //创建导航模块
	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file) throws Exception, UnsupportedEncodingException, SQLException
	    {
	    	String title = request.getParameter("name");
	    	String author = request.getParameter("author");
	    	String navigationbarid = request.getParameter("navigationbar");
	    	String type = request.getParameter("type");
	    	NavigationBar navigationBar = _navigationBarRepository.findOne(Integer.parseInt(navigationbarid));
	    	//文章所属类型
	      String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getHeadportraitPath();
	      byte[] bytes = file.getBytes();
          File directory = new File(filePath);
           if (!directory.exists())
           {
             directory.mkdirs();
           }
	        String  filename=file.getOriginalFilename();
	        filePath += filename;
	        String lujing=_fileUploadSetting.getHeadportraitPath()+filename;
	        HeadPortrait  image=new HeadPortrait();
	        image.setCreateTime(new Date());
	        image.setFileName(filename);
	        image.setFilePath(lujing);
	        _headPortraitRepository.save(image);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(bytes);
            stream.close();
	    	NavigationbarModule navigationbarModule=new NavigationbarModule();
	    	navigationbarModule.setNavigationBar(navigationBar);
	    	navigationbarModule.setCreateTime(new Date());
	    	navigationbarModule.setTitle(title);
	    	navigationbarModule.setAuthor(author);
	    	navigationbarModule.setHeadPortrait(image);
	    	navigationbarModule.setType(Integer.parseInt(type));
	    	_navigationbarModuleRepository.save(navigationbarModule);
			return "redirect:/navigationbarmodule/list";
	    }
		    
	  //初始化导航模块
	    @RequestMapping(value = "/initupdate", method = RequestMethod.GET)
	    public String update(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	 NavigationbarModule navigationbarModule = _navigationbarModuleRepository.findOne(Integer.parseInt(id));
	    	model.addAttribute("navigationbarModule", navigationbarModule);
	    	List<NavigationBar> list = _navigationBarRepository.findAll();
			 model.addAttribute("list", list);
			return "/navigationbarmodule/updatenavigationbarmodule";
	    }
	    
	    //修改家长素材
	   @RequestMapping(value = "/navigationbarmoduleupdate", method = RequestMethod.POST)
	    public String materialupdate(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file) throws Exception
	    {
			   String id = request.getParameter("id");
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String navigationbarid = request.getParameter("navigationbar");
			String type = request.getParameter("type");
		   NavigationbarModule navigationbarModule = _navigationbarModuleRepository.findOne(Integer.parseInt(id));
	    	 String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getHeadportraitPath();
		      byte[] bytes = file.getBytes();
	          File directory = new File(filePath);
	           if (!directory.exists())
	           {
	             directory.mkdirs();
	           }
		        String  filename=file.getOriginalFilename();
		        filePath += filename;
		        String lujing=_fileUploadSetting.getHeadportraitPath()+filename;
		      
		       if(navigationbarModule.getHeadPortrait()==null){
		       HeadPortrait headPortrait =new HeadPortrait();
		       headPortrait.setCreateTime(new Date());
		       headPortrait.setFileName(filename);
		       headPortrait.setFilePath(lujing);
		       _headPortraitRepository.save(headPortrait);
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	            stream.write(bytes);
	            stream.close();
	            navigationbarModule.setAuthor(author);
	            navigationbarModule.setCreateTime(new Date());
		    	NavigationBar navigationBar = _navigationBarRepository.findOne(Integer.parseInt(navigationbarid));
		    	navigationbarModule.setNavigationBar(navigationBar);
		    	navigationbarModule.setTitle(title);
		    	navigationbarModule.setType(Integer.parseInt(type));
		    	_navigationbarModuleRepository.save(navigationbarModule);
		       }else{
		    	   HeadPortrait headPortrait =navigationbarModule.getHeadPortrait();
		    	   headPortrait.setCreateTime(new Date());
			       headPortrait.setFileName(filename);
			       headPortrait.setFilePath(lujing);
			       _headPortraitRepository.save(headPortrait);
		            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
		            stream.write(bytes);
		            stream.close();
		            navigationbarModule.setAuthor(author);
		            navigationbarModule.setCreateTime(new Date());
			    	NavigationBar navigationBar = _navigationBarRepository.findOne(Integer.parseInt(navigationbarid));
			    	navigationbarModule.setNavigationBar(navigationBar);
			    	navigationbarModule.setTitle(title);
			    	navigationbarModule.setType(Integer.parseInt(type));
			    	_navigationbarModuleRepository.save(navigationbarModule);
		       }
			return "redirect:/navigationbarmodule/list";
	    }
	    
	    //删除模块
	    @ResponseBody
	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public void delete(HttpServletRequest request)
	    {
	    	String id = request.getParameter("id");
	    	_navigationbarModuleRepository.delete(Integer.parseInt(id));
	    }
	  
	
	    
	  
	  
}
