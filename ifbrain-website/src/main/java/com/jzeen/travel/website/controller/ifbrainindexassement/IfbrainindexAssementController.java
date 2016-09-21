package com.jzeen.travel.website.controller.ifbrainindexassement;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.jzeen.travel.data.entity.Comment;
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
import com.jzeen.travel.data.repository.CommentRepository;
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
 * 财脑指数评估
 * @author sunyan
 *
 */
@Controller
@RequestMapping("/ifbrainindexassement")
public class IfbrainindexAssementController
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
		private CourseClassReponsitory _cCourseClassReponsitory;

	 @Autowired
		private CourseReponsitory _cCourseReponsitory;
	 @Autowired
		private IfbrainVarReponsitory _IfbrainVarReponsitory;
	 @Autowired
		private ChildShoppingmallRepository _cChildShoppingmallRepository;
	 @Autowired
	 private CommentRepository _commentResponsitory;
	 
   //
   @RequestMapping(value = "/ifbrainindex",method = RequestMethod.GET)
   public  String ifbrainindex(Model model, HttpServletRequest request,HttpSession session)
   {
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlists =_cChildRepository.findByUser(user);
	   if(childlists.size()>0){
		   List <Course> course = _cCourseReponsitory.findAll();
			model.addAttribute("course", course);
			
	    	model.addAttribute("childs", childlists);
	    	
	   Integer childlist=Integer.parseInt(request.getParameter("childId"));
	   model.addAttribute("flagvalue",childlist);
	   List<Object[]> ifbrainIndexFindByClass=_iIfbrainIndexReponsitory.findClassIdByChildId(childlist);
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
	    
	    
		Child child = _cChildRepository.findOne(Integer.parseInt(request.getParameter("childId")));
		List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndClassId(childlist,listfindbyclass.get(0).getId());
		if(course.size()>0){
			 
		      model.addAttribute("ifbrainindex",ifbrainindex);
		      model.addAttribute("courseid", listfindbyclass.get(0).getCourseId().getId());
		}else{
			    model.addAttribute("ifbrainindex","");
		}
	  
	   if(ifbrainindex.size()>0){
		   
		   
		   IfbrainIndex ifbrain = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(ifbrainindex.get(0).getOrdinalNumber(),child.getId(),listfindbyclass.get(0).getCourseLevel());
		  
		   model.addAttribute("ifbrainindexa", ifbrain);
		   //课堂知识加应用
		   BigDecimal total=new BigDecimal(0);
		   total=total.add(new BigDecimal(ifbrain.getApplicationtotalScore())).add(new BigDecimal(ifbrain.getKnowledgetotalScore()));
		    //折线图
		   List<IfbrainIndex> ifbrainIndexlist = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel2(child.getId(),listfindbyclass.get(0).getCourseLevel());
	  		StringBuffer name=new StringBuffer();
	  		String chartvalue="";
	  		String names="";
	  		JSONArray allarray=new JSONArray();
	  		int[] values=new int[ifbrainIndexlist.size()];
	  		int[] values1=new int[ifbrainIndexlist.size()];
	  		JSONObject object=new JSONObject();
	  		JSONObject objecta=new JSONObject();
	  		//第一条折线财脑指数
				object.put("name", "'财脑指数'");
				object.put("type", "'line'");
				/*object.put("stack", "'总量'");*/
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
				if(ifbrainIndexlist.size()>0){
	  		   for(int i=0;i<ifbrainIndexlist.size();i++){
	  			//曲线图横坐标
	  			name.append("第"+ifbrainIndexlist.get(i).getOrdinalNumber()+"节");
	  			name.append(",");
	  			//第一条曲线数据
	  			if(ifbrainIndexlist.get(i).getIfbrainIndex()!=null){
	  				values[i]=ifbrainIndexlist.get(i).getIfbrainIndex();
	  			}
	  			else{
	  				values[i]=0;
	  			}
	  			object.put("data", values);
	  			//第二条曲线图
	  			BigDecimal secondvalue=new BigDecimal(0);
	  			secondvalue=secondvalue.add(new BigDecimal(ifbrainIndexlist.get(i).getKnowledgetotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getApplicationtotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getAfterclassIncome()));
	  			values1[i]=secondvalue.intValue();
	  		}
			  	//第二条折线当前课程财脑得分=知识+应用+课后训练
			  		objecta.put("name", "'当前课程财脑得分=知识+应用+课后训练'");
			  		objecta.put("type", "'line'");
			  		objecta.put("stack", "'总量'");
			  		objecta.put("itemStyle", str3);
			  		objecta.put("data", values1);
			  		allarray.put(objecta);
			  		allarray.put(object);
			  		 chartvalue = allarray.toString().replace("\"", "");
			  		 names = name.substring(0, name.length()-1).toString();
				} 
				model.addAttribute("ifbrainindexvalue",chartvalue);
		  		model.addAttribute("ifbrainindexname", names);
		  		
		  		
		  		
		  
		   List<Object[]> ifbrainval = _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),0,listfindbyclass.get(0).getCourseLevel());	
		   StringBuffer classnameBuffer=new StringBuffer();
		   JSONArray jsonArray1=new JSONArray();
		   
		   //课堂训练柱状图
		   if(ifbrainval.size()>0){
			   JSONObject json22=new JSONObject();
			   json22.put("name", "'知识'");
			   json22.put("type", "'bar'");
				String[] value=new String[ifbrainval.size()];
				for(int i=0;i<ifbrainval.size();i++){
					String varName=(String)ifbrainval.get(i)[0];
					classnameBuffer.append(varName);
					classnameBuffer.append(",");
					if(ifbrainval.get(i)[1]==""){
						value[i]="0";
					}else{
						value[i]=(String)ifbrainval.get(i)[1];
					}
				     }
				json22.put("data",value);
				jsonArray1.put(json22);
				model.addAttribute("name",classnameBuffer.substring(0, classnameBuffer.length()-1));
		   }  
		   List<Object[]> ifbrainval2= _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),1,listfindbyclass.get(0).getCourseLevel());	
		   //课堂训练柱状图
		   if(ifbrainval2.size()>0){
			   JSONObject json21=new JSONObject();
			   json21.put("name", "'应用'");
			   json21.put("type", "'bar'");
			    String[] value=new String[ifbrainval2.size()];
				for(int i=0;i<ifbrainval2.size();i++){
					value[i]=(String)ifbrainval2.get(i)[1];
				     }
				json21.put("data",value);
				jsonArray1.put(json21);
		   }  
		   model.addAttribute("value",jsonArray1.toString().replace("\"", ""));
		  
		   IfbrainIndex classifbrainindex = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(ifbrainindex.get(0).getOrdinalNumber(), childlist,listfindbyclass.get(0).getCourseLevel());
		     
		     if(classifbrainindex==null){
		    	 model.addAttribute("classifbrainindex", new BigDecimal(0));
			      model.addAttribute("AfterclassIncome", new BigDecimal(0));
		     }else{
		     BigDecimal classifbrainindexs = new BigDecimal(classifbrainindex.getKnowledgetotalScore()).add(new BigDecimal(classifbrainindex.getApplicationtotalScore()));
		     model.addAttribute("classifbrainindex", classifbrainindexs);
		      model.addAttribute("AfterclassIncome",  classifbrainindex.getAfterclassIncome());
		     }
		     //
		     List<IfbrainIndex> ifbrainindexs =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlist,listfindbyclass.get(0).getCourseLevel());
		     if(ifbrainindexs.size()>0){
		    	   model.addAttribute("ordinalNumber", "");
		     }
		     
		     String ordinal =ifbrainindex.get(0).getOrdinalNumber().toString();
		      CourseCode courseCode = _ccourseCodeRepository.findByOrdinalNumberAndCourseId(ordinal, listfindbyclass.get(0).getCourseId().getId());
		      if(courseCode!=null){
		         model.addAttribute("courseCode", courseCode);
		     }else{
		    	  model.addAttribute("courseCode", null);
		     }
		     
		     
		   /*  //评论列表
		     List <Comment> commentlist =_commentResponsitory.findByCommentList(ifbrainindex.get(0).getOrdinalNumber(),course.get(0).getCourseLevel());*/
		     model.addAttribute("commentlist", "");
		     
	   }
	   }
	   else{
		   model.addAttribute("commentlist", "");
		   model.addAttribute("type", "");
		     model.addAttribute("ifbrainindexa", "");
			 model.addAttribute("value", "");
  			 model.addAttribute("name", "");
  			model.addAttribute("ifbrainindexvalue","");
  	  		model.addAttribute("ifbrainindexname", "");
  	  	  model.addAttribute("classifbrainindex", new BigDecimal(0));
	      model.addAttribute("AfterclassIncome", new BigDecimal(0));
	      model.addAttribute("courseCode", null);
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
	  		 model.addAttribute("classifbrainindex", new BigDecimal(0));
		      model.addAttribute("AfterclassIncome", new BigDecimal(0));
		      model.addAttribute("courseCode", null);
	   }
	   return "/assessment/ifbrainindex";
	   
   }
