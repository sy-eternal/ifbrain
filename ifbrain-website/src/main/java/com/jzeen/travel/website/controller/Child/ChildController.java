package com.jzeen.travel.website.controller.Child;



import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.BuyPlan;
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.Commodity;
import com.jzeen.travel.data.entity.CommodityType;
import com.jzeen.travel.data.entity.DefineTask;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Money;
import com.jzeen.travel.data.entity.ShopHistory;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.BuyPlanRepository;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.CommodityRepository;
import com.jzeen.travel.data.repository.CommodityTypeRepository;
import com.jzeen.travel.data.repository.CustomCommodityRepository;
import com.jzeen.travel.data.repository.DefineTaskRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.MoneyRepository;
import com.jzeen.travel.data.repository.ShopHistoryRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.WorkTaskRelateRepository;
import com.jzeen.travel.website.setting.FileUploadSetting;

@Controller
@RequestMapping("/child")
public class ChildController
{
    @Autowired
    private ChildRepository _cChildRepository;
    @Autowired
    DefineTaskRepository _defineTaskTaskRepository;
    @Autowired
    ImageRepository _imageResponsitory;
    
	@Autowired
	private ImageRepository _imageRepository;


	@Autowired
	FileUploadSetting _fileUploadSetting;

	@Autowired
	private WorkTaskRelateRepository _worktaskRepository;

	@Autowired
	private UserRepository _userRepository;

	@Autowired
	private ChildRepository _childRepository;

	@Autowired
	private CommodityRepository _commodityRepository;

	@Autowired
	private CommodityTypeRepository _commodityTypeRepository;

	@Autowired
	private BuyPlanRepository _buyPlanRepository;

	@Autowired
	private ShopHistoryRepository _shopHistoryRepository;

	@Autowired
	private MoneyRepository _moneyRepository;

