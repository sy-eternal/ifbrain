package com.jzeen.travel.admin.controller.student;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.core.util.CipherUtil;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.CommentReply;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseClass;
import com.jzeen.travel.data.entity.CourseCode;
import com.jzeen.travel.data.entity.ExamOrder;
import com.jzeen.travel.data.entity.ExamScores;
import com.jzeen.travel.data.entity.ExamSumScores;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Material;
import com.jzeen.travel.data.entity.MaterialComment;
import com.jzeen.travel.data.entity.MaterialImage;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.entity.Member;
import com.jzeen.travel.data.entity.Exam;
import com.jzeen.travel.data.entity.ItemManagement;
import com.jzeen.travel.data.entity.ItemManagementQuestion;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.Question;
import com.jzeen.travel.data.entity.School;
import com.jzeen.travel.data.entity.SchoolClass;
import com.jzeen.travel.data.entity.Student;
import com.jzeen.travel.data.entity.StudentExam;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.UserMaterial;
import com.jzeen.travel.data.repository.CarouselImgRepository;
import com.jzeen.travel.data.repository.CommentReplyRepository;
import com.jzeen.travel.data.repository.ExamOrderRepository;
import com.jzeen.travel.data.repository.ExamScoresRepository;
import com.jzeen.travel.data.repository.ExamSumScoresRepository;
import com.jzeen.travel.data.repository.MaterialCommentRepository;
import com.jzeen.travel.data.repository.MaterialImageRepository;
import com.jzeen.travel.data.repository.MaterialRepository;
import com.jzeen.travel.data.repository.MaterialTypeRepository;
import com.jzeen.travel.data.repository.MemberRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.ExamRepository;
import com.jzeen.travel.data.repository.ItemManagementQuestionRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.QuestionRepository;
import com.jzeen.travel.data.repository.SchoolClassRepository;
import com.jzeen.travel.data.repository.SchoolRepository;
import com.jzeen.travel.data.repository.StudentExamRepository;
import com.jzeen.travel.data.repository.StudentRepository;
import com.jzeen.travel.data.repository.UserRepository;


@Controller
@RequestMapping("/student")
public class StudentController {
	 @Autowired 
	 ExamSumScoresRepository   _examSumScoresRepository;
	@Autowired  
	QuestionRepository _questionRepository;
	
	@Autowired  
	SchoolRepository  _SchoolRepository;
	@Autowired  
	SchoolClassRepository  _SchoolClassRepository;
	 @Autowired
	 private CourseReponsitory _courseRepository;
	@Autowired  
	StudentRepository  _studentRepository;
	
	@Autowired  
	ExamSumScoresRepository  _ExamSumScoresRepository;
	
	@Autowired
	 ExamOrderRepository _ExamOrderRepository;
	@Autowired  
	UserRepository  _UserRepository;
	@Autowired  
	ItemManagementQuestionRepository _itemManagementQuestionRepository;
	@Autowired
	 ExamRepository _examRepository;
	@Autowired
	UserRepository _userRepository;
	@Autowired
	OrderRepository _orderRepository;
	@Autowired
	MemberRepository _MemberRepository;
	 @Autowired 
	 ExamScoresRepository   _examScoresRepository;
	 
