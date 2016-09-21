package com.jzeen.travel.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

//import io.undertow.attribute.RequestMethodAttribute;










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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.DatePlan;
import com.jzeen.travel.data.entity.Document;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.InstructionFileRelate;
import com.jzeen.travel.data.entity.QInstructionFileRelate;
import com.jzeen.travel.data.entity.QVisaInstruction;
import com.jzeen.travel.data.entity.VisaInstruction;
import com.jzeen.travel.data.repository.DocumentRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.InstructionFileRelateRepository;
import com.jzeen.travel.data.repository.VisaInstructionRepository;
import com.mysema.query.types.Predicate;


@Controller
@RequestMapping("/visainstruction")
public class VisaInstructionController
{
    @Autowired
    VisaInstructionRepository _visaInstructionRepository;
    
    @Autowired
    private FileUploadSetting _fileUploadSetting;
    
    @Autowired
    private ImageRepository _imageRepository;
    
    @Autowired
    private InstructionFileRelateRepository _instructionFileRelateRepository;
    
    @Autowired
    private DocumentRepository _documentRepository;
    
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String index()
    {
        return "/visainstruction/index";
    }
    
    /**
     * 签证服务介绍列表
     */
/*    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public DataTable<VisaInstruction, Integer> search(HttpServletRequest request)
    {
        String keyword = request.getParameter("search[value]");
        QVisaInstruction visaInstruction = QVisaInstruction.visaInstruction;
        
        Predicate predicate=visaInstruction.processName.containsIgnoreCase(keyword);
        
        DataTable<VisaInstruction, Integer> dataTable = DataTable.fromRequest(request, _visaInstructionRepository,predicate);
        return dataTable;
    }*/
    
  @ResponseBody
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public  Iterable<InstructionFileRelate> search(Model model, HttpServletRequest request)
    {
        List<InstructionFileRelate> data =_instructionFileRelateRepository.findByVisainstructionrelate();
        System.out.println(data.size());
        return data;
    }

    /**
     * 初始化新增签证服务介绍
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createInit(@ModelAttribute VisaInstruction visainstruction){
        return "/visainstruction/create";
    }
    
    /**
     * 新增签证服务介绍
     */
    @RequestMapping(value="/create",method=RequestMethod.POST)
    public String create(@Valid VisaInstruction visainstruction, BindingResult bindingResult, Model model, HttpServletRequest request,
            @RequestParam("file") MultipartFile file, @RequestParam("filefile") MultipartFile filefile){
        
        System.out.println("进入Controller");
        if (bindingResult.hasErrors())
        {
            return "/visainstruction/create";
        }
        
        //linux
//        String filePath = _fileUploadSetting.getRootPath() + _fileUploadSetting.getVisaPath();
        
        String filePath = "."+File.separator + _fileUploadSetting.getVisaPath();

        if (!file.isEmpty())
        {
            //保存服务介绍表
            String processName=request.getParameter("processName");
            visainstruction.setProcessName(processName);
            visainstruction.setCreatetime(new Date());
            
            //保存上传图片
            try
            {
                byte[] bytes = file.getBytes();
                File directory = new File(_fileUploadSetting.getRootPath()+filePath);
                if (!directory.exists())
                {
                    directory.mkdirs();
                }
                String fileName =file.getOriginalFilename();
//                String nowpath=filePath+File.separator + fileName;
                String nowpath=filePath+ fileName;
               // String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
                Image image = new Image();
                image.setCreateTime(new Date());
                image.setFileName(fileName);
                image.setFilePath(filePath);
                _imageRepository.save(image);
                
                visainstruction.setImage(image);
                _visaInstructionRepository.save(visainstruction);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath()+nowpath)));
                stream.write(bytes);
                stream.close();
         
