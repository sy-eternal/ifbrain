package com.jzeen.travel.admin.controller.itemmanagement;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.Exam;
import com.jzeen.travel.data.entity.ItemManagement;
import com.jzeen.travel.data.entity.ItemManagementQuestion;
import com.jzeen.travel.data.entity.Question;
import com.jzeen.travel.data.entity.QuestionType;
import com.jzeen.travel.data.repository.ExamRepository;
import com.jzeen.travel.data.repository.ItemManagementQuestionRepository;
import com.jzeen.travel.data.repository.ItemManagementRepository;
import com.jzeen.travel.data.repository.QuestionRepository;
import com.jzeen.travel.data.repository.QuestionTypeRepository;

//考试系统题库管理界面
@Controller
@RequestMapping("/itemmanagement")
public class ItemManagementController {
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
	    FileUploadSetting _fileUploadSetting;
	 //创建试题
	 @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(HttpServletRequest request, Model model) throws Exception, UnsupportedEncodingException, SQLException
	    {
		 String examid = request.getParameter("examid");
		 Exam exam = _examRepository.findOne(Integer.parseInt(examid));
		 String QuestionTypeId = request.getParameter("QuestionType");
		 String itemtmanagetitle = request.getParameter("itemtmanagetitle");
		 String ordernumber= request.getParameter("ordernumber");
		 String perscore=request.getParameter("perscore");
		 String itemdescription = request.getParameter("itemdescription");
		 
		 QuestionType questionType = _questionTypeRepository.findOne(Integer.parseInt(QuestionTypeId));
		 List <ItemManagement> itemManagementlist=_itemManagementRepository.findByExamIdAndQuestiontypeId(Integer.parseInt(examid), Integer.parseInt(QuestionTypeId));
		 String message = "";
		 if(itemManagementlist.size()==0){
			 ItemManagement itemManagement=new ItemManagement();
			 itemManagement.setCreateTime(new Date());
			 itemManagement.setItemdescription(itemdescription);
			 itemManagement.setPerscore(perscore);
			 itemManagement.setQuestiontype(questionType);
			 itemManagement.setTitle(itemtmanagetitle);
			 itemManagement.setOrdernumber(Integer.parseInt(ordernumber));
			 itemManagement.setExam(exam);
			 _itemManagementRepository.save(itemManagement);
			 return "redirect:/exam/itemmanagement?id="+exam.getId()+"&message="+"";
		 }else{
			/*message="已经存在相同题型";*/
			return "redirect:/exam/itemmanagement?id="+exam.getId()+"&message="+"1";
		 }
		
			
	    }
	 //获得试题列表
	   @ResponseBody
	   @RequestMapping(value = "/getquestions", method = RequestMethod.GET)
	    public List<Question> getquestions(HttpServletRequest request, Model model) throws Exception
	    {
		    String id = request.getParameter("id");
		    String itemmanagementid = request.getParameter("itemmanagementid");
		    List<Question> questionlist = _itemManagementRepository.findquestion(Integer.parseInt(itemmanagementid));
		    List<Question> list = _questionRepository.findByQuestiontypeId(Integer.parseInt(id));
		    List<Question> result=new ArrayList<Question>();
		    if(questionlist.size()>0){
		    	for(Question q:questionlist){
		    		if(list.contains(q)){
		    			list.remove(q);
		    		}
		    	}
		    	for(int i=0;i<list.size();i++){
    		    	Question n=new Question();
    		    	n.setId( list.get(i).getId());
    		    	n.setQuestionNameContent(list.get(i).getQuestionNameContent());
    		    	n.setTestdifficulty(list.get(i).getTestdifficulty());
    		    	n.setAnswer(list.get(i).getQuestiontype().getQuestionTypeName());
    		    	result.add(n);
    		    }
		    }else{
		    	for(int i=0;i<list.size();i++){
			    	Question n=new Question();
			    	n.setId( list.get(i).getId());
			    	n.setQuestionNameContent(list.get(i).getQuestionNameContent());
			    	n.setTestdifficulty(list.get(i).getTestdifficulty());
			    	n.setAnswer(list.get(i).getQuestiontype().getQuestionTypeName());
			    	result.add(n);
			    }
		    }
		   /* JSONArray array=new JSONArray();
		    for(int i=0;i<list.size();i++){
		    	 JSONObject object=new JSONObject();
		    	object.put("id", list.get(i).getId());
		    	object.put("questionTypeName", list.get(i).getQuestiontype().getQuestionTypeName());
		    	object.put("questionNameContent", list.get(i).getQuestionNameContent());
		    	object.put("testdifficulty", list.get(i).getTestdifficulty());
		    	array.put(object);
		    }*/
			return result;
	    }
	   //获得试题列表1
	   @ResponseBody
	   @RequestMapping(value = "/getquestions1", method = RequestMethod.GET)
	    public List<Question> getquestions1(HttpServletRequest request, Model model) throws Exception
	    {
		    String itemmanagementid = request.getParameter("itemmanagementid");
		    /*  ItemManagement itemManagement = _itemManagementRepository.findOne(Integer.parseInt(itemmanagementid));
		    Set<Question> set = itemManagement.getQuestionList();*/
		   List<Question> questionlist = _itemManagementRepository.findquestion(Integer.parseInt(itemmanagementid));
		    List<Question> result=new ArrayList<Question>();
		  for(Question q:questionlist){
		    	Question question=new Question();
		    	question.setId(q.getId());
		    	question.setQuestionNameContent(q.getQuestionNameContent());
		    	question.setTestdifficulty(q.getTestdifficulty());
		    	question.setAnswer(q.getQuestiontype().getQuestionTypeName());
		    	result.add(question);
		    }
		   /* JSONArray array=new JSONArray();
		    for(int i=0;i<list.size();i++){
		    	 JSONObject object=new JSONObject();
		    	object.put("id", list.get(i).getId());
		    	object.put("questionTypeName", list.get(i).getQuestiontype().getQuestionTypeName());
		    	object.put("questionNameContent", list.get(i).getQuestionNameContent());
		    	object.put("testdifficulty", list.get(i).getTestdifficulty());
		    	array.put(object);
		    }*/
			return result;
	    } 
	   
