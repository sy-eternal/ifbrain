package com.jzeen.travel.admin.controller.childshoppingmall;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.ChildShoppingmall;
import com.jzeen.travel.data.entity.CommodityMall;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseCode;
import com.jzeen.travel.data.entity.DemandLevel;
import com.jzeen.travel.data.entity.IfbrainIndex;
import com.jzeen.travel.data.entity.ShoppingmallCommodity;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.ChildShoppingmallRepository;
import com.jzeen.travel.data.repository.CommodityMallRepository;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.DemandLevelRepository;
import com.jzeen.travel.data.repository.IfbrainIndexReponsitory;
import com.jzeen.travel.data.repository.IfbrainVarReponsitory;
import com.jzeen.travel.data.repository.ShoppingmallCommodityRepository;
import com.jzeen.travel.data.repository.UserRepository;

@Controller
@RequestMapping("/childshoppingmall")
public class ChildShoppingMallController {

	@Autowired
	private CommodityMallRepository _commodityMallRepository;
	@Autowired
	private DemandLevelRepository  _demandLevelRepository;
	@Autowired
	private UserRepository  _userRepository;
	@Autowired
	private ChildRepository  _childRepository;
	@Autowired
	ChildShoppingmallRepository _childShoppingmallRepository;
	@Autowired
	ShoppingmallCommodityRepository  _shoppingmallCommodityRepository;
	@Autowired
	IfbrainVarReponsitory _ifbrainVarReponsitory;
	@Autowired
	IfbrainIndexReponsitory  _ifbrainIndexReponsitory;
	@Autowired
    private CourseCodeRepository _cCourseCodeRepository;
	@Autowired
    private CourseReponsitory _cCourseReponsitory;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
		 List<Child> childlist = _childRepository.findAll();
		 model.addAttribute("childlist",childlist);
         return "/childshoppingmall/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Child> search()
    {
		List<Child> data = _childRepository.findAll();
        return data;
    }
		
