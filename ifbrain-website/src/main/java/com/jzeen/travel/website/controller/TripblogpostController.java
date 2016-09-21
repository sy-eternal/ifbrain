package com.jzeen.travel.website.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.TripBlogCommentsRepository;
import com.jzeen.travel.data.repository.TripBlogItemImageRelateRepository;
import com.jzeen.travel.data.repository.TripBlogItemRepository;
import com.jzeen.travel.data.repository.TripBlogRepository;
import com.jzeen.travel.website.setting.FileUploadSetting;

@Controller
@RequestMapping("/triplogpost")
public class TripblogpostController {
	 @Autowired
	 private TripBlogCommentsRepository _tripBlogCommentsRepository;
	 @Autowired
	 private TripBlogItemImageRelateRepository _tripBlogItemImageRelateRepository;
	 @Autowired
	 private TripBlogRepository _tripBlogRepository;
	 @Autowired
	 private TripBlogItemRepository _tripBlogItemRepository;
	 
	 @Autowired
	 private FileUploadSetting _fileUploadSetting;
	 
	 @Autowired
	 private ImageRepository _imageRepository;
	 @RequestMapping(value="/save",method=RequestMethod.POST)
	 public String saveTripLogPost(@RequestParam("file") MultipartFile[]  file, HttpServletRequest request){
		 for(int i=0;i<file.length;i++){
			 if(!file[i].isEmpty()){
				 try
			        {
			        	String filePath = "."+File.separator +_fileUploadSetting.getBlogPath();
			            byte[] bytes = file[i].getBytes();
			            File directory = new File(_fileUploadSetting.getRootPath()+filePath);
			            if (!directory.exists())
			            {
			                directory.mkdirs();
			            }
			            String fileName =file[i].getOriginalFilename();
			            String nowpath=filePath + fileName;
			           //File.separator为斜杠"/"
			             String lujing=File.separator + fileName;//此打印出来的路径是/文件名
			             Image image = new Image();
			             image.setUpdateTime(new Date());
			             image.setFileName(fileName);
			             image.setFilePath(nowpath);
			             _imageRepository.save(image);
			          
			             BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath() + nowpath)));
			             stream.write(bytes);
			             stream.close();
			             
			    		 
			      }
			      catch (Exception e)
			      {
			          
			          
			      }
			 }
		 }
		 
		 
		 String[] filename=request.getParameterValues("file");
		 System.out.println(filename);
		 System.out.println(file);
//		 String time= request.getParameter("time");
		 String time= request.getParameter("time");
		// System.out.println(time+"||||"+time.toString());
		 String wenben= request.getParameter("wenben");
		 String[] options= request.getParameterValues("option");
		 String city= request.getParameter("city");
		 String neirong= request.getParameter("neirong");
		 return null;
		 
	 }
	 @ResponseBody
	 @RequestMapping(value="/saveimage",method=RequestMethod.POST)
	 public String saveTripLogPostImage(@RequestParam("file") MultipartFile file){
	      //保存上传图片
	        try
	        {
	        	 String filePath = "."+File.separator +_fileUploadSetting.getBlogPath();
	            byte[] bytes = file.getBytes();
	            File directory = new File(_fileUploadSetting.getRootPath()+filePath);
	            if (!directory.exists())
	            {
	                directory.mkdirs();
	            }
	            
	            String fileName =file.getOriginalFilename();
	            String nowpath=filePath + fileName;
	           //File.separator为斜杠"/"
	             String lujing=File.separator + fileName;//此打印出来的路径是/文件名
	             Image image = new Image();
	             image.setUpdateTime(new Date());
	             image.setFileName(fileName);
	             image.setFilePath(nowpath);
	             _imageRepository.save(image);
	          
	             BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath() + nowpath)));
	             stream.write(bytes);
	             stream.close();
	             String result = "fdsf";
	    		 return result;
	      }
	      catch (Exception e)
	      {
	         
	    	  
	          String jsonresult = "fdsfdsafds";
	          return jsonresult;
	      }
		 
		 
	     
	 }
}
