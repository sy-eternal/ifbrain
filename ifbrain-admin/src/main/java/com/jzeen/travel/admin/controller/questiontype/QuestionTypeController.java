package com.jzeen.travel.admin.controller.questiontype;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.data.entity.ItemTemplate;
import com.jzeen.travel.data.entity.Question;
import com.jzeen.travel.data.entity.QuestionType;
import com.jzeen.travel.data.repository.ItemTemplateRepository;
import com.jzeen.travel.data.repository.QuestionTypeRepository;

//考试系统题型管理界面
@Controller
@RequestMapping("/questiontype")
public class QuestionTypeController {
	 @Autowired
	 private QuestionTypeRepository _questionTypeRepository;
	 @Autowired
	 ItemTemplateRepository _itemTemplateRepository;
	//显示所有的文章列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
		
         return "/questiontype/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<QuestionType> search()
    {
		List<QuestionType> data = _questionTypeRepository.findAll();
		List<QuestionType> newdata=new ArrayList<QuestionType>();
		   for(int i=0;i<data.size();i++){
			   QuestionType q = new QuestionType();
				  q.setId(data.get(i).getId());
				  q.setQuestionTypeName(data.get(i).getQuestionTypeName());
				  q.setItemTemplate(data.get(i).getItemTemplate());
				//  q.setItemTemplatename(data.get(i).getItemTemplate().getTemplateName());
			      newdata.add(q);
		   }
        return newdata;
    }
	 @RequestMapping(value = "/index", method = RequestMethod.GET)
	    public String loginInit(Model model, HttpServletRequest request)
	    {
		 List<ItemTemplate> list = _itemTemplateRepository.findAll();
			model.addAttribute("list", list);
	        return "/questiontype/index";
	    }
	 
	 @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(HttpServletRequest request, Model model) throws Exception, UnsupportedEncodingException, SQLException
	    {
	    	String ItemTemplate=request.getParameter("ItemTemplate");
	        String questionTypeName=request.getParameter("questionTypeName");
	        String orderNumber=request.getParameter("orderNumber");
	        ItemTemplate itemTemplate = _itemTemplateRepository.findOne(Integer.parseInt(ItemTemplate));
	        QuestionType questionType=new QuestionType();
	        questionType.setItemTemplate(itemTemplate);
	        questionType.setOrderNumber(Integer.parseInt(orderNumber));
	        questionType.setQuestionTypeName(questionTypeName);
	        _questionTypeRepository.save(questionType);
			return "redirect:/questiontype/list";
	    }
	 
	  @RequestMapping(value = "/initupdate", method = RequestMethod.GET)
	    public String update(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	QuestionType questionType = _questionTypeRepository.findOne(Integer.parseInt(id));
	    	model.addAttribute("questionType", questionType);
	    	List<ItemTemplate> list = _itemTemplateRepository.findAll();	
	    	model.addAttribute("list", list);
			return "/questiontype/updatequestiontype";
	    }
	  @RequestMapping(value = "/questiontypeupdate", method = RequestMethod.POST)
	    public String materialupdate(HttpServletRequest request, Model model) throws Exception
	    {
		    String id = request.getParameter("id");
	    	QuestionType questionType = _questionTypeRepository.findOne(Integer.parseInt(id));			String ItemTemplate=request.getParameter("ItemTemplate");
			String questionTypeName=request.getParameter("questionTypeName");
		    String orderNumber=request.getParameter("orderNumber");
		    ItemTemplate itemTemplate = _itemTemplateRepository.findOne(Integer.parseInt(ItemTemplate));
		    questionType.setItemTemplate(itemTemplate);
		    questionType.setOrderNumber(Integer.parseInt(orderNumber));
		    questionType.setQuestionTypeName(questionTypeName);
		    _questionTypeRepository.save(questionType);
			return "redirect:/questiontype/list";
	    }
	  @ResponseBody
	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public void delete(HttpServletRequest request)
	    {
	    	String id = request.getParameter("id");
	    	_questionTypeRepository.delete(Integer.parseInt(id));
	    }
}