		//新增上传孩子购物信息
		@RequestMapping(value = "/create", method = RequestMethod.GET)
	    public String create(HttpServletRequest request, Model model)
	    {
			List<User> userList = _userRepository.findAll();
			model.addAttribute("userList", userList);
			List<ShoppingmallCommodity> commoditylist = _shoppingmallCommodityRepository.findAll();
			model.addAttribute("list", commoditylist);
			 List<Course> course = _cCourseReponsitory.findAll();
		     model.addAttribute("course", course);
		       
	        return "/childshoppingmall/create";
	
	     }
		//根据商品获得所属商品种类
		@ResponseBody
		@RequestMapping(value = "/findDemandById", method = RequestMethod.GET)
	    public DemandLevel findDemandById(HttpServletRequest request, Model model)
	    {
			String id = request.getParameter("id");
			ShoppingmallCommodity shoppingmallCommodity = _shoppingmallCommodityRepository.findOne(Integer.parseInt(id));
			DemandLevel demandLevel = shoppingmallCommodity.getDemandLevel();
			return demandLevel;
	    }
		//根据商品获得所属需求级别
		@ResponseBody
		@RequestMapping(value = "/findcommodityById", method = RequestMethod.GET)
	    public CommodityMall get(HttpServletRequest request, Model model)
	    {
			String id = request.getParameter("id");
			ShoppingmallCommodity shoppingmallCommodity = _shoppingmallCommodityRepository.findOne(Integer.parseInt(id));
			CommodityMall commodityMall = shoppingmallCommodity.getCommodityMall();
			return commodityMall;
	    }
		/**//**
		 * 孩子新增购物信息提交
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/createinfo", method = RequestMethod.POST)
	    public String createinfo(HttpServletRequest request, Model model)
	    {
			 //获得课程级别
			 String courseLevelId = request.getParameter("Course");
			 Course course = _cCourseReponsitory.findOne(Integer.parseInt(courseLevelId));
			//获得课程级别下面的哪节课
			String ordinalNumber = request.getParameter("CourseCode");
			//获得孩子
			String chidid = request.getParameter("chidid");
			 //获得商品数量
			 String commodityQuantity = request.getParameter("commodityQuantity");
			 //获得商品
			 String commodity = request.getParameter("commodity");
			 ShoppingmallCommodity shoppingmallCommodity = _shoppingmallCommodityRepository.findOne(Integer.parseInt(commodity));
			List<IfbrainIndex> list = _ifbrainIndexReponsitory.findByChildIdAndCourseLeveldesc(Integer.parseInt(chidid),course.getCourseLevel());
			if(list.size()>0){
			//当节课的财脑
		     IfbrainIndex ifbrainIndex = _ifbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber),Integer.parseInt(chidid),course.getCourseLevel());
		    List<IfbrainIndex> ifbrainindexlist =_ifbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevelCheckShop(Integer.parseInt(ordinalNumber),Integer.parseInt(chidid),course.getCourseLevel());
		  
		    
		  /*   if(Integer.parseInt(ordinalNumber)>1){*/
		    	 for(int i=0;i<ifbrainindexlist.size();i++){
		    		 ifbrainindexlist.get(i).setBalance(ifbrainindexlist.get(i).getBalance().subtract(new BigDecimal(Integer.parseInt(commodityQuantity)).multiply(shoppingmallCommodity.getPrice())));
			    	 _ifbrainIndexReponsitory.save(ifbrainindexlist);
			    }
		    	/* list.get(0).setBalance(list.get(0).getBalance().subtract(new BigDecimal(Integer.parseInt(commodityQuantity)).multiply(shoppingmallCommodity.getPrice())));
				 _ifbrainIndexReponsitory.save(list.get(0));*/
		     //上节课的财脑 123456
		   /*IfbrainIndex ifbrainindex = _ifbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber)-1, Integer.parseInt(chidid),course.getCourseLevel());
			 ifbrainIndex.setBalance((ifbrainindex.getBalance().add(ifbrainIndex.getIncome())).subtract(new BigDecimal(Integer.parseInt(commodityQuantity)).multiply(shoppingmallCommodity.getPrice())));*/
		   /*  }*//*else{
		    	 BigDecimal subtract = ifbrainIndex.getIncome().subtract(new BigDecimal(Integer.parseInt(commodityQuantity)).multiply(shoppingmallCommodity.getPrice()));
		    	 ifbrainIndex.setBalance(subtract);
		    	 _ifbrainIndexReponsitory.save(ifbrainIndex);
		     }*/
		     Child child = _childRepository.findOne(Integer.parseInt(chidid));
			 ChildShoppingmall  childshopingmall=new ChildShoppingmall();
			 childshopingmall.setChild(child);
			 childshopingmall.setSumPrice(new BigDecimal(Integer.parseInt(commodityQuantity)).multiply(shoppingmallCommodity.getPrice()));
			 childshopingmall.setCreateTime(new Date());
			 childshopingmall.setShoppingmallCommodity(shoppingmallCommodity);
			 childshopingmall.setCourseLevel(course.getCourseLevel());
			 childshopingmall.setOrdinalNumber(Integer.parseInt(ordinalNumber));
			 childshopingmall.setResult(new BigDecimal(Integer.parseInt(commodityQuantity)).multiply(shoppingmallCommodity.getPrice()).toString());
			 childshopingmall.setPayStatus(1);
			 childshopingmall.setType("1");
			 childshopingmall.setQuantity(Integer.parseInt(commodityQuantity));
			 _childShoppingmallRepository.save(childshopingmall);
			  BigDecimal expense = ifbrainIndex.getExpense();
				 if(expense.intValue()==0){
					 ifbrainIndex.setExpense(new BigDecimal(Integer.parseInt(commodityQuantity)).multiply(shoppingmallCommodity.getPrice()));
				 }else{
					 expense=expense.add(new BigDecimal(Integer.parseInt(commodityQuantity)).multiply(shoppingmallCommodity.getPrice()));
					 ifbrainIndex.setExpense(expense);
				 }
				 _ifbrainIndexReponsitory.save(ifbrainIndex);
			/* if(list.get(0).getBalance().toString()=="0"){
				 //计算代币余额
				 //总收入
				 //BigDecimal sum = _ifbrainIndexReponsitory.findSumPriceByChildIds(child.getId());
				 BigDecimal sum = list.get(0).getBalance();
				 //减去支出,算出余额
				 list.get(0).setBalance(sum.subtract(new BigDecimal(Integer.parseInt(commodityQuantity)).multiply(shoppingmallCommodity.getPrice())));
				 //_childRepository.save(child);
				 _ifbrainIndexReponsitory.save(list.get(0));
			 }else{
				 list.get(0).setBalance(list.get(0).getBalance().subtract(new BigDecimal(Integer.parseInt(commodityQuantity)).multiply(shoppingmallCommodity.getPrice())));
				 _ifbrainIndexReponsitory.save(list.get(0));
			 }*/
			 return  "redirect:/childshoppingmall/list";
			}else if(list.size()==0){
					model.addAttribute("message", "你还没有赚取代币哦!");
			}else{
				if(list.get(0).getBalance().compareTo(new BigDecimal(Integer.parseInt(commodityQuantity)).multiply(shoppingmallCommodity.getPrice()))==-1){
					model.addAttribute("message", "你的代币余额不足,请继续努力赚取代币哦!");
				}
			}
			return "/childshoppingmall/create";
	    }
		@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	    public String detail(@PathVariable int id,HttpServletRequest request, Model model)
	    {
			Child child = _childRepository.findOne(id);
			
	     	List<Object[]> list = _childShoppingmallRepository.findByChildIdAndDemandlevel(child.getId(),"1",1);
			List<ChildShoppingmall> childShoppingmallList =new ArrayList<ChildShoppingmall>();
			//存百分比
			List<ChildShoppingmall> resultall=new ArrayList<ChildShoppingmall>();
			BigDecimal totalprice=new BigDecimal(0); 
			if(list.size()>0){
			for(int i=0;i<list.size();i++){
				ChildShoppingmall childShopping=new ChildShoppingmall();
				BigDecimal sum=(BigDecimal)list.get(i)[0];
				String name=(String)list.get(i)[1];
				totalprice=totalprice.add(sum);
				childShopping.setSumPrice(sum);
				childShopping.setDemandname(name);
				childShoppingmallList.add(childShopping);
			}
			for(int i=0;i<childShoppingmallList.size();i++){
               double value = childShoppingmallList.get(i).getSumPrice().doubleValue();
               double sum = totalprice.doubleValue();
               double percent =value/sum;
				NumberFormat percentresult = NumberFormat.getPercentInstance();
				percentresult.setMaximumFractionDigits(2);
		        String lastresult = percentresult.format(percent);
		        ChildShoppingmall shoppingmall = childShoppingmallList.get(i);
		        shoppingmall.setPercent(lastresult);
		        resultall.add(shoppingmall);
			}
			model.addAttribute("childid", child.getId());
			model.addAttribute("list", resultall);
			//偏思想类占比
			Object[] object = _shoppingmallCommodityRepository.findByChildIdAndDemandlevel(child.getId());
			BigDecimal sixi = (BigDecimal)object[0];
			if(sixi==null){
				sixi=new BigDecimal(0);
			}
			//和偏实用类的占比
			Object[] object1 = _shoppingmallCommodityRepository.findByChildIdAndDemandlevel1(child.getId());
			BigDecimal shiy =(BigDecimal)object1[0];
			if(shiy==null){
			    shiy=new BigDecimal(0);
			}
			//根据百分比，判断所属需求者类型
			BigDecimal physiologicalneeds=new BigDecimal(0);
			BigDecimal securityrequirements=new BigDecimal(0);
			BigDecimal socialdemand=new BigDecimal(0);
			BigDecimal respectdemand=new BigDecimal(0);;
			BigDecimal selfactualizationneeds=new BigDecimal(0);
			if(resultall.size()>0){
			for(int i=0;i<resultall.size();i++){
				if(resultall.get(i).getDemandname().equals("生理需求")){
					physiologicalneeds=physiologicalneeds.add(new BigDecimal(resultall.get(i).getPercent().replace("%", "")));
				     }
				if(resultall.get(i).getDemandname().equals("安全需求")){
					securityrequirements=securityrequirements.add(new BigDecimal(resultall.get(i).getPercent().replace("%", "")));
				     }
				if(resultall.get(i).getDemandname().equals("社交需求")){
					socialdemand=socialdemand.add(new BigDecimal(resultall.get(i).getPercent().replace("%", "")));
				     }
				if(resultall.get(i).getDemandname().equals("尊重需求")){
					respectdemand=respectdemand.add(new BigDecimal(resultall.get(i).getPercent().replace("%", "")));
				     }
				if(resultall.get(i).getDemandname().equals("自我实现需求")){
					selfactualizationneeds=selfactualizationneeds.add(new BigDecimal(resultall.get(i).getPercent().replace("%", "")));
				     }
			                                     }
			    if(new BigDecimal(physiologicalneeds.toString()).compareTo(new BigDecimal("60"))==1){
			    	model.addAttribute("flag", 0);
			    	child.setType("0");
			    }else if(new BigDecimal(physiologicalneeds.toString()).add(securityrequirements).compareTo(new BigDecimal("60"))==1){
			    	if(shiy.compareTo(sixi)==1&&shiy.compareTo(sixi)==0){
			    		model.addAttribute("flag", 1);
			    		child.setType("1");
			    	}else{
			    		model.addAttribute("flag", 2);
			    		child.setType("2");
			    	}
			    }else if(new BigDecimal(physiologicalneeds.toString()).add(securityrequirements).add(socialdemand).compareTo(new BigDecimal("60"))==1){
			    	if(shiy.compareTo(sixi)==1&&shiy.compareTo(sixi)==0){
			    		model.addAttribute("flag", 3);
			    		child.setType("3");
			    	}else{
			    		model.addAttribute("flag", 4);
			    		child.setType("4");
			    	}
			    }else if(new BigDecimal(physiologicalneeds.toString()).add(securityrequirements).add(socialdemand).add(respectdemand).compareTo(new BigDecimal("60"))==1){
			    	if(shiy.compareTo(sixi)==1&&shiy.compareTo(sixi)==0){
			    		model.addAttribute("flag", 5);
			    		child.setType("5");
			    	}else{
			    		model.addAttribute("flag", 6);
			    		child.setType("6");
			    	}
			    }else if(new BigDecimal(physiologicalneeds.toString()).add(securityrequirements).add(socialdemand).add(respectdemand).add(selfactualizationneeds).compareTo(new BigDecimal("60"))==-1){
			    	model.addAttribute("flag", 7);
			    	child.setType("7");
			    }
			}
			}
			_childRepository.save(child);
			return "/childshoppingmall/detail";
	     }
		
		
}
