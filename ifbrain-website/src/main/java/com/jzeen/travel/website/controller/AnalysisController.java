package com.jzeen.travel.website.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.DefineTaskRepository;
import com.jzeen.travel.data.repository.ShopHistoryRepository;

@Controller
@RequestMapping("/analysis")

public class AnalysisController {
	@Autowired
	DefineTaskRepository _historyTaskRepository;
	@Autowired
	ShopHistoryRepository  _shopHistoryRepository;
	@Autowired
	ChildRepository  _cChildRepository;
	
	
	/**
	 * @param 任务分析堆积图
	 * @return
	 */
	/*@RequestMapping(value = "/workanalysis",method = RequestMethod.GET)
	   public  String workanalysis(Model m,HttpServletRequest request)
	   {
		 Integer childid =  (Integer) request.getSession().getAttribute("childid");
		  
		  System.out.println(childid+"--------------------------------------------");
		  List<Object[]> historytask = _historyTaskRepository.findByChildId(childid);
		
		StringBuffer finishtimes=new StringBuffer();
		StringBuffer unfinishtimes=new StringBuffer();
		StringBuffer name=new StringBuffer();
		if(historytask.size()>0){
		for(int i=0;i<historytask.size();i++){
			String n=(String)historytask.get(i)[0];
			name.append(n);
			name.append(",");
			finishtimes.append(historytask.get(i)[1]);
			finishtimes.append(",");
			Long s=(Long)historytask.get(i)[2];
			BigDecimal sum=new BigDecimal(s);
			Long f=(Long)historytask.get(i)[1];
			BigDecimal finish=new BigDecimal(f);
			unfinishtimes.append(sum.subtract(finish).longValue());
			unfinishtimes.append(",");
		}
		m.addAttribute("finish", finishtimes.substring(0, finishtimes.length()-1));
		m.addAttribute("unfinish", unfinishtimes.substring(0, unfinishtimes.length()-1));
		m.addAttribute("name", name.substring(0, name.length()-1));
		}
		else{
			m.addAttribute("finish", "");
			m.addAttribute("unfinish","");
			m.addAttribute("name", "");
		}
	       return "/analysis/workanalysis";
	   }*/
	
	/**
	 * @return购物分析环形图
	 */
	@RequestMapping(value = "/shopanalysis",method = RequestMethod.GET)
	   public String shopanalysis(Model m,HttpServletRequest request)
	   {
		  Integer childid =  (Integer) request.getSession().getAttribute("childid");
		BigDecimal radious=new BigDecimal(150);
		JSONArray jsonArray=new JSONArray();
		List<Object[]> list = _shopHistoryRepository.findByChildId1(childid);
		
		if(list.size()>0){
		StringBuffer namelist=new StringBuffer();
		String type="pie";
		for(int i=0;i<list.size();i++){
	    List<BigDecimal>  radius=new ArrayList<BigDecimal>();
		String name=(String)list.get(i)[0];
		Long price=(Long)list.get(i)[1];
		JSONObject object=new JSONObject();
		object.put("name","'"+name+"'" );
		object.put("type","'"+type+"'");
		object.put("clockWise", false);
		radious=radious.subtract(new BigDecimal(25));
		radius.add(radious);
		radius.add(radious.add(new BigDecimal(25)));
		object.put("radius", radius.toString());
		object.put("itemStyle","dataStyle");
		//data
		JSONArray  data=new JSONArray();
		JSONObject object1=new JSONObject();
		JSONObject object2=new JSONObject();
		object1.put("value", price.intValue());
		object1.put("name", "'"+price.toString()+"%"+name+"'");
		
		namelist.append(price.toString()+"%"+name);
		namelist.append(",");
		object2.put("value", 20);
		object2.put("name", "'invisible'");
		object2.put("itemStyle", "placeHolderStyle");
		data.put(object1);
		data.put(object2);
		object.put("data", data.toString());
		jsonArray.put(object);
		}
		String result=jsonArray.toString().replace("\"", "");
		m.addAttribute("result", result);
		m.addAttribute("namelist", namelist.substring(0,namelist.length()-1));
		}else{
			m.addAttribute("result", "");
			m.addAttribute("namelist", "");
		}
	       return "/analysis/shopanalysis";
	   }
	//支出环形图
	
	@RequestMapping(value = "/choosechildanalysis",method = RequestMethod.GET)
	   public String choosechildanalysis(Model m,HttpServletRequest request)
	   {		  
		  List<Object[]> shophistory = _shopHistoryRepository.findshopByChildId(87);	
		  StringBuffer nameBuffer=new StringBuffer();
			JSONArray jsonArray=new JSONArray();
			if(shophistory.size()>0){
			for(int i=0;i<shophistory.size();i++){
				String name=(String)shophistory.get(i)[0];
				StringBuffer n=new StringBuffer();
				n.append("'");
				n.append(name);
				n.append("'");
				nameBuffer.append(name);
				nameBuffer.append(",");
			
				BigDecimal price=(BigDecimal)shophistory.get(i)[1];
				JSONObject json=new JSONObject();
				json.put("value", price.doubleValue());
				json.put("name", n.toString());
				jsonArray.put(json);
			     }
			String first=jsonArray.toString().replaceAll("\"", "");
			m.addAttribute("shopname",nameBuffer.substring(0, nameBuffer.length()-1));
			m.addAttribute("shopnameandprice",first);
			}
			else{
				m.addAttribute("shopname","");
				m.addAttribute("shopnameandprice","");
			}
	       return "/analysis/list";
	   }
	
	
	
	
	/**
	 * @return 查询各个分类的总金额,以及所占总金额的比例,金额分析饼状图
	 */
	@RequestMapping(value = "/accountanalysis",method = RequestMethod.GET)
	   public String accountanalysis(Model m,HttpServletRequest request)
	   {
		  Integer childid =  (Integer) request.getSession().getAttribute("childid");
		List<Object[]> list = _shopHistoryRepository.findByChildId(childid);
		StringBuffer nameBuffer=new StringBuffer();
		JSONArray jsonArray=new JSONArray();
		if(list.size()>0){
		for(int i=0;i<list.size();i++){
			String name=(String)list.get(i)[0];
			StringBuffer n=new StringBuffer();
			n.append("'");
			n.append(name);
			n.append("'");
			nameBuffer.append(name);
			nameBuffer.append(",");
			BigDecimal price=(BigDecimal)list.get(i)[1];
			JSONObject json=new JSONObject();
			json.put("value", price.doubleValue());
			json.put("name", n.toString());
			jsonArray.put(json);
		     }
		String first=jsonArray.toString().replaceAll("\"", "");
		m.addAttribute("shopname",nameBuffer.substring(0, nameBuffer.length()-1));
		m.addAttribute("shopnameandprice",first);
		}
		else{
			m.addAttribute("shopname","");
			m.addAttribute("shopnameandprice","");
		}
	       return "/analysis/accountanalysis";
	   }

	 
	   
	   /**
	 * @param model
	 * @param request
	 * @param session
	 * @return 炫酷  amcharts
	 */
	@RequestMapping(value = "/amchartsexample", method = RequestMethod.GET)
	    public String amchartsexample( Model model, HttpServletRequest request,HttpSession session)
	    {
	    	   
			   return "/analysis/columnartaskgraph";
	   }

}