                if (!filefile.isEmpty()){
                    try
                    {
                        byte[] bytess = file.getBytes();
                        File directorys = new File(_fileUploadSetting.getRootPath()+filePath);
                        if (!directorys.exists())
                        {
                            directory.mkdirs();
                        }
                        String fileNames =filefile.getOriginalFilename();
//                        String nowpaths=filePath+File.separator + fileName;
                        String nowpaths=filePath + fileName;
                       // String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
                        Document document=new Document();
                        document.setCreateTime(new Date());
                        document.setFileName(fileNames);
                        document.setFilePath(nowpaths);
                        _documentRepository.save(document);
                        
                        InstructionFileRelate instructionFileRelate=new InstructionFileRelate();
                        instructionFileRelate.setDocument(document);
                        instructionFileRelate.setVisainstruction(visainstruction);
                        _instructionFileRelateRepository.save(instructionFileRelate);
                        
                        BufferedOutputStream streams = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath() + nowpaths)));
                        streams.write(bytess);
                        streams.close();
                        
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            catch (Exception e)
            {
                model.addAttribute("errorMessage", "上传文件失败");
                return "redirect:/visainstruction/create";
            }
        }else{
            model.addAttribute("errorMessage", "请选择上传文件");
            return "/visainstruction/create";
        
        }
        return "redirect:/visainstruction";
        
        
        
    }
    
    
    
/**
 * 初始化修改签证服务介绍图片
 */
@RequestMapping(value = "/updateimg/{id}", method = RequestMethod.GET)
public String updateimgInit(@PathVariable int id, Model model)
{

    VisaInstruction visainstruction = _visaInstructionRepository.findOne(id);
    model.addAttribute("visainstruction", visainstruction);
    return "/visainstruction/updateimg";
}

/**
 * 修改签证服务介绍图片
 * 修改图片
 */
@RequestMapping(value = "/updateimg", method = RequestMethod.POST)
public String updateimg(@RequestParam("file") MultipartFile file,Model model, HttpServletRequest request,@RequestParam("visainstructionId") Integer visainstructionId )
{
    VisaInstruction visainstruction=_visaInstructionRepository.findOne(visainstructionId);
    
    //上传图片为空的情况
    if(file.isEmpty()){
        //修改签证服务介绍（t_visa_instruction）表信息
        System.out.println(visainstruction.getId()+"签证服务ID");
        VisaInstruction newvisainstruction=_visaInstructionRepository.findOne(visainstructionId);
        String processName= request.getParameter("processName");
        newvisainstruction.setProcessName(processName);
        _visaInstructionRepository.save(newvisainstruction);
        //根据图片ID查找图片
        Image image=_imageRepository.findOne(newvisainstruction.getImage().getId());
        newvisainstruction.setProcessName(processName);


        _visaInstructionRepository.save(newvisainstruction);
        _imageRepository.save(image);
        return "redirect:/visainstruction";
    
        //上传图片不为空的情况
    }else if(!file.isEmpty()){
        //修改签证服务介绍（t_visa_instruction）表信息
        VisaInstruction newvisainstruction=_visaInstructionRepository.findOne(visainstructionId);
        System.out.println(newvisainstruction.getProcessName());
        System.out.println(newvisainstruction.getId());
        System.out.println(newvisainstruction.getCreatetime());
        System.out.println(newvisainstruction.getImage());
        String processName= request.getParameter("processName");
        newvisainstruction.setProcessName(processName);
        _visaInstructionRepository.save(newvisainstruction);
        //根据图片ID查找图片
        Image image=_imageRepository.findOne(newvisainstruction.getImage().getId());
        
        
        //linux
//        String filePath = _fileUploadSetting.getRootPath() + _fileUploadSetting.getVisaPath();
        
        String filePath = "."+File.separator +_fileUploadSetting.getVisaPath();      
      
      //保存上传图片
        try
        {
            byte[] bytes = file.getBytes();
            File directory = new File(_fileUploadSetting.getRootPath()+filePath);
            if (!directory.exists())
            {
                directory.mkdirs();
            }
            String fileName =file.getOriginalFilename();
//            String nowpath=filePath+File.separator + fileName;
            String nowpath=filePath + fileName;
            // String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
//             String lujing=filePath.substring(filePath.lastIndexOf("\\"));
            
           //File.separator为斜杠"/"
             String lujing=File.separator + fileName;//此打印出来的路径是/文件名
          image.setUpdateTime(new Date());
          image.setFileName(fileName);
          image.setFilePath(nowpath);
         _imageRepository.save(image);
          
          BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath() + nowpath)));
          stream.write(bytes);
          stream.close();
        
      }
      catch (Exception e)
      {
          model.addAttribute("errorMessage", "上传图片失败");
          return "redirect:/visainstruction/updateimg/" + visainstructionId;
      }
    }
    
    return "redirect:/visainstruction";
}

/**
 * 初始化修改签证服务介绍文件
 */
