package com.jzeen.travel.website.controller.myInformation;





import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.DefineTask;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.InstructionFileRelate;
import com.jzeen.travel.data.entity.InsuranceActivity;
import com.jzeen.travel.data.entity.InsurancePlan;
import com.jzeen.travel.data.entity.InsuranceRate;
import com.jzeen.travel.data.entity.Money;
import com.jzeen.travel.data.entity.Order;
import com.jzeen.travel.data.entity.QChild;
import com.jzeen.travel.data.entity.QOrder;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.Task;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.WorkTaskRelate;
import com.jzeen.travel.data.repository.BuyPlanRepository;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CountryRepository;
import com.jzeen.travel.data.repository.CustomCommodityRepository;
import com.jzeen.travel.data.repository.EasyTaskRepository;
import com.jzeen.travel.data.repository.DefineTaskRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.MoneyRepository;
import com.jzeen.travel.data.repository.ShopHistoryRepository;
import com.jzeen.travel.data.repository.TaskRepository;
import com.jzeen.travel.data.repository.WorkTaskRelateRepository;
import com.mysema.query.types.Predicate;
import com.jzeen.travel.website.setting.FileUploadSetting;

@Controller
@RequestMapping("/myinformation")
public class MyInformationController
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
    private  DefineTaskRepository _defineTaskRepository;

   @RequestMapping(value = "/index",method = RequestMethod.GET)
   public String index()
   {
       return "/myinformation/basicinformation";
   }
   
   //我的宝贝
   @RequestMapping(value = "/baby",method = RequestMethod.GET)
   public String baby(Model model, HttpServletRequest request,HttpSession session)
   {
	   User user = (User) request.getSession().getAttribute("user");
	   model.addAttribute("user",user);
	  
	   List<Child> childlist =_cChildRepository.findByUser(user);
       model.addAttribute("childlist", childlist);
       List<Child> child =_cChildRepository.findByUser(user);
       model.addAttribute("child", child);
       model.addAttribute("childwidth", child.size());
       if(childlist.size()>0){
    	   WebUtils.setSessionAttribute(request, "childids", childlist.get(0).getId());
       }  
	   return "/myinformation/baby";
   }
   //我的宝贝列表
   @ResponseBody
   @RequestMapping(value = "/searchbabylist",method = RequestMethod.GET)
   public  Iterable<Child> searchbabylist(Model model, HttpServletRequest request)
   {
	   User user = (User) request.getSession().getAttribute("user");
       List<Child> childlist =_cChildRepository.findByUser(user);
       model.addAttribute("childlist", childlist);
       return childlist;
   }
   //我的任务列表
   @ResponseBody
   @RequestMapping(value = "/searchworktasklists",method = RequestMethod.GET)
   public  List<Task> searchworktasklists(Model model, HttpServletRequest request)
   {
       List<Task> worktasklists =_taskRepository .findAll();
       return worktasklists;
   }
   
   @RequestMapping(value = "/searchworktasklist/{id}",method = RequestMethod.GET)
   public  String searchworktasklistss(Model model, HttpServletRequest request,@PathVariable int id)
   {
	   model.addAttribute("id", id);
	/*   List<WorkTaskRelate> workrelate =_worktaskRepository.findBychildIds(id);*/
	   //上次的任务
	   List<WorkTaskRelate> workrelate =_worktaskRepository.findBychildIds(id);
	   model.addAttribute("workrelate", workrelate);
	   //全部
	   List<Task> worktasklists =_taskRepository .findAll();
	   model.addAttribute("worktasklists", worktasklists);
	  /* StringBuilder workrelates = new StringBuilder();
	   for(int i=0;i<workrelate.size();i++){
		   workrelates.append(workrelate.get(i).getId());
		   workrelates.append(',');
	   }
	   model.addAttribute("workrelates", workrelates);*/
       return "/myinformation/task";
   }
   @RequestMapping(value = "/searchworktasklist",method = RequestMethod.GET)
   public  String searchworktasklistssss(Model model, HttpServletRequest request,@PathVariable int id)
   {
	   model.addAttribute("id", id);
	   List<WorkTaskRelate> workrelate =_worktaskRepository.findBychildIds(id);
	   model.addAttribute("workrelate", workrelate);
	   StringBuilder workrelates = new StringBuilder();
	   for(int i=0;i<workrelate.size();i++){
		   workrelates.append(workrelate.get(i).getId());
		   workrelates.append(',');
	   }
	   model.addAttribute("workrelates", workrelates);
       return "/myinformation/task";
   }
   
   
   //新增Child
   @RequestMapping(value = "/create", method = RequestMethod.GET)
   public String createInit( Model model,HttpServletRequest request)
   {
	   User user = (User) request.getSession().getAttribute("user");
       model.addAttribute("user", user);
       List<Child> child =_cChildRepository.findByUser(user);
       model.addAttribute("child", child);
       model.addAttribute("childwidth", child.size());
       return "/myinformation/babycreate";

   }
   
   
   @RequestMapping(value = "/create", method = RequestMethod.POST)
   public String create( Model model, HttpServletRequest request,
           @RequestParam("file") MultipartFile file,HttpSession session) throws ParseException
   {
       String filePath = _fileUploadSetting.getRootPath()+File.separator+_fileUploadSetting.getChildPath();
       User user = (User) request.getSession().getAttribute("user");
       model.addAttribute("user", user);
       Child child = new Child();
       child.setCreateTime(new Date());
       if(file.isEmpty()){
    	   child.setAge(Integer.parseInt(request.getParameter("addage")));
           child.setName(request.getParameter("addname"));
          
           SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
           child.setBirth(sdf1.parse(request.getParameter("addbirth")));
           child.setCreateTime(new Date());
           child.setGender(Integer.parseInt(request.getParameter("gender")));
           child.setUser(user);    
           child.setBalance(new BigDecimal(0));
           _cChildRepository.save(child);
           Money money=new Money();
           money.setBalance(new BigDecimal(0));
           money.setChildId(child);
           money.setCreateTime(new Date());
           _MoneyRepository.save(money);
           session.removeAttribute("childids");
           List<Child> childids =_cChildRepository.findByUser(user);	
      	   WebUtils.setSessionAttribute(request, "childids", childids.get(0).getId());
      	 return "redirect:/myinformation/baby";
	       }
       if (!file.isEmpty())
       {
           try
           {
               byte[] bytes = file.getBytes();
               File directory = new File(filePath);
               if (!directory.exists())
               {
                   directory.mkdirs();
               }
               String fileName =file.getOriginalFilename();

               filePath +=fileName;
               //String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
              
               
               
               String lujing =File.separator+_fileUploadSetting.getChildPath()+fileName;
               
               
               
               Image image = new Image();
               image.setCreateTime(new Date());
               image.setFileName(fileName);
               image.setFilePath(lujing);
               _imageRepository.save(image);
               System.out.println(request.getParameter("addbirth"));
               child.setAge(Integer.parseInt(request.getParameter("addage")));
               child.setName(request.getParameter("addname"));
               SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
               child.setBirth(sdf.parse(request.getParameter("addbirth")));
               child.setCreateTime(new Date());
               child.setGender(Integer.parseInt(request.getParameter("gender")));
               child.setBalance(new BigDecimal(0));
               child.setUser(user); 
               child.setImage(image);
               _cChildRepository.save(child);
               Money money=new Money();
               money.setBalance(new BigDecimal(0));
               money.setChildId(child);
               money.setCreateTime(new Date());
               _MoneyRepository.save(money);
               session.removeAttribute("childids");
               List<Child> childids =_cChildRepository.findByUser(user);	
          	   WebUtils.setSessionAttribute(request, "childids", childids.get(0).getId());
               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
               stream.write(bytes);
               stream.close();
               return "redirect:/myinformation/baby";
           }
           catch (Exception e)
           {
               model.addAttribute("errorMessage", "上传文件失败");
               model.addAttribute("child", _cChildRepository.findOne(child.getId()));
               return "/myinformation/baby";
           }
       }
       return "redirect:/myinformation/baby";
      

   }
   
   
   //删除孩子信息
   @ResponseBody
   @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
   public String delete(@PathVariable int id,HttpSession session, HttpServletRequest request,Model model)
   {
	   
	  
		  _defineTaskRepository.deleteByChildId(id);
			_worktaskRepository.deleteByChildId(id);  
			_shopHistoryRepository.deleteByChildId(id);
			_CustomCommodityRepository.deleteByChildId(id);
			_buyPlanRepository.deleteByChildId(id);
			_MoneyRepository.deleteByChildIdId(id);
			_cChildRepository.delete(id);	
			session.removeAttribute("childids");
			  User user = (User) request.getSession().getAttribute("user");
			
			   List<Child> child =_cChildRepository.findByUser(user);	
			if(child.size()==0){
				 WebUtils.setSessionAttribute(request, "childids", "");
				/* Child childlistcheck = (Child) request.getSession().getAttribute("childids");
				 model.addAttribute("childlistcheck", childlistcheck);*/
				 String status="1";
				return status;
			}else{
			 WebUtils.setSessionAttribute(request, "childids", child.get(0).getId());
			 String status="2";
			 return status;
			}
			
   }

   
  /* @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
   public String updateInit(@PathVariable int id, Model model,HttpServletRequest request)
   { 
	   User user = (User) request.getSession().getAttribute("user");
       List <Child> child = _cChildRepository.findByUser(user);
       model.addAttribute("child",child);
       return "/myinformation/babyupdate";
   }*/
   //修改孩子信息
  /* @RequestMapping(value = "/update",method = RequestMethod.POST)
   public String update(HttpServletRequest request,@RequestParam("file") MultipartFile[] file,Model model) throws ParseException
   {
	   String parameter = request.getParameter("testdaye");
	   Integer index=Integer.parseInt(request.getParameter("index"));
       Child oldchild =_cChildRepository.findOne(Integer.parseInt(request.getParameter("babygendereditid")));
       String filePath = _fileUploadSetting.getRootPath()+File.separator+_fileUploadSetting.getChildPath();
       oldchild.setEditTime(new Date());
       if(file==null && file.length==0){
    	   if(_cChildRepository.findByImageId(oldchild.getId())==null){
    		   oldchild.setImage(null);
    	   }else{
    	   oldchild.setImage(_imageRepository.findByid(Integer.parseInt(request.getParameter("childimg"))));
    	   }
           oldchild.setAge(Integer.parseInt(request.getParameter("ages")));
           oldchild.setName(request.getParameter("name"));
           oldchild.setEditTime(new Date());
           SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
           oldchild.setBirth(sdf.parse(request.getParameter("birth")));
           _cChildRepository.save(oldchild);
           return "redirect:/myinformation/baby";
       }
       if (file!=null&&file.length>0)
       {
           try
           {
             
        	   for(int i = 0;i<file.length;i++){
        	   byte[] bytes = file[i].getBytes();
        	   if(bytes.length>0){  
               File directory = new File(filePath);
               if (!directory.exists())
               {
                   directory.mkdirs();
               }
               String fileName =file[i].getOriginalFilename();
               filePath +=fileName;        
               String lujing =File.separator+_fileUploadSetting.getChildPath()+fileName;
               Image image = new Image();
               image.setCreateTime(new Date());
               image.setFileName(fileName);
               image.setFilePath(lujing);
               _imageRepository.save(image);
               oldchild.setImage(image);
               oldchild.setAge(Integer.parseInt(request.getParameter("ages")));
               oldchild.setName(request.getParameter("name"));
               SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
               oldchild.setEditTime(new Date());
               oldchild.setBirth(sdf.parse(request.getParameter("birth")));
               _cChildRepository.save(oldchild);
               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
               stream.write(bytes);
               stream.close();
        		}
        	   }
               return "redirect:/myinformation/baby";
           }
           catch (Exception e)
           {
               model.addAttribute("errorMessage", "上传文件失败");
               model.addAttribute("cityactive", _cChildRepository.findOne(Integer.parseInt(request.getParameter("babygendereditid"))));
               return "/myinformation/baby";
           }
   } else
   {
       model.addAttribute("errorMessage", "请选择上传文件");
       return "/myinformation/baby";
   }

}*/
   @RequestMapping(value = "/update",method = RequestMethod.POST)
   public String update(HttpServletRequest request,@RequestParam("file") MultipartFile[] file,Model model) throws ParseException
   {
	   Integer index=Integer.parseInt(request.getParameter("index"));
       Child oldchild =_cChildRepository.findOne(Integer.parseInt(request.getParameter("babygendereditid")));
       String filePath = _fileUploadSetting.getRootPath()+File.separator+_fileUploadSetting.getChildPath();
       oldchild.setEditTime(new Date());
       /*if(file==null && file.length==0){
    	   if(_cChildRepository.findByImageId(oldchild.getId())==null){
    		   oldchild.setImage(null);
    	   }else{
    	   oldchild.setImage(_imageRepository.findByid(Integer.parseInt(request.getParameter("childimg"))));
    	   }
           oldchild.setAge(Integer.parseInt(request.getParameter("ages")));
           oldchild.setName(request.getParameter("name"));
           oldchild.setEditTime(new Date());
           SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
           oldchild.setBirth(sdf.parse(request.getParameter("birth")));
           _cChildRepository.save(oldchild);
           return "redirect:/myinformation/baby";
       }*/
       if (file!=null&&file.length>0)
       {
           try
           {
        	   for(int i = 0;i<file.length;i++){
        	   byte[] bytes = file[i].getBytes();
        	   if(bytes.length>0){  
               File directory = new File(filePath);
               if (!directory.exists())
               {
                   directory.mkdirs();
               }
               String fileName =file[i].getOriginalFilename();
               filePath +=fileName;        
               String lujing =File.separator+_fileUploadSetting.getChildPath()+fileName;
               Image image = new Image();
               image.setCreateTime(new Date());
               image.setFileName(fileName);
               image.setFilePath(lujing);
               _imageRepository.save(image);
               oldchild.setImage(image);
               oldchild.setAge(Integer.parseInt(request.getParameter("ages")));
               oldchild.setName(request.getParameter("name"));
               SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
               oldchild.setEditTime(new Date());
               oldchild.setBirth(sdf.parse(request.getParameter("birth")));
               oldchild.setBalance(new BigDecimal(0));
               _cChildRepository.save(oldchild);
               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
               stream.write(bytes);
               stream.close();
        		}else{
        			oldchild.setAge(Integer.parseInt(request.getParameter("ages")));
                    oldchild.setName(request.getParameter("name"));
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                    oldchild.setEditTime(new Date());
                    oldchild.setBirth(sdf.parse(request.getParameter("birth")));
                    oldchild.setGender(Integer.parseInt(request.getParameter("gender")));
                    oldchild.setBalance(new BigDecimal(0));
                    _cChildRepository.save(oldchild);
        		}
        	   }
               return "redirect:/myinformation/baby";
           }
           catch (Exception e)
           {
               model.addAttribute("errorMessage", "上传文件失败");
               model.addAttribute("cityactive", _cChildRepository.findOne(Integer.parseInt(request.getParameter("babygendereditid"))));
               return "/myinformation/baby";
           }
   } else
   {
       model.addAttribute("errorMessage", "请选择上传文件");
       return "/myinformation/baby";
   }

}
   
   
   
   //查看详细信息
   
   @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
   public String detail(@PathVariable int id, Model model)
   {
	   Child child = _cChildRepository.findOne(id);
       model.addAttribute("child", child);
       Image image = child.getImage();
       model.addAttribute("image", image);
       return "/myinformation/babydetail";
   }
   
   
   //新建任务
   @RequestMapping(value="/createtask",method = RequestMethod.POST)
	public String addInsurancePlan(HttpServletRequest request,Model model,@RequestParam("id") Integer id) {
	   Child childss = _cChildRepository.findById(id);
		String[] child =request.getParameter("childs").split(",");
		String[] times =request.getParameter("timess").split(",");
		String[] incommodityid =request.getParameter("commodityids").split(",");
		String[] task =request.getParameter("names").split(",");
		String[] everytimesmoney =request.getParameter("everytimesmoneys").split(",");
		String[] workrelate =request.getParameter("workrelate").split(",");
		String workrelates =request.getParameter("workrelate");
		if(task.length>0){
		//删除之前插入到历史任务表
			if(workrelates.length()!=0&&workrelate.length!=1){
		    for (int i = 0; i < child.length; i++) {
		    WorkTaskRelate workTaskRelate=_worktaskRepository.findById(new Integer(workrelate[i]));
		    DefineTask historytask =new DefineTask();
			historytask.setChild(childss);
			historytask.setEasyTaskId(_EasyCommdityRepository.findByWorkTaskRelateId(new Integer(workrelate[i])));
			historytask.setTaskTimes(workTaskRelate.getTimes());
		    User user = (User) request.getSession().getAttribute("user");
		    historytask.setUser(user);
		    historytask.setTimesAdd(workTaskRelate.getTimeAdd());
		    historytask.setTaskName(_taskRepository.findBytaskNameIds(new Integer(workrelate[i])));
		   /* historytask.setStartTime(new Date());*/
		    _defineTaskRepository.save(historytask);
		}	
			}
		}
	//	_worktaskRepository.deleteByChildId(id);
		for (int i = 0; i < child.length; i++) {
			if(!times[i].equals("0")&&!incommodityid[i].equals("")){
				WorkTaskRelate worktask =new WorkTaskRelate();
				Integer childs=new Integer(child[i]);
				worktask.setChild(childss);
				worktask.setCreateTime(new Date());
				Integer commdityids=new Integer(incommodityid[i]);
				worktask.setEasyTask(_EasyCommdityRepository.findById(commdityids));
				worktask.setStatus(1);
				worktask.setTimes(new Integer(times[i]));
			    User user = (User) request.getSession().getAttribute("user");
			    worktask.setUser(user);
			    worktask.setTimeAdd(0);
			    String tasks =task[i];
			    worktask.setTask(_taskRepository.findByName(tasks));
			    worktask.setEverytimesmoney(new BigDecimal(everytimesmoney[i]));
	            _worktaskRepository.save(worktask);
			}else{
				 return "redirect:/myinformation/searchworktasklist";
			}
		}
		return "redirect:/project/li";
	}
   
   
   
   
   
   
   //批量新建任务
   @RequestMapping(value="/createBabylist",method = RequestMethod.POST)
	public String createBabylist(HttpServletRequest request,Model model) {
		String child =request.getParameter("childs");
		model.addAttribute("child", child);
		return "/myinformation/tasklist";
	}
   //批量增加任务
   @RequestMapping(value="/createtasklist",method = RequestMethod.POST)
  	public String createtasklist(HttpServletRequest request,Model model) { 
	    String[] ids =request.getParameter("id").split(",");
		String[] child =request.getParameter("childs").split(",");
		String[] times =request.getParameter("timess").split(",");
		String[] incommodityid =request.getParameter("commodityids").split(",");
		String[] task =request.getParameter("names").split(",");
		String[] everytimesmoney =request.getParameter("everytimesmoneys").split(",");
		String[] workrelate =request.getParameter("workrelate").split(",");
		String workrelates =request.getParameter("workrelate");
		for(int j=0;j<ids.length;j++){
			if(child.length>0){
			//删除之前插入到历史任务
	    	List <WorkTaskRelate>list =_worktaskRepository.findBychildIds(new Integer(ids[j]));
		    if(list.size()!=0){
			for (int i = 0; i < 1; i++) {
		    		System.out.println(list.size()==0);
				 Child childss = _cChildRepository.findById(new Integer(ids[j]));
				List<WorkTaskRelate> workTaskRelate=_worktaskRepository.findBychildIdss(childss.getId());
				for(int s=0;s<workTaskRelate.size();s++){
					DefineTask historytask =new DefineTask();
					historytask.setChild(childss);
				/*	historytask.setEasyTaskId(_EasyCommdityRepository.findByWorkTaskRelateId(new Integer(workrelate[i])));*/
					historytask.setEasyTaskId( workTaskRelate.get(s).getEasyTask());
					historytask.setTaskTimes(workTaskRelate.get(s).getTimes());
				    User user = (User) request.getSession().getAttribute("user");
				    historytask.setUser(user);
				    historytask.setTimesAdd( workTaskRelate.get(s).getTimeAdd());
				    historytask.setTaskName(_taskRepository.findBytaskNameIds(new Integer(workTaskRelate.get(s).getId())));
				 /*   historytask.setStartTime(new Date());*/
				    _defineTaskRepository.save(historytask);
				}	
		    	}
			}
			}
	//	_worktaskRepository.deleteByChildId(new Integer(ids[j]));
		for (int i = 0; i < child.length; i++) {
			if(!times[i].equals("0")&&!incommodityid[i].equals("")){
				 Child childss = _cChildRepository.findById(new Integer(ids[j]));
				WorkTaskRelate worktask =new WorkTaskRelate();
				Integer childs=new Integer(child[i]);
				worktask.setChild(childss);
				worktask.setCreateTime(new Date());
				Integer commdityids=new Integer(incommodityid[i]);
				worktask.setEasyTask(_EasyCommdityRepository.findById(commdityids));
				worktask.setStatus(1);
				worktask.setTimes(new Integer(times[i]));
			    User user = (User) request.getSession().getAttribute("user");
			    worktask.setUser(user);
			    String tasks =task[i];
			    worktask.setTask(_taskRepository.findByName(tasks));
			    worktask.setTimeAdd(0);
			    worktask.setEverytimesmoney(new BigDecimal(everytimesmoney[i]));
	            _worktaskRepository.save(worktask);
			}else{
				 return "redirect:/myinformation/createBabylist";
			}
		}}
		return "/myinformation/baby";
  	}
   //批量删除
   @RequestMapping(value = "/deBabylist", method = RequestMethod.GET)
   public String deBabylist(HttpServletRequest request,Model model)
   {
	   String[] childs =request.getParameter("childs").split(",");
	
		for(int i=0;i<childs.length;i++){	
			_defineTaskRepository.deleteByChildId(new Integer(childs[i]));
		//	_worktaskRepository.deleteByChildId(new Integer(childs[i]));  
			_shopHistoryRepository.deleteByChildId(new Integer(childs[i]));
			_CustomCommodityRepository.deleteByChildId(new Integer(childs[i]));
		//	_buyPlanRepository.deleteByChildId(new Integer(childs[i]));
			_cChildRepository.delete(new Integer(childs[i]));			  
		}
			 return "/myinformation/baby";
   }
   
 
   //新建任务
   @RequestMapping(value="/createBabyTasklist",method = RequestMethod.POST)
	public String createBabyTasklist(HttpServletRequest request,Model model) {
	   User user = (User) request.getSession().getAttribute("user");
		String clickchildid =request.getParameter("clickchildid");
		String taskName=request.getParameter("moneynumber");
		String tokenNumbers = request.getParameter("tokenNumbers");
		String taskTimes =request.getParameter("taskTimes");
		DefineTask historytask=new DefineTask();
		Child childss = _cChildRepository.findById(Integer.parseInt(clickchildid));
		historytask.setChild(childss);
		historytask.setTaskName(taskName);
		historytask.setTokennumbers(Integer.parseInt(tokenNumbers));
		historytask.setTimesAdd(0);
		historytask.setTaskTimes(Integer.parseInt(taskTimes));
		historytask.setUser(user);
		historytask.setEasyTaskId(_EasyCommdityRepository.findById(1));
	/*	historytask.getEasyTaskId().setId(1);*/
		historytask.setStartTime(new Date());
		historytask.setTaskstatus(0);
		_defineTaskRepository.save(historytask);
		return "redirect:/project/li/"+clickchildid;
	}

   
   
   //没有宝贝
   //新增Child
   @RequestMapping(value = "/createbaby", method = RequestMethod.GET)
   public String createbaby(@ModelAttribute Child child, Model model,HttpServletRequest request)
   {
	   User user = (User) request.getSession().getAttribute("user");
       model.addAttribute("user", user);
       model.addAttribute("child", child);
       return "/myinformation/nobabycreate";

   }
   
   
   @RequestMapping(value = "/createbaby", method = RequestMethod.POST)
   public String createbaby(@Valid Child child, BindingResult bindingResult, Model model, HttpServletRequest request,
           @RequestParam("file") MultipartFile file,HttpSession session) throws ParseException
   {
       String filePath = _fileUploadSetting.getRootPath()+File.separator+_fileUploadSetting.getChildPath();
       User user = (User) request.getSession().getAttribute("user");
       model.addAttribute("user", user);
       child.setCreateTime(new Date());
       if(file.isEmpty()){
    	   child.setAge(Integer.parseInt(request.getParameter("addage")));
           child.setName(request.getParameter("addname"));
           SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
           child.setBirth(sdf1.parse(request.getParameter("addbirth")));
           child.setCreateTime(new Date());
           child.setGender(Integer.parseInt(request.getParameter("gender")));
           child.setUser(user);    
           _cChildRepository.save(child);
           Money money=new Money();
           money.setBalance(new BigDecimal(0));
           money.setChildId(child);
           money.setCreateTime(new Date());
           _MoneyRepository.save(money);
           session.removeAttribute("childids");
           List<Child> childids =_cChildRepository.findByUser(user);	
      	   WebUtils.setSessionAttribute(request, "childids", childids.get(0).getId());
      	 return "redirect:/myinformation/baby";
	       }
       if (!file.isEmpty())
       {
           try
           {
               byte[] bytes = file.getBytes();
               File directory = new File(filePath);
               if (!directory.exists())
               {
                   directory.mkdirs();
               }
               String fileName =file.getOriginalFilename();

               filePath +=fileName;
               //String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
              
               
               
               String lujing =File.separator+_fileUploadSetting.getChildPath()+fileName;
               
               
               
               Image image = new Image();
               image.setCreateTime(new Date());
               image.setFileName(fileName);
               image.setFilePath(lujing);
               _imageRepository.save(image);
               child.setAge(Integer.parseInt(request.getParameter("addage")));
               child.setName(request.getParameter("addname"));
               SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
               child.setBirth(sdf.parse(request.getParameter("addbirth")));
               child.setCreateTime(new Date());
               child.setGender(Integer.parseInt(request.getParameter("gender")));
              
               child.setUser(user); 
               child.setImage(image);
               _cChildRepository.save(child);
               Money money=new Money();
               money.setBalance(new BigDecimal(0));
               money.setChildId(child);
               money.setCreateTime(new Date());
               _MoneyRepository.save(money);
               session.removeAttribute("childids");
               List<Child> childids =_cChildRepository.findByUser(user);	
          	   WebUtils.setSessionAttribute(request, "childids", childids.get(0).getId());
               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
               stream.write(bytes);
               stream.close();
               return "redirect:/myinformation/baby";
           }
           catch (Exception e)
           {
               model.addAttribute("errorMessage", "上传文件失败");
               model.addAttribute("child", _cChildRepository.findOne(child.getId()));
               return "/myinformation/nobabycreate";
           }
       }
       return "redirect:/myinformation/baby";
      

   }
}
