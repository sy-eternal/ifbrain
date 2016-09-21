package com.jzeen.travel.website.controller;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.DefineTask;
import com.jzeen.travel.data.entity.Money;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.WorkTaskRelate;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.DefineTaskRepository;
import com.jzeen.travel.data.repository.MoneyRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.WorkTaskRelateRepository;

@Controller
@RequestMapping("/project")
public class ProjectController {
	@Autowired
	WorkTaskRelateRepository _workTaksRepository;

	@Autowired
	DefineTaskRepository _defineTaskTaskRepository;
	@Autowired
	ChildRepository _childRepository;
	@Autowired
	UserRepository _userRepository;
	@Autowired
	MoneyRepository _moneyRepository;
	//根据下拉框选择宝贝查询当前任务
	@ResponseBody
	@RequestMapping(value = "/list/{childId}", method = RequestMethod.GET)
	public Iterable<WorkTaskRelate> findByUserId(@PathVariable Integer childId,
			HttpServletRequest request, Model model) {
		//获取当前登陆用户
	
		User user = (User) request.getSession().getAttribute("user");
		
		List<WorkTaskRelate> worktask;
		System.out.println("childId"+childId);
		//如果下拉框为选择，默认value=-1
		if (childId > 0 ) {
			//根据用户与任务状态查询全部任务
			
			Child child = _childRepository.findOne(childId);
			//根据孩子，任务状态，用户 查询任务
			worktask = _workTaksRepository.findByUserAndStatusAndChild(user, 1,
					child);
			//根据孩子查询money
			Money money = _moneyRepository.findByChildId(child);
			model.addAttribute("money",money);
		} else {
			//根据孩子ID查询孩子
			worktask = _workTaksRepository.findByUserAndStatus(user, 1);
		}
		return worktask;
	}
/*
	@RequestMapping(value = "/listload", method = RequestMethod.GET)
	public String indexs(HttpServletRequest request, Model model) {
		
		 * String child=request.getParameter("child"); Integer childId =
		 * Integer.parseInt(child); Child child2 =
		 * _childRepository.findOne(childId); List<Commodity> commodities =
		 * _commodityRepository.findAll(); model.addAttribute("child",child2);
		 * model.addAttribute("commodities",commodities);
		 
		String child1 = request.getParameter("child");
		Integer childId = Integer.parseInt(child1);
		Child child = _childRepository.findOne(childId);
		User user = (User) request.getSession().getAttribute("user");
		List<WorkTaskRelate> worktask = _workTaksRepository
				.findByUserAndStatusAndChild(user, 1, child);
		Money money = _moneyRepository.findByChildId(child);
		model.addAttribute("worktask", worktask);
		model.addAttribute("child", childId);
		model.addAttribute("money", money.getBalance());
		return "/project/listload";
	}*/
	
