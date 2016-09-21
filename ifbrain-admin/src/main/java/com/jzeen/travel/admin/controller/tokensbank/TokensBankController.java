package com.jzeen.travel.admin.controller.tokensbank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.IfbrainIndex;
import com.jzeen.travel.data.repository.CourseClassReponsitory;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.IfbrainIndexReponsitory;

@Controller
@RequestMapping("/tokensbank")
public class TokensBankController {
	@Autowired
    private CourseClassReponsitory _cCourseClassReponsitory;
	@Autowired
    private CourseReponsitory _cCourseReponsitory;
	@Autowired
    private CourseCodeRepository _cCourseCodeRepository;
	@Autowired
    private IfbrainIndexReponsitory _IfbrainIndexReponsitory;
		//代币银行信息列表
			@RequestMapping(value = "/list", method = RequestMethod.GET)
		    public String list(HttpServletRequest request, Model model)
		    {
		        return "/tokensbank/list";
		    }
			//datatable列表显示
			@ResponseBody
		    @RequestMapping(value = "/search", method = RequestMethod.GET)
		    public List<IfbrainIndex> search(HttpServletRequest request)
		    {
				List<IfbrainIndex> list=new ArrayList<IfbrainIndex>();
				Integer ifbrainindexlist = Integer.parseInt(request.getParameter("ifbrainindexlist"));
		        List<IfbrainIndex> data = _IfbrainIndexReponsitory.findByClassId(ifbrainindexlist);
		        for(int i=0;i<data.size();i++){
		        	IfbrainIndex f=new IfbrainIndex();
		        	//f.setBalance(data.get(i).getBalance());
		        	f.setId(data.get(i).getId());
		        	f.setChild(data.get(i).getChild());
		        	f.setIncome(data.get(i).getIncome());
		        	f.setExpense(data.get(i).getExpense());
		        	f.setOrdinalNumber(data.get(i).getOrdinalNumber());
		        	list.add(f);
		        }
		        return list;
		    }
			 //详细信息
		    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
		    public String detail(@PathVariable int id, Model model)
		    {
		    	
		    	IfbrainIndex tokensbank = _IfbrainIndexReponsitory.findOne(id);
		         model.addAttribute("tokensbank", tokensbank);
		         BigDecimal knowledgetotalScore=new BigDecimal(tokensbank.getKnowledgetotalScore());
		         BigDecimal applicationtotalScore=new BigDecimal(tokensbank.getApplicationtotalScore());
		         BigDecimal expense=tokensbank.getExpense();
		         BigDecimal afterclassIncome=new BigDecimal(tokensbank.getAfterclassIncome());
		         BigDecimal income=knowledgetotalScore.add(applicationtotalScore).add(afterclassIncome);
	        	   model.addAttribute("income", income);
	        	   model.addAttribute("expense", expense);
		         if(expense==null){
		        	  BigDecimal overcome=knowledgetotalScore.add(applicationtotalScore).add(afterclassIncome);
		        	  model.addAttribute("overcome", overcome);
		         }else{
		         BigDecimal overcome=knowledgetotalScore.add(applicationtotalScore).add(afterclassIncome).subtract(expense);
		         model.addAttribute("overcome", overcome);
		         }
		        return "/tokensbank/detail";
		    }
}
