package com.jzeen.travel.website.controller.Course;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseClass;
import com.jzeen.travel.data.entity.Material;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.entity.Money;
import com.jzeen.travel.data.entity.NavigationBar;
import com.jzeen.travel.data.entity.NavigationbarModule;
import com.jzeen.travel.data.entity.ParentManual;
import com.jzeen.travel.data.entity.Publicmaterial;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.NavigationBarRepository;
import com.jzeen.travel.data.repository.NavigationbarModuleRepository;
import com.jzeen.travel.data.repository.ParentManualRepository;
import com.jzeen.travel.data.repository.PublicmaterialRepository;

/**
 * @author sunyan 课程
 *
 */
@Controller
@RequestMapping("/course")
public class CourseController
{
	@Autowired
    private CourseCodeRepository _cCourseCodeRepository;
	@Autowired
	private CourseReponsitory _courseReponsitory;
	@Autowired
	private ParentManualRepository _ParentManualRepository;
	@Autowired
	private PublicmaterialRepository _publicmaterialRepository;
	@Autowired 
	private 	NavigationBarRepository _NavigationBarRepository;
    
	@Autowired 
	private 	NavigationbarModuleRepository _NavigationbarModuleRepository;
    /**课程首页
     * @param id
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request,HttpServletResponse response,Model model)
    {
        return "/course/index";
    } 
    
 
   @RequestMapping(value = "/kctable",method = RequestMethod.GET)
   public String kctable(HttpServletRequest request,HttpServletResponse response,Model model)
   {
       return "/course/kctable";
   } 
   
   @RequestMapping(value = "/jinqikecheng",method = RequestMethod.GET) 
   public String jinqikecheng(HttpServletRequest request,HttpServletResponse response,Model model)
   {
       return "/course/jinqikecheng";
   } 

   @RequestMapping(value = "/offlinecoursehome",method = RequestMethod.GET)
   public String offlinecourse(HttpServletRequest request,HttpServletResponse response,Model model) throws SQLException
   {
	   String type =request.getParameter("type");
	   String navigationbartrype =request.getParameter("navigationbartrype");
	   NavigationbarModule navigationBar=_NavigationbarModuleRepository.findByNavigationBarTypeAndType(Integer.parseInt(navigationbartrype), Integer.parseInt(type));
	   	if(navigationBar!=null){
	   		List <Publicmaterial> material=_publicmaterialRepository.findByNavigationbarmoduleIdOrderByCreateTimeAsc(navigationBar.getId());	   	 
	   		model.addAttribute("material", material);
	   	}
	      else{
	   		model.addAttribute("material", "");
	   	 }
       return "/course/offlinecoursehome";
   } 
   
   
   
   @RequestMapping(value = "/offlinecoursedetail",method = RequestMethod.GET)
   public String offlinecoursedetail(HttpServletRequest request,HttpServletResponse response,Model model) throws SQLException
   {
	  
	 
	   Integer id=Integer.parseInt(request.getParameter("id"));
	   Publicmaterial  Publicmaterial= _publicmaterialRepository.findOne(id);
	 	String content = new String(Publicmaterial.getPublicmaterialContent().getBytes((long)1, (int)Publicmaterial.getPublicmaterialContent().length()));  
	 	
		model.addAttribute("Publicmaterial", content);
		model.addAttribute("Publicmaterialdetail", Publicmaterial);
      
	   
		
       return "/course/offlinecoursedetail";
   } 
   
   //财脑课程介绍
   
   @RequestMapping(value = "/ifbrainindexintroduction",method = RequestMethod.GET)
   public String ifbrainindexintroduction(HttpServletRequest request,HttpServletResponse response,Model model)
   {
	   model.addAttribute("clickvalue","K1" );
       return "/course/ifbrainindexintroduction";
   } 
   
   
   @RequestMapping(value = "/clickbuttontoggle",method = RequestMethod.GET)
   public String clickbuttontoggle(HttpServletRequest request,HttpServletResponse response,Model model)
   {
	   String clicktextvalue =request.getParameter("clicktextvalue");
	   model.addAttribute("clickvalue",clicktextvalue );
	   
       return "/course/ifbrainindexintroduction";
   } 
   
   //点击家长课堂级别显示
 
 	@RequestMapping(value = "/familycourse", method = RequestMethod.GET)
     public String familycourse(HttpServletRequest request, Model model)
     {
 		List<Course> course = _courseReponsitory.findByType("0");
 		model.addAttribute("course", course);
 		 String type =request.getParameter("type");
		   String navigationbartrype =request.getParameter("navigationbartrype");
		   NavigationbarModule navigationBar=_NavigationbarModuleRepository.findByNavigationBarTypeAndType(Integer.parseInt(navigationbartrype), Integer.parseInt(type));
		   	if(navigationBar!=null){
		   		model.addAttribute("material", navigationBar);
		   	}
		      else{
		   		model.addAttribute("material", "");
		   	 }
          return "/course/familycourse";
     }
 	//列表
 	
 	 @RequestMapping(value = "/familycourselist",method = RequestMethod.GET)
 	   public String familycourselist(HttpServletRequest request,HttpServletResponse response,Model model)
 	   {
 		   String courseid= request.getParameter("courseid");
 		  
 		   List<ParentManual> parentManual =_ParentManualRepository.findByCourseIdOrderByOrdinalNumberAsc(Integer.parseInt(courseid));
 		   if(parentManual.size()>0){
 			   model.addAttribute("parentManualdetail", parentManual.get(0).getCourse().getCourseLevel());
 			  model.addAttribute("parentManual", parentManual);
 			
 		   }else{
 			  model.addAttribute("parentManual", "");
 		   }

 			
 	       return "/course/familycourselist";
 	   } 
 
//详细信息
 	 @RequestMapping(value = "/familycoursedetail",method = RequestMethod.GET)
 	   public String familycoursedetail(HttpServletRequest request,HttpServletResponse response,Model model) throws SQLException
 	   {
 		   Integer id=Integer.parseInt(request.getParameter("id"));
 		   ParentManual  parentManual= _ParentManualRepository.findOne(id);
 		 	String content = new String(parentManual.getManualContent().getBytes((long)1, (int)parentManual.getManualContent().length()));  
 		 	 if(parentManual.getVisitornumber()==null){
   				WebUtils.setSessionAttribute(request, "Visitornumber",1);
   				parentManual.setVisitornumber(10022);
   				_ParentManualRepository.save(parentManual);
   			}else{
   				if(request.getSession().getAttribute("Visitornumber")==null){
   					/*WebUtils.setSessionAttribute(request, "Visitornumber",material.getVisitornumber()+1);*/
   					parentManual.setVisitornumber(parentManual.getVisitornumber()+1);
   					_ParentManualRepository.save(parentManual);
   				}
   			}
 			model.addAttribute("parentManual", content);
 			model.addAttribute("parentManualdetail", parentManual);
 	      
 	       return "/course/familycoursedetail";
 	   } 
 	@RequestMapping(value = "/ifbrainindexintroductionhome",method = RequestMethod.GET)
    public String ifbrainindexintroductionhome(HttpServletRequest request,HttpServletResponse response,Model model)
    {

 	   String type =request.getParameter("type");
 	   String navigationbartrype =request.getParameter("navigationbartrype");
 	   NavigationbarModule navigationBar=_NavigationbarModuleRepository.findByNavigationBarTypeAndType(Integer.parseInt(navigationbartrype), Integer.parseInt(type));
 	   	if(navigationBar!=null){
 	   		List <Publicmaterial> material=_publicmaterialRepository.findByNavigationbarmoduleIdOrderByCreateTimeAsc(navigationBar.getId());
 			model.addAttribute("material", material);
 			
 	   	}
 	      else{
 	   		model.addAttribute("material", "");
 	   	 }
 	 
 	   
        return "/course/ifbrainindexintroductionhome";
    } 
 	//财脑课程介绍详细
 	
 	 @RequestMapping(value = "/ifbrainindexintroductiondetail",method = RequestMethod.GET)
	   public String ifbrainindexintroductiondetail(HttpServletRequest request,HttpServletResponse response,Model model) throws SQLException
	   {
		   Integer id=Integer.parseInt(request.getParameter("id"));
		   Publicmaterial  Publicmaterial= _publicmaterialRepository.findOne(id);
		 	String content = new String(Publicmaterial.getPublicmaterialContent().getBytes((long)1, (int)Publicmaterial.getPublicmaterialContent().length()));  
		 	
			model.addAttribute("Publicmaterial", content);
			model.addAttribute("Publicmaterialdetail", Publicmaterial);
			if(Publicmaterial.getVisitornumber()==null){
				WebUtils.setSessionAttribute(request, "PublicmaterialVisitornumber",1);
				Publicmaterial.setVisitornumber(10022);
				_publicmaterialRepository.save(Publicmaterial);
			}else{
				if(request.getSession().getAttribute("PublicmaterialVisitornumber")==null){
					/*WebUtils.setSessionAttribute(request, "Visitornumber",material.getVisitornumber()+1);*/
					Publicmaterial.setVisitornumber(Publicmaterial.getVisitornumber()+1);
					_publicmaterialRepository.save(Publicmaterial);
				}
			}
	       return "/course/ifbrainindexintroductiondetail";
	   } 
}
