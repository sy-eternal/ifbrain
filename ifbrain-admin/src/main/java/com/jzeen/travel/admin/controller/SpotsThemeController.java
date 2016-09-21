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
import com.jzeen.travel.data.entity.QDemoUser;
import com.jzeen.travel.data.entity.QThemeActive;
import com.jzeen.travel.data.entity.Supplier;
import com.jzeen.travel.data.entity.Theme;
import com.jzeen.travel.data.entity.ThemeActive;
import com.jzeen.travel.data.repository.AccompanyTypeRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.ThemeActiveRepository;
import com.jzeen.travel.data.repository.ThemeRepository;
import com.mysema.query.types.Predicate;
@Controller
@RequestMapping("/theme")
public class SpotsThemeController {
	@Autowired 
	 ThemeActiveRepository _tThemeRepository;
	
	 @Autowired
	 ImageRepository _imageRepository;
	 
	 @Autowired
    private FileUploadSetting _fileUploadSetting;
	 
	@RequestMapping(method = RequestMethod.GET)
	public String index()
	{
		return "/spotstheme/list";
	}
	
	/*@ResponseBody
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	 public DataTable<ThemeActive, Integer> search(HttpServletRequest request)
	   {
	        DataTable<ThemeActive, Integer> dataTable = DataTable.fromRequest(request,_tThemeRepository);
	        return dataTable;
	   }*/
	   @ResponseBody
	    @RequestMapping(value = "/search", method = RequestMethod.GET)
	    public DataTable<ThemeActive, Integer> search(HttpServletRequest request)
	    {
	        // 使用QueryDsl构造查询条件
	        String keyword = request.getParameter("search[value]");
	        QThemeActive theme = QThemeActive.themeActive;
	        Predicate predicate = theme.theme.containsIgnoreCase(keyword);
	        DataTable<ThemeActive, Integer> dataTable = DataTable.fromRequest(request, _tThemeRepository, predicate);
	        return dataTable;
	    }

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createInit(@ModelAttribute ThemeActive theme,Model model)
	{
		model.addAttribute("theme", theme);
		return "/spotstheme/create";
		
	}
	 @RequestMapping(value = "/create", method = RequestMethod.POST)
	    public String create(@Valid ThemeActive theme, BindingResult bindingResult,Model model, @RequestParam("file") MultipartFile file)
	    {
	        if (bindingResult.hasErrors())
	        {
	            return "/spotstheme/create";
	        }
//	        String folderName = _fileUploadSetting.getRootPath();
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
	                 theme.setImage(image);
	                 theme.setCreateTime(new Date());
	                 BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	                 stream.write(bytes);
	                 stream.close();
	                 _tThemeRepository.save(theme);
	                 return "redirect:/theme";
	             }
	             catch (Exception e)
	             {
	            	 model.addAttribute("errorMessage","上传文件失败");
	            	 model.addAttribute("theme", _tThemeRepository.findOne(theme.getId()));
	                 return "/spotstheme/create";
	             }
	         }
	        else
	         {
	        	 model.addAttribute("errorMessage","请选择上传文件");
	             return "/spotstheme/create";
	         }
	    }
	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable int id)
	{
		_tThemeRepository.delete(id);;
	}
	
	
	  @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
      public String updateInit(@PathVariable int id, Model model)
      {
          ThemeActive theme = _tThemeRepository.findOne(id);
          model.addAttribute("theme",theme);
          return "/spotstheme/update";
      }

      @RequestMapping(value = "/update", method = RequestMethod.POST)
      public String update(@Valid  ThemeActive theme, BindingResult bindingResult, Model model,@RequestParam("file") MultipartFile file)
      {
          if (bindingResult.hasErrors())
          { 
              return "/spotstheme/update";
          }
          ThemeActive oldtheme =_tThemeRepository.findOne(theme.getId());
          theme.setCreateTime(new Date());
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
                  theme.setImage(image);
                  oldtheme.setCreateTime(new Date());
                  _tThemeRepository.save(theme);
                  BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                  stream.write(bytes);
                  stream.close();
                  return "redirect:/theme";
              }
              catch (Exception e)
              {
                  model.addAttribute("errorMessage", "上传文件失败");
                  model.addAttribute("theme", _tThemeRepository.findOne(theme.getId()));
                  return "/spotstheme/update";
              }
      } else
      {
          model.addAttribute("errorMessage", "请选择上传文件");
          return "/spotstheme/update";
      }
      }
	
	
	
	
	
	
	 //详细信息
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detailInit(@PathVariable int id, Model model)
    {
        ThemeActive theme = _tThemeRepository.findOne(id);
        model.addAttribute("theme", theme);
        Image image = theme.getImage();
        model.addAttribute("image", image);
        return "/spotstheme/detail";
    }
	//判断景点主题重复添加
    @ResponseBody
    @RequestMapping(value = "/valueistrue", method = RequestMethod.POST)
    public Boolean valueistrue(String theme)
    {
        ThemeActive themes = _tThemeRepository.findByTheme(theme);
        if (themes == null)
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

