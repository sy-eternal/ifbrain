package com.jzeen.travel.admin.controller.ifbraintask;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jzeen.travel.data.entity.CarouselImg;
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.Course;
import com.jzeen.travel.data.entity.CourseClass;
import com.jzeen.travel.data.entity.CourseCode;
import com.jzeen.travel.data.entity.FreeFile;
import com.jzeen.travel.data.entity.FreeVideo;
import com.jzeen.travel.data.entity.IfbrainIndex;
import com.jzeen.travel.data.entity.IfbrainTask;
import com.jzeen.travel.data.entity.IfbrainVar;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.entity.QIfbrainTask;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.Video;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.CourseClassReponsitory;
import com.jzeen.travel.data.repository.CourseCodeRepository;
import com.jzeen.travel.data.repository.CourseReponsitory;
import com.jzeen.travel.data.repository.IfbrainIndexReponsitory;
import com.jzeen.travel.data.repository.IfbrainTaskRepository;
import com.jzeen.travel.data.repository.IfbrainVarReponsitory;
import com.jzeen.travel.data.repository.MaterialTypeRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.VideoReponsitory;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/ifbraintask")
public class IfbrainTaskController {
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
	@Autowired
	MaterialTypeRepository  _materialTypeRepository;
	@Autowired
	IfbrainTaskRepository   _ifbrainTaskRepository;
	//老师上传任务的列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
		QIfbrainTask task=QIfbrainTask.ifbrainTask;
		 Predicate predicate=task.type.eq("0");
      Iterable<IfbrainTask> data = _ifbrainTaskRepository.findAll(predicate);
		List<IfbrainTask> list=new ArrayList<IfbrainTask>();
	       for(IfbrainTask f:data){
	    	   list.add(f);
	       }
	       model.addAttribute("list", list);
         return "/ifbraintask/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Iterable<IfbrainTask> search()
    {
		QIfbrainTask task=QIfbrainTask.ifbrainTask;
		 Predicate predicate=task.type.eq("0");
       Iterable<IfbrainTask> data = _ifbrainTaskRepository.findAll(predicate);
       return data;
    }
		
