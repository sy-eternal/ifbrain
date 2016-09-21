package com.jzeen.travel.website.controller.Commodity;





import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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



import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.data.entity.BuyPlan;
import com.jzeen.travel.data.entity.Child;
import com.jzeen.travel.data.entity.Commodity;
import com.jzeen.travel.data.entity.CommodityType;
import com.jzeen.travel.data.entity.CustomCommodity;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Money;
import com.jzeen.travel.data.entity.ShopHistory;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.entity.WorkTaskRelate;
import com.jzeen.travel.data.repository.BuyPlanRepository;
import com.jzeen.travel.data.repository.ChildRepository;
import com.jzeen.travel.data.repository.CommodityRepository;
import com.jzeen.travel.data.repository.CommodityTypeRepository;
import com.jzeen.travel.data.repository.CustomCommodityRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.MoneyRepository;
import com.jzeen.travel.data.repository.ShopHistoryRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.data.repository.WorkTaskRelateRepository;
import com.jzeen.travel.website.setting.FileUploadSetting;

@Controller
@RequestMapping("/commodity")
public class CommodityController
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


	//我的计划购买(购物第一页)
	@ResponseBody
	@RequestMapping(value = "/searchbuyplan",method = RequestMethod.GET)
	public  Iterable<BuyPlan> searchbabylist(Model model, HttpServletRequest request)
	{
		String childid=request.getParameter("childId");
		Integer childId = Integer.parseInt(childid);
		Child child = _childRepository.findOne(childId);
		List<BuyPlan> childlist =_buyPlanRepository.findByChildIdss(child.getId());
		return childlist;
	}

	/*
	 * 购物页
	 */
	@RequestMapping(value = "/index/{id}",method = RequestMethod.GET)
	public String index(@PathVariable Integer id,HttpServletRequest request,Model model)
	{
		//拿到存在session中的登录用户
		User user = (User) request.getSession().getAttribute("user");
		//通过user拿到所属的儿童集合
		List<Child> child = _childRepository.findByUser(user);

		List<BuyPlan> buyPlans = _buyPlanRepository.findAll();
		//拿到所有的商品集合table_basic
		/*String childs=request.getParameter("ids");*/
		List<Commodity> commodities = _commodityRepository.findall();
		List<CommodityType> commoditietype = _commodityTypeRepository.findAll();
		List<BuyPlan>  buyPlan=new ArrayList<BuyPlan>();
		String flag=request.getParameter("flag");
		if(child.size()>0){
		
			List<BuyPlan> buyPlanss = _buyPlanRepository.findByChildBuyPlanId(id);	
			model.addAttribute("buyPlanss", buyPlanss);
		}

		model.addAttribute("child",child);
		model.addAttribute("commodities",commodities);
		model.addAttribute("commoditietype",commoditietype);
		model.addAttribute("childs",id);
		model.addAttribute("buyPlans", buyPlans);
		model.addAttribute("flagvalue", id);
		
		List<ShopHistory> shopHistories = _shopHistoryRepository.findByChildIdss(child.get(0).getId());
		model.addAttribute("shopHistories",shopHistories);
		model.addAttribute("shoplistnum", shopHistories.size());
		
		return "/commodity/table_basic";
	} 

	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String indexs(HttpServletRequest request,Model model)
	{
		//拿到存在session中的登录用户
		User user = (User) request.getSession().getAttribute("user");
		//通过user拿到所属的儿童集合
		List<Child> child = _childRepository.findByUser(user);
/*
		List<BuyPlan> buyPlans = _buyPlanRepository.findAll();
		//拿到所有的商品集合table_basic
		String childs=request.getParameter("ids");
		List<Commodity> commodities = _commodityRepository.findall();
		List<BuyPlan>  buyPlan=new ArrayList<BuyPlan>();
		String flag=request.getParameter("flag");
		if(child.size()>0){
		
			List<BuyPlan> buyPlanss = _buyPlanRepository.findByChildBuyPlanId(child.get(0).getId());	
			model.addAttribute("buyPlanss", buyPlanss);
		}

		model.addAttribute("child",child);
		model.addAttribute("commodities",commodities);
		model.addAttribute("childs",childs);
		model.addAttribute("buyPlans", buyPlans);
		model.addAttribute("flagvalue", child.get(0).getId());
		
		List<ShopHistory> shopHistories = _shopHistoryRepository.findByChildIdss(child.get(0).getId());
		model.addAttribute("shopHistories",shopHistories);
		model.addAttribute("shoplistnum", shopHistories.size());*/
		
		//List<ShopHistory> shopHistories = _shopHistoryRepository.findByChildIdss(child.get(0).getId());
		//model.addAttribute("shoplistnum","0");
		model.addAttribute("shoplistnum",0);
		return "/commodity/table_basic";
	} 
	/*@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index1(HttpServletRequest request,Model model)
	{
		//拿到存在session中的登录用户
		User user = (User) request.getSession().getAttribute("user");
		//通过user拿到所属的儿童集合
		List<Child> child = _childRepository.findByUser(user);

		List<BuyPlan> buyPlans = _buyPlanRepository.findAll();
		//拿到所有的商品集合table_basic
		String childs=request.getParameter("child");
		List<Commodity> commodities = _commodityRepository.findall();
		model.addAttribute("child",child);
		model.addAttribute("commodities",commodities);
		model.addAttribute("childs",childs);
		model.addAttribute("buyPlans", buyPlans);
		return "/commodity/table_basic";
	} */
	/**根据孩子id获得计划购买的商品
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/findBuyPlanByChildId",method = RequestMethod.GET)
	public String findBuyPlanByChildId(HttpServletRequest request,Model model)
	{
		String childid=request.getParameter("childId");
		Integer childId = Integer.parseInt(childid);
		Child child = _childRepository.findOne(childId);
		List<BuyPlan> buyPlans = _buyPlanRepository.findByChildIdss(childId);
		model.addAttribute("buyPlans",buyPlans);
		model.addAttribute("childId",child.getId());
		return "/commodity/planedcommodity";
	} 



	/**
	 * 根据id查询商品
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findById", method = RequestMethod.GET)
	public Commodity findById(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		Commodity commodity = _commodityRepository.findOne(Integer.parseInt(id));
		return commodity;
	}

	@ResponseBody
	@RequestMapping(value = "/findByIds", method = RequestMethod.GET)
	public BuyPlan findByIds(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		BuyPlan commodity = _buyPlanRepository.findOne(Integer.parseInt(id));
		return commodity;
	}


	/*
	 * 购物实现
	 */
	@RequestMapping(value = "/commodity",method = RequestMethod.GET)
	public String commodity(HttpServletRequest request,Model model)
	{
		String childIds=request.getParameter("amp;childId");
		Integer childId = Integer.parseInt(childIds);
		String commoditys= request.getParameter("commodityId");
		Integer commodityId = Integer.parseInt(commoditys);
		Child child = _childRepository.findOne(childId);
		Commodity commodity = _commodityRepository.findOne(commodityId);
		ShopHistory shopHistory = new ShopHistory();
		shopHistory.setChild(child);
		shopHistory.setUser(child.getUser());
		shopHistory.setCommodityType(commodity.getCommodityType());
		shopHistory.setImage(commodity.getImage());
		shopHistory.setName(commodity.getName());
		shopHistory.setPrice(commodity.getPrice());
		shopHistory.setStartTime(new Date());
		shopHistory.setBuyNumber(1);
		_shopHistoryRepository.save(shopHistory);
		Money money = _moneyRepository.findByChildId(child);
		money.setBalance(money.getBalance().subtract(commodity.getPrice()));

		Integer nochilds=Integer.parseInt(request.getParameter("childs"));
		_moneyRepository.save(money);
		return "redirect:/commodity/index/"+nochilds;
	} 

	/*
	 * 添加购物计划页
	 */
	@RequestMapping(value = "/addcommodity",method = RequestMethod.GET)
	public String addcommodity(HttpServletRequest request,Model model)
	{
		String child=request.getParameter("childId");
		Integer childId = Integer.parseInt(child);
		Child child2 = _childRepository.findOne(childId);
		List<Commodity> commodities = _commodityRepository.findall();
		model.addAttribute("child",child2);
		model.addAttribute("commodities",commodities);
		return "/commodity/addcommodity";
	} 



	/*
	 * 添加购物计划实现
	 */
	/*@RequestMapping(value = "/toaddcommodity",method = RequestMethod.GET)
	public String toaddcommodity(HttpServletRequest request,Model model)
	{
		String childIds=request.getParameter("amp;childId");
		Integer childId = Integer.parseInt(childIds);
		String[] commoditys= request.getParameter("commoditys").split(",");
		Child child = _childRepository.findOne(childId);
		for (int i = 0; i < commoditys.length; i++) {
			Integer id = Integer.parseInt(commoditys[i]);
			Commodity commodity = _commodityRepository.findOne(id);
			BuyPlan buyPlan = new BuyPlan();
			buyPlan.setChild(child);
			buyPlan.setImage(commodity.getImage());
			buyPlan.setName(commodity.getName());
			buyPlan.setPrice(commodity.getPrice());
			buyPlan.setCommodityType(commodity.getCommodityType());
			_buyPlanRepository.save(buyPlan);
		}
		return "redirect:/commodity/index/"+childId;
	} */

	/*
	 * 删除购物计划
	 */
	 @ResponseBody
	@RequestMapping(value = "/deletebuyplan",method = RequestMethod.DELETE)
	public void deletebuyplan(HttpServletRequest request,Model model)
	{
		/*String [] buyplanIds= request.getParameter("commoditys").split(",");
		Integer childids = (Integer) request.getSession().getAttribute("childids");
		for (int i = 0; i < buyplanIds.length; i++) {
			BuyPlan buyPlan = _buyPlanRepository.findOne(new Integer(buyplanIds[i]));*/
		String  buyplanIds= request.getParameter("buyplanIds");
		_buyPlanRepository.delete(Integer.parseInt(buyplanIds));
	/*	}*/
	/*	return "redirect:/commodity/index/"+childids;*/
	}

	/*
	 * 历史购物页
	 */
	@RequestMapping(value = "/shophistory",method = RequestMethod.GET)
	public String shophistory(HttpServletRequest request,Model model)
	{
		String child=request.getParameter("childId");
		Integer childId = Integer.parseInt(child);
		Child child2 = _childRepository.findOne(childId);
		List<Commodity> commodities = _commodityRepository.findall();
		List<ShopHistory> shopHistories = _shopHistoryRepository.findByChildIdss(child2.getId());
		model.addAttribute("child",child2);
		model.addAttribute("commodities",commodities);
		model.addAttribute("shopHistories",shopHistories);
		return "/commodity/shophistory";
	} 


	/*
	 * 删除购物历史
	 */
	 @ResponseBody
		@RequestMapping(value = "/deleteshophistorys",method = RequestMethod.DELETE)
		public void deleteshophistorys(HttpServletRequest request,Model model)
		{
			String  buyplanIds= request.getParameter("historyshop");
			_shopHistoryRepository.delete(Integer.parseInt(buyplanIds));
		}
	/*
	 * 购物计划页
	 */
	@RequestMapping(value = "/buyplan",method = RequestMethod.GET)
	public String buyplan(HttpServletRequest request,Model model)
	{

		String child=request.getParameter("childId");
		Integer childId = Integer.parseInt(child);
		Child child2 = _childRepository.findOne(childId);
		List<BuyPlan> buyPlans = _buyPlanRepository.findByChildIdss(child2.getId());
		List<Commodity> commodities = _commodityRepository.findall();
		model.addAttribute("commodities",commodities);
		model.addAttribute("child",child2);
		model.addAttribute("buyPlans",buyPlans);
		return "/commodity/buyplan";
	} 
	/*
	 * 购物计划购物实现
	 */
	@RequestMapping(value = "/tocommodity",method = RequestMethod.GET)
	public String tocommodity(HttpServletRequest request,Model model)
	{
		String childIds=request.getParameter("amp;childId");
		Integer childId = Integer.parseInt(childIds);
		String commoditys= request.getParameter("commodityId");
		Integer commodityId = Integer.parseInt(commoditys);
		Child child = _childRepository.findOne(childId);
		Commodity commodity = _commodityRepository.findOne(commodityId);
		ShopHistory shopHistory = new ShopHistory();
		shopHistory.setChild(child);
		shopHistory.setUser(child.getUser());
		shopHistory.setCommodityType(commodity.getCommodityType());
		shopHistory.setImage(commodity.getImage());
		shopHistory.setName(commodity.getName());
		shopHistory.setPrice(commodity.getPrice());
		shopHistory.setStartTime(new Date());
		shopHistory.setBuyNumber(1);
		_shopHistoryRepository.save(shopHistory);
		Money money = _moneyRepository.findByChildId(child);
		money.setBalance(money.getBalance().subtract(commodity.getPrice()));
		Integer conchildId = Integer.parseInt(request.getParameter("childs"));
		_moneyRepository.save(money);
		return "redirect:/commodity/index/"+conchildId;
	} 


	/*
	 * 购物计划购物实现
	 */
	@RequestMapping(value = "/tocommoditys",method = RequestMethod.GET)
	public String tocommoditys(HttpServletRequest request,Model model)
	{
		String childIds=request.getParameter("amp;childId");
		Integer childId = Integer.parseInt(childIds);
		//String commoditys= request.getParameter("commodityId");
		//Integer commodityId = Integer.parseInt(commoditys);
		Child child = _childRepository.findOne(childId);
		String buyplanid=request.getParameter("amp;buyplanid");
		BuyPlan buyplan = _buyPlanRepository.findOne(Integer.parseInt(buyplanid));
		buyplan.setPlanStatus(1);
		_buyPlanRepository.save(buyplan);
		CommodityType commodityType = buyplan.getCommodityType();
		ShopHistory shopHistory = new ShopHistory();
		shopHistory.setChild(child);
		shopHistory.setUser(child.getUser());
		shopHistory.setCommodityType(commodityType);
		//shopHistory.setImage(buyplan.getImage());
		shopHistory.setName(buyplan.getName());
		shopHistory.setPrice(buyplan.getPrice());
		shopHistory.setStartTime(new Date());
		shopHistory.setBuyNumber(1);
		Money money = _moneyRepository.findByChildId(child);
		System.out.println(money.getBalance());
		System.out.println(buyplan.getPrice());
		money.setBalance(money.getBalance().subtract(buyplan.getPrice()));
		Integer childids = (Integer) request.getSession().getAttribute("childids");

		_shopHistoryRepository.save(shopHistory);
		_moneyRepository.save(money);
		return "redirect:/commodity/index/"+childids;
	} 


	/*
	 * 自定义商品
	 */
	@RequestMapping(value = "/creates",method = RequestMethod.GET)
	public String creates(HttpServletRequest request,Model model,@ModelAttribute CustomCommodity customCommodity)
	{
		Integer childId = Integer.parseInt(request.getParameter("childId"));
		model.addAttribute("childId",childId);
		List<CommodityType> commodityTypes = _commodityTypeRepository.findAll();
		model.addAttribute("commodityTypes",commodityTypes);
		return "/commodity/commoditycreates";
	}

	/*
	 * 自定义商品
	 */
	@RequestMapping(value = "/createcommodity", method = RequestMethod.POST)
	public String create(@ModelAttribute CustomCommodity customCommodity, BindingResult bindingResult, Model model, HttpServletRequest request,
			@RequestParam("file") MultipartFile file)
	{

		String filePath = _fileUploadSetting.getRootPath();
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

				filePath += File.separator + fileName;
				String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
				Image image = new Image();
				image.setCreateTime(new Date());
				image.setFileName(fileName);
				image.setFilePath(lujing);
				_imageRepository.save(image);
				Integer type = Integer.parseInt(request.getParameter("commodityType"));
				CommodityType commodityType = _commodityTypeRepository.findOne(type);
				customCommodity.setCommodityType(commodityType);
				customCommodity.setName(request.getParameter("name"));
				customCommodity.setPrice( new BigDecimal(request.getParameter("price")));
				customCommodity.setImage(image);
				Integer childId = Integer.parseInt(request.getParameter("childId"));
				Child child = _childRepository.findOne(childId);
				customCommodity.setChild(child);
				customCommodity.setUser(child.getUser());
				_customCommodityRepository.save(customCommodity);

				/*
				 * 添加到计划购买
				 */
				BuyPlan buyPlan = new BuyPlan();
				buyPlan.setChild(child);
				//buyPlan.setImage(customCommodity.getImage());
				buyPlan.setName(customCommodity.getName());
				buyPlan.setPrice(customCommodity.getPrice());
				buyPlan.setCommodityType(customCommodity.getCommodityType());
				_buyPlanRepository.save(buyPlan);


				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
				stream.write(bytes);
				stream.close();
				return "redirect:/commodity/addcommodity?childId="+childId;
			}
			catch (Exception e)
			{
				model.addAttribute("errorMessage", "上传文件失败");
				return "/commodity/commoditycreates";
			}
		}

		else
		{
			model.addAttribute("errorMessage", "请选择上传文件");
			return "/commodity/commoditycreates";
		}

	}
	
	 //根据孩子id获得相应孩子的商品
    @ResponseBody
    @RequestMapping(value = "/findByChildId",method = RequestMethod.GET)
    public String findByChildId(HttpServletRequest request,HttpServletResponse response,Model model) 
    {
    	String id = request.getParameter("childId");
    	List<BuyPlan> buyplan = _buyPlanRepository.findByChildIdss(Integer.parseInt(id));
    	JSONArray  jsonarray=new JSONArray();
    	for(int i=0;i<buyplan.size();i++){
    		JSONObject object=new JSONObject();
    		object.put("name",buyplan.get(i).getName());
    		object.put("price", buyplan.get(i).getPrice().doubleValue());
    		object.put("commodityTypename",buyplan.get(i).getCommodityType().getName());
    		object.put("id", buyplan.get(i).getId());
    		object.put("childId", buyplan.get(i).getChild().getId());
    		//object.put("imageid", buyplan.get(i).getImage().getId());
    		object.put("balance", buyplan.get(i).getChild().getMoney().getBalance().doubleValue());
    		jsonarray.put(object);
    	}
    	JSONObject  object=new JSONObject();
    	object.put("list", jsonarray.toString());
    	return object.toString();
    } 
    
    //添加购物计划
    @RequestMapping(value="/createcommdity",method = RequestMethod.POST)
	public String createcommdity(HttpServletRequest request,Model model) {
	    	//String childIds=request.getParameter("childId");
			//Integer childId = Integer.parseInt(childIds);
			//String commoditys= request.getParameter("amp;commoditys");
			//String price= request.getParameter("amp;price");
			String clickchildid = request.getParameter("clickchildid");
			Integer childId = Integer.parseInt(clickchildid);
			String commoditys = request.getParameter("selectidval");
			
			String price = request.getParameter("zhuanhuan");
			Child child = _childRepository.findOne(childId);
			Integer id = Integer.parseInt(commoditys);
			//Commodity commodity = _commodityRepository.findOne(id);
			CommodityType commodityType = _commodityTypeRepository.findOne(Integer.parseInt(commoditys));
			BuyPlan buyPlan = new BuyPlan();
			buyPlan.setChild(child);
			//buyPlan.setImage(commodity.getImage());
			buyPlan.setName("");
			buyPlan.setPrice(new BigDecimal(price));
			buyPlan.setCommodityType(commodityType);
			buyPlan.setUpdateTime(new Date());
			buyPlan.setPlanStatus(0);
			//commodity.setPrice(new BigDecimal(price));
			_buyPlanRepository.save(buyPlan);
			//_commodityRepository.save(commodity);
			//Integer childids = (Integer) request.getSession().getAttribute("childids");
		return "redirect:/commodity/index/"+childId;
	}
}
