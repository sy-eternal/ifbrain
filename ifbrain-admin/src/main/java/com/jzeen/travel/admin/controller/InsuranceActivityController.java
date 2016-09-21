package com.jzeen.travel.admin.controller;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.jzeen.travel.data.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.InsuranceActivityRepository;
import com.jzeen.travel.data.repository.InsurancePlanRepository;
import com.jzeen.travel.data.repository.InsuranceRateRepository;
import com.jzeen.travel.data.repository.OrderRepository;
import com.jzeen.travel.data.repository.SupplierRepository;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
// 保险规划
@Controller
@RequestMapping("/insuranceActivity")
public class InsuranceActivityController {
	@Autowired
	private InsurancePlanRepository _insurancePlanRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String index()
	{
		return "/insuranceactivity/index";
	}

	@Autowired
	private CodeRepository _codeRepository;

	@Autowired
	private OrderRepository _orderRepository;

	@Autowired
	private InsuranceActivityRepository _insuranceActivityRepository;

	@Autowired
	private InsuranceRateRepository _insuranceRateRepository;

	@Autowired 
	private SupplierRepository _supplierRepository;

	/**
	 * 保险活动列表显示
	 */
	@ResponseBody
	@RequestMapping(value="/searchs",method=RequestMethod.GET)
	public List<InsuranceActivity> search()
	{
		List<InsuranceActivity> data=_insuranceActivityRepository.findAll();
		return data;
	}


	/**
	 * 跳转到新增页面
	 */
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String createInit(@ModelAttribute InsuranceActivity insuranceActivity,Model model){
	    //查询代码表供应商为保险的对象（供应商和4写成固定参数了）
	    Code code=_codeRepository.findByTypeAndValue("供应商",4);
	    
	    //根据代码表ID，查询供应商信息
		List<Supplier> supplier=_supplierRepository.findByCodeId(code.getId());
		System.out.println(supplier);
		model.addAttribute("supplier", supplier);
		return "/insuranceactivity/create";
	}

	/**
	 * 新增保险活动
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public String create(HttpServletRequest request){
		InsuranceActivity insuranceActivity=new InsuranceActivity();
		String supplierCnName = request.getParameter("supplier");
		Supplier supplier=_supplierRepository.findByCnName(supplierCnName);
		String insuranceType=request.getParameter("insuranceType");
		String insuranceName=request.getParameter("insuranceName");
		String insuranceCove=request.getParameter("insuranceCove");
		insuranceActivity.setInsuranceType(insuranceType);
		insuranceActivity.setInsuranceName(insuranceName);
		insuranceActivity.setInsuranceCove(insuranceCove);
		insuranceActivity.setSupplier(supplier);
		insuranceActivity.setUpdateTime(new Date());
		_insuranceActivityRepository.save(insuranceActivity);

		return "redirect:/insuranceActivity";
	}

	/**
	 * 显示修改页面（根据ID查询当前信息）
	 */
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String updateInit(Model model,@PathVariable Integer id){
		InsuranceActivity insuranceActivity=_insuranceActivityRepository.findOne(id);
		model.addAttribute("insuranceActivity", insuranceActivity);
//		List<Supplier> supplier=_supplierRepository.findAll();
		
		//查询代码表供应商为保险的对象(供应商和4为固定参数了)
        Code code=_codeRepository.findByTypeAndValue("供应商",4);
        
        //根据代码表ID，查询供应商信息
        List<Supplier> supplier=_supplierRepository.findByCodeId(code.getId());
		model.addAttribute("supplierid", supplier);
		return "/insuranceactivity/update";
	}

	/**
	 * 保险活动修改
	 */
	@RequestMapping(value="/update",method=RequestMethod.PUT)
	public String update(@Valid InsuranceActivity insuranceActivity,BindingResult bindingResult){
		//判断是否错误，如果错误，返回到update页面，如果正确继续执行
		if (bindingResult.hasErrors())
		{
			return "/activity/update";
		}
//		insuranceActivity.setCreateTime(new Date());
		
		insuranceActivity.setUpdateTime(new Date());
		_insuranceActivityRepository.save(insuranceActivity);
		return "redirect:/insuranceActivity";
	}
	
	/**
	 * 保险活动详情
	 */
	@RequestMapping(value="/detail/{id}",method=RequestMethod.GET)
	public String detailInit(@PathVariable int id, Model model){
	    InsuranceActivity insuranceActivity=_insuranceActivityRepository.findOne(id);
        model.addAttribute("insuranceActivity", insuranceActivity);
        
        //查询代码表供应商为保险的对象(供应商和4为固定参数了)
        Code code=_codeRepository.findByTypeAndValue("供应商",4);
        
        //根据代码表ID，查询供应商信息
        List<Supplier> supplier=_supplierRepository.findByCodeId(code.getId());
        model.addAttribute("supplierid", supplier);
	    
	    return "/insuranceactivity/detail";
	}

	/**
	 * 根据保险ID查询保险内容，传到保费页面
	 */
	@RequestMapping(value="/insuranceRate/{id}",method=RequestMethod.GET)
	public String insuranceRateInit(@PathVariable int id, Model model){
		model.addAttribute("insuranceActivity",_insuranceActivityRepository.findOne(id));
		return "/insurancerate/index";
	}


}
