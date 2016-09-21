package com.jzeen.travel.website.controller.navigation;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.NavigationbarModule;
import com.jzeen.travel.data.entity.Publicmaterial;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.MaterialRepository;
import com.jzeen.travel.data.repository.MaterialTypeRepository;
import com.jzeen.travel.data.repository.NavigationBarRepository;
import com.jzeen.travel.data.repository.NavigationbarModuleRepository;
import com.jzeen.travel.data.repository.PublicmaterialRepository;

/**
 * @author sunyan 活动
 *
 */
@Controller
@RequestMapping("/navigation")
public class NavigationController
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
	private PublicmaterialRepository _publicmaterialRepository;
	@Autowired
	private CourseReponsitory _courseReponsitory;
   @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request,HttpServletResponse response,Model model)
    {
	   String type = request.getParameter("type");
	  List<NavigationbarModule> navigationbarModule =_navigationbarModuleRepository.findByNavigationBarTypeOrderByTypeAsc(Integer.parseInt(type));
	  if(navigationbarModule.size()>0){
		  model.addAttribute("navigationbarModule", navigationbarModule);
		  model.addAttribute("navigationbarTypeName", navigationbarModule.get(0).getNavigationBar().getName());
		  model.addAttribute("type", type);
	  }else{
		  model.addAttribute("navigationbarModule", "");
		  model.addAttribute("navigationbarTypeName","");
		  model.addAttribute("type", type);
	  }
	 /*  NavigationbarModule navigationBar=_navigationbarModuleRepository.findByNavigationBarTypeAndType(Integer.parseInt(type),0 );
	   	if(navigationBar!=null){
	   		List <Publicmaterial> material=_publicmaterialRepository.findByNavigationbarmoduleIdOrderByCreateTimeAsc(navigationBar.getId());
			model.addAttribute("material", material);
	   	}
	      else{
	   		model.addAttribute("material", "");
	   	 }*/
	  
	  List<Course> course = _courseReponsitory.findByType("0");
		model.addAttribute("course", course);
		
		   NavigationbarModule navigationBar=_navigationbarModuleRepository.findByNavigationBarTypeAndType(Integer.parseInt(type), 1);
		   	if(navigationBar!=null){
		   		model.addAttribute("material", navigationBar);
		   	}
		      else{
		   		model.addAttribute("material", "");
		   	 }
	 
        return "/navigation/index";
    } 
   
   //点击
   @ResponseBody
   @RequestMapping(value = "/findnavigationbarModuletype",method = RequestMethod.GET)
   public String findnavigationbarModuletype(HttpServletRequest request,HttpServletResponse response,Model model)
   {
	  //文章类型
	   Integer navigationbarModuletype = Integer.parseInt(request.getParameter("navigationbarModuletype"));
	  //导航栏类型  0,快慢社区 1，财脑课程 2，代币训练
	   Integer navigationbartrype = Integer.parseInt( request.getParameter("navigationbartrype"));
	   String dataUrl="";
	 //财脑课程
	 if(navigationbartrype==1){
		 //财脑课程介绍
		 if(navigationbarModuletype==0){
			 dataUrl="b1";///course/ifbrainindexintroduction
		 }
		 //线上课程
		 if(navigationbarModuletype==1){
			 dataUrl="b2";///moviematerial/offlinecourse
		 }
		 //线下课程
		 if(navigationbarModuletype==2){
			 dataUrl="b3";		///course/offlinecourse
			 }
		 //家长课程
		 if(navigationbarModuletype==3){
			 dataUrl="b4";	///course/familycourse
		 }
		 if(navigationbarModuletype==4){
			 dataUrl="b5";	
		 }
		 if(navigationbarModuletype==5){
			 dataUrl="b6";	//
		 }
		 
	 }
	 //代币训练
	 if(navigationbartrype==2){
		 //财脑家庭训练器
		 if(navigationbarModuletype==0){
			 dataUrl="c1";	///project/li
		 }
		 //财经心理学
		 if(navigationbarModuletype==1){
			 dataUrl="c2";	///demandcityshopping/maslowdemand
		 }
		 //账户储蓄和预算
		 if(navigationbarModuletype==2){
			 dataUrl="c3";	
			 }
		 //收入职业商业创业
		 if(navigationbarModuletype==3){
			 dataUrl="c4";	///ifbraintask/list
		 }
		 //信用债务和贷款
		 if(navigationbarModuletype==4){
			 dataUrl="c5";	
		 }
		//风险管理和贷款
		 if(navigationbarModuletype==5){
			 dataUrl="c6";	
		 }
		 
	 }
    return dataUrl;
   }
 
}