	@RequestMapping(value="shiyong",method=RequestMethod.GET)
	public String shiyong(HttpServletRequest request){
		
		User user = (User) request.getSession().getAttribute("user");
		if(user!=null){
			HttpSession  session = request.getSession(true);
			session.setAttribute("user", user);
		}else{
		 user = _userRepository.findOne(421);
		HttpSession  session = request.getSession(true);
		session.setAttribute("user", user);}
	
		return "redirect:/myinformation/baby";
	}
//跳转到List页面az
	@RequestMapping(value = "/li/{id}", method = RequestMethod.GET)
	public String list(@PathVariable Integer id,HttpServletRequest request, Model model) {
		//获取用户
		User user = (User) request.getSession().getAttribute("user");
		List<Child> child =_childRepository.findByUser(user);
       
		//未完成任务
		List<DefineTask> historyList =new  ArrayList<DefineTask>();
		//已完成任务
		List<DefineTask> finishhistoryList =new  ArrayList<DefineTask>();
		
		
		//未完成任务
		List<DefineTask>  historyTaskList=new ArrayList<DefineTask>();
		//已完成任务
		List<DefineTask>  finishhistoryTaskList=new ArrayList<DefineTask>();
		//未完成任务
		StringBuffer integerrate=new StringBuffer();
		//已完成任务
		StringBuffer finishintegerrate=new StringBuffer();
		if(child.size()>0){
	     //historyTaskList =_historyTaskRepository.findByChildId(child.get(0).getId());
			
			//未完成任务
			historyTaskList=_defineTaskTaskRepository.findByChildAndTaskstatus(id, 0);
		    //已完成任务
			finishhistoryTaskList=_defineTaskTaskRepository.findByChildAndTaskstatus(id, 1);
		}
		//未完成任务
		if(historyTaskList.size()>0){
			for(int i=0;i<historyTaskList.size();i++){
				DefineTask historyTask = historyTaskList.get(i);
				BigDecimal 	tasktime=new BigDecimal(historyTask.getTaskTimes());
				BigDecimal timesadd=new BigDecimal(historyTask.getTimesAdd());
				if(timesadd.intValue()>0){
					BigDecimal rate = timesadd.divide(tasktime,2,BigDecimal.ROUND_HALF_UP);
					NumberFormat nt = NumberFormat.getPercentInstance();
					String percent = nt.format(rate.doubleValue());
					historyTask.setRate(percent);
					integerrate.append(percent.replaceAll("%", ""));
					integerrate.append(",");
					model.addAttribute("integerrate", integerrate.substring(0, integerrate.length()-1));
				}else{
					historyTask.setRate("0");
					integerrate.append("0");
					integerrate.append(",");
					model.addAttribute("integerrate", integerrate.substring(0, integerrate.length()-1));
				}
				historyList.add(historyTask);
			}
			}
		//已完成任务
		if(finishhistoryTaskList.size()>0){
			for(int i=0;i<finishhistoryTaskList.size();i++){
				DefineTask historyTask = finishhistoryTaskList.get(i);
				BigDecimal 	tasktime=new BigDecimal(historyTask.getTaskTimes());
				BigDecimal timesadd=new BigDecimal(historyTask.getTimesAdd());
				if(timesadd.intValue()>0){
					BigDecimal rate = timesadd.divide(tasktime,2,BigDecimal.ROUND_HALF_UP);
					NumberFormat nt = NumberFormat.getPercentInstance();
					String percent = nt.format(rate.doubleValue());
					historyTask.setRate(percent);
					finishintegerrate.append(percent.replaceAll("%", ""));
					finishintegerrate.append(",");
					model.addAttribute("finishintegerrate", finishintegerrate.substring(0, finishintegerrate.length()-1));
				}else{
					historyTask.setRate("0");
					finishintegerrate.append("0");
					finishintegerrate.append(",");
					model.addAttribute("finishintegerrate", finishintegerrate.substring(0, finishintegerrate.length()-1));
				}
				finishhistoryList.add(historyTask);
			}
			}
		
		
		
		
		//未完成任务
		model.addAttribute("historyList",historyList);
		//已完成任务
		model.addAttribute("finishhistoryList",finishhistoryList);
		//在前台要判断的是否有历史任务
		model.addAttribute("finishhistorysize", finishhistoryList.size());
		
		model.addAttribute("flagvalue", id);
		model.addAttribute("childs", child);

		/*List<WorkTaskRelate> worktask = _workTaksRepository.findByUserAndStatus(user, 1);*/
	//	List<WorkTaskRelate> worktask = _workTaksRepository.findBychildIdsss(id);
		 //List<WorkTaskRelate> worktask = _workTaksRepository.findAll();
	//	model.addAttribute("worktask",worktask);
		model.addAttribute("child", child);

		model.addAttribute("childId", id);
		return "/project/list";
	}
	
	
	
