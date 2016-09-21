package com.jzeen.travel.admin.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.GuideActivity;
import com.jzeen.travel.data.entity.GuideRate;
import com.jzeen.travel.data.entity.HotelActivity;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.SupplierPriceRule;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.GuideActivityRepository;
import com.jzeen.travel.data.repository.GuideRateRepository;
import com.jzeen.travel.data.repository.SupplierPriceRuleRepository;
import com.jzeen.travel.data.repository.SupplierRepository;
@Controller
@RequestMapping("/guideactivity")
public class GuideActivityController {

	/**
	 * 导游活动新增关联实体
	 */
	@Autowired
	private GuideActivityRepository _guideActivityRepository;

	@Autowired
	private CityRepository _cityRepository;

	@Autowired
	private SupplierRepository _supplierRepository;

	@Autowired
	private CodeRepository _codeRepository;
	@Autowired
	private GuideRateRepository _guidereateRepository;
	@Autowired
	private SupplierPriceRuleRepository _suppliserpricerule;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index()
	{
		return "/guideactivity/list";
	}
	
	/**
	 * 导游活动新增控制
	 * 
	 * @param guideactivity
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createInit(@ModelAttribute GuideActivity guideactivity,
			Model model) {
		List<City> cityid = _cityRepository.findAll();
		model.addAttribute("cityid", cityid);
		int status = 1;
		List<Supplier> supplierid = _supplierRepository
				.findBySupplierStatus(status);
		model.addAttribute("supplierid", supplierid);
		model.addAttribute("guideactivity", guideactivity);

		return "/guideactivity/create";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid GuideActivity guideactivity,
			BindingResult bindingResult, Model model, HttpServletRequest request) {

		if (bindingResult.hasErrors()) {
			/**
			 * 激活供应商
			 */
			int status = 1;
			List<Supplier> supplierid = _supplierRepository
					.findBySupplierStatus(status);
			model.addAttribute("supplier", supplierid);

			List<City> cityid = _cityRepository.findAll();
			model.addAttribute("city", cityid);
			model.addAttribute("guideactivity", guideactivity);

