package com.jzeen.travel.admin.controller;

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

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.DemoOrder;
import com.jzeen.travel.data.entity.DemoUser;
import com.jzeen.travel.data.repository.DemoOrderRepository;
import com.jzeen.travel.data.repository.DemoUserRepository;

@Controller
@RequestMapping("/demoOrder")
public class DemoOrderController
{
    @Autowired
    DemoOrderRepository _demoOrderRepository;

    @Autowired
    DemoUserRepository _demoUserRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/demoOrder/index";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<DemoOrder, Integer> search(HttpServletRequest request)
    {
        DataTable<DemoOrder, Integer> dataTable = DataTable.fromRequest(request, _demoOrderRepository);

        return dataTable;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute DemoOrder demoOrder, Model model)
    {
        List<DemoUser> demoUsers = _demoUserRepository.findAll();
        model.addAttribute("demoUsers", demoUsers);
        
        return "/demoOrder/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid DemoOrder demoOrder, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            List<DemoUser> demoUsers = _demoUserRepository.findAll();
            model.addAttribute("demoUsers", demoUsers);
            
            return new ModelAndView("/demoOrder/create", "formErrors", bindingResult.getAllErrors());
        }

        demoOrder.setCreateTime(new Date());
        demoOrder = _demoOrderRepository.save(demoOrder);

        return new ModelAndView("redirect:/demoOrder");
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        DemoOrder demoOrder = _demoOrderRepository.findOne(id);
        model.addAttribute("demoOrder", demoOrder);

        List<DemoUser> demoUsers = _demoUserRepository.findAll();
        model.addAttribute("demoUsers", demoUsers);
        
        return "/demoOrder/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT /* 局部替换而不是整体更新 */)
    public String update(@Valid DemoOrder demoOrder, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            List<DemoUser> demoUsers = _demoUserRepository.findAll();
            model.addAttribute("demoUsers", demoUsers);
            
            return "/demoOrder/update";
        }

        DemoOrder oldOrder = _demoOrderRepository.findOne(demoOrder.getId());
        Date createTime = oldOrder.getCreateTime();
        demoOrder.setCreateTime(createTime);
        
        _demoOrderRepository.save(demoOrder);

        return "redirect:/demoOrder";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _demoOrderRepository.delete(id);
    }
}
