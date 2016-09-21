package com.jzeen.travel.website.controller.visa;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.InstructionFileRelate;
import com.jzeen.travel.data.entity.VisaInstruction;
import com.jzeen.travel.data.entity.VisaPrice;
import com.jzeen.travel.data.repository.InstructionFileRelateRepository;
import com.jzeen.travel.data.repository.VisaInstructionRepository;
import com.jzeen.travel.data.repository.VisaPriceRepository;
@Controller
@RequestMapping("/visa")
public class VisaController
{
    @Autowired
    private VisaInstructionRepository _vVisaInstructionRepository;
    @Autowired
    private InstructionFileRelateRepository _InstructionFileRelateRepository;
    
    @Autowired
    private  VisaPriceRepository _visaPriceRepository;
    
    
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model)
    {
        //获得旅游签证的价格和类型
       
        List<VisaPrice> list = _visaPriceRepository.findAll();
        model.addAttribute("visaPrice", list);
        
        
        //提交预约签证资料图片
        VisaInstruction visainstruction = _vVisaInstructionRepository.findById(1);
        Image image = visainstruction.getImage();
        model.addAttribute("image", image);
        //准备面试资料
        VisaInstruction visainstructions = _vVisaInstructionRepository.findById(2);
        Image images = visainstructions.getImage();
        model.addAttribute("images", images);
        
        //收到预约面试通知
        VisaInstruction visainstruction3 = _vVisaInstructionRepository.findById(3);
        Image imageappointment = visainstruction3.getImage();
        model.addAttribute("imageappointment", imageappointment);
        
        
      //面试出签
        VisaInstruction visainstruction4 = _vVisaInstructionRepository.findById(4);
        Image imageaInterview = visainstruction4.getImage();
        model.addAttribute("imageaInterview", imageaInterview);
        
      //领取护照
        VisaInstruction visainstruction5 = _vVisaInstructionRepository.findById(5);
        Image imagePassport = visainstruction5.getImage();
        model.addAttribute("imagePassport", imagePassport);
        
      //领取护照
        VisaInstruction visainstruction6 = _vVisaInstructionRepository.findById(6);
        Image imagePassports = visainstruction6.getImage();
        model.addAttribute("imagePassports", imagePassports);
        
        return "/visa/index";
    }
    
    @ResponseBody
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public  List<InstructionFileRelate> visoindex(Model model, HttpServletRequest request)
    {
//        List<InstructionFileRelate> instructionfilerelateq=new ArrayList<InstructionFileRelate>();
        List<InstructionFileRelate> instructionfilerelateq =_InstructionFileRelateRepository .findByVisainstructions(1);
        /*for(int i=0;i<data.size();i++){
            String fileName = data.get(i).getDocument().getFileName();
           int extStart=fileName.lastIndexOf(".");
            String ext=fileName.substring(extStart,fileName.length()).toUpperCase();
            if(ext.equals(".DOC")||ext.equals(".XLS")||ext.equals(".PDF")||ext.equals(".XLSX")){
                instructionfilerelateq.add(data.get(i));
            }
        }*/
        return instructionfilerelateq;
    }
    
    
    

    
    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
    public String downloadvisa(@PathVariable int id,Model model) throws UnsupportedEncodingException
    {
       String filePath= _InstructionFileRelateRepository.findByDocumentId(id);
       String present = new String(filePath.getBytes("UTF-8"),"ISO-8859-1"); 
       return "redirect:/downloads?path="+present;
    }
    
}
