package com.jzeen.travel.website.controller.personalCenter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.jzeen.travel.core.util.CipherUtil;
import com.jzeen.travel.data.entity.Code;
import com.jzeen.travel.data.entity.Guide;
import com.jzeen.travel.data.entity.GuideImageRelate;
import com.jzeen.travel.data.entity.GuideTags;
import com.jzeen.travel.data.entity.Image;
import com.jzeen.travel.data.entity.Money;
import com.jzeen.travel.data.entity.Spot;
import com.jzeen.travel.data.entity.StandardGuide;
import com.jzeen.travel.data.entity.ThemeActive;
import com.jzeen.travel.data.entity.User;
import com.jzeen.travel.data.repository.CodeRepository;
import com.jzeen.travel.data.repository.ConfiremedTravelerRepository;
import com.jzeen.travel.data.repository.GuideImageRelateRepository;
import com.jzeen.travel.data.repository.GuideTagsRepository;
import com.jzeen.travel.data.repository.ImageRepository;
import com.jzeen.travel.data.repository.StandardGuideRepository;
import com.jzeen.travel.data.repository.UserRepository;
import com.jzeen.travel.website.setting.FileUploadSetting;

@Controller
@RequestMapping("/personalCenter")
public class PersonalCenter {
	@Autowired
	UserRepository _userRepository;

