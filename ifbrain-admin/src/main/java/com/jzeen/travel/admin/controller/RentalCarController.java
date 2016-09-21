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

import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.RentalCar;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.RentalRepository;
import com.jzeen.travel.data.repository.SupplierRepository;

@Controller
@RequestMapping("/specialCar")
public class RentalCarController
{

    @Autowired 
    private SupplierRepository _supplierRepository;
    
    @Autowired 
    private RentalRepository _rentalRepository;
    
    @Autowired 
    private CodeRepository _codeRepository;
    
    /**
     * 默认跳转页面
     * @return 返回到租车主页/specialcar/index
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        //此路径为templates下的文件夹为specialcar的包下的index.html的页面
        return "/rentalcar/index";
    }
    
    /**
     * 租车活动列表显示
     */
    @ResponseBody
    @RequestMapping(value="/search",method=RequestMethod.GET)
    public List<RentalCar> search()
    {
        List<RentalCar> data=_rentalRepository.findAll();
        return data;
    }
    
    
    
    /**
     * 跳转到新增页面
     */
    @RequestMapping(value="/create",method=RequestMethod.GET)
    public String createInit(@ModelAttribute RentalCar rentalCar,Model model){
        //查询代码表供应商为租车的对象（供应商和5写成固定参数了，写死了）
        Code code=_codeRepository.findByTypeAndValue("供应商",5);
        
        //根据代码表ID，查询供应商信息
        List<Supplier> supplier=_supplierRepository.findByCodeId(code.getId());
        System.out.println(supplier);
        model.addAttribute("supplier", supplier);
        return "/rentalcar/create";
    }
    
    
    /**
     * 租车活动新增
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid RentalCar rentalCar, BindingResult bindingResult)
    {
        //判断是否错误，如果错误，返回
        if (bindingResult.hasErrors())
        {
            return "/specialCar/create";
        }
        rentalCar.setUpdateTime(new Date());
        //保存code对象
        _rentalRepository.save(rentalCar);

        return "redirect:/specialCar";
    }
    
    /**
     * 显示租车修改页面（根据ID查询当前信息）
     */
    @RequestMapping(value="/update/{id}",method=RequestMethod.GET)
    public String updateInit(Model model,@PathVariable Integer id){
        RentalCar rentalCar=_rentalRepository.findOne(id);
        model.addAttribute("rentalCar", rentalCar);
        
        //查询代码表供应商为保险的对象(供应商和5为固定参数了，次数写死了)
        Code code=_codeRepository.findByTypeAndValue("供应商",5);
        
        //根据代码表ID，查询供应商信息
        List<Supplier> supplier=_supplierRepository.findByCodeId(code.getId());
        model.addAttribute("supplier", supplier);
        return "/rentalcar/update";
    }
    
    /**
     * 租车活动修改
     */
    @RequestMapping(value="/update",method=RequestMethod.PUT)
    public String update(@Valid RentalCar rentalCar,BindingResult bindingResult){
        //判断是否错误，如果错误，返回到update页面，如果正确继续执行
        if (bindingResult.hasErrors())
        {
            return "/specialCar/update";
        }
        rentalCar.setUpdateTime(new Date());
        _rentalRepository.save(rentalCar);
        return "redirect:/specialCar";
    }
    
    
    /**
     * 根据租车ID查询租车内容，传到费率管理页面
     */
    @RequestMapping(value="/specialCarRate/{id}",method=RequestMethod.GET)
    public String insuranceRateInit(@PathVariable int id, Model model){
        model.addAttribute("rentalCar",_rentalRepository.findOne(id));
        return "/rentalcar/index";
    }

}
