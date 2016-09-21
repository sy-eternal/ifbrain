package com.jzeen.travel.admin.controller.childinformation;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseClass;
import com.jzeen.travel.data.entity.CourseCode;
import com.jzeen.travel.data.entity.IfbrainIndex;
import com.jzeen.travel.data.entity.IfbrainVar;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.Video;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.CourseClassReponsitory;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.IfbrainIndexReponsitory;
import com.jzeen.travel.data.repository.IfbrainVarReponsitory;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.VideoReponsitory;

@Controller
@RequestMapping("/childinformation")
public class ChildInformationController {
	@Autowired
	private CourseReponsitory _courseReponsitory;
	@Autowired
	private CourseClassReponsitory  _courseClassRepository;
	@Autowired
	private UserRepository  _userRepository;
	@Autowired
	private ChildRepository  _childRepository;
	@Autowired
    FileUploadSetting _fileUploadSetting;
	@Autowired
	VideoReponsitory _videoRepository;
	@Autowired
	IfbrainVarReponsitory _ifbrainVarReponsitory;
	@Autowired
	IfbrainIndexReponsitory  _ifbrainIndexReponsitory;
	@Autowired
    private CourseCodeRepository _cCourseCodeRepository;
	//上传孩子信息列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
		 List<Child> childlist = _childRepository.findAll();
		 model.addAttribute("childlist",childlist);
         return "/childinformation/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<Child> search()
    {
        List<Child> data = _childRepository.findAll();
        return data;
    }
		
