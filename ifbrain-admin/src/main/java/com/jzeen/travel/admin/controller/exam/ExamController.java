package com.jzeen.travel.admin.controller.exam;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.Exam;
import com.jzeen.travel.data.entity.ExamScores;
import com.jzeen.travel.data.entity.ExamSumScores;
import com.jzeen.travel.data.entity.ItemManagement;
import com.jzeen.travel.data.entity.ItemManagementQuestion;
import com.jzeen.travel.data.entity.QuestionType;
import com.jzeen.travel.data.entity.School;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.ExamRepository;
import com.jzeen.travel.data.repository.ExamScoresRepository;
import com.jzeen.travel.data.repository.ExamSumScoresRepository;
import com.jzeen.travel.data.repository.ItemManagementQuestionRepository;
import com.jzeen.travel.data.repository.ItemManagementRepository;
import com.jzeen.travel.data.repository.QuestionOptionImageRepository;
import com.jzeen.travel.data.repository.QuestionRepository;
import com.jzeen.travel.data.repository.QuestionTypeRepository;

//考试系统题库管理界面
@Controller
@RequestMapping("/exam")
public class ExamController {
	 @Autowired
	 private CourseReponsitory _courseRepository;
	 @Autowired
	 QuestionRepository  _questionRepository;
	 @Autowired
	 ItemManagementRepository _itemManagementRepository;
	 @Autowired
	 QuestionTypeRepository  _questionTypeRepository;
	 @Autowired
	 ExamRepository _examRepository;
	 @Autowired
	 QuestionOptionImageRepository  _questionOptionImageRepository;
		@Autowired
	    FileUploadSetting _fileUploadSetting;
		 @Autowired 
		 ItemManagementQuestionRepository   _itemManagementQuestionRepository;
		 
		 @Autowired 
		 ExamScoresRepository   _examScoresRepository;
		 
