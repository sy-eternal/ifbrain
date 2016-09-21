package com.jzeen.travel.admin.controller.material;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.CarouselImg;
import com.jzeen.travel.data.entity.CommentReply;
import com.jzeen.travel.data.entity.Material;
import com.jzeen.travel.data.entity.MaterialComment;
import com.jzeen.travel.data.entity.MaterialImage;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.entity.Member;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.UserMaterial;
import com.jzeen.travel.data.repository.CarouselImgRepository;
import com.jzeen.travel.data.repository.CommentReplyRepository;
import com.jzeen.travel.data.repository.MaterialCommentRepository;
import com.jzeen.travel.data.repository.MaterialImageRepository;
import com.jzeen.travel.data.repository.MaterialRepository;
import com.jzeen.travel.data.repository.MaterialTypeRepository;
import com.jzeen.travel.data.repository.MemberRepository;
import com.jzeen.travel.data.repository.UserMaterialRepository;


@Controller
@RequestMapping("/material")
public class MaterialController {
	@Autowired
    FileUploadSetting _fileUploadSetting;
	@Autowired
    MaterialImageRepository _materialImageRepository;
	@Autowired
	MaterialTypeRepository    _materialTypeRepository;
	@Autowired
	MaterialRepository  _materialRepository;
	@Autowired  
	CarouselImgRepository  _carouselImgRepository;
	
	@Autowired  
	UserMaterialRepository  _userMaterialRepository;
	@Autowired  
	MaterialCommentRepository  _materialCommentRepository;
	
