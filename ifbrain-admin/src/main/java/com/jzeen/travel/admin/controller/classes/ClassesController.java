package com.jzeen.travel.admin.controller.classes;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.Comment;
import com.jzeen.travel.data.entity.Commodity;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseClass;
import com.jzeen.travel.data.entity.CourseCode;
import com.jzeen.travel.data.entity.IfbrainIndex;
import com.jzeen.travel.data.entity.Member;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.CommentRepository;
import com.jzeen.travel.data.repository.CourseClassReponsitory;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.IfbrainIndexReponsitory;
import com.jzeen.travel.data.repository.IfbrainVarReponsitory;
import com.jzeen.travel.data.repository.MemberRepository;
import com.jzeen.travel.data.repository.UserRepository;

@Controller
@RequestMapping("/classes")
public class ClassesController {
	@Autowired
    private CourseClassReponsitory _cCourseClassReponsitory;
	@Autowired
    private CourseReponsitory _cCourseReponsitory;
	@Autowired
    private CourseCodeRepository _cCourseCodeRepository;
	@Autowired
	private IfbrainIndexReponsitory  _ifbrainIndexReponsitory;
	@Autowired
    private ChildRepository _cChildRepository;
	@Autowired
	private UserRepository _userRepository;
	@Autowired
	private MemberRepository _memberRepository;
	@Autowired
	private CommentRepository _commentRepository;
	@Autowired
	private IfbrainVarReponsitory _IfbrainVarReponsitory;
		//班级信息列表
			@RequestMapping(value = "/list", method = RequestMethod.GET)
		    public String list(HttpServletRequest request, Model model)
		    {
				List<CourseClass> data = _cCourseClassReponsitory.findAll();
				model.addAttribute("list", data);
		        return "/classmanage/list";
		    }
		//datatable列表显示
			@ResponseBody
		    @RequestMapping(value = "/search", method = RequestMethod.GET)
		    public List<CourseClass> search()
		    {
		        List<CourseClass> data = _cCourseClassReponsitory.findAll();
		        return data;
		    }
		//新增班级信息页面		
		 @RequestMapping(value = "/create", method = RequestMethod.GET)
		    public String createInit(@ModelAttribute CourseClass courseclass, Model model,HttpServletRequest request)
		    {
		        List<Course> course = _cCourseReponsitory.findAll();
		        model.addAttribute("course", course);
		        return "/classmanage/create";

		    }
			//新增班级信息实现
		 @RequestMapping(value = "/create", method = RequestMethod.POST)
		    public String create(Model model, HttpServletRequest request) throws ParseException
		    {
		        Course getcourseid=_cCourseReponsitory.findByCourseLevel(Integer.parseInt(request.getParameter("courseLevel")));
		        CourseClass courseclass=new CourseClass();
		        courseclass.setCourseId(getcourseid);
		        courseclass.setCourseLevel(getcourseid.getCourseLevel());
		        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		        courseclass.setStartDate(sdf.parse(request.getParameter("startDate")));
		        courseclass.setEndDate(sdf.parse(request.getParameter("endDate")));
		        courseclass.setCreateDate(new Date());
		        List<CourseClass> coursecode=_cCourseClassReponsitory.findByCourseLevel(getcourseid.getCourseLevel());
		        int size;
		        if(coursecode==null || coursecode.size()==0){
		        	size=1;
		        }else{
		        	 size = coursecode.size()+1;
		        }
		        courseclass.setClassName(getcourseid.getCourseLevel()+"_"+size);
		        courseclass.setChildNumber(Integer.parseInt(request.getParameter("childNumber")));
		        _cCourseClassReponsitory.save(courseclass);
				return "redirect:/classes/list";       
		    }
		 //进入修改页面 
		 @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
		    public String updateInit(@PathVariable int id, Model model)
		    {
		        CourseClass courseclass = _cCourseClassReponsitory.findOne(id);
		        model.addAttribute("courseclass", courseclass);
		        model.addAttribute("courselevels", courseclass.getCourseLevel());
		        List<Course> course = _cCourseReponsitory.findAll();
		        model.addAttribute("course", course);
		        List<CourseCode> ordinalNum=_cCourseCodeRepository.findbycourseCode(course.get(0).getCourseLevel());
		        model.addAttribute("ordinalNum", ordinalNum);
		        return "/classmanage/update";
		    }
		 //修改班级信息
		 @RequestMapping(value = "/update", method = RequestMethod.POST)
		    public String update(@Valid CourseClass courseclass, BindingResult bindingResult, HttpServletRequest request) throws ParseException
		    {   
		       Course getcourseid=_cCourseReponsitory.findByCourseLevel(Integer.parseInt(request.getParameter("courseLevel")));
		       courseclass.setCourseId(getcourseid);
		       courseclass.setCourseLevel(getcourseid.getCourseLevel());
		        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		        courseclass.setStartDate(sdf.parse(request.getParameter("startDate")));
		        courseclass.setEndDate(sdf.parse(request.getParameter("endDate")));
		        courseclass.setCreateDate(new Date());
		        courseclass.setClassName(request.getParameter("className"));
		        courseclass.setChildNumber(Integer.parseInt(request.getParameter("childNumber")));
		        _cCourseClassReponsitory.save(courseclass);
		        return "redirect:/classes/list";
		    }
		 
