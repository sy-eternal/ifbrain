package com.jzeen.travel.admin.controller.parentmanual;

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
import com.jzeen.travel.data.entity.ParentManual;
import com.jzeen.travel.data.entity.ParentManualImage;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.ParentManualImageRepository;
import com.jzeen.travel.data.repository.ParentManualRepository;

//家长手册管理
@Controller
@RequestMapping("/parentmanual")
public class ParentManualController {
	@Autowired
    FileUploadSetting _fileUploadSetting;
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
         return "/parentmanual/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<ParentManual> search()
    {
		List<ParentManual> list = _parentManualRepository.findAll();
		 List<ParentManual> newdata=new ArrayList<ParentManual>();
         for(int i=0;i<list.size();i++){
        	 ParentManual parentManual=new ParentManual();
        	 parentManual.setAuthor(list.get(i).getAuthor());
        	 parentManual.setCreateTime(list.get(i).getCreateTime());
        	 parentManual.setId(list.get(i).getId());
        	 parentManual.setManualName(list.get(i).getManualName());
        	 newdata.add(parentManual);
         }
        return newdata;
    }
	 @RequestMapping(value = "/index", method = RequestMethod.GET)
	    public String loginInit(Model model, HttpServletRequest request)
	    {
		 List<Course> list = _courseRepository.findAll();
		 model.addAttribute("list", list);
	        return "/parentmanual/index";
	    }
	 /*	 //上传文章图片
	 @ResponseBody
	 @RequestMapping(value = "/uploadimg", method = RequestMethod.POST)
	    public String createinfo(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam("upload") MultipartFile[] upload) throws IOException
	    {
		 PrintWriter out = response.getWriter();
		 String filePath="";
		 if(upload.length>0){
			 for(int i = 0;i<upload.length;i++){  
		    	    filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getMaterialPath();
		       if (!upload[i].isEmpty())
		       {
		           try
		           {
		               byte[] bytes = upload[i].getBytes();
		               File directory = new File(filePath);
		               if (!directory.exists())
		               {
		                   directory.mkdirs();
		               }
		              String  filename=upload[i].getOriginalFilename();
		              filePath += filename;
		              String lujing=_fileUploadSetting.getMaterialPath()+filename;
		              MaterialImage  image=new MaterialImage();
		              image.setCreateTime(new Date());
		              image.setFileName(filename);
		              image.setFilePath(lujing);
		              _materialImageRepository.save(image);
		               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
		               stream.write(bytes);
		               stream.close();
		               //上传文件到项目文件夹结束
			              String callback =request.getParameter("CKEditorFuncNum"); 
			              out.println("<script type=\"text/javascript\">");
			              out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" +"/materialimage/show/"+image.getId()+"','')");   
			              out.println("</script>");
		           }
		           catch (Exception e)
		           {
		               e.printStackTrace();
		           }
		       }
		       }
		 }
	        return null;
	    }
	    */
        //创建家长手册
	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file) throws Exception, UnsupportedEncodingException, SQLException
	    {
	    	String title = request.getParameter("manualName");
	    	String author = request.getParameter("author");
	    	String content = request.getParameter("content");
	    	String ordinalNumber = request.getParameter("CourseCode");
	    	CourseCode courseCode = _courseCodeRepository.findOne(Integer.parseInt(ordinalNumber));
	    	//文章所属类型
	      String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getParentmanualPath();
	      byte[] bytes = file.getBytes();
          File directory = new File(filePath);
           if (!directory.exists())
           {
             directory.mkdirs();
           }
	        String  filename=file.getOriginalFilename();
	        filePath += filename;
	        String lujing=_fileUploadSetting.getParentmanualPath()+filename;
	        ParentManualImage  image=new ParentManualImage();
	        image.setCreateTime(new Date());
	        image.setFileName(filename);
	        image.setFilePath(lujing);
	        _parentManualImageRepository.save(image);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(bytes);
            stream.close();
	    	String courseid = request.getParameter("Course");
	    	Course course = _courseRepository.findOne(Integer.parseInt(courseid));
	    	ParentManual parentManual=new ParentManual();
	    	parentManual.setCourse(course);
	    	parentManual.setCreateTime(new Date());
	    	parentManual.setManualName(title);
		    Blob b = new SerialBlob(content.getBytes("utf-8"));
		    parentManual.setManualContent(b);
		    parentManual.setOrdinalNumber(courseCode.getOrdinalNumber());
		    parentManual.setAuthor(author);
		    parentManual.setParentmanualimage(image);
		    _parentManualRepository.save(parentManual);
			return "redirect:/parentmanual/list";
	    }
	 //家长手册
		 @ResponseBody
		 @RequestMapping(value = "/updateuploadimg", method = RequestMethod.POST)
		    public String updateinfo(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam("upload") MultipartFile[] upload) throws IOException
		    {
			 PrintWriter out = response.getWriter();
			 String filePath="";
			 if(upload.length>0){
				 for(int i = 0;i<upload.length;i++){  
			    	    filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getParentmanualPath();
			       if (!upload[i].isEmpty())
			       {
			           try
			           {
			               byte[] bytes = upload[i].getBytes();
			               File directory = new File(filePath);
			               if (!directory.exists())
			               {
			                   directory.mkdirs();
			               }
			              String  filename=upload[i].getOriginalFilename();
			              filePath += filename;
			              String lujing=_fileUploadSetting.getParentmanualPath()+filename;
			              ParentManualImage  image=new ParentManualImage();
			              image.setCreateTime(new Date());
			              image.setFileName(filename);
			              image.setFilePath(lujing);
			              _parentManualImageRepository.save(image);
			               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			               stream.write(bytes);
			               stream.close();
			               //上传文件到项目文件夹结束
				              String callback =request.getParameter("CKEditorFuncNum"); 
				              out.println("<script type=\"text/javascript\">");
				              out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" +"/parentmanualimage/show/"+image.getId()+"','')");   
				              out.println("</script>");
			           }
			           catch (Exception e)
			           {
			               e.printStackTrace();
			           }
			       }
			       }
			 }
		        return null;
		    }
		    
	  //初始化修改文章页面
	    @RequestMapping(value = "/initupdate", method = RequestMethod.GET)
	    public String update(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	ParentManual parentManual = _parentManualRepository.findOne(Integer.parseInt(id));
	    	Blob manualContent = parentManual.getManualContent();
	    	String content = new String(manualContent.getBytes((long)1, (int)manualContent.length())); 
	    	model.addAttribute("parentManual", parentManual);
	    	model.addAttribute("content", content);
	    	List<Course> list = _courseRepository.findAll();
			model.addAttribute("list", list);
			return "/parentmanual/updateparentmanual";
	    }
	    
	    //修改家长素材
	   @RequestMapping(value = "/parentmanualupdate", method = RequestMethod.POST)
	    public String materialupdate(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file) throws Exception
	    {
	    	String courseid = request.getParameter("Course");
	    	String id = request.getParameter("id");
	    	String title = request.getParameter("manualName");
	    	String author = request.getParameter("author");
	    	String content = request.getParameter("content");
	    	Course course = _courseRepository.findOne(Integer.parseInt(courseid));
	    	 ParentManual parentManual = _parentManualRepository.findOne(Integer.parseInt(id));
	    	 String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getParentmanualPath();
		      byte[] bytes = file.getBytes();
	          File directory = new File(filePath);
	           if (!directory.exists())
	           {
	             directory.mkdirs();
	           }
		        String  filename=file.getOriginalFilename();
		        filePath += filename;
		        String lujing=_fileUploadSetting.getParentmanualPath()+filename;
		        ParentManualImage  image=parentManual.getParentmanualimage();
		        image.setCreateTime(new Date());
		        image.setFileName(filename);
		        image.setFilePath(lujing);
		        _parentManualImageRepository.save(image);
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	            stream.write(bytes);
	            stream.close();
	            parentManual.setAuthor(author);
	            parentManual.setCreateTime(new Date());
	    	Blob b = new SerialBlob(content.getBytes("utf-8"));
	    	parentManual.setManualContent(b);;
	    	parentManual.setCourse(course);
	    	parentManual.setManualName(title);
	    	_parentManualRepository.save(parentManual);
			return "redirect:/parentmanual/list";
	    }
	    
	    //删除文章
	    @ResponseBody
	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public void delete(HttpServletRequest request)
	    {
	    	String id = request.getParameter("id");
	    	_parentManualRepository.delete(Integer.parseInt(id));
	    }
	  
	/*  //datatable列表显示
		@ResponseBody
	    @RequestMapping(value = "/searchcarouse", method = RequestMethod.GET)
	    public List<CarouselImg> searchcarouse()
	    {
	          List<CarouselImg> data = _carouselImgRepository.getAllImgOrderBySortNumber();
	        return data;
	    }
		  //轮播列表页
		@RequestMapping(value = "/carousellist", method = RequestMethod.GET)
	    public String carousellist(HttpServletRequest request, Model model)
	    {
	         return "/material/carousellist";
	    }
		 //首页轮播新增跳转页面
	    @RequestMapping(value = "/carousel", method = RequestMethod.GET)
	    public String carousel(Model model, HttpServletRequest request)
	    {
	        return "/material/carousel";
	    }
	    
	      */
	    
	    //上传家长手册内容中的图片
	    @ResponseBody
	    @RequestMapping(value = "/contentimagecreate", method = RequestMethod.POST)
	    public String contentimagecreate(Model model, HttpServletRequest request,HttpServletResponse response,@RequestParam("upload") MultipartFile[] upload) throws Exception
	    {
	    	PrintWriter out = response.getWriter();
	    	String filePath="";
	    	if(upload.length>0){
				 for(int i = 0;i<upload.length;i++){  
			    	    filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getParentmanualPath();
			       if (!upload[i].isEmpty())
			       {
			           try
			           {
			               byte[] bytes = upload[i].getBytes();
			               File directory = new File(filePath);
			               if (!directory.exists())
			               {
			                   directory.mkdirs();
			               }
			              String  filename=upload[i].getOriginalFilename();
			              filePath += filename;
			              String lujing=_fileUploadSetting.getParentmanualPath()+filename;
			              ParentManualImage  image=new ParentManualImage();
			              image.setCreateTime(new Date());
			              image.setFileName(filename);
			              image.setFilePath(lujing);
			              _parentManualImageRepository.save(image);
			              WebUtils.setSessionAttribute(request, "imageid", image.getId());
			               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			               stream.write(bytes);
			               stream.close();
			               //上传文件到项目文件夹结束
				              String callback =request.getParameter("CKEditorFuncNum");   
				              out.println("<script type=\"text/javascript\">");
				              out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" +"/parentmanualimage/show/"+image.getId()+"','')");   
				              out.println("$(\"#flag\").val(\"+image.getId()+\")");
				              out.println("</script>");
			           }
			           catch (Exception e)
			           {
			               e.printStackTrace();
			           }
			       }
			       }
			 }
	        return null;
	    }
	   /* //图片轮播新增序号
	    @RequestMapping(value = "/createsortnumber", method = RequestMethod.POST)
	    public String createsortnumber(HttpServletRequest request, Model model) throws Exception
	    {
	    	Integer imageid =(Integer)WebUtils.getSessionAttribute(request, "imageid");
	    	CarouselImg carouselImg = _carouselImgRepository.findOne(imageid);
	    	String sortnumber = request.getParameter("sortnumber");
	    	String content = request.getParameter("content");
	    	carouselImg.setContent(content);
	    	carouselImg.setSortnumber(Integer.parseInt(sortnumber));
	    	_carouselImgRepository.save(carouselImg);
	    	WebUtils.setSessionAttribute(request, "imageid", "");
	    	return  "redirect:/material/carousellist";
	    }	
	    //上移
	    @ResponseBody
	    @RequestMapping(value = "/up", method = RequestMethod.POST)
	    public String up(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	//选中的对象
	    	CarouselImg carouselImg = _carouselImgRepository.findOne(Integer.parseInt(id));
	    	Integer carouselImgsortnumber = carouselImg.getSortnumber();
	    	//上一条记录
	    	Integer sortnumber = _carouselImgRepository.findByUpSortnumber(carouselImg.getSortnumber());
	    	if(sortnumber!=null){
	    	CarouselImg carouselImg1 = _carouselImgRepository.findBySortnumber(sortnumber);
	    	Integer carouselImg1sortnumber = carouselImg1.getSortnumber();
	    	carouselImg1.setSortnumber(carouselImgsortnumber);
	    	carouselImg.setSortnumber(carouselImg1sortnumber);
	    	_carouselImgRepository.save(carouselImg);
	    	_carouselImgRepository.save(carouselImg1);
	    	}
	    	return "";
	    }	
	    //下移
	    @ResponseBody
	    @RequestMapping(value = "/down", method = RequestMethod.POST)
	    public String down(HttpServletRequest request, Model model) throws Exception
	    {
	   	String id = request.getParameter("id");
	    	//选中的对象
	    	CarouselImg carouselImg = _carouselImgRepository.findOne(Integer.parseInt(id));
	    	Integer carouselImgsortnumber = carouselImg.getSortnumber();
	    	//下一条记录
	    	Integer sortnumber = _carouselImgRepository.findByDownSortnumber(carouselImg.getSortnumber());
	    	if(sortnumber!=null){
		    	CarouselImg carouselImg1 = _carouselImgRepository.findBySortnumber(sortnumber);
		    	Integer carouselImg1sortnumber = carouselImg1.getSortnumber();
		    	carouselImg1.setSortnumber(carouselImgsortnumber);
		    	carouselImg.setSortnumber(carouselImg1sortnumber);
		    	_carouselImgRepository.save(carouselImg);
		    	_carouselImgRepository.save(carouselImg1);
		    	}
	    	return  "";
	    }
	    //删除
	    @ResponseBody
	    @RequestMapping(value = "/parentmanual", method = RequestMethod.POST)
	    public String materialdelete(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	//选中的对象
	    	CarouselImg carouselImg = _carouselImgRepository.findOne(Integer.parseInt(id));
	    	_carouselImgRepository.delete(carouselImg);
	    	return  "";
	    }
	   */ 
}
