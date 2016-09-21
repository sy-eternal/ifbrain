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
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.QSupplierPriceRuleActive;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.SupplierPriceRuleActive;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.SupplierPriceRuleActiveRepository;
import com.jzeen.travel.data.repository.SupplierRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/supplierpriceruleactive")
public class SupplierPriceRuleActiveController
{
    @Autowired
    private SupplierPriceRuleActiveRepository _sSupplierPriceRuleActiveRepository;

    @Autowired
    private SupplierRepository _sSupplierRepository;

    @Autowired
    private CodeRepository _sCodeRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request)
    {
        return "/supplierprice/list";
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<SupplierPriceRuleActive, Integer> search(HttpServletRequest request, Model model)
    {
        Integer supplierpriceruleactiveId = Integer.parseInt(request.getParameter("supplierpriceruleactiveId"));
        QSupplierPriceRuleActive supplierpriceruleactive = QSupplierPriceRuleActive.supplierPriceRuleActive;
        Predicate predicate = supplierpriceruleactive.supplier.id.eq(supplierpriceruleactiveId);
        DataTable<SupplierPriceRuleActive, Integer> dataTable = DataTable.fromRequest(request, _sSupplierPriceRuleActiveRepository, predicate);
        return dataTable;
    }

    // 修改
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        SupplierPriceRuleActive supplierpriceruleactive = _sSupplierPriceRuleActiveRepository.findOne(id);
        model.addAttribute("supplierpriceruleactive", supplierpriceruleactive);
        List<Supplier> supplier = _sSupplierRepository.findAll();
        model.addAttribute("supplier", supplier);
        String codeType = "供应商";
        List<Code> code = _sCodeRepository.findByTypeOrderByValueAsc(codeType);
        model.addAttribute("code", code);
        return "/supplierprice/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@Valid SupplierPriceRuleActive supplierpriceruleactive, BindingResult bindingResult,
            Model model)
    {
        if (bindingResult.hasErrors())
        {
            List<Supplier> supplier = _sSupplierRepository.findAll();
            model.addAttribute("supplier", supplier);
            String codeType = "供应商";
            List<Code> code = _sCodeRepository.findByTypeOrderByValueAsc(codeType);
            model.addAttribute("code", code);
            return "/supplierprice/update";
        }
        SupplierPriceRuleActive oldsupplierpriceruleactive = _sSupplierPriceRuleActiveRepository.findOne(supplierpriceruleactive.getId());
        Date createTime = oldsupplierpriceruleactive.getCreateTime();
        Supplier supplier = oldsupplierpriceruleactive.getSupplier();
        Code code = supplierpriceruleactive.getCode();
        supplierpriceruleactive.setCreateTime(createTime);
        supplierpriceruleactive.setCode(code);
        supplierpriceruleactive.setSupplier(supplier);
        _sSupplierPriceRuleActiveRepository.save(supplierpriceruleactive);
        return "redirect:/supplier/supplierpriceruleactive/" + oldsupplierpriceruleactive.getSupplier().getId();
    }

    // 新建
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute SupplierPriceRuleActive supplierpriceruleactive, Model model,
            Integer supplierpriceruleactiveId)
    {
        Supplier supplier = _sSupplierRepository.findOne(supplierpriceruleactiveId);
        model.addAttribute("supplier", supplier);
        String codeType = "供应商";
        List<Code> code = _sCodeRepository.findByTypeOrderByValueAsc(codeType);
        model.addAttribute("code", code);
        model.addAttribute("supplierpriceruleactive", supplierpriceruleactive);
        return "/supplierprice/create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid SupplierPriceRuleActive supplierpriceruleactive, BindingResult bindingResult,
            Model model, Integer supplierpriceruleactiveId)
    {
        if (bindingResult.hasErrors())
        {
            List<Supplier> supplier = _sSupplierRepository.findAll();
            model.addAttribute("supplier", supplier);
            String codeType = "供应商";
            List<Code> code = _sCodeRepository.findByTypeOrderByValueAsc(codeType);
            model.addAttribute("code", code);
            return new ModelAndView("/supplierprice/create", "formErrors", bindingResult.getAllErrors());
        }
        supplierpriceruleactive.setCreateTime(new Date());
        Supplier supplier = _sSupplierRepository.findOne(supplierpriceruleactiveId);
        supplierpriceruleactive.setSupplier(supplier);
        _sSupplierPriceRuleActiveRepository.save(supplierpriceruleactive);
        return new ModelAndView("redirect:/supplier/supplierpriceruleactive/" + supplierpriceruleactiveId);
    }

    // 删除
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int id)
    {
        _sSupplierPriceRuleActiveRepository.delete(id);
    }
    
}
