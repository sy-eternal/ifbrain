package com.jzeen.travel.admin.controller.courseinformation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.CarouselImg;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.CommentReply;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseClass;
import com.jzeen.travel.data.entity.CourseCode;
import com.jzeen.travel.data.entity.CourseInformation;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Material;
import com.jzeen.travel.data.entity.MaterialComment;
import com.jzeen.travel.data.entity.MaterialImage;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.entity.Member;
import com.jzeen.travel.data.entity.School;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.UserMaterial;
import com.jzeen.travel.data.repository.CarouselImgRepository;
import com.jzeen.travel.data.repository.CommentReplyRepository;
import com.jzeen.travel.data.repository.CourseInformationRepository;
import com.jzeen.travel.data.repository.MaterialCommentRepository;
import com.jzeen.travel.data.repository.MaterialImageRepository;
import com.jzeen.travel.data.repository.MaterialRepository;
import com.jzeen.travel.data.repository.MaterialTypeRepository;
import com.jzeen.travel.data.repository.MemberRepository;
import com.jzeen.travel.data.repository.SchoolClassRepository;
import com.jzeen.travel.data.repository.SchoolRepository;
import com.jzeen.travel.data.repository.StudentRepository;
import com.jzeen.travel.data.repository.UserMaterialRepository;


@Controller
@RequestMapping("/courseinformation")
public class CourseInformationController {

	
	
	@Autowired  
	SchoolRepository  _SchoolRepository;
	@Autowired  
	SchoolClassRepository  _SchoolClassRepository;
	
	@Autowired  
	StudentRepository  _studentRepository;
	@Autowired  
	CourseInformationRepository  _CourseInformationRepository;
	
	//显示所有的文章列表
	@RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
         return "/courseinformation/index";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/searchs", method = RequestMethod.GET)
    public List<CourseInformation> search()
    {
          List<CourseInformation> data = _CourseInformationRepository.findAll();
          List<CourseInformation> newdata=new ArrayList<CourseInformation>();
          for(int i=0;i<data.size();i++){
        	  CourseInformation courseinformation = new CourseInformation();
        	  courseinformation.setCreateTime(data.get(i).getCreateTime());
        	  courseinformation.setDescription(data.get(i).getDescription());
        	  courseinformation.setLessonName(data.get(i).getLessonName());
        	  courseinformation.setOrdinalNumber(data.get(i).getOrdinalNumber());
        	  courseinformation.setSchoolClass(data.get(i).getSchoolClass());
        	  newdata.add(courseinformation);
          }
        return data;
    }
	  //删除
	    @ResponseBody
	    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	    public void delete(@PathVariable int id)
	    {
	    	_CourseInformationRepository.delete(id);
	        
	    }
	    //学校新增页面
	    @RequestMapping(value = "/create", method = RequestMethod.GET)
	    public String createInit(@ModelAttribute City cityactive, Model model)
	    {
	    	 List <School> school =_SchoolRepository.findAll();
		       model.addAttribute("school", school);
	        return "/courseinformation/create";

	    }
	    
	    //学校新增实现

	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create( Model model, HttpServletRequest request)
	    {
	        
	        	CourseInformation courseinformation = new CourseInformation();
	        	courseinformation.setCreateTime(new Date());
	        	courseinformation.setDescription(request.getParameter("courseDescription"));
	        	courseinformation.setLessonName(request.getParameter("lessonName"));
	        	courseinformation.setOrdinalNumber(Integer.parseInt(request.getParameter("ordinalNumber")));
	        	courseinformation.setSchoolClass(_SchoolClassRepository.findOne(Integer.parseInt(request.getParameter("SchoolClass"))));
	        	_CourseInformationRepository.save(courseinformation);
	        	
			return "redirect:/courseinformation/index";

	    }
	    
	    
	    //进入修改页面 
		 @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
		    public String updateInit(@PathVariable int id, Model model)
		    {
		       CourseInformation courseinformation =  _CourseInformationRepository.findOne(id);
		        model.addAttribute("courseinformation", courseinformation);
		        return "/courseinformation/update";
		    }
		 //修改班级信息
		 @RequestMapping(value = "/update", method = RequestMethod.POST)
		    public String update(@Valid CourseInformation courseinformation, HttpServletRequest request) throws ParseException
		    {   
			 CourseInformation courseinformations = _CourseInformationRepository.findOne(courseinformation.getId());
			courseinformations.setCreateTime(new Date());
			courseinformations.setDescription(request.getParameter("courseDescription"));
			courseinformations.setLessonName(request.getParameter("lessonName"));
			courseinformations.setOrdinalNumber(Integer.parseInt(request.getParameter("ordinalNumber")));
			courseinformations.setSchoolClass(_SchoolClassRepository.findOne(Integer.parseInt(request.getParameter("SchoolClass"))));
			_CourseInformationRepository.save(courseinformations);
		        return "redirect:/courseinformation/index";
		    } 

}
