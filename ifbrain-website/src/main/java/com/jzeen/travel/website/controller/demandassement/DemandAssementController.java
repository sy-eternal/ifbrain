package com.jzeen.travel.website.controller.demandassement;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.jzeen.travel.data.entity.DefineTask;
import com.jzeen.travel.data.entity.IfbrainIndex;
import com.jzeen.travel.data.entity.ShopHistory;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.Video;
import com.jzeen.travel.data.repository.BuyPlanRepository;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.ChildShoppingmallRepository;
import com.jzeen.travel.data.repository.CommodityRepository;
import com.jzeen.travel.data.repository.CommodityTypeRepository;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.CustomCommodityRepository;
import com.jzeen.travel.data.repository.DefineTaskRepository;
import com.jzeen.travel.data.repository.EasyTaskRepository;
import com.jzeen.travel.data.repository.IfbrainIndexReponsitory;
import com.jzeen.travel.data.repository.IfbrainVarReponsitory;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.MoneyRepository;
import com.jzeen.travel.data.repository.ShopHistoryRepository;
import com.jzeen.travel.data.repository.ShoppingmallCommodityRepository;
import com.jzeen.travel.data.repository.TaskRepository;
import com.jzeen.travel.data.repository.VideoReponsitory;
import com.jzeen.travel.data.repository.WorkTaskRelateRepository;
import com.jzeen.travel.website.setting.FileUploadSetting;
/**
 * 需求评估
 * @author sunyan
 *
 */
@Controller
@RequestMapping("/demandassement")
public class DemandAssementController
{
    @Autowired
    private ImageRepository _imageRepository;
    @Autowired
    private ChildRepository _cChildRepository;
    @Autowired
    FileUploadSetting _fileUploadSetting;
    @Autowired
    private WorkTaskRelateRepository _worktaskRepository;
    @Autowired
	ChildShoppingmallRepository _childShoppingmallRepository;
    @Autowired
    private TaskRepository _taskRepository;
    @Autowired
    private  EasyTaskRepository _EasyCommdityRepository;
    @Autowired
    private  MoneyRepository _MoneyRepository;
    @Autowired
    private  ShopHistoryRepository _shopHistoryRepository;
    @Autowired
    private   BuyPlanRepository _buyPlanRepository;
    @Autowired
    private   CustomCommodityRepository _CustomCommodityRepository;
    @Autowired
    private  DefineTaskRepository _defineTaskTaskRepository;
    @Autowired
	private CommodityRepository _commodityRepository;
    @Autowired
	ShoppingmallCommodityRepository  _shoppingmallCommodityRepository;
	@Autowired
	private CommodityTypeRepository _commodityTypeRepository;
	
	 @Autowired
		private IfbrainIndexReponsitory _iIfbrainIndexReponsitory;
	 @Autowired
		private VideoReponsitory _viVideoReponsitory;
	 @Autowired
		private CourseCodeRepository _ccourseCodeRepository;

	 @Autowired
		private CourseReponsitory _cCourseReponsitory;
	 @Autowired
		private IfbrainVarReponsitory _IfbrainVarReponsitory;
	 @Autowired
		private ChildShoppingmallRepository _cChildShoppingmallRepository;
	 