		//新增上传孩子信息
		@RequestMapping(value = "/create", method = RequestMethod.GET)
	    public String create(HttpServletRequest request, Model model)
	    {
			List<Course> courseList = _courseReponsitory.findAll();
			List<User> userList = _userRepository.findAll();
			model.addAttribute("courseList", courseList);
			model.addAttribute("userList", userList);
			List<CourseCode> ordinalNum=_cCourseCodeRepository.findbycourseCode(courseList.get(0).getCourseLevel());
		    model.addAttribute("ordinalNum", ordinalNum);
	        return "/childinformation/create";
	
	     }
		/**
		 * 孩子信息提交
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/createinfo", method = RequestMethod.POST)
	    public String createinfo(HttpServletRequest request, Model model)
	    {
			String CourseId = request.getParameter("Course");
			Course course = _courseReponsitory.findOne(Integer.parseInt(CourseId));
		
		  List<IfbrainVar> list=new ArrayList<IfbrainVar>();
		  String ChildName = request.getParameter("ChildName");
		  Child child = _childRepository.findOne(Integer.parseInt(ChildName));
		  //获得课程序号和课程名称
		 String CourseCode = request.getParameter("CourseCode");
		 CourseCode courseCode = _cCourseCodeRepository.findOne(Integer.parseInt(CourseCode));
		  //获得班级
		 String CourseClassId = request.getParameter("CourseClass");
	     CourseClass courseClass = _courseClassRepository.findOne(Integer.parseInt(CourseClassId));
	     child.setCourseClass(courseClass);
	     _childRepository.save(child);
	     List<IfbrainIndex> ifbrainIndexList = _ifbrainIndexReponsitory.findByChildIdAndCourseLevel(child.getId(), course.getCourseLevel());
	     String afterclassIncome = request.getParameter("afterclassIncome");
	     //补充代币
	     String supplementaryTokens = request.getParameter("supplementaryTokens");
	     if(ifbrainIndexList.size()>0){
	     IfbrainIndex ifbrai=new IfbrainIndex();
	     ifbrai.setChild(child);
	     ifbrai.setCourseLevel(courseClass.getCourseLevel());
	     ifbrai.setLessonName(courseCode.getLessonName());
	     ifbrai.setOrdinalNumber(courseCode.getOrdinalNumber());
	     ifbrai.setExpense(new BigDecimal(0));
	     _ifbrainIndexReponsitory.save(ifbrai);
	     IfbrainIndex ifbrain = _ifbrainIndexReponsitory.findOne(ifbrai.getId());
	     //知识财脑指数
	     BigDecimal knowlegevalue=new BigDecimal(0);
	     String[] knowledgenames = request.getParameterValues("knowledgename");
	     String[] knowledcount = request.getParameterValues("knowledcount");
	     for(int i=0;i<knowledgenames.length;i++){
	    	 IfbrainVar var=new IfbrainVar();
	    	 var.setVarName(knowledgenames[i]);
	    	 var.setVarValue(knowledcount[i]);
	    	 var.setCreateTime(new Date());
	    	 knowlegevalue=knowlegevalue.add(new BigDecimal(knowledcount[i]));
	    	 var.setIfbrainIndex(ifbrain);
	    	 var.setIncomeType(0);
	    	 _ifbrainVarReponsitory.save(var);
	    	 list.add(var);
	     }
	     //应用财脑指数
	     BigDecimal applicationvalue=new BigDecimal(0);
	     String[] applicationsgename = request.getParameterValues("applicationsgename");
	     String[] applicationscount = request.getParameterValues("applicationscount");
	     for(int i=0;i<applicationsgename.length;i++){
	    	 IfbrainVar var=new IfbrainVar();
	    	 var.setVarName(applicationsgename[i]);
	    	 var.setVarValue(applicationscount[i]);
	    	 var.setCreateTime(new Date());
	    	 applicationvalue=applicationvalue.add(new BigDecimal(applicationscount[i]));
	    	 var.setIfbrainIndex(ifbrain);
	    	 var.setIncomeType(1);
	    	 _ifbrainVarReponsitory.save(var);
	    	 list.add(var);
	     }
	     
	     int ordinalNumber = courseCode.getOrdinalNumber();
	     int ordinalnumber = ordinalNumber;
	     //课后训练分数保存，保存的都是上一节课的课后训练分数
	      ifbrain.setAfterclassIncome(0);
	       ifbrain.setBankAfterclassIncome(0);
	       ifbrain.setExpense(new BigDecimal(0));
	     if(ordinalnumber>1){
		     IfbrainIndex ifbrainindex = _ifbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(ordinalnumber-1, child.getId(),courseClass.getCourseLevel());
		    //这里会出现bug
		     if(ifbrainindex!=null){
		     ifbrainindex.setAfterclassIncome(Integer.parseInt(afterclassIncome));
		     ifbrainindex.setIfbrainIndex(new BigDecimal(ifbrainindex.getIfbrainIndex()).add(new BigDecimal(Integer.parseInt(afterclassIncome))).intValue());
		     _ifbrainIndexReponsitory.save(ifbrainindex);
		     }
	        //代币银行课后
		     IfbrainIndex ifbrainindexbank = _ifbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(ordinalnumber, child.getId(),courseClass.getCourseLevel());
		     ifbrainindexbank.setBankAfterclassIncome(Integer.parseInt(afterclassIncome));
		     ifbrainindexbank.setSupplementaryTokens(Integer.parseInt(supplementaryTokens));
		     ifbrainindexbank.setExpense(new BigDecimal(0));
		     _ifbrainIndexReponsitory.save(ifbrainindexbank);
	     }
	     //根据条件查询初期财脑指数  
	     IfbrainIndex ifbrainindex = _ifbrainIndexReponsitory.findByTiaojian(courseClass.getId(), ordinalnumber-1, child.getId(),courseClass.getCourseLevel());
	     if(ifbrainindex==null){
	    	 ifbrain.setPrimarysIfbrainindex(0);
	    	 //计算财脑指数
		     BigDecimal ifbrainIndex=new BigDecimal(0);
		     ifbrainIndex.add(knowlegevalue).add(new BigDecimal(0)).add(applicationvalue);
		     ifbrain.setIfbrainIndex(ifbrainIndex.intValue());
	     }
	     else{
	    	 ifbrain.setPrimarysIfbrainindex(ifbrainindex.getIfbrainIndex());
	    	 BigDecimal ifbrainIndex=new BigDecimal(0);
	    	 ifbrainIndex=ifbrainIndex.add(knowlegevalue).add(new BigDecimal(0)).add(applicationvalue).add(new BigDecimal(ifbrainindex.getIfbrainIndex()));
		     ifbrain.setIfbrainIndex(ifbrainIndex.intValue());
	     }
	     ifbrain.setApplicationtotalScore(applicationvalue.toString());
	     ifbrain.setKnowledgetotalScore(knowlegevalue.toString());
	     ifbrain.setClassId(courseClass.getId());
	     ifbrain.setSupplementaryTokens(Integer.parseInt(supplementaryTokens));
	     //计算收入
	     BigDecimal income=new BigDecimal(0);
	     income=income.add(knowlegevalue).add(applicationvalue).add(new BigDecimal(afterclassIncome)).add(new BigDecimal(supplementaryTokens));
	     ifbrain.setIncome(income);
	     ifbrain.setIfbrainVar(list);
	     ifbrain.setCreateTime(new Date());
	     ifbrain.setExpense(new BigDecimal(0));
	     if(ifbrainindex==null){
	    	 ifbrain.setBalance(income);
	     }else{
	    	 ifbrain.setBalance(income.add(ifbrainindex.getBalance()));
	     }
	     
	   //获得支出
	    /* BigDecimal expense = _ifbrainIndexReponsitory.findExpendseByChildIdsAndCourselevelAndOrdernumber(child.getId(), course.getCourseLevel(),Integer.parseInt(courseCode.getOrdinalNumber()));
	     if(expense==null||expense.intValue()==0){
	    	 //计算代币余额
	    		 BigDecimal sum = _ifbrainIndexReponsitory.findSumpriceByChildIdsAndCourselevelAndOrdernumber(child.getId(), course.getCourseLevel(),Integer.parseInt(courseCode.getOrdinalNumber()));
		    	 ifbrai.setBalance(sum.add(income));
	     }else{
	    		 BigDecimal yue = _ifbrainIndexReponsitory.findSumPriceByChildIdsAndCourselevelAndOrdernumber(child.getId(), course.getCourseLevel(),Integer.parseInt(courseCode.getOrdinalNumber()));
			     ifbrai.setBalance(yue);
	     }*/
	     _ifbrainIndexReponsitory.save(ifbrain);
	     }else{
	    	
		     IfbrainIndex ifbrai=new IfbrainIndex();
		     ifbrai.setChild(child);
		     ifbrai.setCourseLevel(courseClass.getCourseLevel());
		     ifbrai.setLessonName(courseCode.getLessonName());
		     ifbrai.setOrdinalNumber(courseCode.getOrdinalNumber());
		     ifbrai.setExpense(new BigDecimal(0));
		     _ifbrainIndexReponsitory.save(ifbrai);
		     IfbrainIndex ifbrain = _ifbrainIndexReponsitory.findOne(ifbrai.getId());
		     //知识财脑指数
		     BigDecimal knowlegevalue=new BigDecimal(0);
		     String[] knowledgenames = request.getParameterValues("knowledgename");
		     String[] knowledcount = request.getParameterValues("knowledcount");
		     for(int i=0;i<knowledgenames.length;i++){
		    	 IfbrainVar var=new IfbrainVar();
		    	 var.setVarName(knowledgenames[i]);
		    	 var.setVarValue(knowledcount[i]);
		    	 var.setCreateTime(new Date());
		    	 knowlegevalue=knowlegevalue.add(new BigDecimal(knowledcount[i]));
		    	 var.setIfbrainIndex(ifbrain);
		    	 var.setIncomeType(0);
		    	 _ifbrainVarReponsitory.save(var);
		     }
		     //应用财脑指数
		     BigDecimal applicationvalue=new BigDecimal(0);
		     String[] applicationsgename = request.getParameterValues("applicationsgename");
		     String[] applicationscount = request.getParameterValues("applicationscount");
		     for(int i=0;i<applicationsgename.length;i++){
		    	 IfbrainVar var=new IfbrainVar();
		    	 var.setVarName(applicationsgename[i]);
		    	 var.setVarValue(applicationscount[i]);
		    	 var.setCreateTime(new Date());
		    	 applicationvalue=applicationvalue.add(new BigDecimal(applicationscount[i]));
		    	 var.setIfbrainIndex(ifbrain);
		    	 var.setIncomeType(1);
		    	 _ifbrainVarReponsitory.save(var);
		     }
		     ifbrain.setClassId(courseClass.getId());
		     ifbrain.setAfterclassIncome(Integer.parseInt(afterclassIncome));
		     ifbrain.setBankAfterclassIncome(Integer.parseInt(afterclassIncome));
		     ifbrain.setSupplementaryTokens(Integer.parseInt(supplementaryTokens));
		     ifbrain.setExpense(new BigDecimal(0));
		//     ifbrain.setBankPrimarysIfbrainindex(0);
		     ifbrain.setPrimarysIfbrainindex(0);
		     ifbrain.setApplicationtotalScore(applicationvalue.toString());
		     ifbrain.setKnowledgetotalScore(knowlegevalue.toString());
		     //计算财脑指数
		     BigDecimal ifbrainIndex=new BigDecimal(0);
		     ifbrainIndex=ifbrainIndex.add(knowlegevalue).add(applicationvalue).add(new BigDecimal(afterclassIncome));
		     //计算收入
		     BigDecimal income=new BigDecimal(0);
		     income=income.add(knowlegevalue).add(applicationvalue).add(new BigDecimal(afterclassIncome).add(new BigDecimal(supplementaryTokens)));
		     ifbrain.setIfbrainIndex(ifbrainIndex.intValue());
		     
		    
		     ifbrain.setIncome(income);
		     ifbrain.setCreateTime(new Date());
		     //计算代币余额
		     ifbrai.setBalance(income);
		     _ifbrainIndexReponsitory.save(ifbrain);
	     }
	        return "redirect:/childinformation/list";
	    }
		//视频列表
		@RequestMapping(value = "/movielist", method = RequestMethod.GET)
	    public String movielist(HttpServletRequest request, Model model)
	    {
			List<Video> list = _videoRepository.findAll();
			model.addAttribute("list", list);
	        return "/childinformation/listmovie";
	    }
		  //单独上传视频初始化
	    @RequestMapping(value = "/inituploadmovie",method = RequestMethod.GET)
	    public String inituploadmovie(Model model,HttpServletRequest request) throws Exception
	    {
	    	List<Course> courseList = _courseReponsitory.findAll();
			List<User> userList = _userRepository.findAll();
			model.addAttribute("courseList", courseList);
			model.addAttribute("userList", userList);
			List<CourseCode> ordinalNum=_cCourseCodeRepository.findbycourseCode(courseList.get(0).getCourseLevel());
		    model.addAttribute("ordinalNum", ordinalNum);
	    	return  "/childinformation/uploadmovie";
	    }
	    //上传视频
	    @SuppressWarnings("deprecation")
		@RequestMapping(value = "/uploadmovie", method = RequestMethod.POST)
	    public String uploadmovie(HttpServletRequest request, Model model)
	    {
	    	String CourseId = request.getParameter("Course");
			Course course = _courseReponsitory.findOne(Integer.parseInt(CourseId));
		 // List<IfbrainVar> list=new ArrayList<IfbrainVar>();
		 /* String ChildName = request.getParameter("ChildName");
		  Child child = _childRepository.findOne(Integer.parseInt(ChildName));*/
		  //获得课程序号和课程名称
		 String CourseCode = request.getParameter("CourseCode");
		 CourseCode courseCode = _cCourseCodeRepository.findOne(Integer.parseInt(CourseCode));
		  //获得班级
		 String CourseClassId = request.getParameter("CourseClass");
	     //CourseClass courseClass = _courseClassRepository.findOne(Integer.parseInt(CourseClassId));
	     //child.setCourseClass(courseClass);
	    // _childRepository.save(child);
	   /*  if(file.length>0){
			 for(int i = 0;i<file.length;i++){  */
		    	  // String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getVideoPath();
		      /* if (!file[i].isEmpty())
		       {*/
		          /* try
		           {*/
		             //  byte[] bytes = file[i].getBytes();
		             /*  File directory = new File(filePath);
		               if (!directory.exists())
		               {
		                   directory.mkdirs();
		               }*/
		             // String  filename=file[i].getOriginalFilename();
		              //filePath += filename;
		               String filepath = request.getParameter("filepath");
		               //String encode = java.net.URLEncoder.encode(filename,"utf-8"); 
		              /* String encode=new String(filename.getBytes("UTF-8"),"GBK");*/
		               //String lujing=_fileUploadSetting.getVideoPath()+filepath;
		               Video video=new Video();
		               //video.setChild(child);
		               video.setVideoPath(filepath);
		               video.setClassId(Integer.parseInt(CourseClassId));
		               video.setCourseLevel(course.getCourseLevel());
		               video.setLessonName(courseCode.getLessonName());
		               video.setOrdinalNumber(courseCode.getOrdinalNumber());
		               video.setCreateTime(new Date());
		               _videoRepository.save(video);
		              /* BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
		               stream.write(bytes);
		               stream.close();*/
		          /* }*/
		         /*  catch (Exception e)
		           {
		               e.printStackTrace();
		           }*/
		      /* }*/
		      /* }*/
	     /*}*/
	         return  "redirect:/childinformation/movielist";
	    }
	    
	    
	    
		//根据课程级别查询相应的班级名称
		@ResponseBody
		@RequestMapping(value = "/findByCourseId", method = RequestMethod.GET)
	    public List<CourseClass> findClassNameByClassId(HttpServletRequest request, Model model)
	    {
			String id = request.getParameter("id");
			Course course = _courseReponsitory.findOne(Integer.parseInt(id));
		    List<CourseClass> courseClass = course.getCourseClass();
		    List<CourseClass>  list=new ArrayList<CourseClass>();
		    for(int i=0;i<courseClass.size();i++){
		    	CourseClass c=new CourseClass();
		    	c.setId(courseClass.get(i).getId());
		    	c.setClassName(courseClass.get(i).getClassName());
		    	list.add(c);
		    }
	        return list;
	    }
		//根据班级id查询该班级所上的课程名称
		@ResponseBody
		@RequestMapping(value = "/findLessonNameByClassId", method = RequestMethod.GET)
	    public List <CourseCode> findLessonNameByClassId(HttpServletRequest request, Model model)
	    {
			String id = request.getParameter("id");
			Course course = _courseReponsitory.findOne(Integer.parseInt(id));
			List<CourseCode> courseCode = course.getCourseCode();
			List<CourseCode> c=new ArrayList<CourseCode>();
			for(int i=0;i<courseCode.size();i++){
				CourseCode coursecode=new CourseCode();
				coursecode.setId(courseCode.get(i).getId());
				coursecode.setLessonName(courseCode.get(i).getLessonName());
				coursecode.setOrdinalNumber(courseCode.get(i).getOrdinalNumber());
				c.add(coursecode);
			}
			return c;
	    }
		//根据家长Id查询孩子
		@ResponseBody
		@RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
	    public List<Child> findByUserId(HttpServletRequest request, Model model)
	    {
			String id = request.getParameter("id");
			User user = _userRepository.findOne(Integer.parseInt(id));
			List<Child> childList = user.getChild();
			List<Child>  list=new ArrayList<Child>();
			for(int i=0;i<childList.size();i++){
				Child c=new Child();
				c.setId(childList.get(i).getId());
				c.setName(childList.get(i).getName());
				list.add(c);
			}
			return list;
	    }
		 //进入修改页面 
		 @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
		    public String updateInit(@PathVariable int id, Model model)
		    {
			 List<Course> courseList = _courseReponsitory.findAll();
			   Child child = _childRepository.findOne(id);
			   User user = child.getUser();
			   model.addAttribute("courseList", courseList);
			   model.addAttribute("child", child);
			   model.addAttribute("user", user);
		        return "/childinformation/update";
		    }
		 //修改学生信息
			@RequestMapping(value = "/updateinfo", method = RequestMethod.POST)
		    public String updateinfo(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile[] file)
		    {
			 String ChildName = request.getParameter("ChildName");
			 Child child = _childRepository.findOne(Integer.parseInt(ChildName));
			  //获得班级
			 String CourseClassId = request.getParameter("CourseClass");
		     CourseClass courseClass = _courseClassRepository.findOne(Integer.parseInt(CourseClassId));
		     child.setCourseClass(courseClass);
		     _childRepository.save(child);
				 for(int i = 0;i<file.length;i++){  
			    	   String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getVideoPath();
			       if (!file[i].isEmpty())
			       {
			           try
			           {
			               byte[] bytes = file[i].getBytes();
			               File directory = new File(filePath);
			               if (!directory.exists())
			               {
			                   directory.mkdirs();
			               }
			              String  filename=file[i].getOriginalFilename();
			              filePath += filename;
			               String lujing=_fileUploadSetting.getVideoPath()+filename;
			               Video video=new Video();
			               video.setChild(child);
			               video.setVideoPath(lujing);
			               video.setClassId(Integer.parseInt(CourseClassId));
			               video.setCourseLevel(courseClass.getCourseLevel());
			             
			               video.setCreateTime(new Date());
			               _videoRepository.save(video);
			               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			               stream.write(bytes);
			               stream.close();
			             
			           }
			           catch (Exception e)
			           {
			               e.printStackTrace();
			           }
			       }
			       }
				
		        return "redirect:/childinformation/list";
		
		    }
			
			//提交判断所选是否重复
			@ResponseBody
			@RequestMapping(value = "/findcheckByClassId", method = RequestMethod.GET)
		    public boolean findcheckByClassId(HttpServletRequest request, Model model)
		    {/*
				5 2 85 5 IBL1 财脑1级*/
				String course = request.getParameter("course");
				String CourseCode = request.getParameter("amp;CourseCode");
				String childid = request.getParameter("amp;childid");
				System.out.println(course);
				System.out.println(CourseCode);
				System.out.println(childid);
			     Course courseLevel =_courseReponsitory.findByCourseLevel(Integer.parseInt(course));
				
			     CourseCode coursecode =_cCourseCodeRepository.findOne(Integer.parseInt(CourseCode));
			     IfbrainIndex checktrue=_ifbrainIndexReponsitory.findByOrdinalNumberAndChildIdAndCourseLevel(coursecode.getOrdinalNumber(),Integer.parseInt(childid), courseLevel.getCourseLevel()  );
				System.out.println("checktrue.size()"+checktrue);
				if(checktrue!=null){
					return false;
				}else{
				return true;
				}
		    }
			
			
			
			
			//提交判断上传学生视频所选是否重复
			@ResponseBody
			@RequestMapping(value = "/findcheckByclassMovie", method = RequestMethod.GET)
		    public boolean findcheckByclassMovie(HttpServletRequest request, Model model)
		    {/*
				5 2 85 5 IBL1 财脑1级*/
				String course = request.getParameter("course");
				String CourseCode = request.getParameter("amp;CourseCode");
				String CourseClass = request.getParameter("amp;CourseClass");
				System.out.println(course);
				System.out.println(CourseCode);
		
			     Course courseLevel =_courseReponsitory.findByCourseLevel(Integer.parseInt(course));
				
			     CourseCode coursecode =_cCourseCodeRepository.findOne(Integer.parseInt(CourseCode));
			     
			     CourseClass courseclass =_courseClassRepository.findOne(Integer.parseInt(CourseClass));
			   
			     List<Video> checktrue=_videoRepository.findByOrdinalNumberAndCourseLevelAndClassId(coursecode.getOrdinalNumber(), courseLevel.getCourseLevel() ,courseclass.getId() );
				System.out.println("checktrue.size()"+checktrue);
				if(checktrue.size()>0){
					return false;
				}else{
				return true;
				}
		    }
}
