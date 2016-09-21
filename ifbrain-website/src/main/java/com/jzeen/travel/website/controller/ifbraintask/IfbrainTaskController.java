package com.jzeen.travel.website.controller.ifbraintask;

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
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

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
import com.jzeen.travel.data.entity.NavigationbarModule;
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
import com.jzeen.travel.data.repository.NavigationbarModuleRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.VideoReponsitory;
import com.jzeen.travel.website.setting.FileUploadSetting;
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

	@Autowired 
	private 	NavigationbarModuleRepository _NavigationbarModuleRepository;
	//老师上传任务的列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
		//根据名称查询
		//财经心理学
		String id =request.getParameter("courseid");
		List<MaterialType> list = _materialTypeRepository.findAll();
		model.addAttribute("list", list);
		List<IfbrainTask> list2 = _materialTypeRepository.findifbraintaskbyType(Integer.parseInt(id));
		model.addAttribute("list2", list2);
		User user = (User) request.getSession().getAttribute("user");
		List<Child> child =_childRepository.findByUser(user);
		if(child.size()>0){
			model.addAttribute("childids", child.get(0).getId());
		}else{
			model.addAttribute("childids", "");
		}
         return "/ifbraintask/ifbraintask";
    }
	
	
	@RequestMapping(value = "/taskcourse", method = RequestMethod.GET)
    public String familycourse(HttpServletRequest request, Model model)
    {
		
		List<Course> course = _courseReponsitory.findByType("0");
		model.addAttribute("course", course);
		
		String type =request.getParameter("type");
		   String navigationbartrype =request.getParameter("navigationbartrype");
		   NavigationbarModule navigationBar=_NavigationbarModuleRepository.findByNavigationBarTypeAndType(Integer.parseInt(navigationbartrype), Integer.parseInt(type));
		   	if(navigationBar!=null){
		   		model.addAttribute("material", navigationBar);
		   	}
		      else{
		   		model.addAttribute("material", "");
		   	 }
         return "/ifbraintask/taskcourse";
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
		/*@RequestMapping(value = "/createinfo", method = RequestMethod.POST)
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
		              String lujing=_fileUploadSetting.getFreevideoPath()+ifbraitaskname;
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
		*/
		
		//任务首页
		@RequestMapping(value = "/ifbraintaskhome", method = RequestMethod.GET)
	    public String ifbraintaskhome(HttpServletRequest request, Model model)
	    {
	         return "/ifbraintask/ifbraintaskhome";
	    }
		//上传下载任务
		@RequestMapping(value = "/uploadordownloadtask", method = RequestMethod.GET)
	    public String uploadordownloadtask(HttpServletRequest request, Model model)
	    {
			Integer childid= Integer.parseInt(request.getParameter("childidtask"));
			Integer todownloadtaskid = Integer.parseInt(request.getParameter("todownloadtaskid"));
			
			User user = (User) request.getSession().getAttribute("user");
			List<Child> child =_childRepository.findByUser(user);
			IfbrainTask findbytaskid = _ifbrainTaskRepository.findOne(todownloadtaskid);
			System.out.println(request.getSession().getAttribute("todownloadtaskid"));
			if(request.getSession().getAttribute("todownloadtaskid")!=null){
				HttpSession session = request.getSession();
				session.removeAttribute("todownloadtaskid");
			}else{
				WebUtils.setSessionAttribute(request, "todownloadtaskid",todownloadtaskid);
			}
			if(child.size()>0){
				model.addAttribute("childs", child);
				model.addAttribute("child", child);	
				model.addAttribute("flagvalue", childid);
			/*	IfbrainTask ifbraintaskcheck =_ifbrainTaskRepository.findidandcourseTask(childid, findbytaskid.getCourseLevel().getId(), findbytaskid.getCourseCode().getId(), findbytaskid.getMaterialType().getId(),"1");
				if(ifbraintaskcheck==null){
					 model.addAttribute("ifbraintaskcheck", "");
				}else{
					 model.addAttribute("ifbraintaskcheck", ifbraintaskcheck);
				}		
				 IfbrainTask ifbraintask = _ifbrainTaskRepository.findOne(todownloadtaskid);
				 model.addAttribute("ifbraintask", ifbraintask);*/
			}else{
				model.addAttribute("childs", "");
				/*model.addAttribute("ifbraintaskcheck", "");*/
				model.addAttribute("child", "");
				model.addAttribute("flagvalue", "");
			/*	model.addAttribute("ifbraintask", "");*/
			}	
			IfbrainTask ifbraintaskcheck =_ifbrainTaskRepository.findidandcourseTask(childid, findbytaskid.getCourseLevel().getId(), findbytaskid.getCourseCode().getId(), findbytaskid.getMaterialType().getId(),"1");
			if(ifbraintaskcheck==null){
				 model.addAttribute("ifbraintaskcheck", "");
			}else{
				 model.addAttribute("ifbraintaskcheck", ifbraintaskcheck);
			}		
			 IfbrainTask ifbraintask = _ifbrainTaskRepository.findOne(todownloadtaskid);
			 model.addAttribute("ifbraintask", ifbraintask);
	         return "/ifbraintask/uploadordownloadtask";
	    }
		
		
		
		
		/**
		 * 上传财脑任务
		 * @param request
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/createchildtask", method = RequestMethod.POST)
	    public String createinfo(HttpServletRequest request, Model model,@RequestParam("ifbrainchildtask") MultipartFile[] ifbraintask)
	    {
			String CourseId = request.getParameter("Course");
			Course course = _courseReponsitory.findOne(Integer.parseInt(CourseId));
			//章节
			String materialtypeid = request.getParameter("materialtype");
			MaterialType materialtype = _materialTypeRepository.findOne(Integer.parseInt(materialtypeid));
		    //任务描述
		/*	String description = request.getParameter("description");*/
			//获得课程序号和课程名称
		 String CourseCode = request.getParameter("CourseCode");
		 CourseCode courseCode = _cCourseCodeRepository.findOne(Integer.parseInt(CourseCode));
		 String childId = request.getParameter("flagaa");
		 Child childs = _childRepository.findOne(Integer.parseInt(childId));
			Integer todownloadtaskid =Integer.parseInt(request.getParameter("ifbraintaskid"));
			IfbrainTask findbytaskid = _ifbrainTaskRepository.findOne(todownloadtaskid);
			IfbrainTask ifbraintaskcheck =_ifbrainTaskRepository.findidandcourseTask(childs.getId(), findbytaskid.getCourseLevel().getId(), findbytaskid.getCourseCode().getId(), findbytaskid.getMaterialType().getId(),"1");
			if(ifbraintaskcheck!=null){
				_ifbrainTaskRepository.delete(ifbraintaskcheck.getId());
			}
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
		              ifbrainTask.setDescription("");
		              ifbrainTask.setType("1");
		              ifbrainTask.setChild(childs);
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
	        return "redirect:/ifbraintask/taskcourse";
	    }
		
		
		
		//下载任务
	    @RequestMapping(value = "/downloads/{id}")
	    public  String download(HttpServletRequest request,HttpServletResponse response,@PathVariable int id) throws UnsupportedEncodingException {
			
			
			IfbrainTask ifbrainTask = _ifbrainTaskRepository.findOne(id);
			  String filePath= ifbrainTask.getIfbrainTaskPath();
			  String rootPath = _fileUploadSetting.getRootPath();
			  rootPath+=filePath;
		       String present = new String(rootPath.getBytes("UTF-8"),"ISO-8859-1"); 
		     return "redirect:/downloads?path="+present;
	       /* try  {
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
}
