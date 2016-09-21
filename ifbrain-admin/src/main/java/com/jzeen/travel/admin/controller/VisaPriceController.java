package com.jzeen.travel.admin.controller;

import java.util.Date;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.QVisaPrice;
import com.jzeen.travel.data.entity.VisaPrice;
import com.jzeen.travel.data.repository.VisaPriceRepository;
import com.mysema.query.types.Predicate;


@Controller
@RequestMapping("/visaprice")
public class VisaPriceController
{

    @Autowired
    VisaPriceRepository _visaPriceRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/visaprice/index";
    }
    
    /**
     * 签证服务价格列表
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<VisaPrice, Integer> search(HttpServletRequest request)
    {
        String keyword = request.getParameter("search[value]");
        QVisaPrice visaPrice = QVisaPrice.visaPrice;
        
        Predicate predicate=visaPrice.type.containsIgnoreCase(keyword);
        
        DataTable<VisaPrice, Integer> dataTable = DataTable.fromRequest(request, _visaPriceRepository,predicate);
        return dataTable;
    }
    
    /**
     * 初始化签证更新页面
     */
    @RequestMapping(value="/update/{id}",method=RequestMethod.GET)
    public String update(@PathVariable int id,Model model){
        VisaPrice visaPrice=_visaPriceRepository.findOne(id);
        model.addAttribute("visaprice", visaPrice);
        
        return "/visaprice/update";
    }
    
    /**
     * 签证更新
     */
    @RequestMapping(value="/update",method=RequestMethod.PUT)
    public String update(@Valid VisaPrice visaprice){
        
        
        
//        System.out.println(visaprice.getType());
//        System.out.println(visaprice.getId());
//        System.out.println(visaprice.getUpdatetime());
//        System.out.println(visaprice.getPrice());
//        System.out.println(new Date());
        visaprice.setUpdatetime(new Date());
        
//       VisaPrice vPrice=_visaPriceRepository.findOne(visaprice.getId());
//        vPrice.setUpdatetime(new Date());
        _visaPriceRepository.save(visaprice);
        return "redirect:/visaprice";
    }
}
