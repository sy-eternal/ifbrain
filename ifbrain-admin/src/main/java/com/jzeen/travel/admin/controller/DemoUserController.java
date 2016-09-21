package com.jzeen.travel.admin.controller;

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
import com.jzeen.travel.data.entity.DemoUser;
import com.jzeen.travel.data.entity.QDemoUser;
import com.jzeen.travel.data.repository.DemoUserRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/demoUser")
public class DemoUserController
{
    @Autowired
    DemoUserRepository _demoUserRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/demoUser/index";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<DemoUser, Integer> search(HttpServletRequest request)
    {
        // 使用QueryDsl构造查询条件
        String keyword = request.getParameter("search[value]");
        QDemoUser demoUser = QDemoUser.demoUser;
        Predicate predicate = demoUser.number.containsIgnoreCase(keyword).and(demoUser.visitCount.lt(3));

        DataTable<DemoUser, Integer> dataTable = DataTable.fromRequest(request, _demoUserRepository, predicate);

        return dataTable;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute DemoUser demoUser)
    {
        return "/demoUser/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid DemoUser demoUser, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "/demoUser/create";
        }

        _demoUserRepository.save(demoUser);

        return "redirect:/demoUser";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        DemoUser demoUser = _demoUserRepository.findOne(id);
        model.addAttribute("demoUser", demoUser);

        return "/demoUser/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@Valid DemoUser demoUser, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "/demoUser/update";
        }

        _demoUserRepository.save(demoUser);

        return "redirect:/demoUser";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _demoUserRepository.delete(id);
    }
}
