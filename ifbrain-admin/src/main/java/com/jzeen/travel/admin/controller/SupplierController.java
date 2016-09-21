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

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.DemoUser;
import com.jzeen.travel.data.entity.QDemoUser;
import com.jzeen.travel.data.entity.QSupplier;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.SupplierRepository;
import com.mysema.query.types.Predicate;

@Controller
@RequestMapping("/supplier")
public class SupplierController
{
    @Autowired
    SupplierRepository _supplierRepository;

    @Autowired
    CodeRepository _codeRepository;
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {

		return "/supplier/list";
	}
	/*@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public DataTable<Supplier, Integer> search(HttpServletRequest request) {
	    String keyword = request.getParameter("search[value]");
        QSupplier demoUser = QSupplier.supplier;
        Predicate predicate = demoUser.cnName.containsIgnoreCase(keyword);
        DataTable<Supplier, Integer> dataTable = DataTable.fromRequest(request, _supplierRepository, predicate);
        return dataTable;
	}*/
	  @ResponseBody
	    @RequestMapping(value = "/search", method = RequestMethod.GET)
	    public List<Supplier> search()
	    {
	        List<Supplier> data = _supplierRepository.findAll();
	        return data;
	    }
	//新建
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute Supplier supplier, Model model)
    {
        String codeType = "供应商";
        List<Code> code = _codeRepository.findByTypeOrderByValueAsc(codeType);
        model.addAttribute("code", code);
        return "/supplier/create";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@Valid Supplier supplier, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "/supplier/create";
        }
        supplier.setSupplierStatus(1);
        supplier.setCreateTime(new Date());
        _supplierRepository.save(supplier);

        return "redirect:/supplier";
    }
    
    //详细信息
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable int id, Model model)
    {
        Supplier supplier = _supplierRepository.findOne(id);
        model.addAttribute("supplier", supplier);
        //
        String codeType = "供应商";
        List<Code> code = _codeRepository.findByTypeOrderByValueAsc(codeType);
        model.addAttribute("code", code);
        return "/supplier/detail";
    }

    
    
    //修改
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String updateInit(@PathVariable int id, Model model)
    {
        Supplier supplier = _supplierRepository.findOne(id);
        model.addAttribute("supplier", supplier);

        String codeType = "供应商";
        List<Code> code = _codeRepository.findByTypeOrderByValueAsc(codeType);
        model.addAttribute("code", code);

        return "/supplier/update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(@Valid Supplier supplier, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            List<Supplier> suppliers = _supplierRepository.findAll();
            model.addAttribute("suppliers", suppliers);
            return "/supplier/update";
        }

        supplier.setSupplierStatus(1);
        supplier.setCreateTime(new Date());
        _supplierRepository.save(supplier);

        return "redirect:/supplier";
    }

    
    
    
    @ResponseBody
    @RequestMapping(value = "/{id}/freeze", method = RequestMethod.PUT)
    public void freeze(@PathVariable Integer id)
    {
        Supplier supplier = _supplierRepository.findOne(id);
        supplier.setSupplierStatus(2);
        _supplierRepository.save(supplier);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/active", method = RequestMethod.PUT)
    public void active(@PathVariable Integer id)
    {
        Supplier supplier = _supplierRepository.findOne(id);
        supplier.setSupplierStatus(1);

        _supplierRepository.save(supplier);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}/retrieveVehicleInfo", method = RequestMethod.GET)
    public void retrieveVehicleInfo()
    {
    }
    
    
    @RequestMapping(value = "/supplierpriceruleactive/{id}", method = RequestMethod.GET)
    public String supplierpriceruleactiveInit(@PathVariable int id , Model model)
    {
        Supplier supplier=_supplierRepository.findOne(id);
        model.addAttribute("supplier",supplier);  
        return "/supplierprice/list";
    }   
    
  //判断供应商重复添加
    @ResponseBody
    @RequestMapping(value = "/doubleis", method = RequestMethod.POST)
    public Boolean doubleis(String supplier)
    {
        Supplier supplierid = _supplierRepository.findByCnName(supplier);
        if (supplierid == null)
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