//点击课程列表
   @RequestMapping(value="/showchildifbrainchart", method = RequestMethod.GET)
 	public String showchildifbrainchart(HttpServletRequest request,HttpServletResponse response,Model model){
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlists =_cChildRepository.findByUser(user);
	   Integer childlist=Integer.parseInt(request.getParameter("childId"));
	   String ordinalNumber=request.getParameter("ordinalNumber");
	   System.out.println(request.getParameter("courseid"));
	   Integer courseid=Integer.parseInt(request.getParameter("courseid"));
	  
	   if(childlists.size()>0){
		   List <Course> course = _cCourseReponsitory.findAll();
			model.addAttribute("course", course);
	    	model.addAttribute("childs", childlists);
	    	  model.addAttribute("courseid", courseid);
	    	  Course coursefind = _cCourseReponsitory.findOne(courseid);
	   model.addAttribute("flagvalue",childlist);
		Child child = _cChildRepository.findOne(Integer.parseInt(request.getParameter("childId")));
		if(course.size()>0){
			Integer classid=Integer.parseInt(request.getParameter("classid"));
			 List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndClassId(childlist,classid);
		      model.addAttribute("ifbrainindex",ifbrainindex);
		}else{
			    model.addAttribute("ifbrainindex","");
		}
		
		 List<Object[]> ifbrainIndexFindByClass=_iIfbrainIndexReponsitory.findClassIdByChildId(childlist);
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
		    
		      model.addAttribute("courseid", listfindbyclass.get(0).getCourseId().getId());
	   IfbrainIndex ifbrain = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber),child.getId(),coursefind.getCourseLevel());
	   if(ifbrain!=null){
		   model.addAttribute("ifbrainindexa", ifbrain);
		   //课堂知识加应用
		   BigDecimal total=new BigDecimal(0);
		   total=total.add(new BigDecimal(ifbrain.getApplicationtotalScore())).add(new BigDecimal(ifbrain.getKnowledgetotalScore()));
		    //折线图
		   List<IfbrainIndex> ifbrainIndexlist = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel1(Integer.parseInt(ordinalNumber),child.getId(),coursefind.getCourseLevel());
	  		StringBuffer name=new StringBuffer();
	  		String chartvalue="";
	  		String names="";
	  		JSONArray allarray=new JSONArray();
	  		int[] values=new int[ifbrainIndexlist.size()];
	  		int[] values1=new int[ifbrainIndexlist.size()];
	  		JSONObject object=new JSONObject();
	  		JSONObject objecta=new JSONObject();
	  		//第一条折线财脑指数
				object.put("name", "'财脑指数'");
				object.put("type", "'line'");
				/*object.put("stack", "'总量'");*/
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
				if(ifbrainIndexlist.size()>0){
	  		   for(int i=0;i<ifbrainIndexlist.size();i++){
	  			//曲线图横坐标
	  			name.append("第"+ifbrainIndexlist.get(i).getOrdinalNumber()+"节");
	  			name.append(",");
	  			//第一条曲线数据
	  			if(ifbrainIndexlist.get(i).getIfbrainIndex()!=null){
	  				values[i]=ifbrainIndexlist.get(i).getIfbrainIndex();
	  			}
	  			else{
	  				values[i]=0;
	  			}
	  			object.put("data", values);
	  			//第二条曲线图
	  			BigDecimal secondvalue=new BigDecimal(0);
	  			secondvalue=secondvalue.add(new BigDecimal(ifbrainIndexlist.get(i).getKnowledgetotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getApplicationtotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getAfterclassIncome()));
	  			values1[i]=secondvalue.intValue();
	  		}
			  	//第二条折线当前课程财脑得分=知识+应用+课后训练
			  		objecta.put("name", "'当前课程财脑得分=知识+应用+课后训练'");
			  		objecta.put("type", "'line'");
			  		objecta.put("stack", "'总量'");
			  		objecta.put("itemStyle", str3);
			  		objecta.put("data", values1);
			  		allarray.put(objecta);
			  		allarray.put(object);
			  		 chartvalue = allarray.toString().replace("\"", "");
			  		 names = name.substring(0, name.length()-1).toString();
				} 
				model.addAttribute("ifbrainindexvalue",chartvalue);
		  		model.addAttribute("ifbrainindexname", names);
		  		
		  		
		  		
		   List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlist,coursefind.getCourseLevel());
		   List<Object[]> ifbrainval = _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),0,coursefind.getCourseLevel());	
		   StringBuffer classnameBuffer=new StringBuffer();
		   JSONArray jsonArray1=new JSONArray();
		   
		   //课堂训练柱状图
		   if(ifbrainval.size()>0){
			   JSONObject json22=new JSONObject();
			   json22.put("name", "'知识'");
			   json22.put("type", "'bar'");
				String[] value=new String[ifbrainval.size()];
				for(int i=0;i<ifbrainval.size();i++){
					String varName=(String)ifbrainval.get(i)[0];
					classnameBuffer.append(varName);
					classnameBuffer.append(",");
					if(ifbrainval.get(i)[1]==""){
						value[i]="0";
					}else{
						value[i]=(String)ifbrainval.get(i)[1];
					}
				     }
				json22.put("data",value);
				jsonArray1.put(json22);
				model.addAttribute("name",classnameBuffer.substring(0, classnameBuffer.length()-1));
		   }  
		   List<Object[]> ifbrainval2= _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),1,coursefind.getCourseLevel());	
		   //课堂训练柱状图
		   if(ifbrainval2.size()>0){
			   JSONObject json21=new JSONObject();
			   json21.put("name", "'应用'");
			   json21.put("type", "'bar'");
			    String[] value=new String[ifbrainval2.size()];
				for(int i=0;i<ifbrainval2.size();i++){
					value[i]=(String)ifbrainval2.get(i)[1];
				     }
				json21.put("data",value);
				jsonArray1.put(json21);
		   }  
		   model.addAttribute("value",jsonArray1.toString().replace("\"", ""));
		   IfbrainIndex classifbrainindex = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber), childlist, coursefind.getCourseLevel());
		     
		     if(classifbrainindex==null){
		    	 model.addAttribute("classifbrainindex", new BigDecimal(0));
			      model.addAttribute("AfterclassIncome", new BigDecimal(0));
		     }else{
		     BigDecimal classifbrainindexs = new BigDecimal(classifbrainindex.getKnowledgetotalScore()).add(new BigDecimal(classifbrainindex.getApplicationtotalScore()));
		     model.addAttribute("classifbrainindex", classifbrainindexs);
		      model.addAttribute("AfterclassIncome",  classifbrainindex.getAfterclassIncome());
		     }
		     //
		     List<IfbrainIndex> ifbrainindexs =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlist,coursefind.getCourseLevel());
		     if(ifbrainindexs.size()>0){
		    	   model.addAttribute("ordinalNumber", ordinalNumber);
		     }
		     String ordinal =ordinalNumber;
		      CourseCode courseCode = _ccourseCodeRepository.findByOrdinalNumberAndCourseId(ordinal, coursefind.getId());
		      if(courseCode!=null){
		         model.addAttribute("courseCode", courseCode);
		     }else{
		    	  model.addAttribute("courseCode", null);
		     }
		     //评论列表
		      List <Comment> commentlist =_commentResponsitory.findByIfbrainIndex(classifbrainindex);
		   //  List <Comment> commentlist =_commentResponsitory.findByCommentList(Integer.parseInt(ordinalNumber),coursefind.getCourseLevel());
		     model.addAttribute("commentlist", commentlist);
	   }
	   else{
		   model.addAttribute("commentlist", "");
		   model.addAttribute("type", "");
		     model.addAttribute("ifbrainindexa", "");
			 model.addAttribute("value", "");
  			 model.addAttribute("name", "");
  			model.addAttribute("ifbrainindexvalue","");
  	  		model.addAttribute("ifbrainindexname", "");
  	  	  model.addAttribute("classifbrainindex", new BigDecimal(0));
	      model.addAttribute("AfterclassIncome", new BigDecimal(0));
	      model.addAttribute("courseCode", null);
	   }
	   }else{
		   model.addAttribute("flagvalue","");
		   model.addAttribute("courseCode", null);
	   }
	   return "/assessment/ifbrainindex";
	   
   }
   
   //点击课程级别
   @RequestMapping(value="/showcourseinformations", method = RequestMethod.GET)
 	public String showcourseinformations(HttpServletRequest request,HttpServletResponse response,Model model){
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlists =_cChildRepository.findByUser(user);
	   Integer childlist=Integer.parseInt(request.getParameter("childId"));
	   if(childlists.size()>0){
		   List <Course> course = _cCourseReponsitory.findAll();
			model.addAttribute("course", course);
			Integer courseid=Integer.parseInt(request.getParameter("courseid"));
	    	model.addAttribute("childs", childlists);
	    	  model.addAttribute("courseid", courseid);
	    	  Course coursefind = _cCourseReponsitory.findOne(courseid);
	   model.addAttribute("flagvalue",childlist);
		Child child = _cChildRepository.findOne(Integer.parseInt(request.getParameter("childId")));
		Integer classid=Integer.parseInt(request.getParameter("classid"));
		 List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndClassId(childlist,classid);
		if(course.size()>0){
			 
		      model.addAttribute("ifbrainindex",ifbrainindex);
		}else{
			    model.addAttribute("ifbrainindex","");
		}
	   
		
		 List<Object[]> ifbrainIndexFindByClass=_iIfbrainIndexReponsitory.findClassIdByChildId(childlist);
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
		   //课堂知识加应用
		   BigDecimal total=new BigDecimal(0);
		   total=total.add(new BigDecimal(ifbrain.getApplicationtotalScore())).add(new BigDecimal(ifbrain.getKnowledgetotalScore()));
		    //折线图
		   List<IfbrainIndex> ifbrainIndexlist = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel2(child.getId(),coursefind.getCourseLevel());
	  		StringBuffer name=new StringBuffer();
	  		String chartvalue="";
	  		String names="";
	  		JSONArray allarray=new JSONArray();
	  		int[] values=new int[ifbrainIndexlist.size()];
	  		int[] values1=new int[ifbrainIndexlist.size()];
	  		JSONObject object=new JSONObject();
	  		JSONObject objecta=new JSONObject();
	  		//第一条折线财脑指数
				object.put("name", "'财脑指数'");
				object.put("type", "'line'");
				/*object.put("stack", "'总量'");*/
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
				if(ifbrainIndexlist.size()>0){
	  		   for(int i=0;i<ifbrainIndexlist.size();i++){
	  			//曲线图横坐标
	  			name.append("第"+ifbrainIndexlist.get(i).getOrdinalNumber()+"节");
	  			name.append(",");
	  			//第一条曲线数据
	  			if(ifbrainIndexlist.get(i).getIfbrainIndex()!=null){
	  				values[i]=ifbrainIndexlist.get(i).getIfbrainIndex();
	  			}
	  			else{
	  				values[i]=0;
	  			}
	  			object.put("data", values);
	  			//第二条曲线图
	  			BigDecimal secondvalue=new BigDecimal(0);
	  			secondvalue=secondvalue.add(new BigDecimal(ifbrainIndexlist.get(i).getKnowledgetotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getApplicationtotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getAfterclassIncome()));
	  			values1[i]=secondvalue.intValue();
	  		}
			  	//第二条折线当前课程财脑得分=知识+应用+课后训练
			  		objecta.put("name", "'当前课程财脑得分=知识+应用+课后训练'");
			  		objecta.put("type", "'line'");
			  		objecta.put("stack", "'总量'");
			  		objecta.put("itemStyle", str3);
			  		objecta.put("data", values1);
			  		allarray.put(object);
			  		allarray.put(objecta);
			  		 chartvalue = allarray.toString().replace("\"", "");
			  		 names = name.substring(0, name.length()-1).toString();
				} 
				model.addAttribute("ifbrainindexvalue",chartvalue);
		  		model.addAttribute("ifbrainindexname", names);
		  		
		  		
		  		
		   
		   List<Object[]> ifbrainval = _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),0,coursefind.getCourseLevel());	
		   StringBuffer classnameBuffer=new StringBuffer();
		   JSONArray jsonArray1=new JSONArray();
		   
		   //课堂训练柱状图
		   if(ifbrainval.size()>0){
			   JSONObject json22=new JSONObject();
			   json22.put("name", "'知识'");
			   json22.put("type", "'bar'");
				String[] value=new String[ifbrainval.size()];
				for(int i=0;i<ifbrainval.size();i++){
					String varName=(String)ifbrainval.get(i)[0];
					classnameBuffer.append(varName);
					classnameBuffer.append(",");
					if(ifbrainval.get(i)[1]==""){
						value[i]="0";
					}else{
						value[i]=(String)ifbrainval.get(i)[1];
					}
				     }
				json22.put("data",value);
				jsonArray1.put(json22);
				model.addAttribute("name",classnameBuffer.substring(0, classnameBuffer.length()-1));
		   }  
		   List<Object[]> ifbrainval2= _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),1,coursefind.getCourseLevel());	
		   //课堂训练柱状图
		   if(ifbrainval.size()>0){
			   JSONObject json21=new JSONObject();
			   json21.put("name", "'应用'");
			   json21.put("type", "'bar'");
			    String[] value=new String[ifbrainval2.size()];
				for(int i=0;i<ifbrainval2.size();i++){
					value[i]=(String)ifbrainval2.get(i)[1];
				     }
				json21.put("data",value);
				jsonArray1.put(json21);
		   }  
		   model.addAttribute("value",jsonArray1.toString().replace("\"", ""));
		   IfbrainIndex classifbrainindex = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(ifbrainindex.get(0).getOrdinalNumber(), childlist, coursefind.getCourseLevel());
		     
		     if(classifbrainindex==null){
		    	 model.addAttribute("classifbrainindex", new BigDecimal(0));
			      model.addAttribute("AfterclassIncome", new BigDecimal(0));
		     }else{
		     BigDecimal classifbrainindexs = new BigDecimal(classifbrainindex.getKnowledgetotalScore()).add(new BigDecimal(classifbrainindex.getApplicationtotalScore()));
		     model.addAttribute("classifbrainindex", classifbrainindexs);
		      model.addAttribute("AfterclassIncome",  classifbrainindex.getAfterclassIncome());
		     }
		     //
		     List<IfbrainIndex> ifbrainindexs =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlist,coursefind.getCourseLevel());
		     if(ifbrainindexs.size()>0){
		    	   model.addAttribute("ordinalNumber", "");
		     }
		     //评论列表
		     List <Comment> commentlist =_commentResponsitory.findByIfbrainIndex(classifbrainindex);
		   //  List <Comment> commentlist =_commentResponsitory.findByCommentList(ifbrain.getOrdinalNumber(),coursefind.getCourseLevel());
		     model.addAttribute("commentlist", commentlist);
		     
		     String ordinal =ifbrainindex.get(0).getOrdinalNumber().toString();
		      CourseCode courseCode = _ccourseCodeRepository.findByOrdinalNumberAndCourseId(ordinal, coursefind.getId());
		      if(courseCode!=null){
		         model.addAttribute("courseCode", courseCode);
		     }else{
		    	  model.addAttribute("courseCode", null);
		     }
	   }
	   else{
		   model.addAttribute("commentlist", "");
		   model.addAttribute("type", "");
		     model.addAttribute("ifbrainindexa", "");
			 model.addAttribute("value", "");
  			 model.addAttribute("name", "");
  			model.addAttribute("ifbrainindexvalue","");
  	  		model.addAttribute("ifbrainindexname", "");
  	  	  model.addAttribute("classifbrainindex", new BigDecimal(0));
	      model.addAttribute("AfterclassIncome", new BigDecimal(0));
	   }
	   }else{
		   model.addAttribute("flagvalue","");
	   }
	   return "/assessment/ifbrainindex";
	   
   }
   
   @RequestMapping(value="/addifbrainindexcon", method = RequestMethod.GET)
	public String addifbrainindexcon(HttpServletRequest request,HttpServletResponse response,Model model){
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlists =_cChildRepository.findByUser(user);
	   Integer childlist=Integer.parseInt(request.getParameter("childId"));
	   String ordinalNumber=request.getParameter("ordinalNumber");
	   Integer courseid=Integer.parseInt(request.getParameter("courseid"));
	   if(childlists.size()>0){
		   List <Course> course = _cCourseReponsitory.findAll();
			model.addAttribute("course", course);
	    	model.addAttribute("childs", childlists);
	    	  model.addAttribute("courseid", courseid);
	    	  Course coursefind = _cCourseReponsitory.findOne(courseid);
	   model.addAttribute("flagvalue",childlist);
		Child child = _cChildRepository.findOne(Integer.parseInt(request.getParameter("childId")));
		if(course.size()>0){
			Integer classid=Integer.parseInt(request.getParameter("classid"));
			 List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndClassId(childlist,classid);
		//	 List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlist,coursefind.getCourseLevel());
		      model.addAttribute("ifbrainindex",ifbrainindex);
		}else{
			    model.addAttribute("ifbrainindex","");
		}
		
		 List<Object[]> ifbrainIndexFindByClass=_iIfbrainIndexReponsitory.findClassIdByChildId(childlist);
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
		   //课堂知识加应用
		   BigDecimal total=new BigDecimal(0);
		   total=total.add(new BigDecimal(ifbrain.getApplicationtotalScore())).add(new BigDecimal(ifbrain.getKnowledgetotalScore()));
		    //折线图
		   List<IfbrainIndex> ifbrainIndexlist = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel1(Integer.parseInt(ordinalNumber),child.getId(),coursefind.getCourseLevel());
	  		StringBuffer name=new StringBuffer();
	  		String chartvalue="";
	  		String names="";
	  		JSONArray allarray=new JSONArray();
	  		int[] values=new int[ifbrainIndexlist.size()];
	  		int[] values1=new int[ifbrainIndexlist.size()];
	  		JSONObject object=new JSONObject();
	  		JSONObject objecta=new JSONObject();
	  		//第一条折线财脑指数
				object.put("name", "'财脑指数'");
				object.put("type", "'line'");
				/*object.put("stack", "'总量'");*/
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
				if(ifbrainIndexlist.size()>0){
	  		   for(int i=0;i<ifbrainIndexlist.size();i++){
	  			//曲线图横坐标
	  			name.append("第"+ifbrainIndexlist.get(i).getOrdinalNumber()+"节");
	  			name.append(",");
	  			//第一条曲线数据
	  			if(ifbrainIndexlist.get(i).getIfbrainIndex()!=null){
	  				values[i]=ifbrainIndexlist.get(i).getIfbrainIndex();
	  			}
	  			else{
	  				values[i]=0;
	  			}
	  			object.put("data", values);
	  			//第二条曲线图
	  			BigDecimal secondvalue=new BigDecimal(0);
	  			secondvalue=secondvalue.add(new BigDecimal(ifbrainIndexlist.get(i).getKnowledgetotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getApplicationtotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getAfterclassIncome()));
	  			values1[i]=secondvalue.intValue();
	  		}
			  	//第二条折线当前课程财脑得分=知识+应用+课后训练
			  		objecta.put("name", "'当前课程财脑得分=知识+应用+课后训练'");
			  		objecta.put("type", "'line'");
			  		objecta.put("stack", "'总量'");
			  		objecta.put("itemStyle", str3);
			  		objecta.put("data", values1);
			  		allarray.put(objecta);
			  		allarray.put(object);
			  		 chartvalue = allarray.toString().replace("\"", "");
			  		 names = name.substring(0, name.length()-1).toString();
				} 
				model.addAttribute("ifbrainindexvalue",chartvalue);
		  		model.addAttribute("ifbrainindexname", names);
		  		
		  		
		
		   List<Object[]> ifbrainval = _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),0,coursefind.getCourseLevel());	
		   StringBuffer classnameBuffer=new StringBuffer();
		   JSONArray jsonArray1=new JSONArray();
		   
		   //课堂训练柱状图
		   if(ifbrainval.size()>0){
			   JSONObject json22=new JSONObject();
			   json22.put("name", "'知识'");
			   json22.put("type", "'bar'");
				String[] value=new String[ifbrainval.size()];
				for(int i=0;i<ifbrainval.size();i++){
					String varName=(String)ifbrainval.get(i)[0];
					classnameBuffer.append(varName);
					classnameBuffer.append(",");
					if(ifbrainval.get(i)[1]==""){
						value[i]="0";
					}else{
						value[i]=(String)ifbrainval.get(i)[1];
					}
				     }
				json22.put("data",value);
				jsonArray1.put(json22);
				model.addAttribute("name",classnameBuffer.substring(0, classnameBuffer.length()-1));
		   }  
		   List<Object[]> ifbrainval2= _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),1,coursefind.getCourseLevel());	
		   //课堂训练柱状图
		   if(ifbrainval2.size()>0){
			   JSONObject json21=new JSONObject();
			   json21.put("name", "'应用'");
			   json21.put("type", "'bar'");
			    String[] value=new String[ifbrainval2.size()];
				for(int i=0;i<ifbrainval2.size();i++){
					value[i]=(String)ifbrainval2.get(i)[1];
				     }
				json21.put("data",value);
				jsonArray1.put(json21);
		   }  
		   model.addAttribute("value",jsonArray1.toString().replace("\"", ""));
		   IfbrainIndex classifbrainindex = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber), childlist, coursefind.getCourseLevel());
		     
		     if(classifbrainindex==null){
		    	 model.addAttribute("classifbrainindex", new BigDecimal(0));
			      model.addAttribute("AfterclassIncome", new BigDecimal(0));
		     }else{
		     BigDecimal classifbrainindexs = new BigDecimal(classifbrainindex.getKnowledgetotalScore()).add(new BigDecimal(classifbrainindex.getApplicationtotalScore()));
		     model.addAttribute("classifbrainindex", classifbrainindexs);
		      model.addAttribute("AfterclassIncome",  classifbrainindex.getAfterclassIncome());
		     }
		     //
		     List<IfbrainIndex> ifbrainindexs =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlist,coursefind.getCourseLevel());
		     if(ifbrainindexs.size()>0){
		    	   model.addAttribute("ordinalNumber", ordinalNumber);
		     }
		     
		     
		     String ifbrainindexconcomment=request.getParameter("ifbrainindexconcomment");
		     Comment comment = new Comment();
			   comment.setContent(ifbrainindexconcomment);;
			   comment.setCreateTime(new Date());
			   comment.setType("0");
			   comment.setUsrtype("0");
			   comment.setUser(user);
			   comment.setIfbrainIndex(ifbrain);
			   _commentResponsitory.save(comment);
			   
			 //评论列表
			   List <Comment> commentlist =_commentResponsitory.findByIfbrainIndex(classifbrainindex);
			   //  List <Comment> commentlist =_commentResponsitory.findByCommentList(Integer.parseInt(ordinalNumber),coursefind.getCourseLevel());
			     model.addAttribute("commentlist", commentlist);
			     
			     String ordinal =ordinalNumber;
			      CourseCode courseCode = _ccourseCodeRepository.findByOrdinalNumberAndCourseId(ordinal, coursefind.getId());
			      if(courseCode!=null){
			         model.addAttribute("courseCode", courseCode);
			     }else{
			    	  model.addAttribute("courseCode", null);
			     }
	   }
	   else{
		   model.addAttribute("type", "");
		     model.addAttribute("ifbrainindexa", "");
			 model.addAttribute("value", "");
 			 model.addAttribute("name", "");
 			model.addAttribute("ifbrainindexvalue","");
 	  		model.addAttribute("ifbrainindexname", "");
 	  	  model.addAttribute("classifbrainindex", new BigDecimal(0));
	      model.addAttribute("AfterclassIncome", new BigDecimal(0));
	      model.addAttribute("commentlist", "");
	   }
	   }else{
		   model.addAttribute("flagvalue","");
	   }
	   return "/assessment/ifbrainindex";
	   
  }
   
   
   
   
   
   
   
   //点击柱状图评论
   
   @RequestMapping(value="/addifbrainindexclassscorec", method = RequestMethod.GET)
	public String addifbrainindexclassscorec(HttpServletRequest request,HttpServletResponse response,Model model){
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlists =_cChildRepository.findByUser(user);
	   Integer childlist=Integer.parseInt(request.getParameter("childId"));
	   String ordinalNumber=request.getParameter("ordinalNumber");
	   Integer courseid=Integer.parseInt(request.getParameter("courseid"));
	   if(childlists.size()>0){
		   List <Course> course = _cCourseReponsitory.findAll();
			model.addAttribute("course", course);
	    	model.addAttribute("childs", childlists);
	    	  model.addAttribute("courseid", courseid);
	    	  Course coursefind = _cCourseReponsitory.findOne(courseid);
	   model.addAttribute("flagvalue",childlist);
		Child child = _cChildRepository.findOne(Integer.parseInt(request.getParameter("childId")));
		if(course.size()>0){
			Integer classid=Integer.parseInt(request.getParameter("classid"));
			 List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndClassId(childlist,classid);
		      model.addAttribute("ifbrainindex",ifbrainindex);
		}else{
			    model.addAttribute("ifbrainindex","");
		}
		
		 List<Object[]> ifbrainIndexFindByClass=_iIfbrainIndexReponsitory.findClassIdByChildId(childlist);
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
		   //课堂知识加应用
		   BigDecimal total=new BigDecimal(0);
		   total=total.add(new BigDecimal(ifbrain.getApplicationtotalScore())).add(new BigDecimal(ifbrain.getKnowledgetotalScore()));
		    //折线图
		   List<IfbrainIndex> ifbrainIndexlist = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel1(Integer.parseInt(ordinalNumber),child.getId(),coursefind.getCourseLevel());
	  		StringBuffer name=new StringBuffer();
	  		String chartvalue="";
	  		String names="";
	  		JSONArray allarray=new JSONArray();
	  		int[] values=new int[ifbrainIndexlist.size()];
	  		int[] values1=new int[ifbrainIndexlist.size()];
	  		JSONObject object=new JSONObject();
	  		JSONObject objecta=new JSONObject();
	  		//第一条折线财脑指数
				object.put("name", "'财脑指数'");
				object.put("type", "'line'");
				/*object.put("stack", "'总量'");*/
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
				if(ifbrainIndexlist.size()>0){
	  		   for(int i=0;i<ifbrainIndexlist.size();i++){
	  			//曲线图横坐标
	  			name.append("第"+ifbrainIndexlist.get(i).getOrdinalNumber()+"节");
	  			name.append(",");
	  			//第一条曲线数据
	  			if(ifbrainIndexlist.get(i).getIfbrainIndex()!=null){
	  				values[i]=ifbrainIndexlist.get(i).getIfbrainIndex();
	  			}
	  			else{
	  				values[i]=0;
	  			}
	  			object.put("data", values);
	  			//第二条曲线图
	  			BigDecimal secondvalue=new BigDecimal(0);
	  			secondvalue=secondvalue.add(new BigDecimal(ifbrainIndexlist.get(i).getKnowledgetotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getApplicationtotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getAfterclassIncome()));
	  			values1[i]=secondvalue.intValue();
	  		}
			  	//第二条折线当前课程财脑得分=知识+应用+课后训练
			  		objecta.put("name", "'当前课程财脑得分=知识+应用+课后训练'");
			  		objecta.put("type", "'line'");
			  		objecta.put("stack", "'总量'");
			  		objecta.put("itemStyle", str3);
			  		objecta.put("data", values1);
			  		allarray.put(objecta);
			  		allarray.put(object);
			  		 chartvalue = allarray.toString().replace("\"", "");
			  		 names = name.substring(0, name.length()-1).toString();
				} 
				model.addAttribute("ifbrainindexvalue",chartvalue);
		  		model.addAttribute("ifbrainindexname", names);
		  		
		  		
		
		   List<Object[]> ifbrainval = _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),0,coursefind.getCourseLevel());	
		   StringBuffer classnameBuffer=new StringBuffer();
		   JSONArray jsonArray1=new JSONArray();
		   
		   //课堂训练柱状图
		   if(ifbrainval.size()>0){
			   JSONObject json22=new JSONObject();
			   json22.put("name", "'知识'");
			   json22.put("type", "'bar'");
				String[] value=new String[ifbrainval.size()];
				for(int i=0;i<ifbrainval.size();i++){
					String varName=(String)ifbrainval.get(i)[0];
					classnameBuffer.append(varName);
					classnameBuffer.append(",");
					if(ifbrainval.get(i)[1]==""){
						value[i]="0";
					}else{
						value[i]=(String)ifbrainval.get(i)[1];
					}
				     }
				json22.put("data",value);
				jsonArray1.put(json22);
				model.addAttribute("name",classnameBuffer.substring(0, classnameBuffer.length()-1));
		   }  
		   List<Object[]> ifbrainval2= _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),1,coursefind.getCourseLevel());	
		   //课堂训练柱状图
		   if(ifbrainval2.size()>0){
			   JSONObject json21=new JSONObject();
			   json21.put("name", "'应用'");
			   json21.put("type", "'bar'");
			    String[] value=new String[ifbrainval2.size()];
				for(int i=0;i<ifbrainval2.size();i++){
					value[i]=(String)ifbrainval2.get(i)[1];
				     }
				json21.put("data",value);
				jsonArray1.put(json21);
		   }  
		   model.addAttribute("value",jsonArray1.toString().replace("\"", ""));
		   IfbrainIndex classifbrainindex = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber), childlist, coursefind.getCourseLevel());
		     
		     if(classifbrainindex==null){
		    	 model.addAttribute("classifbrainindex", new BigDecimal(0));
			      model.addAttribute("AfterclassIncome", new BigDecimal(0));
		     }else{
		     BigDecimal classifbrainindexs = new BigDecimal(classifbrainindex.getKnowledgetotalScore()).add(new BigDecimal(classifbrainindex.getApplicationtotalScore()));
		     model.addAttribute("classifbrainindex", classifbrainindexs);
		      model.addAttribute("AfterclassIncome",  classifbrainindex.getAfterclassIncome());
		     }
		     //
		     List<IfbrainIndex> ifbrainindexs =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlist,coursefind.getCourseLevel());
		     if(ifbrainindexs.size()>0){
		    	   model.addAttribute("ordinalNumber", ordinalNumber);
		     }
		    
		     String classscoreccontent=request.getParameter("classscoreccontent");
		     Comment comment = new Comment();
			   comment.setContent(classscoreccontent);;
			   comment.setCreateTime(new Date());
			   comment.setType("1");
			   comment.setUsrtype("0");
			   comment.setUser(user);
			   comment.setIfbrainIndex(ifbrain);
			   _commentResponsitory.save(comment);
			   
			   
			   //评论列表
			   List <Comment> commentlist =_commentResponsitory.findByIfbrainIndex(classifbrainindex);
			   //  List <Comment> commentlist =_commentResponsitory.findByCommentList(Integer.parseInt(ordinalNumber),coursefind.getCourseLevel());
			     model.addAttribute("commentlist", commentlist);
			     
	   }
	   else{
		   model.addAttribute("commentlist", "");
		   model.addAttribute("type", "");
		     model.addAttribute("ifbrainindexa", "");
			 model.addAttribute("value", "");
			 model.addAttribute("name", "");
			model.addAttribute("ifbrainindexvalue","");
	  		model.addAttribute("ifbrainindexname", "");
	  	  model.addAttribute("classifbrainindex", new BigDecimal(0));
	      model.addAttribute("AfterclassIncome", new BigDecimal(0));
	   }
	   }else{
		   model.addAttribute("flagvalue","");
	   }
	   return "/assessment/ifbrainindex";
	   
 }
  	
   
   //删除评论
   @RequestMapping(value="/deletecommentlistindex", method = RequestMethod.GET)
  	public String deletecommentlistindex(HttpServletRequest request,HttpServletResponse response,Model model){
  	   User user = (User) request.getSession().getAttribute("user");
  	   model.addAttribute("user",user);
  	   List<Child> childlists =_cChildRepository.findByUser(user);
  	   Integer childlist=Integer.parseInt(request.getParameter("childId"));
  	   String ordinalNumber=request.getParameter("ordinalNumber");
  	   Integer courseid=Integer.parseInt(request.getParameter("courseid"));
  	   if(childlists.size()>0){
  		   List <Course> course = _cCourseReponsitory.findAll();
  			model.addAttribute("course", course);
  	    	model.addAttribute("childs", childlists);
  	    	  model.addAttribute("courseid", courseid);
  	    	  Course coursefind = _cCourseReponsitory.findOne(courseid);
  	   model.addAttribute("flagvalue",childlist);
  		Child child = _cChildRepository.findOne(Integer.parseInt(request.getParameter("childId")));
  		if(course.size()>0){
  			Integer classid=Integer.parseInt(request.getParameter("classid"));
			 List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndClassId(childlist,classid);
  		      model.addAttribute("ifbrainindex",ifbrainindex);
  		}else{
  			    model.addAttribute("ifbrainindex","");
  		}
  		
  		 List<Object[]> ifbrainIndexFindByClass=_iIfbrainIndexReponsitory.findClassIdByChildId(childlist);
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
  		
  		  //删除
		     Integer deleteid =Integer.parseInt(request.getParameter("deleteid"));
		     _commentResponsitory.delete(deleteid);
		     IfbrainIndex ifbrain = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber),child.getId(),coursefind.getCourseLevel());
 			   //评论列表
		     List <Comment> commentlist =_commentResponsitory.findByIfbrainIndex(ifbrain);
 			//     List <Comment> commentlist =_commentResponsitory.findByCommentList(Integer.parseInt(ordinalNumber),coursefind.getCourseLevel());
 			     model.addAttribute("commentlist", commentlist);
  	   if(ifbrain!=null){
  		   model.addAttribute("ifbrainindexa", ifbrain);
  		   //课堂知识加应用
  		   BigDecimal total=new BigDecimal(0);
  		   total=total.add(new BigDecimal(ifbrain.getApplicationtotalScore())).add(new BigDecimal(ifbrain.getKnowledgetotalScore()));
  		    //折线图
  		   List<IfbrainIndex> ifbrainIndexlist = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel1(Integer.parseInt(ordinalNumber),child.getId(),coursefind.getCourseLevel());
  	  		StringBuffer name=new StringBuffer();
  	  		String chartvalue="";
  	  		String names="";
  	  		JSONArray allarray=new JSONArray();
  	  		int[] values=new int[ifbrainIndexlist.size()];
  	  		int[] values1=new int[ifbrainIndexlist.size()];
  	  		JSONObject object=new JSONObject();
  	  		JSONObject objecta=new JSONObject();
  	  		//第一条折线财脑指数
  				object.put("name", "'财脑指数'");
  				object.put("type", "'line'");
  				/*object.put("stack", "'总量'");*/
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
  				if(ifbrainIndexlist.size()>0){
  	  		   for(int i=0;i<ifbrainIndexlist.size();i++){
  	  			//曲线图横坐标
  	  			name.append("第"+ifbrainIndexlist.get(i).getOrdinalNumber()+"节");
  	  			name.append(",");
  	  			//第一条曲线数据
  	  			if(ifbrainIndexlist.get(i).getIfbrainIndex()!=null){
  	  				values[i]=ifbrainIndexlist.get(i).getIfbrainIndex();
  	  			}
  	  			else{
  	  				values[i]=0;
  	  			}
  	  			object.put("data", values);
  	  			//第二条曲线图
  	  			BigDecimal secondvalue=new BigDecimal(0);
  	  			secondvalue=secondvalue.add(new BigDecimal(ifbrainIndexlist.get(i).getKnowledgetotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getApplicationtotalScore())).add(new BigDecimal(ifbrainIndexlist.get(i).getAfterclassIncome()));
  	  			values1[i]=secondvalue.intValue();
  	  		}
  			  	//第二条折线当前课程财脑得分=知识+应用+课后训练
  			  		objecta.put("name", "'当前课程财脑得分=知识+应用+课后训练'");
  			  		objecta.put("type", "'line'");
  			  		objecta.put("stack", "'总量'");
  			  		objecta.put("itemStyle", str3);
  			  		objecta.put("data", values1);
  			  		allarray.put(objecta);
  			  		allarray.put(object);
  			  		 chartvalue = allarray.toString().replace("\"", "");
  			  		 names = name.substring(0, name.length()-1).toString();
  				} 
  				model.addAttribute("ifbrainindexvalue",chartvalue);
  		  		model.addAttribute("ifbrainindexname", names);
  		  		
  		  		
  		
  		   List<Object[]> ifbrainval = _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),0,coursefind.getCourseLevel());	
  		   StringBuffer classnameBuffer=new StringBuffer();
  		   JSONArray jsonArray1=new JSONArray();
  		   
  		   //课堂训练柱状图
  		   if(ifbrainval.size()>0){
  			   JSONObject json22=new JSONObject();
  			   json22.put("name", "'知识'");
  			   json22.put("type", "'bar'");
  				String[] value=new String[ifbrainval.size()];
  				for(int i=0;i<ifbrainval.size();i++){
  					String varName=(String)ifbrainval.get(i)[0];
  					classnameBuffer.append(varName);
  					classnameBuffer.append(",");
  					if(ifbrainval.get(i)[1]==""){
  						value[i]="0";
  					}else{
  						value[i]=(String)ifbrainval.get(i)[1];
  					}
  				     }
  				json22.put("data",value);
  				jsonArray1.put(json22);
  				model.addAttribute("name",classnameBuffer.substring(0, classnameBuffer.length()-1));
  		   }  
  		   List<Object[]> ifbrainval2= _IfbrainVarReponsitory.findByvarnameAndvalue(childlist,ifbrain.getOrdinalNumber(),1,coursefind.getCourseLevel());	
  		   //课堂训练柱状图
  		   if(ifbrainval2.size()>0){
  			   JSONObject json21=new JSONObject();
  			   json21.put("name", "'应用'");
  			   json21.put("type", "'bar'");
  			    String[] value=new String[ifbrainval2.size()];
  				for(int i=0;i<ifbrainval2.size();i++){
  					value[i]=(String)ifbrainval2.get(i)[1];
  				     }
  				json21.put("data",value);
  				jsonArray1.put(json21);
  		   }  
  		   model.addAttribute("value",jsonArray1.toString().replace("\"", ""));
  		   IfbrainIndex classifbrainindex = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber), childlist, coursefind.getCourseLevel());
  		     
  		     if(classifbrainindex==null){
  		    	 model.addAttribute("classifbrainindex", new BigDecimal(0));
  			      model.addAttribute("AfterclassIncome", new BigDecimal(0));
  		     }else{
  		     BigDecimal classifbrainindexs = new BigDecimal(classifbrainindex.getKnowledgetotalScore()).add(new BigDecimal(classifbrainindex.getApplicationtotalScore()));
  		     model.addAttribute("classifbrainindex", classifbrainindexs);
  		      model.addAttribute("AfterclassIncome",  classifbrainindex.getAfterclassIncome());
  		     }
  		     //
  		     List<IfbrainIndex> ifbrainindexs =	_iIfbrainIndexReponsitory.findByChildIdAndCourseLevel(childlist,coursefind.getCourseLevel());
  		     if(ifbrainindexs.size()>0){
  		    	   model.addAttribute("ordinalNumber", ordinalNumber);
  		     }
  		    
  		 
  			     
  	   }
  	   else{
  		   model.addAttribute("commentlist", "");
  		   model.addAttribute("type", "");
  		     model.addAttribute("ifbrainindexa", "");
  			 model.addAttribute("value", "");
  			 model.addAttribute("name", "");
  			model.addAttribute("ifbrainindexvalue","");
  	  		model.addAttribute("ifbrainindexname", "");
  	  	  model.addAttribute("classifbrainindex", new BigDecimal(0));
  	      model.addAttribute("AfterclassIncome", new BigDecimal(0));
  	   	}
  	   }else{
  		   model.addAttribute("flagvalue","");
  	   }
  	   return "/assessment/ifbrainindex";
  	   
   }
}
