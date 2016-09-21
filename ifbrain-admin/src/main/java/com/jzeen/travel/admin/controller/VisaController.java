package com.jzeen.travel.admin.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.ApplicationInfo;
import com.jzeen.travel.data.entity.Document;
import com.jzeen.travel.data.entity.QVisaOrder;
import com.jzeen.travel.data.entity.VisaOrder;
import com.jzeen.travel.data.repository.ApplicationInfoRepository;
import com.jzeen.travel.data.repository.DocumentRepository;
import com.jzeen.travel.data.repository.VisaOrderRepository;
import com.mysema.query.types.expr.BooleanExpression;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/visa")
public class VisaController {
    @Autowired
    private VisaOrderRepository _visaOrderRepository;
    @Autowired
    private ApplicationInfoRepository _applicationInfoRepository;
    @Autowired
    private DocumentRepository _documentRepository;
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model model) {

        return "/visaorder/list";
    }


    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<VisaOrder, Integer> search(HttpServletRequest request) {


        String orderNumber = request.getParameter("orderNumber");
        String orderStatus = request.getParameter("orderStatus");

        if (orderNumber.equals("") && orderStatus.equals("-1")) {
            DataTable<VisaOrder, Integer> dataTable = DataTable.fromRequest(request, _visaOrderRepository);
            return dataTable;
        } else {
            // 使用QueryDsl构造查询条件
            QVisaOrder visaOrder = QVisaOrder.visaOrder;

//            QOrder order = QOrder.order;
            BooleanExpression predicate = visaOrder.isNotNull();

            if (!orderNumber.equals("")) {
                predicate = predicate.and(visaOrder.orderNumber.contains(orderNumber));
            }
            if (!orderStatus.equals("-1")) {
                predicate = predicate.and(visaOrder.orderStatus.eq(Integer.parseInt(orderStatus)));
            }
            DataTable<VisaOrder, Integer> dataTable = DataTable.fromRequest(request, _visaOrderRepository, predicate);
            return dataTable;
        }
    }

    //根据ID获得支付宝信息
    
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public String findById(@RequestParam("orderId") Integer orderId,HttpServletRequest request, Model model) {
       
        VisaOrder visaOrder = _visaOrderRepository.findOne(orderId);
        
        model.addAttribute("visaOrder", visaOrder);
        
        return "/visaorder/zhifubao";
    }
 //添加淘宝订单号
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(HttpServletRequest request, Model model,VisaOrder visaOrder) {
       
        
        String taobaoOrderNumber = request.getParameter("taobaoOrderNumber");
        List<Document> listdocument=new ArrayList<Document>();
        
       VisaOrder visaOrder2 = _visaOrderRepository.findOne(visaOrder.getId());
        
       
       visaOrder2.setTaobaoOrderNumber(visaOrder.getTaobaoOrderNumber());
       
       
       List<ApplicationInfo> findByVisaOrder = _applicationInfoRepository.findByVisaOrder(visaOrder2);
       
       for(int j=0;j<findByVisaOrder.size();j++){
           
           listdocument.add(findByVisaOrder.get(j).getDocument());
           
       }
       
       
       
       for(int i=0;i<listdocument.size();i++){
           Document document = listdocument.get(i);
           String fileName = document.getFileName();
           //截取后缀.zip
           String substring = fileName.substring(fileName.indexOf("."), fileName.length());
           //截取.zip之前的
           String substring1 = fileName.substring(0,fileName.indexOf("."));
           
           String newname=substring1+taobaoOrderNumber+substring;
           document.setFileName(newname);
           _documentRepository.save(document);
           
           
       }
       
       
       _visaOrderRepository.save(visaOrder2);
      
        return "redirect:/visa/orders";
    }
    
    //跳转到签证邮政地址
    @RequestMapping(value = "/{id}/postAddress", method = RequestMethod.GET)
    public String postAddress(@PathVariable("id") Integer id,HttpServletRequest request, Model model) {
       VisaOrder visaOrder = _visaOrderRepository.findById(id);
       model.addAttribute("visaOrder",visaOrder);
        return "/visaorder/postAddress";
    }

    //保存签证快递信息
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("visaOrderId") Integer visaOrderId, @Valid VisaOrder visaOrder, HttpServletRequest request, Model model) {
        VisaOrder order = _visaOrderRepository.findById(visaOrderId);
        order.setExpressName(visaOrder.getExpressName());
        order.setExpressNumber(visaOrder.getExpressNumber());
        order.setOrderStatus(11);
        _visaOrderRepository.save(order);
        return "redirect:/visa/orders";
    }
    
    //跳转预约签证面试详情
	@RequestMapping(value = "/{id}/interview", method = RequestMethod.GET)
	public String interview(@PathVariable("id") Integer id,HttpServletRequest request,Model model){
		VisaOrder visaOrder = _visaOrderRepository.findById(id);
        model.addAttribute("visaOrder",visaOrder);
		return "/visaorder/interview";
	}
	
	//填写预约签证面试详情
	@RequestMapping(value = "/interview", method = RequestMethod.POST)
	public String interviewUpdate(@RequestParam("visaOrderId") Integer visaOrderId,HttpServletRequest request,Model model) throws ParseException{
	   VisaOrder visaOrder = _visaOrderRepository.findById(visaOrderId);
	   String interviewDate = request.getParameter("interviewDate");
	   String interviewTime = request.getParameter("interviewTime");

       SimpleDateFormat fomat = new SimpleDateFormat("yyyy-MM-dd");
       Date date = fomat.parse(interviewDate);
       
	   String interviewPlace = request.getParameter("interviewPlace");
	   String interviewMemo = request.getParameter("interviewMemo");
	   visaOrder.setInterviewMemo(interviewMemo);
        visaOrder.setInterviewDate(date);
       visaOrder.setInterviewPlace(interviewPlace);
       visaOrder.setInterviewTime(interviewTime);
       visaOrder.setOrderStatus(4);
        _visaOrderRepository.save(visaOrder);
		return "redirect:/visa/orders";
	}

    /**
     * 签证订单信息
     */
    @RequestMapping(value = "/{id}/visaInfo")
    public String visaInfo(@PathVariable Integer id, Model model) {
        VisaOrder visaOrder = _visaOrderRepository.findOne(id);
        model.addAttribute("visaOrder", visaOrder);

        List<ApplicationInfo> applicationInfoList = _applicationInfoRepository.findByVisaOrder(visaOrder);
        model.addAttribute("appNames", constructNames(applicationInfoList));

        return "/visaorder/detail";
    }


    /**
     * 预约成功后，面试通过，状态修改为签证成功状态，申请签证成功
     */
    @RequestMapping(value = "/{id}/interviewPass", method = RequestMethod.GET)
    public String interviewPass(@PathVariable("id") Integer id, HttpServletRequest request, Model model) {
        VisaOrder visaOrder = _visaOrderRepository.findById(id);
        visaOrder.setOrderStatus(6);
        _visaOrderRepository.save(visaOrder);

        return "/visaorder/list";
    }

    /**
     * 预约成功后，面试不通过，状态修改为签证失败状态，申请签证失败
     */
    @RequestMapping(value = "/{id}/interviewFail", method = RequestMethod.GET)
    public String interviewFail(@PathVariable("id") Integer id, HttpServletRequest request, Model model) {
        VisaOrder visaOrder = _visaOrderRepository.findById(id);
        visaOrder.setOrderStatus(10);
        _visaOrderRepository.save(visaOrder);

        return "/visaorder/list";
    }


    private String constructNames(List<ApplicationInfo> list) {
        String names = "";

        if (list != null) {
            for (ApplicationInfo info : list) {
                names += ", " + info.getName();
            }
        }

        if (names.length() > 1) {
            names = names.substring(2);
        }

        return names;
    }
}
