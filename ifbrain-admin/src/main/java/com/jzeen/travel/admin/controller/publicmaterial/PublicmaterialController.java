package com.jzeen.travel.admin.controller.publicmaterial;

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

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.HeadPortrait;
import com.jzeen.travel.data.entity.MaterialImage;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.entity.NavigationbarModule;
import com.jzeen.travel.data.entity.ParentManualImage;
import com.jzeen.travel.data.entity.Publicmaterial;
import com.jzeen.travel.data.repository.HeadPortraitRepository;
import com.jzeen.travel.data.repository.MaterialImageRepository;
import com.jzeen.travel.data.repository.MaterialTypeRepository;
import com.jzeen.travel.data.repository.NavigationbarModuleRepository;
import com.jzeen.travel.data.repository.ParentManualImageRepository;
import com.jzeen.travel.data.repository.PublicmaterialRepository;


@Controller
@RequestMapping("/publicmaterial")
public class PublicmaterialController {
	@Autowired
    FileUploadSetting _fileUploadSetting;
	@Autowired
	MaterialImageRepository  _materialImageRepository;
	@Autowired
	NavigationbarModuleRepository _navigationbarModuleRepository;
	@Autowired
	ParentManualImageRepository _parentManualImageRepository;
	@Autowired
	PublicmaterialRepository  _publicmaterialRepository;
	@Autowired
	MaterialTypeRepository  _materialTypeRepository;
	@Autowired
	HeadPortraitRepository  _headPortraitRepository;
	//显示所有的文章列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
         return "/publicmaterial/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Publicmaterial> search()
    {
		List<Publicmaterial> list = _publicmaterialRepository.findAll();
		 List<Publicmaterial> newdata=new ArrayList<Publicmaterial>();
        for(int i=0;i<list.size();i++){
        	Publicmaterial publicmaterial=new Publicmaterial();
        publicmaterial.setAuthor(list.get(i).getAuthor());
        publicmaterial.setNavigationbarmodule(list.get(i).getNavigationbarmodule());
       	publicmaterial.setCreateTime(list.get(i).getCreateTime());
       	publicmaterial.setId(list.get(i).getId());
       	publicmaterial.setPublicmaterialName(list.get(i).getPublicmaterialName());
       	 newdata.add(publicmaterial);
        }
       return newdata;
    }
	 @RequestMapping(value = "/index", method = RequestMethod.GET)
	    public String loginInit(Model model, HttpServletRequest request)
	    {
		 List<NavigationbarModule> list = _navigationbarModuleRepository.findAll();
		 model.addAttribute("list", list);
	        return "/publicmaterial/index";
	    }
	 
