package com.jzeen.travel.website.controller.visaOrder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.jzeen.travel.data.entity.VisaOrder;
import com.jzeen.travel.data.repository.VisaOrderRepository;
import com.jzeen.travel.website.setting.FileUploadSetting;


/**
 * 签证Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/visaordera")
public class VisaOrderAController
{  
     @Autowired
    private FileUploadSetting _fileUploadSetting;
    @Autowired
    VisaOrderRepository   _visaOrderRepository;
   /**
    * 根据签证订单id获得数量 
    * @param visaorderId
    * @param model
    * @return
    */
    @RequestMapping(value = "/findheadcount", method = RequestMethod.GET)
    public String find(@RequestParam Integer visaorderId,Model model)
    {
        VisaOrder visaOrder = _visaOrderRepository.findOne(visaorderId);
        Integer headCount = visaOrder.getHeadCount();
        model.addAttribute("headCount", headCount);
        model.addAttribute("orderid", visaorderId);
        return "/visaorder/appmaterials";
    }
    
    
   //客户上传资料 
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model,HttpServletRequest request,@RequestParam("file") MultipartFile files[]) throws Exception
    {
        //获得订单
        String orderId = request.getParameter("orderId");
        VisaOrder visaOrder = _visaOrderRepository.findOne(Integer.parseInt(orderId));
        //材料已提交
        visaOrder.setOrderStatus(3);
        _visaOrderRepository.save(visaOrder);
        
        
        String allname = request.getParameter("allname");
        
        String[] splitname = allname.split(",");
        String filePath = _fileUploadSetting.getRootPath();
        File directory = new File(filePath);
        if (!directory.exists())
        {
            directory.mkdirs();
        }
        int y=files.length;
       
             for(int i=0;i<files.length;i++){
       // 获得原始文件名
         String fileName = files[i].getOriginalFilename();
         String substring = fileName.substring(fileName.indexOf("."),fileName.length());
         //新的名字
         String newname=splitname[i]+visaOrder.getOrderNumber()+substring;
         
         System.out.println("原始文件名:" + fileName);
         System.out.println("分割后的名字"+splitname[i]);
         System.out.println("新的文件名字:"+newname);
         
         
         if (!files[i].isEmpty()) {
             try {
                String path=filePath+File.separator + newname;
                 byte[] bytes = files[i].getBytes();
                 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(path)));
                 stream.write(bytes);
                 stream.close();
                 fileName="";
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
     }
        
        return "";
    }
    
    
}
