package com.jzeen.travel.website.controller.familyincomeassement;
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
 * 家庭收入
 * @author sunyan
 *
 */
@Controller
@RequestMapping("/familyincomeassement")
public class FamilyIncomeAssementController
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
   @RequestMapping(value = "/familyincome",method = RequestMethod.GET)
   public  String familyincome(Model model, HttpServletRequest request,HttpSession session)
   {
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlist =_cChildRepository.findByUser(user);
	   StringBuffer str = new StringBuffer();
       if(childlist.size()>0){
    	   Integer childid =Integer.parseInt(request.getParameter("childId"));
    	 List <Course> course = _cCourseReponsitory.findAll();
		model.addAttribute("course", course);
    	model.addAttribute("childs", childlist);
   	    model.addAttribute("flagvalue",childid);
		Child child = _cChildRepository.findOne(childid);
	
		//获得总的任务数
		List<DefineTask> historyTaskList = child.getDefineTask();
		if(historyTaskList.size()>0){
			for (int i = 0; i < historyTaskList.size(); i++) {
				Integer taskTimesaddtime = historyTaskList.get(i).getTimesAdd();
				Integer taskTimes = historyTaskList.get(i).getTaskTimes();
				BigDecimal rate = new BigDecimal(taskTimesaddtime).divide(
						new BigDecimal(taskTimes), 2, BigDecimal.ROUND_HALF_UP);
				NumberFormat nt = NumberFormat.getPercentInstance();
				String taskpercent = nt.format(rate.doubleValue());
				str.append(taskpercent);
				str.append(",");
			}
			model.addAttribute("historyList", historyTaskList);
			model.addAttribute("percent2", str.substring(0, str.length() - 1));
		//总的代币数  	
		BigDecimal AllTokennumbers= _defineTaskTaskRepository.findByTokennumbers(child.getId());
	    //已完成的任务数
		List<DefineTask> finish = _defineTaskTaskRepository.findByChildIdAndTaskstatusAndTimesAdd(child.getId(), 1);
		if(finish.size()>0){
		    //已获得的代币数
			BigDecimal getTokennumbers= _defineTaskTaskRepository.findSumTokenByChildIdAndTaskstatus(child.getId(),1);
			//计算完成的任务任务次数，以及完成任务占总任务的百分比
			Integer finishsize = finish.size();
			BigDecimal rate1 = new BigDecimal(finishsize).divide(new BigDecimal(historyTaskList.size()),2,BigDecimal.ROUND_HALF_UP);
			NumberFormat nt1 = NumberFormat.getPercentInstance();
			String percent1 = nt1.format(rate1.doubleValue());

			
			if(getTokennumbers.intValue()==0){
				model.addAttribute("percent", 0);
			}else{
				BigDecimal rate2 = getTokennumbers.divide(AllTokennumbers,2,BigDecimal.ROUND_HALF_UP);
				NumberFormat nt2 = NumberFormat.getPercentInstance();
				String percent2 = nt2.format(rate2.doubleValue());
				model.addAttribute("percent", percent2);
			}
			
			//计算已获得的代币数,以及已获得的代币数占总代币数的百分比
			
			//计算完成的任务任务次数，以及完成任务占总任务的百分比
			model.addAttribute("percent1", percent1);
			model.addAttribute("finishtimes", finishsize);
			model.addAttribute("alltimes",historyTaskList.size());
			//计算已获得的代币数,以及已获得的代币数占总代币数的百分比
		
			model.addAttribute("finishtoken", getTokennumbers);
			model.addAttribute("alltoken",AllTokennumbers);
		}else{
			//计算完成的任务任务次数，以及完成任务占总任务的百分比
			model.addAttribute("percent1", 0+"%");
			model.addAttribute("finishtimes", 0);
			model.addAttribute("alltimes",historyTaskList.size());
			//计算已获得的代币数,以及已获得的代币数占总代币数的百分比
			model.addAttribute("percent", 0+"%");
			model.addAttribute("finishtoken", 0);
			model.addAttribute("alltoken",AllTokennumbers);
		   }
		  }
		else{
			//计算完成的任务任务次数，以及完成任务占总任务的百分比
			model.addAttribute("percent1", 0+"%");
			model.addAttribute("finishtimes", 0);
			model.addAttribute("alltimes",0);
			//计算已获得的代币数,以及已获得的代币数占总代币数的百分比
			model.addAttribute("percent", 0+"%");
			model.addAttribute("finishtoken", 0);
			model.addAttribute("alltoken",0);
			
			model.addAttribute("percent2",  0+"%");
		  }
		
     /*  //统计支出(zy)环形图
       List<Object[]> shophistory = _shopHistoryRepository.findshopByChildId(childid);	
		  StringBuffer nameBuffer=new StringBuffer();
			JSONArray jsonArray=new JSONArray();
			//历史购物>0
						if(shophistory.size()>0){
						for(int i=0;i<shophistory.size();i++){
							String name=(String)shophistory.get(i)[0];
							StringBuffer n=new StringBuffer();
							n.append("'");
							BigDecimal price=(BigDecimal)shophistory.get(i)[1];
							BigDecimal sunprices=_shopHistoryRepository.findshopSumprice(childid);
							NumberFormat PercentFormat = NumberFormat.getPercentInstance();
							n.append(name);
							nameBuffer.append(name);
							nameBuffer.append("("+PercentFormat.format(price.doubleValue()/sunprices.doubleValue())+")");
							nameBuffer.append(",");
							JSONObject json=new JSONObject();
							json.put("value", price.doubleValue());
							json.put("name",n.toString()+"("+PercentFormat.format(price.doubleValue()/sunprices.doubleValue())+")"+"'" );
							jsonArray.put(json);
						     }
						String first=jsonArray.toString().replaceAll("\"", "");
						model.addAttribute("shopname",nameBuffer.substring(0, nameBuffer.length()-1));
						model.addAttribute("shopnameandprice",first);
						
						//支出页面柱状图(zy)
						//购物支出(zy)
						BigDecimal sumshopprice =_shopHistoryRepository.findshopSumprice(childid);
						//购物支出>0
							if(sumshopprice==null){
								model.addAttribute("sumshopprice", 0);
								//代币收入
								List <DefineTask> timesAdd=_defineTaskTaskRepository.findByTaskTimes(childid);
								if(timesAdd.size()>0){
									for(int i=0;i<timesAdd.size();i++){
										BigDecimal sumtokenprice= _defineTaskTaskRepository.findSumTokenByChildIdAndTaskstatus(child.getId(),1);
									//	BigDecimal sumtokenprice =_defineTaskTaskRepository.findSumTokenByChildId( childlist.get(0).getId(),1,timesAdd.get(i).getTaskTimes());
								
											if(sumtokenprice==null){
													model.addAttribute("sumtokenprice",0);
													//现有代币(zy)
													BigDecimal nowshopprice=sumtokenprice.subtract(sumshopprice);
													model.addAttribute("nowshopprice", nowshopprice);
													//百分比
													NumberFormat PercentFormat = NumberFormat.getPercentInstance();
													String taskshoppercent=PercentFormat.format(sumshopprice.doubleValue()/sumtokenprice.doubleValue());
													model.addAttribute("taskshoppercent", taskshoppercent);
															}else{
													model.addAttribute("sumtokenprice",sumtokenprice);
													//现有代币(zy)
													BigDecimal nowshopprice=sumtokenprice.subtract(sumshopprice);
													model.addAttribute("nowshopprice", nowshopprice);
													//百分比
													NumberFormat PercentFormat = NumberFormat.getPercentInstance();
													String taskshoppercent=PercentFormat.format(sumshopprice.doubleValue()/sumtokenprice.doubleValue());
													model.addAttribute("taskshoppercent", taskshoppercent);
													}
									}
								}
								
								
									}else{
								model.addAttribute("sumshopprice", sumshopprice);
								//代币收入
								List <DefineTask> timesAdd=_defineTaskTaskRepository.findByTaskTimes( childid);
								if(timesAdd.size()>0){
									for(int i=0;i<timesAdd.size();i++){
										BigDecimal sumtokenprice= _defineTaskTaskRepository.findSumTokenByChildIdAndTaskstatus(child.getId(),1);
									//	BigDecimal sumtokenprice =_defineTaskTaskRepository.findSumTokenByChildId( childlist.get(0).getId(),1,timesAdd.get(i).getTaskTimes());
										if(sumtokenprice==null){
											model.addAttribute("sumtokenprice",0);
											//现有代币(zy)
											BigDecimal nowshopprice=sumtokenprice;
											model.addAttribute("nowshopprice", 0);
											//百分比			
											model.addAttribute("taskshoppercent", 0+"%");
											}else{
										model.addAttribute("sumtokenprice",sumtokenprice);
										//现有代币(zy)
										BigDecimal nowshopprice=sumtokenprice.subtract(sumshopprice);
										model.addAttribute("nowshopprice", nowshopprice);
										//百分比
										NumberFormat PercentFormat = NumberFormat.getPercentInstance();
										String taskshoppercent=PercentFormat.format(sumshopprice.doubleValue()/sumtokenprice.doubleValue());
										model.addAttribute("taskshoppercent", taskshoppercent);
										}
									}
								}
							
									
							 }
				}
			//历史购物<0
				else{
					model.addAttribute("shopname","");
					model.addAttribute("shopnameandprice","");
					//现有代币(zy)
					//历史任务<1
						if(historyTaskList.size()<1){
							List <DefineTask> timesAdd=_defineTaskTaskRepository.findByTaskTimes(childid);
							if(timesAdd.size()>0){
								for(int i=0;i<timesAdd.size();i++){
									BigDecimal sumtokenprice= _defineTaskTaskRepository.findSumTokenByChildIdAndTaskstatus(child.getId(),1);
									//		BigDecimal sumtokenprice =_defineTaskTaskRepository.findSumTokenByChildId( childlist.get(0).getId(),1,timesAdd.get(i).getTaskTimes());
									
									if(sumtokenprice==null){
										//代币收入==null
										model.addAttribute("sumtokenprice",0);
										}else{
										//代币收入!=null
										model.addAttribute("sumtokenprice",sumtokenprice);
									}
								model.addAttribute("sumshopprice", 0);
								model.addAttribute("nowshopprice", 0);
								model.addAttribute("taskshoppercent", 0+"%");
								}
							}
							
							}else{
							//代币收入
								List <DefineTask> timesAdd=_defineTaskTaskRepository.findByTaskTimes(childid);
								if(timesAdd.size()>0){
									for(int i=0;i<timesAdd.size();i++){
										BigDecimal sumtokenprice= _defineTaskTaskRepository.findSumTokenByChildIdAndTaskstatus(child.getId(),1);
											//BigDecimal sumtokenprice =_defineTaskTaskRepository.findSumTokenByChildId( childlist.get(0).getId(),1,timesAdd.get(i).getTaskTimes());
											if(sumtokenprice==null){
												model.addAttribute("sumtokenprice",0);	
												model.addAttribute("sumshopprice", 0);
												model.addAttribute("nowshopprice", 0);
												model.addAttribute("taskshoppercent", 0+"%");
												}else{
											model.addAttribute("sumtokenprice",sumtokenprice);
											BigDecimal sumshopprice=_shopHistoryRepository.findshopSumprice(childid);
											if(sumshopprice==null){
												model.addAttribute("sumshopprice",0);
												model.addAttribute("sumtokenprice",sumtokenprice);
												model.addAttribute("nowshopprice", sumtokenprice);
												model.addAttribute("taskshoppercent", 0+"%");
											}else{
											model.addAttribute("sumshopprice",sumshopprice);
											model.addAttribute("sumtokenprice",sumtokenprice);
											BigDecimal nowshopprice=sumtokenprice.subtract(sumshopprice);
											model.addAttribute("nowshopprice", nowshopprice);
											NumberFormat PercentFormat = NumberFormat.getPercentInstance();
											String taskshoppercent=PercentFormat.format(sumshopprice.doubleValue()/sumtokenprice.doubleValue());
											model.addAttribute("taskshoppercent", taskshoppercent);
											}
											}
									}
								}
						}
					//百分比
				}*/
			//支出页面历史购物(zy)
			List<ShopHistory> shopHistories = _shopHistoryRepository.findByChildIdss(childid);
			model.addAttribute("shopHistories",shopHistories);  
       }else{
    	   model.addAttribute("flagvalue","");
    	   model.addAttribute("childs", null);
    	   model.addAttribute("showvideolist", "");
       }
	   return "/assessment/familyincome";
   }

    	
  	
}