	 @Autowired 
	 StudentExamRepository   _StudentExamRepository;
	//显示所有的文章列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
         return "/student/list";
    }
	
	//显示所有的学生列表
		@RequestMapping(value = "/studentlist", method = RequestMethod.GET)
	    public String studentlist(HttpServletRequest request, Model model)
	    {
	         return "/student/studentlist";
	    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<StudentExam> search()
    {
        /*  List<Student> data = _studentRepository.findAll();
          List<Student> newdata=new ArrayList<Student>();
          for(int i=0;i<data.size();i++){
        	  Student school=new Student();
        	  school.setId(data.get(i).getId());
        	  school.setSchoolClass(data.get(i).getSchoolClass());
        	  school.setStudentName(data.get(i).getStudentName());
        	  school.setCreateTime(data.get(i).getCreateTime());
        	 
        	  newdata.add(school);
          }*/
		 List<StudentExam> data = _StudentExamRepository.findAll();
		 System.out.println(data.size());
		 List<StudentExam> newdata=new ArrayList<StudentExam>();
		 for(int i=0;i<data.size();i++){
			 StudentExam studentexam = new StudentExam();
			 studentexam.setExam(data.get(i).getExam());
			 studentexam.setExamsumscore(data.get(i).getExamsumscore());
			 studentexam.setStudent(data.get(i).getStudent());
			 studentexam.setId(data.get(i).getId());
			 newdata.add(studentexam);
		 }
        return newdata;
    }
	
	//datatable列表显示
		@ResponseBody
	    @RequestMapping(value = "/searchlist", method = RequestMethod.GET)
	    public List<Student> searchlist()
	    {
	         List<Student> data = _studentRepository.findAll();
	          List<Student> newdata=new ArrayList<Student>();
	          for(int i=0;i<data.size();i++){
	        	  Student school=new Student();
	        	  school.setId(data.get(i).getId());
	        	  school.setSchoolClass(data.get(i).getSchoolClass());
	        	  school.setStudentName(data.get(i).getStudentName());
	        	  school.setCreateTime(data.get(i).getCreateTime());
	        	 
	        	  newdata.add(school);
	          }
		
	        return newdata;
	    }
	  //删除
	    @ResponseBody
	    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	    public void delete(@PathVariable int id)
	    {
	        _studentRepository.delete(id);	
	        
	    }
	    //学sheng新增页面
	    @RequestMapping(value = "/create", method = RequestMethod.GET)
	    public String createInit(@ModelAttribute City cityactive, Model model)
	    {
	       List <School> school =_SchoolRepository.findAll();
	       List<Exam> exam = _examRepository.findAll();
	       model.addAttribute("exam", exam);
	       model.addAttribute("school", school);
	       List<ItemManagementQuestion> itemManagementquestion = _itemManagementQuestionRepository.findByOrdernumberAsc(exam.get(0).getId());
	       model.addAttribute("itemManagementquestion",itemManagementquestion);
	       model.addAttribute("examid", exam.get(0).getId());
	       model.addAttribute("size", itemManagementquestion.size());
	        return "/student/create";
	    }
	    //查询试卷的试题
	    @RequestMapping(value = "/searchquestion", method = RequestMethod.GET)
	    public String searchquestion( Model model, HttpServletRequest request){
	    	   List<Exam> exam = _examRepository.findAll();
		       model.addAttribute("exam", exam);
		       String examid=request.getParameter("examid");
		       model.addAttribute("examid", examid);
		       List<ItemManagementQuestion> itemManagementquestion =_itemManagementQuestionRepository.findByOrdernumberAsc(Integer.parseInt(examid));
		       model.addAttribute("itemManagementquestion",itemManagementquestion);
		       model.addAttribute("size", itemManagementquestion.size());
		       List <School> school =_SchoolRepository.findAll();
		       model.addAttribute("school", school);
		       String student = request.getParameter("studentid");
		       if(student!=null){
		    	   Student students = _studentRepository.findOne(Integer.parseInt(student));
			        model.addAttribute("student", students);
		       }
		       return "/student/create";
	    }
	    
	    
	    
	    
	    //学校新增实现

	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create( Model model, HttpServletRequest request)
	    {
	    		Student students =_studentRepository.findOne(Integer.parseInt(request.getParameter("studentvalue")));
	    		System.out.println(Integer.parseInt(request.getParameter("studentvalue")));
	    		System.out.println(Integer.parseInt(request.getParameter("SchoolClass")));
	    		Student student =_studentRepository.findByStudentNameAndSchoolClassId(students.getStudentName(),Integer.parseInt(request.getParameter("SchoolClass")));
	        	System.out.println(request.getParameter("Student"));
	        	System.out.println(Integer.parseInt(request.getParameter("SchoolClass")));
	    		if(student!=null){
	    			
	        	   Date date = new Date();
		            SimpleDateFormat sif = new SimpleDateFormat("yyyyMMddHHmmss");
	        		ExamOrder order = new ExamOrder();
		        	order.setCreateTime(new Date());
		        	order.setOrderNumber("EXAM"+date.getTime());
		        	order.setOrderStatus(1);
		        	Date dates = student.getCreateTime();
		        	String format = sif.format(dates);
		        	User user =_userRepository.findByLastNameAndPassword(student.getStudentName() + format, CipherUtil.generatePassword("123456"));
		        	order.setUser(user);
		        	_ExamOrderRepository.save(order);
		        	
		        	String checkanswer = request.getParameter("checkanswer");
		        	String examid = request.getParameter("examid");
		        	Exam exam = _examRepository.findOne(Integer.parseInt(examid));
		        	
		        	List<Question> questionlist1 = _questionRepository.questionlist1(exam.getId());
		        	String[] split = checkanswer.split(",");
		        	int j=0;
		        	for(int i=0;i<split.length;i++){
		        		if(split[i].equals(questionlist1.get(i).getAnswer())){
		        			j++;
		        			  ExamScores examscore = new ExamScores();
		    		    	  examscore.setAnswer(split[i]);    	
		    			      examscore.setCreateTime(new Date());
		    			      examscore.setOrdinalnumber(i+1);
		    			      examscore.setUser(user);
		    			      ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.getId(),i+1);
		    			      examscore.setItemManagementQuestion(ItemManagementQuestions);
		    			      examscore.setAnswerStatus(1);
		    			      _examScoresRepository.save(examscore);
		        		}else{
		        			  ExamScores examscore = new ExamScores();
		    		    	  examscore.setAnswer(split[i]);    	
		    			      examscore.setCreateTime(new Date());
		    			      examscore.setOrdinalnumber(i+1);
		    			      examscore.setUser(user);
		    			      ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.getId(),i+1);
		    			      examscore.setItemManagementQuestion(ItemManagementQuestions);
		    			      examscore.setAnswerStatus(0);
		    			      _examScoresRepository.save(examscore);
		        		}
		        	}
		        	ExamSumScores examSumScores = new ExamSumScores();
		        	examSumScores.setUser(user);
		        	examSumScores.setCreateTime(new Date());
		        	examSumScores.setExam(exam);
		        	examSumScores.setSumScore(new BigDecimal(j).multiply(new BigDecimal(exam.getItemManagement().get(0).getPerscore())));
		        	_examSumScoresRepository.save(examSumScores);
		        	
		        	StudentExam studentExam = new StudentExam();
		        	studentExam.setExam(exam);
		        	studentExam.setExamsumscore(examSumScores);
		        	studentExam.setStudent(student);
		        	_StudentExamRepository.save(studentExam);
		        	
	        	}
	    		
			   return "redirect:/student/list";

	    }
	    
	    //查询试卷的试题修改
	    @RequestMapping(value = "/searchexamquestion", method = RequestMethod.GET)
	    public String searchexamquestion( Model model, HttpServletRequest request){
	    	   List<Exam> exam = _examRepository.findAll();
		       model.addAttribute("exam", exam);
		       String examid=request.getParameter("examid");
		       model.addAttribute("examid", examid);
		       List<ItemManagementQuestion> itemManagementquestion =_itemManagementQuestionRepository.findByOrdernumberAsc(Integer.parseInt(examid));
		       model.addAttribute("itemManagementquestion",itemManagementquestion);
		       model.addAttribute("size", itemManagementquestion.size());
		       List <School> school =_SchoolRepository.findAll();
		       model.addAttribute("school", school);
		       String student = request.getParameter("studentid");
		       Student students = _studentRepository.findOne(Integer.parseInt(student));
			   model.addAttribute("student", students);
		 
		       return "/student/update";
	    }
	    //进入修改页面 
		 @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
		    public String updateInit(@PathVariable int id, Model model)
		    {
		       List <School> school = _SchoolRepository.findAll();
		        model.addAttribute("school", school);
		        List <SchoolClass> schoolclass =_SchoolClassRepository.findAll();
		        model.addAttribute("schoolclass", schoolclass);
		       /* Student student = _studentRepository.findOne(id);
		        model.addAttribute("student", student);*/
		        StudentExam studentexam =_StudentExamRepository.findOne(id);
		        model.addAttribute("student", studentexam);
		        List<ItemManagementQuestion> itemManagementquestion = _itemManagementQuestionRepository.findByOrdernumberAsc(studentexam.getExam().getId());
			       model.addAttribute("itemManagementquestion",itemManagementquestion);
			       model.addAttribute("size", itemManagementquestion.size());
		        return "/student/update";
		    }
		 //修改班级信息
		 @RequestMapping(value = "/update", method = RequestMethod.POST)
		    public String update( HttpServletRequest request) throws ParseException
		    {   
			 System.out.println(request.getParameter("StudentExam"));
			 System.out.println(Integer.parseInt(request.getParameter("SchoolClass")));
			 	StudentExam studente =_StudentExamRepository.findOne(Integer.parseInt(request.getParameter("StudentExam")));
				Student studentss =_studentRepository.findOne(studente.getStudent().getId());
				System.out.println(Integer.parseInt(request.getParameter("SchoolClass")));
			 Student students =_studentRepository.findByStudentNameAndSchoolClassId(studentss.getStudentName(),Integer.parseInt(request.getParameter("SchoolClass")));
	        	if(students!=null){
		        SimpleDateFormat sif = new SimpleDateFormat("yyyyMMddHHmmss");
		        Date dates = studentss.getCreateTime();
		        String format = sif.format(dates);
	        	User user =_userRepository.findByLastNameAndPassword(studente.getStudent().getStudentName() + format, CipherUtil.generatePassword("123456"));
	        	String checkanswer = request.getParameter("checkanswer");
	        	String examid = request.getParameter("examid");
	        	Exam exam = _examRepository.findOne(Integer.parseInt(examid));
	        	List<Question> questionlist1 = _questionRepository.questionlist1(exam.getId());
	        	String[] split = checkanswer.split(",");
	        	int j=0;
	        	for(int i=0;i<split.length;i++){
	        		if(split[i].equals(questionlist1.get(i).getAnswer())){
	        			j++;
	        			 ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.getId(),i+1);
	        			  ExamScores examscore = _examScoresRepository.findByExamUserScores(ItemManagementQuestions.getId(), i+1,user.getId());
	    		    	  examscore.setAnswer(split[i]);    	
	    			      examscore.setCreateTime(new Date());
	    			      examscore.setOrdinalnumber(i+1);
	    			      examscore.setUser(user);
	    			    //  ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.getId(),i+1);
	    			      examscore.setItemManagementQuestion(ItemManagementQuestions);
	    			      examscore.setAnswerStatus(1);
	    			      _examScoresRepository.save(examscore);
	        		}else{
	        			 ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.getId(),i+1);
	        			  ExamScores examscore = _examScoresRepository.findByExamUserScores(ItemManagementQuestions.getId(), i+1,user.getId());
	    		    	  examscore.setAnswer(split[i]);    	
	    			      examscore.setCreateTime(new Date());
	    			      examscore.setOrdinalnumber(i+1);
	    			      examscore.setUser(user);
	    			    //  ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.getId(),i+1);
	    			      examscore.setItemManagementQuestion(ItemManagementQuestions);
	    			      examscore.setAnswerStatus(0);
	    			      _examScoresRepository.save(examscore);
	        		}
	        	}
	        	ExamSumScores examSumScores = _examSumScoresRepository.findByUserIdAndExamId(user.getId(), exam.getId());
	        	examSumScores.setUser(user);
	        	examSumScores.setCreateTime(new Date());
	        	examSumScores.setExam(exam);
	        	examSumScores.setSumScore(new BigDecimal(j).multiply(new BigDecimal(exam.getItemManagement().get(0).getPerscore())));
	        	_examSumScoresRepository.save(examSumScores);
	        	
	        	
	        	StudentExam studentExam =_StudentExamRepository.findByExamIdAndStudentId(exam.getId(),studente.getStudent().getId());
	        	studentExam.setExam(exam);
	        	studentExam.setExamsumscore(examSumScores);
	        	studentExam.setStudent(studente.getStudent());
	        	_StudentExamRepository.save(studentExam);
	        
	        	}
	        	return "redirect:/student/list";

	    
		    } 
		 
		 
		//显示所有的考试
			@RequestMapping(value = "/studentscore", method = RequestMethod.GET)
		    public String studentscore(HttpServletRequest request, Model model)
		    {

		         return "/student/studentscore";
		    }
			//datatable列表显示
			@ResponseBody
		    @RequestMapping(value = "/searchscore", method = RequestMethod.GET)
		    public List<StudentExam> searchscore(HttpServletRequest request, Model model)
		    {
				  Member member = (Member) request.getSession().getAttribute("member");
				List < School> school = _SchoolRepository.findByMemberId(member.getId());
				 List<StudentExam> examstudent=new ArrayList<StudentExam>();
				for(int x =0 ;x<school.size();x++){
					 List <StudentExam> data = _StudentExamRepository.findByStudentSchoolClassId(school.get(x).getId());
					 System.out.println(data.size());
					 for(int i=0;i<data.size();i++){
						 StudentExam studentexam = new StudentExam();
						 studentexam.setExam(data.get(i).getExam());
						 studentexam.setExamsumscore(data.get(i).getExamsumscore());
						 studentexam.setStudent(data.get(i).getStudent());
						 examstudent.add(studentexam);
					 }
					 /*for(int i =0;i<student.size();i++){
						 Date date = student.get(i).getCreateTime();
			             SimpleDateFormat sif = new SimpleDateFormat("yyyyMMddHHmmss");
			             String format = sif.format(date);
					 List<ExamSumScores> examSumScores   = _ExamSumScoresRepository.findByUserLastNameAndUserUserType(student.get(i).getStudentName()+format,0);
						if(examSumScores.size()>0){
							for(int j =0;j<examSumScores.size();j++){
								 Student exstudent = new Student();
								 String str = examSumScores.get(j).getUser().getLastName();
								 String name = str.substring(0,str.length()-14);
								 
								 Student data =_studentRepository.findByStudentName(name);
								 exstudent.setSchoolClass(data.getSchoolClass());
								 exstudent.setStudentName(name);
								 
								 examstudent.add(exstudent);
							}
						
					 }
				}*/
					
					
				 }
				 return examstudent;
		    }
			
			
			//显示所有的考试
			@RequestMapping(value = "/classscore", method = RequestMethod.GET)
		    public String classscore(HttpServletRequest request, Model model)
		    {

		         return "/student/classscore";
		    }
			//datatable列表显示
			@ResponseBody
		    @RequestMapping(value = "/searchclassscore", method = RequestMethod.GET)
		    public List<StudentExam> searchclassscore(HttpServletRequest request, Model model)
		    {
				  Member member = (Member) request.getSession().getAttribute("member");
				List <SchoolClass>  school = _SchoolClassRepository.findByMemberId(member.getId());
				 List<StudentExam> examstudent=new ArrayList<StudentExam>();
				for(int x =0 ;x<school.size();x++){
				 List <StudentExam> data = _StudentExamRepository.findByStudentClassId(school.get(x).getId());
				//	 List <StudentExam> data = _StudentExamRepository.findByStudentSchoolClassId(school.get(x).getId());
					 System.out.println(data.size());
					 for(int i=0;i<data.size();i++){
						 StudentExam studentexam = new StudentExam();
						 studentexam.setExam(data.get(i).getExam());
						 studentexam.setExamsumscore(data.get(i).getExamsumscore());
						 studentexam.setStudent(data.get(i).getStudent());
						 examstudent.add(studentexam);
					 }
					
		    }
				return examstudent;
			
			
		    }
			
			//学sheng信息新增页面
		    @RequestMapping(value = "/studentcreate", method = RequestMethod.GET)
		    public String studentcreate(Model model)
		    {
		       List <School> school =_SchoolRepository.findAll();
		       List<Exam> exam = _examRepository.findAll();
		       model.addAttribute("exam", exam);
		       model.addAttribute("school", school);
		
		        return "/student/studentcreate";
		    }
		    
		    
		    //学生信息新增实现

		    @RequestMapping(value = "/studentcreate", method = RequestMethod.POST)
		    public String studentcreate( Model model, HttpServletRequest request)
		    {
		        	Student student = new Student();
		        	student.setCreateTime(new Date());
		        	student.setSchoolClass(_SchoolClassRepository.findOne(Integer.parseInt(request.getParameter("SchoolClass"))));
		        	student.setStudentName(request.getParameter("studentname"));
		        	_studentRepository.save(student);
		        	User user=new User();
		        	String studentname = request.getParameter("studentname");
		        	user.setActiveStatus(1);
		        	user.setUserType(0);
		        	user.setCreateTime(new Date());
		        	 Date date = new Date();
		             SimpleDateFormat sif = new SimpleDateFormat("yyyyMMddHHmmss");
		             String format = sif.format(date);
		             // 订单号
		             user.setLastName(studentname + format );
		             user.setFirstName(studentname);
		             user.setPassword(CipherUtil.generatePassword("123456"));
		        	_userRepository.save(user);
				   return "redirect:/student/studentlist";

		    }
		    
		    
		  //学sheng信息新增页面
		    @RequestMapping(value = "/studentupdate/{id}", method = RequestMethod.GET)
		    public String studentupdate(@PathVariable int id, Model model)
		    {
		    	 List <School> school = _SchoolRepository.findAll();
			        model.addAttribute("school", school);
			        List <SchoolClass> schoolclass =_SchoolClassRepository.findAll();
			        model.addAttribute("schoolclass", schoolclass);
			        Student student = _studentRepository.findOne(id);
			        model.addAttribute("student", student);
			        List<Exam> exam = _examRepository.findAll();
				       model.addAttribute("exam", exam);
			        List<ItemManagementQuestion> itemManagementquestion = _itemManagementQuestionRepository.findByOrdernumberAsc(exam.get(0).getId());
				       model.addAttribute("itemManagementquestion",itemManagementquestion);
				       model.addAttribute("size", itemManagementquestion.size());
		        return "/student/studentupdate";
		    }
		    
		    
		    //学生信息新增实现

		    @RequestMapping(value = "/studentupdate", method = RequestMethod.POST)
		    public String studentupdate(@Valid Student student, Model model, HttpServletRequest request)
		    {
		    	  
			 	Student students=_studentRepository.findOne(student.getId());
	        /*	students.setCreateTime(new Date());*/
	        	students.setSchoolClass(_SchoolClassRepository.findOne(Integer.parseInt(request.getParameter("SchoolClass"))));
	        	students.setStudentName(request.getParameter("studentName"));
	        	_studentRepository.save(students);
	        	String studentname = request.getParameter("studentName");
	        	 Date date = students.getCreateTime();
	             SimpleDateFormat sif = new SimpleDateFormat("yyyyMMddHHmmss");
	             String format = sif.format(date);
	        	//根据lastname和password获取user
	             User user=_userRepository.findByLastNameAndPassword(students.getStudentName() + format, CipherUtil.generatePassword("123456"));
	        	user.setActiveStatus(1);
	        	user.setUserType(0);
	        /*	user.setCreateTime(new Date());*/
	             // 订单号
	             user.setLastName(studentname + format );
	             user.setFirstName(studentname);
	             user.setPassword(CipherUtil.generatePassword("123456"));
	        	_userRepository.save(user);
				   return "redirect:/student/studentlist";

		    }

}
