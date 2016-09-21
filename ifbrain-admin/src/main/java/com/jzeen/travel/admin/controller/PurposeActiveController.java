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
import com.jzeen.travel.data.entity.City;
import com.jzeen.travel.data.entity.Country;
import com.jzeen.travel.data.entity.DemoUser;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.PurposeActive;
import com.jzeen.travel.data.entity.QDemoUser;
import com.jzeen.travel.data.entity.QPurposeActive;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.PurposeActiveRepository;
import com.mysema.query.types.Predicate;
@Controller
@RequestMapping("/purposeactive")
public class PurposeActiveController {
	@Autowired 
	PurposeActiveRepository _puActiveRepository;
	 @Autowired
	  ImageRepository _imageRepository;
	 @Autowired
	  private FileUploadSetting _fileUploadSetting;
	@RequestMapping(method = RequestMethod.GET)
	public String index()
	{
		return "/purposeactive/list";
	}
	/*@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	 public DataTable<PurposeActive, Integer> search(HttpServletRequest request)
	 {
	        DataTable<PurposeActive, Integer> dataTable = DataTable.fromRequest(request, _puActiveRepository);
	        return dataTable;
	 }	*/
	   @ResponseBody
	    @RequestMapping(value = "/search", method = RequestMethod.GET)
	    public DataTable<PurposeActive, Integer> search(HttpServletRequest request)
	    {
	        // 使用QueryDsl构造查询条件
	        String keyword = request.getParameter("search[value]");
	        QPurposeActive demoUser = QPurposeActive.purposeActive;
	        Predicate predicate = demoUser.purposeactive.containsIgnoreCase(keyword);
	        DataTable<PurposeActive, Integer> dataTable = DataTable.fromRequest(request, _puActiveRepository, predicate);

	        return dataTable;
	    }
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createInit(@ModelAttribute PurposeActive purposeactive,Model model)
	{
		model.addAttribute("purposeactive", purposeactive);
		return "/purposeactive/create";
		
	}
	 @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(@Valid PurposeActive purposeactive,Model model, BindingResult bindingResult,@RequestParam("file") MultipartFile file)
	    {
	        if (bindingResult.hasErrors())
	        {
	            return "/purposeactive/create";
	        }
	        String filePath = _fileUploadSetting.getRootPath();
	        purposeactive.setCreateTime(new Date());
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
	                 purposeactive.setImage(image);
	                 purposeactive.setCreateTime(new Date());
	                 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	                 stream.write(bytes);
	                 stream.close();
	                 _puActiveRepository.save(purposeactive);
	                 return "redirect:/purposeactive";
	             }
	             catch (Exception e)
	             {
	            	 model.addAttribute("errorMessage","上传文件失败");
	            	 model.addAttribute("purposeactive", _puActiveRepository.findOne(purposeactive.getId()));
	                 return "/purposeactive/create";
	             }
	         }
	         else
	         {
	        	 model.addAttribute("errorMessage","请选择上传文件");
	             return "/purposeactive/create";
	         }
	       
	    }
	 
	 
	 @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	    public String updateInit(@PathVariable int id, Model model)
	    {
	        PurposeActive purposeactive = _puActiveRepository.findOne(id);
	        model.addAttribute("purposeactive",purposeactive);
	        Image image = purposeactive.getImage();
	        model.addAttribute("image", image);
	        return "/purposeactive/update";
	    }

	    @RequestMapping(value = "/update", method = RequestMethod.POST)
	    public String update(@RequestParam("file") MultipartFile file,@Valid  PurposeActive purposeactive, BindingResult bindingResult, Model model)
	    {
	        
	        if (bindingResult.hasErrors())
	        { 
	            return "/purposeactive/update";
	        }
	        PurposeActive oldpurposeactive =_puActiveRepository.findOne(purposeactive.getId());
	        String filePath = _fileUploadSetting.getRootPath();
	        purposeactive.setCreateTime(new Date());
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
	                purposeactive.setImage(image);
	                oldpurposeactive.setCreateTime(new Date());;
	                _puActiveRepository.save(purposeactive);
	                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	                stream.write(bytes);
	                stream.close();
	                return "redirect:/purposeactive";
	            }
	            catch (Exception e)
	            {
	                model.addAttribute("errorMessage", "上传文件失败");
	                model.addAttribute("purposeactive", _puActiveRepository.findOne(purposeactive.getId()));
	                return "/purposeactive/update";
	            }
	    } else
	    {
	        model.addAttribute("errorMessage", "请选择上传文件");
	        return "/purposeactive/update";
	    }

	}
	 
	 
	 
	//详细信息
	    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	    public String detailInit(@PathVariable int id, Model model)
	    {
	        PurposeActive purposeactive = _puActiveRepository.findOne(id);
	        model.addAttribute("purposeactive", purposeactive);
	        Image image =  purposeactive.getImage();
	        model.addAttribute("image", image);
	        return "/purposeactive/detail";
	    } 

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id)
	{
		_puActiveRepository.delete(id);
	}
	//出行目的重复添加
	    @ResponseBody
	    @RequestMapping(value = "/valueistrue", method = RequestMethod.POST)
	    public Boolean valueistrue(String purposeactive)
	    {
	        PurposeActive purpose = _puActiveRepository.findByPurposeactive(purposeactive);
	        if (purpose == null)
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

