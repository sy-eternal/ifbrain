package com.jzeen.travel.website.controller.daibibankassement;
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
import com.jzeen.travel.data.entity.CourseClass;
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
import com.jzeen.travel.data.repository.CourseClassReponsitory;
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
 * 代币银行
 * @author sunyan
 *
 */
@Controller
@RequestMapping("/daibibankassement")
public class DaibibankAssementController
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
	 @Autowired
		private CourseClassReponsitory _cCourseClassReponsitory;
   //
   @RequestMapping(value = "/daibibank",method = RequestMethod.GET)
   public  String daibibank(Model model, HttpServletRequest request,HttpSession session)
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
   	 List<Object[]> ifbrainIndexFindByClass=_iIfbrainIndexReponsitory.findClassIdByChildId(childlists);
     if(ifbrainIndexFindByClass.size()>0){
	   List <CourseClass> listfindbyclass = new ArrayList<CourseClass> ();
	    if(ifbrainIndexFindByClass.size()>0){
	    	for(int i=0 ;i<ifbrainIndexFindByClass.size();i++){
	    			Integer varName=(Integer) ifbrainIndexFindByClass.get(i)[1];
	    		 List <CourseClass> courseclass = _cCourseClassReponsitory.findByclassId(varName);
	    		 listfindbyclass.add(courseclass.get(0));
	    	}
	    	model.addAttribute("listfindbyclass", listfindbyclass);
	    	model.addAttribute("listfindbyclassid", listfindbyclass.get(0).getId());
   	    
	    }else{
			  model.addAttribute("listfindbyclass", "");
			  model.addAttribute("listfindbyclassid", "");
		  }
   	    
   	    Child child = _cChildRepository.findOne(childlists);
		//判断需求所占百分比
		 //默认选中第一节课
		List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndClassId(childlists,listfindbyclass.get(0).getId());
		if(course.size()>0){
			 
		      model.addAttribute("ifbrainindex",ifbrainindex);
		      model.addAttribute("courseid", listfindbyclass.get(0).getCourseId().getId());
		}else{
			    model.addAttribute("ifbrainindex","");
		}
		  
	
		   if(ifbrainindex.size()>0){
			   IfbrainIndex ifbrain = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(ifbrainindex.get(0).getOrdinalNumber(),child.getId(),listfindbyclass.get(0).getCourseLevel());
			   model.addAttribute("ifbrainindexa", ifbrain);
			   //课后训练收入
			   model.addAttribute("ifbrainindexaafterincome", 0);
			
				   model.addAttribute("ifbrainindexbalance", 0);
				   System.out.println("++++++++++++++++++++++++OrdinalNumber  +"+ifbrainindex.get(0).getOrdinalNumber());
				   System.out.println("++++++++++++++++++++++++child  +"+child.getId());
				   System.out.println("++++++++++++++++++++++++getCourseLevel  +"+listfindbyclass.get(0).getCourseLevel());
			
			   //课堂知识加应用
			   BigDecimal total=new BigDecimal(0);
			   total=total.add(new BigDecimal(ifbrain.getApplicationtotalScore())).add(new BigDecimal(ifbrain.getKnowledgetotalScore()));
			    //折线图
			   List<IfbrainIndex> ifbrainIndexlist = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel3(child.getId(),listfindbyclass.get(0).getCourseLevel());
		  		JSONObject object=new JSONObject();
		  		//第一条折线财脑指数
					object.put("name", "'财脑指数'");
					object.put("type", "'line'");
					object.put("stack", "'总量'");
					JSONObject object1=new JSONObject();
					object1.put("type", "'default'");
					String stra = object1.toString();
					JSONObject object2=new JSONObject();
					object2.put("areaStyle",stra);
					String str1 = object2.toString();
					JSONObject object3=new JSONObject();
					object3.put("normal",str1);
					String str3 = object3.toString();
					object.put("itemStyle", str3); 		
			  	//财脑银行折线图
					
					
			  		 //代币总余额
			  			BigDecimal totalyue=new BigDecimal(0);
				  		List<IfbrainIndex> ifbrainIndexlistbank = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel3(child.getId(),listfindbyclass.get(0).getCourseLevel());
				  		StringBuffer bankname=new StringBuffer();
				  		String chartbankvalue="";
				  		JSONArray allarraybank=new JSONArray();
				  		int[] valuesbank=new int[ifbrainIndexlist.size()];
				  		JSONObject objectbank=new JSONObject();
			  		//第一条折线财脑指数
				  		objectbank.put("name", "'代币余额'");
				  		objectbank.put("type", "'line'");
				  		objectbank.put("stack", "'总量'");
						JSONObject objectbank1=new JSONObject();
						object1.put("type", "'default'");
						String strbank = objectbank1.toString();
						JSONObject objectbank2=new JSONObject();
						object2.put("areaStyle",strbank);
						String strbank1 = objectbank2.toString();
						JSONObject objectbank3=new JSONObject();
						object3.put("normal",strbank1);
						String strbank3 = objectbank3.toString();
						objectbank.put("itemStyle", strbank3);
						if(ifbrainIndexlist.size()>0){
			  		   for(int i=0;i<ifbrainIndexlist.size();i++){
			  			//曲线图横坐标
			  			 bankname.append("第"+ifbrainIndexlistbank.get(i).getOrdinalNumber()+"节");
			  			 bankname.append(",");
			  			/*totalyue=totalyue.add(ifbrainIndexlistbank.get(i).getIncome().subtract(ifbrainIndexlistbank.get(i).getExpense()));*/
			  			 if(ifbrainIndexlistbank.get(i).getBalance()==null){
			  				totalyue=totalyue.add(new BigDecimal(0));
			  			 }else{
			  			 totalyue=totalyue.add(ifbrainIndexlistbank.get(i).getBalance());
			  			 }
			  			
			  			
			  			//第一条曲线数据
			  			if(ifbrainIndexlistbank.get(i).getBalance()!=null){
			  				valuesbank[i]=(ifbrainIndexlistbank.get(i).getBalance()).intValue();
			  			}else{
			  				valuesbank[i]=0;
			  			}
			  		   }
						}
					objectbank.put("data",valuesbank);
				    chartbankvalue=allarraybank.put(objectbank).toString().replace("\"", "");
				    model.addAttribute("ifbrainindexnamebank",bankname.substring(0, bankname.length()-1).toString());
			  		model.addAttribute("ifbrainindexvaluebank",chartbankvalue);
                    model.addAttribute("totalyue", totalyue.toString());
                  //最后一节课的代币余额
		  			 List <IfbrainIndex>  indextotalyue = _iIfbrainIndexReponsitory.findByChildIdAndCourseLeveldesc(childlists,listfindbyclass.get(0).getCourseLevel());
		  			 model.addAttribute("indextotalyue", indextotalyue.get(0).getBalance());
                    
                   		   
				 
                    //代币银行支出收入比
				   //收入
				   List<Object[]> consumerpending =_iIfbrainIndexReponsitory.findByincome(childlists, listfindbyclass.get(0).getCourseLevel());
				   StringBuffer consumerpendingBuffer=new StringBuffer();
				   JSONArray jsonArrayconsumerpending=new JSONArray();
				   StringBuilder sb = new StringBuilder();
				   StringBuilder sb1 = new StringBuilder();
				   sb.append("[");
				   if(consumerpending.size()>0){
					   JSONObject jsonconsumerpending=new JSONObject();
					   jsonconsumerpending.put("name", "'收入'");
					   jsonconsumerpending.put("type", "'bar'");
					   jsonconsumerpending.put("stack", "'总量'");
					   BigDecimal[]  consumerpendingvalue=new BigDecimal[consumerpending.size()];
						for(int i=0;i<consumerpending.size();i++){
						Integer consumerpendingName=(Integer)consumerpending.get(i)[0];
						consumerpendingBuffer.append("第"+consumerpendingName+"周");
						consumerpendingBuffer.append(",");
						if(consumerpending.get(i)[1]==""){
							consumerpendingvalue[i]=new BigDecimal(0);
							sb.append(consumerpendingvalue[i]);
							 sb.append(",");
						}else{
							consumerpendingvalue[i]=(BigDecimal)consumerpending.get(i)[1];
							
							 sb.append(consumerpendingvalue[i]);
							 sb.append(",");
						 }
						}
						sb.append("]");
						jsonconsumerpending.put("data",sb.toString());
						jsonArrayconsumerpending.put(jsonconsumerpending);
						model.addAttribute("consumerpendingBuffer",consumerpendingBuffer.substring(0, consumerpendingBuffer.length()-1));
				   }else{
					   model.addAttribute("consumerpendingBuffer","");
				   }
				   
					
					 //支出
					List<Object[]> consumerpending2 =_iIfbrainIndexReponsitory.findByexpense(childlists, listfindbyclass.get(0).getCourseLevel());
				   sb1.append("[");
				   if(consumerpending2.size()>0){
					   JSONObject jsonconsumerpending2=new JSONObject();
					   jsonconsumerpending2.put("name", "'消费'");
					   jsonconsumerpending2.put("type", "'bar'");
					   jsonconsumerpending2.put("stack", "'总量'");
					   BigDecimal[] consumerpendingvalue2=new BigDecimal[consumerpending2.size()];
						for(int i=0;i<consumerpending2.size();i++){
							consumerpendingvalue2[i]=(BigDecimal)consumerpending2.get(i)[1];
							if(consumerpending2.get(i)[1]==""||consumerpending2.get(i)[1]==null){
								consumerpendingvalue2[i]=new BigDecimal(0);
								sb1.append(consumerpendingvalue2[i]);
								 sb1.append(",");
							}else{
								consumerpendingvalue2[i]=(BigDecimal)consumerpending2.get(i)[1];
								sb1.append(consumerpendingvalue2[i]);
								sb1.append(",");
							}
						     }
						sb1.append("]");
						jsonconsumerpending2.put("data",sb1.toString());
						jsonArrayconsumerpending.put(jsonconsumerpending2);
						 model.addAttribute("consumerpendingvalue",jsonArrayconsumerpending.toString().replace("\"", ""));
				   }  else{
					   model.addAttribute("consumerpendingvalue","");
				   }
				  
				   //end代币银行支出收入比
			   
				   IfbrainIndex classifbrainindex = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(ifbrainindex.get(0).getOrdinalNumber(), childlists,listfindbyclass.get(0).getCourseLevel());
				     
				     if(classifbrainindex==null){
				    	 model.addAttribute("classifbrainindex", new BigDecimal(0));
				    
					      model.addAttribute("AfterclassIncome", new BigDecimal(0));
				     }else{
				     BigDecimal classifbrainindexs = classifbrainindex.getIncome();
				     model.addAttribute("classifbrainindex", classifbrainindexs);
				      model.addAttribute("AfterclassIncome",  classifbrainindex.getBankAfterclassIncome());

				     }
				     List<IfbrainIndex> ifbrainindexs =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlists,listfindbyclass.get(0).getCourseLevel());
				     if(ifbrainindexs.size()>0){
				    	   model.addAttribute("ordinalNumber", "");
				     }else{
				    	 model.addAttribute("ordinalNumber", "");
				     }
		   }
     }
		   else{
			   model.addAttribute("type", "");
			     model.addAttribute("ifbrainindexa", "");
				 model.addAttribute("value", "");
	  			 model.addAttribute("name", "");
	  			model.addAttribute("ifbrainindexvalue","");
	  	  		model.addAttribute("ifbrainindexname", "");
		   }
       }else{
    	   model.addAttribute("flagvalue","");
    	   model.addAttribute("childs", null);
    	   model.addAttribute("showvideolist", "");
    	   model.addAttribute("type", "");
		     model.addAttribute("ifbrainindexa", "");
			 model.addAttribute("value", "");
			 model.addAttribute("name", "");
			model.addAttribute("ifbrainindexvalue","");
	  		model.addAttribute("ifbrainindexname", "");
       }
	   return "/assessment/daibibank";
   }
