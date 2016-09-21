package com.jzeen.travel.admin.controller.school;

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
import com.jzeen.travel.data.entity.CitySchool;
import com.jzeen.travel.data.entity.CommentReply;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseClass;
import com.jzeen.travel.data.entity.CourseCode;
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
import com.jzeen.travel.data.repository.CitySchoolRepository;
import com.jzeen.travel.data.repository.CommentReplyRepository;
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
@RequestMapping("/school")
public class SchoolController {

	
	
	@Autowired  
	SchoolRepository  _SchoolRepository;
	@Autowired  
	SchoolClassRepository  _SchoolClassRepository;
	@Autowired
	MemberRepository _MemberRepository;
	@Autowired  
	StudentRepository  _studentRepository;
	@Autowired  
	CitySchoolRepository _CitySchoolRepository;
	
	//显示所有的文章列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
         return "/school/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<School> search()
    {
          List<School> data = _SchoolRepository.findAll();
          List<School> newdata=new ArrayList<School>();
          for(int i=0;i<data.size();i++){
        	  School school=new School();
        	  school.setId(data.get(i).getId());
        	  school.setScName(data.get(i).getScName());
        	  school.setCreateTime(data.get(i).getCreateTime());
        	  school.setMember(data.get(i).getMember());
        	  school.setCitySchool(data.get(i).getCitySchool());
        	  newdata.add(school);
          }
        return newdata;
    }
	  //删除
	    @ResponseBody
	    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	    public void delete(@PathVariable int id)
	    {
	    	_SchoolClassRepository.deleteBySchoolId(id);
	        _SchoolRepository.delete(id);	
	        
	    }
	    //学校新增页面
	    @RequestMapping(value = "/create", method = RequestMethod.GET)
	    public String createInit(@ModelAttribute City cityactive, Model model)
	    {
	    	 List <Member> member = _MemberRepository.findAll();
		       model.addAttribute("member", member);
		       
		       List<CitySchool> cityschool = _CitySchoolRepository.findAll();
		       model.addAttribute("cityschool", cityschool);
	        return "/school/create";

	    }
	    
	    //学校新增实现

	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create( Model model, HttpServletRequest request)
	    {
	        
	        	School schools = new School();
	        	schools.setCreateTime(new Date());
	        	schools.setScName(request.getParameter("scName"));
	        	schools.setCitySchool(_CitySchoolRepository.findOne(Integer.parseInt(request.getParameter("cityschool"))));
	        	schools.setMember(_MemberRepository.findOne(Integer.parseInt(request.getParameter("member"))));
	        	_SchoolRepository.save(schools);
	      
			return "redirect:/school/list";

	    }
	    
	    
	    //进入修改页面 
		 @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
		    public String updateInit(@PathVariable int id, Model model)
		    {
		        School school = _SchoolRepository.findOne(id);
		        model.addAttribute("school", school);
		        List <Member> member = _MemberRepository.findAll();
			       model.addAttribute("member", member);
			       List<CitySchool> cityschool = _CitySchoolRepository.findAll();
			       model.addAttribute("cityschool", cityschool);
		        return "/school/update";
		    }
		 //修改班级信息
		 @RequestMapping(value = "/update", method = RequestMethod.POST)
		    public String update(@Valid School school, HttpServletRequest request) throws ParseException
		    {   
			 School schools = _SchoolRepository.findOne(school.getId());
			 schools.setScName(request.getParameter("scName"));
		     schools.setCreateTime(new Date());
		 		schools.setCitySchool(_CitySchoolRepository.findOne(Integer.parseInt(request.getParameter("cityschool"))));
		 	 schools.setMember(_MemberRepository.findOne(Integer.parseInt(request.getParameter("member"))));
		    _SchoolRepository.save(schools);
		        return "redirect:/school/list";
		    } 
		 
		 // 学校名称重复提交判断
		    @ResponseBody
		    @RequestMapping(value = "/valueistrue", method = RequestMethod.POST)
		    public boolean valueistrue(String scName,Integer cityschools)
		    {
		        long citys = _SchoolRepository.findByCityandName(scName,cityschools);
		       if (citys == 0 )
		        {
		            System.out.println("true");
		            return true;
		        }
		        else
		        {
		            System.out.println("false");
		            return false;
		        }
		    }

}