@RequestMapping(value = "/updatefile/{id}", method = RequestMethod.GET)
public String updatefileInit(@PathVariable int id, Model model)
{
    VisaInstruction visainstruction = _visaInstructionRepository.findOne(id);
    
    model.addAttribute("visainstruction", visainstruction);
    
    return "/visainstruction/updatefile";
}

/**
 * 修改签证服务介绍文件
 * 修改文件
 */
@RequestMapping(value = "/updatefile", method = RequestMethod.POST)
public String updatefile(@RequestParam("file") MultipartFile[] file, Model model, HttpServletRequest request,@RequestParam("visainstructionId") Integer visainstructionId){
 

    
    //上传文件为空的情况,不做任何改变
    for(int i=0;i<file.length;i++){
    if(file[i].isEmpty()){
        //修改签证服务介绍（t_visa_instruction）表信息
        VisaInstruction newvisainstruction=_visaInstructionRepository.findOne(visainstructionId);
        String processName= request.getParameter("processName");
        newvisainstruction.setProcessName(processName);
        newvisainstruction.setCreatetime(new Date());
        newvisainstruction.setImage(newvisainstruction.getImage());
        newvisainstruction.setInstructionfilerelate(newvisainstruction.getInstructionfilerelate());
        _visaInstructionRepository.save(newvisainstruction);
    }
    
    }
    //上传文件不为空的情况，删除之前信息，保证修改信息

    
    //判断之前已有文件，如果不为空，就删除
    for(int i=file.length-1;i<file.length;i++){
        if(!file[i].isEmpty()){
            List<InstructionFileRelate> instructionFileRelate=_instructionFileRelateRepository.findByVisainstructions(visainstructionId);
            for(int j=0;j<instructionFileRelate.size();j++){
                _instructionFileRelateRepository.delete(instructionFileRelate.get(j).getId());
                _documentRepository.delete(instructionFileRelate.get(j).getDocument().getId());
            }
        }
    }
    
    
    for(int i=0;i<file.length;i++){
        
    
     if(!file[i].isEmpty()){
         
         //linux
//         String filePath = _fileUploadSetting.getRootPath() + _fileUploadSetting.getVisaPath();
         
        String filePath = "."+File.separator+_fileUploadSetting.getVisaPath();
        
        //修改签证服务介绍（t_visa_instruction）表信息
        VisaInstruction newvisainstruction=_visaInstructionRepository.findOne(visainstructionId);
        String processName= request.getParameter("processName");
        newvisainstruction.setProcessName(processName);
        newvisainstruction.setCreatetime(new Date());
        _visaInstructionRepository.save(newvisainstruction);
        
        
//        //获得中间表对象
//        InstructionFileRelate instructionFileRelate= _instructionFileRelateRepository.findByvisainstructionId(newvisainstruction.getId());
//        System.out.println(newvisainstruction.getInstructionfilerelate());
//        //根据中间表字段，查询文件对象
//        Document document=_documentRepository.findOne(instructionFileRelate.getDocument().getId());
//        
        try
        {
            byte[] bytes = file[i].getBytes();
            File directory = new File(_fileUploadSetting.getRootPath()+filePath);
            if (!directory.exists())
            {
                directory.mkdirs();
            }
            String fileName =file[i].getOriginalFilename();
//            String filename=new String(fileName.getBytes("UTF-8"),"GBK");
            
            String nowpath=filePath + fileName;
            
            // String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
           // String lujing=filePath.substring(filePath.lastIndexOf("\\"));
            String path=File.separator + fileName;
            //文件修改
            Document document=new Document();
            document.setUpdateTime(new Date());
            document.setCreateTime(new Date());
            document.setFileName(fileName);
            document.setFilePath(nowpath);
            _documentRepository.save(document);
            
            //修改中间表
            InstructionFileRelate instructionFileRelate=new InstructionFileRelate();
            instructionFileRelate.setDocument(document);
            instructionFileRelate.setVisainstruction(newvisainstruction);
            instructionFileRelate.setCreatetime(new Date());
            
            _instructionFileRelateRepository.save(instructionFileRelate);
            
            //写文件
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(_fileUploadSetting.getRootPath() + nowpath)));
            stream.write(bytes);
            stream.close();
            
        }catch(Exception e){
            model.addAttribute("errorMessage", "上传图片失败");
            model.addAttribute("visainstruction", _visaInstructionRepository.findOne(visainstructionId));
            return "/visainstruction/updatefile";
        }
    }      
   
}
    return "redirect:/visainstruction";
}
}
