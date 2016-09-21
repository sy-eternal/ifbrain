package com.jzeen.travel.admin.controller;

import com.jzeen.travel.data.entity.ApplicationInfo;
import com.jzeen.travel.data.entity.VisaOrder;
import com.jzeen.travel.data.repository.ApplicationInfoRepository;
import com.jzeen.travel.data.repository.VisaOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/visaaudit")
public class VisaAuditController {
    @Autowired
    private VisaOrderRepository _visaOrderRepository;

    @Autowired
    private ApplicationInfoRepository _applicationInfoRepository;

    @RequestMapping(value = "/{id}/orders", method = RequestMethod.GET)
    public String index(@PathVariable("id") Integer id,HttpServletRequest request, Model model) {
       VisaOrder visaOrder = _visaOrderRepository.findById(id);
       model.addAttribute("visaOrder",visaOrder);
        return "/visaaudit/list";
    }
    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<ApplicationInfo> search(@RequestParam("visaOrderId") Integer visaOrderId,HttpServletRequest request, Model model) {
       VisaOrder visaOrder = _visaOrderRepository.findById(visaOrderId);
       List<ApplicationInfo> applicationInfos = _applicationInfoRepository.findByVisaOrder(visaOrder);
        return applicationInfos;
    }
    
//下载
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public String downloadvisa(@PathVariable int id,Model model) throws UnsupportedEncodingException
    {
        String filePath = _applicationInfoRepository.findByDocumentId(id);
        String present = new String(filePath.getBytes("UTF-8"), "ISO-8859-1");
        
    

        ApplicationInfo applicationInfo = _applicationInfoRepository.findOne(id);
        String taobaoOrderNumber = new String(applicationInfo.getVisaOrder().getTaobaoOrderNumber().getBytes("UTF-8"),"ISO-8859-1");
        
        return "redirect:/downloads?path=" + present + "&tbu=" + taobaoOrderNumber;
    }
    
    //审核通过
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestParam("visaOrderId") Integer visaOrderId,Model model)
    {
      VisaOrder visaOrder = _visaOrderRepository.findById(visaOrderId);
      visaOrder.setOrderStatus(3);   
      _visaOrderRepository.save(visaOrder);
    }
    
    //审核未通过
 
    @RequestMapping(value = "/updateA", method = RequestMethod.POST)
    public String updateA(@RequestParam("visaOrderId") Integer visaOrderId,Model model,HttpServletRequest request)
    {
      VisaOrder visaOrder = _visaOrderRepository.findById(visaOrderId);
      visaOrder.setOrderStatus(8);   
      String comment = request.getParameter("comment") ;
      visaOrder.setComment(comment);
      _visaOrderRepository.save(visaOrder);
      return "redirect:/visa/orders";
    }
    /*
    //审核未通过
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(@RequestParam("visaOrderId") Integer visaOrderId,HttpServletRequest request,Model model)
    {
      VisaOrder visaOrder = _visaOrderRepository.findById(visaOrderId);
      String comment = request.getParameter("comment") ;
      visaOrder.setComment(comment);
      _visaOrderRepository.save(visaOrder);
       return "redirect:/visa/orders";
    }*/
}
