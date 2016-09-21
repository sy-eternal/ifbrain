package com.jzeen.travel.website.controller;

import com.jzeen.travel.data.entity.*;
import com.jzeen.travel.data.repository.*;
import com.mysql.jdbc.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
	@Autowired
	UserRepository _userRepository;

	@Autowired
	GuideRepository _guideRepository;

	@Autowired
	CityRepository _cityRepository;

	@Autowired
	GuideImageRelateRepository _guideImageRelateRepository;

	@Autowired
	GuideCommentsRepository _guideCommentsRepository;

	@Autowired
	TripBlogRepository _tripBlogRepository;
	   @Autowired
	    RouteRepository _routeBlogRepository;

	@Autowired
	TripBlogCommentsRepository _tripBlogCommentsRepository;

	@Autowired
	TripBlogItemRepository _tripBlogItemRepository;

	@Autowired
	NavigationbarModuleRepository _navigationbarModuleRepository;
    
	@Autowired
	CarouselImgRepository _CarouselImgRepository;
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String loginInit(Model model, HttpServletRequest request) {
		String returnUrl = request.getParameter("returnUrl");
		model.addAttribute("returnUrl", returnUrl);
		List <CarouselImg> carouselimg = _CarouselImgRepository.getAllImgOrderBySortNumber();
    	model.addAttribute("carouselimg", carouselimg);
    	List<NavigationbarModule> navigationbarModule= _navigationbarModuleRepository.findByNavigationBarTypeOrderByTypeAsc(3);
    	if(navigationbarModule.size()>0){
    		model.addAttribute("navigationbarModule", navigationbarModule);
    	}else{
    		model.addAttribute("navigationbarModule", "");
    	}
    	
    	return "/home/index";
	}
	

	
	/*
	 * 新首页
	 */
	/*@RequestMapping(value = "/indexs", method = RequestMethod.GET)
	public String indexs(Model model, HttpServletRequest request) {
		String returnUrl = request.getParameter("returnUrl");
		model.addAttribute("returnUrl", returnUrl);
		return "/home/index";
	}*/
	
	@RequestMapping(value = "/enteruse", method = RequestMethod.GET)
	public String enteruse(Model model, HttpServletRequest request) {
		return "/home/enteruse";
	}

	@RequestMapping(value = "/wechatindex", method = RequestMethod.GET)
	public String wechatLogin(Model model, HttpServletRequest request) {
		String returnUrl = request.getParameter("returnUrl");
		String userId = request.getParameter("userId");
		String signature = request.getParameter("signature");

		// ------------校验链接有效性------
		User user = _userRepository.findOne(Integer.valueOf(userId));

		String[] arr = new String[] { "tr2vel", user.getWechat() };
		// 将token、openId 两个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();

		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}

		MessageDigest md = null;
		String tmpStr = null;

		try {
			md = MessageDigest.getInstance("SHA-1");
			// 将两个参数字符串拼接成一个字符串进行sha1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		content = null;

		// ------------校验链接有效性------

		if (tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false) {
			// 有效请求

			System.out.println("有效的用户请求 " + user.getFirstName()
					+ user.getLastName());
			model.addAttribute("returnUrl", returnUrl);
			WebUtils.setSessionAttribute(request, "user", user);
			WebUtils.setSessionAttribute(request, "usertyoe",
					user.getUserType());
			WebUtils.setSessionAttribute(request, "usertyoes",
					user.getFirstName() + user.getLastName());
		} else {
			System.out.println("无效请求 ");
		}

		return "/home/travelindex";
	}

	// 新首页
