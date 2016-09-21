package com.jzeen.travel.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jzeen.travel.admin.setting.FileUploadSetting;
import com.jzeen.travel.core.util.FileUtil;
import com.jzeen.travel.data.dto.DataTable;
import com.jzeen.travel.data.entity.AccompanyActionType;
import com.jzeen.travel.data.entity.AccompanyType;
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.DemoUser;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.QAccompanyActionType;
import com.jzeen.travel.data.entity.QDemoUser;
import com.jzeen.travel.data.entity.SupplierPriceRuleActive;
import com.jzeen.travel.data.repository.AccompanyActionTypeRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.mysema.query.types.Predicate;
@Controller
@RequestMapping("/accompanytype")
public class AccompanyTypeController {
    
	 @Autowired 
	 AccompanyActionTypeRepository _accompanytypeRepository;
	
	 @Autowired
	 ImageRepository _imageRepository;
	 
	 @Autowired
     private FileUploadSetting _fileUploadSetting;
	 
	 @RequestMapping(method = RequestMethod.GET)
	 public String index()
	{
		return "/accompanytype/list";
	}
	/*@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	 public DataTable<AccompanyActionType, Integer> search(HttpServletRequest request)
	   {
	        DataTable<AccompanyActionType, Integer> dataTable = DataTable.fromRequest(request,_accompanytypeRepository);
	        return dataTable;
	   }*/
	 @ResponseBody
	    @RequestMapping(value = "/search", method = RequestMethod.GET)
	    public DataTable<AccompanyActionType, Integer> search(HttpServletRequest request)
	    {
	        // 使用QueryDsl构造查询条件
	        String keyword = request.getParameter("search[value]");
	        QAccompanyActionType demoUser = QAccompanyActionType.accompanyActionType;
	        Predicate predicate = demoUser.accompanyType.containsIgnoreCase(keyword);
	        DataTable<AccompanyActionType, Integer> dataTable = DataTable.fromRequest(request, _accompanytypeRepository, predicate);

	        return dataTable;
	    }
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createInit(@ModelAttribute AccompanyActionType accompanyType,Model model)
	{
		model.addAttribute("accompanyType", accompanyType);
		return "/accompanytype/create";
		
	}
	 @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(@Valid AccompanyActionType accompanyType, BindingResult bindingResult,Model model, @RequestParam("file") MultipartFile file)
	    {
	        if (bindingResult.hasErrors())
	        {
	            return "/accompanytype/create";
	        }
	        String filePath = _fileUploadSetting.getRootPath();
	        accompanyType.setCreateTime(new Date());
	        if (!file.isEmpty())
	        {
	             try
	             {
	                 byte[] bytes = file.getBytes();
	                 File directory = new File(filePath);
	                 if (!directory.exists())
	                 {
	                     directory.mkdirs();
	                 }
	                 String fileName =file.getOriginalFilename();
	                 filePath += File.separator + fileName;
	                 String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
	                 Image image = new Image();
	                 image.setCreateTime(new Date());
	                 image.setFileName(fileName);
	                 image.setFilePath(lujing);
	                 _imageRepository.save(image);
	                 accompanyType.setImage(image);
	                 accompanyType.setCreateTime(new Date());
	                 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	                 stream.write(bytes);
	                 stream.close();
	                 _accompanytypeRepository.save(accompanyType);
	                 return "redirect:/accompanytype";
	             }
	             catch (Exception e)
	             {
	            	 model.addAttribute("errorMessage","上传文件失败");
	            	 model.addAttribute("accompanyType", _accompanytypeRepository.findOne(accompanyType.getId()));
	                 return "/accompanytype/create";
	             }
	         }
	        else
	         {
	        	 model.addAttribute("errorMessage","请选择上传文件");
	             return "/accompanytype/create";
	         }
	    }
	 
	    @ResponseBody
		@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
		public void delete(@PathVariable int id)
		{
	    	_accompanytypeRepository.delete(id);
		}
	    
	    
	    //详细信息
	    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	    public String detailInit(@PathVariable int id, Model model)
	    {
	        AccompanyActionType accompanyType = _accompanytypeRepository.findOne(id);
	        model.addAttribute("accompanyType", accompanyType);
	        Image image = accompanyType.getImage();
	        model.addAttribute("image", image);
	        return "/accompanytype/detail";
	    }
	    
	    
	    
	    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	    public String updateInit(@PathVariable int id, Model model)
	    {
	        AccompanyActionType accompanyType = _accompanytypeRepository.findOne(id);
	        model.addAttribute("accompanyType",accompanyType);
	        return "/accompanytype/update";
	    }

	    @RequestMapping(value = "/update", method = RequestMethod.POST)
	    public String update(@Valid  AccompanyActionType accompanyType, BindingResult bindingResult, Model model,@RequestParam("file") MultipartFile file)
	    {
	        if (bindingResult.hasErrors())
	        { 
	            return "/accompanytype/update";
	        }
	        AccompanyActionType oldaccompanyType =_accompanytypeRepository.findOne(accompanyType.getId());
	        accompanyType.setCreateTime(new Date());
	        String filePath = _fileUploadSetting.getRootPath();
	        if (!file.isEmpty())
	        {
	            try
	            {
	                byte[] bytes = file.getBytes();
	                File directory = new File(filePath);
	                if (!directory.exists())
	                {
	                    directory.mkdirs();
	                }
	                String fileName =file.getOriginalFilename();
	                filePath += File.separator + fileName;
	                String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
	                Image image = new Image();
	                image.setCreateTime(new Date());
	                image.setFileName(fileName);
	                image.setFilePath(lujing);
	                _imageRepository.save(image);
	                accompanyType.setImage(image);
	                oldaccompanyType.setCreateTime(new Date());
	                _accompanytypeRepository.save(accompanyType);
	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	                stream.write(bytes);
	                stream.close();
	                return "redirect:/accompanytype";
	            }
	            catch (Exception e)
	            {
	                model.addAttribute("errorMessage", "上传文件失败");
	                model.addAttribute("accompanyType", _accompanytypeRepository.findOne(accompanyType.getId()));
	                return "/accompanytype/update";
	            }
	    } else
	    {
	        model.addAttribute("errorMessage", "请选择上传文件");
	        return "/accompanytype/update";
	    }
	    }
	    
	    //随行人员重复提交
	    @ResponseBody
	    @RequestMapping(value = "/valueistrue", method = RequestMethod.POST)
	    public Boolean valueistrue(String accompanyType)
	    {
	        AccompanyActionType accom = _accompanytypeRepository.findByAccompanyType(accompanyType);
	        if (accom == null)
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