	//没有宝贝跳转到List页面
		@RequestMapping(value = "/li", method = RequestMethod.GET)
		public String lists(HttpServletRequest request, Model model) {
			//获取用户
			User user = (User) request.getSession().getAttribute("user");
			List<Child> child =_childRepository.findByUser(user);
			List<DefineTask>  finishhistoryTaskList=new ArrayList<DefineTask>();
			//未完成任务
			List<DefineTask> historyList =new  ArrayList<DefineTask>();
			//已完成任务
			List<DefineTask> finishhistoryList =new  ArrayList<DefineTask>();
			//已完成任务
			StringBuffer finishintegerrate=new StringBuffer();
			if(finishhistoryTaskList.size()>0){
				for(int i=0;i<finishhistoryTaskList.size();i++){
					DefineTask historyTask = finishhistoryTaskList.get(i);
					BigDecimal 	tasktime=new BigDecimal(historyTask.getTaskTimes());
					BigDecimal timesadd=new BigDecimal(historyTask.getTimesAdd());
					if(timesadd.intValue()>0){
						BigDecimal rate = timesadd.divide(tasktime,2,BigDecimal.ROUND_HALF_UP);
						NumberFormat nt = NumberFormat.getPercentInstance();
						String percent = nt.format(rate.doubleValue());
						historyTask.setRate(percent);
						finishintegerrate.append(percent.replaceAll("%", ""));
						finishintegerrate.append(",");
						model.addAttribute("finishintegerrate", finishintegerrate.substring(0, finishintegerrate.length()-1));
					}else{
						historyTask.setRate("0");
						finishintegerrate.append("0");
						finishintegerrate.append(",");
						model.addAttribute("finishintegerrate", finishintegerrate.substring(0, finishintegerrate.length()-1));
					}
					finishhistoryList.add(historyTask);
				}
				}
			
			
			
			
			//未完成任务
			model.addAttribute("historyList",historyList);
			//已完成任务
			model.addAttribute("finishhistoryList",finishhistoryList);
			//在前台要判断的是否有历史任务
			model.addAttribute("finishhistorysize", finishhistoryList.size());
			model.addAttribute("childs", child);
			/*List<WorkTaskRelate> worktask = _workTaksRepository.findByUserAndStatus(user, 1);*/
		//	List<WorkTaskRelate> worktask = _workTaksRepository.findBychildIdsss(id);
			 //List<WorkTaskRelate> worktask = _workTaksRepository.findAll();
		//	model.addAttribute("worktask",worktask);
			model.addAttribute("child", child);
			return "/project/list";
		}
	
	     /**根据孩子id获得相应孩子的任务
	     * @param request
	     * @param response
	     * @param model
	     * @return
	     */
	    /*@ResponseBody
	    @RequestMapping(value = "/findByChildId",method = RequestMethod.GET)
	    public String findById(HttpServletRequest request,HttpServletResponse response,Model model)
	    {
	    	String childId = request.getParameter("childId");
	    	Child child = _childRepository.findOne(Integer.parseInt(childId));
	    	List<HistoryTask> historyTaskList = _historyTaskRepository.findByChildId(Integer.parseInt(childId));
	    	JSONArray  jsonarray=new JSONArray();
	    	for(int i=0;i<historyTaskList.size();i++){
	    		JSONObject object=new JSONObject();
	    		object.put("babyname", child.getName());
	    		object.put("taskname", historyTaskList.get(i).getTaskName());
	    		object.put("tokennumbers", historyTaskList.get(i).getTokennumbers());
	    		object.put("timesadd", historyTaskList.get(i).getTimesAdd());
	    		object.put("tasktimes", historyTaskList.get(i).getTaskTimes());
	    		jsonarray.put(object);
	    	}
	    	JSONObject  object=new JSONObject();
	    	object.put("list", jsonarray.toString());
	    	return object.toString();
	    } */
	    
	    //根据孩子id获得相应孩子的历史任务
	    @ResponseBody
	    @RequestMapping(value = "/findByChildIds",method = RequestMethod.GET)
	    public String findByIds(HttpServletRequest request,HttpServletResponse response,Model model) 
	    {
	    	String id = request.getParameter("childId");
	    	List<DefineTask> historyTask = _defineTaskTaskRepository.findByChildIds(Integer.parseInt(id));
	    	JSONArray  jsonarray=new JSONArray();
	    	for(int i=0;i<historyTask.size();i++){
	    		JSONObject object=new JSONObject();
	    		object.put("taskname",historyTask.get(i).getTaskName());
	    		object.put("childname", historyTask.get(i).getChild().getName());
	    		//object.put("startTimes",historyTask.get(i).getStartTime().toString().substring(0,19));
	    		/*object.put("startTimes",historyTask.get(i).getStartTime().toString().substring(0,19));*/
	    		object.put("taskTimes", historyTask.get(i).getTaskTimes());
	    		object.put("timeadd", historyTask.get(i).getTimesAdd());
	    		object.put("id", historyTask.get(i).getId());
	    		object.put("childId", historyTask.get(i).getChild().getId());
	    		jsonarray.put(object);
	    	}
	    	JSONObject  object=new JSONObject();
	    	object.put("list", jsonarray.toString());
	    	return object.toString();
	    } 
	