	@Autowired
	private CustomCommodityRepository _customCommodityRepository;
    /**根据孩子id查找孩子
     * @param id
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/findById",method = RequestMethod.GET)
    public Integer findById(HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	String id = request.getParameter("childId");
    	Child child = _cChildRepository.findOne(Integer.parseInt(id));
    	Image image = child.getImage();
    	if(image==null){
    		return 0;
    	}else{
           return image.getId();
    	}
    	
    } 
    //孩子头像姓名
    @ResponseBody
    @RequestMapping(value = "/findByIdname",method = RequestMethod.GET)
    public String findByIdname(HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	String id = request.getParameter("childId");
    	Child child = _cChildRepository.findOne(Integer.parseInt(id));
      	return child.getName();
      	
    }
    
    //查询孩子的钱币
    @ResponseBody
    @RequestMapping(value = "/findByChildCommdity",method = RequestMethod.GET)
    public BigDecimal findBymoney(HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	String id = request.getParameter("childId");
    	Child child = _cChildRepository.findOne(Integer.parseInt(id));
    	Money balance = child.getMoney();/*
    	String jsonString="{"+"balance" + ":" + "\"" + balance.getBalance() + "\"}";*/
        return balance.getBalance();
    } 
    /**
     * 根据孩子id查找孩子所有的任务
     */
    @RequestMapping(value = "/findTaskByChildId",method = RequestMethod.GET)
    public String  findTaskByChildId(HttpServletRequest request,HttpServletResponse response,Model model){
    	String childId = request.getParameter("childId");
    	//未完成任务
    	List<DefineTask> historyList = _defineTaskTaskRepository.findByChildAndTaskstatus(Integer.parseInt(childId),0);
    	//已完成任务
    	List<DefineTask> finishhistoryList =_defineTaskTaskRepository.findByChildAndTaskstatus(Integer.parseInt(childId), 1);

    			
    	//未完成任务
    	List<DefineTask>  historyTaskList=new ArrayList<DefineTask>();
    	//已完成任务
        List<DefineTask>  finishTaskList=new ArrayList<DefineTask>();
        
        //未完成任务
		StringBuffer integerrate=new StringBuffer();
		//已完成任务
		StringBuffer finishintegerrate=new StringBuffer();
		//未完成任务
		if(historyList.size()>0){
		for(int i=0;i<historyList.size();i++){
			DefineTask historyTask = historyList.get(i);
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
			historyTaskList.add(historyTask);
		}
		}
		//已完成任务
				if(finishhistoryList.size()>0){
					for(int i=0;i<finishhistoryList.size();i++){
						DefineTask historyTask = finishhistoryList.get(i);
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
						finishTaskList.add(historyTask);
					}
					}
		//未完成任务
		model.addAttribute("historyList", historyTaskList);
		//已完成任务
		model.addAttribute("finishhistoryList",finishTaskList);
		//在前台要判断的是否有历史任务
	    model.addAttribute("finishhistorysize", finishhistoryList.size());
		model.addAttribute("flagvalue", childId);
		User user = (User) request.getSession().getAttribute("user");
		List<Child> child =_cChildRepository.findByUser(user);
		model.addAttribute("childs", child);
		
		
		List<Child> childs = _childRepository.findByUser(user);
		model.addAttribute("child", childs);
		return "/project/list";
    }
  
    
    
    
    
    
    
    
    /**
     * 完成任务,获得选中任务的id,改变任务状态
     */
    @ResponseBody
    @RequestMapping(value = "/finishtask",method = RequestMethod.GET)
    public String finishtask(HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	String taskId = request.getParameter("taskId");
    	String timesadd = request.getParameter("amp;timesadd");
    	String childId = request.getParameter("amp;childid");
    	String taskTimes = request.getParameter("amp;taskTimes");
    	DefineTask historyTask = _defineTaskTaskRepository.findOne(Integer.parseInt(taskId));
    	String status="1";
    	historyTask.setTaskstatus(1);
    	historyTask.setTimesAdd(Integer.parseInt(timesadd));
    	_defineTaskTaskRepository.save(historyTask);
    	Child child=_cChildRepository.findOne(Integer.parseInt(childId));
    	if(Integer.valueOf(timesadd)==Integer.parseInt(taskTimes)){
    		Money showmoney=_moneyRepository.findByChildIds(Integer.parseInt(childId));
        	Money money=new Money();
        /*	Integer timesAdd=_defineTaskTaskRepository.findBymoneyTaskTimes(Integer.parseInt(taskId));
        	Integer taskTimess=_defineTaskTaskRepository.findBymoneyTaskTimeAdds(Integer.parseInt(taskId));*/
        	if(showmoney!=null){
        		Money moneys = _moneyRepository.findByChildId(child);/*
        		System.out.println(_defineTaskTaskRepository.findByTokennumbers(Integer.parseInt(childId)));*/
        		/*
        		System.out.println(timesAdd);*/
            	System.out.println(_defineTaskTaskRepository.findByTokennumbersmoney(Integer.parseInt(taskId)));
        		moneys.setBalance(moneys.getBalance().add(new BigDecimal( _defineTaskTaskRepository.findByTokennumbersmoney(Integer.parseInt(taskId)))));
        		//moneys.setBalance(_defineTaskTaskRepository.findByTokennumbersmoney(Integer.parseInt(childId), taskTimes);
            	_moneyRepository.save(moneys);
        	}else{
        		Money moneys = _moneyRepository.findByChildId(child);/*
        		System.out.println(_defineTaskTaskRepository.findByTokennumbers(Integer.parseInt(childId)));*/
        		/*
        		System.out.println(timesAdd);*/
            	System.out.println(_defineTaskTaskRepository.findByTokennumbersmoney(Integer.parseInt(taskId)));
        		moneys.setBalance(moneys.getBalance().add(new BigDecimal( _defineTaskTaskRepository.findByTokennumbersmoney(Integer.parseInt(taskId)))));
        		//moneys.setBalance(_defineTaskTaskRepository.findByTokennumbersmoney(Integer.parseInt(childId), taskTimes);
            	_moneyRepository.save(moneys);
        	}
    	}
        return  status;
    } 
    /**
     * 删除任务,获得选中任务的id,改变任务状态
     */
    @ResponseBody
    @RequestMapping(value = "/deletefinishtaskById",method = RequestMethod.GET)
    public String deletefinishtaskById(HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	String taskId = request.getParameter("taskId");
    	_defineTaskTaskRepository.delete(Integer.parseInt(taskId));
    	String status="1";
        return status;
    } 
    
    
    //动态保存完成的任务次数
    
    @ResponseBody
    @RequestMapping(value = "/dynamicsave",method = RequestMethod.GET)
    public String dynamicsave(HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	String taskId = request.getParameter("historyId");
    	String timesadd=request.getParameter("amp;timesadd");
    	DefineTask defineTask = _defineTaskTaskRepository.findOne(Integer.parseInt(taskId));
    	defineTask.setTimesAdd(Integer.parseInt(timesadd));
    	_defineTaskTaskRepository.save(defineTask);
        return "";
    } 
    
    
    
    
    
    