      //把试题添加到试卷内容中,单选
	   @ResponseBody
	   @RequestMapping(value = "/addquestion", method = RequestMethod.POST)
	    public String addquestion(HttpServletRequest request, Model model) throws Exception
	    {
		    String id = request.getParameter("id");
		    String itemmanagementid = request.getParameter("itemmanagementid");
		    Question question = _questionRepository.findOne(Integer.parseInt(id));
		    ItemManagement itemManagement = _itemManagementRepository.findOne(Integer.parseInt(itemmanagementid));
		    List<ItemManagementQuestion> list = _itemManagementQuestionRepository.findquestionbyitemid(Integer.parseInt(itemmanagementid));
		    if(list.size()>0){
		    	ItemManagementQuestion itemManagementQuestion=new ItemManagementQuestion();
		    	itemManagementQuestion.setItem(itemManagement);
		    	itemManagementQuestion.setQuestion(question);
		    	itemManagementQuestion.setOrdernumber(list.size()+1);
		    	_itemManagementQuestionRepository.save(itemManagementQuestion);
		    }else{
		    	ItemManagementQuestion itemManagementQuestion=new ItemManagementQuestion();
		    	itemManagementQuestion.setItem(itemManagement);
		    	itemManagementQuestion.setQuestion(question);
		    	itemManagementQuestion.setOrdernumber(1);
		    	_itemManagementQuestionRepository.save(itemManagementQuestion);
		    }
			return "选择成功!";
	    }
	   //把试题添加到试卷内容中,多选
	   @ResponseBody
	   @RequestMapping(value = "/addquestions", method = RequestMethod.POST)
	    public String addquestions(HttpServletRequest request, Model model) throws Exception
	    {
		   String ids = request.getParameter("ids");
		    String itemmanagementid = request.getParameter("itemmanagementid");
		    ItemManagement itemManagement = _itemManagementRepository.findOne(Integer.parseInt(itemmanagementid));
		    List<ItemManagementQuestion> list = _itemManagementQuestionRepository.findquestionbyitemid(Integer.parseInt(itemmanagementid));
		    String[] id = ids.substring(0, ids.length()-1).split(",");
		    List<ItemManagementQuestion> listresult=new ArrayList<ItemManagementQuestion>();
		    if(list.size()>0){
			    for(int i=0;i<id.length;i++){
			    	listresult=_itemManagementQuestionRepository.findquestionbyitemid(Integer.parseInt(itemmanagementid));
			    	ItemManagementQuestion itemManagementQuestion=new ItemManagementQuestion();
			    	 Question question = _questionRepository.findOne(Integer.parseInt(id[i]));
			    	 itemManagementQuestion.setItem(itemManagement);
			    	 itemManagementQuestion.setQuestion(question);
			    	 itemManagementQuestion.setOrdernumber(listresult.size()+1);
			    	 _itemManagementQuestionRepository.save(itemManagementQuestion);
			    }
		    }else{
		    	for(int i=0;i<id.length;i++){
			    	ItemManagementQuestion itemManagementQuestion=new ItemManagementQuestion();
			    	 Question question = _questionRepository.findOne(Integer.parseInt(id[i]));
			    	 itemManagementQuestion.setItem(itemManagement);
			    	 itemManagementQuestion.setQuestion(question);
			    	 itemManagementQuestion.setOrdernumber(i+1);
			    	 _itemManagementQuestionRepository.save(itemManagementQuestion);
			    }
		    }
			return "选择成功!";
	    }
	 //初始化修改页面
	  @RequestMapping(value = "/initupdate", method = RequestMethod.GET)
	    public String update(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	Question question = _questionRepository.findOne(Integer.parseInt(id));
	    	model.addAttribute("questionType", question.getQuestiontype());
	    	model.addAttribute("question", question);
			return "/itemmanagement/updatequestion";
	    }
	  //修改试题
	  @RequestMapping(value = "/questionupdate", method = RequestMethod.POST)
	    public String questionupdate(HttpServletRequest request, Model model) throws Exception
	    {
		    String id = request.getParameter("id");
		    Question question = _questionRepository.findOne(Integer.parseInt(id));
		    String content = request.getParameter("content");
			 String content1 = request.getParameter("content1");
			 String answer= request.getParameter("answer");
			 String testdifficulty=request.getParameter("testdifficulty");
			 question.setAnswer(answer);
			 question.setQuestionNameContent(content);
			 question.setQuestionOptionContent(content1);
			 question.setTestdifficulty(testdifficulty);
			 _questionRepository.save(question);
			return "redirect:/itemmanagement/list";
	    }
	  //删除试题
	    @Transactional
	    @ResponseBody
	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public void delete(HttpServletRequest request)
	    {
	    	String id = request.getParameter("id");
	    	Question question = _questionRepository.findOne(Integer.parseInt(id));
	    	int ordernumber = question.getOrdernumber();
	    	_questionRepository.updateordernumber(ordernumber);
	    	_questionRepository.delete(Integer.parseInt(id));
	    }
	    //删除题型
	    @ResponseBody
	    @RequestMapping(value = "/deleteitemmanagement", method = RequestMethod.DELETE)
	    public String deleteitemmanagement(HttpServletRequest request)
	    {
	    	  String id = request.getParameter("id");
	    	  ItemManagement itemManagement = _itemManagementRepository.findOne(Integer.parseInt(id));
	    	  _itemManagementRepository.delete(itemManagement);
			    return "删除成功!";
	    }
	    //批量删除试题
	    @Transactional
	    @ResponseBody
	    @RequestMapping(value = "/deletebyid", method = RequestMethod.DELETE)
	    public String deletebyid(HttpServletRequest request)
	    {
	    	  String ids = request.getParameter("ids");
	    	  String itemmanagementid = request.getParameter("itemmanagementid");
	    	  ItemManagement itemManagement = _itemManagementRepository.findOne(Integer.parseInt(itemmanagementid));
			    String[] id = ids.substring(0, ids.length()-1).split(",");
			   List<ItemManagementQuestion> list = _itemManagementQuestionRepository.findAll();
			    for(int i=0;i<id.length;i++){
			    	 Question question = _questionRepository.findOne(Integer.parseInt(id[i]));
			    	 for(int j=0;j<list.size();j++){
						   if(list.get(j).getItem().getId().equals(itemManagement.getId())&&list.get(j).getQuestion().getId().equals(question.getId())){
							   _itemManagementQuestionRepository.updateordernumber(list.get(j).getOrdernumber(),Integer.parseInt(itemmanagementid));
							   _itemManagementQuestionRepository.delete(list.get(j));
						   }
					   }
			    }
			    return "删除成功!";
	    }
	    //单个删除试题
	    @Transactional
	    @ResponseBody
	    @RequestMapping(value = "/deletebyidsingle", method = RequestMethod.DELETE)
	    public String deletebyidsingle(HttpServletRequest request)
	    {
	    	  String id = request.getParameter("id");
	    	  String itemmanagementid = request.getParameter("itemmanagementid");
	    	    Question question = _questionRepository.findOne(Integer.parseInt(id));
			   ItemManagement itemManagement = _itemManagementRepository.findOne(Integer.parseInt(itemmanagementid));
			   List<ItemManagementQuestion> list = _itemManagementQuestionRepository.findAll();
			   if(list.size()>0){
			   for(int i=0;i<list.size();i++){
				   if(list.get(i).getItem().getId().equals(itemManagement.getId())&&list.get(i).getQuestion().getId().equals(question.getId())){
					   _itemManagementQuestionRepository.updateordernumber(list.get(i).getOrdernumber(),Integer.parseInt(itemmanagementid));
					   _itemManagementQuestionRepository.delete(list.get(i));
				   }
			   }
			   }
			    return "删除成功!";
	    }
	    //初始化修改题型
	       @ResponseBody
		   @RequestMapping(value = "/initupdateitemmanagement", method = RequestMethod.POST)
		    public ItemManagement initupdateitemmanagement(HttpServletRequest request, Model model) throws Exception
		    {
			    String id = request.getParameter("id");
			    ItemManagement itemManagement = _itemManagementRepository.findOne(Integer.parseInt(id));
				return itemManagement;
		    }
	       