		 @Autowired 
		 ExamSumScoresRepository   _examSumScoresRepository;
	//显示所有的文章列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
         return "/exam/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Exam> search()
    {
		List<Exam> data = _examRepository.findAll();
		 List<Exam> newdata=new ArrayList<Exam>();
         for(int i=0;i<data.size();i++){
        	 Exam exam=new Exam();
        	 exam.setId(data.get(i).getId());
        	 //exam.setCourse(data.get(i).getCourse());
        	 exam.setCreateTime(data.get(i).getCreateTime());
        	 exam.setCreditsRequired(data.get(i).getCreditsRequired());
        	 exam.setExamName(data.get(i).getExamName());
        	 exam.setStatus(data.get(i).getStatus());
        	 exam.setTestdifficulty(data.get(i).getTestdifficulty());
        	 exam.setTotalAmount(data.get(i).getTotalAmount());
        	 exam.setExamTime(data.get(i).getExamTime());
        	 exam.setUnit(data.get(i).getUnit());
       	  newdata.add(exam);
         }
        return newdata;
    }
	 @RequestMapping(value = "/index", method = RequestMethod.GET)
	    public String loginInit(Model model, HttpServletRequest request)
	    {
		 List<Course> list = _courseRepository.findAll();
			model.addAttribute("list", list);
	        return "/exam/index";
	    }
	 //创建试题
	 @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(HttpServletRequest request, Model model) throws Exception, UnsupportedEncodingException, SQLException
	    {
		String CourseId = request.getParameter("Course");
		Course course = _courseRepository.findOne(Integer.parseInt(CourseId));
		String unit = request.getParameter("unit");
		String testdifficulty = request.getParameter("testdifficulty");
		String creditsRequired = request.getParameter("creditsRequired");
		String examName = request.getParameter("examName");
		String examTime = request.getParameter("examTime");
		String examDescription = request.getParameter("examDescription");
		/*String totalAmount = request.getParameter("totalAmount");*/
		Exam exam=new Exam();
		exam.setCourse(course);
		exam.setCreateTime(new Date());
		exam.setCreditsRequired(creditsRequired);
		exam.setExamDescription(examDescription);
		exam.setStatus("0");
		exam.setTestdifficulty(testdifficulty);
		exam.setUnit(unit);
		exam.setExamName(examName);
		exam.setExamTime(examTime);
		/*exam.setTotalAmount(new BigDecimal(totalAmount));*/
		_examRepository.save(exam);
			return "redirect:/exam/list";
	    }
	 //初始化修改页面
	  @RequestMapping(value = "/initupdate", method = RequestMethod.GET)
	    public String update(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	Exam exam = _examRepository.findOne(Integer.parseInt(id));
	    	List<Course> list = _courseRepository.findAll();
			model.addAttribute("list", list);
	    	model.addAttribute("exam", exam);
			return "/exam/updateexam";
	    }
	  //修改试题
	  @RequestMapping(value = "/examupdate", method = RequestMethod.POST)
	    public String questionupdate(HttpServletRequest request, Model model) throws Exception
	    {
		    String examid = request.getParameter("examid");
		    Exam exam = _examRepository.findOne(Integer.parseInt(examid));
		    String CourseId = request.getParameter("Course");
			Course course = _courseRepository.findOne(Integer.parseInt(CourseId));
		    String unit = request.getParameter("unit");
			String testdifficulty = request.getParameter("testdifficulty");
			String creditsRequired = request.getParameter("creditsRequired");
			String examName = request.getParameter("examName");
			String examTime = request.getParameter("examTime");
			String examDescription = request.getParameter("examDescription");
			/*String totalAmount = request.getParameter("totalAmount");*/
			exam.setCourse(course);
			exam.setCreateTime(new Date());
			exam.setCreditsRequired(creditsRequired);
			exam.setExamDescription(examDescription);
			exam.setStatus("0");
			exam.setTestdifficulty(testdifficulty);
			exam.setUnit(unit);
			exam.setExamName(examName);
			exam.setExamTime(examTime);
			/*exam.setTotalAmount(new BigDecimal(totalAmount));*/
			_examRepository.save(exam);
			return "redirect:/exam/list";
	    }
	  //试卷发布
	  @ResponseBody
	  @RequestMapping(value = "/examrelease", method = RequestMethod.GET)
	    public String examrelease(HttpServletRequest request, Model model) throws Exception
	    {
		  String data;
	    	String id = request.getParameter("id");
	    	Exam exam = _examRepository.findOne(Integer.parseInt(id));
	    	List <Exam> examlist =_examRepository.findByStatus("1");
	    	if(examlist.size()==0){
	    		exam.setStatus("1");
	    		_examRepository.save(exam);
	    		data ="发布成功！";
	    	}else{
	    		 data ="已经有发布过的内容了！";
	    	}
	    	return data;
	    	
	    }
	  //试卷撤销
	  @ResponseBody
	  @RequestMapping(value = "/revoke", method = RequestMethod.GET)
	    public void revoke(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	Exam exam = _examRepository.findOne(Integer.parseInt(id));
	    	exam.setStatus("2");
	    	_examRepository.save(exam);
	    }
	  
	  
	  
	  
	    //删除试卷
	    @Transactional
	    @ResponseBody
	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public String  delete(HttpServletRequest request)
	    {
	    	String id = request.getParameter("id");
	    	System.out.println(_examSumScoresRepository.findByExamId(Integer.parseInt(id))!=null);
	    	System.out.println(_examSumScoresRepository.findByExamId(Integer.parseInt(id)));
	    	if(_examSumScoresRepository.findByExamId(Integer.parseInt(id))!=null){
	    		_examSumScoresRepository.deleteByExamId(Integer.parseInt(id));
	    	}
	    	Exam exam = _examRepository.findOne(Integer.parseInt(id));
	    	_examRepository.delete(exam);
	    	
	    	
	    	String result="删除成功!";
	    	return result;
	    }
	    //跳转到添加题型页面
	    @RequestMapping(value = "/itemmanagement", method = RequestMethod.GET)
	    public String itemmanagement(HttpServletRequest request,Model model)
	    {
	    	String id = request.getParameter("id");
	    	String message = request.getParameter("message");
	    	if(message!=null){
	    		if(message=="1"||message.equals("1")){
		    		model.addAttribute("message", "已经存在相同的题型！");
		    	}else{
		    		model.addAttribute("message", "");
		    	}
	    	}
	    	
	    
	    	Exam exam = _examRepository.findOne(Integer.parseInt(id));
	    	List<QuestionType> list = _questionTypeRepository.findAll();
	    	List<ItemManagement> list2 = _itemManagementRepository.findByExamId(Integer.parseInt(id));
	    	model.addAttribute("list", list);
	    	model.addAttribute("list2", list2);
	    	model.addAttribute("examid", exam.getId());
	    	
	    	 List <ItemManagementQuestion> itemManagementQuestion = _itemManagementQuestionRepository.findByOrdernumberAsc(exam.getId());
			 if(itemManagementQuestion.size()>0){
				 model.addAttribute("itemManagementQuestion", itemManagementQuestion);
			 }else{
				 model.addAttribute("itemManagementQuestion", null);
			 }
	    	return "/exam/itemmanagement";
	    }
	 
}
