package com.jzeen.travel.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.core.util.FileUtil;
import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.DemoUser;
import com.jzeen.travel.data.entity.ExchangeRate;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Margin;
import com.jzeen.travel.data.entity.QCity;
import com.jzeen.travel.data.entity.QDemoUser;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.SupplierPriceRuleActive;
import com.jzeen.travel.data.entity.ThemeActive;
import com.jzeen.travel.data.repository.CityRepository;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.CountryRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.MarginRepository;
import com.jzeen.travel.data.repository.SupplierRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/depositactivity")
public class DepositActivityController
{

    @Autowired
    MarginRepository _mMarginRepository;
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {

        return "/depositactivity/list";
    }
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<Margin, Integer> search(HttpServletRequest request) {
        DataTable<Margin, Integer> dataTable = DataTable.fromRequest(request, _mMarginRepository);
        return dataTable;
    }
    //新建
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute Margin margin, Model model)
    {
        model.addAttribute("margin", margin);
        return "/depositactivity/create";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid Margin margin, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "/depositactivity/create";
        }
        margin.setCreateTime(new Date());
        _mMarginRepository.save(margin);

        return "redirect:/depositactivity";
    }
    

    
    
    //修改
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        Margin margin = _mMarginRepository.findOne(id);
        model.addAttribute("margin", margin);
        return "/depositactivity/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid Margin margin, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "/depositactivity/update";
        }
        Margin oldsmargin = _mMarginRepository.findOne(margin.getId());
        Date createTime = oldsmargin.getCreateTime();
        margin.setCreateTime(createTime);
        _mMarginRepository.save(margin);

        return "redirect:/depositactivity";
    }
}
