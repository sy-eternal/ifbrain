package com.jzeen.travel.website.controller.itemmanagement;






import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.Exam;
import com.jzeen.travel.data.entity.ExamOrder;
import com.jzeen.travel.data.entity.ExamScores;
import com.jzeen.travel.data.entity.ExamSumScores;
import com.jzeen.travel.data.entity.IfbrainTask;
import com.jzeen.travel.data.entity.ItemManagementQuestion;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.ExamOrderRepository;
import com.jzeen.travel.data.repository.ExamRepository;
import com.jzeen.travel.data.repository.ExamScoresRepository;
import com.jzeen.travel.data.repository.ExamSumScoresRepository;
import com.jzeen.travel.data.repository.ItemManagementQuestionRepository;
import com.jzeen.travel.data.repository.ItemManagementRepository;
import com.jzeen.travel.data.repository.QuestionRepository;
import com.jzeen.travel.data.repository.QuestionTypeRepository;





@Controller
@RequestMapping("/itemmanagement")
public class ItemManagementController
{
	    @Autowired
	    private ChildRepository _cChildRepository;
	    @Autowired
		 private QuestionTypeRepository _questionTypeRepository;
		 @Autowired
		 QuestionRepository  _questionRepository;
		 @Autowired
		 ItemManagementRepository _itemManagementRepository;
		 @Autowired
		 ExamRepository  _examRepository;
		 @Autowired 
		 ItemManagementQuestionRepository   _itemManagementQuestionRepository;
		 
		 @Autowired 
		 ExamScoresRepository   _examScoresRepository;
		 
		 @Autowired 
		 ExamSumScoresRepository   _examSumScoresRepository;
		 
