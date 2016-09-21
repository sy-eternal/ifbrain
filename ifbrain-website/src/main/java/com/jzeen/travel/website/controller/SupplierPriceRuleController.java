package com.jzeen.travel.website.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.SupplierPriceRule;
import com.jzeen.travel.data.repository.SupplierPriceRuleRepository;

@Controller
@RequestMapping("/supplierPriceRule")
public class SupplierPriceRuleController
{
    @Autowired
    private SupplierPriceRuleRepository _supplierPriceRuleRepository;

    @ResponseBody
    @RequestMapping(value = "/getByVehicleTypeCode", method = RequestMethod.GET)
    public List<SupplierPriceRule> getByVehicleTypeCode(HttpServletRequest request) throws ParseException
    {
        Integer vehicleTypeCode = Integer.parseInt(request.getParameter("vehicleTypeCode"));
        List<SupplierPriceRule> supplierPriceRules = _supplierPriceRuleRepository.getByVehicleTypeCode(vehicleTypeCode);
        return supplierPriceRules;
    }
    
    @RequestMapping(value = "/getByVehicleTypeCode", method = RequestMethod.POST)
    public List<SupplierPriceRule> getByVehicleTypeCodeA(HttpServletRequest request) throws ParseException
    {
        Integer vehicleTypeCode = Integer.parseInt(request.getParameter("vehicleTypeCode"));
        List<SupplierPriceRule> supplierPriceRules = _supplierPriceRuleRepository.getByVehicleTypeCode(vehicleTypeCode);
        return supplierPriceRules;
    }
}