//查询宝贝列表信息
    @ResponseBody
    @RequestMapping(value = "/findBychildId",method = RequestMethod.GET)
    public Child findBychildId(HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	String id = request.getParameter("childId");
    	Child child = _cChildRepository.findOne(Integer.parseInt(id));
    	String name = child.getName();
       Date birth = child.getBirth();
       Child childs =_cChildRepository.findImageIdById(Integer.parseInt(id));
       if(childs.getImage()!=null){
//    	   Image image = child.getImage();
//           SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
//           String childBirth = format.format(child.getBirth());
//           String jsonString="{"+"name" + ":" + "\"" + child.getName() + "\""+","+"imageId" + ":" + "\"" + image.getId() + "\""+","+"birth" + ":" + "\"" +childBirth + "\""+","+"gender" + ":" + "\"" + child.getGender() + "\""+","+"id" + ":" + "\"" + child.getId() + "\""+"}";
           return childs;
       }else{
      //  SimpleDateFormat  format=new SimpleDateFormat("yyyy-MM-dd");
        //   String childBirth = format.format(child.getBirth());
       //    String jsonString="{"+"name" + ":" + "\"" + child.getName() + "\""+","+"imageId" + ":" + "\"" + null + "\""+","+"birth" + ":" + "\"" +childBirth + "\""+","+"gender" + ":" + "\"" + child.getGender() + "\""+","+"id" + ":" + "\"" + child.getId() + "\""+"}";
          childs.setName(null);
    	   return childs;
       }
      
      
    } 
    //根据孩子查询所有购物信息
    @RequestMapping(value = "/findPlanByChildId",method = RequestMethod.GET)
	public String index(HttpServletRequest request,Model model)
	{
    	String childId = request.getParameter("childId");
		//拿到存在session中的登录用户
		User user = (User) request.getSession().getAttribute("user");
		//通过user拿到所属的儿童集合
		List<Child> child = _childRepository.findByUser(user);

		List<BuyPlan> buyPlans = _buyPlanRepository.findAll();
		//拿到所有的商品集合table_basic
		String childs=request.getParameter("ids");
		List<Commodity> commodities = _commodityRepository.findall();
		List<CommodityType> commoditietype = _commodityTypeRepository.findAll();
		List<BuyPlan>  buyPlan=new ArrayList<BuyPlan>();
		String flag=request.getParameter("flag");
		if(child.size()>0){
		
			List<BuyPlan> buyPlanss = _buyPlanRepository.findByChildBuyPlanId(Integer.parseInt(childId));	
			model.addAttribute("buyPlanss", buyPlanss);
		}

		model.addAttribute("child",child);
		model.addAttribute("commodities",commodities);
		model.addAttribute("commoditietype",commoditietype);
		model.addAttribute("childs",childs);
		model.addAttribute("buyPlans", buyPlans);
		model.addAttribute("flagvalue", childId);
		
		//支出页面历史购物(zy)
		List<ShopHistory> shopHistories = _shopHistoryRepository.findByChildIdss(Integer.parseInt(childId));
		model.addAttribute("shopHistories",shopHistories);
		model.addAttribute("shoplistnum", shopHistories.size());
		return "/commodity/table_basic";
	} 
//根据商品id获得图片
    @ResponseBody
    @RequestMapping(value = "/findByCommdityImage",method = RequestMethod.GET)
    public Integer findByCommdityImage(HttpServletRequest request,HttpServletResponse response,Model model)
    {
    	String id = request.getParameter("commodityId");
    	CommodityType   commoditytype = _commodityTypeRepository.findOne(Integer.parseInt(id));
    	
        return commoditytype.getId();
    } 
}