   //
   @RequestMapping(value = "/demand",method = RequestMethod.GET)
   public  String demand(Model model, HttpServletRequest request,HttpSession session)
   {
	   

	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlist =_cChildRepository.findByUser(user);
       if(childlist.size()>0){
    	   List <Course> course = _cCourseReponsitory.findAll();
		model.addAttribute("course", course);
    	model.addAttribute("childs", childlist);
    	 Integer childlists=Integer.parseInt(request.getParameter("childId"));
   	    model.addAttribute("flagvalue", childlists);
		Child child = _cChildRepository.findOne(childlists);
		//判断需求所占百分比
		 //默认选中第一节课
		if(course.size()>0){
			 List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlists,course.get(0).getCourseLevel());
		      model.addAttribute("ifbrainindex",ifbrainindex);
		      model.addAttribute("courseid", course.get(0).getId());

			 
			 //商品分类环形图
		 		StringBuffer strcommodity=new StringBuffer();
		 		List<Object[]> commodity = _cChildShoppingmallRepository.findByChildIdAndCommdity(child.getId(),"1",1);
		 		JSONArray commodityty=new JSONArray();
		 		if(commodity.size()>0){
		 		for(int i=0;i<commodity.size();i++){
		 			strcommodity.append(commodity.get(i)[1]);
		 			strcommodity.append(",");
		 			JSONObject commoditytype=new JSONObject();
		 			BigDecimal valuecommodity=(BigDecimal)commodity.get(i)[0];
		 			commoditytype.put("value",valuecommodity.toString());
		 			commoditytype.put("name", "'"+commodity.get(i)[1].toString()+"'");
		 			commodityty.put(commoditytype);
		 		  }
		 		model.addAttribute("commoditytypename", strcommodity.toString().subSequence(0, strcommodity.toString().length()));
		 		model.addAttribute("commoditytypevalue", commodityty.toString().replace("\"", "")); 
		 		}else{
		 			model.addAttribute("commoditytypename", "");
			 		model.addAttribute("commoditytypevalue", ""); 
		 		}
		 		
		 		//List<ChildShoppingmall> childShoppingmalllist= child.getChildShoppingmall();
		  		
		 		List<ChildShoppingmall> childShoppingmalllist= _childShoppingmallRepository.findByDemandlevelIdAndChildIdsType(child.getId(), "1");
		 		if(childShoppingmalllist.size()>0){
		  			for(int i=0;i<childShoppingmalllist.size();i++){
		  				if(childShoppingmalllist.get(i).getQuantity()!=null&&childShoppingmalllist.get(i).getShoppingmallCommodity().getPrice()!=null){
		  				
		  					childShoppingmalllist.get(i).setResult(new BigDecimal(childShoppingmalllist.get(i).getQuantity()).multiply(childShoppingmalllist.get(i).getShoppingmallCommodity().getPrice()).toString());
		  					_cChildShoppingmallRepository.save(childShoppingmalllist.get(i));
		  				}else{
		  					childShoppingmalllist.get(i).setResult("");
		  				}
		  			}
		  			model.addAttribute("childShoppingmalllist", childShoppingmalllist);
		  		}else{
		  			model.addAttribute("childShoppingmalllist", "");
		  		}
		 		//需求评估
			      List<Object[]> demandlevel = _cChildShoppingmallRepository.findDemandNameByChildId(child.getId(),"1",1);	
			      StringBuffer demandlevelnameBuffer1=new StringBuffer();
					JSONArray demandleveljsonArray1=new JSONArray();
					if(demandlevel.size()>0){
						for(int i=0;i<demandlevel.size();i++){
							String demandlevename=(String)demandlevel.get(i)[0];
							StringBuffer n2=new StringBuffer();
							n2.append("'");
							BigDecimal price=(BigDecimal)demandlevel.get(i)[1];
							BigDecimal sunprices=_cChildShoppingmallRepository.findDemandNameSumprice(child.getId(),"1",1);
							NumberFormat PercentFormat = NumberFormat.getPercentInstance();
							n2.append(demandlevename);
							demandlevelnameBuffer1.append(demandlevename);
							demandlevelnameBuffer1.append("("+PercentFormat.format(price.doubleValue()/sunprices.doubleValue())+")");
							demandlevelnameBuffer1.append(",");
							JSONObject json12=new JSONObject();
							json12.put("value", price.doubleValue());
							json12.put("name",n2.toString()+"("+PercentFormat.format(price.doubleValue()/sunprices.doubleValue())+")"+"'" );
							demandleveljsonArray1.put(json12);
						     }
						String first=demandleveljsonArray1.toString().replaceAll("\"", "");
						model.addAttribute("demandlevelnames",demandlevelnameBuffer1.substring(0, demandlevelnameBuffer1.length()-1));
						model.addAttribute("demandlevelnameandprice",first);
						BigDecimal sunprices=_cChildShoppingmallRepository.findDemandNameSumprice( child.getId(),"1",1);
						model.addAttribute("childsumprice", sunprices);
						}else{
							model.addAttribute("demandlevelnames","");
							model.addAttribute("demandlevelnameandprice","");
							model.addAttribute("childsumprice", 0);
						}
					//end需求评估
		 		
		 		
		 		
		 		//判断各个需求所占百分比
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
				_cChildRepository.save(child);
				model.addAttribute("type", child.getType());
				//判断需求所占百分比
		 		
			   
			   
		}     
		      
		}else{
			    model.addAttribute("ifbrainindex","");
			    model.addAttribute("childs", null);	
			    model.addAttribute("type", "");
			    model.addAttribute("childShoppingmalllist", "");
		}
       
	   return "/assessment/demend";
   
   }

     	
  	
}
