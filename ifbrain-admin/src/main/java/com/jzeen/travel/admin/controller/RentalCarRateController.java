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
import com.jzeen.travel.data.entity.RentalCar;
import com.jzeen.travel.data.entity.RentalRate;
import com.jzeen.travel.data.entity.SupplierPriceRule;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.RentalRateRepository;
import com.jzeen.travel.data.repository.RentalRepository;
import com.jzeen.travel.data.repository.SupplierPriceRuleRepository;

@Controller
@RequestMapping("/specialCarRate")
public class RentalCarRateController
{

    @Autowired
    private RentalRepository _rentalRepository;

    @Autowired
    private RentalRateRepository _rentalRateRepository;

    @Autowired
    private CodeRepository _codeRepository;

    @Autowired
    private SupplierPriceRuleRepository _supplierPriceRuleRepository;

    /**
     * 租车费率列表显示（根据保险活动ID显示）
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<RentalRate> search(HttpServletRequest request, Model model)
    {
        Integer rentalCarId = Integer.parseInt(request.getParameter("rentalCarId"));
        RentalCar rentalCar = _rentalRepository.findOne(rentalCarId);
        
        // 查询代码表供应商为保险的对象（供应商和4写成固定参数了）
        Code code = _codeRepository.findByTypeAndValue("供应商", 5);
        // 查询相应的中间表的数据（价格系数）
        SupplierPriceRule supplierPriceRule = _supplierPriceRuleRepository.findBySupplierAndSupplierTypeCode(rentalCar.getSupplier().getId(), code.getId());
       
        //查找系数
        BigDecimal price= supplierPriceRule.getPriceCoefficient();
        
        List<RentalRate> data = _rentalRateRepository.findBySpecialcar(rentalCar);
        for(int i=0;i<data.size();i++){
            data.get(i).setCarrateprice(data.get(i).getCarratecost().multiply(price));
            _rentalRateRepository.save(data.get(i));
        }
        
        return data;
    }

    /**
     * 跳转到租车费率新增页面
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute RentalRate rentalRate, Model model, Integer rentalCarId)
    {
        model.addAttribute("rentalCar", _rentalRepository.findOne(rentalCarId));

        return "/rentalcarrate/create";
    }

    /**
     * 租车费率新增
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid RentalRate specialCarRate, BindingResult bindingResult, HttpServletRequest request,
            Model model)
    {
        // 判断是否错误，如果错误，返回
        if (bindingResult.hasErrors())
        {
            List<RentalCar> specialCar = _rentalRepository.findAll();
            model.addAttribute("specialCar", specialCar);
            return "/specialcarrate/create";
        }
        Integer rentalCarId = Integer.parseInt(request.getParameter("rentalCarId"));
        RentalCar specialCar = _rentalRepository.findOne(rentalCarId);
        specialCarRate.setSpecialcar(specialCar);
        specialCarRate.setUpdateTime(new Date());
        _rentalRateRepository.save(specialCarRate);
        return "redirect:/specialCar/specialCarRate/" + rentalCarId;
    }

    /**
     * 跳转到租车费率修改页面
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable Integer id,Model model)
    {

        RentalRate specialCarRate = _rentalRateRepository.findOne(id);
        model.addAttribute("rentalCar", _rentalRepository.findOne(specialCarRate.getSpecialcar().getId()));
        model.addAttribute("specialCarRate", specialCarRate);
        return "/rentalcarrate/update";
    }

    /**
     * 租车费率修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@Valid RentalRate rentalRate, BindingResult bindingResult, HttpServletRequest request)
    {
        // 判断是否错误，如果错误，返回到update页面，如果正确继续执行
        if (bindingResult.hasErrors())
        {
            return "/rentalcarrate/update" + rentalRate.getId();
        }

        Integer rentalCarId = Integer.parseInt(request.getParameter("rentalCarId"));
        rentalRate.setSpecialcar(_rentalRepository.findOne(rentalCarId));
        rentalRate.setUpdateTime(new Date());
        _rentalRateRepository.save(rentalRate);
        return "redirect:/specialCar/specialCarRate/" + rentalCarId;
    }

    /**
     * 根据成本价，自动改变销售价格（成本价*供应商价格系数）
     */
    @ResponseBody
    @RequestMapping(value = "/price", method = RequestMethod.GET)
    public BigDecimal price(BigDecimal carratecost, int rentalCarId)
    {
        
        System.out.println(carratecost+"\t"+rentalCarId);
        // 查询代码表供应商为保险的对象（供应商和4写成固定参数了）
        Code code = _codeRepository.findByTypeAndValue("供应商", 5);
        // 查询保险活动的供应商ID
        RentalCar rentalCar = _rentalRepository.findOne(rentalCarId);
        // 查询相应的中间表的数据（价格系数）
        SupplierPriceRule supplierPriceRule = _supplierPriceRuleRepository.findBySupplierAndSupplierTypeCode(rentalCar.getSupplier().getId(), code.getId());
        // 返回价格系数
        return supplierPriceRule.getPriceCoefficient();
    }
}