		//新增上传孩子信息
		@RequestMapping(value = "/create", method = RequestMethod.GET)
	    public String create(HttpServletRequest request, Model model)
	    {
			List<Course> courseList = _courseReponsitory.findAll();
			List<User> userList = _userRepository.findAll();
			List<MaterialType> materialtypeList = _materialTypeRepository.findAll();
			model.addAttribute("courseList", courseList);
			model.addAttribute("userList", userList);
			model.addAttribute("materialtypeList", materialtypeList);
	        return "/ifbraintask/create";
	
	     }
		/**
		 * 上传财脑任务
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/createinfo", method = RequestMethod.POST)
	    public String createinfo(HttpServletRequest request, Model model,@RequestParam("ifbraintask") MultipartFile[] ifbraintask)
	    {
			String CourseId = request.getParameter("Course");
			Course course = _courseReponsitory.findOne(Integer.parseInt(CourseId));
			//章节
			String materialtypeid = request.getParameter("materialtype");
			MaterialType materialtype = _materialTypeRepository.findOne(Integer.parseInt(materialtypeid));
		    //任务描述
			String description = request.getParameter("description");
			//获得课程序号和课程名称
		 String CourseCode = request.getParameter("CourseCode");
		 CourseCode courseCode = _cCourseCodeRepository.findOne(Integer.parseInt(CourseCode));
		 String ifbraintaskPath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getIfbraintaskPath();
		 if(ifbraintask.length>0){
			 for(int i = 0;i<ifbraintask.length;i++){  
		       if (!ifbraintask[i].isEmpty())
		       {
		           try
		           {
		               byte[] bytes = ifbraintask[i].getBytes();
		               File directory = new File(ifbraintaskPath);
		               if (!directory.exists())
		               {
		                   directory.mkdirs();
		               }
		              String  ifbraitaskname=ifbraintask[i].getOriginalFilename();
		              String newifbraintaskname = ifbraitaskname.substring(0, ifbraitaskname.lastIndexOf("."));
		              ifbraintaskPath += ifbraitaskname;
		              String lujing=_fileUploadSetting.getIfbraintaskPath()+ifbraitaskname;
		              IfbrainTask  ifbrainTask=new IfbrainTask();
		              ifbrainTask.setCourseCode(courseCode);
		              ifbrainTask.setIfbrainTaskName(newifbraintaskname);
		              ifbrainTask.setCourseLevel(course);
		              ifbrainTask.setCreateTime(new Date());
		              ifbrainTask.setIfbrainTaskPath(lujing);
		              ifbrainTask.setMaterialType(materialtype);
		              ifbrainTask.setDescription(description);
		              ifbrainTask.setDescription(description);
		              ifbrainTask.setType("0");
		              _ifbrainTaskRepository.save(ifbrainTask);
		               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(ifbraintaskPath)));
		               stream.write(bytes);
		               stream.close();
		           }
		           catch (Exception e)
		           {
		               e.printStackTrace();
		           }
		       }
		       }
		 }
	        return "redirect:/ifbraintask/list";
	    }
		
		
		//根据课程级别查询相应的班级名称
		@ResponseBody
		@RequestMapping(value = "/findByCourseId", method = RequestMethod.GET)
	    public List<CourseClass> findClassNameByClassId(HttpServletRequest request, Model model)
	    {
			String id = request.getParameter("id");
			Course course = _courseReponsitory.findOne(Integer.parseInt(id));
		    List<CourseClass> courseClass = course.getCourseClass();
	        return courseClass;
	    }
		//根据班级id查询该班级所上的课程名称
		@ResponseBody
		@RequestMapping(value = "/findLessonNameByClassId", method = RequestMethod.GET)
	    public List <CourseCode> findLessonNameByClassId(HttpServletRequest request, Model model)
	    {
			String id = request.getParameter("id");
			Course course = _courseReponsitory.findOne(Integer.parseInt(id));
			List<CourseCode> courseCode = course.getCourseCode();
			return courseCode;
	    }
		//根据家长Id查询孩子
		@ResponseBody
		@RequestMapping(value = "/findByUserId", method = RequestMethod.GET)
	    public List<Child> findByUserId(HttpServletRequest request, Model model)
	    {
			String id = request.getParameter("id");
			User user = _userRepository.findOne(Integer.parseInt(id));
			List<Child> childList = user.getChild();
			return childList;
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
		    {
				
				String course = request.getParameter("course");
				String ordinalNumber = request.getParameter("amp;ordinalNumber");
				String childid = request.getParameter("amp;childid");
				String courseClass = request.getParameter("amp;CourseClass");
				Course courseLevel =_courseReponsitory.findByCourseLevel(Integer.parseInt(course));
				List<IfbrainIndex> checktrue=_ifbrainIndexReponsitory.findByCheck(Integer.parseInt(childid), courseLevel.getCourseLevel(),  Integer.parseInt(ordinalNumber),Integer.parseInt(courseClass));
				System.out.println("checktrue.size()"+checktrue.size());
				if(checktrue.size()>0){
					return false;
				}else{
				return true;
				}
		    }
			   //删除
		    
		    @RequestMapping(value = "/deletetask", method = RequestMethod.DELETE)
		    public String materialdelete(HttpServletRequest request, Model model) throws Exception
		    {
		    	String id = request.getParameter("id");
		    	//选中的对象
		    	IfbrainTask ifbrainTask = _ifbrainTaskRepository.findOne(Integer.parseInt(id));
		    	_ifbrainTaskRepository.delete(ifbrainTask);
		    	return  "redirect:/list/ifbraintask";
		    }
		    //根据课程级别展示孩子上传的任务列表
			@RequestMapping(value = "/childtasklist", method = RequestMethod.GET)
		    public String childtasklist(HttpServletRequest request, Model model)
		    {
				List<Course> courseList = _courseReponsitory.findAll();
				model.addAttribute("courseList", courseList);
				model.addAttribute("courseid", courseList.get(0).getId());
				List<CourseCode> courseCodeList = _courseReponsitory.findByCourseCodeid(courseList.get(0).getId());
				model.addAttribute("courseCodeList", courseCodeList);
				
				
				return "/ifbraintask/childtasklist";
		    }
		   //根据课程级别查询相应的孩子上传任务的列表
			@RequestMapping(value = "/searchchildtasklist", method = RequestMethod.GET)
		    public String searchchildtasklist(HttpServletRequest request, Model model)
		    {
				List<Course> courseList = _courseReponsitory.findAll();
				model.addAttribute("courseList", courseList);
				String courseid = request.getParameter("Courseid");
				model.addAttribute("courseid", courseid);
				List<CourseCode> courseCodeList = _courseReponsitory.findByCourseCodeid(Integer.parseInt(courseid));
				model.addAttribute("courseCodeList", courseCodeList);
				return "/ifbraintask/childtasklist";
		    }
			//根据课程id查询相应的孩子上传的任务
			@RequestMapping(value = "/searchtaskbyid", method = RequestMethod.GET)
		    public String searchtaskbyid(HttpServletRequest request, Model model)
		    {
				String courseid = request.getParameter("courseid");
				String coursecodeid = request.getParameter("coursecodeid");
				model.addAttribute("courseid", courseid);
				model.addAttribute("coursecodeid", coursecodeid);
				List<IfbrainTask> list = _ifbrainTaskRepository.findByCouseCodeAndCourseId(Integer.parseInt(courseid), Integer.parseInt(coursecodeid));
				model.addAttribute("ifbraintask", list);
				return "/ifbraintask/downloadtasklist";
		    }
			//下载任务
			
		    @RequestMapping(value = "/downloads/{id}")
		    public  String download(HttpServletRequest request,HttpServletResponse response,@PathVariable int id) throws Exception {
				IfbrainTask ifbrainTask = _ifbrainTaskRepository.findOne(id);
				  String filePath= ifbrainTask.getIfbrainTaskPath();
				  String rootPath = _fileUploadSetting.getRootPath();
				  rootPath+=filePath;
			       String present = new String(rootPath.getBytes("UTF-8"),"ISO-8859-1"); 
			     return "redirect:/downloads?path="+present;
				/*try  {
		        	IfbrainTask ifbrainTask = _ifbrainTaskRepository.findOne(id);
		        	String filePath = ifbrainTask.getIfbrainTaskPath();
		        	String rootPath = _fileUploadSetting.getRootPath();
		        	rootPath+=filePath;
		            request.setCharacterEncoding("UTF-8");  
		            BufferedInputStream bis = null;  
		            BufferedOutputStream bos = null;  
		            File file  =   new  File(rootPath);
		            String filename  =  file.getName();
		            //获取文件的长度
		            long fileLength = file.length();  
		            //设置文件输出类型
		            response.setContentType("application/octet-stream");  
		            response.setHeader("Content-disposition", "attachment; filename="  
		                    + new String(filename.getBytes("utf-8"), "ISO8859-1")); 
		            //设置输出长度
		            response.setHeader("Content-Length", String.valueOf(fileLength));  
		            //获取输入流
		            bis = new BufferedInputStream(new FileInputStream(rootPath));  
		            //输出流
		            bos = new BufferedOutputStream(response.getOutputStream());  
		            byte[] buff = new byte[2048];  
		            int bytesRead;  
		            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
		                bos.write(buff, 0, bytesRead);  
		            }  
		            //关闭流
		            bis.close();  
		            bos.close();
		           
		       }catch(IOException ex) {
		           ex.printStackTrace();
		       }*/
		   }
			//删除孩子任务
		    @RequestMapping(value = "/delete", method = RequestMethod.GET)
		    public String delete(HttpServletRequest request)
		    {
		    	String id = request.getParameter("id");
		    	String courseid = request.getParameter("courseid");
		    	String coursecodeid = request.getParameter("coursecodeid");
		    	IfbrainTask ifbrainTask = _ifbrainTaskRepository.findOne(Integer.parseInt(id));
		    	_ifbrainTaskRepository.delete(ifbrainTask);
		    	return  "redirect:/ifbraintask/searchtaskbyid?courseid="+courseid+"&coursecodeid="+coursecodeid;
		    }
		    //上传验证
		    @ResponseBody
			@RequestMapping(value = "/check", method = RequestMethod.GET)
		    public Integer uploadordownloadtask(HttpServletRequest request, Model model)
		    {
				String CourseId = request.getParameter("CourseId");
				String CourseCodeId = request.getParameter("CourseCodeId");
				Integer flage;
				IfbrainTask ifbraintask = _ifbrainTaskRepository.check(Integer.parseInt(CourseId), Integer.parseInt(CourseCodeId), "0");
				if(ifbraintask!=null){
						flage=ifbraintask.getId();
					}else{
						flage=0;
					}		
		         return  flage;
		    }  
		    //确认删除吗?
		    @ResponseBody
		    @RequestMapping(value = "/deleteifbraintask", method = RequestMethod.GET)
		    public String deleteifbraintask(HttpServletRequest request)
		    {
		    	String id = request.getParameter("id");
		    	_ifbrainTaskRepository.delete(Integer.parseInt(id));
		    	String result="删除成功!";
		    	return  result;
		    }
		    
		    
}