		//班级信息列表
			@RequestMapping(value = "/childmanagelist/{id}", method = RequestMethod.GET)
		    public String childmanagelist(@PathVariable int id,HttpServletRequest request, Model model)
		    {
				List<IfbrainIndex> ifbrainindexlist = _ifbrainIndexReponsitory.findByClassId(id);
				model.addAttribute("ifbrainindexlist", ifbrainindexlist);
		        return "/classmanage/childmanagelist";
		    }
			//班级信息列表
			@RequestMapping(value = "/tokenbanklist/{id}", method = RequestMethod.GET)
		    public String tokenbanklist(@PathVariable int id,HttpServletRequest request, Model model)
		    {
			List <IfbrainIndex> ifbrainindexlist = _ifbrainIndexReponsitory.findByClassId(id);
			   model.addAttribute("list", ifbrainindexlist);
				model.addAttribute("ifbrainindexlist", id);
		        return "/tokensbank/list";
		    }
			//后台孩子每一节课的财脑指数和每一节课的曲线和柱状图
       @RequestMapping(value="/showchildifbrainchart", method = RequestMethod.GET)
			 	public String showchildifbrainchart(HttpServletRequest request,HttpServletResponse response,Model model){
    	           Member member = (Member) request.getSession().getAttribute("member");
    	           model.addAttribute("memberid", member.getId());
    	           Integer childId=Integer.parseInt(request.getParameter("childid"));
    	           Child child = _cChildRepository.findOne(childId);
    	           model.addAttribute("userid", child.getUser().getId());
    	           model.addAttribute("childid", childId);
				   String ordinalNumber=request.getParameter("ordinalnumber");
				   model.addAttribute("ordinalNumber", ordinalNumber);
				   String courselevel=request.getParameter("courselevel");
				   model.addAttribute("courselevel", courselevel);
				   Course course = _cCourseReponsitory.findByCourseLevel(courselevel);
				   CourseCode courseCode = _cCourseCodeRepository.findByOrdinalNumberAndCourseId(ordinalNumber, course.getId());   
				   IfbrainIndex ifbrain = _ifbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber),childId,courselevel);
				   if(ifbrain!=null){
					   model.addAttribute("ifbrainindexa", ifbrain);
					   model.addAttribute("ifbrainid", ifbrain.getId());
					   model.addAttribute("courseCode", courseCode);
					   //根据财脑指数查询评论
					   List<Comment> list = _commentRepository.findByIfbrainIndex(ifbrain);
					   model.addAttribute("commentList", list);
					   //课堂知识加应用
					   BigDecimal total=new BigDecimal(0);
					   total=total.add(new BigDecimal(ifbrain.getApplicationtotalScore())).add(new BigDecimal(ifbrain.getKnowledgetotalScore()));
					    //折线图
					   List<IfbrainIndex> ifbrainIndexlist = _ifbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel1(Integer.parseInt(ordinalNumber),childId,courselevel);
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
					  		
					   List<Object[]> ifbrainval = _IfbrainVarReponsitory.findByvarnameAndvalue(childId,ifbrain.getOrdinalNumber(),0,courselevel);	
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
					   List<Object[]> ifbrainval2= _IfbrainVarReponsitory.findByvarnameAndvalue(childId,ifbrain.getOrdinalNumber(),1,courselevel);	
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
					   IfbrainIndex classifbrainindex = _ifbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(Integer.parseInt(ordinalNumber), childId, courselevel);
					     
					     if(classifbrainindex==null){
					    	 model.addAttribute("classifbrainindex", new BigDecimal(0));
						      model.addAttribute("AfterclassIncome", new BigDecimal(0));
					     }else{
					     BigDecimal classifbrainindexs = new BigDecimal(classifbrainindex.getKnowledgetotalScore()).add(new BigDecimal(classifbrainindex.getApplicationtotalScore()));
					     model.addAttribute("classifbrainindex", classifbrainindexs);
					      model.addAttribute("AfterclassIncome",  classifbrainindex.getAfterclassIncome());
					     }
					    /* List<IfbrainIndex> ifbrainindexs =	_ifbrainIndexReponsitory.findByChildIdAndCourseLevel(childId,courselevel);
					     if(ifbrainindexs.size()>0){
					    	   model.addAttribute("ordinalNumber", ordinalNumber);
					     }*/
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
				      model.addAttribute("courseCode", "");
				   }
				   return "/classmanage/ifbrainindex";
			   }
		     /**
		   	 * 老师评论
		   	 * @param request
		   	 * @param response
		   	 * @param model
		   	 * @return
		   	 */
		   	@ResponseBody
		   	@RequestMapping(value = "/comment", method = RequestMethod.GET)
		   	public IfbrainIndex findById(HttpServletRequest request,HttpServletResponse response, Model model) {
		   		String userid = request.getParameter("userid");
		   		String memberid = request.getParameter("memberid");
		   		String content = request.getParameter("content");
		   		String ifbrainid = request.getParameter("ifbrainid");
		   		String type = request.getParameter("type");
		   		String ordinalNumber = request.getParameter("ordinalNumber");
		   		String courselevel = request.getParameter("courselevel");
		   		String childid = request.getParameter("childid");
		   		IfbrainIndex ifbrainIndex = _ifbrainIndexReponsitory.findOne(Integer.parseInt(ifbrainid));
		   		User user = _userRepository.findOne(Integer.parseInt(userid));
		   		Member member = _memberRepository.findOne(Integer.parseInt(memberid));
		   		Comment comment=new Comment();
		   		comment.setIfbrainIndex(ifbrainIndex);
		   		comment.setUser(user);
		   		comment.setCreateTime(new Date());
		   		comment.setContent(content);
		   		comment.setType(type);
		   		comment.setUsrtype("1");
		   		comment.setMember(member);
		   		_commentRepository.save(comment);
		   		//返回数据
		   		IfbrainIndex ifbrain =new IfbrainIndex();
		   		ifbrain.setOrdinalNumber(Integer.parseInt(ordinalNumber));
		   		ifbrain.setCourseLevel(courselevel);
		   		ifbrain.setId(Integer.parseInt(childid));
		   		ifbrain.setLessonName(content);
		   		ifbrain.setApplicationtotalScore("评论成功!");
		   		return ifbrain;
		   	}	
		   	
		   	
		   	
		   	@ResponseBody
		    @RequestMapping(value = "/deletecommentlistindex", method = RequestMethod.GET)
		    public IfbrainIndex deletecommentlistindex(HttpServletRequest request)
		    {
		   		String content = request.getParameter("content");
		   		String ordinalNumber = request.getParameter("ordinalNumber");
		   		String courselevel = request.getParameter("courselevel");
		   		String childid = request.getParameter("childid");
		   		String deleteid = request.getParameter("deleteid");
		   		_commentRepository.delete(Integer.parseInt(deleteid));
		   		//返回数据
		   		IfbrainIndex ifbrain =new IfbrainIndex();
		   		ifbrain.setOrdinalNumber(Integer.parseInt(ordinalNumber));
		   		ifbrain.setCourseLevel(courselevel);
		   		ifbrain.setId(Integer.parseInt(childid));
		   		ifbrain.setLessonName(content);
		   		ifbrain.setApplicationtotalScore("删除成功!");
		   		return ifbrain;
		    }
			
			
			
}
