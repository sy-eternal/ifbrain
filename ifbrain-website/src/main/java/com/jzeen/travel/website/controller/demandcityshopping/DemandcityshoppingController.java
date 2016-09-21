package com.jzeen.travel.website.controller.demandcityshopping;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.ChildShoppingmall;
import com.jzeen.travel.data.entity.CommodityMall;
import com.jzeen.travel.data.entity.IfbrainIndex;
import com.jzeen.travel.data.entity.ShoppingmallCommodity;
import com.jzeen.travel.data.entity.ShoppingmallCommodityImage;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.ChildShoppingmallRepository;
import com.jzeen.travel.data.repository.CommodityMallRepository;
import com.jzeen.travel.data.repository.IfbrainIndexReponsitory;
import com.jzeen.travel.data.repository.ShoppingmallCommodityImageRepository;
import com.jzeen.travel.data.repository.ShoppingmallCommodityRepository;

@Controller
@RequestMapping("/demandcityshopping")
public class DemandcityshoppingController
{
	@Autowired
	ChildRepository _childRepository;
	@Autowired
	CommodityMallRepository _commodityMallRepository;
	@Autowired
	ShoppingmallCommodityRepository  _shoppingmallCommodityRepository;
	
	@Autowired
	ChildShoppingmallRepository  _childShoppingmallRepository;
	@Autowired
	ShoppingmallCommodityImageRepository  _shoppingmallCommodityImageRepository;
	@Autowired
	IfbrainIndexReponsitory  _IfbrainIndexReponsitory;
		    @RequestMapping(value = "/shoppinghome",method = RequestMethod.GET)
		    public String shoppinghome(HttpServletRequest request,HttpServletResponse response,Model model)
		    {
		    	User user = (User) request.getSession().getAttribute("user");
			 	String childid = request.getParameter("id");
			    	if(!childid.equals("")){
			    		List<Child> child =_childRepository.findByUser(user);
			    		
			    		model.addAttribute("flagvalue", childid);
			    		
						model.addAttribute("childs", child);
						model.addAttribute("child", child);
			    	}else{
		    	//获取用户
			    		List<Child> child =_childRepository.findByUser(user);
			    	
			    			model.addAttribute("flagvalue", child.get(0).getId());
			    		
						model.addAttribute("childs", child);
						model.addAttribute("child", child);
		    	}
		    	 return "/demandcityshopping/shoppinghome";
		    } 
	    @RequestMapping(value = "/planningrequirements",method = RequestMethod.GET)
	    public String planningrequirements(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	String childId = request.getParameter("flagvalue");
	    	User user = (User) request.getSession().getAttribute("user");
	    	if(childId.equals("")){
	    	//获取用户
	    		List<Child> child =_childRepository.findByUser(user);
				model.addAttribute("childs", child);
				model.addAttribute("child", child);	
				if(child.size()>0){
				  model.addAttribute("flagvalue", child.get(0).getId());
				}else{
					model.addAttribute("flagvalue", "");
				}
				
		    	}else{
		    		List<Child> child =_childRepository.findByUser(user);
					model.addAttribute("childs", child);
					model.addAttribute("child", child);	
		    		model.addAttribute("flagvalue", childId);
		    	}
	    	 return "/demandcityshopping/planningrequirements";
	    } 
	    
	    
	    @RequestMapping(value = "/maslowdemand",method = RequestMethod.GET)
	    public String maslowdemand(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	String childId = request.getParameter("childid");
	    	//获取用户
			User user = (User) request.getSession().getAttribute("user");
			if(childId.equals("")){
			List<Child> child =_childRepository.findByUser(user);
			model.addAttribute("childs", child);
			model.addAttribute("child", child);	
			if(child.size()>0){
			  model.addAttribute("flagvalue", child.get(0).getId());
			}else{
				model.addAttribute("flagvalue", "");
			}
			}else{
				List<Child> child =_childRepository.findByUser(user);
				model.addAttribute("childs", child);
				model.addAttribute("child", child);	
	    		model.addAttribute("flagvalue", childId);
			}
	    	 return "/demandcityshopping/maslowdemand";
	    } 
	    
	    
	    @RequestMapping(value = "/maslowdemands",method = RequestMethod.GET)
	    public String maslowdemands(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	
	    	 return "/demandcityshopping/maslowdemand";
	    } 
	    //九种商品分类
	    @RequestMapping(value = "/commodityclassification",method = RequestMethod.GET)
	    public String commodityclassification(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	String childId = request.getParameter("childid");
	    	User user = (User) request.getSession().getAttribute("user");
	    	//获取用户
	    	if(childId.equals("")){
			List<Child> child =_childRepository.findByUser(user);
			model.addAttribute("childs", child);
			model.addAttribute("child", child);	
			if(child.size()>0){
			  model.addAttribute("flagvalue", child.get(0).getId());
			}else{
				model.addAttribute("flagvalue", "");
			}
	    	}
	    	else{
				List<Child> child =_childRepository.findByUser(user);
				model.addAttribute("childs", child);
				model.addAttribute("child", child);	
	    		model.addAttribute("flagvalue", childId);
			}
	    	 return "/demandcityshopping/commodityclassification";
	    } 
	    //购物城中的孩子切换
	    @RequestMapping(value = "/switchbychildid",method = RequestMethod.GET)
	    public String switchbychildid(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	String id = request.getParameter("childId");
	    	return  "redirect:/demandcityshopping/maslowdemand?childid="+id;
	    } 
	    //购买页面
	    @RequestMapping(value = "/shoppingtogo",method = RequestMethod.GET)
	    public String shoppingtogo(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception
	    {
	   String classification = request.getParameter("classification");
	   String childids =request.getParameter("childid");
	   
	   if(childids==""){
		  
		   CommodityMall commodityType = _commodityMallRepository.findByCommodityType(classification.trim());
	    	List<ShoppingmallCommodity> shoppingmallCommodityList = commodityType.getShoppingmallCommodity();
	    	
	    	if(shoppingmallCommodityList.size()>0){
		    //	List<Child> child =_childRepository.findByUser(user);
				model.addAttribute("childs", "");
				model.addAttribute("child", "");	
			/*	model.addAttribute("shoppingmallCommodityList", shoppingmallCommodityList);	*/
				List<ShoppingmallCommodity> list = new ArrayList<ShoppingmallCommodity>();
				for(int i=0;i<shoppingmallCommodityList.size();i++){
					ShoppingmallCommodity sshoppingmallCommodityList = new ShoppingmallCommodity();
					sshoppingmallCommodityList.setCommodityName(shoppingmallCommodityList.get(i).getCommodityName());
					sshoppingmallCommodityList.setCommodityMall(shoppingmallCommodityList.get(i).getCommodityMall());
					sshoppingmallCommodityList.setCreateTime(shoppingmallCommodityList.get(i).getCreateTime());
					sshoppingmallCommodityList.setDemandLevel(shoppingmallCommodityList.get(i).getDemandLevel());
					sshoppingmallCommodityList.setDescription(shoppingmallCommodityList.get(i).getDescription());
					sshoppingmallCommodityList.setPrice(shoppingmallCommodityList.get(i).getPrice());
					if(_shoppingmallCommodityImageRepository.findByShoppingmallCommodityId(shoppingmallCommodityList.get(i).getId()).size()==0){
						sshoppingmallCommodityList.setCommodityQuantity(0);
					}else{
						sshoppingmallCommodityList.setCommodityQuantity(_shoppingmallCommodityImageRepository.findByShoppingmallCommodityId(shoppingmallCommodityList.get(i).getId()).get(0).getId());
					}
					sshoppingmallCommodityList.setId(shoppingmallCommodityList.get(i).getId());
					list.add(sshoppingmallCommodityList);
				}
				model.addAttribute("shoppingmallCommodityList", list);	
				/*ShoppingmallCommodityImage image = _shoppingmallCommodityImageRepository.findByShoppingmallCommodityId();
				model.addAttribute("shoppingmallCommodityimage", );*/
				model.addAttribute("pagesize", shoppingmallCommodityList.size());/*
				
				 model.addAttribute("flagvalue", child.get(0).getId());*/
				
				 model.addAttribute("flagvalue", "");
				
	    	}else{
	    	//	List<Child> child =_childRepository.findByUser(user);
				model.addAttribute("childs", "");
				model.addAttribute("child", "");	
				model.addAttribute("shoppingmallCommodityList", null);	
				
				model.addAttribute("pagesize", 1);
				 model.addAttribute("flagvalue", "");
	    	}
	     
	    	
	        		 model.addAttribute("sumprice", 0);
	        	
	   }else{
		   Integer childid =Integer.parseInt(request.getParameter("childid"));
		   CommodityMall commodityType = _commodityMallRepository.findByCommodityType(classification.trim());
	    	List<ShoppingmallCommodity> shoppingmallCommodityList = commodityType.getShoppingmallCommodity();
	    	User user = (User) request.getSession().getAttribute("user");
	    	if(shoppingmallCommodityList.size()>0){
		    	List<Child> child =_childRepository.findByUser(user);
				model.addAttribute("childs", child);
				model.addAttribute("child", child);	
				List<ShoppingmallCommodity> list = new ArrayList<ShoppingmallCommodity>();
				for(int i=0;i<shoppingmallCommodityList.size();i++){
					ShoppingmallCommodity sshoppingmallCommodityList = new ShoppingmallCommodity();
					sshoppingmallCommodityList.setCommodityName(shoppingmallCommodityList.get(i).getCommodityName());
					sshoppingmallCommodityList.setCommodityMall(shoppingmallCommodityList.get(i).getCommodityMall());
					sshoppingmallCommodityList.setCreateTime(shoppingmallCommodityList.get(i).getCreateTime());
					sshoppingmallCommodityList.setDemandLevel(shoppingmallCommodityList.get(i).getDemandLevel());
					sshoppingmallCommodityList.setDescription(shoppingmallCommodityList.get(i).getDescription());
					sshoppingmallCommodityList.setPrice(shoppingmallCommodityList.get(i).getPrice());
					if(_shoppingmallCommodityImageRepository.findByShoppingmallCommodityId(shoppingmallCommodityList.get(i).getId()).size()==0){
						sshoppingmallCommodityList.setCommodityQuantity(0);
					}else{
						sshoppingmallCommodityList.setCommodityQuantity(_shoppingmallCommodityImageRepository.findByShoppingmallCommodityId(shoppingmallCommodityList.get(i).getId()).get(0).getId());
					}
					sshoppingmallCommodityList.setId(shoppingmallCommodityList.get(i).getId());
					list.add(sshoppingmallCommodityList);
				}
				model.addAttribute("shoppingmallCommodityList", list);	
				model.addAttribute("pagesize", shoppingmallCommodityList.size());/*
				 model.addAttribute("flagvalue", child.get(0).getId());*/
				 model.addAttribute("flagvalue", childid);
				
				
	    	}else{
	    		List<Child> child =_childRepository.findByUser(user);
				model.addAttribute("childs", child);
				model.addAttribute("child", child);	
				model.addAttribute("shoppingmallCommodityList", null);	
				model.addAttribute("pagesize", 1);
				 model.addAttribute("flagvalue", "");
	    	}
	     
	    		 BigDecimal sum = _IfbrainIndexReponsitory.findSumPriceByChildIds(childid);
	    		 if(sum==null){
	        		 model.addAttribute("sumprice", 0);
	        	 }else{
	        		 model.addAttribute("sumprice", sum);
	        	 }
	   }
	  
    	
    	
	    	 return "/demandcityshopping/shoppingtogo";
	    } 
	    //购买商品
	    @ResponseBody
	    @RequestMapping(value = "/buy",method = RequestMethod.GET)
	    public String buy(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	String commodityid = request.getParameter("commodityid");
	    	String quanlity = request.getParameter("quanlity");
	    	String childid = request.getParameter("childid");
	    	Child child = _childRepository.findOne(Integer.parseInt(childid));
	    	ShoppingmallCommodity commodity = _shoppingmallCommodityRepository.findOne(Integer.parseInt(commodityid));
	    	ChildShoppingmall  shoppingmall=new ChildShoppingmall();
	    	shoppingmall.setChild(child);
	    	shoppingmall.setCreateTime(new Date());
	    	shoppingmall.setResult((new BigDecimal(quanlity).multiply(commodity.getPrice())).toString());
	    	shoppingmall.setShoppingmallCommodity(commodity);
	    	shoppingmall.setSumPrice(new BigDecimal(quanlity).multiply(commodity.getPrice()));
	    	shoppingmall.setPayStatus(1);
	    	shoppingmall.setQuantity(Integer.parseInt(quanlity));
	    
	    	List<IfbrainIndex> findifbrainlist = _IfbrainIndexReponsitory.findByIfbrainIdDesc(child.getId());
	    	if(findifbrainlist!=null){
	    		shoppingmall.setCourseLevel(findifbrainlist.get(0).getCourseLevel());
		    	shoppingmall.setOrdinalNumber(findifbrainlist.get(0).getOrdinalNumber());
		    	_childShoppingmallRepository.save(shoppingmall);
	    	}else{
	    		_childShoppingmallRepository.save(shoppingmall);
	    	}
	    	
	    	if(findifbrainlist.get(0).getExpense().intValue()==0){
	    		
	    		
	    		 BigDecimal	expense =new BigDecimal(Integer.parseInt(quanlity)).multiply(commodity.getPrice());
	    		findifbrainlist.get(0).setExpense(expense);
	    		findifbrainlist.get(0).setBalance(findifbrainlist.get(0).getBalance().subtract(expense));
	    		
	    		_IfbrainIndexReponsitory.save(findifbrainlist.get(0));
	    	}else{
	    		//原来的expense
	    		BigDecimal oldexpense = findifbrainlist.get(0).getExpense();
	    		//计算expense
	    		BigDecimal	expense =new BigDecimal(Integer.parseInt(quanlity)).multiply(commodity.getPrice());
	    		findifbrainlist.get(0).setExpense(oldexpense.add(expense));
	    		findifbrainlist.get(0).setBalance(findifbrainlist.get(0).getBalance().subtract(oldexpense.add(expense)));	
	    		_IfbrainIndexReponsitory.save(findifbrainlist.get(0));
	    	}
	    	 //计算代币余额
			 //总收入
			/* BigDecimal sum = _IfbrainIndexReponsitory.findSumPriceByChildId(Integer.parseInt(childid));
			 //减去支出,算出余额
			 child.setBalance(sum.subtract((new BigDecimal(quanlity).multiply(commodity.getPrice()))));
			 _childRepository.save(child);*/
	    	String result="购买成功!";
	    	return  result;
	    } 
	    
	    //购买商品
	   /* @ResponseBody
	    @RequestMapping(value = "/findSumpriceById",method = RequestMethod.GET)
	    public String findSumpriceById(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	String id = request.getParameter("id");
	    	Child child = _childRepository.findOne(Integer.parseInt(id));
	    	if(child.getBalance()==null||child.getBalance().toString()=="0"){
		    	return _IfbrainIndexReponsitory.findSumPriceByChildIds(Integer.parseInt(id)).toString();
	    	}else{
	    		 child = _childRepository.findOne(Integer.parseInt(id));
		    	return child.getBalance().toString();
	    	}
	    	
	    } */
	    
	   
}
