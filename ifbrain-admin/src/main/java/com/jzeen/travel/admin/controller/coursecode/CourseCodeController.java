package com.jzeen.travel.admin.controller.coursecode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseClass;
import com.jzeen.travel.data.entity.CourseCode;
import com.jzeen.travel.data.entity.Margin;
import com.jzeen.travel.data.repository.CourseClassReponsitory;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.IfbrainIndexReponsitory;

@Controller
@RequestMapping("/coursecode")
public class CourseCodeController {
	@Autowired
    private CourseClassReponsitory _cCourseClassReponsitory;
	@Autowired
    private CourseReponsitory _cCourseReponsitory;
	@Autowired
    private CourseCodeRepository _cCourseCodeRepository;
	@Autowired
	private IfbrainIndexReponsitory  _ifbrainIndexReponsitory;
		//班级信息列表
			@RequestMapping(value = "/list", method = RequestMethod.GET)
		    public String list(HttpServletRequest request, Model model)
		    {
				return "/coursecode/list";
		    }
		//datatable列表显示
			@ResponseBody
		    @RequestMapping(value = "/search", method = RequestMethod.GET)
		    public List<CourseCode> search()
		    {
		        List<CourseCode> data = _cCourseCodeRepository.findAll();
		        List<CourseCode> list =new ArrayList<CourseCode>(); 
		        for(int i=0;i<data.size();i++){
		        	CourseCode c=new CourseCode();
		        	c.setClassTime(data.get(i).getClassTime());
		        	c.setCourseDescription(data.get(i).getCourseDescription());
		        	c.setId(data.get(i).getId());
		        	c.setCourselevel(data.get(i).getCourseId().getCourseLevel());
		        	c.setOrdinalNumber(data.get(i).getOrdinalNumber());
		        	c.setLessonName(data.get(i).getLessonName());
		        	list.add(c);
		        }
		        return list;
		    }
		//新增班级信息页面		
		 @RequestMapping(value = "/create", method = RequestMethod.GET)
		    public String createInit(@ModelAttribute CourseClass courseclass, Model model,HttpServletRequest request)
		    {
		        List<Course> course = _cCourseReponsitory.findAll();
		        model.addAttribute("course", course);
		       
		        return "/coursecode/create";

		    }
			//新增班级信息实现
		 @RequestMapping(value = "/create", method = RequestMethod.POST)
		    public String create(@Valid CourseCode coursecode, BindingResult bindingResult, Model model, HttpServletRequest request) throws ParseException
		    {
			   CourseCode cosecode1=new CourseCode();
			   cosecode1.setCourseId(_cCourseReponsitory.findByCourseLevel(Integer.parseInt(request.getParameter("courseLevel"))));
			   SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			   cosecode1.setClassTime(sdf.parse(request.getParameter("classTime")));
			   cosecode1.setCourseDescription(request.getParameter("courseDescription"));
			   cosecode1.setLessonName(request.getParameter("lessonName"));
			   cosecode1.setOrdinalNumber(Integer.parseInt(request.getParameter("ordinalNumber")));
			   _cCourseCodeRepository.save(cosecode1);
				return "redirect:/coursecode/list";       
		    }
		 //进入修改页面 
		 @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
		    public String updateInit(@PathVariable int id, Model model)
		    {
		        CourseCode coursecode = _cCourseCodeRepository.findOne(id);
		        model.addAttribute("coursecode", coursecode);
		      List<Course> course = _cCourseReponsitory.findAll();
		        model.addAttribute("course", course);
		        return "/coursecode/update";
		    }
		 //修改班级信息
		 @RequestMapping(value = "/update", method = RequestMethod.POST)
		    public String update(@Valid CourseCode coursecode, BindingResult bindingResult, HttpServletRequest request) throws ParseException
		    {   
			 	coursecode.setCourseId(_cCourseReponsitory.findByCourseLevel(Integer.parseInt(request.getParameter("courseLevel"))));
			   SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
			   coursecode.setClassTime(sdf.parse(request.getParameter("classTime")));
			   coursecode.setCourseDescription(request.getParameter("courseDescription"));
			   coursecode.setLessonName(request.getParameter("lessonName"));
			   coursecode.setOrdinalNumber(Integer.parseInt(request.getParameter("ordinalNumber")));
			   coursecode.setCourseDescription(request.getParameter("courseDescription"));
			   _cCourseCodeRepository.save(coursecode);
		        return "redirect:/coursecode/list";
		    }
		 //查找是否添加重复数据
			@ResponseBody
			@RequestMapping(value = "/findcheckBycoursecode", method = RequestMethod.GET)
		    public boolean findcheckByClassId(HttpServletRequest request, Model model)
		    {
				
				String courseLevel = request.getParameter("courseLevel");
				String ordinalNumber = request.getParameter("amp;ordinalNumber");
				CourseCode coursecode =_cCourseCodeRepository.findByOrdinalNumberAndCourseId(ordinalNumber, Integer.parseInt(courseLevel));
				
				if(coursecode!=null){
					return false;
				}else{
				return true;
				}
		    }
}