//点击课程
   @RequestMapping(value = "/showchildifbraindaibi",method = RequestMethod.GET)
   public  String showchildifbraindaibi(Model model, HttpServletRequest request,HttpSession session)
   {
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlist =_cChildRepository.findByUser(user);
       if(childlist.size()>0){
    	   List <Course> course = _cCourseReponsitory.findAll();
		model.addAttribute("course", course);
    	model.addAttribute("childs", childlist);
    Integer childlists=Integer.parseInt(request.getParameter("childId"));
   	   String ordinalNumber=request.getParameter("ordinalNumber");
   	  Integer courseid=Integer.parseInt(request.getParameter("courseid"));
   	 Course coursefind = _cCourseReponsitory.findOne(courseid);
   	    model.addAttribute("flagvalue", childlists);
		Child child = _cChildRepository.findOne(childlists);
		
		//判断需求所占百分比
		 //默认选中第一节课
		if(course.size()>0){
			Integer classid=Integer.parseInt(request.getParameter("classid"));
			 List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndClassId(childlists,classid);
		      model.addAttribute("ifbrainindex",ifbrainindex);
		      model.addAttribute("courseid", coursefind.getId());
		}else{
			    model.addAttribute("ifbrainindex","");
		}
		
		 List<Object[]> ifbrainIndexFindByClass=_iIfbrainIndexReponsitory.findClassIdByChildId(childlists);
		   List <CourseClass> listfindbyclass = new ArrayList<CourseClass> ();
		    if(ifbrainIndexFindByClass.size()>0){
		    	for(int i=0 ;i<ifbrainIndexFindByClass.size();i++){
		    			Integer varName=(Integer) ifbrainIndexFindByClass.get(i)[1];
		    		 List <CourseClass> courseclass = _cCourseClassReponsitory.findByclassId(varName);
		    		 listfindbyclass.add( courseclass.get(0));
		    	}
		    	model.addAttribute("listfindbyclass", listfindbyclass);
		    	model.addAttribute("listfindbyclassid", request.getParameter("classid"));
				 
			  
		  }else{
			  model.addAttribute("listfindbyclass", "");
			  model.addAttribute("listfindbyclassid", "");
		  }
		
		
		   IfbrainIndex ifbrain = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber),child.getId(),coursefind.getCourseLevel());
	
		   if(ifbrain!=null){
			   model.addAttribute("ifbrainindexa", ifbrain);
			   //上节课
			   if(Integer.parseInt(ordinalNumber)>1){
				   IfbrainIndex ifbrainprevcheck = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber)-1,child.getId(),coursefind.getCourseLevel());
				   if(ifbrainprevcheck!=null){
					   if(Integer.parseInt(ordinalNumber)==1){
						   model.addAttribute("ifbrainindexaafterincome", 0);
						   model.addAttribute("ifbrainindexbalance", 0);
					   }else{
						   Integer previncome = Integer.parseInt(ordinalNumber)-1;
						   IfbrainIndex ifbrainprev = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(previncome,child.getId(),coursefind.getCourseLevel());
						   model.addAttribute("ifbrainindexaafterincome", ifbrainprev.getBankAfterclassIncome());
						   model.addAttribute("ifbrainindexbalance", ifbrainprev.getBalance());
					   }
				   }else{
					   model.addAttribute("ifbrainindexaafterincome", 0);
					   model.addAttribute("ifbrainindexbalance", 0);
				   }
			   }else{
				   IfbrainIndex ifbrainprevcheck = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(1,child.getId(),coursefind.getCourseLevel());
				   if(ifbrainprevcheck!=null){
					   if(Integer.parseInt(ordinalNumber)==1){
						   model.addAttribute("ifbrainindexaafterincome", 0);
						   model.addAttribute("ifbrainindexbalance", 0);
					   }else{
						   Integer previncome = Integer.parseInt(ordinalNumber)-1;
						   IfbrainIndex ifbrainprev = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(previncome,child.getId(),coursefind.getCourseLevel());
						   model.addAttribute("ifbrainindexaafterincome", ifbrainprev.getBankAfterclassIncome());
						   model.addAttribute("ifbrainindexbalance", ifbrainprev.getBalance());
					   }
				   }else{
					   model.addAttribute("ifbrainindexaafterincome", 0);
					   model.addAttribute("ifbrainindexbalance", 0);
				   }
			   }
			  
			  
			   if(ifbrain.getBalance()==null){/*
				   model.addAttribute("ifbrainindexbalance", 0);*/
				   model.addAttribute("indextotalyue", 0);
			   }else{/*
				   model.addAttribute("ifbrainindexbalance", ifbrain.getBalance());*/
				   model.addAttribute("indextotalyue", ifbrain.getBalance());
			   }
			   //课堂知识加应用
			   BigDecimal total=new BigDecimal(0);
			   total=total.add(new BigDecimal(ifbrain.getApplicationtotalScore())).add(new BigDecimal(ifbrain.getKnowledgetotalScore()));
			    //折线图
				//折线图
		  		List<IfbrainIndex> ifbrainIndexlist = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel1(Integer.parseInt(ordinalNumber),child.getId(),coursefind.getCourseLevel());
		  		StringBuffer name=new StringBuffer();
		  		String chartvalue="";
		  		JSONArray allarray=new JSONArray();
		  		int[] values=new int[ifbrainIndexlist.size()];
		  		BigDecimal totalyue=new BigDecimal(0);
		  		JSONObject object=new JSONObject();
		  		//第一条折线财脑指数
					object.put("name", "'代币余额'");
					object.put("type", "'line'");
					object.put("stack", "'总量'");
					JSONObject object1=new JSONObject();
					object1.put("type", "'default'");
					String str = object1.toString();
					JSONObject object2=new JSONObject();
					object2.put("areaStyle",str);
					String str1 = object2.toString();
					JSONObject object3=new JSONObject();
					object3.put("normal",str1);
					String str3 = object3.toString();
					object.put("itemStyle", str3);
					if(ifbrainIndexlist.size()>0){
		  		   for(int i=0;i<ifbrainIndexlist.size();i++){
		  			//曲线图横坐标
		  			name.append("第"+ifbrainIndexlist.get(i).getOrdinalNumber()+"节");
		  			name.append(",");
		  			if(ifbrainIndexlist.get(i).getBalance()==null){
		  				totalyue=totalyue.add(new BigDecimal(0));
		  			}else{
		  			totalyue=totalyue.add(ifbrainIndexlist.get(i).getBalance());
		  			}
		  			//第一条曲线数据
		  			if(ifbrainIndexlist.get(i).getBalance()!=null){
		  				values[i]=(ifbrainIndexlist.get(i).getBalance()).intValue();
		  			}else{
		  				 values[i]=0;
		  			}
		  		   
		  		   }
					}	
					    object.put("data", values);
			    chartvalue=allarray.put(object).toString().replace("\"", "");
			    model.addAttribute("totalyue", totalyue.toString());
			    model.addAttribute("ifbrainindexnamebank",name.substring(0, name.length()-1).toString());
		  		model.addAttribute("courseid", courseid);
		  		model.addAttribute("ifbrainindexvaluebank",chartvalue);
                    List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlists,coursefind.getCourseLevel());		   
				 //代币银行支出收入比
				   //收入
				   List<Object[]> consumerpending =_iIfbrainIndexReponsitory.findByincome(childlists, coursefind.getCourseLevel());
				   StringBuffer consumerpendingBuffer=new StringBuffer();
				   JSONArray jsonArrayconsumerpending=new JSONArray();
				   StringBuilder sb = new StringBuilder();
				   StringBuilder sb1 = new StringBuilder();
				   sb.append("[");
				   if(consumerpending.size()>0){
					   JSONObject jsonconsumerpending=new JSONObject();
					   jsonconsumerpending.put("name", "'收入'");
					   jsonconsumerpending.put("type", "'bar'");
					   jsonconsumerpending.put("stack", "'总量'");
					   BigDecimal[]  consumerpendingvalue=new BigDecimal[consumerpending.size()];
						for(int i=0;i<consumerpending.size();i++){
						Integer consumerpendingName=(Integer)consumerpending.get(i)[0];
						consumerpendingBuffer.append("第"+consumerpendingName+"周");
						consumerpendingBuffer.append(",");
						if(consumerpending.get(i)[1]==""){
							consumerpendingvalue[i]=new BigDecimal(0);
							sb.append(consumerpendingvalue[i]);
							 sb.append(",");
						}else{
							consumerpendingvalue[i]=(BigDecimal)consumerpending.get(i)[1];
							
							 sb.append(consumerpendingvalue[i]);
							 sb.append(",");
						 }
						}
						sb.append("]");
						jsonconsumerpending.put("data",sb.toString());
						jsonArrayconsumerpending.put(jsonconsumerpending);
						model.addAttribute("consumerpendingBuffer",consumerpendingBuffer.substring(0, consumerpendingBuffer.length()-1));
				   }else{
					   model.addAttribute("consumerpendingBuffer","");
				   }
				   
					
					 //支出
					List<Object[]> consumerpending2 =_iIfbrainIndexReponsitory.findByexpense(childlists, coursefind.getCourseLevel());
				   sb1.append("[");
				   if(consumerpending2.size()>0){
					   JSONObject jsonconsumerpending2=new JSONObject();
					   jsonconsumerpending2.put("name", "'消费'");
					   jsonconsumerpending2.put("type", "'bar'");
					   jsonconsumerpending2.put("stack", "'总量'");
					   BigDecimal[] consumerpendingvalue2=new BigDecimal[consumerpending2.size()];
						for(int i=0;i<consumerpending2.size();i++){
							consumerpendingvalue2[i]=(BigDecimal)consumerpending2.get(i)[1];
							if(consumerpending2.get(i)[1]==""||consumerpending2.get(i)[1]==null){
								consumerpendingvalue2[i]=new BigDecimal(0);
								sb1.append(consumerpendingvalue2[i]);
								 sb1.append(",");
							}else{
								consumerpendingvalue2[i]=(BigDecimal)consumerpending2.get(i)[1];
								sb1.append(consumerpendingvalue2[i]);
								sb1.append(",");
							}
						     }
						sb1.append("]");
						jsonconsumerpending2.put("data",sb1.toString());
						jsonArrayconsumerpending.put(jsonconsumerpending2);
						 model.addAttribute("consumerpendingvalue",jsonArrayconsumerpending.toString().replace("\"", ""));
				   }  else{
					   model.addAttribute("consumerpendingvalue","");
				   }
				  
				   //end代币银行支出收入比
			   
				   IfbrainIndex classifbrainindex = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber), childlists,coursefind.getCourseLevel());
				     
				     if(classifbrainindex==null){
				    	 model.addAttribute("classifbrainindex", new BigDecimal(0));
					      model.addAttribute("AfterclassIncome", new BigDecimal(0));
				     }else{
				     BigDecimal classifbrainindexs = classifbrainindex.getIncome();
				     model.addAttribute("classifbrainindex", classifbrainindexs);
				      model.addAttribute("AfterclassIncome",  classifbrainindex.getBankAfterclassIncome());
				     }
				     List<IfbrainIndex> ifbrainindexs =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlists,coursefind.getCourseLevel());
				     if(ifbrainindexs.size()>0){
				    	   model.addAttribute("ordinalNumber",ordinalNumber);
				     }else{
				    	 model.addAttribute("ordinalNumber", "");
				     }
		   }
		   else{
			   model.addAttribute("type", "");
			     model.addAttribute("ifbrainindexa", "");
				 model.addAttribute("value", "");
	  			 model.addAttribute("name", "");
	  			model.addAttribute("ifbrainindexvalue","");
	  	  		model.addAttribute("ifbrainindexname", "");
		   }
       }else{
    	   model.addAttribute("flagvalue","");
    	   model.addAttribute("childs", null);
    	   model.addAttribute("showvideolist", "");
    	   model.addAttribute("toggleshow", 1);
       }
	   return "/assessment/daibibank";
   }
 //点击课程
   @RequestMapping(value = "/showcourseinformations",method = RequestMethod.GET)
   public  String showcourseinformations(Model model, HttpServletRequest request,HttpSession session)
   {
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlist =_cChildRepository.findByUser(user);
       if(childlist.size()>0){
    	   List <Course> course = _cCourseReponsitory.findAll();
		model.addAttribute("course", course);
    	model.addAttribute("childs", childlist);
    Integer childlists=Integer.parseInt(request.getParameter("childId"));
   	  Integer courseid=Integer.parseInt(request.getParameter("courseid"));
   	 Course coursefind = _cCourseReponsitory.findOne(courseid);
   	    model.addAttribute("flagvalue", childlists);
		Child child = _cChildRepository.findOne(childlists);
		//判断需求所占百分比
		 //默认选中第一节课
		Integer classid=Integer.parseInt(request.getParameter("classid"));
		 List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndClassId(childlists,classid);
		if(course.size()>0){
			
		      model.addAttribute("ifbrainindex",ifbrainindex);
		      model.addAttribute("courseid", coursefind.getId());
		}else{
			    model.addAttribute("ifbrainindex","");
		}
		   
		List<Object[]> ifbrainIndexFindByClass=_iIfbrainIndexReponsitory.findClassIdByChildId(childlists);
		   List <CourseClass> listfindbyclass = new ArrayList<CourseClass> ();
		    if(ifbrainIndexFindByClass.size()>0){
		    	for(int i=0 ;i<ifbrainIndexFindByClass.size();i++){
		    			Integer varName=(Integer) ifbrainIndexFindByClass.get(i)[1];
		    		 List <CourseClass> courseclass = _cCourseClassReponsitory.findByclassId(varName);
		    		 listfindbyclass.add( courseclass.get(0));
		    	}
		    	model.addAttribute("listfindbyclass", listfindbyclass);
		    	model.addAttribute("listfindbyclassid", request.getParameter("classid"));
				 
			  
		  }else{
			  model.addAttribute("listfindbyclass", "");
			  model.addAttribute("listfindbyclassid", "");
		  }
		   if(ifbrainindex.size()>0){
			   IfbrainIndex ifbrain = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(ifbrainindex.get(0).getOrdinalNumber(),child.getId(),coursefind.getCourseLevel());
			   model.addAttribute("ifbrainindexa", ifbrain);
			   model.addAttribute("ifbrainindexaafterincome", 0);
			 
				   model.addAttribute("ifbrainindexbalance", 0);
			
			
			   //课堂知识加应用
			   BigDecimal total=new BigDecimal(0);
			   total=total.add(new BigDecimal(ifbrain.getApplicationtotalScore())).add(new BigDecimal(ifbrain.getKnowledgetotalScore()));
			    //折线图
			   List<IfbrainIndex> ifbrainIndexlist = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel3(child.getId(),coursefind.getCourseLevel());
		  		JSONObject object=new JSONObject();
		  		//第一条折线财脑指数
					object.put("name", "'财脑指数'");
					object.put("type", "'line'");
					object.put("stack", "'总量'");
					JSONObject object1=new JSONObject();
					object1.put("type", "'default'");
					String stra = object1.toString();
					JSONObject object2=new JSONObject();
					object2.put("areaStyle",stra);
					String str1 = object2.toString();
					JSONObject object3=new JSONObject();
					object3.put("normal",str1);
					String str3 = object3.toString();
					object.put("itemStyle", str3);
			  		 
			  		
			  		
			  		
			  		
			  		
			  		
			  		
			  	//财脑银行折线图
			  		 //代币总余额
			  			BigDecimal totalyue=new BigDecimal(0);
				  		List<IfbrainIndex> ifbrainIndexlistbank = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel3(child.getId(),coursefind.getCourseLevel());
				  		StringBuffer bankname=new StringBuffer();
				  		String chartbankvalue="";
				  		JSONArray allarraybank=new JSONArray();
				  		int[] valuesbank=new int[ifbrainIndexlist.size()];
				  		JSONObject objectbank=new JSONObject();
			  		//第一条折线财脑指数
				  		objectbank.put("name", "'代币余额'");
				  		objectbank.put("type", "'line'");
				  		objectbank.put("stack", "'总量'");
						JSONObject objectbank1=new JSONObject();
						object1.put("type", "'default'");
						String strbank = objectbank1.toString();
						JSONObject objectbank2=new JSONObject();
						object2.put("areaStyle",strbank);
						String strbank1 = objectbank2.toString();
						JSONObject objectbank3=new JSONObject();
						object3.put("normal",strbank1);
						String strbank3 = objectbank3.toString();
						objectbank.put("itemStyle", strbank3);
						if(ifbrainIndexlist.size()>0){
			  		   for(int i=0;i<ifbrainIndexlist.size();i++){
			  			//曲线图横坐标
			  			 bankname.append("第"+ifbrainIndexlistbank.get(i).getOrdinalNumber()+"节");
			  			 bankname.append(",");
			  			/*totalyue=totalyue.add(ifbrainIndexlistbank.get(i).getIncome().subtract(ifbrainIndexlistbank.get(i).getExpense()));*/
			  			 if(ifbrainIndexlistbank.get(i).getBalance()==null){
			  				totalyue=totalyue.add(new BigDecimal(0));
			  			 }else{
			  			totalyue=totalyue.add(ifbrainIndexlistbank.get(i).getBalance());
			  			 }
			  			 //第一条曲线数据
			  			if(ifbrainIndexlistbank.get(i).getBalance()!=null){
			  				valuesbank[i]=(ifbrainIndexlistbank.get(i).getBalance()).intValue();
			  			}else{
			  				valuesbank[i]=0;
			  			}
			  		   }
						}
					objectbank.put("data",valuesbank);
				    chartbankvalue=allarraybank.put(objectbank).toString().replace("\"", "");
				    model.addAttribute("ifbrainindexnamebank",bankname.substring(0, bankname.length()-1).toString());
			  		model.addAttribute("ifbrainindexvaluebank",chartbankvalue);
                    model.addAttribute("totalyue", totalyue.toString());
                
                  //最后一节课的代币余额
		  			 List <IfbrainIndex>  indextotalyue = _iIfbrainIndexReponsitory.findByChildIdAndCourseLeveldesc(childlists,coursefind.getCourseLevel());
		  			 model.addAttribute("indextotalyue", indextotalyue.get(0).getBalance());
                    // List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlists,coursefind.getCourseLevel());		   
				 //代币银行支出收入比
				   //收入
				   List<Object[]> consumerpending =_iIfbrainIndexReponsitory.findByincome(childlists, coursefind.getCourseLevel());
				   StringBuffer consumerpendingBuffer=new StringBuffer();
				   JSONArray jsonArrayconsumerpending=new JSONArray();
				   StringBuilder sb = new StringBuilder();
				   StringBuilder sb1 = new StringBuilder();
				   sb.append("[");
				   if(consumerpending.size()>0){
					   JSONObject jsonconsumerpending=new JSONObject();
					   jsonconsumerpending.put("name", "'收入'");
					   jsonconsumerpending.put("type", "'bar'");
					   jsonconsumerpending.put("stack", "'总量'");
					   BigDecimal[]  consumerpendingvalue=new BigDecimal[consumerpending.size()];
						for(int i=0;i<consumerpending.size();i++){
						Integer consumerpendingName=(Integer)consumerpending.get(i)[0];
						consumerpendingBuffer.append("第"+consumerpendingName+"周");
						consumerpendingBuffer.append(",");
						if(consumerpending.get(i)[1]==""){
							consumerpendingvalue[i]=new BigDecimal(0);
							sb.append(consumerpendingvalue[i]);
							 sb.append(",");
						}else{
							consumerpendingvalue[i]=(BigDecimal)consumerpending.get(i)[1];
							
							 sb.append(consumerpendingvalue[i]);
							 sb.append(",");
						 }
						}
						sb.append("]");
						jsonconsumerpending.put("data",sb.toString());
						jsonArrayconsumerpending.put(jsonconsumerpending);
						model.addAttribute("consumerpendingBuffer",consumerpendingBuffer.substring(0, consumerpendingBuffer.length()-1));
				   }else{
					   model.addAttribute("consumerpendingBuffer","");
				   }
				   
					
					 //支出
					List<Object[]> consumerpending2 =_iIfbrainIndexReponsitory.findByexpense(childlists, coursefind.getCourseLevel());
				   sb1.append("[");
				   if(consumerpending2.size()>0){
					   JSONObject jsonconsumerpending2=new JSONObject();
					   jsonconsumerpending2.put("name", "'消费'");
					   jsonconsumerpending2.put("type", "'bar'");
					   jsonconsumerpending2.put("stack", "'总量'");
					   BigDecimal[] consumerpendingvalue2=new BigDecimal[consumerpending2.size()];
						for(int i=0;i<consumerpending2.size();i++){
							consumerpendingvalue2[i]=(BigDecimal)consumerpending2.get(i)[1];
							if(consumerpending2.get(i)[1]==""||consumerpending2.get(i)[1]==null){
								consumerpendingvalue2[i]=new BigDecimal(0);
								sb1.append(consumerpendingvalue2[i]);
								 sb1.append(",");
							}else{
								consumerpendingvalue2[i]=(BigDecimal)consumerpending2.get(i)[1];
								sb1.append(consumerpendingvalue2[i]);
								sb1.append(",");
							}
						     }
						sb1.append("]");
						jsonconsumerpending2.put("data",sb1.toString());
						jsonArrayconsumerpending.put(jsonconsumerpending2);
						 model.addAttribute("consumerpendingvalue",jsonArrayconsumerpending.toString().replace("\"", ""));
				   }  else{
					   model.addAttribute("consumerpendingvalue","");
				   }
				  
				   //end代币银行支出收入比
			   
				   IfbrainIndex classifbrainindex = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(ifbrainindex.get(0).getOrdinalNumber(), childlists,coursefind.getCourseLevel());
				     
				     if(classifbrainindex==null){
				    	 model.addAttribute("classifbrainindex", new BigDecimal(0));
					      model.addAttribute("AfterclassIncome", new BigDecimal(0));
					      
				     }else{
				     BigDecimal classifbrainindexs =classifbrainindex.getIncome();
				     model.addAttribute("classifbrainindex", classifbrainindexs);
				      model.addAttribute("AfterclassIncome",  classifbrainindex.getBankAfterclassIncome());
				     }
				     List<IfbrainIndex> ifbrainindexs =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlists,coursefind.getCourseLevel());
				     if(ifbrainindexs.size()>0){
				    	   model.addAttribute("ordinalNumber","");
				     }else{
				    	 model.addAttribute("ordinalNumber", "");
				     }
		   }
		   else{
			   model.addAttribute("type", "");
			     model.addAttribute("ifbrainindexa", "");
				 model.addAttribute("value", "");
	  			 model.addAttribute("name", "");
	  			model.addAttribute("ifbrainindexvalue","");
	  	  		model.addAttribute("ifbrainindexname", "");
		   }
       }else{
    	   model.addAttribute("flagvalue","");
    	   model.addAttribute("childs", null);
    	   model.addAttribute("showvideolist", "");
    	   model.addAttribute("toggleshow", 1);
       }
	   return "/assessment/daibibank";
   }
  	
}
