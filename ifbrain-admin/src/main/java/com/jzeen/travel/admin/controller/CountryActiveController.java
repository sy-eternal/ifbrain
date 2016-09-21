package com.jzeen.travel.admin.controller;

import java.util.Date;

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

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.repository.CountryRepository;

@Controller
@RequestMapping("/countryActive")
public class CountryActiveController
{
    @Autowired
    private CountryRepository _countryRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String list()
    {
        return "/country/list";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<Country, Integer> search(HttpServletRequest request)
    {
        DataTable<Country, Integer> dataTable = DataTable.fromRequest(request, _countryRepository);
        return dataTable;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute Country name, Model model)
    {
        model.addAttribute("name", name);
        return "/country/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid Country name, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "/country/create";
        }
        name.setCreateTime(new Date());
        _countryRepository.save(name);
        return "redirect:/countryActive";

    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _countryRepository.delete(id);
    }
//判断国家重复添加
    @ResponseBody
    @RequestMapping(value = "/valueistrue", method = RequestMethod.POST)
    public Boolean valueistrue(String country)
    {
        Country countrys = _countryRepository.findByName(country);
        if (countrys == null)
        {
            System.out.println("true");
            return true;
        }
        else
        {
            System.out.println("false");
            return false;
        }
    }
}