	//完成一次任务，timeAdd+1 money.banlace+每次完成奖励金币
	@RequestMapping(value = "/add/{id}/{childId}", method = RequestMethod.GET)
	public String add(@PathVariable Integer id, @PathVariable Integer childId,
			HttpServletRequest request, Model model) {
		Child child = _childRepository.findOne(childId);
		WorkTaskRelate WorkTaskRelate = _workTaksRepository.findOne(id);
		if (WorkTaskRelate.getTimeAdd() < WorkTaskRelate.getTimes()) {
			int timeAdd = WorkTaskRelate.getTimeAdd() + 1;
			WorkTaskRelate.setTimeAdd(timeAdd);
		}
		Money money = _moneyRepository.findByChildId(child);
		//获取这个孩子的balance
		BigDecimal balance = money.getBalance();
		//获取每次完成的金币数
		BigDecimal timesmoney = WorkTaskRelate.getEverytimesmoney();
		//加法
		money.setBalance(balance.add(timesmoney));
		_moneyRepository.save(money);
		_workTaksRepository.save(WorkTaskRelate);
		return "redirect:/project/li/"+childId;

	}
	//结束任务，保存到历史记录表
	@RequestMapping(value = "/endadd/{id}", method = RequestMethod.GET)
	public String endadd( HttpServletRequest request,
			Model model,@PathVariable Integer id) {
		/*String id = request.getParameter("id");
		Integer ID =  Integer.parseInt(id);*/
		WorkTaskRelate WorkTaskRelate = _workTaksRepository.findOne(id);
		DefineTask historyTask = new DefineTask();
		historyTask.setChild(WorkTaskRelate.getChild());
		historyTask.setEasyTaskId(WorkTaskRelate.getEasyTask());
		historyTask.setTaskTimes(WorkTaskRelate.getTimes());
		historyTask.setTaskName(WorkTaskRelate.getTask().getName());
		//historyTask.setUser(WorkTaskRelate.getUser());
		historyTask.setTimesAdd(WorkTaskRelate.getTimeAdd());
	/*	historyTask.setStartTime(WorkTaskRelate.getCreateTime());*/
		WorkTaskRelate.setStatus(0);
		_workTaksRepository.save(WorkTaskRelate);
		_defineTaskTaskRepository.save(historyTask);
		return "redirect:/project/li/"+WorkTaskRelate.getChild().getId();

	}
	//跳转到历史人物页面
	@RequestMapping(value = "/ls/{id}", method = RequestMethod.GET)
	public String ls(Model model,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		List<Child> child = _childRepository.findByUser(user);
		List<DefineTask> HistoryTask = _defineTaskTaskRepository.findByUser(user);

		model.addAttribute("child", child);
		model.addAttribute("HistoryTask", HistoryTask);
		return "/project/listls";

	}
	//根据孩子查询历史任务
	@ResponseBody
	@RequestMapping(value = "/lslist/{childId}", method = RequestMethod.GET)
	public Iterable<DefineTask> findByUserIdls(@PathVariable Integer childId,
			HttpServletRequest request, Model model) {

		User user = (User) request.getSession().getAttribute("user");
		List<DefineTask> HistoryTask;
		if (childId < 0) {
			HistoryTask = _defineTaskTaskRepository.findByUser(user);
		} else {
			Child child = _childRepository.findOne(childId);
			HistoryTask = _defineTaskTaskRepository
					.findByUserAndChild(user, child);
		}
		return HistoryTask;

	}
	@ResponseBody
	@RequestMapping(value="/findMoney", method = RequestMethod.POST)
	public Money findMoney(HttpServletRequest request){
		String cc = request.getParameter("cc");
	/*	Integer childId = Integer.parseInt(cc);*/
		Child child = _childRepository.findByName(cc);
		
		Money money = _moneyRepository.findByChildId(child);
		System.out.println(money);
		return money;
	}
	//批量删除
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String del(Model model,HttpServletRequest request) {
		String cc=request.getParameter("chkvalue");
		 String[] arr=cc.split(",");
		 List<String> list = Arrays.asList(arr);
		 System.out.println(list.size());
	    for(int i =0;i<list.size();i++){
	    	int a=Integer.valueOf(list.get(i)).intValue(); 
	    	_defineTaskTaskRepository.delete(a);
	    }
		return "redirect:/project/ls";

	}
	
	//首页导航栏跳转到代币银行
	  @RequestMapping(value = "/daibibank", method = RequestMethod.GET)
		public String  daibibank(Model model,HttpServletRequest request){
		 return  "/home/daibibank";
	   }
	
	   @ResponseBody
	   @RequestMapping(value = "/delhistorytask/{id}", method = RequestMethod.DELETE)
	   public void delhistorytask(@PathVariable int id)
	   {
		   _defineTaskTaskRepository.delete(id);	

	   }
}
