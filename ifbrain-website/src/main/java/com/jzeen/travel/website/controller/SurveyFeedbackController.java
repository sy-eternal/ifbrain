package com.jzeen.travel.website.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;











import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.RouteTag;
import com.jzeen.travel.data.entity.SurveyFeedback;
import com.jzeen.travel.data.entity.SurveyImageRelate;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.surveyFeedbackRepository;
import com.jzeen.travel.data.repository.surveyImageRepository;
import com.jzeen.travel.website.setting.FileUploadSetting;

@Controller
@RequestMapping("/survey")
public class SurveyFeedbackController {
	
	  @Autowired
	    private FileUploadSetting _fileUploadSetting;
	  @Autowired
	   private  ImageRepository  _imageRepository;
	  @Autowired
	   private  surveyFeedbackRepository  _surveyFeedbackRepository;
	  @Autowired
	   private  surveyImageRepository  _surveyImageRepository;
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(HttpServletRequest request,Model model){
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user",user);
		return "/survey/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String adding(Model model,SurveyFeedback surveyFeedback,SurveyImageRelate surveyImageRelate1,HttpServletRequest request,@RequestParam("file") MultipartFile[] file2){
		 
		   //路径
		     //windows本地图片测试     
		      String filePath = _fileUploadSetting.getRootPath();
		        
		      //linux环境上测试
		     //  String filePath = "." + File.separator + _fileUploadSetting.getRoutePath();  
	        
	       
	        	User user = (User) request.getSession().getAttribute("user");
	   
	        		String username = request.getParameter("username");
	        	  String title = request.getParameter("title");
	               String textarea = request.getParameter("textarea");
	               String select = request.getParameter("select");
	        	surveyFeedback.setCreateTime(new Date());
	        	surveyFeedback.setContent(textarea);
	        	surveyFeedback.setTitle(title);
	        	surveyFeedback.setFunctionTypeId(select);
	        	surveyFeedback.setUsername(username);
	        	surveyFeedback.setUser(user);
	        	
	        	_surveyFeedbackRepository.save(surveyFeedback);
	           
	           for(int i=0;i<file2.length;i++){
                try
                {
                    byte[] bytes = file2[i].getBytes();
                    File directory = new File(_fileUploadSetting.getRootPath()+filePath);
                    
                    if (!directory.exists())
                    {
                        directory.mkdirs();
                    }
                    String fileName =file2[i].getOriginalFilename();
                    String nowpath=filePath + fileName;
                    String path=File.separator + fileName;
                    //图片上传
                    Image image = new Image();
                    image.setCreateTime(new Date());
                    image.setFileName(fileName);
                    image.setFilePath(nowpath);
                    _imageRepository.save(image);
                   SurveyImageRelate surveyImageRelate=new SurveyImageRelate(); 
                    surveyImageRelate.setImage(image);
 	               surveyImageRelate.setSurveyFeedback(surveyFeedback);
 	             
 	              _surveyImageRepository.save(surveyImageRelate);
                    //写文件
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath() + nowpath)));
                    stream.write(bytes);
                    stream.close();
                    
                }catch(Exception e){
                	  model.addAttribute("errorMessage", "上传文件失败");
  	                e.printStackTrace();
  	                return "redirect:/survey/add";
                }
              
            }
		return "/survey/add";
	}

}