	       @RequestMapping(value = "/updateitemmanagement", method = RequestMethod.POST)
		    public String updateitemmanagement(HttpServletRequest request, Model model) throws Exception
		    {
		    String itemtmanageid = request.getParameter("itemtmanageid");
		    ItemManagement itemManagement = _itemManagementRepository.findOne(Integer.parseInt(itemtmanageid));
	  		 String QuestionTypeId = request.getParameter("QuestionType");
	  		 String itemtmanagetitle = request.getParameter("itemtmanagetitle");
	  		 String ordernumber= request.getParameter("ordernumber");
	  		 String perscore=request.getParameter("perscore");
	  		 String itemdescription = request.getParameter("itemdescription");
	  		 QuestionType questionType = _questionTypeRepository.findOne(Integer.parseInt(QuestionTypeId));
	  		itemManagement.setItemdescription(itemdescription);
	  		itemManagement.setOrdernumber(Integer.parseInt(ordernumber));
	  		itemManagement.setPerscore(perscore);
	  		itemManagement.setTitle(itemtmanagetitle);
	  		itemManagement.setQuestiontype(questionType);
	  		_itemManagementRepository.save(itemManagement);
	  		return "redirect:/exam/itemmanagement?id="+itemManagement.getExam().getId();
		    }   
	       
	       
}