        //创建财脑课程和代币训练下几个模块的文章
	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file) throws Exception, UnsupportedEncodingException, SQLException
	    {
	    	
	    	String navigationbarmoduleid = request.getParameter("navigationbarmodule");
	    	String publicmaterialName = request.getParameter("publicmaterialName");
	    	String author = request.getParameter("author");
	    	String content = request.getParameter("content");
	    	NavigationbarModule navigationbarModule = _navigationbarModuleRepository.findOne(Integer.parseInt(navigationbarmoduleid));
	    	
	       String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getHeadportraitPath();
	       byte[] bytes = file.getBytes();
           File directory = new File(filePath);
           if (!directory.exists())
           {
             directory.mkdirs();
           }
	       String  filename=file.getOriginalFilename();
	        filePath += filename;
	        String lujing=_fileUploadSetting.getMaterialPath()+filename;
	        HeadPortrait  image=new HeadPortrait();
	        image.setCreateTime(new Date());
	        image.setFileName(filename);
	        image.setFilePath(lujing);
	        _headPortraitRepository.save(image);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(bytes);
            stream.close();
            Publicmaterial publicmaterial=new Publicmaterial();
            publicmaterial.setAuthor(author);
            publicmaterial.setCreateTime(new Date());
            publicmaterial.setHeadPortrait(image);
            publicmaterial.setNavigationbarmodule(navigationbarModule);
            publicmaterial.setPublicmaterialName(publicmaterialName);
            Blob b = new SerialBlob(content.getBytes("utf-8"));
            publicmaterial.setPublicmaterialContent(b);
            _publicmaterialRepository.save(publicmaterial);
			return "redirect:/publicmaterial/list";
	    }
		    
	  //初始化导航模块
	    @RequestMapping(value = "/initupdate", method = RequestMethod.GET)
	    public String update(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	 Publicmaterial publicmaterial = _publicmaterialRepository.findOne(Integer.parseInt(id));
	    	 Blob publicmaterialContent = publicmaterial.getPublicmaterialContent();
		    	String content = new String(publicmaterialContent.getBytes((long)1, (int)publicmaterialContent.length())); 
	    	 model.addAttribute("content", content);
	    	 model.addAttribute("publicmaterial", publicmaterial);
	    	List<NavigationbarModule> list = _navigationbarModuleRepository.findAll();
			 model.addAttribute("list", list);
			return "/publicmaterial/updatepublicmaterial";
	    }
	    
	    //修改财脑课程和代币训练下的文章
	       @RequestMapping(value = "/publicmaterialupdate", method = RequestMethod.POST)
	    public String materialupdate(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file) throws Exception
	    {
	    	   
			   String id = request.getParameter("id");
			   String content = request.getParameter("content");
			   String publicmaterialName = request.getParameter("publicmaterialName");
				String navigationbarmoduleid = request.getParameter("navigationbarmodule");
		    	NavigationbarModule navigationbarModule = _navigationbarModuleRepository.findOne(Integer.parseInt(navigationbarmoduleid));
		    	Publicmaterial publicmaterial = _publicmaterialRepository.findOne(Integer.parseInt(id));
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
		        if(publicmaterial.getHeadPortrait()==null){
		        HeadPortrait image = new HeadPortrait();
		        image.setCreateTime(new Date());
		        image.setFileName(filename);
		        image.setFilePath(lujing);
		        _headPortraitRepository.save(image);
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	            stream.write(bytes);
	            stream.close();
	            publicmaterial.setCreateTime(new Date());
	            publicmaterial.setHeadPortrait(image);
	            publicmaterial.setPublicmaterialName(publicmaterialName);
	            publicmaterial.setNavigationbarmodule(navigationbarModule);
	            Blob b = new SerialBlob(content.getBytes("utf-8"));
	            publicmaterial.setPublicmaterialContent(b);
	            _publicmaterialRepository.save(publicmaterial);
		        }else{
		        	 HeadPortrait image =publicmaterial.getHeadPortrait();
				        image.setCreateTime(new Date());
				        image.setFileName(filename);
				        image.setFilePath(lujing);
				        _headPortraitRepository.save(image);
			            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			            stream.write(bytes);
			            stream.close();
			            publicmaterial.setCreateTime(new Date());
			            publicmaterial.setHeadPortrait(image);
			            publicmaterial.setPublicmaterialName(publicmaterialName);
			            publicmaterial.setNavigationbarmodule(navigationbarModule);
			            Blob b = new SerialBlob(content.getBytes("utf-8"));
			            publicmaterial.setPublicmaterialContent(b);
			            _publicmaterialRepository.save(publicmaterial);
		        }
			return "redirect:/publicmaterial/list";
	    }
	    
	      //删除财脑课程和代币训练下的文章
	    @ResponseBody
	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public void delete(HttpServletRequest request)
	    {
	    	String id = request.getParameter("id");
	    	_publicmaterialRepository.delete(Integer.parseInt(id));
	    }
	    //上传文章内容中的图片
		 @ResponseBody
		 @RequestMapping(value = "/updateuploadimg", method = RequestMethod.POST)
		    public String updateinfo(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam("upload") MultipartFile[] upload) throws IOException
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
}