			return "redirect:/guideactivity";
		}
		guideactivity.setUpdate_time(new Date());
		
		_guideActivityRepository.save(guideactivity);
		return "redirect:/guideactivity";
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String updateInit(Model model,@PathVariable Integer id){
		
		GuideActivity guideactivity=_guideActivityRepository.findOne(id);//id
		model.addAttribute("guideactivity", guideactivity);
		List<City> city = _cityRepository.findAll();
		 model.addAttribute("cityid",city);
		//List<Supplier> supplier=_supplierRepository.findAll();
		
		//查询代码表供应商为保险的对象(供应商和4为固定参数了)
        Code code=_codeRepository.findByTypeAndValue("供应商",7);
        //根据代码表ID，查询供应商信息
        List<Supplier> supplier=_supplierRepository.findByCodeId(code.getId());
       
		model.addAttribute("supplierid", supplier);
		 System.out.println(code);
		return "/guideactivity/update";
	}
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public String update(@Valid GuideActivity guideactivity,BindingResult bindingResult){
		
		//判断是否错误，如果错误，返回到update页面，如果正确继续执行
		if (bindingResult.hasErrors())
		{
			return "/activity/update";
		}
//		insuranceActivity.setCreateTime(new Date());
		
		guideactivity.setUpdate_time(new Date());
		_guideActivityRepository.save(guideactivity);//
		
		return "redirect:/guideactivity";
	}
	@RequestMapping(value="/tguiderate/{id}",method=RequestMethod.GET)
	public String tguiderate(Model model,@PathVariable Integer id){
		GuideActivity guideactivity = _guideActivityRepository.findOne(id);
		model.addAttribute("guideactivity", guideactivity);
		return "/guideactivity/ratelist";
	}
	/*导费列表
	 * */
	@ResponseBody
	@RequestMapping(value="/searchs",method=RequestMethod.GET)
	public List<GuideRate> searchs(HttpServletRequest request){
		Integer id=Integer.parseInt(request.getParameter("guideactivityid"));
		GuideActivity guideactivity = _guideActivityRepository.findOne(id);
		List<GuideRate> data = _guidereateRepository.findByGuideActivity(guideactivity);//费率表的集合
		
		return data;
	}
	@RequestMapping(value="/updatetguiderate/{id}",method=RequestMethod.GET)
	public String updatetguiderate(Model model,@PathVariable Integer id){
		GuideRate guiderate = _guidereateRepository.findOne(id);
		model.addAttribute("guiderate",guiderate);
		return "/guideactivity/rateupdate";
	}
	/*
	 * 计算
	 * */
	 	@ResponseBody
	    @RequestMapping(value="/price",method=RequestMethod.GET)
	    public BigDecimal price(BigDecimal guideRateCost,int guideRateCostId){
		 
//		 GuideRate guidereate =   _guidereateRepository.findOne(guideRateCostId);
		 
//		 GuideActivity guideactivity = guidereate.getGuideActivity();
		 GuideActivity guideActivity=_guideActivityRepository.findOne(guideRateCostId);
		 System.out.println(guideActivity);
			int supplierid = guideActivity.getSupplier().getId();
			// 拿到价格系数对象
				SupplierPriceRule supplierpricerule = _suppliserpricerule.findBySupplierAndSupplierTypeCode(supplierid, 21);//写死的21代表导游的
			 	
	        return supplierpricerule.getPriceCoefficient();
	    }
	 	//计算
	 	@ResponseBody
	    @RequestMapping(value="/pricea",method=RequestMethod.GET)
	    public BigDecimal pricea(BigDecimal guideRateCost,int guideRateCostId){
		 
	 		GuideActivity guideactivity =   _guideActivityRepository.findOne(guideRateCostId);
		 
		 
		 System.out.println(guideactivity);
			int supplierid = guideactivity.getSupplier().getId();
			// 拿到价格系数对象
				SupplierPriceRule supplierpricerule = _suppliserpricerule.findBySupplierAndSupplierTypeCode(supplierid, 21);//写死的21代表导游的
			 	
	        return supplierpricerule.getPriceCoefficient();
	    }
	 
	 @RequestMapping(value="/updateguiderate",method=RequestMethod.PUT)
		public String updateguiderate(@Valid GuideRate guidereate,BindingResult bindingResult){
			
			//判断是否错误，如果错误，返回到update页面，如果正确继续执行
			if (bindingResult.hasErrors())
			{
				return "/guideactivity/rateupdate";
			}
			guidereate.setUpdateTime(new Date());
			System.out.println(guidereate.getId());
			
			_guidereateRepository.save(guidereate);//		
			return "redirect:/guideactivity/ratelist/"+guidereate.getGuideActivity().getId();
		}
	 
	 
	 
	 //新增费率跳转
	 	@RequestMapping(value="/createguiderate",method=RequestMethod.GET)
		public String createguiderate(@ModelAttribute GuideRate guiderate,Model model,HttpServletRequest request) {
	 		Integer id=Integer.parseInt(request.getParameter("guideactivity"));
	 		GuideActivity guideactivity = _guideActivityRepository.findOne(id);
			model.addAttribute("guideactivity",guideactivity);
			model.addAttribute("guiderate", guiderate);
			return "/guideactivity/ratecreate";
		}
	 	
	 	
	 	//新增费率
	 	@RequestMapping(value="/createguideratea",method=RequestMethod.POST)
		public String createguideratea(@ModelAttribute GuideRate guiderate,BindingResult bindingResult,HttpServletRequest request,
				Model model) {
	 		Integer id=Integer.parseInt(request.getParameter("guideactivity"));
	 		GuideActivity guideactivity = _guideActivityRepository.findOne(id);
	 		model.addAttribute("guideactivity",guideactivity);
	 		guiderate.setGuideActivity(guideactivity);
	 		guiderate.setUpdateTime(new Date());
	 		_guidereateRepository.save(guiderate);
			return "redirect:/guideactivity/ratelist/"+id;
		}
	 
	 
	 
	 
	
	/**
	 * 导游活动列表显示
	 */
	@ResponseBody
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public List<GuideActivity> search()
	{
		
		List<GuideActivity> data=_guideActivityRepository.findAll();
		return data;
	}	
	
	
	

}
