package com.jzeen.travel.website.controller.coursemovie;
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
 * 评估
 * @author sunyan
 *
 */
@Controller
@RequestMapping("/coursemovie")
public class CourseMovieController
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
   @RequestMapping(value = "/coursemovie",method = RequestMethod.GET)
   public  String coursemovie(Model model, HttpServletRequest request,HttpSession session)
   {
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlist =_cChildRepository.findByUser(user);
       if(childlist.size()>0){
    	   List <Course> course = _cCourseReponsitory.findAll();
    	model.addAttribute("childs", childlist);
   	  Integer childlists=Integer.parseInt(request.getParameter("childId"));
      model.addAttribute("flagvalue", childlists);
      List<Object[]> ifbrainIndexFindByClass=_iIfbrainIndexReponsitory.findClassIdByChildId(childlists);
      if(ifbrainIndexFindByClass.size()>0){
	     List <CourseClass> listfindbyclass = new ArrayList<CourseClass> ();
			if (ifbrainIndexFindByClass.size() > 0) {
				for (int i = 0; i < ifbrainIndexFindByClass.size(); i++) {
					Integer varName = (Integer) ifbrainIndexFindByClass.get(i)[1];
					List<CourseClass> courseclass = _cCourseClassReponsitory.findByclassId(varName);
					listfindbyclass.add(courseclass.get(0));
				}
				model.addAttribute("listfindbyclass", listfindbyclass);
				model.addAttribute("listfindbyclassid", listfindbyclass.get(0).getId());
			} else {
				model.addAttribute("listfindbyclass", "");
				model.addAttribute("listfindbyclassid", "");
			}
		     Child child = _cChildRepository.findOne(childlists);
		      //视频播放展示
		      model.addAttribute("courseid", listfindbyclass.get(0).getCourseId().getId());
		      List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndClassId(child.getId(),listfindbyclass.get(0).getId());
		      model.addAttribute("ifbrainindex",ifbrainindex);
		      //视频
		      if(ifbrainindex.size()>0){
		    	IfbrainIndex ifbrainindexlist = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(ifbrainindex.get(0).getOrdinalNumber(), childlists, course.get(0).getCourseLevel());
		    	  if(ifbrainindexlist!=null){
		    	Integer classid= ifbrainindexlist.getClassId();
		    	 List <Video> video= _viVideoReponsitory.findByOrdinalNumberAndCourseLevelAndClassId(ifbrainindex.get(0).getOrdinalNumber(), course.get(0).getCourseLevel(),classid);
		    	  if(video.size()>0){
			    	  model.addAttribute("showvideolist",video.get(0));
			      }else{
			    	  model.addAttribute("showvideolist","");
			      }
		    	 
		    	}else{
		    		 model.addAttribute("showvideolist","");
		    	}
		      model.addAttribute("ordinalNumber", ifbrainindex.get(0).getOrdinalNumber());
		      model.addAttribute("lessonName", ifbrainindex.get(0).getLessonName());
		      model.addAttribute("classId", ifbrainindex.get(0).getClassId());
		      model.addAttribute("classTime", ifbrainindex.get(0).getClass());
		      String ordinal =ifbrainindex.get(0).getOrdinalNumber().toString();
		      CourseCode courseCode = _ccourseCodeRepository.findByOrdinalNumberAndCourseId(ordinal, listfindbyclass.get(0).getCourseId().getId());
		      if(courseCode!=null){
		         model.addAttribute("courseCode", courseCode);
		      }else{
		    	  model.addAttribute("courseCode", null);
		      }
		      
		      }else{
		      model.addAttribute("showvideolist","");
		      model.addAttribute("ifbrainindex","");
		      }
            }else{
               model.addAttribute("ifbrainindex","");
          	   model.addAttribute("showvideolist","");
          	   model.addAttribute("course", "");
          	    model.addAttribute("courseid", "");
          	    model.addAttribute("courseCode", null);
                }
	   }
       else{
    	   model.addAttribute("ifbrainindex","");
    	   model.addAttribute("showvideolist","");
    	   model.addAttribute("flagvalue","");
    	   model.addAttribute("childs", null);
    	   model.addAttribute("course", "");
    	    model.addAttribute("courseid", "");
    	    model.addAttribute("courseCode", null);
       }
	   return "/assessment/coursemovie";
   }

    	
   //点击第几节课显示相应内容
	@RequestMapping(value="/showvideoinformation", method = RequestMethod.GET)
	public String showvideoinformation(HttpServletRequest request,Model model){
		 User user = (User) request.getSession().getAttribute("user");
		 List<Child> childlist =_cChildRepository.findByUser(user);
			Integer childId =Integer.parseInt(request.getParameter("childId"));
			 model.addAttribute("flagvalue", childId);
			Integer courseid =Integer.parseInt(request.getParameter("courseid"));
			model.addAttribute("courseid", courseid);
			Integer ordinalNumber=Integer.parseInt(request .getParameter("ordinalNumber"));
			model.addAttribute("ordinalNumber", ordinalNumber);
			Integer classId =Integer.parseInt(request.getParameter("classid"));
			model.addAttribute("classId", classId);
			Course courselevel=_cCourseReponsitory.findByCourseLevel(courseid);
			
			List<Object[]> ifbrainIndexFindByClass=_iIfbrainIndexReponsitory.findClassIdByChildId(childId);
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
	       if(childlist.size()>0){
	    	   List <Course> course = _cCourseReponsitory.findAll();
			model.addAttribute("course", course);
	    	model.addAttribute("childs", childlist);
	    		List<IfbrainIndex> ifbrainindex =	_iIfbrainIndexReponsitory.findByChildIdAndClassId(childId,classId);
			      model.addAttribute("ifbrainindex",ifbrainindex);	
			    //视频
			      if(ifbrainindex.size()>0){
			  		IfbrainIndex ifbrain =_iIfbrainIndexReponsitory.findByOrdinalNumberAndClassIdAndChildIdAndCourseLevel(ordinalNumber,classId,childId,courselevel.getCourseLevel());
				if (ifbrain != null) {
					System.out.println("判断不为空!!!!!!!");
					model.addAttribute("lessonName", ifbrain.getLessonName());
					Integer classid = ifbrain.getClassId();
					List<Video> video = _viVideoReponsitory.findByOrdinalNumberAndCourseLevelAndClassId(ordinalNumber,courselevel.getCourseLevel(), classid);
					if (video.size() > 0) {
						model.addAttribute("showvideolist", video.get(0));
					} else {
						model.addAttribute("showvideolist", "");
					}
				} else {
					model.addAttribute("showvideolist", "");
				}
					CourseCode coursecode = _ccourseCodeRepository.findByOrdinalNumberAndCourseId(request.getParameter("ordinalNumber"),courseid);
					 if(coursecode!=null){
				         model.addAttribute("courseCode", coursecode);
				     }else{
				    	  model.addAttribute("courseCode", null);
				     }
			      }else{
				      model.addAttribute("showvideolist","");
				      model.addAttribute("ifbrainindex","");
				      }
	    	    
	    	}else{
	     	   model.addAttribute("ifbrainindex","");
	    	   model.addAttribute("showvideolist","");
	    	   model.addAttribute("flagvalue","");
	    	   model.addAttribute("childs", null);
	    	   model.addAttribute("course", "");
	    	   model.addAttribute("courseid", "");
	    	   model.addAttribute("courseCode", null);
	       }
		return "/assessment/coursemovie";
	}
   //点击显示视频
   @ResponseBody
	@RequestMapping(value="/showvideo", method = RequestMethod.GET)
	public Video showvideo(HttpServletRequest request){
		Integer classId =Integer.parseInt(request.getParameter("classId"));//16
		Integer childId =Integer.parseInt(request.getParameter("childId"));//85
		Integer ordinalNumber=Integer.parseInt(request.getParameter("ordinalNumber"));//2
		Integer courseid =Integer.parseInt(request.getParameter("courseid"));//7
		Course courselevel=_cCourseReponsitory.findByCourseLevel(courseid);//IB L4-财脑4级
		List <Video> video= _viVideoReponsitory.findByOrdinalNumberAndCourseLevelAndClassId(ordinalNumber, courselevel.getCourseLevel(),classId);
		return video.get(0);
	}
   
 //点击第几节课显示相应内容
   @ResponseBody
	@RequestMapping(value="/showcoursecodeinformation", method = RequestMethod.GET)
	public CourseCode showcoursecodeinformation(HttpServletRequest request){
		String ordinalNumber=request.getParameter("ordinalNumber");
		String childId =request.getParameter("childId");
		String courseid =request.getParameter("courseid");
		CourseCode coursecode = _ccourseCodeRepository.findByOrdinalNumberAndCourseId(ordinalNumber, Integer.parseInt(courseid));
		return coursecode;
	}
   
   
   
   //点击课程级别
   @RequestMapping(value = "/showcourseinformations",method = RequestMethod.GET)
   public  String showcourseinformations(Model model, HttpServletRequest request,HttpSession session)
   {
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	   List<Child> childlist =_cChildRepository.findByUser(user);
       if(childlist.size()>0){
			Integer childlists = Integer.parseInt(request.getParameter("childId"));
			Child child = _cChildRepository.findOne(childlists);
			model.addAttribute("childs", childlist);
			model.addAttribute("flagvalue", childlists);
			Integer courseid = Integer.parseInt(request.getParameter("courseid"));
			model.addAttribute("courseid", courseid);
			Course coursefin = _cCourseReponsitory.findOne(courseid);
			List<Object[]> ifbrainIndexFindByClass = _iIfbrainIndexReponsitory.findClassIdByChildId(childlists);
			List<CourseClass> listfindbyclass = new ArrayList<CourseClass>();
			if (ifbrainIndexFindByClass.size() > 0) {
				for (int i = 0; i < ifbrainIndexFindByClass.size(); i++) {
					Integer varName = (Integer) ifbrainIndexFindByClass.get(i)[1];
					List<CourseClass> courseclass = _cCourseClassReponsitory.findByclassId(varName);
					listfindbyclass.add(courseclass.get(0));
				}
				model.addAttribute("listfindbyclass", listfindbyclass);
				model.addAttribute("listfindbyclassid",request.getParameter("classid"));
			} else {
				model.addAttribute("listfindbyclass", "");
				model.addAttribute("listfindbyclassid", "");
			}
		    List<IfbrainIndex> ifbrainindex = _iIfbrainIndexReponsitory.findByChildIdAndClassId(child.getId(),Integer.parseInt(request.getParameter("classid")));
		    model.addAttribute("ifbrainindex", ifbrainindex);
				// 视频1 85 IBL1 财脑1级
				if (ifbrainindex.size() > 0) {
					IfbrainIndex ifbrainindexlist = _iIfbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(ifbrainindex.get(0).getOrdinalNumber(),childlists, coursefin.getCourseLevel());
					if (ifbrainindexlist != null) {
						Integer classid = ifbrainindexlist.getClassId();
						List<Video> video = _viVideoReponsitory.findByOrdinalNumberAndCourseLevelAndClassId(ifbrainindex.get(0).getOrdinalNumber(),coursefin.getCourseLevel(), classid);
						if (video.size() > 0) {
							model.addAttribute("showvideolist", video.get(0));
						} else {
							model.addAttribute("showvideolist", "");
						}
					} else {
						model.addAttribute("showvideolist", "");
					}
					model.addAttribute("ordinalNumber", ifbrainindex.get(0).getOrdinalNumber());
					model.addAttribute("lessonName", ifbrainindex.get(0).getLessonName());
					model.addAttribute("classId", ifbrainindex.get(0).getClassId());
					model.addAttribute("classTime", ifbrainindex.get(0).getClass());
					String ordinal = ifbrainindex.get(0).getOrdinalNumber().toString();
					CourseCode courseCode = _ccourseCodeRepository.findByOrdinalNumberAndCourseId(ordinal,coursefin.getId());
					if (courseCode != null) {
						model.addAttribute("courseCode", courseCode);
					} else {
						model.addAttribute("courseCode", null);
					}
				} else {
					model.addAttribute("showvideolist", "");
					model.addAttribute("ifbrainindex", "");
				}
			}else{
						model.addAttribute("ifbrainindex", "");
						model.addAttribute("showvideolist", "");
						model.addAttribute("flagvalue", "");
						model.addAttribute("childs", null);
						model.addAttribute("course", "");
						model.addAttribute("courseid", "");
						model.addAttribute("courseCode", null);
			}
		 
	   return "/assessment/coursemovie";
   }
}
