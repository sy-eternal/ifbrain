package com.jzeen.travel.website.controller.Activity;



import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.entity.Material;
import com.jzeen.travel.data.entity.MaterialComment;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.UserMaterial;
import com.jzeen.travel.data.repository.CommentReplyRepository;
import com.jzeen.travel.data.repository.MaterialCommentRepository;
import com.jzeen.travel.data.repository.MaterialRepository;
import com.jzeen.travel.data.repository.MaterialTypeRepository;
import com.jzeen.travel.data.repository.NavigationBarRepository;
import com.jzeen.travel.data.repository.NavigationbarModuleRepository;
import com.jzeen.travel.data.repository.UserMaterialRepository;

/**
 * @author sunyan 活动
 *
 */
@Controller
@RequestMapping("/activity")
public class ActivityController
{
	@Autowired
	MaterialRepository  _materialRepository;
	@Autowired
	MaterialTypeRepository  _materialTypeRepository;
	@Autowired
	NavigationBarRepository  _navigationBarRepository;
	@Autowired
	NavigationbarModuleRepository  _navigationbarModuleRepository;
	@Autowired  
	UserMaterialRepository  _userMaterialRepository;
	@Autowired  
	MaterialCommentRepository  _materialCommentRepository;
	
	@Autowired  
	CommentReplyRepository  _CommentReplyRepository;
    /**活动首页
     * @param id
     * @param request
     * @param model
     * @return
     * @throws SQLException 
     * @throws IOException 
     */
   @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request,HttpServletResponse response,Model model)
    {
	   		String type = request.getParameter("type");
	   	  List<MaterialType> materialType =_materialTypeRepository.findAll();
	   	  if(materialType.size()>0){
	   		 model.addAttribute("materialType", materialType);
	   	  }else{
	   		 model.addAttribute("materialType", "");
	   	  }
		  List <MaterialType> types= _materialTypeRepository.findAll();
		   List<Material> material =_materialRepository.findByMaterialTypeOrderByCreateTimeDesc(types.get(0)); 
		   model.addAttribute("material", material); 
        return "/activity/index";
    } 

   //文章列表
   @RequestMapping(value = "/materiallist",method = RequestMethod.GET)
   public String materiallist(HttpServletRequest request,HttpServletResponse response,Model model)
   {
	   String typename= request.getParameter("typename");
	   MaterialType type= _materialTypeRepository.findById(Integer.parseInt(typename));
	   List<Material> material =_materialRepository.findByMaterialTypeOrderByCreateTimeDesc(type); 
	   model.addAttribute("material", material);
	   model.addAttribute("typename", typename.trim());
       return "/activity/materiallist";
   } 
 //文章内容
   @RequestMapping(value = "/materialdetail",method = RequestMethod.GET)
   public String materialdetail(HttpServletRequest request,HttpServletResponse response,Model model) throws SQLException
   {
	   
	   Integer id=Integer.parseInt(request.getParameter("id"));
	   Material material = _materialRepository.findOne(id);
	 	String content = new String(material.getMaterialContent().getBytes((long)1, (int)material.getMaterialContent().length()));  
	 	StringBuffer str =new StringBuffer();
		str.append(content);
		model.addAttribute("material", content);
		model.addAttribute("materialdetail", material);
		
		if(material.getVisitornumber()==null){
			WebUtils.setSessionAttribute(request, "Visitornumber",1);
			material.setVisitornumber(10022);
			_materialRepository.save(material);
		}else{
			if(request.getSession().getAttribute("Visitornumber")==null){
				/*WebUtils.setSessionAttribute(request, "Visitornumber",material.getVisitornumber()+1);*/
				material.setVisitornumber(material.getVisitornumber()+1);
				_materialRepository.save(material);
			}
		}
		if(request.getSession().getAttribute("materialid")!=""){
	   		HttpSession session = request.getSession();
			session.removeAttribute("materialid");
	   		WebUtils.setSessionAttribute(request, "materialid",id);
	   	}else{
	   		WebUtils.setSessionAttribute(request, "materialid",id);
	   	}
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("usersession", user);
	  	List <UserMaterial> number = _userMaterialRepository.findByMaterialIdAndStatus(material.getId(),1);
	  	model.addAttribute("number", number);
	  	if(user!=null){
	  		UserMaterial userMaterial = _userMaterialRepository.findByMaterialIdAndUserId(material.getId(), user.getId());
		  	if(userMaterial==null){
		  		model.addAttribute("userMaterial", null);
		  	}else{
		  		model.addAttribute("userMaterial", userMaterial);
		  	}	
	  	}
	  
	  	List<MaterialComment> comment =_materialCommentRepository.findByMaterialIdOrderByCreateTimeDesc(material.getId());
	  	model.addAttribute("comment", comment);
	  	
       return "/activity/materialdetail";
   }
   
 //留言
   @RequestMapping(value = "/leavemessage", method = RequestMethod.POST)
   public String leavemessage(HttpServletRequest request, Model model) throws Exception
   {
   	String materialid = request.getParameter("materialid");
   	String messagecontent = request.getParameter("messagecontent");
   	User user = (User) request.getSession().getAttribute("user");
   	if(request.getSession().getAttribute("materialid")!=""){
   		HttpSession session = request.getSession();
		session.removeAttribute("materialid");
   		WebUtils.setSessionAttribute(request, "materialid",materialid);
   	}else{
   		WebUtils.setSessionAttribute(request, "materialid",materialid);
   	}
	
    MaterialComment materialComment = new MaterialComment();
    materialComment.setUser(user);
    materialComment.setCreateTime(new Date());
    materialComment.setMaterial(_materialRepository.findOne(Integer.parseInt(materialid)));
    materialComment.setUsercomment(messagecontent);
    _materialCommentRepository.save(materialComment);
//List<MaterialComment> comment =_materialCommentRepository.findByMaterialIdOrderByCreateTimeDesc(Integer.parseInt(materialid));
    return  "redirect:/activity/materialdetail?id="+Integer.parseInt(materialid);
   }
   
 //点赞
   @RequestMapping(value = "/clickzan", method = RequestMethod.POST)
   public String clickzan(HttpServletRequest request, Model model) throws Exception
   {
	User user = (User) request.getSession().getAttribute("user");
	
   	String materialid = request.getParameter("materialid");
	if(request.getSession().getAttribute("materialid")!=""){
   		HttpSession session = request.getSession();
		session.removeAttribute("materialid");
   		WebUtils.setSessionAttribute(request, "materialid",materialid);
   	}else{
   		WebUtils.setSessionAttribute(request, "materialid",materialid);
   	}
    UserMaterial userMaterial = new UserMaterial();
    userMaterial.setStatus(1);
    userMaterial.setMaterial(_materialRepository.findOne(Integer.parseInt(materialid)));
    userMaterial.setCreateTime(new Date());
    userMaterial.setUser(user);
    _userMaterialRepository.save(userMaterial);
   return  "redirect:/activity/materialdetail?id="+Integer.parseInt(materialid);
   } 
   
   //取消点赞
   @RequestMapping(value = "/noclickzan", method = RequestMethod.POST)
   public String noclickzan(HttpServletRequest request, Model model) throws Exception
   {
	User user = (User) request.getSession().getAttribute("user");
   	String materialid = request.getParameter("materialid");
   	if(request.getSession().getAttribute("materialid")!=""){
   		HttpSession session = request.getSession();
		session.removeAttribute("materialid");
   		WebUtils.setSessionAttribute(request, "materialid",materialid);
   	}else{
   		WebUtils.setSessionAttribute(request, "materialid",materialid);
   	}
   	UserMaterial userMaterial=  _userMaterialRepository.findByMaterialIdAndUserId(Integer.parseInt(materialid), user.getId());
   	if(userMaterial!=null){
   	    _userMaterialRepository.delete(userMaterial.getId());
   	}
  	return  "redirect:/activity/materialdetail?id="+Integer.parseInt(materialid);
   } 
}