		 @Autowired
		 ExamOrderRepository  _examOrderRepository;
		 
		
 //考试主页
   @RequestMapping(value = "/index",method = RequestMethod.GET)
   public String index()
   {
       return "/itemmanagement/index";
   }
  //前往考试页面 
   @RequestMapping(value = "/toexamination",method = RequestMethod.GET)
   public String toexamination(Model model, HttpServletRequest request,HttpSession session)
   {
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlist =_cChildRepository.findByUser(user);
	   List<Exam> exam =_examRepository.findByStatus("1");
	  
		   if(childlist.size()>0){
		
			
			   model.addAttribute("childs", childlist);
			   if(request.getParameter("childId")==null){
					ExamOrder examorder =  _examOrderRepository.findByChildIdAndOrderStatus(childlist.get(0).getId(),1);
					if(examorder!=null){
						model.addAttribute("examorder", examorder.getOrderStatus());
					}else{
						model.addAttribute("examorder","");
					}
				   model.addAttribute("flagvalue", childlist.get(0).getId());
				  
				  if(exam.size()>0){
					  model.addAttribute("examcourse", exam.get(0).getCourse().getCourseLevel());
					   ExamSumScores findexamSumScores  = _examSumScoresRepository.findByChildIdAndExamId(childlist.get(0).getId(), exam.get(0).getId());
					   if(findexamSumScores!=null){
						   //已经考试完毕
						   model.addAttribute("message","1" );
					   }
				  }else{
					  model.addAttribute("examcourse", "0");
					   model.addAttribute("message","1" );
				  }
				 
			   }else{
				   
				//   List<Exam> exam =_examRepository.findByStatus("1");
				   if(exam.size()>0){
					   ExamSumScores findexamSumScores  = _examSumScoresRepository.findByChildIdAndExamId(Integer.parseInt(request.getParameter("childId")), exam.get(0).getId());
					   model.addAttribute("examcourse", exam.get(0).getCourse().getCourseLevel());
					   if(findexamSumScores!=null){
						   //已经考试完毕
						   model.addAttribute("message","1" );
					   }
					   ExamOrder examorder =  _examOrderRepository.findByChildIdAndOrderStatus(Integer.parseInt(request.getParameter("childId")),1);
						if(examorder!=null){
							model.addAttribute("examorder", examorder.getOrderStatus());
						}else{
							model.addAttribute("examorder","");
						}
				   }else{
					   model.addAttribute("examcourse", "0");
					   model.addAttribute("message","1" );
				   }
				 
				  
				   model.addAttribute("flagvalue", request.getParameter("childId"));
			   }
		  
			   if(exam.size()>0){
		   model.addAttribute("exam", exam);
		   model.addAttribute("examid", exam.get(0).getId());
		   //价格
		  /* model.addAttribute("examTotalAmount", exam.get(0).getTotalAmount());*/
			   }else{
				   model.addAttribute("exam", null);
			   }
			   if(exam.size()>0){
		 List < ItemManagementQuestion> ItemManagementQuestion= _itemManagementQuestionRepository.findByOrdernumberAsc(exam.get(0).getId());
	      model.addAttribute("ItemManagementQuestion", ItemManagementQuestion);
	      model.addAttribute("ItemManagementsize", ItemManagementQuestion.size());
			  }
		 
	   }else{
		   model.addAttribute("examcourse", "0");
		   model.addAttribute("message","1" );
		   model.addAttribute("exam", null);
	   }
	   
    
	 return "/itemmanagement/toexamination";
   }
   //开始考试页面 
   @RequestMapping(value = "/beginexamination",method = RequestMethod.GET)
   public String beginexamination(Model model, HttpServletRequest request,HttpSession session)
   {
	   Integer childId=Integer.parseInt(request.getParameter("childId"));
	   Integer ordernumber = Integer.parseInt(request.getParameter("ordernumber"));
	   Child child = _cChildRepository.findById(childId);
	   model.addAttribute("child", child);
	   List<Exam> exam =_examRepository.findByStatus("1");
	   if(exam.size()>0){
		   List<ItemManagementQuestion> temManagementQuestion = _itemManagementQuestionRepository.findByexamStatues("1");
		   model.addAttribute("questionsize", temManagementQuestion.size());
		   model.addAttribute("temManagementQuestion", temManagementQuestion);
			 
			 model.addAttribute("exam", exam);
		   model.addAttribute("ordernumber", ordernumber);
			ItemManagementQuestion ItemManagementQuestion=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber);
			  ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber);
			ExamScores examscores = _examScoresRepository.findByExamScores(ItemManagementQuestions.getId(), ordernumber, childId);
			if(examscores==null){
				 model.addAttribute("answers", "");
			}else{
				 model.addAttribute("answers",examscores.getAnswer());
				 System.out.println("examscores.getAnswer()"+examscores.getAnswer());
			}
			 model.addAttribute("examcourse", exam.get(0).getCourse().getCourseLevel());
		      model.addAttribute("itemManagementQuestion", ItemManagementQuestion);
	   }else{
		   model.addAttribute("exam", null);
	   }
	 
	   return "/itemmanagement/beginexamination";
   }
   //下一题
   @RequestMapping(value = "/beginnextexamination",method = RequestMethod.GET)
   public String beginnextexamination(Model model, HttpServletRequest request,HttpSession session)
   {
	   Integer childId=Integer.parseInt(request.getParameter("childId"));
	   Integer ordernumber = Integer.parseInt(request.getParameter("ordernumber"));
	   String answer = request.getParameter("answer");
	   Integer questionsize =Integer.parseInt(request.getParameter("questionsize"));
	   Child child = _cChildRepository.findById(childId);
	   model.addAttribute("child", child);
	  List<ItemManagementQuestion> temManagementQuestion = _itemManagementQuestionRepository.findByexamStatues("1");
	   model.addAttribute("questionsize", temManagementQuestion.size());
	   model.addAttribute("temManagementQuestion", temManagementQuestion);
		List<Exam> exam =_examRepository.findByStatus("1");
		model.addAttribute("exam", exam);
	   model.addAttribute("ordernumber", ordernumber);
		ItemManagementQuestion ItemManagementQuestion=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber);
		if(ItemManagementQuestion==null){
			ItemManagementQuestion ItemManagementQuestionsel=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber-1);
			model.addAttribute("itemManagementQuestion", ItemManagementQuestionsel);
			}else{
				 model.addAttribute("itemManagementQuestion", ItemManagementQuestion);
			}
		 model.addAttribute("examcourse", exam.get(0).getCourse().getCourseLevel());
	      
	      
		ExamSumScores findexamSumScores  = _examSumScoresRepository.findByChildIdAndExamId(childId, exam.get(0).getId());
		   if(findexamSumScores==null){      
	      
	      //保存到成绩表
	      //判断是否存在
			   if(ordernumber-1<=questionsize){
				   if(ordernumber-1==questionsize){
					   ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber-1);
					      ExamScores examscores = _examScoresRepository.findByExamScores(ItemManagementQuestions.getId(), ordernumber-1, childId);
					      if(examscores==null){
					    	  ExamScores examscore = new ExamScores();
					    	  if(answer==""){
					    		  examscore.setAnswer("");
					    		  model.addAttribute("answers", "");
					    	  }else
					    	  {
					    		  examscore.setAnswer(answer);
					    		  model.addAttribute("answers",answer );
					    	  }
					    	 
						      examscore.setChild(child);
						      examscore.setCreateTime(new Date());
						      examscore.setOrdinalnumber(ordernumber-1);
						      examscore.setItemManagementQuestion(ItemManagementQuestions);
						      
						      if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
						    	  examscore.setAnswerStatus(1);
						      }else{
						    	  examscore.setAnswerStatus(0);
						      }
						      _examScoresRepository.save(examscore);
					      }else{
					    	  ExamScores examscoreupdate =_examScoresRepository.findOne(examscores.getId());
					    	 
					    	  if(answer==""){
					    		  examscoreupdate.setAnswer("");
					    	  }else
					    	  {
					    		  examscoreupdate.setAnswer(answer);
					    	  }
					    	  if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
					    		  examscoreupdate.setAnswerStatus(1);
						      }else{
						    	  examscoreupdate.setAnswerStatus(0);
						      }
					    	
					    	  model.addAttribute("answers",examscores.getAnswer());
					    
						      _examScoresRepository.save(examscoreupdate);
					      }
				   }else{

					   ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber-1);
					      ExamScores examscores = _examScoresRepository.findByExamScores(ItemManagementQuestions.getId(), ordernumber-1, childId);
					      if(examscores==null){
					    	  ExamScores examscore = new ExamScores();
					    	  if(answer==""){
					    		  examscore.setAnswer("");
					    	  }else
					    	  {
					    		  examscore.setAnswer(answer);
					    	  }
					    	  model.addAttribute("answers", "");
						      examscore.setChild(child);
						      examscore.setCreateTime(new Date());
						      examscore.setOrdinalnumber(ordernumber-1);
						     examscore.setItemManagementQuestion(ItemManagementQuestions);
						      
						      if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
						    	  examscore.setAnswerStatus(1);
						      }else{
						    	  examscore.setAnswerStatus(0);
						      }
						      _examScoresRepository.save(examscore);
					      }else{
					    	  ExamScores examscoreupdate =_examScoresRepository.findOne(examscores.getId());
					    	 
					    	  if(answer==""){
					    		  examscoreupdate.setAnswer("");
					    	  }else
					    	  {
					    		  examscoreupdate.setAnswer(answer);
					    	  }
					    	  if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
					    		  examscoreupdate.setAnswerStatus(1);
						      }else{
						    	  examscoreupdate.setAnswerStatus(0);
						      }
					    	  ItemManagementQuestion answereItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber);
					    	  ExamScores answerexamscores = _examScoresRepository.findByExamScores(answereItemManagementQuestions.getId(), ordernumber, childId);
					    	  if(answerexamscores==null){
					    		  model.addAttribute("answers","");
					    	  }else{
					    	  model.addAttribute("answers",answerexamscores.getAnswer());
					    	  }
						      _examScoresRepository.save(examscoreupdate);
					      }
				   
				   }
				  
			   }
	     
	     
   }
	     
	   return "/itemmanagement/beginexamination";
   }
   //上一题
   @RequestMapping(value = "/beginprevexamination",method = RequestMethod.GET)
   public String beginprevexamination(Model model, HttpServletRequest request,HttpSession session)
   {
	   Integer childId=Integer.parseInt(request.getParameter("childId"));
	   Integer ordernumber = Integer.parseInt(request.getParameter("ordernumber"));
	   Integer questionsize =Integer.parseInt(request.getParameter("questionsize"));
	   Child child = _cChildRepository.findById(childId);
	   String answer = request.getParameter("answer");
	   model.addAttribute("child", child);
	   List<ItemManagementQuestion> temManagementQuestion = _itemManagementQuestionRepository.findByexamStatues("1");
	   model.addAttribute("questionsize", temManagementQuestion.size());
	   model.addAttribute("temManagementQuestion", temManagementQuestion);
	  
		   List<Exam> exam =_examRepository.findByStatus("1");
		   model.addAttribute("exam", exam);
	   model.addAttribute("ordernumber", ordernumber);
	
		   ItemManagementQuestion ItemManagementQuestion=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber);
			if(ItemManagementQuestion==null){
				ItemManagementQuestion ItemManagementQuestionsel=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber+1);
				model.addAttribute("itemManagementQuestion", ItemManagementQuestionsel);
				}else{
					 model.addAttribute("itemManagementQuestion", ItemManagementQuestion);
				}      
			 model.addAttribute("examcourse", exam.get(0).getCourse().getCourseLevel());
		ExamSumScores findexamSumScores  = _examSumScoresRepository.findByChildIdAndExamId(childId, exam.get(0).getId());
		   if(findexamSumScores==null){    
			   if(ordernumber<=questionsize){
				   if(ordernumber==questionsize){
					   //保存到成绩表
					      ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber);
					      ExamScores examscores = _examScoresRepository.findByExamScores(ItemManagementQuestions.getId(), ordernumber, childId);
					      if(examscores==null){
					    	  ExamScores examscore = new ExamScores();
					    	  if(answer==""){
					    		  examscore.setAnswer("");
					    	  }else{
					    		  examscore.setAnswer(answer);
					    	  }
					    	  model.addAttribute("answers","");
						      examscore.setChild(child);
						      examscore.setCreateTime(new Date());
						      examscore.setOrdinalnumber(ordernumber);
						      examscore.setItemManagementQuestion(ItemManagementQuestions);
						     
						      if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
						    	  examscore.setAnswerStatus(1);
						      }else{
						    	  examscore.setAnswerStatus(0);
						      }
						      _examScoresRepository.save(examscore);
					      }else{
					    	  ExamScores examscoreupdate =_examScoresRepository.findOne(examscores.getId());
					    	  ItemManagementQuestion answereItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber);
					    	  ExamScores answerexamscores = _examScoresRepository.findByExamScores(answereItemManagementQuestions.getId(), ordernumber, childId);
					    	  model.addAttribute("answers",answerexamscores.getAnswer());
					    	  
					    	  if(answer==""){
					    		  examscoreupdate.setAnswer("");
					    	  }else{
					    		  examscoreupdate.setAnswer(answer);
					    	  }
					    	  if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
					    		  examscoreupdate.setAnswerStatus(1);
						      }else{
						    	  examscoreupdate.setAnswerStatus(0);
						      }
					    
						      _examScoresRepository.save(examscoreupdate);
					      }
						   }else{
							   //保存到成绩表
							      ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber+1);
							      ExamScores examscores = _examScoresRepository.findByExamScores(ItemManagementQuestions.getId(), ordernumber+1, childId);
							      if(examscores==null){
							    	  ExamScores examscore = new ExamScores();
							    	  if(answer==""){
							    		  examscore.setAnswer("");
							    	  }else{
							    		  examscore.setAnswer(answer);
							    	  }
							    	  model.addAttribute("answers","");
								      examscore.setChild(child);
								      examscore.setCreateTime(new Date());
								      examscore.setOrdinalnumber(ordernumber+1);
								      examscore.setItemManagementQuestion(ItemManagementQuestions);
								     
								      if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
								    	  examscore.setAnswerStatus(1);
								      }else{
								    	  examscore.setAnswerStatus(0);
								      }
								      _examScoresRepository.save(examscore);
							      }else{
							    	  ExamScores examscoreupdate =_examScoresRepository.findOne(examscores.getId());
							    	  ItemManagementQuestion answereItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber);
							    	  ExamScores answerexamscores = _examScoresRepository.findByExamScores(answereItemManagementQuestions.getId(), ordernumber, childId);
							    	if(answerexamscores==null){
							    		  model.addAttribute("answers","");
							    	}else{
							    		model.addAttribute("answers",answerexamscores.getAnswer());
							    	}
							    	
							    	  if(answer==""){
							    		  examscoreupdate.setAnswer("");
							    	  }else{
							    		  examscoreupdate.setAnswer(answer);
							    	  }
							    	  if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
							    		  examscoreupdate.setAnswerStatus(1);
								      }else{
								    	  examscoreupdate.setAnswerStatus(0);
								      }
							    
								      _examScoresRepository.save(examscoreupdate);
							      }
						   }
				     
				      
					   
				   }
		   }
	   return "/itemmanagement/beginexamination";
   }
   
   //点击面板
   //开始考试页面 
   @RequestMapping(value = "/clickbeginexamination",method = RequestMethod.GET)
   public String clickbeginexamination(Model model, HttpServletRequest request,HttpSession session)
   {
	   Integer childId=Integer.parseInt(request.getParameter("childId"));
	   Integer ordernumber = Integer.parseInt(request.getParameter("ordernumber"));
	   Child child = _cChildRepository.findById(childId);
	   String answer = request.getParameter("answer");
	   Integer questionsize =Integer.parseInt(request.getParameter("questionsize"));
	   model.addAttribute("child", child);
	  List<ItemManagementQuestion> temManagementQuestion = _itemManagementQuestionRepository.findByexamStatues("1");
	   model.addAttribute("questionsize", temManagementQuestion.size());
	   model.addAttribute("temManagementQuestion", temManagementQuestion);
	  
		   List<Exam> exam =_examRepository.findByStatus("1");
		   model.addAttribute("exam", exam);
	   model.addAttribute("ordernumber", ordernumber);
		ItemManagementQuestion ItemManagementQuestion=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber);
		if(ItemManagementQuestion==null){
			ItemManagementQuestion ItemManagementQuestionsel=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber);
			model.addAttribute("itemManagementQuestion", ItemManagementQuestionsel);
			}else{
				 model.addAttribute("itemManagementQuestion", ItemManagementQuestion);
			}
		  if(ordernumber<=questionsize){
	      
	      //保存到成绩表
	      ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),ordernumber);
	      ExamScores examscores = _examScoresRepository.findByExamScores(ItemManagementQuestions.getId(), ordernumber, childId);
	      if(examscores==null){
	    	  ExamScores examscore = new ExamScores();
	    	  if(answer==""){
	    		  examscore.setAnswer("");
	    	  }else{
	    		  examscore.setAnswer(answer);
	    	  }
	    	  model.addAttribute("answers","");
		      examscore.setChild(child);
		      examscore.setCreateTime(new Date());
		      examscore.setOrdinalnumber(ordernumber);
		      examscore.setItemManagementQuestion(ItemManagementQuestions);
		     
		      if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
		    	  examscore.setAnswerStatus(1);
		      }else{
		    	  examscore.setAnswerStatus(0);
		      }
		      _examScoresRepository.save(examscore);
	      }else{
	    	  ExamScores examscoreupdate =_examScoresRepository.findOne(examscores.getId());
	    	  model.addAttribute("answers",examscores.getAnswer());
	    	  if(answer==""){
	    		  examscoreupdate.setAnswer("");
	    	  }else{
	    		  examscoreupdate.setAnswer(answer);
	    	  }
	    	  examscoreupdate.setOrdinalnumber(ordernumber);
		      _examScoresRepository.save(examscoreupdate);
	      }
		  }
	   return "/itemmanagement/beginexamination";
   }
   

   
   //提交考卷
   @RequestMapping(value = "/submiteexam",method = RequestMethod.GET)
   public String submiteexam(Model model, HttpServletRequest request,HttpSession session)
   {
	   Integer childId =Integer.parseInt(request.getParameter("childId"));
	   Integer perscore =Integer.parseInt(request.getParameter("perscore"));
	
	   List<Exam> exam =_examRepository.findByStatus("1");
	   ExamSumScores findexamSumScores  = _examSumScoresRepository.findByChildIdAndExamId(childId, exam.get(0).getId());
	   if(findexamSumScores==null){
		   ExamSumScores examSumScores = new ExamSumScores();
		   examSumScores.setChild(_cChildRepository.findOne(childId));
		   examSumScores.setCreateTime(new Date());
		   List<ExamScores> examchildsumscores =  _examScoresRepository.findByChildExamSum(childId, perscore);
		  if(examchildsumscores.size()==0){
			  examSumScores.setSumScore(new BigDecimal(0));
			  List<ItemManagementQuestion> temManagementQuestion = _itemManagementQuestionRepository.findByexamStatues("1");
			  for(int i=0;i<temManagementQuestion.size();i++){
				  ExamScores examscore = new ExamScores();
		    	  examscore.setAnswer("");    	
		    	  model.addAttribute("answers","");
			      examscore.setChild(_cChildRepository.findById(childId));
			      examscore.setCreateTime(new Date());
			      examscore.setOrdinalnumber(i+1);
			      ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),i+1);
			      examscore.setItemManagementQuestion(ItemManagementQuestions);
			      examscore.setAnswerStatus(0);
			      _examScoresRepository.save(examscore); 
			  }
			 
		  }else{
			   List<ExamScores> examscores =  _examScoresRepository.findByChildExamSumScores(childId, perscore, 1);
			   if(examscores.size()>0){
				   examSumScores.setSumScore(new BigDecimal(examscores.get(0).getItemManagementQuestion().getItem().getPerscore()).multiply(new BigDecimal(examscores.size())));
			   }else{
				   examSumScores.setSumScore(new BigDecimal(0));
			   }
		  
		  }
		   examSumScores.setExam(exam.get(0));
		   _examSumScoresRepository.save(examSumScores);
	   }
	   model.addAttribute("message","1" );
	 return "redirect:/itemmanagement/toexamination";
   }
   
   
   
   
   
 /*//考试主页
   @RequestMapping(value = "/myscore",method = RequestMethod.GET)
   public String myscore(Model model, HttpServletRequest request,HttpSession session) throws ParseException
   {
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlist =_cChildRepository.findByUser(user);
	   if(childlist.size()>0){
		   model.addAttribute("childs", childlist);
		   if(request.getParameter("childId")==null){
			   model.addAttribute("flagvalue", request.getParameter("childId"));
			   List<Exam> exam =_examRepository.findByStatus("1");
			   if(exam.size()>0){
				   model.addAttribute("exam", exam); 
				   ExamSumScores examSumScores;
				   if(user.getUserType()==1){
					   examSumScores =   _examSumScoresRepository.findByChildIdAndExamId(childlist.get(0).getId(), exam.get(0).getId());
				   }else{
					   examSumScores =   _examSumScoresRepository.findByUserIdAndExamId(user.getId(), exam.get(0).getId());
				   }
				   model.addAttribute("examcourse", exam.get(0).getCourse().getCourseLevel());
				 
				   if(examSumScores!=null){
					   SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
						Date beginDate = examSumScores.getCreateTime();
						Calendar date = Calendar.getInstance();
						date.setTime(beginDate);
						date.set(Calendar.DATE, date.get(Calendar.DATE) + 3);
						Date endDate = dft.parse(dft.format(date.getTime()));
						Date nowdate = new Date();
						int days =(int)((nowdate.getTime() - beginDate.getTime())/86400000);
					   System.out.println(days);
						if(days>=3){
							 model.addAttribute("examSumScores", examSumScores);
							BigDecimal myscore = examSumScores.getSumScore();
							BigDecimal creditsRequired =new BigDecimal(exam.get(0).getCreditsRequired()) ;
							BigDecimal result =myscore.subtract(creditsRequired);
							int r =result.compareTo(new BigDecimal(0));
							if(r==1 || r==0){
								//考试分数大于等于要求学分
								model.addAttribute("result", "1");
							}else{
								model.addAttribute("result", "0");
							}
							
						}else{
							 
						        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
						          
						        String formatDate = df.format(endDate);  
						      
							 model.addAttribute("examSumScores", "");
							 model.addAttribute("endDate", formatDate);
						}
					   
					  
					   } else{
						   model.addAttribute("examSumScores", null);
					   }
			   }else{
				   model.addAttribute("exam",null);
			   }
			
			 
			   
			  
			  
			 
		   }else{
			   model.addAttribute("flagvalue", request.getParameter("childId"));
			   List<Exam> exam =_examRepository.findByStatus("1");
			   if(exam.size()>0){
				   model.addAttribute("exam", exam);
				   model.addAttribute("examcourse", exam.get(0).getCourse().getCourseLevel());
				   ExamSumScores examSumScores =   _examSumScoresRepository.findByChildIdAndExamId(Integer.parseInt(request.getParameter("childId")), exam.get(0).getId());
				   ExamSumScores examSumScores;
				   if(user.getUserType()==1){
					   examSumScores =   _examSumScoresRepository.findByChildIdAndExamId(childlist.get(0).getId(), exam.get(0).getId());
				   }else{
					   examSumScores =   _examSumScoresRepository.findByUserIdAndExamId(user.getId(), exam.get(0).getId());
				   }
				   if(examSumScores!=null){
					   SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
						Date beginDate = examSumScores.getCreateTime();
						Calendar date = Calendar.getInstance();
						date.setTime(beginDate);
						date.set(Calendar.DATE, date.get(Calendar.DATE) + 3);
						Date endDate = dft.parse(dft.format(date.getTime()));
						Date nowdate = new Date();
					   int days =(int)((nowdate.getTime() - beginDate.getTime())/86400000);
					   System.out.println("days+++++++++++"+days);
					   
						
							 model.addAttribute("examSumScores", examSumScores);
						
				   }else{
					   model.addAttribute("examSumScores", null);
				   }
			   }else{
				   model.addAttribute("exam", null);
			   }
			
			   model.addAttribute("examSumScores", examSumScores);
			  
			   
		   }
	   }
	   return "/itemmanagement/myscore";
   }*/
   
   
 //考试主页
   @RequestMapping(value = "/myscore",method = RequestMethod.GET)
   public String myscore(Model model, HttpServletRequest request,HttpSession session) throws ParseException
   {
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlist =_cChildRepository.findByUser(user);
	   if(user.getUserType()==0){
		   List<Exam> exam =_examRepository.findAll();
		   if(exam==null){
			   model.addAttribute("exam", null); 
		   }else{
			   model.addAttribute("exam", exam); 
		   }
		   List <ExamSumScores> examSumScores;
		   examSumScores =_examSumScoresRepository.findByUserId(user.getId());
		   if(examSumScores!=null){
			   model.addAttribute("examSumScores", examSumScores);
			   List<ExamSumScores> list=new ArrayList<ExamSumScores>();
			   for(int i =0;i<examSumScores.size();i++){
				//   ExamSumScores   examSumScoresexam =_examSumScoresRepository.findByExamId(examSumScores.get(i).getExam().getId());
				   BigDecimal myscore = examSumScores.get(i).getSumScore();
					BigDecimal creditsRequired =new BigDecimal(examSumScores.get(i).getExam().getCreditsRequired()) ;
					BigDecimal result =myscore.subtract(creditsRequired);
					int r =result.compareTo(new BigDecimal(0));
					String results;
					if(r==1 || r==0){
						//考试分数大于等于要求学分
						results ="1";
						
					}else{
						results ="0";
					}
					
					ExamSumScores examSumScoresadd = new ExamSumScores();
					examSumScoresadd.setExam(examSumScores.get(i).getExam());
					examSumScoresadd.setSumScore(examSumScores.get(i).getSumScore());
					examSumScoresadd.setResult(results);
					list.add(examSumScoresadd);
				    model.addAttribute("examSumScoresadd", list);
			   }
			  
		   }else{
			   model.addAttribute("examSumScores", null);
		   }
	   }
	   if(childlist.size()>0){
		   model.addAttribute("childs", childlist);
		 /*  List<Exam> exam =_examRepository.findByStatus("1");
		   if(exam==null){
			   model.addAttribute("exam", null); 
		   }else{
			   model.addAttribute("exam", exam); 
		   }*/
		   List<Exam> exam =_examRepository.findAll();
		   if(exam==null){
			   model.addAttribute("exam", null); 
		   }else{
			   model.addAttribute("exam", exam); 
		   }
		 
		   if(request.getParameter("childId")==null){
			   model.addAttribute("flagvalue", childlist.get(0).getId());
			  List <ExamSumScores> examSumScores;
			   if(user.getUserType()==1){
				   examSumScores =_examSumScoresRepository.findByChildId(childlist.get(0).getId());
				   if(examSumScores!=null){
					   List<ExamSumScores> list=new ArrayList<ExamSumScores>();
					   	for(int i =0;i<examSumScores.size();i++){
						//   ExamSumScores   examSumScoresexam =_examSumScoresRepository.findByExamId(examSumScores.get(i).getExam().getId());
					   		
					   		BigDecimal myscore = examSumScores.get(i).getSumScore();
							BigDecimal creditsRequired =new BigDecimal(examSumScores.get(i).getExam().getCreditsRequired()) ;
							BigDecimal result =myscore.subtract(creditsRequired);
							int r =result.compareTo(new BigDecimal(0));
							String results;
							if(r==1 || r==0){
								//考试分数大于等于要求学分
								results ="1";
								
							}else{
								results ="0";
							}
						
							ExamSumScores examSumScoresadd = new ExamSumScores();
							examSumScoresadd.setExam(examSumScores.get(i).getExam());
							examSumScoresadd.setSumScore(examSumScores.get(i).getSumScore());
							examSumScoresadd.setResult(results);
							list.add(examSumScoresadd);
						    model.addAttribute("examSumScoresadd", list);
					   }
					   
					   model.addAttribute("examSumScores", examSumScores);
				   }else{
					   model.addAttribute("examSumScores", null);
				   }
			   }else{
				   examSumScores =_examSumScoresRepository.findByUserId(user.getId());
				   if(examSumScores!=null){
					   model.addAttribute("examSumScores", examSumScores);
					   List<ExamSumScores> list=new ArrayList<ExamSumScores>();
					   for(int i =0;i<examSumScores.size();i++){
						   ExamSumScores   examSumScoresexam =_examSumScoresRepository.findByExamId(examSumScores.get(i).getExam().getId());
						   BigDecimal myscore = examSumScores.get(i).getSumScore();
							BigDecimal creditsRequired =new BigDecimal(examSumScores.get(i).getExam().getCreditsRequired()) ;
							BigDecimal result =myscore.subtract(creditsRequired);
							int r =result.compareTo(new BigDecimal(0));
							String results;
							if(r==1 || r==0){
								//考试分数大于等于要求学分
								results ="1";
								
							}else{
								results ="0";
							}
							
							ExamSumScores examSumScoresadd = new ExamSumScores();
							examSumScoresadd.setExam(examSumScores.get(i).getExam());
							examSumScoresadd.setSumScore(examSumScores.get(i).getSumScore());
							examSumScoresadd.setResult(results);
							list.add(examSumScoresadd);
						    model.addAttribute("examSumScoresadd", list);
					   }
					  
				   }else{
					   model.addAttribute("examSumScores", null);
				   }
			   }
		   }else{
			   model.addAttribute("flagvalue", request.getParameter("childId"));
			  List <ExamSumScores> examSumScores;
			   if(user.getUserType()==1){
				   examSumScores =_examSumScoresRepository.findByChildId(Integer.parseInt(request.getParameter("childId")));
				   if(examSumScores!=null){
						List<ExamSumScores> list=new ArrayList<ExamSumScores>();
					   	for(int i =0;i<examSumScores.size();i++){
						//   ExamSumScores   examSumScoresexam =_examSumScoresRepository.findByExamId(examSumScores.get(i).getExam().getId());
						   BigDecimal myscore = examSumScores.get(i).getSumScore();
							BigDecimal creditsRequired =new BigDecimal(examSumScores.get(i).getExam().getCreditsRequired()) ;
							BigDecimal result =myscore.subtract(creditsRequired);
							int r =result.compareTo(new BigDecimal(0));
							String results;
							if(r==1 || r==0){
								//考试分数大于等于要求学分
								results ="1";
								
							}else{
								results ="0";
							}
						
							ExamSumScores examSumScoresadd = new ExamSumScores();
							examSumScoresadd.setExam(examSumScores.get(i).getExam());
							examSumScoresadd.setSumScore(examSumScores.get(i).getSumScore());
							examSumScoresadd.setResult(results);
							list.add(examSumScoresadd);
						    model.addAttribute("examSumScoresadd", list);
							
					   }
					   
					    model.addAttribute("examSumScores", examSumScores);
				   }else{
					   model.addAttribute("examSumScores", null);
				   }
			   }else{
				   examSumScores =_examSumScoresRepository.findByUserId(user.getId());
				   if(examSumScores!=null){
					   model.addAttribute("examSumScores", examSumScores);
					   List<ExamSumScores> list=new ArrayList<ExamSumScores>();
					   for(int i =0;i<examSumScores.size();i++){
						//   ExamSumScores   examSumScoresexam =_examSumScoresRepository.findByExamId(examSumScores.get(i).getExam().getId());
						   BigDecimal myscore = examSumScores.get(i).getSumScore();
							BigDecimal creditsRequired =new BigDecimal(examSumScores.get(i).getExam().getCreditsRequired()) ;
							BigDecimal result =myscore.subtract(creditsRequired);
							int r =result.compareTo(new BigDecimal(0));
							String results;
							if(r==1 || r==0){
								//考试分数大于等于要求学分
								results ="1";
								
							}else{
								results ="0";
							}
							
							ExamSumScores examSumScoresadd = new ExamSumScores();
							examSumScoresadd.setExam(examSumScores.get(i).getExam());
							examSumScoresadd.setSumScore(examSumScores.get(i).getSumScore());
							examSumScoresadd.setResult(results);
							list.add(examSumScoresadd);
						    model.addAttribute("examSumScoresadd", list);
					   }
					  
				   }else{
					   model.addAttribute("examSumScores", null);
				   }
			   }
		   
			   
		  
		   }
	   }
	   return "/itemmanagement/myscore";
   }
   
   
   
   
   
   
   //点击按钮保存
   //上一题
   @RequestMapping(value = "/checksaveanswer",method = RequestMethod.GET)
   public String checksaveanswer(Model model, HttpServletRequest request,HttpSession session)
   {
	   Integer childId=Integer.parseInt(request.getParameter("childId"));
	   System.out.println(request.getParameter("ordernumber"));
	   String answer = request.getParameter("answer");
	   String ordernumber = request.getParameter("ordernumber");
	  
	   Child child = _cChildRepository.findById(childId);
	 
	   model.addAttribute("child", child);
	   List<ItemManagementQuestion> temManagementQuestion = _itemManagementQuestionRepository.findByexamStatues("1");
	   model.addAttribute("questionsize", temManagementQuestion.size());
	   model.addAttribute("temManagementQuestion", temManagementQuestion);
	  
		   List<Exam> exam =_examRepository.findByStatus("1");
		   model.addAttribute("exam", exam);
	   model.addAttribute("ordernumber", ordernumber);
	
		   /*ItemManagementQuestion ItemManagementQuestion=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),Integer.parseInt(ordernumber));
			if(ItemManagementQuestion==null){
				ItemManagementQuestion ItemManagementQuestionsel=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),Integer.parseInt(ordernumber)+1);
				model.addAttribute("itemManagementQuestion", ItemManagementQuestionsel);
				}else{
					 model.addAttribute("itemManagementQuestion", ItemManagementQuestion);
				}      
			 model.addAttribute("examcourse", exam.get(0).getCourse().getCourseLevel());
			 ExamSumScores findexamSumScores  = _examSumScoresRepository.findByChildIdAndExamId(childId, exam.get(0).getId());
								   if(findexamSumScores==null){    	
							   //保存到成绩表
							      ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),Integer.parseInt(ordernumber));
							      ExamScores examscores = _examScoresRepository.findByExamScores(ItemManagementQuestions.getId(), Integer.parseInt(ordernumber), childId);
							      if(examscores==null){
							    	  ExamScores examscore = new ExamScores();
							    	  if(answer==""){
							    		  examscore.setAnswer("");
							    	  }else{
							    		  examscore.setAnswer(answer);
							    	  }
							    	  model.addAttribute("answers","");
								      examscore.setChild(child);
								      examscore.setCreateTime(new Date());
								      examscore.setOrdinalnumber(Integer.parseInt(ordernumber));
								      examscore.setItemManagementQuestion(ItemManagementQuestions);
								     
								      if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
								    	  examscore.setAnswerStatus(1);
								      }else{
								    	  examscore.setAnswerStatus(0);
								      }
								      _examScoresRepository.save(examscore);
							      }else{
							    	  
							      }
				   
		   }*/
	   		ItemManagementQuestion ItemManagementQuestions=  _itemManagementQuestionRepository.findByOrdernumber(exam.get(0).getId(),Integer.parseInt(ordernumber));
	   		ExamScores examscores = _examScoresRepository.findByExamScores(ItemManagementQuestions.getId(), Integer.parseInt(ordernumber), childId);
	   		
	   		if(examscores==null){
	   		 ExamScores examscore = new ExamScores();
	    	  if(answer==""){
	    		  examscore.setAnswer("");
	    	  }else{
	    		  examscore.setAnswer(answer);
	    	  }
	    	  model.addAttribute("answers","");
		      examscore.setChild(child);
		      examscore.setCreateTime(new Date());
		      examscore.setOrdinalnumber(Integer.parseInt(ordernumber));
		      examscore.setItemManagementQuestion(ItemManagementQuestions);
		     
		      if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
		    	  examscore.setAnswerStatus(1);
		      }else{
		    	  examscore.setAnswerStatus(0);
		      }
		      _examScoresRepository.save(examscore);
	   		}else{
	   		 model.addAttribute("answers",examscores.getAnswer());
		 		ExamScores examscoreupdate =_examScoresRepository.findOne(examscores.getId());
		 		if(answer==""){
			 		  examscoreupdate.setAnswer("");
			 	  }else{
			 		  examscoreupdate.setAnswer(answer);
			 	  }
			 	  if(ItemManagementQuestions.getQuestion().getAnswer().equals(answer)){
			 		  examscoreupdate.setAnswerStatus(1);
				      }else{
				    	  examscoreupdate.setAnswerStatus(0);
				      }

			      _examScoresRepository.save(examscoreupdate);
	   		}
	   		
 	 
	   return "/itemmanagement/beginexamination";
   }
   
   
   
   //考试主页图
   @RequestMapping(value = "/coursetest",method = RequestMethod.GET)
   public String coursetest()
   {
       return "/itemmanagement/coursetest";
   }
   //fukuan
   @RequestMapping(value="/doSubmit",method = RequestMethod.GET)
	public String doSubmit(HttpServletRequest request,Model model)
	{
	   //计算总费用
       String result = request.getParameter("totalAmount");
       Integer child =Integer.parseInt( request.getParameter("childid"));
     //   Integer examid = Integer.parseInt(request.getParameter("examid"));
       BigDecimal totalresult = new BigDecimal(result);
       totalresult = totalresult.setScale(2, BigDecimal.ROUND_HALF_UP);
       User user = (User) WebUtils.getSessionAttribute(request, "user");

       //创建新订单
       ExamOrder examorder = new ExamOrder();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
    /*   Exam exam    = _examRepository.findOne(examid);*/
       //订单号 
       Date date = new Date();
       String ordernumber = "EXAM" + date.getTime();
       examorder.setCreateTime(new Date());
     /*  examorder.setExam(exam);*/
       //价格
       examorder.setOrderAmount(totalresult);
       examorder.setOrderStatus(0);
       examorder.setChild(_cChildRepository.findById(child));
       examorder.setOrderNumber(ordernumber);
       _examOrderRepository.save(examorder);
       //根据订单号查询orderid
       ExamOrder examorders  = _examOrderRepository.findByOrderNumber(ordernumber);
	   Integer orderID = examorders.getId();
       return "redirect:/AliPay/SubmitExam/"+orderID;
	}
}
