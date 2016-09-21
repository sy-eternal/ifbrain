package com.jzeen.travel.admin.controller.financialbrainspeed;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.data.entity.MaterialImage;
import com.jzeen.travel.data.entity.MaterialType;
import com.jzeen.travel.data.repository.MaterialImageRepository;
import com.jzeen.travel.data.repository.MaterialTypeRepository;
import com.jzeen.travel.data.repository.NavigationbarModuleRepository;
import com.jzeen.travel.data.repository.ParentManualImageRepository;

@Controller
@RequestMapping("/financialbrainspeed")
public class FinancialBrainSpeedController {
	
	@Autowired
    FileUploadSetting _fileUploadSetting;
	@Autowired
	MaterialImageRepository  _materialImageRepository;
	@Autowired
	NavigationbarModuleRepository _navigationbarModuleRepository;
	@Autowired
	ParentManualImageRepository _parentManualImageRepository;
	
	@Autowired
	MaterialTypeRepository  _materialTypeRepository;
	
	//显示所有的文章列表
	@RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request, Model model)
    {
         return "/financialbrainspeed/list";
    }
	//datatable列表显示
	@ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public List<MaterialType> search()
    {
		List<MaterialType> data =new ArrayList<MaterialType>();
		List<MaterialType> list = _materialTypeRepository.findAll();
		for(int i=0;i<list.size();i++){
			MaterialType m=new MaterialType();
			m.setId(list.get(i).getId());
			m.setCreateTime(list.get(i).getCreateTime());
			m.setMaterialName(list.get(i).getMaterialName());
			data.add(m);
		}
        return data;
    }
	 @RequestMapping(value = "/index", method = RequestMethod.GET)
	    public String loginInit(Model model, HttpServletRequest request)
	    {
	        return "/financialbrainspeed/index";
	    }
	 
        //创建财脑快慢模块
	    @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file) throws Exception, UnsupportedEncodingException, SQLException
	    {
	    	String materialName = request.getParameter("materialName");
	      String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getMaterialPath();
	      byte[] bytes = file.getBytes();
          File directory = new File(filePath);
           if (!directory.exists())
           {
             directory.mkdirs();
           }
	        String  filename=file.getOriginalFilename();
	        filePath += filename;
	        String lujing=_fileUploadSetting.getMaterialPath()+filename;
	        MaterialImage  image=new MaterialImage();
	        image.setCreateTime(new Date());
	        image.setFileName(filename);
	        image.setFilePath(lujing);
	        _materialImageRepository.save(image);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            stream.write(bytes);
            stream.close();
            MaterialType materialType=new MaterialType();
            materialType.setMaterialName(materialName);
            materialType.setMaterialimage(image);
            materialType.setCreateTime(new Date());
            _materialTypeRepository.save(materialType);
			return "redirect:/financialbrainspeed/list";
	    }
		    
	  //初始化导航模块
	    @RequestMapping(value = "/initupdate", method = RequestMethod.GET)
	    public String update(HttpServletRequest request, Model model) throws Exception
	    {
	    	String id = request.getParameter("id");
	    	 MaterialType materialType = _materialTypeRepository.findOne(Integer.parseInt(id));
	    	model.addAttribute("materialType", materialType);
			return "/financialbrainspeed/updatefinancialbrainspeed";
	    }
	    
	    //修改财脑快慢
	       @RequestMapping(value = "/financialbrainspeedupdate", method = RequestMethod.POST)
	    public String materialupdate(HttpServletRequest request, Model model,@RequestParam("file") MultipartFile file) throws Exception
	    {
			   String id = request.getParameter("id");
			   String materialName = request.getParameter("materialName");
			   MaterialType materialType = _materialTypeRepository.findOne(Integer.parseInt(id));
	    	 String filePath = _fileUploadSetting.getRootPath()+_fileUploadSetting.getMaterialPath();
		      byte[] bytes = file.getBytes();
	          File directory = new File(filePath);
	           if (!directory.exists())
	           {
	             directory.mkdirs();
	           }
		        String  filename=file.getOriginalFilename();
		        filePath += filename;
		        String lujing=_fileUploadSetting.getMaterialPath()+filename;
		      
		        if(materialType.getMaterialimage()==null){
		        	MaterialImage  image=new MaterialImage();
			        image.setCreateTime(new Date());
			        image.setFileName(filename);
			        image.setFilePath(lujing);
			        _materialImageRepository.save(image);
		            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
		            stream.write(bytes);
		            stream.close();
		            materialType.setMaterialName(materialName);
		            materialType.setMaterialimage(image);
		            materialType.setCreateTime(new Date());
		            _materialTypeRepository.save(materialType);
		        }else{
		        	    MaterialImage materialImage = materialType.getMaterialimage();
		        	    materialImage.setCreateTime(new Date());
				        materialImage.setFileName(filename);
				        materialImage.setFilePath(lujing);
				        _materialImageRepository.save(materialImage);
			            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
			            stream.write(bytes);
			            stream.close();
			            materialType.setCreateTime(new Date());
			            materialType.setMaterialimage(materialImage);
			            materialType.setMaterialName(materialName);
			            _materialTypeRepository.save(materialType);
		        }
			return "redirect:/financialbrainspeed/list";
	    }
	    
	      //删除模块
	    @ResponseBody
	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public void delete(HttpServletRequest request)
	    {
	    	String id = request.getParameter("id");
	    	_materialTypeRepository.delete(Integer.parseInt(id));
	    }
	  
	
	    
	  
	  
}
