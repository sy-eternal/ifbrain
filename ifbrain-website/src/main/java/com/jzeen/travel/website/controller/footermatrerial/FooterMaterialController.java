package com.jzeen.travel.website.controller.footermatrerial;



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
 * @author 
 *
 */
@Controller
@RequestMapping("/footermaterial")
public class FooterMaterialController
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
    
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(HttpServletRequest request,HttpServletResponse response,Model model) throws SQLException
    { 
    	String type = request.getParameter("type");
    	Integer navigationbartrype = 3;
    	List<NavigationbarModule> navigationbarModule = _NavigationbarModuleRepository.findByNavigationBarTypeOrderByTypeAsc(navigationbartrype);
    	NavigationbarModule nav = _NavigationbarModuleRepository.findByNavigationBarTypeAndType(navigationbartrype,Integer.parseInt(type));
    	if(navigationbarModule.size()>0){
        	model.addAttribute("navigationbarModule", navigationbarModule);
        	model.addAttribute("navigationbarModulelist",nav.getTitle());
        	model.addAttribute("navigationbarModulelistid",nav.getId());
        }else{
        	model.addAttribute("navigationbarModule", "");
        	model.addAttribute("navigationbarModulelist","" );
        	model.addAttribute("navigationbarModulelistid","");
        }
    	
    	NavigationbarModule navigationBar=_NavigationbarModuleRepository.findByNavigationBarTypeAndType(navigationbartrype, Integer.parseInt(type));
	   	if(navigationBar!=null){
	   		List <Publicmaterial> material=_publicmaterialRepository.findByNavigationbarmoduleIdOrderByCreateTimeAsc(navigationBar.getId());
			List<Publicmaterial> list =new ArrayList<Publicmaterial>();
			if(material.size()>0){
	   		 for(int i =0;i<material.size();i++){
	   			String content = new String(material.get(i).getPublicmaterialContent().getBytes((long)1, (int)material.get(i).getPublicmaterialContent().length()));  
	   			Publicmaterial mtr = new Publicmaterial();
	   			mtr.setContent(content);
	   			mtr.setCreateTime(material.get(i).getCreateTime());
	   			mtr.setHeadPortrait(material.get(i).getHeadPortrait());
	   			mtr.setAuthor(material.get(i).getAuthor());
	   			mtr.setNavigationbarmodule(material.get(i).getNavigationbarmodule());
	   			mtr.setPublicmaterialName(material.get(i).getPublicmaterialName());
	   			mtr.setPublicmaterialContent(material.get(i).getPublicmaterialContent());
	   			list.add(mtr);
	   		 }
	   		model.addAttribute("material", list);
	   	 }else{
	   		model.addAttribute("material", "");
	   	 }
	   	}
    	return "/footermaterial/index";
    } 
    
 
   
   
}
