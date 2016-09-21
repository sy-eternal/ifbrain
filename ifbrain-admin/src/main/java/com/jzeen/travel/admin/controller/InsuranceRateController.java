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
import org.springframework.web.servlet.ModelAndView;

import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.InsuranceActivity;
import com.jzeen.travel.data.entity.InsuranceRate;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.SupplierPriceRule;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.InsuranceActivityRepository;
import com.jzeen.travel.data.repository.InsuranceRateRepository;
import com.jzeen.travel.data.repository.SupplierPriceRuleRepository;
import com.jzeen.travel.data.repository.SupplierRepository;

@Controller
@RequestMapping("/insuranceRate")
public class InsuranceRateController
{
    @Autowired
    InsuranceRateRepository _insuranceRateRepository;
    
    @Autowired
    InsuranceActivityRepository _insuranceActivityRepository;
    
    @Autowired
    CodeRepository _codeRepository;
    
    @Autowired 
    private SupplierRepository _supplierRepository;
    
    @Autowired
    SupplierPriceRuleRepository _supplierPriceRuleRepository;
    
    @RequestMapping(method=RequestMethod.GET)
    public String index()
    {
        return "/insurancerate/index";
    }
    
    /**
     * 保费管理列表显示（根据保险活动ID显示）
     */
    @ResponseBody
    @RequestMapping(value="/search",method=RequestMethod.GET)
    public List<InsuranceRate> search(HttpServletRequest request, Model model)
    {
        
        
        
        Integer insuranceActivityId=Integer.parseInt(request.getParameter("insuranceActivityId"));
        
        //查询代码表供应商为保险的对象（供应商和4写成固定参数了）
        Code code=_codeRepository.findByTypeAndValue("供应商",4);
        //查询保险活动的供应商ID
        InsuranceActivity insuranceActivity= _insuranceActivityRepository.findOne(insuranceActivityId);
        //查询相应的中间表的数据（价格系数）
        SupplierPriceRule supplierPriceRule = _supplierPriceRuleRepository.findBySupplierAndSupplierTypeCode(insuranceActivity.getSupplier().getId(), code.getId());
        
        //查找系数
        BigDecimal price= supplierPriceRule.getPriceCoefficient();
        
        List<InsuranceRate> data= _insuranceRateRepository.findByInsuranceActivity(insuranceActivity);
        for(int i=0;i<data.size();i++){
            data.get(i).setInsuranceRatePrice(data.get(i).getInsuranceRateCost().multiply(price));
            _insuranceRateRepository.save(data.get(i));
        }
        
        return data;
    }
    
    /**
     * 跳转到保费新增页面
     */
    @RequestMapping(value="/create",method=RequestMethod.GET)
    public String createInit(@ModelAttribute InsuranceRate insuranceRate,Model model,Integer insuranceActivityId){
        model.addAttribute("insuranceActivity", _insuranceActivityRepository.findOne(insuranceActivityId));
        return "/insurancerate/create";
    }
    
    /**
     * 保费新增
     */
    @RequestMapping(value="/create",method=RequestMethod.POST)
    public ModelAndView create(@Valid InsuranceRate insuranceRate, BindingResult bindingResult,HttpServletRequest request,Model model){
     
        //判断是否错误，如果错误，返回
        if (bindingResult.hasErrors())
        {
            List<InsuranceActivity> insuranceActivitys=_insuranceActivityRepository.findAll();
            model.addAttribute("insuranceActivity", insuranceActivitys);
            return new ModelAndView("/insuranceRate/create","formErrors",bindingResult.getAllErrors());
        }
        
        Integer insuranceActivityId=Integer.parseInt(request.getParameter("insuranceActivityId"));
//        System.out.println(insuranceActivityId);
        insuranceRate.setInsuranceActivity(_insuranceActivityRepository.findOne(insuranceActivityId));
        insuranceRate.setUpdateTime(new Date());
        _insuranceRateRepository.save(insuranceRate);
        return new ModelAndView("redirect:/insuranceActivity/insuranceRate/"+insuranceActivityId);
        

    }
    
    /**
     * 跳转到保费修改页面
     */
    @RequestMapping(value="/update/{id}",method=RequestMethod.GET)
    public String updateInit(@PathVariable Integer id,Model model){
        InsuranceRate insuranceRate=_insuranceRateRepository.findOne(id);
        model.addAttribute("insuranceRate",insuranceRate);
        model.addAttribute("insuranceActivity", _insuranceActivityRepository.findOne(insuranceRate.getInsuranceActivity().getId()));
        return "/insurancerate/update";
    }
    
    /**
     * 保费修改
     */
    @RequestMapping(value="/update",method=RequestMethod.PUT)
    public String update(@Valid InsuranceRate insuranceRate,BindingResult bindingResult,HttpServletRequest request){
//        System.out.println(insuranceRate.getId());
        
        //判断是否错误，如果错误，返回到update页面，如果正确继续执行
        if (bindingResult.hasErrors())
        {
            return "/insuranceRate/update/"+insuranceRate.getId();
        }
        Integer insuranceActivityId=Integer.parseInt(request.getParameter("insuranceActivityId"));
        insuranceRate.setInsuranceActivity(_insuranceActivityRepository.findOne(insuranceActivityId));
        insuranceRate.setUpdateTime(new Date());
        _insuranceRateRepository.save(insuranceRate);
//        return "redirect:/insuranceRate";
        return "redirect:/insuranceActivity/insuranceRate/"+insuranceActivityId;
    }
    
    /**
     * 根据成本价，自动改变销售价格（成本价*供应商价格系数）
     */
    @ResponseBody
    @RequestMapping(value="/price",method=RequestMethod.GET)
    public BigDecimal price(BigDecimal insuranceRateCost,int insuranceActivityId){
        //查询代码表供应商为保险的对象（供应商和4写成固定参数了）
        Code code=_codeRepository.findByTypeAndValue("供应商",4);
        //查询保险活动的供应商ID
        InsuranceActivity insuranceActivity= _insuranceActivityRepository.findOne(insuranceActivityId);
        //查询相应的中间表的数据（价格系数）
        SupplierPriceRule supplierPriceRule = _supplierPriceRuleRepository.findBySupplierAndSupplierTypeCode(insuranceActivity.getSupplier().getId(), code.getId());
        //返回价格系数
        return supplierPriceRule.getPriceCoefficient();
    }
}
