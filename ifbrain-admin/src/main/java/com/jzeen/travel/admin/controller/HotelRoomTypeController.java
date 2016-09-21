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

import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.HotelActivity;
import com.jzeen.travel.data.entity.HotelRoomType;
import com.jzeen.travel.data.entity.InsuranceActivity;
import com.jzeen.travel.data.entity.SupplierPriceRule;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.HotelActivityRepository;
import com.jzeen.travel.data.repository.HotelRoomTypeRepository;
import com.jzeen.travel.data.repository.InsuranceActivityRepository;
import com.jzeen.travel.data.repository.SupplierPriceRuleRepository;

@Controller
@RequestMapping("/hotelRoomType")
public class HotelRoomTypeController
{
    @Autowired
    private CodeRepository _codeRepository;
    
    @Autowired
    private HotelActivityRepository _hotelActivityRepository;
    
    @Autowired
    private SupplierPriceRuleRepository _supplierPriceRuleRepository;
    
    @Autowired
    private HotelRoomTypeRepository _hotelRoomTypeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/hotelroomtype/index";
    }

    /**
     * 房间类型管理列表
     */
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<HotelRoomType> search(HttpServletRequest request, Model model)
    {
        
        Integer hotelActivityId=Integer.parseInt(request.getParameter("hotelActivityId"));
        
        //查询代码表供应商为酒店的对象（供应商和1写成固定参数了）
        Code code=_codeRepository.findByTypeAndValue("供应商",1);
        //查询保险活动的供应商ID
        HotelActivity HotelActivity= _hotelActivityRepository.findOne(hotelActivityId);
        //查询相应的中间表的数据（价格系数）
        SupplierPriceRule supplierPriceRule = _supplierPriceRuleRepository.findBySupplierAndSupplierTypeCode(HotelActivity.getSupplier().getId(), code.getId());
        
      //查找系数
        BigDecimal price= supplierPriceRule.getPriceCoefficient();
        
        List<HotelRoomType> data=_hotelRoomTypeRepository.findByHotelActivity(HotelActivity);
        for(int i=0;i<data.size();i++){
            data.get(i).setPerNightPrice(data.get(i).getPerNightCost().multiply(price));
            _hotelRoomTypeRepository.save(data.get(i));
        }
        
        return data;
    }
    
    /**
     * 跳转到房间类型新增页面
     */
    @RequestMapping(value="/create",method=RequestMethod.GET)
    public String createInit(@ModelAttribute HotelRoomType hotelRoomType,HttpServletRequest request,Model model){
        Integer hotelActivityId=Integer.parseInt(request.getParameter("hotelActivityId"));
        
        System.out.println(hotelActivityId);
        HotelActivity hotelActivity= _hotelActivityRepository.findOne(hotelActivityId);
        
        System.out.println(hotelActivity.getHotelEngName());
        model.addAttribute("hotelActivity", hotelActivity);
        
        return "/hotelroomtype/create";
    }
    
    /**
     * 房间类型新增
     */
    @RequestMapping(value="/create",method=RequestMethod.POST)
    public String create(@Valid HotelRoomType hotelRoomType , BindingResult bindingResult , Model model, HttpServletRequest request){
        Integer hotelActivityId = Integer.parseInt(request.getParameter("hotelActivityId"));
        
        if (bindingResult.hasErrors())
        {
            return "/hotelRoomType/create?hotelActivityId="+hotelActivityId;
        }
        hotelRoomType.setHotelActivity(_hotelActivityRepository.findOne(hotelActivityId));
        hotelRoomType.setCreateTime(new Date());
        
        _hotelRoomTypeRepository.save(hotelRoomType);
        
        
        return "redirect:/hotelActivity/hotelRoomType/"+hotelActivityId;
    }
    
    
    /**
     * 跳转到房间类型修改页面
     */
    @RequestMapping(value="/update/{id}",method=RequestMethod.GET)
    public String updateInit(@PathVariable Integer id,Model model){
        HotelRoomType hotelRoomType=_hotelRoomTypeRepository.findOne(id);
        
        System.out.println(hotelRoomType.getRoomType());
        model.addAttribute("hotelRoomType", hotelRoomType);
        HotelActivity hotelActivity=_hotelActivityRepository.findOne(hotelRoomType.getHotelActivity().getId());
        
        System.out.println(hotelActivity.getHotelEngName());
        model.addAttribute("hotelActivity", hotelActivity);
        return "/hotelroomtype/update";
    }
    
    /**
     * 房间类型页面内容修改
     */
    @RequestMapping(value="/update", method=RequestMethod.PUT)
    public String update(@Valid HotelRoomType hotelRoomType , BindingResult bindingResult,HttpServletRequest request){
        
        //获取页面酒店活动ID
        Integer hotelActivityId=Integer.parseInt(request.getParameter("hotelActivityId"));
        if (bindingResult.hasErrors())
        {
            return "/hotelRoomType/update/"+hotelActivityId;
        }
        //将房间类型的内容进行修改
        hotelRoomType.setHotelActivity(_hotelActivityRepository.findOne(hotelActivityId));
        hotelRoomType.setCreateTime(new Date());
        _hotelRoomTypeRepository.save(hotelRoomType);
        
        
        return "redirect:/hotelActivity/hotelRoomType/"+hotelActivityId;
    }
    
    /**
     * 根据成本价，自动改变销售价格（成本价*供应商价格系数）
     */
    @ResponseBody
    @RequestMapping(value="/price",method=RequestMethod.GET)
    public BigDecimal price(BigDecimal perNightCost,int hotelActivityId){
        //查询代码表供应商为酒店的对象（供应商和1写成固定参数了）
        Code code=_codeRepository.findByTypeAndValue("供应商",1);
        //查询酒店活动的供应商ID
        HotelActivity hotelActivity= _hotelActivityRepository.findOne(hotelActivityId);
        //查询相应的中间表的数据（价格系数）
        SupplierPriceRule supplierPriceRule = _supplierPriceRuleRepository.findBySupplierAndSupplierTypeCode(hotelActivity.getSupplier().getId(), code.getId());
        //返回价格系数
        return supplierPriceRule.getPriceCoefficient();
    }
    
}
