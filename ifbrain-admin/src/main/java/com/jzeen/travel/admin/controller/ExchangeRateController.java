package com.jzeen.travel.admin.controller;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.ExchangeRate;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.repository.ExchangeRateRepository;
import com.jzeen.travel.service.ExchangeRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/exchangerate")
public class ExchangeRateController
{
    @Autowired
    private ExchangeRateRepository _exchangeRateRepository;

    @Autowired
    private ExchangeRateService _exchangeRateService;

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/exchangerate/list";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<ExchangeRate, Integer> search(HttpServletRequest request)
    {
        DataTable<ExchangeRate, Integer> dataTable = DataTable.fromRequest(request, _exchangeRateRepository);
        return dataTable;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute ExchangeRate exchangerate, Model model)
    {
        model.addAttribute("exchangerate", exchangerate);
        return "/exchangerate/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid ExchangeRate exchangerate, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            return "/exchangerate/create";
        }
        exchangerate.setCreateTime(new Date());
        _exchangeRateRepository.save(exchangerate);

        return "redirect:/exchangerate";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _exchangeRateRepository.delete(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getCurrentExchangeRate", method = RequestMethod.GET)
    public BigDecimal getCurrentExchangeRate()
    {
        return _exchangeRateService.getCurrentExchangeRate();
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
    	ExchangeRate exchangerate = _exchangeRateRepository.findOne(id);
        model.addAttribute("exchangerate", exchangerate);


        return "/exchangerate/update";
    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@Valid ExchangeRate exchangerate, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
       
            model.addAttribute("exchangerate", exchangerate);
            return "/supplier/update";
        }
        exchangerate.setCreateTime(new Date());;
        _exchangeRateRepository.save(exchangerate);

        return "redirect:/exchangerate";
    }

}