	@Autowired
	ConfiremedTravelerRepository _confiremedTravelerRepository;
	@Autowired
	StandardGuideRepository _standardGuideRepository;
	@Autowired
	CodeRepository _codeRepository;
	@Autowired
	GuideImageRelateRepository _guideImageRelateRepository;
	@Autowired
	GuideTagsRepository _guGuideTagsRepository;
	 @Autowired
	    FileUploadSetting _fileUploadSetting;
	 @Autowired
	    private ImageRepository _imageRepository;
	/**
	 * 个人信息
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(HttpServletRequest request, Model model) {
		User userInfo = (User) request.getSession().getAttribute("user");
		model.addAttribute("userInfo", userInfo);
		User olduser = _userRepository.findOne(userInfo.getId());
		model.addAttribute("olduser", olduser);
		return "/personalcenter/index";
	}

	@RequestMapping(value = "/doCreate", method = RequestMethod.POST)
	public String doCreate( Model model, @RequestParam("file") MultipartFile file,HttpServletRequest request,HttpSession session) {
		
		User userInfo = (User) request.getSession().getAttribute("user");
	
		  String filePath = _fileUploadSetting.getRootPath()+File.separator+_fileUploadSetting.getUserPath();
		  userInfo.setCreateTime(new Date());
		  if(file.isEmpty()){
			  userInfo.setImage(userInfo.getImage());
			  userInfo.setFirstName(request.getParameter("firstname"));
              userInfo.setLastName("");
	          session.removeAttribute("usertyoes");
	          WebUtils.setSessionAttribute(request, "usertyoes", request.getParameter("firstname"));
              _userRepository.save(userInfo);
	           return "redirect:/myinformation/baby";
	       }
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

	               filePath += fileName;
	               //String lujing = "." + filePath.substring(filePath.lastIndexOf("//") + 1);
	               String lujing =File.separator+_fileUploadSetting.getUserPath()+fileName;
	               Image image = new Image();
	               image.setCreateTime(new Date());
	               image.setFileName(fileName);
	               image.setFilePath(lujing);
	               _imageRepository.save(image);
	               userInfo.setFirstName(request.getParameter("firstname"));
	               userInfo.setLastName("");
	               userInfo.setImage(image);
		           	session.removeAttribute("usertyoes");
		    		WebUtils.setSessionAttribute(request, "usertyoes", request.getParameter("firstname"));
	               _userRepository.save(userInfo);
	               session.removeAttribute("userimg");
		        	WebUtils.setSessionAttribute(request, "userimg", userInfo.getImage().getId());
	               BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
	               stream.write(bytes);
	               stream.close();
	               return "redirect:/myinformation/baby";
	           }
	           catch (Exception e)
	           {
	               model.addAttribute("errorMessage", "上传文件失败");
	               model.addAttribute("child", _userRepository.findOne(userInfo.getId()));
	               return "/personalCenter/create";
	           }
	       }
		/*String email = userInfo.getEmail();
		String mobile = userInfo.getMobile();
		
		String firstName = userInfo.getFirstName();
		String lastName = userInfo.getLastName();
	
		User userA = _userRepository.findByEmail(email);

	
			userA.setMobile(mobile);
			userA.setFirstName(firstName);
			userA.setLastName(lastName);
			_userRepository.save(userA);
			model.addAttribute("olduser", userA);
			System.out.println("mobile" + mobile);*/
			model.addAttribute("message", "修改成功");
		model.addAttribute("userInfo", userInfo);
		return "/myinformation/baby";
	}

	// zyy导游中心
	@RequestMapping(value = "/doCreate1", method = RequestMethod.GET)
	public String doCreate1(HttpServletRequest request, Model model) {
		Guide guideInfo = (Guide) request.getSession().getAttribute("guide");
		model.addAttribute("guideInfo", guideInfo);
		System.out.println("-----------------"+guideInfo.getId());
		// //驾照扫描
		GuideImageRelate guideimage = _guideImageRelateRepository.findByTypes(
				2, guideInfo.getId());
		Image image1 = guideimage.getImage();
		model.addAttribute("image", image1.getId());

		GuideImageRelate guideimage1 = _guideImageRelateRepository.findByTypes(
				1, guideInfo.getId());
		Image image2 = guideimage1.getImage();
		model.addAttribute("image2", image2.getId());
		GuideImageRelate guideimage2 = _guideImageRelateRepository.findByTypes(
				3, guideInfo.getId());
		Image image3 = guideimage2.getImage();
		model.addAttribute("image3", image3.getId());
		List<GuideTags> guideTags= _guGuideTagsRepository.findByGuide(guideInfo);
		model.addAttribute("guideTags", guideTags);
		
		List<GuideImageRelate> image4 = _guideImageRelateRepository.findByTypelist(4, guideInfo.getId());
		model.addAttribute("image4", image4);

		
		List<GuideImageRelate> image5 = _guideImageRelateRepository.findByTypelist(5, guideInfo.getId());
		model.addAttribute("image5", image5);

		
		/*String[] guidetag = request.getParameter("tag").split(",");
		List<Integer> themeIds = new ArrayList<Integer>();
		List<GuideTags> themeActives = new ArrayList<GuideTags>();
		  if(guidetag.length!=0){
				for (int i = 0; i < guidetag.length; i++) {
					GuideTags guidetags =_guGuideTagsRepository.findBytag(guidetag[i]);
					themeIds.add(guidetags.getId());
					themeActives.add(guidetags);
				}
				}
		if (guideInfo == null) {
			return "redirect:/guide/login";
		}*/
		return "/guide/center";
	}

	@RequestMapping(value = "/passwordmain", method = RequestMethod.GET)
	public String password(HttpServletRequest request, Model model) {
		User user = (User) WebUtils.getSessionAttribute(request, "user");
		if (user == null) {
			return "/user/login";
		}
		return "/personalcenter/passwordtenance";
	}

	@RequestMapping(value = "/passwordmain", method = RequestMethod.PUT)
	public String passwordmain(HttpServletRequest request, Model model) {
		String oldPassword = CipherUtil.generatePassword((String) request
				.getParameter("oldPassword"));
		String newPassword = (String) request.getParameter("newPassword");
		User user = (User) WebUtils.getSessionAttribute(request, "user");
		if (user == null || !oldPassword.equals(user.getPassword())) {
			model.addAttribute("message", "旧密码错误，请重新填写！");
			return "/personalcenter/passwordtenance";
		}

		user.setPassword(CipherUtil.generatePassword(newPassword));
		_userRepository.save(user);

		model.addAttribute("message", "密码修改成功！");
		return "/personalcenter/passwordtenance";
	}
}