	@Autowired  
	CommentReplyRepository  _CommentReplyRepository;
	
	
	@Autowired  
	MemberRepository  _MemberRepository;
	//显示所有的文章列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
         return "/material/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Material> search()
    {
          List<Material> data = _materialRepository.findAll();
          List<Material> newdata=new ArrayList<Material>();
          for(int i=0;i<data.size();i++){
        	  Material material=new Material();
        	  material.setId(data.get(i).getId());
        	  material.setAuthor(data.get(i).getMaterialType().getMaterialName());
        	  material.setCreateTime(data.get(i).getCreateTime());
        	  material.setMaterialName(data.get(i).getMaterialName());
        	  //material.setMaterialType(data.get(i).getMaterialType());
        	  newdata.add(material);
          }
        return newdata;
    }
	 @RequestMapping(value = "/index", method = RequestMethod.GET)
	    public String loginInit(Model model, HttpServletRequest request)
	    {
		 List<MaterialType> list = _materialTypeRepository.findAll();
		 model.addAttribute("list", list);
	        return "/material/index";
	    }
	 //上传文章图片
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
        //创建文章	 
	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file) throws Exception, UnsupportedEncodingException, SQLException
	    {
	    	String title = request.getParameter("title");
	    	String author = request.getParameter("author");
	    	String content = request.getParameter("content");
	    	//文章所属类型
	      String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getMaterialPath();
	      byte[] bytes = file.getBytes();
          File directory = new File(filePath);
           if (!directory.exists())
           {
             directory.mkdirs();
           }
	        String  filename=file.getOriginalFilename();
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
	    	String materialtype = request.getParameter("materialtype");
	    	MaterialType type = _materialTypeRepository.findOne(Integer.parseInt(materialtype));
		    Material material=new Material();
		    material.setCreateTime(new Date());
		    material.setMaterialName(title);
		    material.setMaterialType(type);
		    Blob b = new SerialBlob(content.getBytes("utf-8"));
		    material.setMaterialContent(b);
		    material.setAuthor(author);
		    material.setMaterialimage(image);
		   _materialRepository.save(material);
			return "redirect:/material/list";
	    }
	  //修改文章图片
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
	  //初始化修改文章页面
	    @RequestMapping(value = "/initupdate", method = RequestMethod.GET)
	    public String update(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	Material material = _materialRepository.findOne(Integer.parseInt(id));
	    	Blob materialContent = material.getMaterialContent();
	    	String content = new String(materialContent.getBytes((long)1, (int)materialContent.length())); 
	    	model.addAttribute("material", material);
	    	model.addAttribute("content", content);
	    	 List<MaterialType> list = _materialTypeRepository.findAll();
			 model.addAttribute("list", list);
			return "/material/updatematerial";
	    }
	    
	    //修改文章
	    @RequestMapping(value = "/materialupdate", method = RequestMethod.POST)
	    public String materialupdate(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file) throws Exception
	    {
	    	String materialtype = request.getParameter("materialtype");
	    	String id = request.getParameter("id");
	    	String title = request.getParameter("title");
	    	String author = request.getParameter("author");
	    	String content = request.getParameter("content");
	    	MaterialType type = _materialTypeRepository.findOne(Integer.parseInt(materialtype));
	    	Material material = _materialRepository.findOne(Integer.parseInt(id));
	    	 String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getMaterialPath();
		      byte[] bytes = file.getBytes();
	          File directory = new File(filePath);
	           if (!directory.exists())
	           {
	             directory.mkdirs();
	           }
		        String  filename=file.getOriginalFilename();
		        filePath += filename;
		        String lujing=_fileUploadSetting.getMaterialPath()+filename;
		        MaterialImage  image=material.getMaterialimage();
		        image.setCreateTime(new Date());
		        image.setFileName(filename);
		        image.setFilePath(lujing);
	           _materialImageRepository.save(image);
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	            stream.write(bytes);
	            stream.close();
	    	material.setAuthor(author);
	    	material.setCreateTime(new Date());
	    	Blob b = new SerialBlob(content.getBytes("utf-8"));
	    	material.setMaterialContent(b);
	    	material.setMaterialType(type);
	    	material.setMaterialName(title);
	    	_materialRepository.save(material);
			return "redirect:/material/list";
	    }
	    
	    //删除文章
	    @ResponseBody
	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public void delete(HttpServletRequest request)
	    {
	    	String id = request.getParameter("id");
	    	_materialRepository.delete(Integer.parseInt(id));
	    }
	  
	  //datatable列表显示
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
	    
	    
	    
	    //首页轮播上传新增
	    @ResponseBody
	    @RequestMapping(value = "/carouselcreate", method = RequestMethod.POST)
	    public String carouselcreate(Model model, HttpServletRequest request,HttpServletResponse response,@RequestParam("upload") MultipartFile[] upload) throws Exception
	    {
	    	PrintWriter out = response.getWriter();
	    	String filePath="";
	    	if(upload.length>0){
				 for(int i = 0;i<upload.length;i++){  
			    	    filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getCarouselPath();
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
			              String lujing=_fileUploadSetting.getCarouselPath()+filename;
			              CarouselImg  image=new CarouselImg();
			              image.setCreateTime(new Date());
			              image.setFileName(filename);
			              image.setFilePath(lujing);
			              _carouselImgRepository.save(image);
			              WebUtils.setSessionAttribute(request, "imageid", image.getId());
			               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			               stream.write(bytes);
			               stream.close();
			               //上传文件到项目文件夹结束
				              String callback =request.getParameter("CKEditorFuncNum");   
				              out.println("<script type=\"text/javascript\">");
				              out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" +"/carouselimage/show/"+image.getId()+"','')");   
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
	    //图片轮播新增序号
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
	    @RequestMapping(value = "/materialdelete", method = RequestMethod.POST)
	    public String materialdelete(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	//选中的对象
	    	CarouselImg carouselImg = _carouselImgRepository.findOne(Integer.parseInt(id));
	    	_carouselImgRepository.delete(carouselImg);
	    	return  "";
	    }
	    @ResponseBody
	    @RequestMapping(value = "/check", method = RequestMethod.POST)
	    public boolean check(HttpServletRequest request, Model model) throws Exception
	    {
	    	String sortnumber = request.getParameter("sortnumber");
	    	//选中的对象
	    	CarouselImg carouselImg = _carouselImgRepository.findBySortnumber(Integer.parseInt(sortnumber));
	    	if(carouselImg!=null){
	    		return false;
	    	}else{
	    		return true;
	    	}
	    }
	    
	    //评论列表
	    @RequestMapping(value = "/commentmaterial", method = RequestMethod.GET)
	    public String commentmaterial(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	Material material = _materialRepository.findOne(Integer.parseInt(id));
	    	
	    	
	    	 String content = new String(material.getMaterialContent().getBytes((long)1, (int)material.getMaterialContent().length()));  
	    	  StringBuffer str =new StringBuffer();
		  		str.append(content);
		  		model.addAttribute("content", content);
		  		model.addAttribute("material", material);
		  	
		  	List <UserMaterial> number = _userMaterialRepository.findByMaterialIdAndStatus(material.getId(),1);
		  	model.addAttribute("number", number.size());

		  	
		  	List<MaterialComment> comment =_materialCommentRepository.findByMaterialIdOrderByCreateTimeDesc(material.getId());
		  	model.addAttribute("comment", comment);
		
		  	
			return "/material/commentmaterial";
	    }
		
	  //回复
	    @RequestMapping(value = "/replycomment", method = RequestMethod.POST)
	    public String replycomment(HttpServletRequest request, Model model) throws Exception
	    {
	    	String commentid = request.getParameter("commentid");
	    	String textcommentcont = request.getParameter("textcommentcont");
	    	 Member member = (Member) request.getSession().getAttribute("member");
	    	CommentReply commentReply = new CommentReply();
	    	commentReply.setMaterialComment(_materialCommentRepository.findOne(Integer.parseInt(commentid)));
	    	commentReply.setMembercomment(textcommentcont);
	    	commentReply.setMembercommentTime(new Date());
	    	commentReply.setMember(_MemberRepository.findOne(member.getId()));
	    	_CommentReplyRepository.save(commentReply);
	    	return  "redirect:/material/commentmaterial?id="+_materialCommentRepository.findOne(Integer.parseInt(commentid)).getMaterial().getId();
	    }
	    
	    //删除用户评论
	    @ResponseBody
	    @RequestMapping(value = "/deleteall", method = RequestMethod.POST)
	    public String deleteall(HttpServletRequest request, Model model) throws Exception
	    {
	    	String commentid = request.getParameter("commentid");
	    	//选中的对象
	    	List<CommentReply> comm =_CommentReplyRepository.findByMaterialCommentId(Integer.parseInt(commentid));
	    	if(comm.size()>0){
	    		for(int i =0;i <comm.size();i++){
	    			_CommentReplyRepository.deleteByMaterialCommentId(comm.get(i).getId());
	    		}
	    	}
	    	_materialCommentRepository.delete(Integer.parseInt(commentid));
			return "";	
	    }
	  //删除作者评论
	    @ResponseBody
	    @RequestMapping(value = "/commentmemberdelete", method = RequestMethod.POST)
	    public String commentmemberdelete(HttpServletRequest request, Model model) throws Exception
	    {
	    	String replyid = request.getParameter("replyid");
	    	//选中的对象
	    	CommentReply comm =_CommentReplyRepository.findOne(Integer.parseInt(replyid))  ; 	
	    			
	    		
	    	_CommentReplyRepository.delete(comm.getId());
			return "";	
	    }
	    	
}
