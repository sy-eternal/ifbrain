package com.jzeen.travel.admin.controller.question;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.Exam;
import com.jzeen.travel.data.entity.Question;
import com.jzeen.travel.data.entity.QuestionOptionImage;
import com.jzeen.travel.data.entity.QuestionType;
import com.jzeen.travel.data.repository.ItemTemplateRepository;
import com.jzeen.travel.data.repository.QuestionOptionImageRepository;
import com.jzeen.travel.data.repository.QuestionRepository;
import com.jzeen.travel.data.repository.QuestionTypeRepository;

//考试系统题库管理界面
@Controller
@RequestMapping("/question")
public class QuestionController {
	 @Autowired
	 private QuestionTypeRepository _questionTypeRepository;
	 @Autowired
	 QuestionRepository  _questionRepository;
	 @Autowired
	 ItemTemplateRepository _itemTemplateRepository;
	 @Autowired
	 QuestionOptionImageRepository  _questionOptionImageRepository;
		@Autowired
	    FileUploadSetting _fileUploadSetting;
	//显示所有的文章列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
         return "/question/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Question> search()
    {
		List<Question> data = _questionRepository.findAll();
		/*List<Question> newdata=new ArrayList<Question>();
		   for(int i=0;i<data.size();i++){
			   Question q = new Question();
			   q.setAnswer(data.get(i).getAnswer());
			   q.setCreateTime(data.get(i).getCreateTime());
			   q.setId(data.get(i).getId());
			   q.setOrdernumber(data.get(i).getOrdernumber());
			   q.setQuestionNameContent(data.get(i).getQuestionNameContent());
			   q.setTestdifficulty(data.get(i).getTestdifficulty());
			     q.setQuestiontype(data.get(i).getQuestiontype());
			   q.setRate(data.get(i).getRate());
			  
			   newdata.add(q);
		   }*/
        return data;
    }
	 @RequestMapping(value = "/index", method = RequestMethod.GET)
	    public String loginInit(Model model, HttpServletRequest request)
	    {
		 List<QuestionType> list = _questionTypeRepository.findAll();
			model.addAttribute("list", list);
	        return "/question/index";
	    }
	 //创建试题
	 @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(HttpServletRequest request, Model model) throws Exception, UnsupportedEncodingException, SQLException
	    {
		 String content = request.getParameter("content");
		 String content1 = request.getParameter("content1");
		 String answer= request.getParameter("answer");
		 String testdifficulty=request.getParameter("testdifficulty");
		 String questionTypeid = request.getParameter("QuestionType");
		 QuestionType questionType = _questionTypeRepository.findOne(Integer.parseInt(questionTypeid));
		 Integer ordernumber=_questionRepository.findmaxordernumber(questionType.getId());
		 if(ordernumber==null){
			 ordernumber=1;
		 }else{
			 ordernumber+=1;
		 }
		 Question question=new Question();
		 question.setCreateTime(new Date());
		 question.setQuestionNameContent(content);
		 question.setQuestionOptionContent(content1);
		 question.setAnswer(answer);
		 question.setTestdifficulty(testdifficulty);
		 question.setQuestiontype(questionType);
		 question.setOrdernumber(ordernumber);
		 _questionRepository.save(question);
			return "redirect:/question/list";
	    }
	 //初始化修改页面
	  @RequestMapping(value = "/initupdate", method = RequestMethod.GET)
	    public String update(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	Question question = _questionRepository.findOne(Integer.parseInt(id));
	    	model.addAttribute("questionType", question.getQuestiontype());
	    	model.addAttribute("question", question);
			return "/question/updatequestion";
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
			return "redirect:/question/list";
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
	
	  //试题题目上传的图片
		 @ResponseBody
		 @RequestMapping(value = "/updateuploadimg", method = RequestMethod.POST)
		    public String updateinfo(HttpServletRequest request,HttpServletResponse response,Model model,@RequestParam("upload") MultipartFile[] upload) throws IOException
		    {
			 PrintWriter out = response.getWriter();
			 String filePath="";
			 if(upload.length>0){
				 for(int i = 0;i<upload.length;i++){  
			    	    filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getQuestionoptionPath();
			       if (!upload[i].isEmpty())
			       {
			           try
			           {
			               byte[] bytes = upload[i].getBytes();
			               File directory = new File(filePath);
			               if (!directory.exists())
			               {
			                   directory.mkdirs();
			               }
			              String  filename=upload[i].getOriginalFilename();
			              filePath += filename;
			              String lujing=_fileUploadSetting.getQuestionoptionPath()+filename;
			              QuestionOptionImage image=new QuestionOptionImage();
			              image.setCreateTime(new Date());
			              image.setFileName(filename);
			              image.setFilePath(lujing);
			              _questionOptionImageRepository.save(image);
			              WebUtils.setSessionAttribute(request, "questionimageid", image.getId());
			               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			               stream.write(bytes);
			               stream.close();
			               //上传文件到项目文件夹结束
				              String callback =request.getParameter("CKEditorFuncNum"); 
				              out.println("<script type=\"text/javascript\">");
				              out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'" +"/questionoptionimage/show/"+image.getId()+"','')");   
				              out.println("</script>");
			           }
			           catch (Exception e)
			           {
			               e.printStackTrace();
			           }
			       }
			       }
			 }
		        return null;
		    }
}