/*	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
	
		return "/home/onepage-index";
	}*/

	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String index(HttpServletRequest request, Model model,
			HttpSession session, User user) {
		String returnUrl = request.getParameter("returnUrl");
		if (StringUtils.isNullOrEmpty(returnUrl)) {
			returnUrl = "/home/index";
		}
		try {
			returnUrl = URLDecoder.decode(returnUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		returnUrl = "redirect:" + returnUrl;
		return returnUrl;
	}

	@RequestMapping(value = "/angular", method = RequestMethod.GET)
	public String angular() {
		return "/home/angular";
	}

	@RequestMapping(value = "/loginout", method = RequestMethod.GET)
	public String loginout(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("usertyoes");
		session.removeAttribute("user");

		session.removeAttribute("usertyoe");

		session.removeAttribute("usertyoe");

		session.removeAttribute("guide");
		session.removeAttribute("tripPlan");
		session.removeAttribute("returnUrlTrip");
		session.removeAttribute("svaehelpplan");
		session.removeAttribute("guideorder");
		session.removeAttribute("id");
		session.removeAttribute("childid");
		session.removeAttribute("childids");
		session.removeAttribute("userimg");
		session.removeAttribute("userwechat");
		session.removeAttribute("todownloadtaskid");
		session.removeAttribute("commdityid");
		return "/home/index";

	}

	@RequestMapping(value = "/aboutus", method = RequestMethod.GET)
	public String aboutus(Model model, HttpServletRequest request) {
		return "/home/aboutus";
	}

	/**
	 * 将字节数组转换为十六进制字符串
	 *
	 * @param byteArray
	 * @return
	 */
	private String byteToStr(byte[] byteArray) {
		String strDigest = "";
		for (int i = 0; i < byteArray.length; i++) {
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}

	/**
	 * 将字节转换为十六进制字符串
	 *
	 * @param mByte
	 * @return
	 */
	private String byteToHexStr(byte mByte) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = Digit[mByte & 0X0F];

		String s = new String(tempArr);
		return s;
	}

	// 私人向导
	@RequestMapping(value = "/privateguide", method = RequestMethod.GET)
	public String privateguide(Model model, HttpServletRequest request) {
		Integer type = 3;
		List<GuideImageRelate> guides = _guideImageRelateRepository
				.findByType(type);
		model.addAttribute("guides", guides);
		return "/home/privateguide";
	}

	/*
	 * 私人向导详情页
	 */
	@RequestMapping(value = "/guidedetail", method = RequestMethod.GET)
	public String guidedetail(HttpServletRequest request, Model model) {
		// 导游id
		Integer guideId = Integer.parseInt(request.getParameter("guideId"));
		model.addAttribute("guideId", guideId);
		Guide guide = _guideRepository.findOne(guideId);
		// 通过导游找到关联表
		List<GuideImageRelate> guideImageRelate = _guideImageRelateRepository
				.findByGuide(guide);
		// 通过关联表id和type值写相应的方法找到相对应模块的图片
		// 导游个人生活照
		List<Integer> lifePhotos = new ArrayList<Integer>();
		for (int i = 0; i < guideImageRelate.size(); i++) {
			List<GuideImageRelate> guideImageRelates = _guideImageRelateRepository
					.findByTypeimage(guide.getId(), 5);
			for (int j = 0; j < guideImageRelates.size(); j++) {
				lifePhotos.add(guideImageRelates.get(j).getImage().getId());
			}
		}
		model.addAttribute("lifePhotos", lifePhotos);
		// 当地风景照
		List<Integer> Landscapes = new ArrayList<Integer>();
		for (int i = 0; i < guideImageRelate.size(); i++) {
			List<GuideImageRelate> guideImageRelates2 = _guideImageRelateRepository
					.findByTypeimage(guide.getId(), 4);
			for (int j = 0; j < guideImageRelates2.size(); j++) {
				Landscapes.add(guideImageRelates2.get(j).getImage().getId());
			}
		}
		// 封面照
		List<GuideImageRelate> imageRelates = _guideImageRelateRepository
				.findByTypeimage(guide.getId(), 3);
		model.addAttribute("imageRelates", imageRelates.get(0).getImage()
				.getId());
		model.addAttribute("Landscapes", Landscapes);
		model.addAttribute("guide", guideImageRelate.get(0));
		// 专长领域
		String targs = "";
		List<GuideTags> guideTags = guide.getGuidetag();
		for (int i = 0; i < guideTags.size(); i++) {
			targs += guideTags.get(i).getTag() + ",";
		}
		model.addAttribute("targs", targs);
		return "/home/guidedetail";
	}

	/*
	 * 私人向导评论
	 */
	@RequestMapping(value = "/review", method = RequestMethod.POST)
	public String review(HttpServletRequest request, Model model) {
		Integer guideId = Integer.parseInt(request.getParameter("guideId"));
		User user = (User) WebUtils.getSessionAttribute(request, "user");
		String content = request.getParameter("review");
		Guide guide = _guideRepository.findOne(guideId);
		GuideComments guideComments = new GuideComments();
		guideComments.setComments(content);
		guideComments.setUser(user);
		guideComments.setGuide(guide);
		guideComments.setCreatetime(new Date());
		_guideCommentsRepository.save(guideComments);
		return "redirect:/home/guidedetail?guideId=" + guideId;
	}

	// 经典路线
	@RequestMapping(value = "/classicroute", method = RequestMethod.GET)
	public String classicroute(Model model, HttpServletRequest request) {
	    List <Route> route =_routeBlogRepository.findAll();
	    model.addAttribute("route", route);
		return "/home/classicroute";
	}

	// 游记列表
	@RequestMapping(value = "/tripblog", method = RequestMethod.GET)
	public String pageNew(Model model, HttpServletRequest request) {
		List<TripBlog> tripBlog = _tripBlogRepository.findFirst2();
		
		if (tripBlog.size()!=0) {
			Image image1 = tripBlog.get(0).getImage();
			model.addAttribute("image1", image1);
			String tile1 = tripBlog.get(0).getTile();
			System.out.println(tile1);
			model.addAttribute("tile1", tile1);

		}else{
			model.addAttribute("image1", null);
			model.addAttribute("tile1", null);
		}
		if (tripBlog.size()!=0) {
			Image image2 = tripBlog.get(1).getImage();
			model.addAttribute("image2", image2);
			String tile2 = tripBlog.get(1).getTile();
			model.addAttribute("tile2", tile2);
		}
		else{
			model.addAttribute("image2", null);
			model.addAttribute("tile2", null);
		}
		if (tripBlog.size()!=0) {
			Image image3 = tripBlog.get(2).getImage();
			model.addAttribute("image3", image3);
			String tile3 = tripBlog.get(2).getTile();
			model.addAttribute("tile3", tile3);
		}
		else{
			model.addAttribute("image3", null);
			model.addAttribute("tile3", null);
		}

		List<List> groupList = new ArrayList<List>();

		for (int i = 0; i < tripBlog.size(); i++) {
			/* System.out.println(tripBlog.get(i).getId()); */
			List<TripBlogItem> tripBlogItem = _tripBlogItemRepository
					.findFirst2BytripBlogId(tripBlog.get(i).getId());
			groupList.add(tripBlogItem);

		}

		model.addAttribute("groupList", groupList);

		model.addAttribute("tripBlog", tripBlog);
		return "/home/tripblog";
	}

	// 游记详情
	@RequestMapping(value = "/tripblogitem/{id}", method = RequestMethod.GET)
	public String pageNewItem(@PathVariable Integer id, Model model,
			HttpServletRequest request, HttpSession session) {
		TripBlog tripBlog = _tripBlogRepository.findOne(id);
		List<TripBlogItem> tripBlogItem = _tripBlogItemRepository
				.findBytripBlogId(id);
		for (int i = 0; i < tripBlogItem.size(); i++) {
			int count = i + 1;
			String name = "day" + count;
			tripBlogItem.get(i).setName(name);
		}
		List<TripBlogComments> tripBlogComments = _tripBlogCommentsRepository
				.findBytripBlog(tripBlog);
		model.addAttribute("tripBlogComments", tripBlogComments);
		System.out.println(tripBlog.getId());
		model.addAttribute("tripBlogItem", tripBlogItem);
		model.addAttribute("tripBlog", tripBlog);
		return "/home/tripblogitem";
	}

	@RequestMapping(value = "/tripCommentsSave", method = RequestMethod.GET)
	public String tripsave(Model model, HttpServletRequest request,
			HttpSession session) {

		User user = (User) session.getAttribute("user");
		String textarea = request.getParameter("textarea");
		Integer ids = Integer.parseInt(request.getParameter("tripBlog"));
		TripBlog TripBlog = _tripBlogRepository.findOne(ids);
		TripBlogComments tripBlogComments = new TripBlogComments();
		tripBlogComments.setComment(textarea);
		tripBlogComments.setUser(user);
		tripBlogComments.setTripBlog(TripBlog);
		tripBlogComments.setCreateTime(new Date());
		_tripBlogCommentsRepository.save(tripBlogComments);
		return "redirect:/home/tripblog";
	}

	/**
	 * 写游记
	 */
	@RequestMapping(value = "/writetrip", method = RequestMethod.GET)
	public String writeTrip(Model model, HttpServletRequest request) {

		return "/home/writetrip";
	}

	/*
	 * 游记
	 */

	/**
	 * 美国公司介绍
	 */
	@RequestMapping(value = "/usaincintroduction", method = RequestMethod.GET)
	public String usaIncIntroduction() {
		return "/home/usaincintroduction";
	}
	
	/**
	 * 首页
	 */
	@RequestMapping(value = "/toindex", method = RequestMethod.GET)
	public String index() {
		return "/home/new-travelindex";
	}
	
	/**
	 * 关于我们
	 */
	@RequestMapping(value = "/team", method = RequestMethod.GET)
	public String team() {
		return "/team/team";
	}
	
	/**
	 * 活动
	 */
	@RequestMapping(value = "/activity", method = RequestMethod.GET)
	public String activity() {
		return "/activity/activity";
	}
	
	/**
	 * 课程
	 */
	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String course() {
		return "/course/course";
	}
	
	/**
	 * 财脑
	 */
	@RequestMapping(value = "/ifbrain", method = RequestMethod.GET)
	public String ifbrain() {
		return "/ifbrain/ifbrain";
	}
	/*
	 * 关于我们（我们的项目）
	 * */
	@RequestMapping(value = "/ourproject", method = RequestMethod.GET)
	public String ourproject(Model model, HttpServletRequest request) {
		
		return "/about/ourproject";
	}
	/*
	 * 关于我们（我们的团队）
	 * */
	@RequestMapping(value = "/ourteam", method = RequestMethod.GET)
	public String ourteam(Model model, HttpServletRequest request) {
		
		return "/about/ourteam";
	}
	
	/*
	 * 关于我们（财经素养）
	 * */
	@RequestMapping(value = "/literacy", method = RequestMethod.GET)
	public String literacy(Model model, HttpServletRequest request) {
		
		return "/about/literacy";
	}
	@RequestMapping(value = "/headernew", method = RequestMethod.GET)
	public String headernew(Model model, HttpServletRequest request) {
		
		return "/partial/header-new";
	}
	@RequestMapping(value = "/slide", method = RequestMethod.GET)
	public String slide(Model model, HttpServletRequest request) {
		List <CarouselImg> carouselimg = _CarouselImgRepository.getAllImgOrderBySortNumber();
    	model.addAttribute("carouselimg", carouselimg);
    	List<NavigationbarModule> navigationbarModule= _navigationbarModuleRepository.findByNavigationBarTypeOrderByTypeAsc(3);
    	if(navigationbarModule.size()>0){
    		model.addAttribute("navigationbarModule", navigationbarModule);
    	}else{
    		model.addAttribute("navigationbarModule", "");
    	}
		return "/partial/slide";
	}
}
