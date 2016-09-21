package com.jzeen.travel.admin.controller.schoolclass;

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
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.City;
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
import com.jzeen.travel.data.entity.SchoolClass;
import com.jzeen.travel.data.entity.Student;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.UserMaterial;
import com.jzeen.travel.data.repository.CarouselImgRepository;
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
@RequestMapping("/schoolclass")
public class SchoolClassController {

	
	
	@Autowired  
	SchoolRepository  _SchoolRepository;
	@Autowired  
	SchoolClassRepository  _SchoolClassRepository;
	@Autowired  
	StudentRepository  _studentRepository;
	
	@Autowired
	MemberRepository _MemberRepository;
	//显示所有的文章列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
         return "/schoolclass/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<SchoolClass> search()
    {
          List<SchoolClass> data = _SchoolClassRepository.findAll();
          List<SchoolClass> newdata=new ArrayList<SchoolClass>();
          for(int i=0;i<data.size();i++){
        	  SchoolClass school=new SchoolClass();
        	  school.setId(data.get(i).getId());
        	  school.setName(data.get(i).getName());
        	  school.setCreateTime(data.get(i).getCreateTime());
        	  school.setSchool(data.get(i).getSchool());
        	  school.setMember(data.get(i).getMember());
        	  
        	  newdata.add(school);
          }
          System.out.println(newdata.get(0).getSchool());
        return newdata;
    }
	  //删除
	    @ResponseBody
	    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	    public void delete(@PathVariable int id)
	    {
	    	_studentRepository.deleteBySchoolClassId(id);
	        _SchoolClassRepository.deleteById(id);	     
	        
	    }
	    //学校新增页面
	    @RequestMapping(value = "/create", method = RequestMethod.GET)
	    public String createInit( Model model)
	    {
	    	List<School> school = _SchoolRepository.findAll();
			model.addAttribute("school", school);
			List<Member> member =_MemberRepository.findAll();
			model.addAttribute("member", member);
	        return "/schoolclass/create";

	    }
	    
	    //学校新增实现

	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create( Model model, HttpServletRequest request)
	    {
	        
	        	SchoolClass schoolclass = new SchoolClass();
	        	schoolclass.setCreateTime(new Date());
	        	schoolclass.setName(request.getParameter("classname"));
	        	schoolclass.setSchool(_SchoolRepository.findOne(Integer.parseInt(request.getParameter("school"))));
	        	schoolclass.setMember(_MemberRepository.findOne(Integer.parseInt(request.getParameter("member"))));
	        	_SchoolClassRepository.save(schoolclass);
	      
			return "redirect:/schoolclass/list";

	    }
	    
	    
	    //进入修改页面 
		 @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
		    public String updateInit(@PathVariable int id, Model model)
		    {
			 List<Member> member =_MemberRepository.findAll();
				model.addAttribute("member", member);
		        SchoolClass schoolclass= _SchoolClassRepository.findOne(id);
		        model.addAttribute("schoolclass", schoolclass);
		        model.addAttribute("findclassid", _SchoolRepository.findBySchoolClassId(id));
		       List< School> school = _SchoolRepository.findAll();
		        model.addAttribute("school", school);
		        return "/schoolclass/update";
		    }
		 //修改班级信息
		 @RequestMapping(value = "/update", method = RequestMethod.POST)
		    public String update(@Valid SchoolClass schoolclass, HttpServletRequest request) throws ParseException
		    {   
			 SchoolClass schoolclasss = _SchoolClassRepository.findOne(schoolclass.getId());
			 schoolclasss.setName(request.getParameter("name"));
			 schoolclasss.setCreateTime(new Date());
			 System.out.println(request.getParameter("school"));
			 schoolclasss.setSchool(_SchoolRepository.findOne(Integer.parseInt(request.getParameter("school"))));
			 schoolclass.setMember(_MemberRepository.findOne(Integer.parseInt(request.getParameter("member"))));
		    _SchoolClassRepository.save(schoolclasss);
		    return "redirect:/schoolclass/list";
		    } 
		 
		//根据家长Id查询孩子
			@ResponseBody
			@RequestMapping(value = "/findByClassId", method = RequestMethod.GET)
		    public List<SchoolClass> findByClassId(HttpServletRequest request, Model model)
		    {
				String id = request.getParameter("id");
				School school = _SchoolRepository.findOne(Integer.parseInt(id));
				List<SchoolClass> childList = school.getSchoolClass();
				List<SchoolClass>  list=new ArrayList<SchoolClass>();
				for(int i=0;i<childList.size();i++){
					SchoolClass c=new SchoolClass();
					c.setId(childList.get(i).getId());
					c.setName(childList.get(i).getName());
					c.setSchool(childList.get(i).getSchool());
					c.setCreateTime(childList.get(i).getCreateTime());
					list.add(c);
				}
				return list;
		    }
			
			
			//根据class查询孩子
			@ResponseBody
			@RequestMapping(value = "/findBychildId", method = RequestMethod.GET)
		    public List<Student> findBychildId(HttpServletRequest request, Model model)
		    {
				String id = request.getParameter("id");
				SchoolClass school = _SchoolClassRepository.findOne(Integer.parseInt(id));
				List<Student>  list=new ArrayList<Student>();
					List<Student> childList = school.getStudent();
						for(int i=0;i<childList.size();i++){
							Student c=new Student();
							c.setId(childList.get(i).getId());
							c.setCreateTime(childList.get(i).getCreateTime());
							c.setStudentName(childList.get(i).getStudentName());
							list.add(c);
						}
				
				return list;
		    }
			
			//根据class查询孩子
			@ResponseBody
			@RequestMapping(value = "/findByclasschildId", method = RequestMethod.GET)
		    public List<Student> findByclasschildId(HttpServletRequest request, Model model)
		    {
				String id = request.getParameter("id");
				School school = _SchoolRepository.findOne(Integer.parseInt(id));
				
				List<SchoolClass> schoolclass = _SchoolClassRepository.findBySchoolId(school.getId());
				List<Student>  list=new ArrayList<Student>();
				for(int j=0;j<schoolclass.size();j++){
					List<Student> childList = schoolclass.get(j).getStudent();
					for(int i=0;i<childList.size();i++){
						Student c=new Student();
						c.setId(childList.get(i).getId());
						c.setCreateTime(childList.get(i).getCreateTime());
						c.setStudentName(childList.get(i).getStudentName());
						list.add(c);
					}
				}
				return list;
		    }
			
			
			
			 // 学校名称重复提交判断
		    @ResponseBody
		    @RequestMapping(value = "/valueistrue", method = RequestMethod.POST)
		    public boolean valueistrue(String classname,Integer schools)
		    {
		        long citys = _SchoolClassRepository.findByClassandName(classname,schools);
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
