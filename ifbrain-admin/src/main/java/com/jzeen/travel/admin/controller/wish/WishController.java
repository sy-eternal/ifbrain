package com.jzeen.travel.admin.controller.wish;



import java.math.BigDecimal;
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
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseCode;
import com.jzeen.travel.data.entity.QChildShoppingmall;
import com.jzeen.travel.data.entity.QStandardGuidePlan;
import com.jzeen.travel.data.entity.ShoppingmallCommodity;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.ChildShoppingmallRepository;
import com.jzeen.travel.data.repository.CommodityMallRepository;
import com.jzeen.travel.data.repository.ShoppingmallCommodityRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/wish")
public class WishController
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
	private UserRepository  _userRepository;
	//查询孩子的心愿清单
	@RequestMapping(value = "/searchxinyuan", method = RequestMethod.GET)
    public String create(HttpServletRequest request, Model model)
    {
		List<User> userList = _userRepository.findAll();
		model.addAttribute("userList", userList);
        return "/wish/search";
     }
	
	 @RequestMapping(value = "/index",method = RequestMethod.POST)
	 public String index(HttpServletRequest request,HttpServletResponse response,Model model){
		 String childid = request.getParameter("ChildName");
		 model.addAttribute("childid", childid);
		 return  "/wish/list";
	 }
	//datatable列表显示
		@ResponseBody
	    @RequestMapping(value = "/search", method = RequestMethod.GET)
	    public Iterable<ChildShoppingmall> search(HttpServletRequest request)
	    {
			Integer childid = Integer.parseInt(request.getParameter("childid"));
	        QChildShoppingmall childShoppingmall = QChildShoppingmall.childShoppingmall;
	        Predicate predicate = childShoppingmall.child.id.eq(childid).and(childShoppingmall.type.eq("0"));
			Iterable<ChildShoppingmall> list = _childShoppingmallRepository.findAll(predicate);
	        return list;
	    }
	//心愿清单
	 //初次进入页面的方法
	 @RequestMapping(value = "/initwish",method = RequestMethod.GET)
	 public String initwish(HttpServletRequest request,HttpServletResponse response,Model model){
		    User user = (User) request.getSession().getAttribute("user");
			if(null==_childRepository.findByUser(user) || _childRepository.findByUser(user).size()==0){
				 model.addAttribute("childShoppingmall", "");
				 model.addAttribute("childs", "");
			}else{
				List<Child> childs = _childRepository.findByUser(user);
				List<ChildShoppingmall> childShoppingmall = childs.get(0).getChildShoppingmall();
				model.addAttribute("childShoppingmall", childShoppingmall);
				model.addAttribute("flagvalue", childs.get(0).getId());
				model.addAttribute("childs", childs);
			}
		 return  "/wish/wish";
	 }
	    @RequestMapping(value = "/createinfo", method = RequestMethod.POST)
	    public String create(Model model,HttpServletRequest request) throws Exception
	    {
		  String commodityName = request.getParameter("commodityName");
		  String price = request.getParameter("price");
		  String childid = request.getParameter("childid");
		  Child child = _childRepository.findOne(Integer.parseInt(childid));
		  ChildShoppingmall  childShoppingmall=new ChildShoppingmall();
		  childShoppingmall.setPrice(new BigDecimal(price));
		  childShoppingmall.setCommodityName(commodityName);
		  childShoppingmall.setType("0");
		  childShoppingmall.setPayStatus(0);
		  childShoppingmall.setChild(child);
		  _childShoppingmallRepository.save(childShoppingmall);
	        return "redirect:/wish/wish?childid="+childid;
	     }
	 //进去后点击孩子的跳转页面的方法
	 @RequestMapping(value = "/wish",method = RequestMethod.GET)
	 public String wish(HttpServletRequest request,HttpServletResponse response,Model model){
		 String childid = request.getParameter("childid");
		 model.addAttribute("flagvalue", childid);
		 if(childid==""){
			 model.addAttribute("childShoppingmall", "");
		 }else{
			 Child child = _childRepository.findOne(Integer.parseInt(childid));
			 List<ChildShoppingmall> childShoppingmall = child.getChildShoppingmall();
			 model.addAttribute("childShoppingmall", childShoppingmall);
		 }
		 //查询所有的孩子
		 User user = (User) request.getSession().getAttribute("user");
		 List<Child> childs =_childRepository.findByUser(user);
		 model.addAttribute("childs", childs);
		 return  "/wish/wish";
	 }
	 //财脑商城
	 //初次进入商城
	 @RequestMapping(value = "/initshangcheng",method = RequestMethod.GET)
	 public String initshangcheng(HttpServletRequest request,HttpServletResponse response,Model model){
		    User user = (User) request.getSession().getAttribute("user");
		    String childid="";
			if(null==_childRepository.findByUser(user) || _childRepository.findByUser(user).size()==0){
				 model.addAttribute("childs", "");
				 model.addAttribute("flagvalue", "");
			}else{
				List<Child> childs = _childRepository.findByUser(user);
				if(childs.size()<0){
					childid=childs.get(0).getId().toString();
					model.addAttribute("flagvalue", "");
					model.addAttribute("childs", "");
				}else{
				childid=childs.get(0).getId().toString();
				model.addAttribute("flagvalue", childs.get(0).getId());
				model.addAttribute("childs", childs);
				}
			}
		 return  "redirect:/demandcityshopping/maslowdemand?childid="+childid;
	 }
	 //商品详情
	    @RequestMapping(value = "/demendwishlist",method = RequestMethod.GET)
	    public String demendwishlist(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	String shoppingmallCommodityid =request.getParameter("shoppingmallCommodityid");
	    	String childId = request.getParameter("childid");
	    	User user = (User) request.getSession().getAttribute("user");
	    	//获取用户
	    	ShoppingmallCommodity commdity = _shoppingmallCommodityRepository.findOne(Integer.parseInt(shoppingmallCommodityid));
	    	model.addAttribute("commdity", commdity);
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
	    	 return "/wish/demendwhishlist";
	    }
	  //我的订单
	    @RequestMapping(value = "/mywhishlist",method = RequestMethod.GET)
	    public String mywhishlist(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	String childId = request.getParameter("childid");
	    	if(childId.equals("")){
	    		model.addAttribute("childs", "");
				model.addAttribute("child", "");	
	    		model.addAttribute("flagvalue", "");
	    	}
	    	else{
	    		User user = (User) request.getSession().getAttribute("user");
				List<Child> child =_childRepository.findByUser(user);
				model.addAttribute("childs", child);
				model.addAttribute("child", child);	
	    		model.addAttribute("flagvalue", childId);
	    		Child childs = _childRepository.findOne(Integer.parseInt(childId));
	    	//	List<ChildShoppingmall> childShoppingmalllist= childs.getChildShoppingmall();
	    		List<ChildShoppingmall> childShoppingmalllist= _childShoppingmallRepository.findByDemandlevelIdAndChildIdsType(Integer.parseInt(childId), "1");
		  		if(childShoppingmalllist.size()>0){
		  			for(int i=0;i<childShoppingmalllist.size();i++){
		  				if(childShoppingmalllist.get(i).getQuantity()!=null&&childShoppingmalllist.get(i).getShoppingmallCommodity().getPrice()!=null){
		  				
		  					childShoppingmalllist.get(i).setResult(new BigDecimal(childShoppingmalllist.get(i).getQuantity()).multiply(childShoppingmalllist.get(i).getShoppingmallCommodity().getPrice()).toString());
		  					_childShoppingmallRepository.save(childShoppingmalllist.get(i));
		  				}else{
		  					childShoppingmalllist.get(i).setResult("");
		  				}
		  			}
		  			model.addAttribute("childShoppingmalllist", childShoppingmalllist);
		  		}else{
		  			model.addAttribute("childShoppingmalllist", "");
		  		}
	    	}
	    	 return "/wish/mywhishlist";
	    }
	    
	    
	  //商品详情
	    @RequestMapping(value = "/addwishlist",method = RequestMethod.GET)
	    public String addwishlist(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	String commdityid =request.getParameter("commdityid");
	    	String childId = request.getParameter("childId");
	    	User user = (User) request.getSession().getAttribute("user");
	    	Child childs = _childRepository.findOne(Integer.parseInt(childId));
	    	ShoppingmallCommodity commditty = _shoppingmallCommodityRepository.findOne(Integer.parseInt(commdityid));
	    	
	    
	    	 ChildShoppingmall  childshopingmall=new ChildShoppingmall();
			 childshopingmall.setChild(childs);
			 childshopingmall.setSumPrice(commditty.getPrice());
			 childshopingmall.setCreateTime(new Date());
			 childshopingmall.setShoppingmallCommodity(commditty);
			 childshopingmall.setResult(commditty.getPrice().toString());
			 childshopingmall.setPayStatus(0);
			 childshopingmall.setQuantity(1);
			 childshopingmall.setType("1");
			 childshopingmall.setCommodityName(commditty.getCommodityName());
			 childshopingmall.setPrice(commditty.getPrice());
			 _childShoppingmallRepository.save(childshopingmall);
	    	
	    	
	    	 return "redirect:/wish/demendwishlist?shoppingmallCommodityid="+commdityid+"&childid="+childId;
	    }
  
}
